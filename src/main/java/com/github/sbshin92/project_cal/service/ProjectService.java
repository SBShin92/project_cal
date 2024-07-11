package com.github.sbshin92.project_cal.service;

import java.io.IOException;
import java.util.List;

import com.github.sbshin92.project_cal.data.vo.ProjectVO;
import com.github.sbshin92.project_cal.data.vo.UserVO;

public interface ProjectService {

	 void createProject(ProjectVO project) throws IOException;
	    ProjectVO getProjectById(int projectId);
	    List<ProjectVO> getAllProjects();
	    boolean updateProject(ProjectVO project);
	    boolean deleteProject(int projectId);
	    boolean isUserProjectMember(Integer userId, Integer projectId);
	    List<UserVO> getProjectMembers(Integer projectId);
}