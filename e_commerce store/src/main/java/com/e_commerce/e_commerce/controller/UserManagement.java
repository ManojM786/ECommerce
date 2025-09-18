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
import org.springframework.ui.Model;

@Controller
public class UserManagement {

    @Autowired
    private UserManagementService userService;

    @GetMapping("/login")
    public String login(Model model) {
        if (!model.containsAttribute("loginDTO")) {
            model.addAttribute("loginDTO", new LoginDTO());
        }
        return "login";
    }

    @GetMapping("/signup")
    public String signup(Model model) {
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
        } catch (IllegalArgumentException e) {
            mav.addObject("message", e.getMessage());
            mav.addObject("userData", userData);
        } catch (Exception e) {
            mav.addObject("message", "Signup failed: " + e.getMessage());
        }
        return mav;
    }

    @GetMapping("/test")
    public String test() {
        return "index";
    }
}