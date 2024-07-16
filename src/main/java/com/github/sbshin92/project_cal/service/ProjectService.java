package com.github.sbshin92.project_cal.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.github.sbshin92.project_cal.data.vo.ProjectFileVO;
import com.github.sbshin92.project_cal.data.vo.ProjectVO;

@Service
public interface ProjectService {

	 void createProject(ProjectVO project) throws IOException;
	    ProjectVO getProjectById(int projectId);
	    List<ProjectVO> getAllProjects();
	    boolean updateProject(ProjectVO project);
	    boolean deleteProject(int projectId);
	    boolean addProjectUser(int userId);
//	    boolean isUserProjectMember(Integer userId, Integer projectId);
//	    List<UserVO> getProjectMembers(Integer projectId)
	    List<ProjectFileVO> getFilesByProjectId(int projectId);
		void createProjectWithFiles(ProjectVO project, List<MultipartFile> files) throws IOException;
		void addFileToProject(ProjectFileVO projectFileVO);
	    void createProject(ProjectVO project, MultipartFile file, List<MultipartFile> files) throws IOException;
	    void addFileToProject(ProjectFileVO projectFile, MultipartFile file) throws IOException;
	    ProjectFileVO getFileById(int fileId);
	    boolean deleteProjectFile(int fileId);
}