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
        return "login/resetRequest";
    }

    @PostMapping("/resetrequest")
    public String resetPasswordRequest(@RequestParam String email, Model model) {
        try {
            userService.createPasswordResetTokenForUser(email);
            model.addAttribute("message", "비밀번호 초기화 이메일이 발송되었습니다.");
            return "login/resettokensuccess";
        } catch (Exception e) {
            model.addAttribute("message", "오류가 발생했습니다. 다시 시도해주세요.");
            return "login/resetRequest";
        }
    }

    @GetMapping("/verifytoken")
    public String showTokenVerificationPage() {
        return "login/passwordresetToken";
    }

    @PostMapping("/verifytoken")
    public String verifyToken(@RequestParam("token") String token, Model model) {
        if (userService.validatePasswordResetToken(token)) {
            model.addAttribute("token", token);
            return "login/resetForm";
        } else {
            model.addAttribute("message", "유효하지 않은 토큰입니다.");
            return "login/passwordresetToken";
        }
    }

    @PostMapping("/reset")
    public String handlePasswordReset(@RequestParam("token") String token,
                                      @RequestParam("password") String password,
                                      Model model) {
        boolean isResetSuccessful = userService.resetPassword(token, password);
        if (isResetSuccessful) {
            model.addAttribute("message", "비밀번호가 성공적으로 재설정되었습니다.");
            return "redirect:/login";
        } else {
            model.addAttribute("message", "비밀번호 재설정에 실패하였습니다. 다시 시도해주세요.");
            return "login/resetForm";
        }
    }
}
