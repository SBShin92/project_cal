package com.github.sbshin92.project_cal.controller.api;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.sbshin92.project_cal.data.vo.ProjectVO;
import com.github.sbshin92.project_cal.service.ProjectService;

@RestController
@RequestMapping("/api/projects")
public class ProjectsTableApiController {
	
	@Autowired
	private ProjectService projectService;

	@GetMapping("")
	public List<ProjectVO> getProjectList() {
		System.out.println(projectService.getAllProjects());
		return projectService.getAllProjects();
	}
	
    @GetMapping("/add")
    public String addProject() {
        ProjectVO projectVO = ProjectVO.builder()
        		.userId(1)
        		.projectTitle("테스트 프로젝트 제목")
        		.projectDescription("프로젝트 설명입니다\n설명을 하고있습니다.")
                .build();

       try {
    	   projectService.createProject(projectVO);    	   
       } catch (IOException e) {};

        return "<h1>Done!! Project ID: " + projectVO.getProjectId() + "</h1>";
    }
	
	
	
}
