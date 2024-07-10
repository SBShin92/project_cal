package com.github.sbshin92.project_cal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	@GetMapping("/login")
	public String loginSuccess() {
		return "login"; // login.jsp 페이지를 반환
	}

	@GetMapping("/home")
	public String home() {
		return "home"; // 로그인 성공 후 홈 페이지로 리디렉션
	}
}