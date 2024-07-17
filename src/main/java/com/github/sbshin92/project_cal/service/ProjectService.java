package com.github.sbshin92.project_cal.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.github.sbshin92.project_cal.data.vo.ProjectFileVO;
import com.github.sbshin92.project_cal.data.vo.ProjectVO;
import com.github.sbshin92.project_cal.data.vo.UserVO;

@Service
public interface ProjectService {

	 void createProject(ProjectVO project) throws IOException;
	    ProjectVO getProjectById(int projectId);
	    List<ProjectVO> getAllProjects();
	    boolean updateProject(ProjectVO project);
	    boolean deleteProject(int projectId);

	    List<UserVO> getProjectMembers(Integer projectId);
	    static List<ProjectFileVO> getFilesByProjectId(int projectId) {
			// TODO Auto-generated method stub
			return null;
		}
		void createProjectWithFiles(ProjectVO project, List<MultipartFile> files) throws IOException;
		void addFileToProject(ProjectFileVO projectFileVO);
	
	    void addFileToProject(ProjectFileVO projectFile, MultipartFile file) throws IOException;
	    ProjectFileVO getFileById(int fileId);
	    boolean deleteProjectFile(int fileId);
		boolean isUserProjectMember(Object currentUserId, int projectId);
		void createProject(ProjectVO project, Integer userId, MultipartFile file, List<MultipartFile> files)
				throws IOException;
}