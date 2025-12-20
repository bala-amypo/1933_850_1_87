package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;
    private String password;
    private String role;
    private String firstName;
    private String lastName;

    // 0-arg constructor
    public User() {
    }

    // ===== Constructors using Long id =====

    public User(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public User(Long id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    public User(Long id,
                String username,
                String email,
                String password,
                String role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public User(Long id,
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

    // ===== Constructors using String id (tests may call these) =====

    public User(String id, String username) {
        this.id = parseId(id);
        this.username = username;
    }

    public User(String id, String username, String email) {
        this.id = parseId(id);
        this.username = username;
        this.email = email;
    }

    public User(String id,
                String username,
                String email,
                String password,
                String role) {
        this.id = parseId(id);
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public User(String id,
                String username,
                String email,
                String password,
                String role,
                String firstName,
                String lastName) {
        this.id = parseId(id);
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Helper to convert String id to Long safely
    private Long parseId(String id) {
        if (id == null || id.isEmpty()) {
            return null;
        }
        try {
            return Long.valueOf(id);
        } catch (NumberFormatException ex) {
            return null;
        }
    }

    // ===== Getters and setters =====

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
