package com.github.sbshin92.project_cal.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@EnableWebSecurity
public class MainSecurity {

//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication().withUser("user").password(passwordEncoder().encode("password")).roles("USER")
//				.and().withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN");
//	}
//
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}

	   @Bean
	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	        http
	            // 정적 리소스에 대한 접근 허용
	            .authorizeHttpRequests(authorize -> authorize
	                .requestMatchers("/css/**", "/js/**").permitAll()
	            )
	            // 로그인 구성
	            .formLogin(formLogin -> formLogin
	                .loginPage("/login") // 커스텀 로그인 페이지 URL
	                .loginProcessingUrl("/login") // 로그인 처리 URL
	                .permitAll() // 모든 사용자가 로그인 페이지 접근 허용
	                .defaultSuccessUrl("/home", true) // 로그인 성공 후 리디렉션할 URL을 캘린더 페이지로 설정
	            )
	            // 로그아웃 구성
	            .logout(logout -> logout
	                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	                .logoutSuccessUrl("/login") // 로그아웃 성공 후 리디렉션할 URL
	                .permitAll()
	            )
	            // 권한 요구사항 구성
	            .authorizeHttpRequests(authorize -> authorize
	                .requestMatchers("/login", "/home").permitAll() // 로그인 및 로그아웃 페이지 접근 허용
	                .requestMatchers("/user/**").hasAnyRole("USER", "CENTER", "ADMIN") // /user/** 경로에 대해 USER, CENTER, ADMIN 역할 요구
	                .requestMatchers("/calendar/**").hasAnyRole("USER", "ADMIN") // /calendar/** 경로에 대해 USER, ADMIN 역할 요구
	                .anyRequest().authenticated() // 그 외 모든 요청은 인증 요구
	            );

	        return http.build();
	    }
	}