package com.github.sbshin92.project_cal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.sbshin92.project_cal.data.dao.UsersDAO;
import com.github.sbshin92.project_cal.data.vo.UserVO;
import com.github.sbshin92.project_cal.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
    private UsersDAO usersDAO;

    public List<UserVO> getAllUsers() {
        List<UserVO> lst = usersDAO.findAll();
        return lst;
    }

    
    @Override
    public boolean addUser(UserVO userVO) {
        return 1 == usersDAO.save(userVO);
    }

}