package com.e_commerce.e_commerce.controller.viewcontrollers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserManagement {

    @RequestMapping("/")
    public String getHomePage(){

        return "index";
    }

    @RequestMapping("login")
    public String getLoginPage(){

        return "login";
    }
}
