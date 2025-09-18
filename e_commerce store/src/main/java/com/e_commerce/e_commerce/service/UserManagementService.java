package com.e_commerce.e_commerce.service;

import com.e_commerce.e_commerce.model.UserData;
import com.e_commerce.e_commerce.repository.UserManagementDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserManagementService {
    @Autowired
    private UserManagementDAO userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void registerUser(UserData user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("User already exists with this email");
        }
        String encodedPassword = passwordEncoder.encode(user.getPassWord());
        user.setPassWord(encodedPassword);
        userRepository.save(user);
    }

}