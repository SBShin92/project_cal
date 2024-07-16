//package com.github.sbshin92.project_cal.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//import com.github.sbshin92.project_cal.service.CustomUserDetailsService;
//
//@Configuration
//@EnableWebSecurity
//public class MainSecurity {
//	
//	  private final UserDetailsService userDetailsService;
//	  private final PasswordEncoder passwordEncoder;
//	  
//	    @Autowired
//	    public MainSecurity(CustomUserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
//	        this.userDetailsService = userDetailsService;
//	        this.passwordEncoder = passwordEncoder;
//	    }
//
//	  
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//        http
//            .authorizeHttpRequests(authorize -> authorize
//                .requestMatchers("/css/**", "/js/**", "/**").permitAll()
//                .anyRequest().authenticated()
//            )
//            .formLogin(formLogin -> formLogin
//                .loginPage("/login")            
//                .loginProcessingUrl("/login") // 로그인 처리 URL
//                .defaultSuccessUrl("/calendar", true)
//                .failureUrl("/error/404")
//                .permitAll()
//            )
//            .logout(logout -> logout
//                .logoutSuccessUrl("/")
//                .permitAll()
//            )
//            .userDetailsService(userDetailsService)
//            .sessionManagement(session -> session
//                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
//            )
//            .csrf(csrf -> csrf
//                .disable() // CSRF 보호 기능 비활성화
//            );   
//
//        
//        return http.build();
//    }
//  
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//    	auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
//    }
//}
