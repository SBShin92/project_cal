package com.github.sbshin92.project_cal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {
	
	  	@Bean
	     PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
}
