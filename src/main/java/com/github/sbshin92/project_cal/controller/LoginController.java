package com.github.sbshin92.project_cal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    private UserService userService; // 사용자 서비스 주입

    @Autowired
    private PasswordEncoder passwordEncoder; // 비밀번호 인코더 주입

    // 로그인 페이지로 이동
    @GetMapping({"", "/"})
    public String loginPage(Model model) {
        return "login/login"; // login 폴더 내의 login.jsp 뷰를 반환
    }

    // 로그인 액션 (Post 방식)
    @PostMapping("/login")
    public String loginAction(@RequestParam(value = "email", required = false, defaultValue = "") String email,
                              @RequestParam(value = "password", required = false, defaultValue = "") String password,
                              HttpSession session, Model model) {
        System.out.println("email: " + email);
        System.out.println("password: " + password);

        if (email.isEmpty() || password.isEmpty()) {
            System.out.println("email 혹은 password가 입력되지 않았습니다");
            model.addAttribute("error", "이메일 혹은 비밀번호가 입력되지 않았습니다.");
            return "login/login";
        }

        // email과 password 이용, 사용자 정보 질의
        UserVO authUser = userService.getUserByEmail(email);

        if (authUser != null && passwordEncoder.matches(password, authUser.getUserPassword())) {
            // 로그인 처리
            session.setAttribute("authUser", authUser);
            // 캘린더 페이지로 Redirect
            return "redirect:/calendar";
        } else {
            model.addAttribute("error", "이메일 혹은 비밀번호가 잘못되었습니다.");
            return "login/login";
        }
    }

    // 로그아웃
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("authUser");
        session.invalidate();

        return "redirect:/";
    }
}
