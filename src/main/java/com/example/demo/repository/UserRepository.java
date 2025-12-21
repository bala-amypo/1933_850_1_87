package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // Remove this because User entity has no 'username' field
    // Optional<User> findByUsername(String username);

    // Check if a user exists by email
    boolean existsByEmail(String email);

    // Find a user by email
    Optional<User> findByEmail(String email);
}
