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
    public boolean updateProject(ProjectVO project) {
        return 1 == projectsDAO.update(project);
    }
    
    @Override
    public boolean createProject(ProjectVO project) {
        return 1 == projectsDAO.insert(project);
    }

    @Override
    public boolean deleteProject(int projectId) {
        return 1 == projectsDAO.delete(projectId);
    }

    @Override
    public List<ProjectVO> getAllProjects() {
        return projectsDAO.findAll();
    }
}