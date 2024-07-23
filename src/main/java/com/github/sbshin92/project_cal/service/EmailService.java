package com.github.sbshin92.project_cal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {
	
	
	
	@Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String to, String subject, String text) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper;

        try {
            helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text, true);
            mailSender.send(mimeMessage);
            System.out.println("Email sent to: " + to);
        } catch (MessagingException e) {
        System.err.println("Failed to send email: " + e.getMessage()); // 로그 추가
            e.printStackTrace();
        }
    }
}