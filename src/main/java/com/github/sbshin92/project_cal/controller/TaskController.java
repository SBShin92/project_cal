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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.sbshin92.project_cal.data.vo.TaskVO;
import com.github.sbshin92.project_cal.service.TaskService;

@Controller
@RequestMapping("/tasks")
public class TaskController {

		@Autowired
	    private TaskService taskService;

		/*
	    @Autowired
	    public TaskController(TaskService taskService) {
	        this.taskService = taskService;
	    }
	    */

	    @GetMapping
	    public String listTasks(Model model) {
	        List<TaskVO> tasks = taskService.findAll();
	        model.addAttribute("tasks", tasks);
	        return "task/list";
	    }

	    @GetMapping("/{taskId}")
	    public String viewTask(@PathVariable int taskId, Model model) {
	        TaskVO task = taskService.findById(taskId);
	        model.addAttribute("task", task);
	        return "task/view";
	    }

	    @GetMapping("/create")
	    public String createTaskForm(Model model) {
	        model.addAttribute("task", new TaskVO());
	        return "task/form";
	    }

	    @PostMapping
	    public String createTask(@ModelAttribute TaskVO taskVO) {
	        taskService.insert(taskVO);
	        return "redirect:/tasks";
	    }

	    @GetMapping("/{taskId}/edit")
	    public String editTaskForm(@PathVariable int taskId, Model model) {
	        TaskVO task = taskService.findById(taskId);
	        model.addAttribute("task", task);
	        return "task/form";
	    }

	    @PostMapping("/{taskId}")
	    public String updateTask(@PathVariable int taskId, @ModelAttribute TaskVO taskVO) {
	        taskVO.setTaskId(taskId);
	        taskService.updateTask(taskVO);
	        return "redirect:/tasks/" + taskId;
	    }

	    @DeleteMapping("/{taskId}")
	    public ResponseEntity<Void> deleteTask(@PathVariable int taskId) {
	        taskService.deleteTask(taskId);
	        return ResponseEntity.ok().build();
	    }

	    @PostMapping("/{taskId}/members")
	    public ResponseEntity<Void> addMemberToTask(@PathVariable int taskId, @RequestParam int userId) {
	        taskService.addMemberToTask(userId, taskId);
	        return ResponseEntity.ok().build();
	    }
	}
	
	/*
    @Autowired
    private TaskService taskService;

    @Autowired
    private ProjectService projectService;
    
    @GetMapping("/list/{projectId}")
    public String listTasks(@PathVariable int projectId, Model model) {
        List<TaskVO> tasks = taskService.getTasksByProjectId(projectId);
        model.addAttribute("tasks", tasks);
        model.addAttribute("projectId", projectId);
        return "task/list";
    }

    @GetMapping("/create/{projectId}")
    public String createTaskForm(@PathVariable int projectId, Model model) {
        model.addAttribute("projectId", projectId);
        return "task/create";
    }

    @PostMapping("/create")
    public String createTask(@ModelAttribute TaskVO task, @RequestParam int userId, RedirectAttributes redirectAttributes) {
        if (!projectService.isUserProjectMember(userId, task.getProjectId())) {
            redirectAttributes.addFlashAttribute("error", "You are not a member of this project.");
            return "redirect:/project/list";
        }
        
        taskService.createTask(task);
        taskService.addTaskMember(userId, task.getTaskId(), true);
        
        redirectAttributes.addFlashAttribute("message", "Task created successfully.");
        return "redirect:/task/list/" + task.getProjectId();
    }

    @GetMapping("/view/{taskId}")
    public String viewTask(@PathVariable int taskId, Model model) {
        TaskVO task = taskService.getTaskById(taskId);
        if (task == null) {
            return "redirect:/error";
        }
        model.addAttribute("task", task);
        return "task/view";
    }

    @GetMapping("/edit/{taskId}")
    public String editTaskForm(@PathVariable int taskId, Model model) {
        TaskVO task = taskService.getTaskById(taskId);
        if (task == null) {
            return "redirect:/error";
        }
        model.addAttribute("task", task);
        return "task/edit";
    }

    @PostMapping("/edit")
    public String editTask(@ModelAttribute TaskVO task, @RequestParam int userId, RedirectAttributes redirectAttributes) {
        if (!taskService.isTaskCreator(userId, task.getTaskId())) {
            redirectAttributes.addFlashAttribute("error", "You don't have permission to edit this task.");
            return "redirect:/task/view/" + task.getTaskId();
        }
        
        taskService.updateTask(task);
        
        redirectAttributes.addFlashAttribute("message", "Task updated successfully.");
        return "redirect:/task/view/" + task.getTaskId();
    }

    @PostMapping("/delete/{taskId}")
    public String deleteTask(@PathVariable int taskId, @RequestParam int userId, RedirectAttributes redirectAttributes) {
        TaskVO task = taskService.getTaskById(taskId);
        if (task == null) {
            return "redirect:/error";
        }
        
        
        if (!taskService.isTaskCreator(userId, taskId)) {
            redirectAttributes.addFlashAttribute("error", "You don't have permission to delete this task.");
            return "redirect:/task/view/" + taskId;
        }
        
        taskService.deleteTask(taskId);
        
        redirectAttributes.addFlashAttribute("message", "Task deleted successfully.");
        return "redirect:/task/list/" + task.getProjectId();
    }

    @PostMapping("/{taskId}/addMember")
    public String addTaskMember(@PathVariable int taskId, @RequestParam int userId, @RequestParam int creatorId, RedirectAttributes redirectAttributes) {
        if (!taskService.isTaskCreator(creatorId, taskId)) {
            redirectAttributes.addFlashAttribute("error", "You don't have permission to add members to this task.");
            return "redirect:/task/view/" + taskId;
        }
        
        taskService.addTaskMember(userId, taskId, false);
        
        redirectAttributes.addFlashAttribute("message", "Member added to task successfully.");
        return "redirect:/task/view/" + taskId;
    }
}

*/