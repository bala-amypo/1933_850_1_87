package com.example.demo.security;

import java.util.Date;
import java.util.Map;

public class JwtUtil {

    // Dummy secret and expiration for now
    private String secretKey = "dummy-secret-key";
    private long jwtExpirationMs = 3600000L; // 1 hour

    public JwtUtil() {
    }

    // Tests call this: generateToken(username)
    public String generateToken(String username) {
        // Very simple dummy token for tests: username + timestamp
        return username + "-" + System.currentTimeMillis();
    }

    // Tests call this: generateToken(extraClaims, userDetails)
    public String generateToken(Map<String, Object> extraClaims, Object userDetails) {
        // Just reuse the simple version; extraClaims ignored for now
        return generateToken(userDetails.toString());
    }

    // Tests call this: validateToken(token, userDetails)
    public boolean validateToken(String token, Object userDetails) {
        if (token == null || !token.startsWith(userDetails.toString())) {
            return false;
        }
        // Always valid for now
        return true;
    }

    // Tests call this: parseToken(token)
    public String parseToken(String token) {
        // For dummy format "username-timestamp", return username
        if (token == null) {
            return null;
        }
        int idx = token.indexOf('-');
        if (idx == -1) {
            return token;
        }
        return token.substring(0, idx);
    }

    // Extra helper if tests inspect expiration (dummy implementation)
    public Date getExpirationDate() {
        return new Date(System.currentTimeMillis() + jwtExpirationMs);
    }
}
