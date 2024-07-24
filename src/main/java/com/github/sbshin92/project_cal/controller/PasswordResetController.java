//package com.github.sbshin92.project_cal.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import com.github.sbshin92.project_cal.service.UserService;
//
//@Controller
//@RequestMapping("/password")
//public class PasswordResetController {
//
//	@Autowired
//	private UserService userService;
//	
//	@GetMapping("/reset")
//	public String ResetPasswordPage() {
//		return "login/passwordreset";
//	}
//	
//	@PostMapping("/reset")
//	public String handlePasswordReset(@RequestParam("email")String email, Model model) {
//		
//		boolean isEmailSent = userService.sendPasswordResetToken(email);
//		if(isEmailSent) {
//			model.addAttribute("message","비밀번호 재설정 링크를 이메일로 보냈습니다");
//		}else {
//			model.addAttribute("error","이메일 전송에 실패하였습니다");
//		}
//		return "login/passwordreset";
//	}
//	
//	@GetMapping("reset/confirm")
//	public String ResetPasswordPage(@RequestParam("token") String token, Model model) {
//		model.addAttribute("token",token);
//		return "login/reset_confirm";
//	}
//	
//	@PostMapping("/reset/confirm")
//	public String handleResetPassword(@RequestParam("token")String token,
//									  @RequestParam("new_password") String newPassword,
//									  @RequestParam("confirm_password") String confirmPassword,
//									  Model model) {
//		if(!newPassword.equals(confirmPassword)) {
//			model.addAttribute("error","비밀번호가 일치하지 않습니다");
//			model.addAttribute("token",token);
//			return "login/reset_confirm";
//		}
//		
//		boolean isPasswordReset = userService.resetPassword(token, newPassword);
//		if(isPasswordReset) {
//			return "redirect:/login?resetSuccess";
//		}else {
//			model.addAttribute("error","비밀번호 재설정에 실패하였습니다 다시 시도해 주세요");
//			model.addAttribute("token",token);
//			return"login/reset_confirm";
//		}
//	}
//}
