package com.github.sbshin92.project_cal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.sbshin92.project_cal.data.vo.UserVO;
import com.github.sbshin92.project_cal.service.UserService;

@Controller
@RequestMapping("/join")
public class UserController {

    @Autowired
    private UserService userService;
    
    @Autowired
//    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping
    public String join() {
        return "login/join";
    }
    
    @PostMapping("/join")
    public String signup(@RequestParam("username") String username,
                         @RequestParam("email") String email,
                         @RequestParam("password") String password,
                         @RequestParam("confirm_password") String confirmPassword,
                         Model model) {
        // 비밀번호와 비밀번호 확인이 일치하는지 확인
        if (!password.equals(confirmPassword)) {
            model.addAttribute("error", "비밀번호가 일치하지 않습니다.");
            return "login/join";
        }

        // 이메일 형식 유효성 검사 (간단한 예)
        if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            model.addAttribute("error", "유효하지 않은 이메일 형식입니다.");
            return "login/join";
        }

        // 사용자 이메일 중복 확인
        if (userService.getUserByEmail(email) != null) {
            model.addAttribute("error", "이미 존재하는 이메일입니다.");
            return "login/join";
        }

        // 새로운 사용자 객체 생성 및 설정
        UserVO user = new UserVO();
        user.setUserName(username);
        user.setUserEmail(email);
        user.setUserPassword(password);
        user.setUserAuthority("ROLE_USER"); // 기본 권한 설정

        // 비밀번호를 암호화하여 설정
//        String encodedPassword = passwordEncoder.encode(password);
//        user.setUserPassword(encodedPassword);

        try {
            // 사용자 정보를 데이터베이스에 저장
            boolean isUserAdded = userService.addUser(user);

            if (!isUserAdded) {
                model.addAttribute("error", "회원가입에 실패했습니다. 다시 시도해주세요.");
                return "login/join";
            }
        } catch (Exception e) {
            model.addAttribute("error", "회원가입 도중 오류가 발생했습니다. 다시 시도해주세요.");
            return "login/join";
        }

        return "redirect:/join/joinsuccess"; // 회원가입 성공 시 성공 페이지로 리다이렉트
    }

    @GetMapping("/joinsuccess")
    public String joinSuccess() {
        return "login/joinsuccess"; // joinsuccess.jsp 페이지를 반환
    }
}