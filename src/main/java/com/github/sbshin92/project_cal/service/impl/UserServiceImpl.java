package com.github.sbshin92.project_cal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
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
	public UserVO getUserByUserId(Integer userId) {
		return usersDAO.findByUserId(userId);
	}
    
	@Override
	public UserVO getUserByUserName(String userName) {
		return usersDAO.findByUserName(userName);
	}

	/**
     * 새 사용자를 추가합니다.
     * @param userVO 추가할 사용자 정보
     * @return 사용자 추가 성공 여부
     */
    @Override
    @Transactional
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

    /**
     * 사용자 이름으로 사용자를 조회합니다.
     * @param username 조회할 사용자의 이름
     * @return 조회된 사용자 정보, 없으면 null
     */
    @Override
    public UserVO getUserByEmail(String email) {
        return usersDAO.findByEmail(email);
    }
}