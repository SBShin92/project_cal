package com.github.sbshin92.project_cal.service;

import java.sql.Timestamp;
import java.util.List;

import com.github.sbshin92.project_cal.data.vo.UserVO;

public interface UserService {

	public List<UserVO> getAllUsers();

	public UserVO getUserByUserId(Integer userId);

	public UserVO getUserByUserName(String userName);

	public boolean addUser(UserVO userVO);

	public UserVO getUserByEmail(String email);

	public boolean deleteUser(Integer userId);

//	boolean updateUser(int userId, String userName, String userEmail, String userPosition);

//	UserVO getUserById(int userId);

//	void updateUser(UserVO user);

//	boolean updateUser(int userId, String userName, String userEmail, String userAuthority, String userPosition);

	UserVO getUserById(int userId);

	void updateUser(UserVO user);
	
	// 비밀번호 리셋
	void createPasswordResetTokenForUser(String email);
    boolean validatePasswordResetToken(String token);
    boolean resetPassword(String token, String password);
    Timestamp calculateExpiryDate(int expiryTimeInMinutes);
    void sendResetTokenEmail(String email, String token);
}