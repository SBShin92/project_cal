package com.github.sbshin92.project_cal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.sbshin92.project_cal.data.dao.MessagesDAO;
import com.github.sbshin92.project_cal.data.vo.MessageVO;
import com.github.sbshin92.project_cal.service.MessageService;

@Service
public class messageServiceImpl implements MessageService {

	@Autowired
	private MessagesDAO messagesDAO;
	
	@Transactional
	@Override
	public MessageVO getMessageWithReadCheck(Integer messageId) {
		MessageVO messageVO = messagesDAO.getMessageByMessageId(messageId);
		if ("unread".equals(messageVO.getReadStatus()))
			messagesDAO.updateReadStatusRead(messageId);
		return messageVO;
	}
	
	@Override
	public MessageVO getMessage(Integer messageId) {
		return messagesDAO.getMessageByMessageId(messageId);
	}

	@Override
	public List<MessageVO> getMessageListByReceiverUserId(Integer receiverUserId) {
		return messagesDAO.getListByDateByReceiverUserId(receiverUserId);
	}

	@Override
	public List<MessageVO> getMessageListBySenderUserId(Integer senderUserId) {
		return messagesDAO.getListByDateBySenderUserId(senderUserId);
	}

	@Override
	public boolean sendMessage(MessageVO messageVO) {
		return 1 == messagesDAO.insert(messageVO);
	}

}