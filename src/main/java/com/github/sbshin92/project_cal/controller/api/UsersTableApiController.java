//package com.github.sbshin92.project_cal.controller.api;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.github.sbshin92.project_cal.data.vo.UserVO;
//import com.github.sbshin92.project_cal.service.impl.UserServiceImpl;
//
//@RestController
//@RequestMapping("/api/users")
//public class UsersTableApiController {
//
//	@Autowired
//    private UserServiceImpl userService;
//
//    @GetMapping
//    public List<UserVO> getAllUsers() {
//        return userService.getAllUsers();
//    }
//    
//    @GetMapping("/add")
//    public String addUser() {
//        UserVO userVO = UserVO.builder()
//                .userName("testUser")
//                .userEmail("testUser@example.com")
//                .userPassword("testPassword")
//                .build();
//
//        boolean isSuccess = userService.addUser(userVO);
//
//        return "<h1>Done!! " + userVO.getUserId() + "</h1>";
//    }
//}