package com.github.sbshin92.project_cal.service.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
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
    
	//해당 테스크 멤버 삭제
	public int deleteUsersTasksMember(int taskId, int userId) {
        return tasksDAO.deleteUsersTasksMember(taskId, userId);
    }
    
	
	
	
	//기능 추가 getTasksByProjectId
	@Override
	 public List<TaskVO> getTasksByProjectId(Integer projectId) {
        if (projectId == null) {
            throw new IllegalArgumentException("Project ID cannot be null");
        }
        return tasksDAO.getTasksByProjectId(projectId);
    }

	//삭제금지 0722 21:00 // searchByTitle for search
	@Override
	public List<TaskVO> searchByTitle(TaskVO taskVO) {
		  	// 검색어가 null이거나 비어있는 경우 처리
		if (taskVO.getTaskTitle() == null || taskVO.getTaskTitle().trim().isEmpty()) {
			return List.of();	//빈리스트 반환 또는 다른 적절한 처리 
		}
			// 검색어 전처리 (옵션)
        String processedTitle = taskVO.getTaskTitle().trim(); // 앞뒤 공백 제거
	    taskVO.setTaskTitle(processedTitle);
	    
        //페이지 세팅
        int page = 0;
        int size = 10;
        if(taskVO.getPage() != 0 ) {
        	page = taskVO.getPage();
        }
        
		int offset = (page - 1) * size;
		
		return tasksDAO.searchByTitle(taskVO, new RowBounds(offset, size));
	}
	
	//삭제금지 0723
	@Override
	public int getTotalTasksCount(TaskVO taskVO) {

	  	// 검색어가 null이거나 비어있는 경우 처리
		if (taskVO.getTaskTitle() == null || taskVO.getTaskTitle().trim().isEmpty()) {
			return 0;	//빈리스트 반환 또는 다른 적절한 처리 
		}
			// 검색어 전처리 (옵션)
	    String processedTitle = taskVO.getTaskTitle().trim(); // 앞뒤 공백 제거
	    taskVO.setTaskTitle(processedTitle);
	    return tasksDAO.getTotalTasksCount(taskVO);
	}

	//
	
    
}    