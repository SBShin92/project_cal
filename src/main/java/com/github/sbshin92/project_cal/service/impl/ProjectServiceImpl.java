package com.github.sbshin92.project_cal.service.impl;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.github.sbshin92.project_cal.data.dao.ProjectsDAO;
import com.github.sbshin92.project_cal.data.vo.ProjectVO;
import com.github.sbshin92.project_cal.data.vo.UserVO;
import com.github.sbshin92.project_cal.service.FileService;
import com.github.sbshin92.project_cal.service.ProjectService;

/**
 * ProjectService 인터페이스의 구현 클래스입니다.
 * 프로젝트 관련 비즈니스 로직을 처리합니다.
 */
@Service
public class ProjectServiceImpl implements ProjectService {

	
    // 로깅을 위한 Logger 인스턴스 생성
    private static final Logger logger = LoggerFactory.getLogger(ProjectServiceImpl.class);
	
    private ProjectsDAO projectsDAO;
	private FileService fileService;

  
    @Autowired
    public ProjectServiceImpl(ProjectsDAO projectsDAO, FileService fileService) {
        this.projectsDAO = projectsDAO;
        this.fileService = fileService;
    }
  
    /**
     * 새 프로젝트를 생성합니다.
     * @param project 생성할 프로젝트 정보
     * @throws IOException 파일 업로드 중 오류 발생 시
     */
    @Override
    @Transactional // 트랜잭션 처리를 위한 어노테이션
    public void createProject(ProjectVO project) throws IOException {
        // 프로젝트 데이터 유효성 검사
        validateProject(project);
        
        logger.info("Creating new project: {}", project.getProjectTitle());
        
        // 프로젝트 정보를 데이터베이스에 삽입
        projectsDAO.insert(project);
        
        System.out.println("projectserviceimpl" + project.getProjectId());
        
        // 프로젝트 관련 파일 업로드
        uploadProjectFiles(project);
        
        logger.info("Project created successfully with ID: {}", project.getProjectId());
    }

    /**
     * 프로젝트 ID로 프로젝트를 조회합니다.
     * @param projectId 조회할 프로젝트 ID
     * @return 조회된 프로젝트 정보
     */
    @Override
    public ProjectVO getProjectById(int projectId) {
        logger.debug("Fetching project with ID: {}", projectId);
        
        ProjectVO project = projectsDAO.findById(projectId);
        if (project == null) {
            logger.warn("Project not found with ID: {}", projectId);
        }
        return project;
    }

    /**
     * 모든 프로젝트를 조회합니다.
     * @return 모든 프로젝트 목록
     */
    @Override
    public List<ProjectVO> getAllProjects() {
        logger.debug("Fetching all projects");
        return projectsDAO.findAll();
    }

    /**
     * 프로젝트 정보를 업데이트합니다.
     * @param project 업데이트할 프로젝트 정보
     * @return 업데이트 성공 여부
     */
    @Override
    @Transactional
    public boolean updateProject(ProjectVO project) {
        validateProject(project);
        logger.info("Updating project with ID: {}", project.getProjectId());
        
        boolean updated = projectsDAO.update(project) > 0;
        if (updated) {
            logger.info("Project updated successfully");
        } else {
            logger.warn("Failed to update project with ID: {}", project.getProjectId());
        }
        return updated;
    }

    /**
     * 프로젝트를 삭제합니다.
     * @param projectId 삭제할 프로젝트 ID
     * @return 삭제 성공 여부
     */
    @Override
    @Transactional
    public boolean deleteProject(int projectId) {
        logger.info("Deleting project with ID: {}", projectId);
        
        // 프로젝트 관련 파일 경로 조회
        List<String> filePaths = projectsDAO.getProjectFilePaths(projectId);
        
        // 프로젝트 삭제
        boolean deleted = projectsDAO.delete(projectId) > 0;
        
        if (deleted) {
            // 프로젝트 관련 파일 삭제
            deleteProjectFiles(filePaths);
            logger.info("Project and associated files deleted successfully");
        } else {
            logger.warn("Failed to delete project with ID: {}", projectId);
        }
        return deleted;
    }

    /**
     * 사용자가 프로젝트의 멤버인지 확인합니다.
     * @param userId 확인할 사용자 ID
     * @param projectId 확인할 프로젝트 ID
     * @return 프로젝트 멤버 여부
     */
    @Override
    public boolean isUserProjectMember(Integer userId, Integer projectId) {
        return projectsDAO.isUserProjectMember(userId, projectId);
    }

    /**
     * 프로젝트의 멤버 목록을 조회합니다.
     * @param projectId 조회할 프로젝트 ID
     * @return 프로젝트 멤버 목록
     */
    @Override
    public List<UserVO> getProjectMembers(Integer projectId) {
        return projectsDAO.getProjectMembers(projectId);
    }

    /**
     * 프로젝트 관련 파일들을 업로드합니다.
     * @param project 파일을 업로드할 프로젝트
     * @throws IOException 파일 업로드 중 오류 발생 시
     */
    private void uploadProjectFiles(ProjectVO project) throws IOException {
        if (project.getFiles() != null && !project.getFiles().isEmpty()) {
            for (MultipartFile file : project.getFiles()) {
                String filePath = fileService.saveFile(file);
                projectsDAO.insertFile(project.getProjectId(), file.getOriginalFilename(), filePath, file.getSize());
                logger.debug("File uploaded: {}", filePath);
            }
        }
    }

    /**
     * 프로젝트 관련 파일들을 삭제합니다.
     * @param filePaths 삭제할 파일 경로 목록
     */
    private void deleteProjectFiles(List<String> filePaths) {
        for (String filePath : filePaths) {
            try {
                fileService.deleteFile(filePath);
                logger.debug("File deleted: {}", filePath);
            } catch (IOException e) {
                logger.error("Failed to delete file: {}", filePath, e);
            }
        }
    }

    /**
     * 프로젝트 데이터의 유효성을 검사합니다.
     * @param project 검사할 프로젝트 데이터
     * @throws IllegalArgumentException 유효하지 않은 데이터인 경우
     */
    private void validateProject(ProjectVO project) {
        if (project == null) {
            throw new IllegalArgumentException("Project cannot be null");
        }
        if (project.getProjectTitle() == null || project.getProjectTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Project title cannot be empty");
        }
        // 필요에 따라 추가적인 유효성 검사 로직을 구현할 수 있습니다.
    }
}