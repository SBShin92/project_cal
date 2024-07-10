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
	
	  @Select("SELECT * FROM projects")
	    List<ProjectVO> findAll();

	    @Select("SELECT * FROM projects WHERE project_id = #{projectId}")
	    ProjectVO findById(@Param("projectId") int projectId);

	    @Insert("INSERT INTO projects (user_id, project_title, project_description, created_at, updated_at, project_status) " +
	            "VALUES (#{userId}, #{projectTitle}, #{projectDescription}, #{createdAt}, #{updatedAt}, #{projectStatus})")
	    @Options(useGeneratedKeys = true, keyProperty = "projectId")
	    void insert(ProjectVO project);

	    @Update("UPDATE projects SET user_id = #{userId}, project_title = #{projectTitle}, " +
	            "project_description = #{projectDescription}, updated_at = #{updatedAt}, " +
	            "project_status = #{projectStatus} WHERE project_id = #{projectId}")
	    void update(ProjectVO project);

	    @Delete("DELETE FROM projects WHERE project_id = #{projectId}")
	    void delete(@Param("projectId") int projectId);
}


