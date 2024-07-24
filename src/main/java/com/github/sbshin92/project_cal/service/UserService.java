package com.github.sbshin92.project_cal.service;

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
	
	// 토큰 관련
//	UserVO findByToken(String token);
//	void deleteToken(int userId);
	
	// 비밀번호 리셋
//	boolean sendPasswordResetToken(String email);
//	boolean resetPassword(String token, String newPassword);
	
}
