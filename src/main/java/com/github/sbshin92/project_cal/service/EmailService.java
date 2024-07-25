package com.github.sbshin92.project_cal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {
	
	
	
	@Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String to, String subject, String body) {
    	
    	try {
    		MimeMessage message = mailSender.createMimeMessage();
    		MimeMessageHelper helper = new MimeMessageHelper(message, true);
    		
    		helper.setTo(to);
    		helper.setSubject(subject);
    		helper.setText(body);
    		helper.setFrom("himj9515@naver.com");
    		
    		mailSender.send(message);
    	} catch(Exception e) {
    		e.printStackTrace();
    		throw new RuntimeException("Failed to send Mail",e);
    	}
    }
}