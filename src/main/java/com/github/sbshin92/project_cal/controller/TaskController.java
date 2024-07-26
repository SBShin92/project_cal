package com.github.sbshin92.project_cal.controller;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.sbshin92.project_cal.data.vo.MessageVO;
import com.github.sbshin92.project_cal.data.vo.ProjectVO;
import com.github.sbshin92.project_cal.data.vo.TaskVO;
import com.github.sbshin92.project_cal.data.vo.UserVO;
import com.github.sbshin92.project_cal.service.MessageService;
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

	@Autowired
	private MessageService messageService;

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
		
		// 프로젝트 멤버 아니면 태스크 생성 권한이 없어
		
		
		ProjectVO projectVO = projectService.getProjectById(projectId);
		TaskVO tv = taskService.findById(taskId);
		
		List<UserVO> userMemberVOs = projectService.getProjectMembers(projectId);
		// 관리자와 본인과 팀장이 아니면 태스크 삭제 권한이 없어
		
		
		if (!"admin".equals(userVO.getUserAuthority()) && projectVO.getUserId() != userVO.getUserId()) {
			boolean flag = false;
			for (UserVO userMemverVO: userMemberVOs) {
				if (userMemverVO.getUserId() == userVO.getUserId()) {
					flag = true;
					break ;
				}
			}
			if (flag == false)
				return "redirect:/access-denied";
		}
		
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
			if (existingTask.getUserId() == userVO.getUserId() || "admin".equals(userVO.getUserAuthority()) || projectVO.getUserId() == userVO.getUserId()) {
				// 현재 사용자가 테스크 생성자인 경우에만 수정 허용
				taskVo = existingTask;
				model.addAttribute("createTaskForm", taskVo);
			} else {
				// 권한이 없는 경우 에러 처리
				model.addAttribute("error", "이 태스크를 수정할 권한이 없습니다.");
				return "redirect:/access-denied";
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
		ProjectVO projectVO = projectService.getProjectById(taskVo.getProjectId());
		taskVo.setUserId(userVO.getUserId());

		// 프로젝트 멤버 아니면 태스크 생성 권한이 없어
		if (!"admin".equals(userVO.getUserAuthority())
				&& !projectService.isUserProjectMember(userVO.getUserId(), projectVO.getProjectId())) {
			return "redirect:/access-denied";
		}

		boolean success = (1 == taskService.insert(taskVo));
		if (success) {
			String title = "태스크가 생성되었습니다.";
			String description = "[대상 프로젝트]: " + projectVO.getProjectTitle() + "<br/>[생성된 태스크]: "
					+ taskVo.getTaskTitle() + "<br/>[진행 상태]: " + taskVo.getTaskStatus();

			MessageVO sendMessageVO = MessageVO.builder().senderUserId(userVO.getUserId())
					.receiverUserId(projectVO.getUserId()).messageTitle(title).messageDescription(description)
					.isAlarm(true).build();
			messageService.sendMessage(sendMessageVO);
		}
		return "redirect:/project/" + taskVo.getProjectId(); // 페이지를 리다이렉트 매핑된 url을 찾으러가야함
	}

	// 테스크 조회
	@GetMapping("/listTasks")
	public String listTasks(Model model) { // attribute 때문에 파라미터를 담기위해 모델선언(박스같은 개념)
		List<TaskVO> tasks = taskService.findAll();
		model.addAttribute("listTasks", tasks); // 최종 뷰에 보내기 위한 작업(여기선 list.jsp)위해 모델 안의.attribute에 담는작업
		return "search/search";
	}
	
	// 0725 수정함 해당테스크Id로 조회한 해당 테스크 관련 상세 페이지 조회 	
	@GetMapping("/viewTask/{taskId}")
	public String viewTask(@PathVariable int taskId, Model model) {
		TaskVO task = taskService.findById(taskId);
		model.addAttribute("Task", task);
		
		return "task/view";
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
		ProjectVO projectVO = projectService.getProjectById(projectId);
		TaskVO taskVO = taskService.findById(taskId);

		// 관리자와 본인과 팀장이 아니면 태스크 삭제 권한이 없어,
		if (!"admin".equals(userVO.getUserAuthority()) && taskVO.getUserId() != userVO.getUserId()
				&& projectVO.getUserId() != userVO.getUserId()) {
			return "redirect:/access-denied";
		}


		boolean success = (1 == taskService.deleteTask(taskId));

		if (success) {
			String title = "태스크가 삭제되었습니다.";
			String description = "[대상 프로젝트]: " + projectVO.getProjectTitle() + "<br/>[삭제된 태스크]: "
					+ taskVO.getTaskTitle();
			MessageVO sendMessageVO = MessageVO.builder().senderUserId(userVO.getUserId())
					.receiverUserId(projectVO.getUserId()).messageTitle(title).messageDescription(description)
					.isAlarm(true).build();
			messageService.sendMessage(sendMessageVO);
		}
		return "redirect:/project/" + projectId;
		

	}

	// 테스크 수정
	@PostMapping("/updateTask/{taskId}")
	public String updateTask(@PathVariable int taskId, @ModelAttribute TaskVO taskVO, HttpSession session) {

		// 로그인한 사용자가 이 task를 만든 사용자인지 체크 후 수정
		UserVO userVO = (UserVO) session.getAttribute("authUser");
		ProjectVO projectVO = projectService.getProjectById(taskVO.getProjectId());

		// 관리자와 본인과 팀장이 아니면 태스크 삭제 권한이 없어,
		if (!"admin".equals(userVO.getUserAuthority()) && taskVO.getUserId() != userVO.getUserId()
				&& projectVO.getUserId() != userVO.getUserId()) {
			return "redirect:/access-denied";
		}

		taskVO.setTaskId(taskId);
		boolean success = (1 == taskService.updateTask(taskVO));
		if (success) {
			String title = "태스크에 변경사항이 있습니다.";
			String description = "[대상 프로젝트]: " + projectVO.getProjectTitle() + "<br/>[변경된 태스크]: "
					+ taskVO.getTaskTitle() + "<br/>[진행 상태]: " + taskVO.getTaskStatus();

			MessageVO sendMessageVO = MessageVO.builder().senderUserId(userVO.getUserId())
					.receiverUserId(projectVO.getUserId()).messageTitle(title).messageDescription(description)
					.isAlarm(true).build();
			messageService.sendMessage(sendMessageVO);
		}

		return "redirect:/project/" + taskVO.getProjectId();
	}
	
}
