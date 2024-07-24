package com.github.sbshin92.project_cal.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.sbshin92.project_cal.data.dao.UsersDAO;

@Service
public class TokenService {
	
	private static final Logger logger = LoggerFactory.getLogger(TokenService.class);

    @Autowired
    private EmailService emailService;
    
    @Autowired
    private UsersDAO usersDAO;

    private Map<String, String> tokenStore = new HashMap<>();

    public String generateToken() {
        String token = UUID.randomUUID().toString();
        logger.info("생성된토큰:" + token);
        return token;
    }

    public void sendTokenToEmail(String email) {
    	try {
            String token = generateToken();
            tokenStore.put(email, token);
            usersDAO.updateToken(email, token);
            emailService.sendEmail(email, "토큰이 도착했습니다", "당신의 토큰은: " + token);
            logger.info("Email sent to: {}", email);
        } catch (Exception e) {
            logger.error("Error occurred while sending token to email: {}", email, e);
        }
    }

    public boolean isValidToken(String token) {
        return tokenStore.containsValue(token); // 저장된 토큰과 비교
    }

    public String getEmailByToken(String token) {
        for (Map.Entry<String, String> entry : tokenStore.entrySet()) {
            if (entry.getValue().equals(token)) {
                return entry.getKey();
            }
        }
        return null;
    }
    
    public void invalidateToken(String token) {
    	String email = getEmailByToken(token);
    	if(email != null) {
    		tokenStore.remove(email);
    		usersDAO.updateToken(email, token); // DB에서 토큰 무효화
    	}
    }
}
