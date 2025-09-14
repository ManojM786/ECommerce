package com.e_commerce.e_commerce.controller.apicontroller;


import com.e_commerce.e_commerce.model.UserData;
import com.e_commerce.e_commerce.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/api")
public class UserManagementApi {

    @Autowired
    private UserManagementService userService;

    @PostMapping("/login")
    public ModelAndView login(@RequestParam String email, @RequestParam String password) {
        userService.loginUser(email, password);
        return new ModelAndView("redirect:/index");
    }

    @PostMapping("/signup")
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
