package com.example.demo.security;

import com.example.demo.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
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

    private static final String SECRET =
            "dGhpc19pc19hX3Rlc3Rfc2VjcmV0X2Zvcl9jYXJib25fZm9vdHByaW50X2FwaV9qd3Q=";

    private static final long EXPIRATION_MS = 1000L * 60 * 60; // 1 hour

    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // ----------------------------------------------------------------
    // Internal helpers
    // ----------------------------------------------------------------

    // low-level parser: returns Jws<Claims> which HAS getPayload()
    private Jws<Claims> parseJws(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token);
    }

    // tests expect: jwtUtil.parseToken(token).getPayload()
    public Jws<Claims> parseToken(String token) {
        return parseJws(token);
    }

    // convenience: get only Claims when needed
    private Claims getClaims(String token) {
        return parseJws(token).getPayload();
    }

    public <T> T extractClaim(String token, Function<Claims, T> resolver) {
        Claims claims = getClaims(token);
        return resolver.apply(claims);
    }

    // ----------------------------------------------------------------
    // Required public API from helper document
    // ----------------------------------------------------------------

    // generateToken(Map<String,Object>, String)
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

    // generateTokenForUser(User)
    public String generateTokenForUser(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getId());
        claims.put("email", user.getEmail());
        claims.put("role", user.getRole());
        return generateToken(claims, user.getEmail());
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String extractRole(String token) {
        return extractClaim(token, c -> c.get("role", String.class));
    }

    public Long extractUserId(String token) {
        return extractClaim(token, c -> c.get("userId", Long.class));
    }

    public boolean isTokenValid(String token, String username) {
        String subject = extractUsername(token);
        Date expiration = extractClaim(token, Claims::getExpiration);
        return subject != null
                && subject.equals(username)
                && expiration != null
                && expiration.after(new Date());
    }
}
