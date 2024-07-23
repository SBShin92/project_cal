package com.github.sbshin92.project_cal.controller;

import java.util.List;
import java.util.Map;

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

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RequestMapping("/manager")
@Controller
public class ManagerController {
	@Autowired
	private ProjectService projectService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	

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
	

	@GetMapping({ "/user" })
	public String managerUsersPage(Model model) {
		List<UserVO> userVOs = userService.getAllUsers();
		model.addAttribute("userVOs", userVOs);

		return "manager/manager-user";
	}

	// 유저 삭제
	@PostMapping("/user/delete/{userId}")
	public String deleteUser(@PathVariable Integer userId) {
		// userId를 경로 변수로 받아와 사용자를 삭제

		boolean deleted = userService.deleteUser(userId);
		// userService에서 실제 사용자를 삭제하는 비즈니스 로직을 호출

		return "redirect:/manager/user";
	}

	// 유저 수정
//	@PostMapping("/user/edit/{userId}")
//	public Map<String, Object> editUser(@PathVariable int userId, @RequestParam String name, @RequestParam String email,
//			@RequestParam String position, @RequestParam String authority) {
//		Map<String, Object> response = new HashMap<>();
//		try {
//// 사용자 정보 업데이트 로직
//			UserVO user = userService.getUserById(userId);
//			if (user != null) {
//				user.setUserName(name);
//				user.setUserEmail(email);
//				user.setUserAuthority(authority);
//				user.setUserPosition(position);
//				userService.updateUser(user);
//				response.put("success", true);
//				response.put("message", "사용자 정보가 업데이트되었습니다.");
//			} else {
//				response.put("success", false);
//				response.put("message", "사용자를 찾을 수 없습니다.");
//			}
//		} catch (Exception e) {
//			response.put("success", false);
//			response.put("message", "사용자 정보 업데이트 중 오류가 발생했습니다.");
//		}
//		return response;
//	}
	
	 // 기존 역할을 수정하고 역할 목록 페이지로 리다이렉트
//	@PostMapping("/user/edit/{userId}")
//	public String updateUser(@PathVariable int userId,
//	                         @RequestParam String name,
//	                         @RequestParam String email,
//	                         @RequestParam String position,
//	                         @RequestParam String authority,
//	                         @RequestParam(required = false) Boolean projectCreate,
//	                         @RequestParam(required = false) Boolean projectRead,
//	                         @RequestParam(required = false) Boolean projectUpdate,
//	                         @RequestParam(required = false) Boolean projectDelete,
//	                         RedirectAttributes redirectAttributes) {
//	    try {
//	        UserVO user = userService.getUserById(userId);
//	        if (user == null) {
//	            redirectAttributes.addFlashAttribute("error", "사용자를 찾을 수 없습니다.");
//	            return "redirect:/manager/user";
//	        }
//
//	        user.setUserName(name);
//	        user.setUserEmail(email);
//	        user.setUserPosition(position);
//	        user.setUserAuthority(authority);
//
//	        RoleVO role = roleService.getRoleByUserId(userId);
//	        if (role == null) {
//	            role = new RoleVO();
//	            role.setUserId(userId);
//	        }
//	        role.setProjectCreate(projectCreate != null && projectCreate);
//	        role.setProjectRead(projectRead != null && projectRead);
//	        role.setProjectUpdate(projectUpdate != null && projectUpdate);
//	        role.setProjectDelete(projectDelete != null && projectDelete);
//
//	        userService.updateUser(user);
//	        roleService.createOrUpdateRole(role);
//
//	        redirectAttributes.addFlashAttribute("message", "사용자 정보가 성공적으로 업데이트되었습니다.");
//	    } catch (Exception e) {
//	        redirectAttributes.addFlashAttribute("error", "사용자 정보 업데이트 중 오류가 발생했습니다: " + e.getMessage());
//	    }
//	    return "redirect:/manager/user";
//	}
	
	@PostMapping("/user/edit/{userId}")
	public ResponseEntity<?> updateUser(
	    @PathVariable int userId,
	    @RequestParam String name,
	    @RequestParam String email,
	    @RequestParam String position,
	    @RequestParam(required = false) Boolean projectCreate,
	    @RequestParam(required = false) Boolean projectRead,
	    @RequestParam(required = false) Boolean projectUpdate,
	    @RequestParam(required = false) Boolean projectDelete
	) {
	    try {
	        UserVO user = userService.getUserById(userId);
	        if (user == null) {
	            return ResponseEntity.badRequest().body("사용자를 찾을 수 없습니다.");
	        }

	        user.setUserName(name);
	        user.setUserEmail(email);
	        user.setUserPosition(position);

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