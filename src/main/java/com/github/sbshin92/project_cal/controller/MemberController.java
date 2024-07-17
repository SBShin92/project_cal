package com.github.sbshin92.project_cal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.sbshin92.project_cal.data.vo.ProjectVO;
import com.github.sbshin92.project_cal.data.vo.UserVO;
import com.github.sbshin92.project_cal.service.ProjectService;
import com.github.sbshin92.project_cal.service.UserService;

@Controller
public class MemberController {

  
    
   @Autowired
    private ProjectService projectService;
   @Autowired
   	private UserService userService;
   
   @GetMapping("/inviteMember")
    public String member() {
	return"project/inviteMember";
}
    /**
     * 프로젝트 멤버 조회
     * @param projectId 조회할 프로젝트의 ID
     * @param model 뷰에 전달할 모델
     * @return 멤버 조회 뷰 이름
     */
//    @GetMapping("/projects/{projectId}/members")
//    public String viewProjectMembers(@PathVariable int projectId, Model model) {
//        ProjectVO projectVo = projectService.getProjectById(projectId);
//        
//        List<UserVO> uvo = projectService.getNonProjectMembers(projectId);
//        model.addAttribute("users",uvo);
//        
//        return"project/inviteMember";
//    }
   @GetMapping("/projects/{projectId}/members")
   public String viewProjectMembers(@PathVariable int projectId, Model model) {
       // 프로젝트 ID 유효성 검사
       ProjectVO projectVo = projectService.getProjectById(projectId);
       if (projectVo == null) {
           model.addAttribute("error", "Invalid project ID");
           return "error"; // 에러 페이지로 리다이렉트
       }
       
       // 프로젝트에 속하지 않은 멤버 목록 조회
       List<UserVO> nonMembers = projectService.getNonProjectMembers(projectId);
       model.addAttribute("users", nonMembers);
       model.addAttribute("project", projectVo);

       return "project/inviteMember"; // 뷰 이름 반환
   }

//    /**
//     * 프로젝트에 멤버 추가
//     * @param projectId 추가할 프로젝트의 ID
//     * @param userId 추가할 사용자의 ID
//     * @return 리디렉션 URL
//     */
    @PostMapping("/projects/{projectId}/members/add")
    public String addProjectMember(@PathVariable int projectId, @RequestParam int userId) {
        try {
        	projectService.addProjectUser(userId, projectId);
        } catch(Exception e) {
        	e.printStackTrace();
        }
        return"redirect:/project/inviteMember" + String.valueOf(projectId);
    }
//
//    /**
//     * 프로젝트에서 멤버 삭제
//     * @param projectId 삭제할 프로젝트의 ID
//     * @param userId 삭제할 사용자의 ID
//     * @return 리디렉션 URL
//     */
//    @PostMapping("/projects/{projectId}/members/delete")
//    public String deleteProjectMember(@PathVariable int projectId, @RequestParam int userId) {
//        projectService.deleteProjectMember(userId, projectId);
//        return "redirect:/projects/" + projectId + "/members";
//    }
}
