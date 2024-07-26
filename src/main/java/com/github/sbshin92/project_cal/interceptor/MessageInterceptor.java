package com.github.sbshin92.project_cal.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import com.github.sbshin92.project_cal.data.vo.RoleVO;
import com.github.sbshin92.project_cal.data.vo.UserVO;
import com.github.sbshin92.project_cal.service.MessageService;
import com.github.sbshin92.project_cal.service.RoleService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class MessageInterceptor implements HandlerInterceptor {

	private final MessageService messageService;
	private final RoleService roleService;
	
	
	public MessageInterceptor(MessageService messageService, RoleService roleService) {
		super();
		this.messageService = messageService;
		this.roleService = roleService;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		UserVO authUser = (UserVO)session.getAttribute("authUser");
		// 추가
		if(authUser == null) {
			response.sendRedirect("/project_cal/login");
			return false;
		}
		// 현재 권한 추가
		RoleVO authUserRole = roleService.getRoleByUserId(authUser.getUserId());
		session.setAttribute("authUserRole", authUserRole);
		
		int messageUnreadCount = messageService.getMessageCountByReceiverUserId(authUser.getUserId());
		
		session.setAttribute("messageUnreadCount", messageUnreadCount);
		return true;
	}
}