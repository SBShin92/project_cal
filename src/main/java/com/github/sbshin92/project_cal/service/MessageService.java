package com.github.sbshin92.project_cal.service;

import java.util.List;

import com.github.sbshin92.project_cal.data.vo.MessageVO;

public interface MessageService {
	public MessageVO getMessageWithReadCheck(Integer messageId);
	public MessageVO getMessage(Integer messageId);
	public List<MessageVO> getMessageListByReceiverUserId(Integer receiverUserId);
	public List<MessageVO> getMessageListBySenderUserId(Integer senderUserId);
	
	public List<MessageVO> getMessageListByReceiverUserIdWithRowBounds(int page, int size, int receivedUserId);
	public List<MessageVO> getAlarmListByReceiverUserIdWithRowBounds(int page, int size, int receivedUserId);
	public List<MessageVO> getMessageListBySenderUserIdWithRowBounds(int page, int size, int sendedUserId);
	
	public Integer getMessageCountByReceiverUserId(int receivedUserId);
	
	public boolean sendMessage(MessageVO messageVO);
}