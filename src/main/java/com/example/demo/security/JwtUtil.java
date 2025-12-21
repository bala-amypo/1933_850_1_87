package com.example.demo.security;

import com.example.demo.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class JwtUtil {

    // base64 secret (any sufficiently long random string, encoded)
    private static final String SECRET =
            "dGhpc19pc19hX3Rlc3Rfc2VjcmV0X2Zvcl9jYXJib25fZm9vdHByaW50X2FwaV9qd3Q=";

    private static final long EXPIRATION_MS = 1000L * 60 * 60; // 1 hour

    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // ===== Core helpers =====

    private Claims parseClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public <T> T extractClaim(String token, Function<Claims, T> resolver) {
        Claims claims = parseClaims(token);
        return resolver.apply(claims);
    }

    // ===== Methods explicitly required by helper doc =====

    // 1) generateToken(Map<String,Object>, String)
    public String generateToken(Map<String, Object> extraClaims, String subject) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + EXPIRATION_MS);

        return Jwts.builder()
                .claims(extraClaims)
                .subject(subject)
                .issuedAt(now)
                .expiration(expiry)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // 2) generateTokenForUser(User user) – includes userId, email, role
    public String generateTokenForUser(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getId());
        claims.put("email", user.getEmail());
        claims.put("role", user.getRole());
        return generateToken(claims, user.getEmail());
    }

    // 3) parseToken(String token) – tests call this
    public Claims parseToken(String token) {
        return parseClaims(token);
    }

    // 4) extractUsername(String token) – returns subject (email)
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // 5) extractRole(String token) – role claim
    public String extractRole(String token) {
        return extractClaim(token, claims -> claims.get("role", String.class));
    }

    // 6) extractUserId(String token) – userId claim
    public Long extractUserId(String token) {
        return extractClaim(token, claims -> claims.get("userId", Long.class));
    }

    // 7) isTokenValid(String token, String username)
    public boolean isTokenValid(String token, String username) {
        String subject = extractUsername(token);
        Date expiration = extractClaim(token, Claims::getExpiration);
        return subject != null
                && subject.equals(username)
                && expiration != null
                && expiration.after(new Date());
    }
}
