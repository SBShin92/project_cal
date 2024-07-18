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
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import com.github.sbshin92.project_cal.data.vo.ProjectVO;
//import com.github.sbshin92.project_cal.data.vo.UserVO;
//import com.github.sbshin92.project_cal.service.ProjectService;
//
//import jakarta.servlet.http.HttpSession;
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
//    // 모든 사용자를 조회하여 멤버 초대 페이지를 표시하는 메서드입니다.
//    @GetMapping({"","/"})
//    public String showInviteMemberPage(Model model) {
//        logger.info("Entering showInviteMemberPage method");
//        List<UserVO> users = projectService.getAllUsers(); // 모든 사용자 조회
//        logger.info("All users: {}", users);
//        if (users == null || users.isEmpty()) {
//            logger.warn("No users found or users list is null");
//        } else {
//            logger.info("Number of users found: {}", users.size());
//        }
//        model.addAttribute("users", users); // 조회된 사용자 목록을 모델에 추가
//        return "project/inviteMember"; // inviteMember 페이지로 이동
//    }
//
////    // 특정 프로젝트의 멤버와 모든 사용자를 조회하여 초대 페이지를 표시하는 메서드입니다.
//    @GetMapping("/{projectId}")
//    public String viewProjectMembers(@PathVariable int projectId, Model model) {
//        ProjectVO project = projectService.getProjectById(projectId); // 프로젝트 ID로 프로젝트 조회
//        List<UserVO> members = projectService.getProjectMembers(projectId); // 프로젝트 ID로 멤버 조회
//        List<UserVO> users = projectService.getAllUsers(); // 모든 사용자 조회
//
//        logger.info("Project: {}", project);
//        model.addAttribute("project", project); // 조회된 프로젝트 정보를 모델에 추가
//
//        if (members != null && !members.isEmpty()) {
//            logger.info("Members: {}", members);
//            model.addAttribute("members", members); // 조회된 프로젝트 멤버 목록을 모델에 추가
//        } else {
//            logger.warn("No members found for project ID: {}", projectId);
//            model.addAttribute("members", List.of()); // 멤버가 없을 경우 빈 리스트를 모델에 추가
//        }
//
//        logger.info("All users: {}", users);
//        model.addAttribute("users", users); // 조회된 모든 사용자 목록을 모델에 추가
//
//        return "project/inviteMember"; // inviteMember 페이지로 이동
//    }
//
//    // 프로젝트에 멤버를 추가하는 메서드입니다.
//    @PostMapping("/projects/{projectId}/members/add")
//    public String addMemberToProject(@RequestParam int userId, @RequestParam int projectId, HttpSession session) {
//        try {
//            UserVO authUser = (UserVO) session.getAttribute("authUser"); // 세션에서 인증된 사용자 정보를 가져옵니다.
//            if (authUser != null) {
//                projectService.addMemberProject(userId, projectId); // 프로젝트에 멤버를 추가합니다.
//                logger.info("User {} added to project {}", userId, projectId);
//            } else {
//                logger.warn("No authenticated user found in session");
//            }
//        } catch (Exception e) {
//            logger.error("Error adding member to project", e);
//        }
//        return "redirect:/project/inviteMember/"; // 멤버 추가 후 해당 프로젝트 초대 페이지로 리디렉션
//    }
//}

package com.github.sbshin92.project_cal.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.github.sbshin92.project_cal.data.vo.ProjectVO;
import com.github.sbshin92.project_cal.data.vo.UserVO;
import com.github.sbshin92.project_cal.service.ProjectService;

@Controller
@RequestMapping("/inviteMember") // 컨트롤러의 기본 경로를 "/inviteMember"로 설정합니다.
public class MemberController {

    private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

    @Autowired
    private ProjectService projectService;

    // 모든 사용자를 조회하여 멤버 초대 페이지를 표시하는 메서드입니다.
    @GetMapping({"","/"})
    public String showInviteMemberPage(Model model) {
        logger.info("Entering showInviteMemberPage method");
        List<ProjectVO> users = projectService.getAllProjects(); // 모든 사용자 조회
        logger.info("All users: {}", users);
        if (users == null || users.isEmpty()) {
            logger.warn("No users found or users list is null");
        } else {
            logger.info("Number of users found: {}", users.size());
        }
        model.addAttribute("users", users); // 조회된 사용자 목록을 모델에 추가
        return "project/inviteMember"; // inviteMember 페이지로 이동
    }

    // 특정 프로젝트의 멤버와 모든 사용자를 조회하여 초대 페이지를 표시하는 메서드입니다.
    @GetMapping("/{projectId}")
    public String viewProjectMembers(@PathVariable int projectId, Model model) {
        ProjectVO project = projectService.getProjectById(projectId); // 프로젝트 ID로 프로젝트 조회
        List<UserVO> members = projectService.getProjectMembers(projectId); // 프로젝트 ID로 멤버 조회
        List<ProjectVO> users = projectService.getAllProjects(); // 모든 사용자 조회

        logger.info("Project: {}", project);
        model.addAttribute("project", project); // 조회된 프로젝트 정보를 모델에 추가

        if (members != null && !members.isEmpty()) {
            logger.info("Members: {}", members);
            model.addAttribute("members", members); // 조회된 프로젝트 멤버 목록을 모델에 추가
        } else {
            logger.warn("No members found for project ID: {}", projectId);
            model.addAttribute("members", List.of()); // 멤버가 없을 경우 빈 리스트를 모델에 추가
        }

        logger.info("All users: {}", users);
        model.addAttribute("users", users); // 조회된 모든 사용자 목록을 모델에 추가

        return "project/inviteMember"; // inviteMember 페이지로 이동
    }

    // 프로젝트에 멤버를 추가하는 메서드입니다.
    @PostMapping("/inviteMember/projects/{userId}/members/add")
    public String addMemberToProject(@PathVariable int projectId, @RequestParam("userId") int userId, RedirectAttributes redirectAttributes) {
        try {
            if (userId > 0 && projectId > 0) {
                projectService.addMemberProject(userId, projectId);
                redirectAttributes.addFlashAttribute("message", "User added to project successfully");
            } else {
                redirectAttributes.addFlashAttribute("error", "Invalid userId or projectId");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error adding member to project");
        }
        return "redirect:/projects";
    }
}
