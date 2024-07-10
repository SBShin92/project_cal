package com.github.sbshin92.project_cal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.github.sbshin92.project_cal.data.vo.ProjectVO;
import com.github.sbshin92.project_cal.service.ProjectService;

import jakarta.validation.Valid;

/**
 * 프로젝트 관리 기능을 처리하는 컨트롤러
 * 프로젝트 목록 조회, 상세 조회, 생성, 수정, 삭제 기능을 제공
 */
@Controller
@RequestMapping("/calendar")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

//    /**
//     * 모든 프로젝트 목록을 조회하여 리스트 페이지로 이동
//     * @param model 뷰에 전달할 데이터를 담는 모델 객체
//     * @return 프로젝트 목록 페이지 뷰 이름
//     */
//    @GetMapping("/list")
//    public String listProjects(Model model) {
//        model.addAttribute("projects", projectService.getAllProjects());
//        return "calendar/list";
//    }

    /**
     * 특정 프로젝트의 상세 정보를 조회하여 상세 페이지로 이동
     * @param projectId 조회할 프로젝트의 ID (선택적)
     * @param model 뷰에 전달할 데이터를 담는 모델 객체
     * @return 프로젝트 상세 페이지 뷰 이름 또는 에러 페이지
     */
    @GetMapping({"/detail", "/detail/{projectId}"})
    public String getProject(@PathVariable(required = false) Integer projectId, Model model) {
        try {
            if (projectId == null) {
                // projectId가 제공되지 않았을 때의 처리
                // 예: 가장 최근 프로젝트를 보여주거나, 프로젝트 목록 페이지로 리다이렉트
//                return "redirect:/calendar/detail";
            	 return "calendar/detail";
            	
            }
            
            ProjectVO project = projectService.getProjectById(projectId);
            if (project == null) {
                throw new Exception("Project not found");
            }
            model.addAttribute("project", project);
            return "calendar/detail";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "프로젝트를 찾을 수 없습니다.");
            return "error/404";
        }
    }

    /**
     * 새 프로젝트 생성 폼을 표시
     * @param model 뷰에 전달할 데이터를 담는 모델 객체
     * @return 프로젝트 생성 폼 페이지 뷰 이름
     */
    @GetMapping("/create")
    public String createProjectForm(Model model) {
        model.addAttribute("project", new ProjectVO());
        return "calendar/form";
    }

    /**
     * 새 프로젝트를 생성하고 목록 페이지로 리다이렉트
     * @param project 생성할 프로젝트 정보
     * @param result 유효성 검사 결과
     * @param redirectAttributes 리다이렉트 시 전달할 속성
     * @return 리다이렉트 URL 또는 폼 페이지 뷰 이름
     */
    @PostMapping("/create")
    public String createProject(@Valid @ModelAttribute ProjectVO project, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "calendar/form";
        }
        projectService.createProject(project);
        redirectAttributes.addFlashAttribute("message", "프로젝트가 성공적으로 생성되었습니다.");
        return "redirect:/calendar/list";
    }

    /**
     * 프로젝트 수정 폼을 표시
     * @param projectId 수정할 프로젝트의 ID
     * @param model 뷰에 전달할 데이터를 담는 모델 객체
     * @return 프로젝트 수정 폼 페이지 뷰 이름 또는 에러 페이지
     */
    @GetMapping("/update/{projectId}")
    public String updateProjectForm(@PathVariable int projectId, Model model) {
        try {
            model.addAttribute("project", projectService.getProjectById(projectId));
            return "calendar/form";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "프로젝트를 찾을 수 없습니다.");
            return "error/404";
        }
    }

    /**
     * 프로젝트를 수정하고 상세 페이지로 리다이렉트
     * @param projectId 수정할 프로젝트의 ID
     * @param project 수정할 프로젝트 정보
     * @param result 유효성 검사 결과
     * @param redirectAttributes 리다이렉트 시 전달할 속성
     * @return 리다이렉트 URL 또는 폼 페이지 뷰 이름
     */
    @PostMapping("/update/{projectId}")
    public String updateProject(@PathVariable int projectId, @Valid @ModelAttribute ProjectVO project, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "calendar/form";
        }
        project.setProjectId(projectId);
        projectService.updateProject(project);
        redirectAttributes.addFlashAttribute("message", "프로젝트가 성공적으로 수정되었습니다.");
        return "redirect:/calendar/detail/" + projectId;
    }

    /**
     * 프로젝트를 삭제하고 목록 페이지로 리다이렉트
     * @param projectId 삭제할 프로젝트의 ID
     * @param redirectAttributes 리다이렉트 시 전달할 속성
     * @return 리다이렉트 URL
     */
    @PostMapping("/delete/{projectId}")
    public String deleteProject(@PathVariable int projectId, RedirectAttributes redirectAttributes) {
        try {
            projectService.deleteProject(projectId);
            redirectAttributes.addFlashAttribute("message", "프로젝트가 성공적으로 삭제되었습니다.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "삭제할 프로젝트를 찾을 수 없습니다.");
        }
        return "redirect:/calendar/list";
    }
}