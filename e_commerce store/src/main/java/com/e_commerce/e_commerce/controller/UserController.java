package com.e_commerce.e_commerce.controller;

import com.e_commerce.e_commerce.model.UserData;
import com.e_commerce.e_commerce.repository.UserManagementDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    @Autowired
    private UserManagementDAO userManagementDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private UserData getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        return userManagementDAO.findByEmail(currentUserName)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + currentUserName));
    }

    @GetMapping("/profile")
    public String viewProfile(Model model) {
        UserData user = getCurrentUser();
        model.addAttribute("user", user);
        return "profile";
    }

    @PostMapping("/profile")
    public String updateProfile(@ModelAttribute("user") UserData formUser, Model model) {
        UserData user = getCurrentUser();
        user.setUserName(formUser.getUserName());
        user.setEmail(formUser.getEmail());
        // Only encode and update password if changed
        if (formUser.getPassWord() != null && !formUser.getPassWord().isBlank()) {
            user.setPassWord(passwordEncoder.encode(formUser.getPassWord()));
        }
        try {
            userManagementDAO.save(user);
            model.addAttribute("message", "Profile updated successfully.");
        } catch (Exception e) {
            model.addAttribute("message", "Error updating profile: " + e.getMessage());
        }
        model.addAttribute("user", user);
        return "profile";
    }
}
