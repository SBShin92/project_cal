package com.github.sbshin92.project_cal.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

import com.github.sbshin92.project_cal.data.vo.ProjectFileVO;
import com.github.sbshin92.project_cal.data.vo.ProjectVO;
import com.github.sbshin92.project_cal.data.vo.TaskVO;
import com.github.sbshin92.project_cal.service.FileService;
import com.github.sbshin92.project_cal.service.ProjectService;
import com.github.sbshin92.project_cal.service.TaskService;

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
//    @Autowired
//    private UserService userService;

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
            
            // ProjectController에 추가한 내용 (지원)  
            List<TaskVO> tasks = taskService.getTasksByProjectId(projectVO.getProjectId());
	        model.addAttribute("projectTasks", tasks);
	        
	       
            return "project/detail";
            
    	} catch (Exception e) {
        // 예외 발생 시 에러 페이지로 이동
        model.addAttribute("errorMessage", "프로젝트를 찾을 수 없습니다.");
        return "error/404";
    	}
        
    }

    
    // 첨부파일 관련 메서드들
    @GetMapping("/upload")
    public String showUploadForm() {
        return "project/uploadForm";
    }

        
    /*
        @GetMapping("/createTaskForm")
        public String createTaskForm(@PathVariable int projectId,
                                     Model model) {
            TaskVO taskVo = new TaskVO();
            taskVo.setProjectId(projectId);
            model.addAttribute("task", taskVo);
            //return "task/form";
            return "project/detail";
        }
            
        @PostMapping("/createTask")
	    public String createTask(@RequestParam("userId") int userId, 
	    		                 @RequestParam("projectId") int projectId, 
	    		                 @RequestParam("taskTitle") String taskTitle, 
	    		                 @RequestParam("taskDescription") String taskDescription) {
	    	TaskVO taskVo = new TaskVO();
	    	taskVo.setUserId(userId);  
	    	taskVo.setProjectId(projectId);
	    	taskVo.setTaskTitle(taskTitle);
	    	taskVo.setTaskDescription(taskDescription);
	    	  
	        taskService.insert(taskVo);
	        return "redirect:/project/" + projectId;  // 페이지를 리다이렉트 매핑된 url을 찾으러가야함
	        
        } 
        지원이 추가한것인데 아직 진행중  
    	*/


    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, 
                                   @RequestParam("projectId") Integer projectId,
                                   RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:/project/" + projectId;
        }

        try {
            String fileName = fileService.saveFile(file);
            // 여기서 프로젝트와 파일을 연결하는 로직을 추가해야 합니다.
            // 예: projectService.addFileToProject(projectId, fileName);

            redirectAttributes.addFlashAttribute("message", "File uploaded successfully: " + fileName);
        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("error", "Failed to upload file: " + e.getMessage());
        }

        return "redirect:/project/" + projectId;
    }

    @GetMapping("/download/{filename:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) {
        try {
            Resource resource = fileService.loadAsResource(filename);
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        } catch (IOException e) {
            return ResponseEntity.notFound().build();
        }
    }


