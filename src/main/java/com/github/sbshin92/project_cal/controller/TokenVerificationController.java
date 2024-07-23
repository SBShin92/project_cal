package com.github.sbshin92.project_cal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.sbshin92.project_cal.service.TokenService;

@Controller
public class TokenVerificationController {
	
	//추가
	private TokenService tokenService;
	

    @GetMapping("/verify-token")
    public String showVerifyTokenPage() {
        return "login/verify-token";
    }

    @PostMapping("/verify-token")
    public String verifyToken(@RequestParam("token") String token, Model model) {
        // 토큰 검증 로직을 추가합니다.
        if (isValidToken(token)) {
            return "redirect:/calendar";
        } else {
            model.addAttribute("error", "Invalid token. Please try again.");
            return "login/verify-token";
        }
    }

    private boolean isValidToken(String token) {
        // 토큰 유효성 검증 로직을 추가합니다.
        return true; // 예시로 항상 true를 반환합니다.
    }
}