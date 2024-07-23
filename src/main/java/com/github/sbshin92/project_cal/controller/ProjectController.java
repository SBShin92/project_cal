package com.github.sbshin92.project_cal.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

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

import com.github.sbshin92.project_cal.data.vo.FileVO;
import com.github.sbshin92.project_cal.data.vo.ProjectVO;
import com.github.sbshin92.project_cal.data.vo.TaskVO;
import com.github.sbshin92.project_cal.data.vo.UserVO;
import com.github.sbshin92.project_cal.service.FileService;
import com.github.sbshin92.project_cal.service.ProjectService;
import com.github.sbshin92.project_cal.service.TaskService;
import com.github.sbshin92.project_cal.service.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private FileService fileService;

    @Autowired
    private UserService userService;

    @GetMapping("/{projectId}")
    public String getProject(@PathVariable(required = false) Integer projectId, Model model) {
        try {
            ProjectVO projectVO = projectService.getProjectById(projectId);
            if (projectVO == null) {
                throw new Exception("Project not found");
            }
            model.addAttribute("projectVO", projectVO);
            List<FileVO> fileVOs = fileService.getFileListByProjectId(projectId);
            model.addAttribute("fileVOs", fileVOs);
            List<TaskVO> tasks = taskService.getTasksByProjectId(projectVO.getProjectId());
            model.addAttribute("projectTasks", tasks);
            List<UserVO> projectMembers = projectService.getProjectMembers(projectId);
            model.addAttribute("projectMembers",projectMembers);
            List<UserVO> allUsers = projectService.getAllUsers();
            // 추가 가능한 멤버 조회
            List<UserVO> availableUsers = allUsers.stream().filter(user -> !projectService.isUserProjectMember(user.getUserId(),projectId))
            												.collect(Collectors.toList());
            model.addAttribute("availableUsers",availableUsers);
           
            return "project/detail";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "프로젝트를 찾을 수 없습니다.");
            return "error/404";
        }
    }

    @GetMapping("/create")
    public String createProjectForm(Model model) {
        List<UserVO> allUsers = userService.getAllUsers(); // 사용자 목록을 가져와서 뷰에 추가
        model.addAttribute("allUsers", allUsers);
        model.addAttribute("projectVO", new ProjectVO()); // 빈 ProjectVO 객체 추가
        return "project/form";
    }

    @PostMapping("/create")
    public String createProject(@RequestParam("userId") String userIdStr,
                                @ModelAttribute ProjectVO projectVO,
                                @RequestParam("projectFiles") MultipartFile[] files,
                                @RequestParam("members") List<Integer> members,
                                RedirectAttributes redirectAttributes,
                                HttpSession session) throws IOException {
        try {
            // userId 처리
            int userId = 0;
            if (userIdStr != null && !userIdStr.trim().isEmpty()) {
                userId = Integer.parseInt(userIdStr);
            }
            projectVO.setUserId(userId);

            // 프로젝트 생성
            projectService.createProject(projectVO);
            int projectId = projectVO.getProjectId(); // 생성된 프로젝트 ID 가져오기

            // 멤버 추가
            for (Integer memberId : members) {
                projectService.addMemberProject(memberId, projectId);
            }

            // 파일 업로드
            fileService.saveFilesInProject(files, projectId);
            redirectAttributes.addFlashAttribute("message", "프로젝트가 성공적으로 생성되었습니다.");
            return "redirect:/project/" + projectId;
        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("error", "파일 업로드 중 오류가 발생했습니다.");
            return "project/form";
        } catch (NumberFormatException e) {
            redirectAttributes.addFlashAttribute("error", "유효하지 않은 사용자 ID입니다.");
            return "project/form";
        }
    }

    @GetMapping("/update/{projectId}")
    public String updateProjectForm(@PathVariable int projectId, Model model) {
        try {
            model.addAttribute("project", projectService.getProjectById(projectId));
            return "project/form";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "프로젝트를 찾을 수 없습니다.");
            return "error/404";
        }
    }

    // 프로젝트 수정
    @PostMapping("update/{projectId}")
    public String updateProject(@PathVariable int projectId, @Valid @ModelAttribute ProjectVO project,
                                BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "project/form";
        }
        project.setProjectId(projectId);
        boolean updated = projectService.updateProject(project);
        if (updated) {
            redirectAttributes.addFlashAttribute("message", "프로젝트가 성공적으로 수정되었습니다.");
        } else {
            redirectAttributes.addFlashAttribute("error", "프로젝트 수정에 실패했습니다.");
        }
        return "redirect:/project/" + projectId;
    }

    // 프로젝트 삭제
    @PostMapping("/delete/{projectId}")
    public String deleteProject(@PathVariable int projectId, RedirectAttributes redirectAttributes) {
        try {
            projectService.deleteProject(projectId);
            redirectAttributes.addFlashAttribute("message", "프로젝트가 성공적으로 삭제되었습니다.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "삭제할 프로젝트를 찾을 수 없습니다.");
        }
        return "redirect:/calendar";
    }


    // 멤버 추가
    @PostMapping("/inviteMember")
    public String inviteMember(@RequestParam("projectId") int projectId, @RequestParam("userId") int userId,
    																	RedirectAttributes redirectAttributes){
    	
   try {
	   if(!projectService.isUserProjectMember(userId,projectId)) {
		   boolean success = projectService.addMemberProject(userId, projectId);
		   
		 if(success) {
			 redirectAttributes.addFlashAttribute("message","멤버 추가 성공했어용");
		 } else {
			 redirectAttributes.addFlashAttribute("message","멤버 추가 실패했어용");
		 }
	   } else {
		   redirectAttributes.addFlashAttribute("error","찾을수없어요");
	   }
	   	return "redirect:/project/" + projectId;
   	} catch(Exception e) {
	   redirectAttributes.addFlashAttribute("error","멤버 추가중 오류발생");
	   return "redirect:/project/" + projectId;
   	 }
  }

    // 멤버 삭제
    @PostMapping("/removeMember")
    public String removeMember(@RequestParam("projectId") int projectId, @RequestParam("userId") int userId,
    							RedirectAttributes redirectAttributes) {  
 
    try {
    	if( projectService.isUserProjectMember(userId, projectId)) {
    		boolean success = projectService.deleteProjectUser(userId, projectId);
    	if(success) {
    		redirectAttributes.addFlashAttribute("message","멤버 삭제 성공");
    		
    	} else {
    		redirectAttributes.addFlashAttribute("error","멤버 삭제 실패");	
    	}
    	
    	} else {
    		redirectAttributes.addFlashAttribute("error","존재하지 않거나 프로젝트멤버가 아닙니다");
    	}
        
    } catch(Exception e) {
    		redirectAttributes.addFlashAttribute("error","멤버 삭제중 에러 발생");
    }
    return "redirect:/project/" + projectId;
}
}
