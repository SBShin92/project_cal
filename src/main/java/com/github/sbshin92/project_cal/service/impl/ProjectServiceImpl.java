package com.github.sbshin92.project_cal.service.impl;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.sbshin92.project_cal.data.dao.ProjectsDAO;
import com.github.sbshin92.project_cal.data.vo.ProjectVO;
import com.github.sbshin92.project_cal.data.vo.UserVO;
import com.github.sbshin92.project_cal.service.ProjectService;

/**
 * ProjectService 인터페이스의 구현 클래스입니다. 프로젝트 관련 비즈니스 로직을 처리합니다.
 */
@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectsDAO projectsDAO;

	/**
	 * 새 프로젝트를 조회 및 생성합니다.
	 * 
	 * @param project 생성할 프로젝트 정보
	 * @throws IOException 파일 업로드 중 오류 발생 시
	 */

	@Override
	public List<ProjectVO> getAllProjects() {
		return projectsDAO.findAll();
	}

	@Override
	public List<ProjectVO> getProjectsWithPage(int page, int size) {
		int offset = (page - 1) * size;
		return projectsDAO.findAllWithRowBounds(new RowBounds(offset, size));
	}

	@Override
	public Integer getTotalProjectCount() {
		return projectsDAO.getTotalProjectCount();
	}

	@Override
	public ProjectVO getProjectById(int projectId) {
		return projectsDAO.findById(projectId);
	}

	@Override
	@Transactional // 트랜잭션 처리를 위한 어노테이션
	public boolean createProject(ProjectVO project) throws IOException {
		return 1 == projectsDAO.insert(project);
	}

	@Override
	@Transactional
	public boolean updateProject(ProjectVO project) {
		return projectsDAO.update(project) > 0;
	}

	@Override
	@Transactional
	public boolean deleteProject(int projectId) {
		return projectsDAO.delete(projectId) > 0;
	}
	/*
	 * 이건 모
	 * 
	 * @Override boolean updated = projectsDAO.update(project) > 0; return updated;
	 * }
	 */
	/**
	 * 프로젝트를 삭제합니다.
	 * 
	 * @param projectId 삭제할 프로젝트 ID
	 * @return 삭제 성공 여부
	 */

	/**
	 * 사용자가 프로젝트의 멤버인지 확인합니다. isUserProjectMember
	 * 
	 * @param userId    확인할 사용자 ID
	 * @param projectId 확인할 프로젝트 ID
	 * @return 프로젝트 멤버 여부
	 */

	/**
	 * 프로젝트 관련 파일들을 업로드합니다. uploadProjectFiles
	 * 
	 * @param project 파일을 업로드할 프로젝트
	 * @throws IOException 파일 업로드 중 오류 발생 시
	 */

	/**
	 * 프로젝트 관련 파일들을 삭제합니다.
	 * 
	 * @param filePaths 삭제할 파일 경로 목록
	 */

//-------------------------------------------------------------------------------------	
	/**
	 * 프로젝트의 멤버 목록을 조회합니다.멤버조회 추가 삭제 
	 * 
	 * @param projectId 조회할 프로젝트 ID
	 * @return 프로젝트 멤버 목록
	 */
	//데이터베이스에 들어있는 모든 사원조회
	@Override
	public List<UserVO> getAllUsers() {	
		return projectsDAO.getAllUsers();
	}
	
	// 프로젝트에 참여중인 사람 조회
	@Override
	public boolean isUserProjectMember(int userId, int projectId) {
		return projectsDAO.isUserProjectMember(userId, projectId);
	}
	
	// 추가 가능한 사람 조회
	@Override
	public List<UserVO> getProjectMembers(int projectId) {
		return projectsDAO.getProjectMembers(projectId);
	}
	
	// 프로젝트에 멤버 추가
	@Override
	public boolean addMemberProject(int userId, int projectId) {
		return projectsDAO.addMemberProject(userId, projectId) > 0;
	}

	// 프로젝트에서 멤버 삭제
	@Override
	public boolean deleteProjectUser(int userId, int projectId) {
		return projectsDAO.deleteProjectUser(userId, projectId) > 0;
	}

	// 지원 추가 07 18 //22
	@Override
	public List<ProjectVO> searchedProjects(String projectTitle) {
		// 검색어가 projectTitle이거나 비어있는 경우 처리
		if (projectTitle == null || projectTitle.trim().isEmpty()) {
			return List.of(); // 빈리스트 반환 또는 다른 적절한 처리
		}
		// 검색어 전처리 (옵션)
		String processedTitle = projectTitle.trim(); // 앞뒤 공백 제거\
		return projectsDAO.findByProjectTitle(processedTitle);
	}

}
