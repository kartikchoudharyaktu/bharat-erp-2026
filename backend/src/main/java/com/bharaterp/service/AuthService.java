package com.bharaterp.service;

import org.springframework.stereotype.Service;

@Service
public class AuthService {
    public String login(String username, String password) {
        // Authentication logic
        return "Login successful";
    }
    
    public String register(String username, String password, String email) {
        // Registration logic
        return "Registration successful";
    }
    
    public String logout(String token) {
        // Logout logic
        return "Logout successful";
    }
}
