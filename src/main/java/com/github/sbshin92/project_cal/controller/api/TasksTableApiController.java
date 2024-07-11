package com.github.sbshin92.project_cal.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.sbshin92.project_cal.data.vo.ProjectVO;
import com.github.sbshin92.project_cal.data.vo.TaskVO;
import com.github.sbshin92.project_cal.data.vo.UserVO;
import com.github.sbshin92.project_cal.service.ProjectService;
import com.github.sbshin92.project_cal.service.TaskService;

@RestController
@RequestMapping("/api/tasks")
public class TasksTableApiController {
	
	@Autowired
	private TaskService taskService;

	@GetMapping("")
	public List<TaskVO> gettaskList() {
		return taskService.getAllTasks();
	}
	
    @GetMapping("/add")
    public String addtask() {
        TaskVO taskVO = TaskVO.builder()
        		.userId(1)
        		.projectId(1)
        		.taskTitle("테스트 테스크 제목")
        		.taskDescription("테스크 설명입니다\n 오늘은 이거 할래요. 같은 설명을 하고있습니다.")
                .build();

        boolean isSuccess = taskService.addTask(taskVO);

        return "<h1>Done!! Project ID: " + taskVO.getTaskId() + "</h1>";
    }
	
}
