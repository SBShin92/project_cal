package com.github.sbshin92.project_cal.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import com.github.sbshin92.project_cal.data.vo.UserVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AdminAuthInterceptor implements HandlerInterceptor {
	 @Override
	    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
	        HttpSession session = request.getSession();
	        UserVO user = (UserVO) session.getAttribute("authUser");

	        if (user == null || !"admin".equals(user.getUserAuthority())) {
	            response.sendRedirect("/project_cal/access-denied");
	            return false;
	        }
	        return true;
	    }
}
