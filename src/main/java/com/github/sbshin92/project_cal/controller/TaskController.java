package com.github.sbshin92.project_cal.controller;

import java.util.List;
/*
 모든 task 목록을 보여주는 페이지 (/tasks)
개별 task 상세 보기 페이지 (/tasks/{taskId})
task 생성 폼 페이지 (/tasks/create) 
task 수정 폼 페이지 (/tasks/{taskId}/edit)
task 생성, 수정, 삭제 기능
task에 멤버 추가 기능
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.sbshin92.project_cal.data.vo.TaskVO;
import com.github.sbshin92.project_cal.data.vo.UsersTasksVO;
import com.github.sbshin92.project_cal.service.ProjectService;
import com.github.sbshin92.project_cal.service.TaskService;

@Controller
@RequestMapping("/tasks") // 이거중요
public class TaskController {

		@Autowired
	    private TaskService taskService;

		@Autowired
	    private ProjectService projectService;


		//테스크 생성 폼
	    @GetMapping("/createTaskForm")
	    public String createTaskForm(@RequestParam(defaultValue="0") int taskId, 
	    							@RequestParam(defaultValue="0") int userId, 
					                @RequestParam(defaultValue="0") int projectId, 
					                @RequestParam(required = false) String taskTitle, 
					                @RequestParam(required = false) String taskDescription,
					                Model model) {
	    	TaskVO taskVo = new TaskVO();
	    	taskVo.setTaskId(taskId);
	    	taskVo.setUserId(userId);
	    	taskVo.setProjectId(projectId);
	    	taskVo.setTaskTitle(taskTitle);
	    	taskVo.setTaskDescription(taskDescription);
	    	
	    	model.addAttribute("task", taskVo);
	    	
	        return "task/form";
	    }
	    
		//테스크 생성 
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
	        return "redirect:/tasks/listTasks"; // 페이지를 리다이렉트 매핑된 url을 찾으러가야함
	    }
	    
		// 테스크 조회
		 @GetMapping("/listTasks")
	    public String listTasks(Model model) { //attribute 때문에 파라미터를 담기위해 모델선언(박스같은 개념)
	        List<TaskVO> tasks = taskService.findAll();
	        model.addAttribute("tasks", tasks); // 최종 뷰에 보내기 위한 작업(여기선 list.jsp)위해 모델 안의.attribute에 담는작업
	        return "task/list";
	    }
		 
	    //테스크 삭제
	    @PostMapping("/deleteTask/{taskId}")
	    public String deleteTask(@PathVariable int taskId) {
	        taskService.deleteTask(taskId);      
	        return "redirect:/tasks/listTasks";
	    }

	    //테스크 수정
	    @PostMapping("/updateTask/{taskId}")
	    public String updateTask(@PathVariable int taskId, @ModelAttribute TaskVO taskVO) {
	        taskVO.setTaskId(taskId);
	        taskService.updateTask(taskVO);
	        return "redirect:/tasks/listTasks";
	    }	    
	    
	    @GetMapping("/viewTask/{taskId}")
	    public String viewTask(@PathVariable int taskId, Model model) {
	        TaskVO task = taskService.findById(taskId);
	        // UserTasks에 있는 멤버 조회
	        List<UsersTasksVO> usersTasksVO = taskService.getUserTasksMember(taskId); 
	        model.addAttribute("task", task);
	        model.addAttribute("usersTasks", usersTasksVO);
	        
	        return "task/view";
	    }

	    
	    //테스크에 멤버 추가기능 
	    @PostMapping("/members/{taskId}")
	    public String addMemberToTask(@PathVariable int taskId, @RequestParam int userId, @RequestParam int projectId) {
	        try {
	            taskService.addMemberToTask(userId, taskId, projectId);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return "redirect:/tasks/viewTask/" + String.valueOf(taskId);
	    }
	    
	    //테스크 멤버 삭제
	    @PostMapping("/deleteUsersTask/{taskId}")
	    public String deleteUsersTask(@PathVariable int taskId, @RequestParam int userId) {
	        taskService.deleteUsersTask(taskId, userId);      
	        return "redirect:/tasks/viewTask/" + String.valueOf(taskId);
	    }

}