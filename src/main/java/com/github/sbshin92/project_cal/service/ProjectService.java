package com.github.sbshin92.project_cal.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.github.sbshin92.project_cal.data.vo.ProjectVO;
import com.github.sbshin92.project_cal.data.vo.UserVO;

@Service
public interface ProjectService {
//
//	List<ProjectVO> getAllProjects(); // 프로젝트조회
//	public List<ProjectVO> getProjectsWithPage(int page, int size);
//	public Integer getTotalProjectCount();
//	
//	public ProjectVO getProjectById(int projectId); // 프로젝트 아이디로 프로젝트 조회	
//	public boolean createProject(ProjectVO project) throws IOException; // 생성
//	public boolean updateProject(ProjectVO project); // 수정
//	public boolean deleteProject(int projectId); // 삭제
//
////	 // 멤버조회 및 멤버 추가
//	List<UserVO> getAllUsers(); // 전체 조회
//	List<UserVO> getProjectMembers(int projectId);
//    boolean isUserProjectMember(int userId, int projectId); // 프로젝트에 추가되어있는 사람 조회
//    boolean addMemberProject(int userId, int projectId); // 프로젝트에 멤버 추가
//    boolean deleteProjectUser(int userId, int projectId); // 멤버 삭제
//    public List<ProjectVO> searchedProjects(String projectTitle); // 지원 추가 0718// 프로젝트타이틀을 통한 프로젝트목록의 검색기능구현
//--------------------------------------------------------------------------	 
	
	List<ProjectVO> getAllProjects();
    List<ProjectVO> getProjectsWithPage(int page, int size);
    Integer getTotalProjectCount();
    
    ProjectVO getProjectById(int projectId);
    void createProject(ProjectVO project) throws IOException;
    boolean updateProject(ProjectVO project);
    boolean deleteProject(int projectId);
    
    List<UserVO> getAllUsers();
    List<UserVO> getProjectMembers(int projectId);
    boolean isUserProjectMember(int userId, int projectId);
    boolean addMemberProject(int userId, int projectId);
    boolean deleteProjectUser(int userId, int projectId);
    public List<ProjectVO> searchedProjects(String projectTitle);
    
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