package com.github.sbshin92.project_cal.service;

import java.util.List;

import com.github.sbshin92.project_cal.data.vo.MessageVO;

public interface MessageService {
	public MessageVO getMessageWithReadCheck(Integer messageId);
	public MessageVO getMessage(Integer messageId);
	public List<MessageVO> getMessageListByReceiverUserId(Integer receiverUserId);
	public List<MessageVO> getMessageListBySenderUserId(Integer senderUserId);
	public boolean sendMessage(MessageVO messageVO);
}
