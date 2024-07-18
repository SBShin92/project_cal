package com.github.sbshin92.project_cal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.sbshin92.project_cal.data.vo.UserVO;
import com.github.sbshin92.project_cal.service.UserService;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/login")
@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
//    private PasswordEncoder passwordEncoder;

    @GetMapping({"", "/"})
    public String loginPage() {
        return "login/login";
    }

    @PostMapping
    public String login(@RequestParam("email") String email, 
                        @RequestParam("password") String password, 
                        HttpSession session) {
        UserVO user = userService.getUserByEmail(email);
//        && passwordEncoder.matches(password, user.getUserPassword())
        if (user != null && user.getUserPassword().equals(password))  {
            session.setAttribute("authUser", user);
            return "redirect:/calendar";
        } else {
            return "redirect:/login";
        }
    }
    
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("authUser");
        session.invalidate();
        return "redirect:/";
    }
}
