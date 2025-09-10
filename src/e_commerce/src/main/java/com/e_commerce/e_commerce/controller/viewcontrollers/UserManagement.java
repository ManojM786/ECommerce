package com.e_commerce.e_commerce.controller.viewcontrollers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserManagement {

    @RequestMapping({"/","/home"})
    public String getHomePage(){
        return "index";
    }

    @RequestMapping("login")
    public String getLoginPage(){
        return "login";
    }

    @RequestMapping("signin")
    public String getSignPage(){
        return "signin";
    }
}
