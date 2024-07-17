package com.github.sbshin92.project_cal.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.github.sbshin92.project_cal.data.vo.ProjectVO;
import com.github.sbshin92.project_cal.data.vo.TaskVO;
import com.github.sbshin92.project_cal.service.FileService;
import com.github.sbshin92.project_cal.service.ProjectService;
import com.github.sbshin92.project_cal.service.TaskService;

import jakarta.servlet.http.HttpSession;
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
	@Autowired
	private TaskService taskService;
	@Autowired
	private FileService fileService;

    @GetMapping("/{projectId}")
    public String getProject(@PathVariable(required = false) Integer projectId, Model model) {
        try {
            // 프로젝트 정보 조회
            ProjectVO projectVO = projectService.getProjectById(projectId);
            if (projectVO == null) {
                throw new Exception("Project not found");
            }
            // project 상세정보 뷰에 추가
            model.addAttribute("projectVO", projectVO);
			
			// TODO: 프로젝트 멤버 추가 후 상세페이지 뷰에 추가
            
            // 프로젝트의 TASK리스트 뷰에 추가
            List<TaskVO> tasks = taskService.getTasksByProjectId(projectVO.getProjectId());
	        model.addAttribute("projectTasks", tasks);
            return "project/detail";
    	} catch (Exception e) {
        model.addAttribute("errorMessage", "프로젝트를 찾을 수 없습니다.");
        return "error/404";
    	}
    }


	@GetMapping("/create")
	public String createProjectForm() {
//        // TODO: 현재 사용자의 프로젝트 생성 권한 확인 (인터셉트)
//        UserVO currentUser = userService.getUserByUsername(principal.getName());
//        if (!currentUser.isCanCreateProject() && !"admin".equals(currentUser.getUserAuthority())) {
//            return "error/403"; // 권한 없음 페이지로 리다이렉트
//        }
//        model.addAttribute("projectVO", new ProjectVO());
		return "project/form";
	}


    @PostMapping("/create")
    public String createProject(@ModelAttribute ProjectVO projectVO,
                                @RequestParam("projectFiles") MultipartFile[] files,
                                RedirectAttributes redirectAttributes,
                                HttpSession session) throws IOException {
//        // TODO: 사용자의 프로젝트 생성 권한 확인 (인터셉트)
////        UserVO currentUser = userService.getUserByUsername(principal.getName());
////        if (!currentUser.isCanCreateProject() && !"admin".equals(currentUser.getUserAuthority())) {
////            redirectAttributes.addFlashAttribute("error", "프로젝트 생성 권한이 없습니다.");
////            return "redirect:/project/list";
////        }
    	
        try {
            // 프로젝트 생성
        	projectService.createProject(projectVO);
        	// TODO: 파일 업로드
        	if (files != null)
        		fileService.saveFilesInProject(files, projectVO.getProjectId());

            redirectAttributes.addFlashAttribute("message", "프로젝트가 성공적으로 생성되었습니다.");
        } catch (IOException e) {
            // 파일 업로드 실패 시 에러 메시지 설정
            redirectAttributes.addFlashAttribute("error", "파일 업로드 중 오류가 발생했습니다.");
            return "project/form";
        }
        return "redirect:/calendar";
    }
    

//
//    /**
//     * 프로젝트 수정 폼을 표시
//     * @param projectId 수정할 프로젝트의 ID
//     * @param model 뷰에 전달할 데이터를 담는 모델 객체
//     * @return 프로젝트 수정 폼 페이지 뷰 이름 또는 에러 페이지
//     */
	@GetMapping("/update/{projectId}")
	public String updateProjectForm(@PathVariable int projectId, Model model) {
		try {
			// 프로젝트 정보 조회 및 모델에 추가
			model.addAttribute("project", projectService.getProjectById(projectId));
			return "project/form";
		} catch (Exception e) {
			// 프로젝트를 찾을 수 없는 경우 에러 페이지로 이동
			model.addAttribute("errorMessage", "프로젝트를 찾을 수 없습니다.");
			return "error/404";
		}
	}

//
//    /**
//     * 프로젝트를 수정
//     * @param projectId 수정할 프로젝트의 ID
//     * @param project 수정할 프로젝트 정보
//     * @param result 유효성 검사 결과
//     * @param redirectAttributes 리다이렉트 시 전달할 속성
//     * @return 리다이렉트 URL 또는 폼 페이지 뷰 이름
//     */
	@PostMapping("update/{projectId}")
	public String updateProject(@PathVariable int projectId, @Valid @ModelAttribute ProjectVO project,
			BindingResult result, RedirectAttributes redirectAttributes) {
		// 폼 데이터 유효성 검사
		if (result.hasErrors()) {
			return "project/form";
		}
		// 프로젝트 ID 설정
		project.setProjectId(projectId);
		// 프로젝트 업데이트
		boolean updated = projectService.updateProject(project);
		if (updated) {
			redirectAttributes.addFlashAttribute("message", "프로젝트가 성공적으로 수정되었습니다.");
		} else {
			redirectAttributes.addFlashAttribute("error", "프로젝트 수정에 실패했습니다.");
		}
		return "redirect:/project/" + projectId;
	}

	/**
	 * 프로젝트를 삭제
	 * 
	 * @param projectId          삭제할 프로젝트의 ID
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