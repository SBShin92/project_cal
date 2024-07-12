package com.github.sbshin92.project_cal.service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

	@Autowired
    private JavaMailSender emailSender;

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 이메일로 인증 토큰을 전송합니다.
     * @param to 수신자 이메일 주소
     */
    public void sendVerificationEmail(String to) {
        String token = generateToken();

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@yourdomain.com");
        message.setTo(to);
        message.setSubject("Email Verification");
        message.setText("Your verification token is: " + token);

        emailSender.send(message);

        // 토큰을 Redis에 저장 (10분 동안 유효)
        redisTemplate.opsForValue().set(to, token, 10, TimeUnit.MINUTES);
    }

    /**
     * UUID를 사용하여 랜덤 토큰을 생성합니다.
     * @return 생성된 토큰
     */
    private String generateToken() {
        return UUID.randomUUID().toString();
    }

    /**
     * 저장된 토큰을 검증합니다.
     * @param email 이메일 주소
     * @param token 사용자가 입력한 토큰
     * @return 토큰이 일치하면 true, 그렇지 않으면 false
     */
    public boolean verifyToken(String email, String token) {
        String storedToken = redisTemplate.opsForValue().get(email);
        return token.equals(storedToken);
    }
}
