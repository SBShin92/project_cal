package com.github.sbshin92.project_cal.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderUtil {
    public static void main(String[] args) {
    	// 일반 Password 암호화
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String rawPassword = "921206";	// 원하는 비밀번호 입력
        String encodedPassword = passwordEncoder.encode(rawPassword); // 암호화진행
        System.out.println(encodedPassword); // 암호화된 비밀번호 확인
    }
}
