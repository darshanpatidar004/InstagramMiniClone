package com.trueigtech.instaclone.service;

import com.trueigtech.instaclone.model.User;
import com.trueigtech.instaclone.repository.UserRepository;
import com.trueigtech.instaclone.security.JwtUtil;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String register(User user) {

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        return "User registered successfully";
    }

    public String login(String email, String password) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        boolean valid = passwordEncoder.matches(password, user.getPassword());

        if (!valid) {
            throw new RuntimeException("Invalid credentials");
        }

        return JwtUtil.generateToken(user.getEmail());
    }
}
