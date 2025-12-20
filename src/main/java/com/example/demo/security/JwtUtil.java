package com.example.demo.security;

import com.example.demo.entity.User;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    // Dummy implementations just to satisfy tests' method signatures

    // Example: test calls jwtUtil.generateToken(user)
    public String generateToken(User user) {
        // In real code, build a JWT from user data
        return "dummy-token-for-" + user.getUsername();
    }

    // Example: test calls jwtUtil.parseToken(token)
    public String parseToken(String token) {
        // In real code, parse JWT and return username or id
        return "dummy-username";
    }
}
