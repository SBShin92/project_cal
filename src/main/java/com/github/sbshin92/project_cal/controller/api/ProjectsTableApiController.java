package com.github.sbshin92.project_cal.controller.api;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.github.sbshin92.project_cal.data.vo.ProjectVO;
import com.github.sbshin92.project_cal.service.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 프로젝트 관련 API를 처리하는 REST 컨트롤러입니다.
 */
@RestController
@RequestMapping("/api/projects")
public class ProjectsTableApiController {
    
    private static final Logger logger = LoggerFactory.getLogger(ProjectsTableApiController.class);

    @Autowired
    private ProjectService projectService;

    /**
     * 모든 프로젝트 목록을 조회합니다.
     * @return 프로젝트 목록
     */
    @GetMapping("")
    public ResponseEntity<List<ProjectVO>> getProjectList() {
        logger.info("Fetching all projects");
        List<ProjectVO> projects = projectService.getAllProjects();
        logger.debug("Found {} projects", projects.size());
        return ResponseEntity.ok(projects);
    }
    
    /**
     * 새 프로젝트를 추가합니다.
     * @param projectVO 추가할 프로젝트 정보
     * @return 생성된 프로젝트 정보
     */
    @PostMapping("")
    public ResponseEntity<ProjectVO> addProject(@RequestBody ProjectVO projectVO) {
        logger.info("Adding new project: {}", projectVO.getProjectTitle());
        try {
            projectService.createProject(projectVO);
            logger.info("Project added successfully with ID: {}", projectVO.getProjectId());
            return ResponseEntity.status(HttpStatus.CREATED).body(projectVO);
        } catch (Exception e) {
            logger.error("Failed to add project", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * 특정 프로젝트를 조회합니다.
     * @param id 조회할 프로젝트 ID
     * @return 조회된 프로젝트 정보
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProjectVO> getProject(@PathVariable int id) {
        logger.info("Fetching project with id: {}", id);
        ProjectVO project = projectService.getProjectById(id);
        if (project != null) {
            return ResponseEntity.ok(project);
        } else {
            logger.warn("Project not found with id: {}", id);
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * 프로젝트를 수정합니다.
     * @param id 수정할 프로젝트 ID
     * @param projectVO 수정할 프로젝트 정보
     * @return 수정된 프로젝트 정보
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProjectVO> updateProject(@PathVariable int id, @RequestBody ProjectVO projectVO) {
        logger.info("Updating project with id: {}", id);
        projectVO.setProjectId(id);
        boolean updated = projectService.updateProject(projectVO);
        if (updated) {
            logger.info("Project updated successfully");
            return ResponseEntity.ok(projectVO);
        } else {
            logger.warn("Failed to update project with id: {}", id);
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * 프로젝트를 삭제합니다.
     * @param id 삭제할 프로젝트 ID
     * @return 삭제 결과
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable int id) {
        logger.info("Deleting project with id: {}", id);
        boolean deleted = projectService.deleteProject(id);
        if (deleted) {
            logger.info("Project deleted successfully");
            return ResponseEntity.noContent().build();
        } else {
            logger.warn("Failed to delete project with id: {}", id);
            return ResponseEntity.notFound().build();
        }
    }
}