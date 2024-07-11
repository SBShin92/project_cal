package com.github.sbshin92.project_cal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.github.sbshin92.project_cal.data.dao.UsersDAO;
import com.github.sbshin92.project_cal.data.vo.UserVO;
import com.github.sbshin92.project_cal.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
    private UsersDAO usersDAO;
	
	@Autowired
	private  PasswordEncoder passwordEncoder; // 패스워드 암호화를 위한 메서드

    @Override
	public List<UserVO> getAllUsers() {
        List<UserVO> lst = usersDAO.findAll();
        return lst;
    }

    
    @Override
    public boolean addUser(UserVO userVO) {
    	
    	try {
    	String encodePassword = passwordEncoder.encode(userVO.getUserPassword());
    	userVO.setUserPassword(encodePassword);
        return 1 == usersDAO.save(userVO);
    } catch(DataAccessException e) {
    	e.printStackTrace();
    }
		return false;
  }

}