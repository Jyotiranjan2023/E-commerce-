package com.ecommerce.backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.backend.model.User;
import com.ecommerce.backend.repository.UserRepository;
import com.ecommerce.backend.security.JwtUtil;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    // 🔹 Register User
    @Override
    public User registerUser(User user) {
        return userRepository.save(user);
    }

    // 🔹 Login User (JWT)
    @Override
    public String login(String email, String password) {

        Optional<User> userOpt = userRepository.findByEmail(email);

        if (userOpt.isPresent()) {
            User user = userOpt.get();

            // ⚠️ Plain password check (we will secure later)
            if (user.getPassword().equals(password)) {
                return jwtUtil.generateToken(email);
            }
        }

        throw new RuntimeException("Invalid email or password");
    }
}