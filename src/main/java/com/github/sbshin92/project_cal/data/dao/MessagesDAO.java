package com.github.sbshin92.project_cal.data.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.github.sbshin92.project_cal.data.vo.MessageVO;

@Mapper
public interface MessagesDAO {

	@Select("SELECT m.message_id as messageId, "
			+ " m.sender_user_id as senderUserId, "
			+ " m.receiver_user_id as receiverUserId, "
			+ " m.message_title as messageTitle, "
			+ " m.message_description as messageDescription, "
			+ " m.created_at as createdAt, "
			+ " su.user_name as senderUserName, "
			+ " ru.user_name as receiverUserName "
			+ " FROM messages m JOIN users su ON m.sender_user_id = su.user_id JOIN users ru ON m.receiver_user_id = ru.user_id "
			+ " WHERE m.receiver_user_id = #{receiverUserId} "
			+ " ORDER BY m.created_at DESC")
	public List<MessageVO> getListByDateByReceiverUserId(Integer receiverUserId);
	
	@Select("SELECT m.message_id as messageId, "
			+ " m.sender_user_id as senderUserId, "
			+ " m.receiver_user_id as receiverUserId, "
			+ " m.message_title as messageTitle, "
			+ " m.message_description as messageDescription, "
			+ " m.created_at as createdAt, "
			+ " su.user_name as senderUserName, "
			+ " ru.user_name as receiverUserName "
			+ " FROM messages m JOIN users su ON m.sender_user_id = su.user_id JOIN users ru ON m.receiver_user_id = ru.user_id "
			+ " WHERE m.sender_user_id = #{senderUserId} "
			+ " ORDER BY m.created_at DESC")
	public List<MessageVO> getListByDateBySenderUserId(Integer senderUserId);
	
	
	
	@Insert("INSERT INTO messages(sender_user_id, receiver_user_id, message_title, message_description) "
			+ " VALUES(#{senderUserId}, #{receiverUserId}, #{messageTitle}, #{messageDescription})")
	public Integer insert(MessageVO messageVO);
}
