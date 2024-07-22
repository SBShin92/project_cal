package com.github.sbshin92.project_cal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.github.sbshin92.project_cal.data.vo.UserVO;

//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    private UserService userService;
//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        UserVO user = userService.getUserByEmail(email);
//        if (user == null) {
//            throw new UsernameNotFoundException("User not found with email: " + email);
//        }
//        return User.withUsername(user.getUserEmail())
//                   .password(user.getUserPassword())
//                   .roles(user.getUserAuthority()) // 역할 설정
//                   .build();
//    }
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserVO user = userService.getUserByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        return User.withUsername(user.getUserEmail())
                   .password(user.getUserPassword())
                   .roles("AUTHUSER") // 기본 권한 설정
                   .build();
    }
}