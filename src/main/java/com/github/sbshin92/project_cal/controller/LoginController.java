package com.github.sbshin92.project_cal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class LoginController {
	@GetMapping("/login")
    public String loginSuccess() {
        return "login/login";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }
 
}