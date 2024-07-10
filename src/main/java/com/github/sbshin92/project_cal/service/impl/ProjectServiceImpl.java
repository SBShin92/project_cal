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
	
    @Override
    public ProjectVO getProjectById(int projectId) {
        return projectsDAO.findById(projectId);
    }

    @Override
    public void updateProject(ProjectVO project) {
        projectsDAO.update(project);
    }
    
    @Override
    public void createProject(ProjectVO project) {
        projectsDAO.insert(project);
    }

    @Override
    public void deleteProject(int projectId) {
        projectsDAO.delete(projectId);
    }

    @Override
    public List<ProjectVO> getAllProjects() {
        return projectsDAO.findAll();
    }
}