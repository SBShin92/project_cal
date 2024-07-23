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
        List<UserVO> projectMembers = projectService.getProjectMembers(projectId);
        model.addAttribute("allUsers", allUsers);
        model.addAttribute("projectMembers", projectMembers);
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
