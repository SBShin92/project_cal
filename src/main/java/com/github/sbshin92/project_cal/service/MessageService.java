package com.github.sbshin92.project_cal.service;

import java.util.List;

import com.github.sbshin92.project_cal.data.vo.MessageVO;

public interface MessageService {
	public List<MessageVO> getMessageListByReceiverUserId(Integer receiverUserId);
	public List<MessageVO> getMessageListBySenderUserId(Integer senderUserId);
	public boolean sendMessage(MessageVO messageVO);
}
