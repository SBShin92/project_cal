package com.github.sbshin92.project_cal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/manager")
@Controller
public class ManagerController {

	@GetMapping({"", "/", "/projects"})
	public String managerProjectsPage() {
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
