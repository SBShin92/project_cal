package com.github.sbshin92.project_cal.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.sbshin92.project_cal.data.vo.ProjectVO;
import com.github.sbshin92.project_cal.data.vo.RoleVO;
import com.github.sbshin92.project_cal.data.vo.UserVO;
import com.github.sbshin92.project_cal.service.ProjectService;
import com.github.sbshin92.project_cal.service.RoleService;
import com.github.sbshin92.project_cal.service.UserService;

@RequestMapping("/manager")
@Controller
public class ManagerController {
	@Autowired
	private ProjectService projectService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	 private static final Logger logger = LoggerFactory.getLogger(ManagerController.class);

	

	@GetMapping({ "", "/", "/project" })
	public String managerProjectsPage(Model model, @RequestParam(defaultValue = "1") int page) {
		List<ProjectVO> projectVOs = projectService.getProjectsWithPage(page, 10);
		model.addAttribute("projectVOs", projectVOs);
		model.addAttribute("projectCount", projectService.getTotalProjectCount());
		model.addAttribute("totalPages", (projectVOs.size()));
		return "manager/manager-project";
	}
	
	@PostMapping("/project/delete/{projectId}")
	public String managerProjectDeleteAction(@PathVariable("projectId") Integer projectId, 
						@RequestParam(value = "page", required = false) Integer page,
						Model model) {
		projectService.deleteProject(projectId);
		if (page != null)
			return "redirect:/manager/project?page=" + page;
		else
			return "redirect:/manager/project";
	}
	
	// 유저 삭제
	@PostMapping("/user/delete/{userId}")
	public String deleteUser(@PathVariable Integer userId) {
		// userId를 경로 변수로 받아와 사용자를 삭제

		boolean deleted = userService.deleteUser(userId);
		// userService에서 실제 사용자를 삭제하는 비즈니스 로직을 호출

		return "redirect:/manager/user";
	}

	
	 @GetMapping("/user")
	    public String managerUsersPage(Model model) {
	        List<UserVO> userVOs = userService.getAllUsers();
	        model.addAttribute("userVOs", userVOs);
	        return "manager/manager-user";
	    }

	 @GetMapping("/user/{userId}")
	    public ResponseEntity<?> getUser(@PathVariable int userId) {
	        logger.debug("Received request for user with ID: {}", userId);
	        logger.info("Received request for user with ID: {}", userId);
	        try {
	            UserVO user = userService.getUserById(userId);
	            if (user == null) {
	                logger.warn("User not found with ID: {}", userId);
	                return ResponseEntity.notFound().build();
	            }
	            RoleVO role = roleService.getRoleByUserId(userId);
	            if (role == null) {
	                logger.info("Role not found for user ID: {}. Creating default role.", userId);
	                role = new RoleVO();
	                role.setUserId(userId);
	            }
	            Map<String, Object> response = new HashMap<>();
	            response.put("user", user);
	            response.put("role", role);
	            return ResponseEntity.ok(response);
	        } catch (Exception e) {
	            logger.error("Error retrieving user with ID: {}", userId, e);
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                                 .body("Error: " + e.getMessage());
	        }
	    }

	  @PostMapping("/user/edit/{userId}")
	    public ResponseEntity<?> updateUser(
	            @PathVariable int userId,
	            @RequestParam String name,
	            @RequestParam String email,
	            @RequestParam String position,
	            @RequestParam String authority,
	            @RequestParam(required = false) Boolean projectCreate,
	            @RequestParam(required = false) Boolean projectRead,
	            @RequestParam(required = false) Boolean projectUpdate,
	            @RequestParam(required = false) Boolean projectDelete,
	            @RequestParam(required = false) Boolean isAdmin
	    ) {
	        try {
	            UserVO user = userService.getUserById(userId);
	            if (user == null) {
	                return ResponseEntity.badRequest().body("사용자를 찾을 수 없습니다.");
	            }

	            user.setUserName(name);
	            user.setUserEmail(email);
	            user.setUserPosition(position);
	            user.setUserAuthority(isAdmin != null && isAdmin ? "admin" : authority);

	            RoleVO role = roleService.getRoleByUserId(userId);
	            if (role == null) {
	                role = new RoleVO();
	                role.setUserId(userId);
	            }
	            role.setProjectCreate(projectCreate != null ? projectCreate : false);
	            role.setProjectRead(projectRead != null ? projectRead : false);
	            role.setProjectUpdate(projectUpdate != null ? projectUpdate : false);
	            role.setProjectDelete(projectDelete != null ? projectDelete : false);

	            userService.updateUser(user);
	            roleService.createOrUpdateRole(role);

	            return ResponseEntity.ok().body("사용자 정보가 성공적으로 업데이트되었습니다.");
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                                 .body("사용자 정보 업데이트 중 오류가 발생했습니다: " + e.getMessage());
	        }
	 }
	
}
