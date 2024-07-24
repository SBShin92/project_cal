package com.github.sbshin92.project_cal.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.github.sbshin92.project_cal.data.vo.ProjectVO;
import com.github.sbshin92.project_cal.data.vo.UserVO;

@Service
public interface ProjectService {	 
	
	List<ProjectVO> getAllProjects(); // 프로젝트 조회
    List<ProjectVO> getProjectsWithPage(int page, int size);
    Integer getTotalProjectCount();    
    
	public ProjectVO getProjectById(int projectId); // 프로젝트 아이디로 프로젝트 조회	
	public boolean createProject(ProjectVO project) throws IOException; // 생성
	public boolean updateProject(ProjectVO project); // 수정
	public boolean deleteProject(int projectId); // 삭제

//	 // 멤버조회 및 멤버 추가
	List<UserVO> getAllUsers(); // 전체 조회
    boolean isUserProjectMember(int userId, int projectId); // 프로젝트에 추가할수있는 사람 조회
    List<UserVO> getProjectMembers(int projectId); // 프로젝트에 들어있는 사람 조회
    boolean addMemberProject(int userId, int projectId); // 프로젝트에 멤버 추가
    boolean deleteProjectUser(Integer userId, int projectId); // 멤버 삭제
    public List<ProjectVO> searchedProjects(String projectTitle, int page); //0724 프로젝트타이틀을 통한 프로젝트목록 검색
    public int getTotalProjectsCount(String projectTitle);  //0724 프로젝트타이틀을 통한 프로젝트목록 검색
}