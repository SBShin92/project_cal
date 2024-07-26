package com.github.sbshin92.project_cal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.github.sbshin92.project_cal.data.vo.UserVO;
import com.github.sbshin92.project_cal.service.TokenService;
import com.github.sbshin92.project_cal.service.UserService;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/login")
@Controller
public class LoginController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private TokenService tokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping({"", "/"})
    public String loginPage() {
        return "login/login";
    }

    @PostMapping
    public ModelAndView login(@RequestParam("email") String email, 
                        @RequestParam("password") String password, 
                        HttpSession session) {
        UserVO user = userService.getUserByEmail(email);
        ModelAndView mv = new ModelAndView();
       
        if (user != null && passwordEncoder.matches(password, user.getUserPassword()))  {
            session.setAttribute("authUser", user);
            session.setAttribute("userName", user.getUserName()); // 사용자 이름 저장
            tokenService.sendTokenToEmail(email); // 이메일로 토큰 전송
            mv.setViewName("redirect:/login/verify-token");
  
        } else {
        	mv.setViewName("redirect:/login");
            mv.addObject("error","Invalid email or password");
        }
        return mv;
      
    }
    
    // 토큰 인증
    @GetMapping("/verify-token")
    public String verifyTokenPage() {
    	return "login/verify-token";
    }
    
    @PostMapping("/token")
    public ModelAndView verifyToken(@RequestParam("token")String token, HttpSession session) {
    	ModelAndView mw = new ModelAndView();
    
    	if(tokenService.isValidToken(token)) {
    		String email = tokenService.getEmailByToken(token);
    		UserVO userVO = userService.getUserByEmail(email);
    	  
    		if(userVO != null) {
    			session.setAttribute("authUser",userVO);
    			tokenService.invalidateToken(token); // 토큰 무효화
    			mw.setViewName("redirect:/calendar");
    		}else {
    			mw.setViewName("redirect:/login/verify-token");
    			mw.addObject("error","Invalid token");
    		}
    	} else {
    		mw.setViewName("redirect:/login/verify-token");
    		mw.addObject("error","Invalid token");
    	}
    	return mw;
    }
    
}