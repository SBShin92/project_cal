package com.github.sbshin92.project_cal.controller;

import java.io.IOException;
import java.security.Principal;

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
import com.github.sbshin92.project_cal.service.TaskService;
import com.github.sbshin92.project_cal.service.UserService;

import jakarta.validation.Valid;


//
///**
// * 프로젝트 관리 기능을 처리하는 컨트롤러
// * 프로젝트 조회, 생성, 수정, 삭제 기능을 제공
// */
@Controller
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

//    @Autowired
//    private UserService userService;

//    @Autowired
//    private TaskService taskService;

    /**
     * 프로젝트 상세 정보를 조회하거나 프로젝트 목록으로 리다이렉트
     * @param projectId 조회할 프로젝트의 ID (선택적)
     * @param model 뷰에 전달할 데이터를 담는 모델 객체
     * @return 프로젝트 상세 페이지 뷰 이름 또는 리다이렉트 URL
     */
    @GetMapping("/{projectId}")
    public String getProject(@PathVariable(required = false) Integer projectId, Model model) {
        try {
            // 프로젝트 정보 조회
            ProjectVO projectVO = projectService.getProjectById(projectId);
            if (projectVO == null) {
                throw new Exception("Project not found");
            }
            
            // 프로젝트 생성자 여부와 멤버 여부 확인
//           boolean isProjectCreator = project.getUserId().equals(currentUser.getUserId());
//            boolean isProjectMember = projectService.isUserProjectMember(currentUser.getUserId(), projectId);
            
            // 모델에 데이터 추가
            model.addAttribute("projectVO", projectVO);
//            Object isProjectCreator = new Object();
//			model.addAttribute("isProjectCreator", isProjectCreator);
//            model.addAttribute("isProjectMember", isProjectMember);
//            model.addAttribute("projectMembers", projectService.getProjectMembers(projectId));
//            model.addAttribute("projectTasks", taskService.getTasksByProjectId(projectId));
            
            return "project/detail";
        } catch (Exception e) {
            // 예외 발생 시 에러 페이지로 이동
            model.addAttribute("errorMessage", "프로젝트를 찾을 수 없습니다.");
            return "error/404";
        }
    }


//    /**
//     * 새 프로젝트 생성 폼을 표시
//     * @param model 뷰에 전달할 데이터를 담는 모델 객체
//     * @param principal 현재 로그인한 사용자 정보
//     * @return 프로젝트 생성 폼 페이지 뷰 이름 또는 접근 거부 페이지
//     */
    @GetMapping("/create")
    public String createProjectForm() {
////        // 현재 사용자의 프로젝트 생성 권한 확인
////        UserVO currentUser = userService.getUserByUsername(principal.getName());
////        if (!currentUser.isCanCreateProject() && !"admin".equals(currentUser.getUserAuthority())) {
////            return "error/403"; // 권한 없음 페이지로 리다이렉트
////        }
//        model.addAttribute("projectVO", new ProjectVO());
        return "project/form";
    }

//    /**
//     * 새 프로젝트를 생성
//     * @param project 생성할 프로젝트 정보
//     * @param result 유효성 검사 결과
//     * @param redirectAttributes 리다이렉트 시 전달할 속성
//     * @param principal 현재 로그인한 사용자 정보
//     * @return 리다이렉트 URL 또는 폼 페이지 뷰 이름
//     */
    @PostMapping("/create")
    public String createProject(@ModelAttribute ProjectVO projectVO, 
                                RedirectAttributes redirectAttributes) {
    	System.out.println("is here??");
//        // 사용자의 프로젝트 생성 권한 확인
////        UserVO currentUser = userService.getUserByUsername(principal.getName());
////        if (!currentUser.isCanCreateProject() && !"admin".equals(currentUser.getUserAuthority())) {
////            redirectAttributes.addFlashAttribute("error", "프로젝트 생성 권한이 없습니다.");
////            return "redirect:/project/list";
////        }
//
//        // 프로젝트 생성자 설정
//       project.setUserId(currentUser.getUserId());
//        
        try {
            // 프로젝트 생성 (파일 업로드 포함)
            projectService.createProject(projectVO);
        	System.out.println("project " + projectVO.getProjectId());
            redirectAttributes.addFlashAttribute("message", "프로젝트가 성공적으로 생성되었습니다.");
            System.out.println(" isGood?");
        } catch (IOException e) {
            // 파일 업로드 실패 시 에러 메시지 설정
            redirectAttributes.addFlashAttribute("error", "파일 업로드 중 오류가 발생했습니다.");
            System.out.println(" fail");
            return "project/form";
        }
        System.out.println(" last");
        
        return "redirect:/calendar";
       
    }
//
//    /**
//     * 프로젝트 수정 폼을 표시
//     * @param projectId 수정할 프로젝트의 ID
//     * @param model 뷰에 전달할 데이터를 담는 모델 객체
//     * @return 프로젝트 수정 폼 페이지 뷰 이름 또는 에러 페이지
//     */
//    @GetMapping("/update/{projectId}")
//    public String updateProjectForm(@PathVariable int projectId, Model model) {
//        try {
//            // 프로젝트 정보 조회 및 모델에 추가
//            model.addAttribute("project", projectService.getProjectById(projectId));
//            return "project/form";
//        } catch (Exception e) {
//            // 프로젝트를 찾을 수 없는 경우 에러 페이지로 이동
//            model.addAttribute("errorMessage", "프로젝트를 찾을 수 없습니다.");
//            return "error/404";
//        }
//    }
//
//    /**
//     * 프로젝트를 수정
//     * @param projectId 수정할 프로젝트의 ID
//     * @param project 수정할 프로젝트 정보
//     * @param result 유효성 검사 결과
//     * @param redirectAttributes 리다이렉트 시 전달할 속성
//     * @return 리다이렉트 URL 또는 폼 페이지 뷰 이름
//     */
//    @PostMapping("/update/{projectId}")
//    public String updateProject(@PathVariable int projectId, @Valid @ModelAttribute ProjectVO project, 
//                                BindingResult result, RedirectAttributes redirectAttributes) {
//    	
//        // 폼 데이터 유효성 검사
//        if (result.hasErrors()) {
//            return "project/form";
//        }
//        // 프로젝트 ID 설정
//        project.setProjectId(projectId);
//        // 프로젝트 업데이트
//        projectService.updateProject(project);
//        redirectAttributes.addFlashAttribute("message", "프로젝트가 성공적으로 수정되었습니다.");
//        return "redirect:/project/" + projectId;
//    }

    /**
     * 프로젝트를 삭제
     * @param projectId 삭제할 프로젝트의 ID
     * @param redirectAttributes 리다이렉트 시 전달할 속성
     * @return 리다이렉트 URL
     */
    @PostMapping("/delete/{projectId}")
    public String deleteProject(@PathVariable int projectId, RedirectAttributes redirectAttributes) {
        try {
            // 프로젝트 삭제
            projectService.deleteProject(projectId);
            redirectAttributes.addFlashAttribute("message", "프로젝트가 성공적으로 삭제되었습니다.");
        } catch (Exception e) {
            // 프로젝트를 찾을 수 없는 경우 에러 메시지 설정
            redirectAttributes.addFlashAttribute("errorMessage", "삭제할 프로젝트를 찾을 수 없습니다.");
        }
        
        return "redirect:/calendar";
    }
}