package com.github.sbshin92.project_cal.data.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.github.sbshin92.project_cal.data.vo.MessageVO;

@Mapper
public interface MessagesDAO {

	@Select("SELECT m.message_id as messageId, "
			+ " m.sender_user_id as senderUserId, "
			+ " m.receiver_user_id as receiverUserId, "
			+ " m.message_title as messageTitle, "
			+ " m.message_description as messageDescription, "
			+ " m.read_status as readStatus, "
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
			+ " m.read_status as readStatus, "
			+ " m.created_at as createdAt, "
			+ " su.user_name as senderUserName, "
			+ " ru.user_name as receiverUserName "
			+ " FROM messages m JOIN users su ON m.sender_user_id = su.user_id JOIN users ru ON m.receiver_user_id = ru.user_id "
			+ " WHERE m.sender_user_id = #{senderUserId} "
			+ " ORDER BY m.created_at DESC")
	public List<MessageVO> getListByDateBySenderUserId(Integer senderUserId);
	
	
	@Select("SELECT m.message_id as messageId, "
			+ " m.sender_user_id as senderUserId, "
			+ " m.receiver_user_id as receiverUserId, "
			+ " m.message_title as messageTitle, "
			+ " m.message_description as messageDescription, "
			+ " m.read_status as readStatus, "
			+ " m.created_at as createdAt, "
			+ " su.user_name as senderUserName, "
			+ " ru.user_name as receiverUserName "
			+ " FROM messages m JOIN users su ON m.sender_user_id = su.user_id JOIN users ru ON m.receiver_user_id = ru.user_id "
			+ " WHERE m.message_id = #{messageId} ")
	public MessageVO getMessageByMessageId(Integer messageId);
	
		
	@Insert("INSERT INTO messages(sender_user_id, receiver_user_id, message_title, message_description) "
			+ " VALUES(#{senderUserId}, #{receiverUserId}, #{messageTitle}, #{messageDescription})")
	public Integer insert(MessageVO messageVO);
	
	
	@Update("UPDATE messages "
			+ " SET read_status = 'read' "
			+ " WHERE message_id = #{messageId}")
	public Integer updateReadStatusRead(Integer messageId);

	
	@Update("UPDATE messages "
			+ " SET read_status = 'unread' "
			+ " WHERE message_id = #{messageId}")
	public Integer updateReadStatusUnRead(Integer messageId);
	
}
