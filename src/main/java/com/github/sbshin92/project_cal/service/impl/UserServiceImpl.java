package com.github.sbshin92.project_cal.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.sbshin92.project_cal.data.dao.UsersDAO;
import com.github.sbshin92.project_cal.data.vo.UserVO;
import com.github.sbshin92.project_cal.service.UserService;

/**
 * UserService 인터페이스의 구현 클래스입니다.
 * 사용자 관련 비즈니스 로직을 처리합니다.
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UsersDAO usersDAO;

    /**
     * 모든 사용자 정보를 조회합니다.
     * @return 모든 사용자 목록
     */
    @Override
    public List<UserVO> getAllUsers() {
        logger.info("Fetching all users");
        List<UserVO> users = usersDAO.findAll();
        logger.debug("Found {} users", users.size());
        return users;
    }
    
    /**
     * 새 사용자를 추가합니다.
     * @param userVO 추가할 사용자 정보
     * @return 사용자 추가 성공 여부
     */
    @Override
    @Transactional
    public boolean addUser(UserVO userVO) {
        logger.info("Adding new user: {}", userVO.getUserName());
        int result = usersDAO.save(userVO);
        boolean success = result == 1;
        if (success) {
            logger.info("User added successfully with ID: {}", userVO.getUserId());
        } else {
            logger.warn("Failed to add user: {}", userVO.getUserName());
        }
        return success;
    }

    /**
     * 사용자 이름으로 사용자를 조회합니다.
     * @param username 조회할 사용자의 이름
     * @return 조회된 사용자 정보, 없으면 null
     */
    @Override
    public UserVO getUserByUsername(String username) {
        logger.info("Fetching user by username: {}", username);
        UserVO user = (usersDAO).findByUsername(username);
        if (user == null) {
            logger.warn("User not found with username: {}", username);
        } else {
            logger.debug("Found user: {}", user.getUserName());
        }
        return user;
    }

    // 필요에 따라 추가 메서드 구현
}