package com.github.sbshin92.project_cal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@RequestMapping("/login")
@Controller
public class LoginController {

    @GetMapping
    public String login() {
        return "login/login"; // login.jsp 페이지를 반환
    }

    @GetMapping("/home")
    public String home() {
        return "home"; // 홈 페이지를 반환
    }
}