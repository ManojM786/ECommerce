package com.e_commerce.e_commerce.controller;

import com.e_commerce.e_commerce.model.UserData;
import com.e_commerce.e_commerce.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.validation.BindingResult;
import jakarta.validation.Valid;
import com.e_commerce.e_commerce.model.Role;
import com.e_commerce.e_commerce.model.LoginDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import com.e_commerce.e_commerce.service.UserAlreadyExistsException;

@Controller
public class UserManagement {

    @Autowired
    private UserManagementService userService;

    private static final Logger logger = LoggerFactory.getLogger(UserManagement.class);

    @GetMapping("/login")
    public String login(Model model) {
        if (!model.containsAttribute("loginDTO")) {
            model.addAttribute("loginDTO", new LoginDTO());
        }
        return "login";
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        logger.info("/signup endpoint accessed");
        if (!model.containsAttribute("userData")) {
            model.addAttribute("userData", new UserData());
        }
        return "signin";
    }

    @PostMapping("/api/signup")
    public ModelAndView signup(@Valid @ModelAttribute("userData") UserData userData, BindingResult bindingResult) {
        ModelAndView mav = new ModelAndView("signin");

        if (bindingResult.hasErrors()) {
            return mav;
        }

        try {
            userData.setUserRole(Role.CUSTOMER);
            userService.registerUser(userData);
            mav.setViewName("redirect:/login");
        } catch (UserAlreadyExistsException e) {
            mav.addObject("message", "User already exists with this email");
            mav.addObject("userData", userData);
        } catch (Exception e) {
            logger.error("Signup failed for user: " + userData.getEmail(), e);
            mav.addObject("message", "Signup failed: " + e.getMessage());
        }
        return mav;
    }

    @GetMapping("/test")
    public String test() {
        return "index";
    }
}