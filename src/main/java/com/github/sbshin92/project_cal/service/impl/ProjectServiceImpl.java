package com.github.sbshin92.project_cal.service.impl;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.sbshin92.project_cal.data.dao.ProjectsDAO;
import com.github.sbshin92.project_cal.data.vo.ProjectFileVO;
import com.github.sbshin92.project_cal.data.vo.ProjectVO;
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
	
    @Autowired
    private ProjectsDAO projectsDAO;
    
    @Autowired
	private FileService fileService;
    
 

        @Override
        @Transactional
        public void createProject(ProjectVO project, MultipartFile file, List<MultipartFile> files) throws IOException {
            logger.info("Creating new project: {}", project.getProjectTitle());
            
            // 프로젝트 정보를 데이터베이스에 삽입
            projectsDAO.insert(project);
            
            // 단일 파일 처리
            if (file != null && !file.isEmpty()) {
                String filePath = fileService.saveFile(file);
                projectsDAO.insertFile(project.getProjectId(), file.getOriginalFilename(), filePath, file.getSize());
            }
            
            // 다중 파일 처리
            if (files != null && !files.isEmpty()) {
                for (MultipartFile multipartFile : files) {
                    if (!multipartFile.isEmpty()) {
                        String filePath = fileService.saveFile(multipartFile);
                        projectsDAO.insertFile(project.getProjectId(), multipartFile.getOriginalFilename(), filePath, multipartFile.getSize());
                    }
                }
            }
            
            logger.info("Project created successfully with ID: {}", project.getProjectId());
        }

        @Override
        @Transactional
        public void addFileToProject(ProjectFileVO projectFile, MultipartFile file) throws IOException {
            if (file != null && !file.isEmpty()) {
                String filePath = fileService.saveFile(file);
                projectFile.setFilePath(filePath);
                projectFile.setFileSize(file.getSize());
                projectsDAO.insertFile(projectFile.getProjectId(), projectFile.getFileName(), filePath, file.getSize());
            }
        }

        @Override
        public ProjectFileVO getFileById(int fileId) {
            return projectsDAO.findFileById(fileId);
        }

        @Override
        @Transactional
        public boolean deleteProjectFile(int fileId) {
            ProjectFileVO file = projectsDAO.findFileById(fileId);
            if (file != null) {
                try {
                    fileService.deleteFile(file.getFilePath());
                    return projectsDAO.deleteFile(fileId) > 0;
                } catch (IOException e) {
                    logger.error("Failed to delete file: {}", file.getFilePath(), e);
                    return false;
                }
            }
            return false;
        }

        // ... 기존 코드 ...
    
  
    /**
     * 새 프로젝트를 생성합니다.
     * @param project 생성할 프로젝트 정보
     * @throws IOException 파일 업로드 중 오류 발생 시
     */
    @Override
    @Transactional // 트랜잭션 처리를 위한 어노테이션
    public void createProject(ProjectVO project) throws IOException {
        // 프로젝트 데이터 유효성 검사
//        validateProject(project);
        
        logger.info("Creating new project: {}", project.getProjectTitle());
        
        // 프로젝트 정보를 데이터베이스에 삽입
        projectsDAO.insert(project);
        
        System.out.println("projectserviceimpl" + project.getProjectId());
        
        // 프로젝트 관련 파일 업로드
//        uploadProjectFiles(project);
        
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
//        validateProject(project);
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
//        logger.info("Deleting project with ID: {}", projectId);
        
        // 프로젝트 관련 파일 경로 조회
//        List<String> filePaths = projectsDAO.getProjectFilePaths(projectId);
        
        // 프로젝트 삭제
        boolean deleted = projectsDAO.delete(projectId) > 0;
        
//        if (deleted) {
//            // 프로젝트 관련 파일 삭제
//            deleteProjectFiles(filePaths);
//            logger.info("Project and associated files deleted successfully");
//        } else {
//            logger.warn("Failed to delete project with ID: {}", projectId);
//        }
        return deleted;
    }
    
    /** 
     * 프로젝트 멤버 초대
     */
    public boolean addProjectUser(int userId, int projectId) {
    	if(userId == 0 || projectId == 0) {
    		throw new IllegalArgumentException("User Id and project_ID cannot be 0");	
    	}
    	if(isUserProjectUser(userId, proje))
    }

	@Override
	public List<ProjectFileVO> getFilesByProjectId(int projectId) {
		 return projectsDAO.getProjectFiles(projectId);
	}
	  

    /**
     * 사용자가 프로젝트의 멤버인지 확인합니다.
     * @param userId 확인할 사용자 ID
     * @param projectId 확인할 프로젝트 ID
     * @return 프로젝트 멤버 여부
     */
//    @Override
//    public boolean isUserProjectMember(Integer userId, Integer projectId) {
//        return projectsDAO.isUserProjectMember(userId, projectId);
//    }

    /**
     * 프로젝트의 멤버 목록을 조회합니다.
     * @param projectId 조회할 프로젝트 ID
     * @return 프로젝트 멤버 목록
     */
//    @Override
//    public List<UserVO> getProjectMembers(Integer projectId) {
//        return projectsDAO.getProjectMembers(projectId);
//    }

    /**
     * 프로젝트 관련 파일들을 업로드합니다.
     * @param project 파일을 업로드할 프로젝트
     * @throws IOException 파일 업로드 중 오류 발생 시
     */
//    private void uploadProjectFiles(ProjectVO project) throws IOException {
//        if (project.getFiles() != null && !project.getFiles().isEmpty()) {
//            for (MultipartFile file : project.getFiles()) {
//                String filePath = fileService.saveFile(file);
//                projectsDAO.insertFile(project.getProjectId(), file.getOriginalFilename(), filePath, file.getSize());
//                logger.debug("File uploaded: {}", filePath);
//            }
//        }
//    }

	  @Transactional
	    public void createProjectWithFiles(ProjectVO project, List<MultipartFile> files) throws IOException {
	        

	        
	        projectsDAO.insert(project);
	        
	        System.out.println("projectserviceimpl" + project.getProjectId());
	        
	        uploadProjectFiles(project.getProjectId(), files);
	        
	    }

	    private void uploadProjectFiles(Integer projectId, List<MultipartFile> files) throws IOException {
	        if (files != null && !files.isEmpty()) {
	            for (MultipartFile file : files) {
	                String filePath = fileService.saveFile(file);
	                projectsDAO.insertFile(projectId, file.getOriginalFilename(), filePath, file.getSize());

	            }
	        }
	    }

		@Override
		public void addFileToProject(ProjectFileVO projectFileVO) {
			// TODO Auto-generated method stub
			
		}


	
    /**
     * 프로젝트 관련 파일들을 삭제합니다.
     * @param filePaths 삭제할 파일 경로 목록
     */
//    private void deleteProjectFiles(List<String> filePaths) {
//        for (String filePath : filePaths) {
//            try {
//                fileService.deleteFile(filePath);
//                logger.debug("File deleted: {}", filePath);
//            } catch (IOException e) {
//                logger.error("Failed to delete file: {}", filePath, e);
//            }
//        }
//    }


}