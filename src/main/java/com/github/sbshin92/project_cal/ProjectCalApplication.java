package com.github.sbshin92.project_cal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class ProjectCalApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectCalApplication.class, args);
	}

}
