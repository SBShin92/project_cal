package com.github.sbshin92.project_cal.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.github.sbshin92.project_cal.data.vo.ProjectVO;

@Service
public interface ProjectService {

<<<<<<< HEAD
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
=======
	 void createProject(ProjectVO project) throws IOException;
	    ProjectVO getProjectById(int projectId);
	    List<ProjectVO> getAllProjects();
	    boolean updateProject(ProjectVO project);
	    boolean deleteProject(int projectId);	    
	    boolean isUserProjectMember(Integer userId, Integer projectId); // 프로젝트 멤버확인
	    List<UserVO> getProjectMembers(Integer projectId); // 프로젝트 멤버 목록 조회
	    List<ProjectFileVO> getFilesByProjectId(int projectId);
		void createProjectWithFiles(ProjectVO project, List<MultipartFile> files) throws IOException;
		void addFileToProject(ProjectFileVO projectFileVO);
	    void createProject(ProjectVO project, MultipartFile file, List<MultipartFile> files) throws IOException;
	    void addFileToProject(ProjectFileVO projectFile, MultipartFile file) throws IOException;
	    ProjectFileVO getFileById(int fileId);
	    boolean deleteProjectFile(int fileId);
	    
	    boolean addProjectUser(int userId, int projectId); // 프로젝트에 유저추가
	    List<UserVO> getAllUsers();
//	    List<UserVO> getProjectMembers(int projectId); // 특정 프로젝트의 멤버 목록을 가져옴
	    List<UserVO> getNonProjectMembers(int projectId); // 특정 프로젝트에 속하지 않은 사용자 목록을 가져옴
		void deleteProjectMember(int userId, int projectId);
		
>>>>>>> refs/remotes/origin/develop_minjoon
}