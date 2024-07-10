package com.github.sbshin92.project_cal.data.dao;

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
