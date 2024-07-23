package com.github.sbshin92.project_cal.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.github.sbshin92.project_cal.data.vo.UserVO;
import com.github.sbshin92.project_cal.service.CustomUserDetailsService;
import com.github.sbshin92.project_cal.service.UserService;

@Configuration
@EnableWebSecurity
public class MainSecurity {

    private final CustomUserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
//    private final TokenService tokenService;

    public MainSecurity(CustomUserDetailsService userDetailsService, PasswordEncoder passwordEncoder,UserService userService) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
//        this.tokenService = tokenService;
    }
    
    
    @Bean
    public AuthenticationSuccessHandler customAuthenticationSuccessHandler() {
        return (request, response, authentication) -> {
            String email = authentication.getName();
            System.out.println("Authenticated user: " + email); // 로그 추가
            UserVO userVO = userService.getUserByEmail(email);
            if(userVO != null) {
//            tokenService.sendTokenToEmail(email);
            request.getSession().setAttribute("userRole", "authUser");
            System.out.println("Session attribute 'userRole' set to 'authUser'");
            response.sendRedirect("/project_cal/calendar");
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
                .requestMatchers("/css/**", "/js/**", "/join/**", "/login/**", "/oauth2/**", "/**").permitAll()
                .requestMatchers("/user/**").hasRole("admin")
                .requestMatchers("/**").hasRole("authUser")
                .requestMatchers("/calendar/**").hasRole("authUser")
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
                    .logoutSuccessUrl("/")
                    .permitAll()		
           
                    /* )
            .oauth2Login(oauth2Login -> oauth2Login
                .loginPage("/login")
                .defaultSuccessUrl("/verify-token", true)
                .failureUrl("/login?error=true")
                .userInfoEndpoint(userInfo -> userInfo
                    .oidcUserService(new OidcUserService())
                ) 
                .successHandler(customAuthenticationSuccessHandler())  */
            )
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
            )
            .csrf(csrf -> csrf.disable());

        return http.build();
    }

   
    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        return new InMemoryClientRegistrationRepository(this.naverClientRegistration());
    }

   
    // 네이버 클라이언트 등록
    private ClientRegistration naverClientRegistration() {
        return ClientRegistration.withRegistrationId("naver")
            .clientId("fCbESEtEsGr2Lcs5w2Lp3")
            .clientSecret("vS1egkAK42")
            .redirectUri("http://localhost:8080/project_cal/login/oauth2/code/naver")
            .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
            .scope("profile", "email")
            .tokenUri("https://nid.naver.com/oauth2.0/token")
            .authorizationUri("https://nid.naver.com/oauth2.0/authorize")
            .userInfoUri("https://openapi.naver.com/v1/nid/me")
            .userNameAttributeName("response.email")
            .clientName("Naver")
            .build();
    }  
}
