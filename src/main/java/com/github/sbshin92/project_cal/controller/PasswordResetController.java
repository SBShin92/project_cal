package com.github.sbshin92.project_cal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.sbshin92.project_cal.service.UserService;

@Controller
@RequestMapping("/password")
public class PasswordResetController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/resetrequest")
	public String resetRequest() {
	    return "login/reset_request";
	}

	@PostMapping("/resetrequest")
	public String resetPasswordRequest(@RequestParam String email, Model model) {
		userService.createPasswordResetTokenForUser(email);
		model.addAttribute("message","비밀번호 초기화 이메일이 발송되었습니다");
		return "redirect:/password/resetrequest";
	}
	
	@GetMapping("/reset")
	public String showResetForm(@RequestParam("token") String token,Model model) {
		if(userService.validatePasswordResetToken(token)) {
			model.addAttribute("token",token);
			return "password/reset_form";
		}else {
			model.addAttribute("message","유효하지 않은 토큰입니다");
			return "login/reset_request";
		}
	}
	
	@PostMapping("/reset")
	public String resetPassword(@RequestParam String token,@RequestParam String newPassword, Model model) {
		if(userService.validatePasswordResetToken(token)) {
			userService.resetPassword(token, newPassword);
			return "login/reset_success";
		}else {
			model.addAttribute("message","유효하지 않은 토큰입니다");
			return "login/reset_request";
		}
	}

}
