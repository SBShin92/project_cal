package com.github.sbshin92.project_cal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.sbshin92.project_cal.data.vo.ProjectVO;
import com.github.sbshin92.project_cal.service.ProjectService;

@RequestMapping("/manager")
@Controller
public class ManagerController {
	
	@Autowired
	private ProjectService projectService;
	
	
	@GetMapping({"", "/", "/projects"})
	public String managerProjectsPage(Model model, @RequestParam(defaultValue = "1") int page) {
		List<ProjectVO> projectVOs = projectService.getProjectsWithPage(page, 10);
		model.addAttribute("projectVOs", projectVOs);
		model.addAttribute("projectCount", projectService.getTotalProjectCount());
		model.addAttribute("totalPages", (projectVOs.size()));
		return "manager/manager-projects";
	}
	@GetMapping({"/users"})
	public String managerUsersPage() {
		return "manager/manager-users";
	}
	@GetMapping("/roles")
	public String managerRolesPage() {
		return "manager/manager-roles";
	}
	
}
