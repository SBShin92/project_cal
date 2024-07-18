package com.github.sbshin92.project_cal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.github.sbshin92.project_cal.data.vo.UserVO;
import com.github.sbshin92.project_cal.service.ProjectService;

@Controller
public class MemberController {
  
   @Autowired
    private ProjectService projectService;
  
   @GetMapping("/inviteMember")
    public String member(Model model) {
	   int projectId = 1;
	return"project/inviteMember";
	


}
    /**
     * 프로젝트 멤버 조회
     * @param projectId 조회할 프로젝트의 ID
     * @param model 뷰에 전달할 모델
     * @return 멤버 조회 뷰 이름
     */
   // 멤버 조회
   @GetMapping("/users/{userId}projectMembers")
   public String projectUser(@PathVariable int projectId, Model model) {
	   List<UserVO> members = projectService.getProjectMembers(projectId);
	   model.addAttribute("members",members);
	   return "project/inviteMember";
   }
   // 멤버 추가
//    @PostMapping("/users/{projectId}/projectMembers")
//    public String addMember(@PathVariable int userId,@RequestParam int projectId,
//    	@RequestParam int addUserId,
//    	@RequestParam int projectsId, HttpSession session) {
//    	
//    	try {
//    		ProjectVO pvo = (ProjectVO)session.getAttribute("AuthUser");
//    		
//    		if(userId == pvo.getUserId()) {
//    			projectService.addMemberProject(addUserId, projectId);
//    			return "redirect:/project/inviteMember";
//    		}else {
//        		return "redirect:/project/inviteMember";
//    	} 
//    	} catch(Exception e) {
//    		e.printStackTrace();
//    	}
//    	return "redirect:/project/inviteMember";
//    }

}
