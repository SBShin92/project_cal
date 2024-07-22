package com.github.sbshin92.project_cal.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.github.sbshin92.project_cal.service.CustomUserDetailsService;
import com.github.sbshin92.project_cal.service.TokenService;

@Configuration
@EnableWebSecurity
public class MainSecurity {

    private final CustomUserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public MainSecurity(CustomUserDetailsService userDetailsService, PasswordEncoder passwordEncoder, TokenService tokenService) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }
    
    
    @Bean
    public AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler() {
        return (request, response, authentication) -> {
            String email = authentication.getName();
            System.out.println("Authenticated user: " + email); // 로그 추가
            tokenService.sendTokenToEmail(email);
            response.sendRedirect("/login/verify-token");
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,
                                           ClientRegistrationRepository clientRegistrationRepository,
                                           OAuth2AuthorizedClientRepository authorizedClientRepository) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/css/**", "/js/**", "/join/**", "/login/**", "/oauth2/**", "/**").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(formLogin -> formLogin
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .usernameParameter("email")
                .defaultSuccessUrl("/calendar", true)
                .failureUrl("/login?error=true")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/")
                .permitAll()
            )
            .oauth2Login(oauth2Login -> oauth2Login
                .loginPage("/login")
                .defaultSuccessUrl("/verify-token", true)
                .failureUrl("/login?error=true")
                .userInfoEndpoint(userInfo -> userInfo
                    .oidcUserService(new OidcUserService())
                )
                .successHandler(oAuth2AuthenticationSuccessHandler())
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
