import com.example.demo.entity.User;

// ...

public String generateTokenForUser(User user) {
    if (user == null) return null;
    String username = user.getUsername();
    String role = user.getRole();
    return generateToken(username, role);
}
