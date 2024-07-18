package com.github.sbshin92.project_cal.controller;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.sbshin92.project_cal.data.vo.TaskVO;
import com.github.sbshin92.project_cal.data.vo.UserVO;
import com.github.sbshin92.project_cal.data.vo.UsersTasksVO;
import com.github.sbshin92.project_cal.service.ProjectService;
import com.github.sbshin92.project_cal.service.TaskService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/tasks") // 이거중요
public class TaskController {

	@Autowired
	private TaskService taskService;

	@Autowired
	private ProjectService projectService;

	// 테스크 생성 및 수정을 위해
	// 이 폼에서 같이 사용
	@GetMapping("/createTaskForm") // 주의 수정과 생성을 같이 씀
	public String createTaskForm(@RequestParam(defaultValue = "0") int taskId,
								// taskId에 널이 넘어온다면, int형 타입에는 널이 들어갈수 없으므로
								// 기본값으로 0으로 바꿔준다(defaultValue= "0" =>null상태)
								@RequestParam(defaultValue = "0") int userId, 
								@RequestParam(defaultValue = "0") int projectId,
								@RequestParam(required = false) String taskTitle, 
								@RequestParam(required = false) String taskDescription,
								@RequestParam(required = false) Timestamp createdAt,
								@RequestParam(required = false) Timestamp updatedAt,
								@RequestParam(required = false) String taskStatus,
								@RequestParam(defaultValue = "0") int taskPriority,
								
								HttpSession session, Model model) {

		// 추가 0716 //사용자 아이디를 가져오는 메서드 필요
		UserVO userVO = (UserVO) session.getAttribute("authUser");

		TaskVO taskVo = new TaskVO();

		// 1. taskId가 0이면 생성을 하는것으로 한다
		// - taskId가 0이면 userId를 auth에서 가져와야 한다 .
		// - taskVo.setUserId(auth.userId);

		// 2. taskId가 0이 아니면 수정을 하는것으로 한다
		// - taskVo.setUserId(auth.userId);
		// - 추후에 테스크수정은 테스크생성자만 할수 있으므로
		// - 마찬가지로 authuser로 사용 해야한다.

		// 추가 0716 if-else 문??
		if (taskId == 0) {
			// 생성로직
			taskVo.setUserId(userVO.getUserId());
			taskVo.setTaskId(taskId);
			taskVo.setProjectId(projectId);
			model.addAttribute("createTaskForm", taskVo);

		} else {
			// 수정로직
			TaskVO existingTask = taskService.findById(taskId);
			if (existingTask != null && existingTask.getUserId() == userId) {
				// 현재 사용자가 테스크 생성자인 경우에만 수정 허용
				taskVo = existingTask;
				model.addAttribute("createTaskForm", taskVo);
			} else {
				// 권한이 없는 경우 에러 처리
				model.addAttribute("error", "이 태스크를 수정할 권한이 없습니다.");
				return "error/404";
			}
		}

		// 모델 어트리뷰트에 담고 보내주기
		// model.addAttribute("createTaskForm", taskVo);
		return "task/form";
	}

	// 테스크 생성
	@PostMapping("/createTask")
	public String createTask(@ModelAttribute TaskVO taskVo, HttpSession session) {
		// 추가 0716 //사용자 아이디를 가져오는 메서드 필요
		UserVO userVO = (UserVO) session.getAttribute("authUser");
		taskVo.setUserId(userVO.getUserId());
		taskService.insert(taskVo);
		return "redirect:/project/" + taskVo.getProjectId(); // 페이지를 리다이렉트 매핑된 url을 찾으러가야함
	}

	// 테스크 조회
	@GetMapping("/listTasks")
	public String listTasks(Model model) { //attribute 때문에 파라미터를 담기위해 모델선언(박스같은 개념)
		List<TaskVO> tasks = taskService.findAll();
		model.addAttribute("listTasks", tasks); // 최종 뷰에 보내기 위한 작업(여기선 list.jsp)위해 모델 안의.attribute에 담는작업
		return "search/search";

	}

