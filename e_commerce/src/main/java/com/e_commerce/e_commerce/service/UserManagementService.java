package com.e_commerce.e_commerce.service;

import com.e_commerce.e_commerce.model.UserData;
import com.e_commerce.e_commerce.repository.UserManagementDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Pattern;


@Service
public class UserManagementService {
    private String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private String nameRegex = "^[A-Za-z0-9]{6,}$";

    @Autowired
    private UserManagementDAO daoManger;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public String saveUser(UserData data) {
        if (data.getUserName() == null || !Pattern.matches(nameRegex, data.getUserName())) {
            return "Username must be at least 6 characters and contain only letters or numbers";
        }
        if (data.getEmail() == null || !Pattern.matches(emailRegex, data.getEmail())) {
            return "Invalid email format";
        }
        if (data.getUserRole() == null) {
            return "User role must be provided";
        }
        try {
            Optional<UserData> resultData = daoManger.findByEmail(data.getEmail());
            if (resultData.isPresent()) {
                return "Email is already registered";
            }
            // Encode password before saving
            data.setPassWord(passwordEncoder.encode(data.getPassWord()));
            daoManger.save(data);
            return "User saved successfully";
        } catch (Exception e) {
            return "Unexpected Server Error. Please try again later.";
        }
    }

    public String checkLogin(UserData userdata) {
        Optional<UserData> resultData = daoManger.findByEmail(userdata.getEmail());
        if (resultData.isPresent()) {
            UserData existingUser = resultData.get();
            String storedPassword = existingUser.getPassWord();
            if (!existingUser.getUserRole().equals(userdata.getUserRole())) {
                return "Role not matched";
            }
            if (storedPassword.equals(userdata.getPassWord())) {
                return "Login successful";
            } else {
                return "Incorrect password";
            }
        } else {
            return "No email found in user data";
        }
    }

    public UserData getUserByEmail(String email) {
        return daoManger.findByEmail(email).orElse(null);
    }

}
