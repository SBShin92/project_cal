package com.github.sbshin92.project_cal.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService {
	
	private static final Logger logger = LoggerFactory.getLogger(TokenService.class);

    @Autowired
    private EmailService emailService;

    private Map<String, String> tokenStore = new HashMap<>();

    public String generateToken() {
        String token = UUID.randomUUID().toString();
        System.out.println("Generated Token: " + token); // 로그 추가
        return token;
    }

    public void sendTokenToEmail(String email) {
    	try {
            String token = generateToken();
            tokenStore.put(email, token);
            logger.info("Token generated for email: {}", email);
            emailService.sendEmail(email, "Your Login Token", "Your login token is: " + token);
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
}
