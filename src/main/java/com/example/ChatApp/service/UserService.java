package com.example.ChatApp.service;

import com.example.ChatApp.model.AuthRequest;
import com.example.ChatApp.model.User;
import com.example.ChatApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    public User registerUser(AuthRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(encoder.encode(request.getPassword()));
        user.setRole("USER");
        return userRepository.save(user);
    }

    public User validateCredentials(AuthRequest request) {
        return userRepository.findByUsername(request.getUsername())
                .filter(user -> encoder.matches(request.getPassword(), user.getPassword()))
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));
    }
}
