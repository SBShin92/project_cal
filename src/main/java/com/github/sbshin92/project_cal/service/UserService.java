package com.github.sbshin92.project_cal.service;

import java.util.List;

import com.github.sbshin92.project_cal.data.vo.UserVO;

public interface UserService {
	public List<UserVO> getAllUsers();
	public boolean addUser(UserVO userVO);
}
