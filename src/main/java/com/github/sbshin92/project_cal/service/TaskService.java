package com.github.sbshin92.project_cal.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.sbshin92.project_cal.data.vo.TaskVO;

public interface TaskService {
	
	//1.메서드 구현: TasksDAO 인터페이스에 선언된 메서드를 구현해야 합니다. 
	//각 메서드는 데이터베이스 쿼리를 실행하고 결과를 반환해야 합니다
	
	//2.매개변수와 반환값: 메서드의 매개변수와 반환값은 TaskVO 객체를 사용해야 합니다. 
	//예를 들어 findById 메서드는 taskId를 입력으로 받아 
	//해당 ID에 해당하는 작업을 반환해야 합니다.
	
	//3. TaskService 에서는 서비스를 구현을위한 서비스 로직을 작성한다 
	
	/* 서비스 로직 구현.
	1. get * list datas
	2. CRUD 기능 구현 
	3. 테스크에 뉴멤버 새로 추가하는 기능 구현 
	
	
	*/
	
	// List<TaskVO>-> 데이터를 리스트화해서 모두 불러오기
	public List<TaskVO> findAll();
	
	//Read task
	//(TaskVO를 리턴타입으로) TaskId(pk)를 통해 해당 task테이블 내의 모든컬럼의 데이터들 불러오기
	public TaskVO findById(@Param("taskId") int taskId);
	
	//Create task
	//insert문 수행 (task title + task desc 추가 기재위함 (add) ->이후 서브밋제출은 tasks테이블에 반영할예정
	public int insert(TaskVO taskVO);
	
	//Update task
	public int updateTask(TaskVO taskVO);
		
	//Delete task
	public int deleteTask(int taskId);
	
	//추가기능구현
	//1)users_tasks 테이블에서 특정 사용자가 특정 프로젝트의 멤버인지 확인합니다 ?
	public boolean isUserTaskMember(@Param("userId") Integer userId, @Param("taskId") Integer taskId);
		
	//2) users_tasks 테이블에서 테스크에 뉴멤버 새로 추가하는 메서드
	public int addMemberToTask(@Param("userId") Integer userId, @Param("taskId") Integer taskId);

	 //3)특정 프로젝트에 속한 모든 태스크를 조회합니다.
     //@param projectId 조회할 프로젝트의 ID
     //@return 해당 프로젝트의 모든 태스크 리스트
     List<TaskVO> getTasksByProjectId(Integer projectId);
}
	
	// TaskService.java

		
	
	





/*
 package com.github.sbshin92.project_cal.service;


import java.util.List;

import com.github.sbshin92.project_cal.data.vo.TaskVO;

public interface TaskService {
	
	public List<TaskVO> getAllTasks();
	public TaskVO getTaskById(Integer taskId);
	public boolean addTask(TaskVO taskVO);
	
	public Object getTasksByProjectId(Integer projectId);
}
*/