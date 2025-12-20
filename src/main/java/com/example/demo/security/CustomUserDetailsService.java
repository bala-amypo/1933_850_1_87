package com.example.demo.security;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

public class CustomUserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Tests call this with a username and expect a User back
    public User loadUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }
}