//    @GetMapping("/project/{projectId}")
//    public String getProjectDetail(@PathVariable int projectId, Model model) {
//        ProjectVO project = projectService.getProjectById(projectId);
//        List<ProjectFileVO> projectFiles;
//		try {
//			projectFiles = ProjectsService.getFilesByProjectId(projectId);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
////        boolean isProjectMember = projectService.isUserProjectMember(getCurrentUserId(), projectId);
//
//        model.addAttribute("projectVO", project);
//        model.addAttribute("projectFiles", projectFiles);
////        model.addAttribute("isProjectMember", isProjectMember);
//
//        return "project/detail";
//    }

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
	                            @ModelAttribute ProjectFileVO projectFileVO,
	                            @RequestParam(value = "file", required = false) MultipartFile file,
	                            @RequestParam(value = "files", required = false) List<MultipartFile> files, 
	                            Model model,
	                            RedirectAttributes redirectAttributes) {
	    try {
	        // 프로젝트 생성
	        projectService.createProject(projectVO);

	        // 단일 파일 처리
	        if (file != null && !file.isEmpty()) {
	            String fileName = fileService.saveFile(file);
	            projectFileVO.setFileName(fileName);
	            projectFileVO.setProjectId(projectVO.getProjectId());
	            projectService.addFileToProject(projectFileVO);
	        }

	        // 다중 파일 처리
	        if (files != null && !files.isEmpty()) {
	            for (MultipartFile multipartFile : files) {
	                if (!multipartFile.isEmpty()) {
	                    String fileName = fileService.saveFile(multipartFile);
	                    ProjectFileVO newFileVO = new ProjectFileVO();
	                    newFileVO.setFileName(fileName);
	                    newFileVO.setProjectId(projectVO.getProjectId());
	                    projectService.addFileToProject(newFileVO);
	                }
	            }
	        }

	        redirectAttributes.addFlashAttribute("message", "프로젝트가 성공적으로 생성되었습니다.");
	        return "redirect:/calendar";
	    } catch (IOException e) {
	        model.addAttribute("error", "파일 업로드 중 오류가 발생했습니다: " + e.getMessage());
	        return "project/form";
	    } catch (Exception e) {
	        model.addAttribute("error", "프로젝트 생성 중 오류가 발생했습니다: " + e.getMessage());
	        return "project/form";
	    }
	
		
//		try {
//			// 단일 파일 처리
//			if (file != null && !file.isEmpty()) {
//				String fileName = fileService.saveFile(file);
//				projectFileVO.setFileName(fileName);
//			}
//
//			// 프로젝트 생성 (단일 파일 정보 포함)
//			projectService.createProject(projectVO);
//
//			// 다중 파일 처리
//			if (files != null && !files.isEmpty()) {
//				projectService.createProjectWithFiles(projectVO, files);
//			}
//
//			redirectAttributes.addFlashAttribute("message", "프로젝트가 성공적으로 생성되었습니다.");
//			return "redirect:/calendar";
//		} catch (IOException e) {
//			model.addAttribute("error", "파일 업로드 중 오류가 발생했습니다: " + e.getMessage());
//			return "project/form";
//		} catch (Exception e) {
//			model.addAttribute("error", "프로젝트 생성 중 오류가 발생했습니다: " + e.getMessage());
//			return "project/form";
//		}
	}

//    @PostMapping("/create")
//    public String createProject(@ModelAttribute ProjectVO projectVO, 
//    							@ModelAttribute ProjectFileVO fileVO,
//                                RedirectAttributes redirectAttributes,    
//                                @RequestParam("file") MultipartFile file,
//                                @RequestParam("files") List<MultipartFile> files,
//                                Model model) throws IOException {
//    	System.out.println("is here??");
////        // 사용자의 프로젝트 생성 권한 확인
//////        UserVO currentUser = userService.getUserByUsername(principal.getName());
//////        if (!currentUser.isCanCreateProject() && !"admin".equals(currentUser.getUserAuthority())) {
//////            redirectAttributes.addFlashAttribute("error", "프로젝트 생성 권한이 없습니다.");
//////            return "redirect:/project/list";
//////        }
////
////        // 프로젝트 생성자 설정
////       project.setUserId(currentUser.getUserId());
////        
//        try {
//            // 프로젝트 생성 (파일 업로드 포함)
//            projectService.createProject(projectVO);
//            projectService.createProjectWithFiles(projectVO, files);
//            Object fileService;
//            String fileName = fileService.saveFile(file);
//
//            redirectAttributes.addFlashAttribute("message", "프로젝트가 성공적으로 생성되었습니다.");
//            System.out.println(" isGood?");
//            model.addAttribute("message", "프로젝트 생성 및 파일 업로드 성공");
//            
//        } catch (IOException e) {
//            // 파일 업로드 실패 시 에러 메시지 설정
//            redirectAttributes.addFlashAttribute("error", "파일 업로드 중 오류가 발생했습니다.");
//            model.addAttribute("message", "프로젝트 생성 실패:" + e.getMessage());
//            System.out.println(" fail");
//            return "project/form";
//        }
//        System.out.println(" last");
//        
//        return "redirect:/calendar";
//       
//    }
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