	// 테스크 삭제
	@PostMapping("/deleteTask/{taskId}")
	public String deleteTask(@PathVariable int taskId, @RequestParam int projectId, @RequestParam int userId,
			HttpSession httpsession) {

		// 로그인한 사용자가 이 task를 만든 사용자인지 체크 위함
		// 1) 테스크를 생성한 사용자의 아이디 필요
		// userId
		// 2) 로그인한 사용자의 아이디 필요
		UserVO userVO = (UserVO) httpsession.getAttribute("authUser");

		if (userId == userVO.getUserId()) {
			taskService.deleteTask(taskId);
			return "redirect:/project/" + projectId;

		} else {
			return "redirect:/project/" + projectId;
		}

	}

	// 테스크 수정
	@PostMapping("/updateTask/{taskId}")
	public String updateTask(@PathVariable int taskId, @ModelAttribute TaskVO taskVO, HttpSession session) {

		// 로그인한 사용자가 이 task를 만든 사용자인지 체크 후 수정
		UserVO userVO = (UserVO) session.getAttribute("authUser");

		if (taskVO.getUserId() == userVO.getUserId()) {
			taskVO.setTaskId(taskId);
			taskService.updateTask(taskVO);
			return "redirect:/project/" + taskVO.getProjectId();
		} else {
			return "redirect:/project/" + taskVO.getProjectId();
		}

	}

	// 해당 테스크 상세 페이지 조회 안에
	// UsersTasks에 있는 멤버 조회
	@GetMapping("/viewTask/{taskId}")
	public String viewTask(@PathVariable int taskId, Model model) {
		TaskVO task = taskService.findById(taskId);

		// UserTasks에 있는 멤버 조회
		List<UsersTasksVO> usersTasksVO = taskService.getUserTasksMember(taskId);
		model.addAttribute("viewTask", task);
		model.addAttribute("usersTasks", usersTasksVO);

		return "task/view";
	}

	// 해당 테스크에 멤버 추가
	@PostMapping("/members/{taskId}")
	public String addMemberToTask(@PathVariable int taskId, @RequestParam int userId, // task 생성한 userId
			@RequestParam int addUserId, // 추가하고싶은 userId
			@RequestParam int projectId, HttpSession httpsession) {
		try {
			// 로그인한 사용자가 이 task를 만든 사용자인지 체크 후 추가
			UserVO userVO = (UserVO) httpsession.getAttribute("authUser");

			if (userId == userVO.getUserId()) {
				taskService.addMemberToTask(addUserId, taskId, projectId);
				return "redirect:/tasks/viewTask/" + String.valueOf(taskId);

			} else {
				return "redirect:/tasks/viewTask/" + String.valueOf(taskId);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/tasks/viewTask/" + String.valueOf(taskId);
	}

	// 해당 테스크 멤버 삭제
	@PostMapping("/deleteUsersTask/{taskId}")
	public String deleteUsersTasksMember(@PathVariable int taskId, @RequestParam int userId) {
		taskService.deleteUsersTasksMember(taskId, userId);
		return "redirect:/tasks/viewTask/" + String.valueOf(taskId);
	}
	
	//이 부분 우선 추가함 .. 0717 18:13
	// 해당 테스크 검색위한 모든 데이터 search
		
		@GetMapping("/SearchTasks")
		public String SearchTask(@RequestParam ("taskTitle") String taskTitle, Model model) { // attribute 때문에 파라미터를 담기위해 모델선언(박스같은 개념)
			List<TaskVO> searchedTasks = taskService.searchByTitle(taskTitle);
			model.addAttribute("SearchedTasks", searchedTasks); // 최종 뷰에 보내기 위한 작업(여기선 list.jsp)위해 모델 안의.attribute에 담는작업
			return "search/search"; //검색 결과를 보여줄 jsp 페이지로 리다이렉트
		}

}