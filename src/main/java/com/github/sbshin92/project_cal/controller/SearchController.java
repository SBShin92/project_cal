package com.github.sbshin92.project_cal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.sbshin92.project_cal.data.vo.ProjectVO;
import com.github.sbshin92.project_cal.data.vo.RoleVO;
import com.github.sbshin92.project_cal.data.vo.TaskVO;
import com.github.sbshin92.project_cal.data.vo.UserVO;
import com.github.sbshin92.project_cal.service.ProjectService;
import com.github.sbshin92.project_cal.service.TaskService;

import jakarta.servlet.http.HttpSession;

@Controller
public class SearchController {
	@Autowired
	private ProjectService projectService;
	@Autowired
	private TaskService taskService;

	// 0724
	// 해당 테스크, 프로젝트 타이틀 검색위한 모든 데이터 search
	// tasktitle :검색할 작업의 제목
	// page: 페이지번호 (기본값을 1로 둠)
	@GetMapping("/SearchProjectTasks")
	public String SearchTask(@RequestParam("taskProjectTitle") String taskProjectTitle,
							@RequestParam(defaultValue = "1") int taskPage, 
							@RequestParam(defaultValue = "1") int projectPage,
							Model model,
							HttpSession session) { // attribute 때문에 파라미터를 담기위해 모델선언(박스같은 개념)
		
		// 프로젝트 읽기 권한이 없으면 검색도 안되지
		UserVO authUser = (UserVO)session.getAttribute("authUser");
		RoleVO authUserRole = (RoleVO)session.getAttribute("authUserRole");
		if (!"admin".equals(authUser.getUserAuthority()) && !authUserRole.getProjectRead()) {
			return "redirect:/access-denied";
		}
		// 테스크 조회
		// taskVO 객체 생성-> taskTitle 설정
		TaskVO taskVO = new TaskVO();
		taskVO.setTaskTitle(taskProjectTitle);
		taskVO.setPage(taskPage);
		// 서비스 호출하여 제목으로 작업을 검색
		List<TaskVO> searchedTasks = taskService.searchByTitle(taskVO);
		// 겁색된 작업목록을 attribute통해 모델에 추가
		model.addAttribute("searchedTasks", searchedTasks); // 최종 뷰에 보내기 위한 작업
		model.addAttribute("tasksCount", taskService.getTotalTasksCount(taskVO)); // 리스트 총카운트(작업 총 개수 설정)
		model.addAttribute("totalPages", (searchedTasks.size()));
		//
		// 프로젝트 조회
		// 검색된 프로젝트목록도 attribute통해 모델에 추가
		List<ProjectVO> searchedProjects = projectService.searchedProjects(taskProjectTitle, projectPage);
		model.addAttribute("searchedProjects", searchedProjects);
		model.addAttribute("projectCount", projectService.getTotalProjectsCount(taskProjectTitle)); // 리스트 총카운트(작업 총 개수
																									// 설정)
		model.addAttribute("totalProjectPages", (searchedProjects.size()));
		//
		// 검색시 사용했던 타이틀 재세팅
		// 다시 세팅해줌으로써 taskProjectTitle대로 검색할수 있게 뜨도록 하는것
		model.addAttribute("taskProjectTitle", taskProjectTitle);

		return "search/search"; // 검색 결과를 보여줄 jsp 페이지로 리다이렉트
	}
	
}
