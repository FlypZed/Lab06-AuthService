package com.eci.Lab06_AuthService.controllers;

import com.eci.Lab06_AuthService.security.JwtUtil;
import com.eci.Lab06_AuthService.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestParam String username, @RequestParam String password) {
        if (userService.userExists(username)) {
            return ResponseEntity.badRequest().body("Username already exists!");
        }
        userService.registerUser(username, password);
        return ResponseEntity.ok("User registered successfully!");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
        boolean isAuthenticated = userService.authenticateUser(username, password);
        if (!isAuthenticated) {
            return ResponseEntity.badRequest().body("Invalid credentials");
        }
        String token = jwtUtil.generateToken(username);
        return ResponseEntity.ok(token);
    }
}
