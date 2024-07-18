//package com.github.sbshin92.project_cal.config;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.web.multipart.MultipartResolver;
//import org.springframework.web.multipart.support.StandardServletMultipartResolver;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import jakarta.annotation.PostConstruct;
//
//public class MainConfig implements WebMvcConfigurer {
//	
//
//    @Value("${file.upload-dir}")
//    private String uploadDir;
//
//    @PostConstruct
//    public void init() {
//        try {
//            Files.createDirectories(Paths.get(uploadDir));
//        } catch (IOException e) {
//            throw new RuntimeException("Could not create upload directory!", e);
//        }
//    }
//
//    
//    @Bean
//    public MultipartResolver multipartResolver() {
//    	 StandardServletMultipartResolver resolver = new StandardServletMultipartResolver();
//    	    resolver.setResolveLazily(true);
//    	    return resolver;
//    }
//
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
//
//}