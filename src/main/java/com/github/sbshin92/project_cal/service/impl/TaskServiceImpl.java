package com.github.sbshin92.project_cal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.sbshin92.project_cal.data.dao.TasksDAO;
import com.github.sbshin92.project_cal.data.vo.TaskVO;
import com.github.sbshin92.project_cal.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {

    private final TasksDAO tasksDAO;

    @Autowired
    public TaskServiceImpl(TasksDAO tasksDAO) {
        this.tasksDAO = tasksDAO;
    }

    @Override
    public List<TaskVO> findAll() {
        return tasksDAO.findAll();
    }

    @Override
    public TaskVO findById(int taskId) {
        return tasksDAO.findById(taskId);
    }

    @Override
    @Transactional
    public int insert(TaskVO taskVO) {
        return tasksDAO.insert(taskVO);
    }

    @Override
    @Transactional
    public int updateTask(TaskVO taskVO) {
    	return tasksDAO.updateTask(taskVO);
    }

    @Override
    @Transactional
    public int deleteTask(int taskId) {
        return tasksDAO.deleteTask(taskId);
    }

    @Override
    public boolean isUserTaskMember(Integer userId, Integer taskId) {
        return tasksDAO.isUserTaskMember(userId, taskId);
    }

    @Override
    @Transactional
    public int addMemberToTask(Integer userId, Integer taskId) {
        // 1. 유효성 검사(null체크)
        if (userId == null || taskId == null) {
            throw new IllegalArgumentException("User ID and Task ID cannot be null");
        }

        // 2. 이미 멤버인지 확인
        if (isUserTaskMember(userId, taskId)) {
            throw new IllegalStateException("User is already a member of this task");
        }

        // 3. 멤버 추가
        return tasksDAO.addMemberToTask(userId, taskId);
    }

		//기능 추가 
    	@Override
    	 public List<TaskVO> getTasksByProjectId(Integer projectId) {
            if (projectId == null) {
                throw new IllegalArgumentException("Project ID cannot be null");
            }
            return tasksDAO.getTasksByProjectId(projectId);
        }

        
    
    }

//TaskServiceImpl.java














/*package com.github.sbshin92.project_cal.service.impl;

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

	@Override
	public Object getTasksByProjectId(Integer projectId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
*/