package com.github.sbshin92.project_cal.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.sbshin92.project_cal.data.vo.TaskVO;
import com.github.sbshin92.project_cal.data.vo.UsersTasksVO;

public interface TaskService {
	
	//테스크 생성
	public int insert(TaskVO taskVO); //public 접근제한자 _모든접근권한가능 / private은 클래스 안에서만 접근 가능
	
	// 테스트 조회
	public List<TaskVO> findAll(); //리턴타입은 List<TaskVO> 로 이것을 리턴해준다는뜻. 

	//테스트 삭제
	public int deleteTask(int taskId);
	
	//테스트 수정
	public int updateTask(TaskVO taskVO);
	
	//veiw 내용 조회
	//(TaskVO를 리턴타입으로) TaskId(pk)를 통해 해당 task테이블 내의 모든컬럼의 데이터들 불러오기
	public TaskVO findById(int taskId);
	
	
	//테스크 멤버 추가
	public int addMemberToTask(int userId, int taskId, int projectId);
	
	// UserTasks에 있는 멤버 조회
	public List<UsersTasksVO> getUserTasksMember(int taskId);
	
	//추가기능구현
	//1)users_tasks 테이블에서 특정 사용자가 특정 프로젝트의 멤버인지 확인합니다 ?
	public boolean isUserTaskMember(@Param("userId") Integer userId, @Param("taskId") Integer taskId);
		

	//테스크 멤버 삭제
	public int deleteUsersTasksMember(int taskId, int userId);
	
	 //3)특정 프로젝트에 속한 모든 태스크를 조회합니다.
     //@param projectId 조회할 프로젝트의 ID
     //@return 해당 프로젝트의 모든 태스크 리스트
	public List<TaskVO> getTasksByProjectId(Integer projectId);

     //
	public List<TaskVO> searchByTitle(String taskTitle);
}
	

//
//



