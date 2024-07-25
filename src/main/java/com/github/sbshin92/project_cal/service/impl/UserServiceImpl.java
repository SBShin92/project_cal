package com.github.sbshin92.project_cal.service.impl;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.sbshin92.project_cal.data.dao.RoleDAO;
import com.github.sbshin92.project_cal.data.dao.UsersDAO;
import com.github.sbshin92.project_cal.data.vo.RoleVO;
import com.github.sbshin92.project_cal.data.vo.UserVO;
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
            // 기존 사용자 정보 조회
            UserVO existingUser = usersDAO.findByUserId(user.getUserId());
            if (existingUser == null) {
                throw new RuntimeException("사용자를 찾을 수 없습니다.");
            }

            // 관리자 권한 변경 확인
            if (!existingUser.getUserAuthority().equals(user.getUserAuthority())) {
                // 관리자 권한이 변경되었을 경우 로깅 또는 추가 처리
                System.out.println("사용자 ID " + user.getUserId() + "의 권한이 " 
                    + existingUser.getUserAuthority() + "에서 " + user.getUserAuthority() + "로 변경되었습니다.");
            }

            // 사용자 정보 업데이트
            usersDAO.update(user);

            // 역할 정보 업데이트
            if (user.getRoleVO() != null) {
                RoleVO existingRole = roleDAO.findByUserId(user.getUserId());
                if (existingRole == null) {
                    roleDAO.insert(user.getRoleVO());
                } else {
                    roleDAO.update(user.getRoleVO());
                }
            }
        } catch (DataAccessException e) {
            throw new RuntimeException("사용자 정보 업데이트 중 오류 발생", e);
        }
    }

	@Override
	public UserVO getUserById(int userId) {
		return usersDAO.findByUserId(userId);
	}


/* 토큰 조회 및 삭제
	@Override
	public UserVO findByToken(String token) {
		return usersDAO.findByToken(token);
	}

	@Override
	public void deleteToken(int userId) {
		usersDAO.deleteToken(userId);
		
	} */
	
	
// 비밀번호 리셋

	
	@Override
	public void createPasswordResetTokenForUser(String email) {
		Optional<UserVO> userOVO = Optional.ofNullable(usersDAO.findByEmail(email));
		if(userOVO.isPresent()) {
			UserVO user = userOVO.get();
			String token = UUID.randomUUID().toString();
			user.setToken(token);
			user.setTokenExpiryDate(calculateExpiryDate(24 * 60)); // 24 hours
			usersDAO.save(user);
		}
			
		}

	public boolean validatePasswordResetToken(String token) {
		Optional<UserVO> userOVO = usersDAO.findByToken(token);
		if(userOVO.isPresent()) {
			UserVO user = userOVO.get();
			return !isTokenExpired(user);
		}
		return false;
	}
	 
		@Override
		public Timestamp calculateExpiryDate(int expiryTimeInMinutes) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Timestamp(System.currentTimeMillis()));
			cal.add(Calendar.MINUTE, expiryTimeInMinutes);
			return new Timestamp(cal.getTime().getTime());
		}

		@Override
	public boolean resetPassword(String token, String newPassword) {
		Optional<UserVO> userOVO = usersDAO.findByToken(token);
			if(userOVO.isPresent()) {
				UserVO user = userOVO.get();
				if(!isTokenExpired(user)) {
					user.setUserPassword(newPassword);
					user.setToken(null);
					user.setTokenExpiryDate(null);
					usersDAO.save(user);
				}
			}
			return false;
		}
		
	private boolean isTokenExpired(UserVO user) {
      return user.getTokenExpiryDate().before(new Timestamp(System.currentTimeMillis()));
   }

}
