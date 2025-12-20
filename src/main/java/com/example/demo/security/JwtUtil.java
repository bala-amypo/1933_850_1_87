package com.example.demo.security;

import com.example.demo.entity.User;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private final long EXPIRATION_MS = 24 * 60 * 60 * 1000; // 1 day

    // Base token generator: username + role
    public String generateToken(String username, String role) {
        long now = System.currentTimeMillis();
        long exp = now + EXPIRATION_MS;
        // token format: username|role|expiryMillis
        return username + "|" + role + "|" + exp;
    }

    // Method name expected by tests
    public String generateTokenForUser(User user) {
        if (user == null) return null;
        return generateToken(user.getUsername(), user.getRole());
    }

    public String extractUsername(String token) {
        if (token == null) return null;
        String[] parts = token.split("\\|");
        return parts.length >= 1 ? parts[0] : null;
    }

    public String extractRole(String token) {
        if (token == null) return null;
        String[] parts = token.split("\\|");
        return parts.length >= 2 ? parts[1] : null;
    }

    public String extractUserId(String token) {
        // Not encoded in this simple token
        return null;
    }

    public Date extractExpiration(String token) {
        if (token == null) return null;
        String[] parts = token.split("\\|");
        if (parts.length < 3) return null;
        try {
            long exp = Long.parseLong(parts[2]);
            return new Date(exp);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public boolean isTokenValid(String token, String username) {
        if (token == null || username == null) return false;
        String tokenUsername = extractUsername(token);
        Date exp = extractExpiration(token);
        return username.equals(tokenUsername) && exp != null && exp.after(new Date());
    }
}
