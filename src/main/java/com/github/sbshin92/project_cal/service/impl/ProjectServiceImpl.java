package com.github.sbshin92.project_cal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.sbshin92.project_cal.data.dao.ProjectsDAO;
import com.github.sbshin92.project_cal.data.vo.ProjectVO;
import com.github.sbshin92.project_cal.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService {
	
	@Autowired
    private ProjectsDAO projectsDAO;
	
	 
	    public ProjectVO getProjectById(int projectId) {
	        return projectsDAO.findById(projectId);
	    }
	
	    public void updateProject(ProjectVO projectvo) {
	    	projectsDAO.update(projectvo);
	    }

	    
	    public void createProject(ProjectVO projectvo) {
	    	projectsDAO.insert(projectvo);
	    }

	
	    public void deleteProject(int projectId) {
	    	projectsDAO.delete(projectId);
	    }
	}


