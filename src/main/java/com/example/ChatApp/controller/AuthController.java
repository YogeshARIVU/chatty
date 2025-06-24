package com.example.ChatApp.controller;

import com.example.ChatApp.model.AuthRequest;
import com.example.ChatApp.model.User;
import com.example.ChatApp.service.UserService;
import com.example.ChatApp.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public String register(@RequestBody AuthRequest request) {
        userService.registerUser(request);
        return "User registered!";
    }

    @PostMapping("/login")
    public String login(@RequestBody AuthRequest request) {
        User user = userService.validateCredentials(request);
        return jwtUtil.generateToken(user.getUsername(), user.getRole());
    }
}
