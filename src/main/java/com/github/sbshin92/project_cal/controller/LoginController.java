//package com.github.sbshin92.project_cal.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import com.github.sbshin92.project_cal.data.vo.UserVO;
//import com.github.sbshin92.project_cal.service.TokenService;
//import com.github.sbshin92.project_cal.service.UserService;
//
//@RequestMapping("/login")
//@Controller
//public class LoginController {
//
//	@Autowired
//    private UserService userService; // 사용자 서비스 주입
//
//    @Autowired
//    private TokenService tokenService; // 토큰 서비스 주입 (이메일로 토큰 전송 및 검증)
//
//    @Autowired
//    private PasswordEncoder passwordEncoder; // 비밀번호 인코더 주입
//
//    // 로그인 페이지로 이동
//    @GetMapping({"", "/"})
//    public String loginPage() {
//        return "login/login"; // login 폴더 내의 login.html 뷰를 반환
//    }
//
//    // 로그인 폼 제출 처리
//    @PostMapping("/login")
//    public String sendToken(@RequestParam("email") String email, 
//                            @RequestParam("password") String password, 
//                            Model model) {
//        UserVO user = userService.getUserByEmail(email); // 이메일로 사용자 검색
//        if (user != null && passwordEncoder.matches(password, user.getUserPassword())) { // 사용자 존재 및 비밀번호 일치 확인
//            try {
//                tokenService.sendVerificationEmail(email); // 인증 이메일 전송
//                model.addAttribute("email", email); // 모델에 이메일 추가
//                model.addAttribute("message", "Token sent to your email."); // 모델에 메시지 추가
//                return "login/verify-token"; // verify-token.html 뷰 반환
//            } catch (Exception e) {
//                e.printStackTrace(); // 예외 발생 시 스택 트레이스 출력
//                model.addAttribute("error", "Failed to send token."); // 모델에 에러 메시지 추가
//                return "error/404"; // login-error.html 뷰 반환
//            }
//        }
//        model.addAttribute("error", "Invalid email or password."); // 모델에 에러 메시지 추가
//        return "error/404"; // login-error.html 뷰 반환
//    }
//
//    // 토큰 검증 처리
//    @PostMapping("/verify-token")
//    public String verifyToken(@RequestParam("email") String email, 
//                              @RequestParam("token") String token, 
//                              Model model) {
//        if (tokenService.verifyToken(email, token)) { // 토큰 검증
//            return "redirect:/home"; // 홈 페이지로 리디렉션
//        }
//        model.addAttribute("error", "Invalid token."); // 모델에 에러 메시지 추가
//        return "error/404"; // verify-token-error.html 뷰 반환
//    }
//}