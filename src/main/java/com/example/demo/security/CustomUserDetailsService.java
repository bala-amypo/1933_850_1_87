package com.example.demo.security;

import com.example.demo.repository.UserRepository;

public class CustomUserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Add real load logic later; for now only constructor is required for compilation
}
