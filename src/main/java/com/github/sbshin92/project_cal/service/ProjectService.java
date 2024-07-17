package com.github.sbshin92.project_cal.service;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.github.sbshin92.project_cal.data.vo.ProjectVO;
import com.github.sbshin92.project_cal.data.vo.UserVO;

@Service
public interface ProjectService {

	List<ProjectVO> getAllProjects(); // 프로젝트조회
	public ProjectVO getProjectById(int projectId); // 프로젝트 아이디로 프로젝트 조회
	
	public boolean createProject(ProjectVO project) throws IOException; // 생성
	public boolean updateProject(ProjectVO project); // 수정
	public boolean deleteProject(int projectId); // 삭제
	 
	 // 멤버조회 및 멤버 추가
	public List<UserVO> getProjectMembers(int userId); //멤버조회
	public int addMemberProject(int userId, int projectId); // 멤버추가
	public boolean isUserProjectMember(@Param("userId") Integer userId); // 이미 등록된 사용자
	public int deleteProjectUser(int userId, int projectId);
	public ProjectVO findById(int userId);
	
	 
	 
	 
	 
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