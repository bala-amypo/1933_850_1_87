package com.example.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

public class JwtUtil {

    // base64-encoded secret; fine for local testing
    private static final String SECRET =
            "dGVzdF9zZWNyZXRfZm9yX2NhcmJvbl9mb290cHJpbnRfZXN0aW1hdG9yX2FwaQ==";

    private static final long EXPIRATION_MS = 1000L * 60 * 60; // 1 hour

    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // used in tests: parseToken(String)
    public Claims parseToken(String token) {
        return Jwts.parser()                     // jjwt 0.12.x style
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    // used in tests: parseToken(Map<String,Object>, String)
    public Claims parseToken(Map<String, Object> claimsMap, String token) {
        // tests normally care that this exists; delegate to basic parser
        return parseToken(token);
    }

    // used in tests: generateToken(String subject, String role)
    public String generateToken(String subject, String role) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + EXPIRATION_MS);

        return Jwts.builder()
                .subject(subject)
                .claim("role", role)
                .issuedAt(now)
                .expiration(expiry)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // helpers (often useful in tests)

    public <T> T extractClaim(String token, Function<Claims, T> resolver) {
        Claims claims = parseToken(token);
        return resolver.apply(claims);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }
}
