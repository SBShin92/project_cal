package com.github.sbshin92.project_cal.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.github.sbshin92.project_cal.interceptor.AdminAuthInterceptor;
import com.github.sbshin92.project_cal.interceptor.MessageInterceptor;
import com.github.sbshin92.project_cal.service.MessageService;
import com.github.sbshin92.project_cal.service.RoleService;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Autowired
	private MessageService messageService;
	@Autowired
	private RoleService roleService;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(new AuthInterceptor())
//			.addPathPatterns("/**")
//			.excludePathPatterns("/login/**")
//			.excludePathPatterns("/join/**")
//			.excludePathPatterns("/css/**")
//			.excludePathPatterns("/javascript/**");
		
		registry.addInterceptor(new MessageInterceptor(messageService, roleService))
			.addPathPatterns("/**")
			.excludePathPatterns("/login")
			.excludePathPatterns("/join/**")
			.excludePathPatterns("/password/**")
			.excludePathPatterns("/perform_logout")
			.excludePathPatterns("/css/**")
			.excludePathPatterns("/js/**");
		
		//예은추가
		registry.addInterceptor(new AdminAuthInterceptor())
        .addPathPatterns("/manager/**");
	}
	


}