package com.github.sbshin92.project_cal.controller;

import java.util.List;

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
	
	@GetMapping({"", "/", "/projects"})
	public String managerProjectsPage(Model model, @RequestParam(defaultValue = "1") int page) {
		List<ProjectVO> projectVOs = projectService.getProjectsWithPage(page, 10);
		model.addAttribute("projectVOs", projectVOs);
		model.addAttribute("projectCount", projectService.getTotalProjectCount());
		model.addAttribute("totalPages", (projectVOs.size()));
		return "manager/manager-projects";
	}
	
	@GetMapping({"/users"})
	public String managerUsersPage(Model model) {
		List<UserVO> userVOs = userService.getAllUsers();
		model.addAttribute("userVOs", userVOs);

		return "manager/manager-users";
	}
	  //유저 삭제 
    @PostMapping("/users/delete/{userId}")
    public String deleteUser(@PathVariable Integer userId) {
    	//userId를 경로 변수로 받아와 사용자를 삭제
    	
        boolean deleted = userService.deleteUser(userId);
        //userService에서 실제 사용자를 삭제하는 비즈니스 로직을 호출
        
        return "redirect:/manager/users";
    }
//	@GetMapping("/roles")
//	public String managerRolesPage() {
//		return "manager/manager-roles";
//	}
//	
	
}
