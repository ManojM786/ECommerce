package com.e_commerce.e_commerce.controller.viewcontrollers;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.e_commerce.e_commerce.model.UserData;

@Controller
public class UserManagement {

    @RequestMapping({"/","/home"})
    public String getHomePage(HttpSession session, Model model) {
        UserData user = (UserData) session.getAttribute("user");
        model.addAttribute("user", user);
        model.addAttribute("message", "");
        return "index";
    }

    @RequestMapping("login")
    public String getLoginPage(HttpSession session, Model model) {
        UserData user = (UserData) session.getAttribute("user");
        model.addAttribute("user", user);
        model.addAttribute("message", "");
        return "login";
    }

    @RequestMapping("signin")
    public String getSignPage(HttpSession session, Model model) {
        UserData user = (UserData) session.getAttribute("user");
        model.addAttribute("user", user);
        model.addAttribute("message", "");
        return "signin";
    }
}
