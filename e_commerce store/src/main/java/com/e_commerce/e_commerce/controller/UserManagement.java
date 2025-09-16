package com.e_commerce.e_commerce.controller;

import com.e_commerce.e_commerce.model.UserData;
import com.e_commerce.e_commerce.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserManagement {

    @Autowired
    private UserManagementService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/signup")
    public String signup() {
        return "signin";
    }

    @PostMapping("/api/login")
    public ModelAndView login(@RequestParam String email, @RequestParam String password) {
        userService.loginUser(email, password);
        return new ModelAndView("redirect:/index");
    }

    @PostMapping("/api/signup")
    public ModelAndView signup(@ModelAttribute UserData userData) {
        ModelAndView mav = new ModelAndView("signin");
        try {
            userService.registerUser(userData);
            mav.setViewName("redirect:/login");
        } catch (Exception e) {
            mav.addObject("message", "Signup failed: " + e.getMessage());
        }
        return mav;
    }
}
