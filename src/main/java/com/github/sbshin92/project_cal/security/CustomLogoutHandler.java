package com.github.sbshin92.project_cal.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class CustomLogoutHandler implements LogoutHandler {
	
	  @Override
	  public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
	        HttpSession session = request.getSession(false);
	        if (session != null) {
	            session.invalidate();
	        }
	        SecurityContextHolder.clearContext();
	    }
	}