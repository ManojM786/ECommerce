package com.e_commerce.e_commerce.controller.restcontroller;

import com.e_commerce.e_commerce.model.ApiReturnData;
import com.e_commerce.e_commerce.model.ApiStatus;
import com.e_commerce.e_commerce.model.UserData;
import com.e_commerce.e_commerce.service.UserManagementService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@RequestMapping("/api")
public class UserManagementRest {

    @Autowired
    private UserManagementService userService;

    @PostMapping("/signUp")
    public String signUp(@ModelAttribute UserData userData, Model model) {
        String response = userService.saveUser(userData);
        model.addAttribute("message", response);
        return "signin";
    }

    @RequestMapping("/logout")
    public String logout(SessionStatus status) {
        status.setComplete();
        return "redirect:/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UserData userdata, Model model, HttpSession session) {
        String result = userService.checkLogin(userdata);
        if ("Login successful".equals(result)) {
            UserData user = userService.getUserByEmail(userdata.getEmail());
            session.setAttribute("user", user);
            return "redirect:/";
        } else {
            model.addAttribute("message", result);
            return "login";
        }
    }

}
