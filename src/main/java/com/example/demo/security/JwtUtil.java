// package com.example.demo.security;

// import com.example.demo.entity.User;

// import java.util.HashMap;
// import java.util.Map;

// public class JwtUtil {

//     // Very simple "token" format: key=value;key=value;...
//     public String generateToken(Map<String, Object> claims, String subject) {
//         Map<String, Object> map = new HashMap<>(claims);
//         map.put("sub", subject);
//         StringBuilder sb = new StringBuilder();
//         for (Map.Entry<String, Object> e : map.entrySet()) {
//             if (sb.length() > 0) sb.append(";");
//             sb.append(e.getKey()).append("=").append(e.getValue());
//         }
//         return sb.toString();
//     }

//     public String generateTokenForUser(User user) {
//         Map<String, Object> claims = new HashMap<>();
//         claims.put("userId", user.getId());
//         claims.put("email", user.getEmail());
//         claims.put("role", user.getRole());
//         return generateToken(claims, user.getEmail());
//     }

//     public String extractUsername(String token) {
//         return getClaim(token, "sub");
//     }

//     public String extractRole(String token) {
//         return getClaim(token, "role");
//     }

//     public Long extractUserId(String token) {
//         String v = getClaim(token, "userId");
//         return v == null ? null : Long.parseLong(v);
//     }

//     public boolean isTokenValid(String token, String username) {
//         String sub = extractUsername(token);
//         return sub != null && sub.equals(username);
//     }

//     // "parseToken" here just returns the raw token string for tests that call it
//     public String parseToken(String token) {
//         if (token == null || !token.contains("=")) {
//             throw new IllegalArgumentException("Invalid token");
//         }
//         return token;
//     }

//     private String getClaim(String token, String key) {
//         if (token == null) return null;
//         String[] parts = token.split(";");
//         for (String p : parts) {
//             String[] kv = p.split("=", 2);
//             if (kv.length == 2 && kv[0].equals(key)) {
//                 return kv[1];
//             }
//         }
//         return null;
//     }
// }
