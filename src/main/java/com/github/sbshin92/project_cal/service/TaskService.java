package com.github.sbshin92.project_cal.service;

import java.util.List;

import com.github.sbshin92.project_cal.data.vo.TaskVO;

public interface TaskService {
	
	public List<TaskVO> getAllTasks();
	public TaskVO getTaskById(Integer taskId);
	public boolean addTask(TaskVO taskVO);
	
	public Object getTasksByProjectId(Integer projectId);
}
