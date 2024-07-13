package com.github.sbshin92.project_cal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.sbshin92.project_cal.data.dao.TasksDAO;
import com.github.sbshin92.project_cal.data.vo.TaskVO;
import com.github.sbshin92.project_cal.data.vo.UsersTasksVO;
import com.github.sbshin92.project_cal.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {

    private final TasksDAO tasksDAO;

    @Autowired
    public TaskServiceImpl(TasksDAO tasksDAO) {
        this.tasksDAO = tasksDAO;
    }
    
    //테스크 생성
    @Override
    @Transactional
    public int insert(TaskVO taskVO) {
        return tasksDAO.insert(taskVO);
    }

    //테스크 조회
    @Override
    public List<TaskVO> findAll() {
        return tasksDAO.findAll();
    }

    
    //테크스 삭제
    @Override
    @Transactional
    public int deleteTask(int taskId) {
        return tasksDAO.deleteTask(taskId);
    }

    //테스크 수정
    @Override
    @Transactional
    public int updateTask(TaskVO taskVO) {
    	return tasksDAO.updateTask(taskVO);
    }
    
    //veiw 내용 조회
    @Override
    public TaskVO findById(int taskId) {
        return tasksDAO.findById(taskId);
    }    

    //테스크 멤버 추가
    @Override
    @Transactional
    public int addMemberToTask(int userId, int taskId , int projectId) {
        // 1. 유효성 검사(null체크)
        if (userId == 0 || taskId == 0) {
            throw new IllegalArgumentException("User ID and Task ID cannot be 0");
        }

        // 2. 이미 멤버인지 확인
        if (isUserTaskMember(userId, taskId)) {
            throw new IllegalStateException("User is already a member of this task");
        }

        // 3. 멤버 추가
        return tasksDAO.addMemberToTask(userId, taskId, projectId);
    }


	// UserTasks에 있는 멤버 조회
	public List<UsersTasksVO> getUserTasksMember(int taskId){
        return tasksDAO.getUserTasksMember(taskId);
	}
    
    
    
    
    //멤버가 테스크에 있는지 조회
    @Override
    public boolean isUserTaskMember(Integer userId, Integer taskId) {
        return tasksDAO.isUserTaskMember(userId, taskId);
    }
    
	//기능 추가 
	@Override
	 public List<TaskVO> getTasksByProjectId(Integer projectId) {
        if (projectId == null) {
            throw new IllegalArgumentException("Project ID cannot be null");
        }
        return tasksDAO.getTasksByProjectId(projectId);
    }

	//테스크 멤버 삭제
	public int deleteUsersTask(int taskId, int userId) {
        return tasksDAO.deleteUsersTask(taskId, userId);
    }
    
}    