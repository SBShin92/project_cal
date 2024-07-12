package com.github.sbshin92.project_cal.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.github.sbshin92.project_cal.data.vo.ProjectVO;
import com.github.sbshin92.project_cal.data.vo.UserVO;

@Service
//public class ProjectService {
//    @Autowired
//    private ProjectRepository projectRepository;
//
//    public List<ProjectVO> getAllProjects() {
//        return projectRepository.findAll();
//    }
//
//    public ProjectVO getProjectById(int id) {
//        return projectRepository.findById(id).orElse(null);
//    }
//
//    public ProjectVO saveProject(ProjectVO project) {
//        return projectRepository.save(project);
//    }
//
//    public void deleteProject(int id) {
//        projectRepository.deleteById(id);
//    }
//}

public interface ProjectService {

	 void createProject(ProjectVO project) throws IOException;
	    ProjectVO getProjectById(int projectId);
	    List<ProjectVO> getAllProjects();
	    boolean updateProject(ProjectVO project);
	    boolean deleteProject(int projectId);
	    boolean isUserProjectMember(Integer userId, Integer projectId);
	    List<UserVO> getProjectMembers(Integer projectId);
}