package com.github.sbshin92.project_cal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	// 로그인 페이지를 반환하는 메서드
	@GetMapping("/login")
	public String login() {
		return "login"; // 로그인 폼이 있는 뷰 이름을 반환
	}

	// Home 페이지를 반환하는 메서드
	@GetMapping("/home")
	public String home() {
		return "home"; // 로그인 성공 후 보여줄 뷰 이름
	}
}