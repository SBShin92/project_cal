package com.github.sbshin92.project_cal.data.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.github.sbshin92.project_cal.data.vo.TaskVO;
import com.github.sbshin92.project_cal.data.vo.UsersTasksVO;

//데이터베이스 연결: TasksDAO 메서드 내에서 데이터베이스 연결을 설정해야 합니다. 
// 이를 위해 MyBatis 설정 파일과 데이터베이스 연결 정보를 구성해야 합니다.
//여기선 굳이 .xml 파일을 쓰지 않고 @어노테이션을 통해 @Mapper로 선언해준다 ? ->why??
@Mapper

public interface TasksDAO {
	
	//테스크 생성
	@Insert("INSERT INTO tasks (user_id, project_id, task_title, task_description) " +
	        "VALUES (#{userId}, #{projectId}, #{taskTitle}, #{taskDescription})")
	public Integer insert(TaskVO taskVO); // => 서비스들려서 컨트롤러의 insert메서드로 가기

	//모든 task리스트들을 조회	
	@Select("SELECT task_id as taskId, "
			+ " 	user_id as userId, "
			+ " 	project_id as projectId, "
			+ " 	task_title as taskTitle, "
			+ " 	task_description as taskDescription, "
			+ " 	created_at as createdAt, "
			+ " 	updated_at as updatedAt, "
			+ " 	task_status as taskStatus "
			+ " FROM tasks")
	public List<TaskVO> findAll();

	//테스크 삭제
	 @Delete("DELETE FROM tasks WHERE task_id = #{taskId}")
	 public int deleteTask(@Param("taskId") int taskId);
	
	 
	//테스크 수정
	 @Update("UPDATE tasks SET user_id = #{userId}, project_id = #{projectId}, " +
	            "task_title = #{taskTitle}, task_description = #{taskDescription}, " +
	            "updated_at = CURRENT_TIMESTAMP " +
	            "WHERE task_id = #{taskId}")
	 public int updateTask(TaskVO taskVO);

	 //view 내용 조회
	@Select("SELECT task_id as taskId, "
			+ " 	user_id as userId, "
			+ " 	project_id as projectId, "
			+ " 	task_title as taskTitle, "
			+ " 	task_description as taskDescription, "
			+ " 	created_at as createdAt, "
			+ " 	updated_at as updatedAt, "
			+ " 	task_status as taskStatus "
			+ " FROM tasks "
			+ " WHERE task_id = #{taskId}")
	public TaskVO findById(@Param("taskId") int taskId);
	// taskVO로부터 파라미터를 받은 taskid를 통해 그 task에대한 모든세부데이터들을 찾겠다 
		
    // 테스크 멤버 추가
    @Insert("INSERT INTO users_tasks (user_id, task_id, project_id) VALUES (#{userId}, #{taskId}, #{projectId})")
    public int addMemberToTask(int userId,  int taskId, int projectId);
    
	// UserTasks에 있는 멤버 조회
	@Select("SELECT a.user_id as userId, "
			+ " 	(select user_name from users where user_id = a.user_id limit 1)  as userName "
			+ " FROM users_tasks a"
			+ " WHERE task_id = #{taskId}")    
	public List<UsersTasksVO> getUserTasksMember(int taskId);
	
		

	
	 	//users_tasks 테이블-> 어떤users가 어떤 tasks를 가지고있는지 확인하기 위한 테이블 설정
		//프로젝트멤버들 전원 (user_id,project_id는 pk값으로묶어둠 )
		
	  	// 특정 사용자가 특정 task의 멤버인지 확인 (?)
	    @Select("SELECT COUNT(*) > 0 FROM users_tasks " +
	            "WHERE user_id = #{userId} AND task_id = #{taskId}")
	    public boolean isUserTaskMember(@Param("userId") Integer userId, @Param("taskId") Integer taskId);

	    
		//테스크 멤버 삭제
		 @Delete("DELETE FROM users_tasks WHERE task_id = #{taskId} and user_id = #{userId}")
		public int deleteUsersTask(int taskId, int userId);
	    
	    
	    
	    
	    

	    //
	    @Select("SELECT t.task_id as taskId, " +
	            "t.user_id as userId, " +
	            "t.project_id as projectId, " +
	            "t.task_title as taskTitle, " +
	            "t.task_description as taskDescription, " +
	            "t.created_at as createdAt, " +
	            "t.updated_at as updatedAt, " +
	            "t.task_status as taskStatus " +
	            "FROM tasks t " +
	            "WHERE t.project_id = #{projectId}")
	    public List<TaskVO> getTasksByProjectId(@Param("projectId") Integer projectId);
}

