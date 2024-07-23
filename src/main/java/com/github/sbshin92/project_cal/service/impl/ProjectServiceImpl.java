package com.github.sbshin92.project_cal.service.impl;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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
	 * 새 프로젝트를 생성합니다.
	 * 
	 * @param project 생성할 프로젝트 정보
	 * @throws IOException 파일 업로드 중 오류 발생 시
	 */
	@Override
	@Transactional // 트랜잭션 처리를 위한 어노테이션
	public boolean createProject(ProjectVO project) throws IOException {
		return 1 == projectsDAO.insert(project);
	}

	/**
	 * 프로젝트 ID로 프로젝트를 조회합니다.
	 * 
	 * @param projectId 조회할 프로젝트 ID
	 * @return 조회된 프로젝트 정보
	 */
	@Override
	public ProjectVO getProjectById(int projectId) {

		ProjectVO project = projectsDAO.findById(projectId);
		if (project == null) {
		}
		return project;
	}

	@Override
	public Integer getTotalProjectCount() {
		return projectsDAO.getTotalProjectCount();
	}

	/**
	 * 모든 프로젝트를 조회합니다.
	 * 
	 * @return 모든 프로젝트 목록
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

	/**
	 * 프로젝트 정보를 업데이트합니다.
	 * 
	 * @param project 업데이트할 프로젝트 정보
	 * @return 업데이트 성공 여부
	 */
	@Override
	@Transactional
	public boolean updateProject(ProjectVO project) {
//        validateProject(project);

		boolean updated = projectsDAO.update(project) > 0;
		return updated;
	}

	/**
	 * 프로젝트를 삭제합니다.
	 * 
	 * @param projectId 삭제할 프로젝트 ID
	 * @return 삭제 성공 여부
	 */
	@Override
	@Transactional
	public boolean deleteProject(int projectId) {

		// 프로젝트 삭제
		boolean deleted = projectsDAO.delete(projectId) > 0;


		return deleted;
	}
	
	//지원 추가 0722
		/*
		@Override
		public List<ProjectVO> searchedProjects(String projectTitle) {
		 	// 검색어가 projectTitle이거나 비어있는 경우 처리
				if (projectTitle == null || projectTitle.trim().isEmpty()) {
					return List.of();	//빈리스트 반환 또는 다른 적절한 처리 
				}
					// 검색어 전처리 (옵션)
		        String processedTitle = projectTitle.trim(); // 앞뒤 공백 제거\
			return projectsDAO.searchedProjects(processedTitle);
		}
		*/

    //지원 추가 07 18 //22
	@Override
	public List<ProjectVO> searchedProjects(String projectTitle) {
	 	// 검색어가 projectTitle이거나 비어있는 경우 처리
			if (projectTitle == null || projectTitle.trim().isEmpty()) {
				return List.of();	//빈리스트 반환 또는 다른 적절한 처리 
			}
				// 검색어 전처리 (옵션)
	        String processedTitle = projectTitle.trim(); // 앞뒤 공백 제거\
		return projectsDAO.findByProjectTitle(processedTitle);
	}
	
	

    

	
//-------------------------------------------------------------------------------------	
	/**
	 * 프로젝트의 멤버 목록을 조회합니다.
	 * 
	 * @param projectId 조회할 프로젝트 ID
	 * @return 프로젝트 멤버 목록
	 */
	@Override
    public List<UserVO> getAllUsers() {
        return projectsDAO.getAllUsers();
    }


    @Override
    public boolean isUserProjectMember(int userId, int projectId) {
        return projectsDAO.isUserProjectMember(userId, projectId);
    }

    @Override
    public boolean addMemberProject(int userId, int projectId) {
        return projectsDAO.addMemberProject(userId, projectId) > 0;
    }

    @Override
    public boolean deleteProjectUser(int userId, int projectId) {
        return projectsDAO.deleteProjectUser(userId, projectId) > 0;
    }

	

}