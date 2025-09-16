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
import org.springframework.validation.BindingResult;
import jakarta.validation.Valid;
import com.e_commerce.e_commerce.model.Role;
import com.e_commerce.e_commerce.model.LoginDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Controller
public class UserManagement {

    @Autowired
    private UserManagementService userService;

    private static final Logger logger = LoggerFactory.getLogger(UserManagement.class);

    @GetMapping("/login")
    public String login(org.springframework.ui.Model model) {
        if (!model.containsAttribute("loginDTO")) {
            model.addAttribute("loginDTO", new LoginDTO());
        }
        return "login";
    }

    @GetMapping("/signup")
    public String signup(org.springframework.ui.Model model) {
        logger.info("/signup endpoint accessed");
        if (!model.containsAttribute("userData")) {
            model.addAttribute("userData", new com.e_commerce.e_commerce.model.UserData());
        }
        return "signin";
    }

    @PostMapping("/api/signup")
    public ModelAndView signup(@Valid @ModelAttribute("userData") UserData userData, BindingResult bindingResult) {
        ModelAndView mav = new ModelAndView("signin");
        if (bindingResult.hasErrors()) {
            mav.addObject("org.springframework.validation.BindingResult.userData", bindingResult);
            mav.addObject("userData", userData);
            return mav;
        }
        try {
            userData.setUserRole(Role.CUSTOMER); // Enforce only CUSTOMER role
            userService.registerUser(userData);
            mav.setViewName("redirect:/login");
        } catch (com.e_commerce.e_commerce.service.UserAlreadyExistsException e) {
            mav.addObject("message", "User already exists with this email");
            mav.addObject("userData", userData);
            return mav;
        } catch (Exception e) {
            mav.addObject("message", "Signup failed: " + e.getMessage());
        }
        return mav;
    }

    @GetMapping("/test")
    public String test() {
        return "index"; // Use your existing index.html template for a simple test
    }
}
