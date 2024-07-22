//package com.github.sbshin92.project_cal.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import com.github.sbshin92.project_cal.interceptor.AuthInterceptor;
//import com.github.sbshin92.project_cal.interceptor.MessageInterceptor;
//import com.github.sbshin92.project_cal.service.MessageService;
//
//@Configuration
//public class WebMvcConfig implements WebMvcConfigurer {
//
//	@Autowired
//	private MessageService messageService;
//	
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(new AuthInterceptor())
//			.addPathPatterns("/**")
//			.excludePathPatterns("/login/**")
//			.excludePathPatterns("/join/**")
//			.excludePathPatterns("/css/**")
//			.excludePathPatterns("/javascript/**");
//		
//		registry.addInterceptor(new MessageInterceptor(messageService))
//			.addPathPatterns("/**")
//			.excludePathPatterns("/login")
//			.excludePathPatterns("/css/**")
//			.excludePathPatterns("/javascript/**");
//	}
//
//	
//	
//
////    @Override
////    public void addResourceHandlers(ResourceHandlerRegistry registry) {
////        Path uploadPath = Paths.get(uploadDir).toAbsolutePath().normalize();
////        String uploadAbsolutePath = uploadPath.toString().replace("\\","/") + "/";
////        
////        registry.addResourceHandler("/uploads/**")
////                .addResourceLocations("file:/" + uploadAbsolutePath)
////                .setCachePeriod(3600)
////                .resourceChain(true);
////    }
//
//}