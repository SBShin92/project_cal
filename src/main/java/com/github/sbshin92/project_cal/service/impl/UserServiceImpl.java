package com.github.sbshin92.project_cal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.sbshin92.project_cal.data.dao.RoleDAO;
import com.github.sbshin92.project_cal.data.dao.UsersDAO;
import com.github.sbshin92.project_cal.data.vo.RoleVO;
import com.github.sbshin92.project_cal.data.vo.UserVO;
import com.github.sbshin92.project_cal.service.RoleService;
import com.github.sbshin92.project_cal.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UsersDAO usersDAO;

	@Autowired
	private RoleDAO roleDAO;

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
	 * 
	 * @param userVO 추가할 사용자 정보
	 * @return 사용자 추가 성공 여부
	 */
	@Override
	@Transactional
	public boolean addUser(UserVO userVO) {
		try {
			String Password = userVO.getUserPassword();
			userVO.setUserPassword(Password);

			return 1 == usersDAO.save(userVO);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 사용자 이름으로 사용자를 조회합니다.
	 * 
	 * @param username 조회할 사용자의 이름
	 * @return 조회된 사용자 정보, 없으면 null
	 */
	@Override
	public UserVO getUserByEmail(String email) {
		return usersDAO.findByEmail(email);
	}

	// 유저 삭제 기능
	@Override
	public boolean deleteUser(Integer userId) {
		return usersDAO.deleteByUserId(userId) > 0;
	}

	// 유저 수정 기능
	@Override
	@Transactional
	public void updateUser(UserVO user) {
		try {
			usersDAO.update(user);
			if (user.getRoleVO() != null) {
				roleDAO.update(user.getRoleVO());
			}
		} catch (DataAccessException e) {
			throw new RuntimeException("사용자 정보 업데이트 중 오류 발생", e);
		}

	}

	@Override
	public UserVO getUserById(int userId) {
		return usersDAO.findByUserId(userId);
	}



}
