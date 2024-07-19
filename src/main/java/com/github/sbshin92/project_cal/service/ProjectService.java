package com.github.sbshin92.project_cal.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.github.sbshin92.project_cal.data.vo.ProjectVO;
import com.github.sbshin92.project_cal.data.vo.UserVO;

@Service
public interface ProjectService {

	List<ProjectVO> getAllProjects(); // 프로젝트조회
	public List<ProjectVO> getProjectsWithPage(int page, int size);
	public Integer getTotalProjectCount();
	
	public ProjectVO getProjectById(int projectId); // 프로젝트 아이디로 프로젝트 조회	
	public boolean createProject(ProjectVO project) throws IOException; // 생성
	public boolean updateProject(ProjectVO project); // 수정
	public boolean deleteProject(int projectId); // 삭제
	 

	
	 
	 

//	 // 멤버조회 및 멤버 추가
	List<UserVO> getAllUsers(); // 전체 조회
    boolean isUserProjectMember(int userId, int projectId); // 프로젝트에 추가되어있는 사람 조회
    boolean addMemberProject(int userId, int projectId); // 프로젝트에 멤버 추가
    boolean deleteProjectUser(int userId, int projectId); // 멤버 삭제

	 
	 
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