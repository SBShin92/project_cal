package com.github.sbshin92.project_cal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.sbshin92.project_cal.data.dao.TasksDAO;
import com.github.sbshin92.project_cal.data.vo.TaskVO;
import com.github.sbshin92.project_cal.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TasksDAO tasksDAO;
	
	@Override
	public List<TaskVO> getAllTasks() {
		return tasksDAO.findAll();
	}

	@Override
	public TaskVO getTaskById(Integer taskId) {
		return tasksDAO.findById(taskId);
	}

	@Override
	public boolean addTask(TaskVO taskVO) {
		return 1 == tasksDAO.insert(taskVO);
	}
	
	
}
