package com.github.sbshin92.project_cal.data.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.github.sbshin92.project_cal.data.vo.ProjectVO;

@Mapper
public interface ProjectsDAO {
	
	@Select("SELECT project_id as projectId, "
			+ " user_id as userId, "
			+ " project_title as projectTitle, "
			+ " project_description as projectDescription, "
			+ " created_at as createdAt, "
			+ " updated_at as updatedAt, "
			+ " project_status as projectStatus "
			+ " FROM projects")
	public List<ProjectVO> findAll();
	
	@Select("SELECT project_id as projectId, "
			+ " user_id as userId, "
			+ " project_title as projectTitle, "
			+ " project_description as projectDescription, "
			+ " created_at as createdAt, "
			+ " updated_at as updatedAt, "
			+ " project_status as projectStatus "
			+ " FROM projects "
			+ " WHERE project_id = #{projectId}")
	public ProjectVO findById(@Param("projectId") int projectId);
	
	@Insert("INSERT INTO projects (user_id, project_title, project_description) " +
	        "VALUES (#{userId}, #{projectTitle}, #{projectDescription})")
	@Options(useGeneratedKeys = true, keyProperty = "projectId")
	public Integer insert(ProjectVO project);
	
	@Update("UPDATE projects SET user_id = #{userId}, project_title = #{projectTitle}, " +
	        "project_description = #{projectDescription}, updated_at = #{updatedAt}, " +
	        "project_status = #{projectStatus} WHERE project_id = #{projectId}")
	public Integer update(ProjectVO project);
	
	@Delete("DELETE FROM projects WHERE project_id = #{projectId}")
    public Integer delete(@Param("projectId") int projectId);
}


