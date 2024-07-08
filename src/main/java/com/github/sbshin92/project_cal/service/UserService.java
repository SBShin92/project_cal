package com.github.sbshin92.project_cal.service;

import java.util.List;

import com.github.sbshin92.project_cal.data.dto.UserDTO;

public interface UserService {
	public List<UserDTO> getAllUsers();
	public Long addUser(UserDTO userDTO);
}
