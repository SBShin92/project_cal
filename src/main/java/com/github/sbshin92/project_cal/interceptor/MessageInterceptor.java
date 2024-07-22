//package com.github.sbshin92.project_cal.interceptor;
//
//import java.util.List;
//
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import com.github.sbshin92.project_cal.data.vo.MessageVO;
//import com.github.sbshin92.project_cal.data.vo.UserVO;
//import com.github.sbshin92.project_cal.service.MessageService;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//
////public class MessageInterceptor implements HandlerInterceptor {
////
////	private final MessageService messageService;
////	
////	
////	
////	public MessageInterceptor(MessageService messageService) {
////		super();
////		this.messageService = messageService;
////	}
////
////	@Override
////	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
////			throws Exception {
////		HttpSession session = request.getSession();
////		UserVO authUser = (UserVO)session.getAttribute("authUser");
////		int messageUnreadCount = 0;
////		List<MessageVO> messageVOs = messageService.getMessageListByReceiverUserId(authUser.getUserId());
////		for (MessageVO messageVO: messageVOs) {
////			if ("unread".equals(messageVO.getReadStatus()))
////				messageUnreadCount += 1;
////		}
////		session.setAttribute("messageUnreadCount", messageUnreadCount);
////		return true;
////	}
////}
//public class MessageInterceptor implements HandlerInterceptor {
//
//    private final MessageService messageService;
//
//    public MessageInterceptor(MessageService messageService) {
//        super();
//        this.messageService = messageService;
//    }
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//            throws Exception {
//        HttpSession session = request.getSession();
//        UserVO authUser = (UserVO) session.getAttribute("authUser");
//
//        if (authUser == null) {
//            response.sendRedirect("/login"); // 로그인 페이지로 리다이렉트
//            return false; // 요청을 계속 진행하지 않음
//        }
//
//        int messageUnreadCount = 0;
//        List<MessageVO> messageVOs = messageService.getMessageListByReceiverUserId(authUser.getUserId());
//        for (MessageVO messageVO : messageVOs) {
//            if ("unread".equals(messageVO.getReadStatus())) {
//				messageUnreadCount += 1;
//			}
//        }
//        session.setAttribute("messageUnreadCount", messageUnreadCount);
//        return true;
//    }
//}