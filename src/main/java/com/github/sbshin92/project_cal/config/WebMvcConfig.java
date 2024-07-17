package com.github.sbshin92.project_cal.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.github.sbshin92.project_cal.interceptor.AuthInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new AuthInterceptor())
			.addPathPatterns("/**")
			.excludePathPatterns("/login")
			.excludePathPatterns("/css/**")
			.excludePathPatterns("/javascript/**");
	}

	
	

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        Path uploadPath = Paths.get(uploadDir).toAbsolutePath().normalize();
//        String uploadAbsolutePath = uploadPath.toString().replace("\\","/") + "/";
//        
//        registry.addResourceHandler("/uploads/**")
//                .addResourceLocations("file:/" + uploadAbsolutePath)
//                .setCachePeriod(3600)
//                .resourceChain(true);
//    }

}