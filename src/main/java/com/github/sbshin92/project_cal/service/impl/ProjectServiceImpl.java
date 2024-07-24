package com.github.sbshin92.project_cal.service.impl;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.sbshin92.project_cal.data.dao.ProjectsDAO;
import com.github.sbshin92.project_cal.data.dao.UsersDAO;
import com.github.sbshin92.project_cal.data.vo.ProjectVO;
import com.github.sbshin92.project_cal.data.vo.UserVO;
import com.github.sbshin92.project_cal.service.NotificationService;
import com.github.sbshin92.project_cal.service.ProjectService;

/**
 * ProjectService 인터페이스의 구현 클래스입니다. 프로젝트 관련 비즈니스 로직을 처리합니다.
 */
@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectsDAO projectsDAO;
	
	@Autowired
	private UsersDAO usersDAO;
	
	@Autowired
	private NotificationService notificationService;

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
		return projectsDAO.delete(projectId) > 0;
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
	@Transactional
	public boolean addMemberProject(int userId, int projectId) {
		boolean isAdded = projectsDAO.addMemberProject(userId, projectId) > 0;
		
		if(isAdded) {
			UserVO member = usersDAO.findById(userId);
			ProjectVO project = projectsDAO.findById(projectId);
			UserVO sender = usersDAO.findById(project.getUserId());
			String content = "축하드립니다 " + member.getUserName() + "님" + project.getProjectTitle() + "프로젝트에 참여하게되었습니다";
			notificationService.sendNotification(sender,member,"프로젝트 초대", content);
			
		}
		return isAdded;
	}

	// 프로젝트에서 멤버 삭제
	@Override
	public boolean deleteProjectUser(Integer userId, int projectId) {
		return projectsDAO.deleteProjectUser(userId, projectId) > 0;
	}

//------------------------------------------------------------------------
	//0724
			@Override
			public List<ProjectVO> searchedProjects(String projectTitle, int page) {
			 	// 검색어(projectTitle) 유효성 검사: 검색어가 projectTitle이거나 비어있는 경우 처리
					if (projectTitle == null || projectTitle.trim().isEmpty()) {
						return List.of();	//빈리스트 반환 또는 다른 적절한 처리 
					}
					
					// 검색어 전처리 (옵션)
			        String processedTitle = projectTitle.trim(); // 앞뒤 공백 제거
			        
			        //페이지 세팅
			        int size = 10; //페이지 크기를 10으로 설정
					int offset = (page - 1) * size; //오프셋을 계산(현재 페이지 번호에 따라).
					
				//처리된 검색어와 RowBounds 객체(페이징을 위해)를 사용하여 projectsDAO의 searchedProjects 메소드를 호출	
				return projectsDAO.searchedProjects(processedTitle, new RowBounds(offset, size));
			}

			//0724
			@Override
			public int getTotalProjectsCount(String projectTitle) {


			  	// 검색어(projectTitle) 유효성 검사// 검색어가 null이거나 비어있는 경우 처리
				if (projectTitle == null || projectTitle.trim().isEmpty()) {
					return 0;	//빈리스트 반환
				}
				// 검색어 전처리 (옵션)
			    String processedTitle = projectTitle.trim(); // 앞뒤 공백 제거
			    //projectsDAO의 getTotalProjectsCount 메소드를 호출하여 총 프로젝트 수를 가져옴
			    return projectsDAO.getTotalProjectsCount(projectTitle);
		}
		
		
		// 멤버 추가시 알람
		@Override
		@Transactional // 트랜잭션 처리를 위한 어노테이션
		public boolean createProject(ProjectVO project, List<Integer> memberIds) throws IOException {
			boolean isCreated = projectsDAO.insert(project) > 0;
			
			if(isCreated) {
				for(Integer memberId : memberIds) {
					projectsDAO.addMemberProject(memberId, project.getProjectId());
					UserVO member = usersDAO.findById(memberId);
					UserVO sender = usersDAO.findById(project.getUserId());
					String contnet = member.getUserName() + "님" + project.getProjectTitle()+"프로젝트에 추가되었습니다";
					notificationService.sendNotification(sender,member,"프로젝트 초대", contnet);
				}
			}
			
			return isCreated;
		}	

//
//				  	// 검색어(projectTitle) 유효성 검사// 검색어가 null이거나 비어있는 경우 처리
//					if (projectTitle == null || projectTitle.trim().isEmpty()) {
//						return 0;	//빈리스트 반환
//					}
//					// 검색어 전처리 (옵션)
//				    String processedTitle = projectTitle.trim(); // 앞뒤 공백 제거
//				    //projectsDAO의 getTotalProjectsCount 메소드를 호출하여 총 프로젝트 수를 가져옴
//				    return projectsDAO.getTotalProjectsCount(projectTitle);
//			}	
			

}	
