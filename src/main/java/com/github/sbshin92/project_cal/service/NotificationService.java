package com.github.sbshin92.project_cal.service;




import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.sbshin92.project_cal.data.dao.MessagesDAO;
import com.github.sbshin92.project_cal.data.vo.MessageVO;
import com.github.sbshin92.project_cal.data.vo.UserVO;

@Service
public class NotificationService {

	@Autowired
	private MessagesDAO messagesDAO;
	
	public void sendNotification(UserVO sender, UserVO recipient,String title, String content) {
		
		MessageVO message = new MessageVO();
		message.setSenderUserId(sender.getUserId());
		message.setReceiverUserId(recipient.getUserId());
		message.setMessageTitle(title);
		message.setMessageDescription(content);
		message.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		message.setReadStatus("읽지않음");
		message.setIsAlarm(true);

		messagesDAO.insert(message);

	}

}
