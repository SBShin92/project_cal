package com.github.sbshin92.project_cal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.sbshin92.project_cal.data.vo.MessageVO;
import com.github.sbshin92.project_cal.data.vo.UserVO;
import com.github.sbshin92.project_cal.service.MessageService;
import com.github.sbshin92.project_cal.service.UserService;

@RequestMapping("/message")
@Controller
public class MessageController {

	@Autowired
	private MessageService messageService;
	@Autowired
	private UserService userService;
	
	@GetMapping({"", "/", "/received"})
	public String receivedMessagePage(Model model) {
		
		// TODO: 세션에서 가져와야하지만, 편의상 receiver의 user_id = 1인 녀석이 '받은' 쪽지만 출력하도록 설정
		List<MessageVO> messageVOs = messageService.getMessageListByReceiverUserId(1);
		
		model.addAttribute("messages", messageVOs);
		// TODO: 세션에서 authUserName을 가져와야함
		model.addAttribute("authName", "임시");
		return "message/message-list";
	}

	@GetMapping("/sended")
	public String sendedMessagePage(Model model) {
		
		// TODO: 세션에서 가져와야하지만, 편의상 sender의 user_id = 1인 녀석이 '보낸' 쪽지만 출력하도록 설정
		List<MessageVO> messageVOs = messageService.getMessageListBySenderUserId(1);
		
		model.addAttribute("messages", messageVOs);
		model.addAttribute("authName", "임시");
		return "message/message-list";
	}
	
	@GetMapping("/create")
	public String createMessagePage(Model model) {
		model.addAttribute("authName", "임시");

		return "message/message-create-form";
	}
	
	@PostMapping("/create")
	public String createMessageAction(@RequestParam("receiverUserNameOrEmail") String receiverUserNameOrEmail,
			@ModelAttribute MessageVO messageVO) {
		// 받는이에 아무것도 적지 않았을 때의 처리
		if (receiverUserNameOrEmail == null)
			return "redirect:/message/sended";
		
		UserVO userVO = null;
		if (receiverUserNameOrEmail.contains("@")) {
			userVO = userService.getUserByEmail(receiverUserNameOrEmail);
		} else {
			userVO = userService.getUserByUserName(receiverUserNameOrEmail);
		}
		
		if (userVO != null) {
			messageVO.setReceiverUserId(userVO.getUserId());
			// TODO: 보내는 이는 1번으로 고정, 세션 완성 시 수정하자
			messageVO.setSenderUserId(1);
			messageService.sendMessage(messageVO);
			
			// TODO: 메세지를 DB에 저장 실패 시 처리?
		}
		return "redirect:/message/sended";
	}

}
