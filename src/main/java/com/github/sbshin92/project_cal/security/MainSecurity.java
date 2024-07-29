package com.github.sbshin92.project_cal.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import com.github.sbshin92.project_cal.data.vo.UserVO;
import com.github.sbshin92.project_cal.service.CustomUserDetailsService;
import com.github.sbshin92.project_cal.service.UserService;

@Configuration
@EnableWebSecurity
public class MainSecurity {

    private final CustomUserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;


    public MainSecurity(CustomUserDetailsService userDetailsService, PasswordEncoder passwordEncoder,UserService userService) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;

    }
    
    @Bean
    public SessionRegistry sessionRegistry() {
    	return new SessionRegistryImpl();
    }
    
    
    @Bean
    public AuthenticationSuccessHandler customAuthenticationSuccessHandler() {
        return (request, response, authentication) -> {
            String email = authentication.getName();
            System.out.println("Authenticated user: " + email); // 로그 추가
            UserVO userVO = userService.getUserByEmail(email);
            if(userVO != null) {
            request.getSession().setAttribute("userRole", "authUser");
            System.out.println("Session attribute 'userRole' set to 'authUser'");
            response.sendRedirect("/project_cal/");
            } else {
            	response.sendRedirect("/login?error=true");
            }
        };
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                                         /*  ClientRegistrationRepository clientRegistrationRepository,
                                           OAuth2AuthorizedClientRepository authorizedClientRepository) throws Exception { */
        http
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/css/**", "/js/**", "/join/**", "/login/**", "/oauth2/**","/**","/password/resetrequest**\"").permitAll()          
                .requestMatchers("/password/reset**").permitAll()
                .anyRequest().authenticated() 
            )
            .formLogin(formLogin -> formLogin
                .loginPage("/login")
                .loginProcessingUrl("/")
                .usernameParameter("email")
                .defaultSuccessUrl("/calendar", true)
                .failureUrl("/login?error=true")
                .successHandler(customAuthenticationSuccessHandler())
                .permitAll()
            )
            .logout(logout -> logout
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/")
                    .invalidateHttpSession(true) // 세션 무효화
                    .deleteCookies("authUser") // JSESSIONID 쿠키 삭제 및 세션과 연관된 쿠키 삭제
                    .addLogoutHandler(new SecurityContextLogoutHandler()) // 로그아웃 핸들러추가
                    .addLogoutHandler(new CookieClearingLogoutHandler("authUser")) // 쿠키제거 추가 핸들러
                    .addLogoutHandler(new CustomLogoutHandler())
                    .permitAll()
                
                )
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .maximumSessions(1)
                .maxSessionsPreventsLogin(true)
                .expiredUrl("/login?expired")
                .sessionRegistry(sessionRegistry())
            )
       
            .csrf(csrf -> csrf.disable());

        return http.build();
    }
    

   
  
}