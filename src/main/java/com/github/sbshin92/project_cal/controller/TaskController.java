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

		
		
		//테스크 생성 및 수정을 위해 
		//이 폼에서 같이 사용
	    @GetMapping("/createTaskForm") // 주의 수정과 생성을 같이 씀
	    public String createTaskForm(@RequestParam(defaultValue="0") int taskId, 
	    							// taskId에 널이 넘어온다면, int형 타입에는 널이 들어갈수 없으므로
	    							// 기본값으로 0으로 바꿔준다(defaultValue= "0" =>null상태)
	    							@RequestParam(defaultValue="0") int userId, 
					                @RequestParam (defaultValue="0") int projectId, 
					                @RequestParam(required = false) String taskTitle, 
					                @RequestParam(required = false) String taskDescription,
					                Model model) {
	    	TaskVO taskVo = new TaskVO();
	    	taskVo.setTaskId(taskId);
	    	
	    	//1. taskId가 0이면 생성을 하는것으로 한다
			// - taskId가 0이면  userId를 auth에서 가져와야 한다 .
    		// - taskVo.setUserId(auth.userId);	
	    	
	    	//2. taskId가 0이 아니면 수정을 하는것으로 한다              
	    	// - taskVo.setUserId(auth.userId);	           							 
	    	// - 추후에 테스크수정은 테스크생성자만 할수 있으므로               
	    	// - 마찬가지로 authuser로 사용 해야한다.                  
    		taskVo.setUserId(userId);
	    	taskVo.setTaskTitle(taskTitle);
	    	taskVo.setTaskDescription(taskDescription);	   	    	
	    	taskVo.setProjectId(projectId); 
	    	// 해당프로젝트의 테스크를 생성하는거니까 , 
	    	// projectId에는 값이있어야하고 나머지는 널값,

	    	//모델 어트리뷰트에 담고 보내주기 
	    	model.addAttribute("createTaskForm", taskVo);
	        return "task/form";
	    }
	    
		

		//테스크 생성 
	    @PostMapping("/createTask")
	    public String createTask(@ModelAttribute TaskVO taskVo) {
	        taskService.insert(taskVo);
	        return "redirect:/project/" + taskVo.getProjectId(); // 페이지를 리다이렉트 매핑된 url을 찾으러가야함
	    }
	    
	    
		// 테스크 조회 
		 @GetMapping("/listTasks")
	    public String listTasks(Model model) { //attribute 때문에 파라미터를 담기위해 모델선언(박스같은 개념)
	        List<TaskVO> tasks = taskService.findAll();
	        model.addAttribute("listTasks", tasks); // 최종 뷰에 보내기 위한 작업(여기선 list.jsp)위해 모델 안의.attribute에 담는작업
	        return "task/list";
	    }
		 
	    //테스크 삭제 
	    @PostMapping("/deleteTask/{taskId}")
	    public String deleteTask(@PathVariable int taskId, 
	    						 @RequestParam int projectId) {
	    	taskService.deleteTask(taskId);      
	        
	        //return "redirect:/tasks/listTasks";
	        return "redirect:/project/" + projectId; 
	    }

	    //테스크 수정 
	    @PostMapping("/updateTask/{taskId}")
	    public String updateTask(@PathVariable int taskId, @ModelAttribute TaskVO taskVO) {
	        taskVO.setTaskId(taskId);
	        taskService.updateTask(taskVO);
	        
	        //return "redirect:/tasks/listTasks";
	        return "redirect:/project/" + taskVO.getProjectId(); 
	    }	    
	    
	    //해당 테스크 상세 페이지 조회 안에 
	    //UsersTasks에 있는 멤버 조회
	    @GetMapping("/viewTask/{taskId}")
	    public String viewTask(@PathVariable int taskId, Model model) {
	        TaskVO task = taskService.findById(taskId);
	        // UserTasks에 있는 멤버 조회
	        List<UsersTasksVO> usersTasksVO = taskService.getUserTasksMember(taskId); 
	        model.addAttribute("viewTask", task);
	        model.addAttribute("usersTasks", usersTasksVO);
	        
	        return "task/view";
	    }

	    //해당 테스크에 멤버 추가 
	    @PostMapping("/members/{taskId}")
	    public String addMemberToTask(@PathVariable int taskId, @RequestParam int userId, @RequestParam int projectId) {
	        try { 
	            taskService.addMemberToTask(userId, taskId, projectId);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return "redirect:/tasks/viewTask/" + String.valueOf(taskId);
	    }
	    
	    //해당 테스크 멤버 삭제
	    @PostMapping("/deleteUsersTask/{taskId}")
	    public String deleteUsersTasksMember(@PathVariable int taskId, @RequestParam int userId) {
	        taskService.deleteUsersTasksMember(taskId, userId);      
	        return "redirect:/tasks/viewTask/" + String.valueOf(taskId);
	    }
	    
	    
	    

}