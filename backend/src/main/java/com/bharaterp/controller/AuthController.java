package com.bharaterp.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");
        
        System.out.println("=== LOGIN ATTEMPT ===");
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        
        Map<String, Object> response = new HashMap<>();
        
        if ("admin".equals(username) && "admin123".equals(password)) {
            response.put("token", "test-token-12345");
            response.put("username", username);
            response.put("role", "SUPER_ADMIN");
            response.put("message", "Login successful");
            System.out.println("Login SUCCESS!");
            return ResponseEntity.ok(response);
        } else {
            response.put("error", "Invalid credentials");
            System.out.println("Login FAILED!");
            return ResponseEntity.status(401).body(response);
        }
    }
    
    @GetMapping("/test")
    public String test() {
        return "Backend is working!";
    }
}