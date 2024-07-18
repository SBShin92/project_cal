package com.github.sbshin92.project_cal;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@MapperScan("com.github.sbshin92.project_cal.data.dao") // 메퍼스캔이 필요한 이유?
public class ProjectCalApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectCalApplication.class, args);
	}

}
