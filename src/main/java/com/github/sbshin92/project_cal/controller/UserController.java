package com.github.sbshin92.project_cal.controller;

import com.github.sbshin92.project_cal.data.dto.UserDTO;
import com.github.sbshin92.project_cal.service.impl.UserServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
    private UserServiceImpl userService;

    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }
    
    @GetMapping("/add")
    public String addUser() {
        UserDTO userDTO = UserDTO.builder()
                .userName("testUser")
                .userEmail("testUser@example.com")
                .userPassword("testPassword")
                .build();

        Long userId = userService.addUser(userDTO);

        return "<h1>Done!! " + userId + "</h1>";
    }
}