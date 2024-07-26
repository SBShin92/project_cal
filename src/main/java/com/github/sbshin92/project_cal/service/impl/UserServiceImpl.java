package com.github.sbshin92.project_cal.service.impl;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.sbshin92.project_cal.data.dao.RoleDAO;
import com.github.sbshin92.project_cal.data.dao.UsersDAO;
import com.github.sbshin92.project_cal.data.vo.RoleVO;
import com.github.sbshin92.project_cal.data.vo.UserVO;
import com.github.sbshin92.project_cal.service.EmailService;
import com.github.sbshin92.project_cal.service.UserService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UsersDAO usersDAO;

	@Autowired
	private RoleDAO roleDAO;

	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Autowired
	private EmailService emailService;

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
				System.out.println("사용자 ID " + user.getUserId() + "의 권한이 " + existingUser.getUserAuthority() + "에서 "
						+ user.getUserAuthority() + "로 변경되었습니다.");
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

	/*
	 * 토큰 조회 및 삭제
	 * 
	 * @Override public UserVO findByToken(String token) { return
	 * usersDAO.findByToken(token); }
	 * 
	 * @Override public void deleteToken(int userId) { usersDAO.deleteToken(userId);
	 * 
	 * }
	 */

	// 비밀번호 리셋

	// 비밀번호 초기화 토큰 생성
	@Override
    public void createPasswordResetTokenForUser(String email) {
        UserVO user = usersDAO.findByEmail(email);
        if (user != null) {
            String token = UUID.randomUUID().toString();
            Timestamp tokenExpiryDate = calculateExpiryDate(60); // 1 hour expiry time
            usersDAO.updatePasswordResetToken(email, token, tokenExpiryDate);
            sendResetTokenEmail(email, token);
        }
    }
		
	 // 비밀번호 초기화 토큰 검증
		@Override
	    public boolean validatePasswordResetToken(String token) {
	        Optional<UserVO> userOptional = usersDAO.findByToken(token);
	        if (userOptional.isPresent()) {
	            UserVO user = userOptional.get();
	            if (user.getTokenExpiryDate().after(new Timestamp(System.currentTimeMillis()))) {
	                return true;
	            }
	        }
	        return false;
	    }
	 

	 // 비밀번호 재설정
		@Override
	    public boolean resetPassword(String token, String password) {
	        if (validatePasswordResetToken(token)) {
	            String encodedPassword = passwordEncoder.encode(password);
	            usersDAO.updatePasswordByToken(token, encodedPassword);
	            return true;
	        }
	        return false;
	    }
		
		   
		 // 토큰 만료 시간 계산
			@Override
		    public Timestamp calculateExpiryDate(int expiryTimeInMinutes) {
		        Calendar cal = Calendar.getInstance();
		        cal.setTime(new Timestamp(System.currentTimeMillis()));
		        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
		        return new Timestamp(cal.getTime().getTime());
		    }

			// 이메일 전송
			@Override
		    public void sendResetTokenEmail(String email, String token) {
		        String resetUrl = "http://localhost:8080/project_cal/password/verifytoken";
		        String message = "<p>비밀번호를 재설정하려면 다음 링크를 클릭하고 토큰을 입력하세요:</p>" + 
		                         "<p><a href=\"" + resetUrl + "\">" + resetUrl + "</a></p>" +
		                         "<p>토큰: " + token + "</p>";
		        
		        try {
		        	MimeMessage mimeMessage = mailSender.createMimeMessage();
		        	MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,false,"UTF-8");
		        	
		        	helper.setTo(email);
		        	helper.setSubject("비밀번호 재설정 메일입니다");
		        	helper.setText(message, true);
		        	helper.setFrom("himj9515@naver.com");
		        	mailSender.send(mimeMessage);
	        	
		        } catch(MessagingException e) {
		        	e.printStackTrace();
		        }
		    }
		}