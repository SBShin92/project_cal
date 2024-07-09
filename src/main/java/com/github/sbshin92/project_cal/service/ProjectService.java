package com.github.sbshin92.project_cal.service;

import com.github.sbshin92.project_cal.data.vo.ProjectVO;

public interface ProjectService {

	ProjectVO getProjectById(int projectId);

	void updateProject(ProjectVO project);

	void createProject(ProjectVO project);

	void deleteProject(int projectId);

}
