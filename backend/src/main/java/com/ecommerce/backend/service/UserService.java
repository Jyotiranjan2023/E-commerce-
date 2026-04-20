package com.ecommerce.backend.service;

import com.ecommerce.backend.model.User;
    
    public interface UserService {

        User registerUser(User user);

        String login(String email, String password); // ✅ add this
    }
