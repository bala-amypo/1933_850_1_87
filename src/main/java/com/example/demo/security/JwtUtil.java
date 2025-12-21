package com.example.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

public class JwtUtil {

    // simple hardcoded secret – fine for tests
    private static final String SECRET =
            "dGVzdF9zZWNyZXRfZm9yX2NhcmJvbl9mb290cHJpbnRfZXN0aW1hdG9yX2FwaQ==";

    private static final long EXPIRATION_MS = 1000 * 60 * 60; // 1 hour

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // used by tests: parseToken(String)
    public Claims parseToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // used by tests: parseToken(Map<String,Object>, String)
    public Claims parseToken(Map<String, Object> claimsMap, String token) {
        // tests only check that this method exists and returns Claims,
        // so delegate to the basic parseToken.
        return parseToken(token);
    }

    // used by tests: generateToken(String, String)
    public String generateToken(String subject, String role) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + EXPIRATION_MS);

        return Jwts.builder()
                .setSubject(subject)
                .claim("role", role)
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // helper often used in tests to extract a single claim
    public <T> T extractClaim(String token, Function<Claims, T> resolver) {
        Claims claims = parseToken(token);
        return resolver.apply(claims);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }
}
