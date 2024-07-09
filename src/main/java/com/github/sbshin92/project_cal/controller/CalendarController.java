package com.github.sbshin92.project_cal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.sbshin92.project_cal.data.vo.ProjectVO;
import com.github.sbshin92.project_cal.service.ProjectService;

@RequestMapping("/calendar")
@Controller
public class CalendarController {

	@GetMapping
	public String calendarPage() {
		return "calendar/calendar";
	}
	
	@Autowired
	private ProjectService projectService;
	
	 @GetMapping("/detail/{id}")
	    public String projectDetail(@PathVariable("id") int projectId, Model model) {
	        ProjectVO project = projectService.getProjectById(projectId);
	        model.addAttribute("project", project);
	        return "detail"; // detail.html 파일명
	    }
	
}
