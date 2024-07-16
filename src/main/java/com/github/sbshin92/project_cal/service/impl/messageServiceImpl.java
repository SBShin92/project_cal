package com.github.sbshin92.project_cal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.sbshin92.project_cal.data.dao.MessagesDAO;
import com.github.sbshin92.project_cal.data.vo.MessageVO;
import com.github.sbshin92.project_cal.service.MessageService;

@Service
public class messageServiceImpl implements MessageService {

	@Autowired
	private MessagesDAO messagesDAO;

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
