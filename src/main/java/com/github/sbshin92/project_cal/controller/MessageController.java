package com.github.sbshin92.project_cal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.sbshin92.project_cal.data.vo.MessageVO;
import com.github.sbshin92.project_cal.data.vo.UserVO;
import com.github.sbshin92.project_cal.service.MessageService;
import com.github.sbshin92.project_cal.service.UserService;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/message")
@Controller
public class MessageController {

	@Autowired
	private MessageService messageService;
	@Autowired
	private UserService userService;
	
	@GetMapping({"", "/", "/received"})
	public String receivedMessageListPage(Model model, HttpSession session) {
		
		UserVO authUser = (UserVO)session.getAttribute("authUser");
		List<MessageVO> messageVOs = messageService.getMessageListByReceiverUserId(authUser.getUserId());
		model.addAttribute("messageVOs", messageVOs);
		model.addAttribute("url", "received");
		return "message/message-list";
	}
	
	@GetMapping("/alarm")
	public String alarmMessageListPage(Model model, HttpSession session) {
		UserVO authUser = (UserVO)session.getAttribute("authUser");
		List<MessageVO> messageVOs = messageService.getMessageListByReceiverUserId(authUser.getUserId());
		model.addAttribute("messageVOs", messageVOs);
		model.addAttribute("url", "alarm");
		return "message/message-list-alarm";
	}
	
	@GetMapping("/sended")
	public String sendedMessagePage(Model model, HttpSession session) {
		
		UserVO authUser = (UserVO)session.getAttribute("authUser");
		List<MessageVO> messageVOs = messageService.getMessageListBySenderUserId(authUser.getUserId());
		model.addAttribute("messageVOs", messageVOs);
		model.addAttribute("url", "sended");
		return "message/message-list-sended";
	}
	
	@GetMapping("/received/{messageId}")
	public String receivedMessageDetailPage(@PathVariable("messageId") Integer messageId , Model model, HttpSession session) {

		MessageVO messageVO = messageService.getMessageWithReadCheck(messageId);
		model.addAttribute("messageVO", messageVO);
		return "message/message-detail";
	}
	
	@GetMapping("/alarm/{messageId}")
	public String alarmMessageDetailPage(@PathVariable("messageId") Integer messageId , Model model, HttpSession session) {
		
		MessageVO messageVO = messageService.getMessageWithReadCheck(messageId);
		model.addAttribute("messageVO", messageVO);
		return "message/message-detail-alarm";
	}
	
	@GetMapping("/sended/{messageId}")
	public String sendedMessageDetailPage(@PathVariable("messageId") Integer messageId , Model model, HttpSession session) {
		MessageVO messageVO = messageService.getMessage(messageId);
		model.addAttribute("messageVO", messageVO);
		return "message/message-detail-sended";
	}
	
	@GetMapping("/create")
	public String createMessagePage(Model model) {
		return "message/message-create-form";
	}
	
	@PostMapping("/create")
	public String createMessageAction(@RequestParam("receiverUserNameOrEmail") String receiverUserNameOrEmail,
			@ModelAttribute MessageVO messageVO,
			HttpSession session) {
		UserVO authUser = (UserVO)session.getAttribute("authUser");
		// 받는이에 아무것도 적지 않았을 때, 유저확인 오류의 처리
		if (receiverUserNameOrEmail == null || authUser == null) {
			return "redirect:/message/sended";
		}
		
		UserVO userVO = null;
		if (receiverUserNameOrEmail.contains("@")) {
			userVO = userService.getUserByEmail(receiverUserNameOrEmail);
		} else {
			userVO = userService.getUserByUserName(receiverUserNameOrEmail);
		}
		
		if (userVO != null) {
			messageVO.setReceiverUserId(userVO.getUserId());
			messageVO.setSenderUserId(authUser.getUserId());
			messageVO.setIsAlarm(false);
			messageService.sendMessage(messageVO);
			
		}
		return "redirect:/message/sended";
	}

}
