package com.github.sbshin92.project_cal.service.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
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
		if ("unread".equals(messageVO.getReadStatus())) {
			messagesDAO.updateReadStatusRead(messageId);
		}
		return messageVO;
	}
	
	
	
	@Override
	public Integer getMessageCountByReceiverUserId(int receivedUserId) {
		return messagesDAO.getMessageCountByReceiverUserId(receivedUserId);
	}



	@Override
	public List<MessageVO> getMessageListByReceiverUserIdWithRowBounds(int page, int size, int receivedUserId) {
		int offset = (page - 1) * size;
		return messagesDAO.getMessageListByReceiverUserIdWithRowBounds(receivedUserId, new RowBounds(offset, size));
	}

	@Override
	public List<MessageVO> getAlarmListByReceiverUserIdWithRowBounds(int page, int size, int receivedUserId) {
		int offset = (page - 1) * size;
		return messagesDAO.getAlarmListByReceiverUserIdWithRowBounds(receivedUserId, new RowBounds(offset, size));
	}
	
	@Override
	public List<MessageVO> getMessageListBySenderUserIdWithRowBounds(int page, int size, int sendedUserId) {
		int offset = (page - 1) * size;
		return messagesDAO.getMessageListBySenderUserIdWithRowBounds(sendedUserId, new RowBounds(offset, size));
	}
	
	@Override
	public MessageVO getMessage(Integer messageId) {
		return messagesDAO.getMessageByMessageId(messageId);
	}

	@Override
	public List<MessageVO> getMessageListByReceiverUserId(Integer receiverUserId) {
		return messagesDAO.getMessageListByReceiverUserId(receiverUserId);
	}

	@Override
	public List<MessageVO> getMessageListBySenderUserId(Integer senderUserId) {
		return messagesDAO.getMessageListBySenderUserId(senderUserId);
	}

	@Override
	public boolean sendMessage(MessageVO messageVO) {
		return 1 == messagesDAO.insert(messageVO);
	}
	
}