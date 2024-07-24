package com.github.sbshin92.project_cal.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.sbshin92.project_cal.data.dao.MessagesDAO;
import com.github.sbshin92.project_cal.data.vo.MessageVO;
import com.github.sbshin92.project_cal.data.vo.UserVO;

@Service
public class NotificationService {

	@Autowired
	private MessagesDAO messagesDAO;
	
	public void sendNotification(UserVO recipient, String content) {
		MessageVO message = new MessageVO();
		message.setReceiverUserId(recipient.getUserId());
		message.setMessageTitle("프로젝트 초대알림");
		message.setMessageDescription(content);
		message.setReadStatus("unread");
		messagesDAO.insert(message);
	}

}
