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
     * 새 프로젝트를 생성합니다.
     * 
     * @param project 생성할 프로젝트 정보
     * @throws IOException 파일 업로드 중 오류 발생 시
     */
    @Override
    @Transactional // 트랜잭션 처리를 위한 어노테이션
    public void createProject(ProjectVO project) throws IOException {
        projectsDAO.insert(project);
    }

    @Override
    public ProjectVO getProjectById(int projectId) {
        return projectsDAO.findById(projectId);
    }

    @Override
    public Integer getTotalProjectCount() {
        return projectsDAO.getTotalProjectCount();
    }

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
    @Transactional
    public boolean updateProject(ProjectVO project) {
        return projectsDAO.update(project) > 0;
    }

    @Override
    @Transactional
    public boolean deleteProject(int projectId) {
        return projectsDAO.delete(projectId) > 0;
    }

    @Override
    public List<UserVO> getAllUsers() {
        return projectsDAO.getAllUsers();
    }

    @Override
    public List<UserVO> getProjectMembers(int projectId) {
        return projectsDAO.getProjectMembers(projectId);
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

    @Override
    public List<ProjectVO> searchedProjects(String projectTitle) {
        if (projectTitle == null || projectTitle.trim().isEmpty()) {
            return List.of(); // 빈 리스트 반환
        }
        String processedTitle = projectTitle.trim(); // 앞뒤 공백 제거
        return projectsDAO.searchedProjects(processedTitle);
    }
}
