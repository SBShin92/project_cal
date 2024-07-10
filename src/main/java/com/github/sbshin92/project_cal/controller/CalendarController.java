package com.github.sbshin92.project_cal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import com.github.sbshin92.project_cal.data.vo.ProjectVO;
import com.github.sbshin92.project_cal.service.ProjectService;

@RequestMapping("/calendar")
@Controller
public class CalendarController {

	@GetMapping
	public String calendarPage() {
		return "/calendar/calendar";
	}
	
	@Autowired
	private ProjectService projectService;
	

	/*
	 * @GetMapping("/detail/{id}") URL 패턴에 매핑
	 * {id}는 경로 변수로, 조회할 프로젝트의 ID를 나타냄
	 */
	@GetMapping("/detail/{id}")
	public String projectDetail(@PathVariable("id") int projectId, Model model) {
	    try {
	        // 프로젝트 서비스를 통해 지정된 ID의 프로젝트를 조회
	        ProjectVO project = projectService.getProjectById(projectId);
	        
	        // 프로젝트가 존재하지 않는 경우 NOT_FOUND 예외를 발생
	        if (project == null) {
	            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found");
	        }
	        
	        // 조회된 프로젝트 정보를 모델에 추가
	        // 이를 통해 뷰에서 프로젝트 정보를 사용가능
	        model.addAttribute("project", project);
	        
	        // calendar/detail 뷰를 반환
	        // 이 뷰는 프로젝트 상세 정보를 표시하는 JSP 페이지
	        return "calendar/detail";
	    } catch (Exception e) {
	        // 프로젝트 조회 중 예외가 발생한 경우 INTERNAL_SERVER_ERROR 예외를 발생
	        // 이는 클라이언트에게 500 에러를 반환
	        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error retrieving project", e);
	    }
	}
}
