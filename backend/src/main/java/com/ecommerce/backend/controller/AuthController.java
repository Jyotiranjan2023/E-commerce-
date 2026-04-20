package com.ecommerce.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import com.ecommerce.backend.dto.LoginRequest;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.backend.model.User;
import com.ecommerce.backend.service.UserService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User register(@RequestBody User user) {

        System.out.println("EMAIL: " + user.getEmail()); // DEBUG

        return userService.registerUser(user);
        
    }

@PostMapping("/login")
public String login(@RequestBody LoginRequest request) {
    return userService.login(request.getEmail(), request.getPassword());
}
}