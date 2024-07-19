//package com.github.sbshin92.project_cal.controller;
//
//import java.util.List;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import com.github.sbshin92.project_cal.data.vo.UserVO;
//import com.github.sbshin92.project_cal.service.ProjectService;
//
//@Controller
//@RequestMapping("/inviteMember") // 컨트롤러의 기본 경로를 "/inviteMember"로 설정합니다.
//public class MemberController {
//
//    private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
//
//    @Autowired
//    private ProjectService projectService;
//
//    // 특정 사용자를 조회하여 멤버 초대 페이지를 표시하는 메서드입니다.
//    @GetMapping({"","/"})
//    public String showInviteMemberPage(@PathVariable("userId") int userId, Model model) {
//        logger.info("Entering showInviteMemberPage method with userId: {}", userId);
//        List<UserVO> allUsers = projectService.getAllUsers(userId);
//        logger.info("All users: {}", allUsers);
//        model.addAttribute("allUsers", allUsers);
//        model.addAttribute("userId", userId);
//        return "project/inviteMember";
//    }
//
//    // 프로젝트에 멤버를 추가하는 메서드입니다.
////    @RequestMapping(value = "/projects/{projectId}/members/add", method = RequestMethod.POST)
////    public String addMemberToProject(@PathVariable("projectId") int projectId, @RequestParam("userId") int userId, RedirectAttributes redirectAttributes) {
////        try {
////            if (userId > 0 && projectId > 0) {
////                projectService.addMemberProject(userId, projectId);
////                redirectAttributes.addFlashAttribute("message", "User added to project successfully");
////                logger.info("User {} added to project {}", userId, projectId);
////            } else {
////                redirectAttributes.addFlashAttribute("error", "Invalid userId or projectId");
////                logger.warn("Invalid userId or projectId: userId={}, projectId={}", userId, projectId);
////            }
////        } catch (Exception e) {
////            redirectAttributes.addFlashAttribute("error", "Error adding member to project");
////            logger.error("Error adding member to project", e);
////        }
////        return "redirect:/inviteMember/" + userId;
////    }
//}
package com.github.sbshin92.project_cal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.github.sbshin92.project_cal.data.vo.UserVO;
import com.github.sbshin92.project_cal.service.ProjectService;

@Controller
@RequestMapping("/inviteMember")
public class MemberController {

    @Autowired
    private ProjectService projectService;

    @GetMapping
    public String showInviteMemberPage(@RequestParam(value = "projectId", required = false) Integer projectId, Model model) {
        List<UserVO> allUsers = projectService.getAllUsers();
        model.addAttribute("allUsers", allUsers);
        model.addAttribute("projectId", projectId);
        return "project/inviteMember";
    }

    @PostMapping("/add")
    public String addMemberToProject(@RequestParam("userId") int userId,
                                     @RequestParam("projectId") int projectId,
                                     RedirectAttributes redirectAttributes) {
        try {
            if (projectService.isUserProjectMember(userId, projectId)) {
                redirectAttributes.addFlashAttribute("error", "User is already a member of the project");
            } else {
                projectService.addMemberProject(userId, projectId);
                redirectAttributes.addFlashAttribute("message", "User added to project successfully");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error adding member to project");
        }
        return "redirect:/inviteMember?projectId=" + projectId;
    }

    @PostMapping("/remove")
    public String removeMemberFromProject(@RequestParam("userId") int userId,
                                          @RequestParam("projectId") int projectId,
                                          RedirectAttributes redirectAttributes) {
        try {
            boolean result = projectService.deleteProjectUser(userId, projectId);
            if (result) {
                redirectAttributes.addFlashAttribute("message", "User removed from project successfully");
            } else {
                redirectAttributes.addFlashAttribute("error", "Error removing user from project");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error removing member from project");
        }
        return "redirect:/inviteMember?projectId=" + projectId;
    }
}
