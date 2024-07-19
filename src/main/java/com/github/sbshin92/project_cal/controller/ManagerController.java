package com.github.sbshin92.project_cal.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.sbshin92.project_cal.data.vo.ProjectVO;
import com.github.sbshin92.project_cal.data.vo.UserVO;
import com.github.sbshin92.project_cal.service.ProjectService;
import com.github.sbshin92.project_cal.service.UserService;

@RequestMapping("/manager")
@Controller
public class ManagerController {
	@Autowired
	private ProjectService projectService;

	@Autowired
	private UserService userService;

	@GetMapping({ "", "/", "/projects" })
	public String managerProjectsPage(Model model, @RequestParam(defaultValue = "1") int page) {
		List<ProjectVO> projectVOs = projectService.getProjectsWithPage(page, 10);
		model.addAttribute("projectVOs", projectVOs);
		model.addAttribute("projectCount", projectService.getTotalProjectCount());
		model.addAttribute("totalPages", (projectVOs.size()));
		return "manager/manager-projects";
	}

	@GetMapping({ "/users" })
	public String managerUsersPage(Model model) {
		List<UserVO> userVOs = userService.getAllUsers();
		model.addAttribute("userVOs", userVOs);

		return "manager/manager-users";
	}

	// 유저 삭제
	@PostMapping("/users/delete/{userId}")
	public String deleteUser(@PathVariable Integer userId) {
		// userId를 경로 변수로 받아와 사용자를 삭제

		boolean deleted = userService.deleteUser(userId);
		// userService에서 실제 사용자를 삭제하는 비즈니스 로직을 호출

		return "redirect:/manager/users";
	}

	// 유저 수정
	@PostMapping("/users/edit/{userId}")
	public Map<String, Object> editUser(@PathVariable int userId, @RequestParam String name, @RequestParam String email,
			@RequestParam String position) {
		Map<String, Object> response = new HashMap<>();
		try {
// 사용자 정보 업데이트 로직
			UserVO user = userService.getUserById(userId);
			if (user != null) {
				user.setUserName(name);
				user.setUserEmail(email);
				user.setUserPosition(position);
				userService.updateUser(user);
				response.put("success", true);
				response.put("message", "사용자 정보가 업데이트되었습니다.");
			} else {
				response.put("success", false);
				response.put("message", "사용자를 찾을 수 없습니다.");
			}
		} catch (Exception e) {
			response.put("success", false);
			response.put("message", "사용자 정보 업데이트 중 오류가 발생했습니다.");
		}
		return response;
	}

//	@GetMapping("/roles")
//	public String managerRolesPage() {
//		return "manager/manager-roles";
//	}
//	

}
