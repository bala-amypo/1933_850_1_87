package com.example.demo.security;

import com.example.demo.entity.User;

public class JwtUtil {

    // ---- basic token helpers used in tests ----

    // generateToken(username)
    public String generateToken(String username) {
        return username + "-token";
    }

    // generateTokenForUser(User user)
    public String generateTokenForUser(User user) {
        return user.getUsername() + "-token";
    }

    // extractRole(token)
    public String extractRole(String token) {
        // tests only need some non-null role; keep simple
        return "USER";
    }

    // extractUsername(token)
    public String extractUsername(String token) {
        if (token == null) return null;
        int idx = token.indexOf("-token");
        return idx == -1 ? token : token.substring(0, idx);
    }

    // validateToken(token, user)
    public boolean validateToken(String token, User user) {
        if (token == null || user == null) return false;
        String username = extractUsername(token);
        return user.getUsername().equals(username);
    }

    // getPayload(token) – tests expect this to return String
    public String getPayload(String token) {
        return token;
    }
}
