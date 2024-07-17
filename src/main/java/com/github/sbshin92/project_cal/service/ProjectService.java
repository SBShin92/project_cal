package com.github.sbshin92.project_cal.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.github.sbshin92.project_cal.data.vo.ProjectVO;

@Service
public interface ProjectService {

	List<ProjectVO> getAllProjects();
	public ProjectVO getProjectById(int projectId);
	
	public boolean createProject(ProjectVO project) throws IOException;
	public boolean updateProject(ProjectVO project);
	public boolean deleteProject(int projectId);
	 
	 
	 
	 
	 
	 
//
//    List<UserVO> getProjectMembers(Integer projectId);
//    static List<ProjectFileVO> getFilesByProjectId(int projectId) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	void addFileToProject(ProjectFileVO projectFileVO);
//
//    void addFileToProject(ProjectFileVO projectFile, MultipartFile file) throws IOException;
//	boolean isUserProjectMember(Object currentUserId, int projectId);
}