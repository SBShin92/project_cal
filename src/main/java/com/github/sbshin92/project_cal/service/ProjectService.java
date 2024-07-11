package com.github.sbshin92.project_cal.service;

import com.github.sbshin92.project_cal.data.vo.ProjectVO;
import java.util.List;

public interface ProjectService {

    public ProjectVO getProjectById(int projectId);

    public boolean updateProject(ProjectVO project);

    public boolean createProject(ProjectVO project);

    public boolean deleteProject(int projectId);

    List<ProjectVO> getAllProjects();
}