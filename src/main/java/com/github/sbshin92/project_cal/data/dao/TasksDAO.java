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

//데이터베이스 연결: TasksDAO 메서드 내에서 데이터베이스 연결을 설정해야 합니다. 
// 이를 위해 MyBatis 설정 파일과 데이터베이스 연결 정보를 구성해야 합니다.
//여기선 굳이 .xml 파일을 쓰지 않고 @어노테이션을 통해 @Mapper로 선언해준다 ? ->why??
@Mapper

public interface TasksDAO {
	
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
	//모든 task리스트들을 조회
	
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
	
	@Insert("INSERT INTO tasks (user_id, project_id, task_title, task_description) " +
	        "VALUES (#{userId}, #{projectId}, #{taskTitle}, #{taskDescription})")
	@Options(useGeneratedKeys = true, keyProperty = "taskId")
	public Integer insert(TaskVO taskVO);
	//TaskVO의 파라미터를 받아 
	//user_id, project_id, task_title, task_description 을 insert해주기 (add,create)  

	
	//update 수정
	 @Update("UPDATE tasks SET user_id = #{userId}, project_id = #{projectId}, " +
	            "task_title = #{taskTitle}, task_description = #{taskDescription}, " +
	            "updated_at = CURRENT_TIMESTAMP " +
	            "WHERE task_id = #{taskId}")
	 public int updateTask(TaskVO taskVO);
	

	//delete 삭제
	 @Delete("DELETE FROM tasks WHERE task_id = #{taskId}")
	 public int deleteTask(@Param("taskId") int taskId);
	
	
		
	 	//users_tasks 테이블-> 어떤users가 어떤 tasks를 가지고있는지 확인하기 위한 테이블 설정
		//프로젝트멤버들 전원 (user_id,project_id는 pk값으로묶어둠 )
		
	  	// 특정 사용자가 특정 task의 멤버인지 확인 (?)
	    @Select("SELECT COUNT(*) > 0 FROM users_tasks " +
	            "WHERE user_id = #{userId} AND task_id = #{taskId}")
	    public boolean isUserTaskMember(@Param("userId") Integer userId, @Param("taskId") Integer taskId);

	    // 이미 생성된 users_tasks에 새 멤버 추가
	    @Insert("INSERT INTO users_tasks (user_id, task_id) VALUES (#{userId}, #{taskId})")
	    public int addMemberToTask(@Param("userId") Integer userId, @Param("taskId") Integer taskId);
}

// TasksDAO.java


/*package com.github.sbshin92.project_cal.data.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.github.sbshin92.project_cal.data.vo.TaskVO;

@Mapper
public interface TasksDAO {
	
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
	
	@Insert("INSERT INTO tasks (user_id, project_id, task_title, task_description) " +
	        "VALUES (#{userId}, #{projectId}, #{taskTitle}, #{taskDescription})")
	@Options(useGeneratedKeys = true, keyProperty = "taskId")
	public Integer insert(TaskVO taskVO);
	
}
*/