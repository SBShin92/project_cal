package com.github.sbshin92.project_cal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

////
//@RequestMapping("/login")
@Controller
public class LoginController {
////
//// @Autowired
//    private UsersDAO usersDAO;
//
//    @Autowired
//    private MailConfig mailConfig;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
	@GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", "Invalid username or password.");
        }
        return "login/login";
    
}
}
//
//    @PostMapping("/login")
//    public String login(String email, String password, HttpSession session) {
//        UserVO user = usersDAO.findByEmail(email);
//        if (user != null && passwordEncoder.matches(password, user.getUserPassword())) {
//            String token = UUID.randomUUID().toString();
//            mailConfig.sendTokenEmail(email, token); // 사용자의 이메일 주소로 토큰 전송
//            session.setAttribute("token", token);
//            session.setAttribute("email", email);
//            return "redirect:/verify-token";
//        }
//        return "redirect:/login?error=true";
//    }
//
//    @GetMapping("/verify-token")
//    public String verifyToken() {
//        return "verify-token";
//    }
//
//    @PostMapping("/verify-token")
//    public String verifyToken(String token, HttpSession session) {
//        String sessionToken = (String) session.getAttribute("token");
//        String email = (String) session.getAttribute("email");
//
//        if (token.equals(sessionToken)) {
//            session.removeAttribute("token");
//            return "redirect:/home";
//        } else {
//            return "redirect:/verify-token?error=true";
//        }
//    }
//}
