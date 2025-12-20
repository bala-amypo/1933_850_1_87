// Minimal constructor: id + username (used in some tests)
public User(String id, String username) {
    this.id = id;
    this.username = username;
}

// Full constructor with 7 String arguments (id, username, email, password, role, firstName, lastName)
public User(String id,
            String username,
            String email,
            String password,
            String role,
            String firstName,
            String lastName) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.password = password;
    this.role = role;
    this.firstName = firstName;
    this.lastName = lastName;
}
