package com.github.sbshin92.project_cal.data.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.github.sbshin92.project_cal.data.vo.RoleVO;

@Mapper
public interface RoleDAO {
	
	 @Select("SELECT role_id as roleId, " +
	            "role_name as roleName, " +
	            "project_create as projectCreate, " +
	            "project_read as projectRead, " +
	            "project_update as projectUpdate, " +
	            "project_delete as projectDelete, " +
	            "task_create as taskCreate, " +
	            "task_read as taskRead, " +
	            "task_update as taskUpdate, " +
	            "task_delete as taskDelete " +
	            "FROM roles")
	 List<RoleVO> findAll();
	 
	  @Select("SELECT role_id as roleId, " +
	            "role_name as roleName, " +
	            "project_create as projectCreate, " +
	            "project_read as projectRead, " +
	            "project_update as projectUpdate, " +
	            "project_delete as projectDelete, " +
	            "task_create as taskCreate, " +
	            "task_read as taskRead, " +
	            "task_update as taskUpdate, " +
	            "task_delete as taskDelete " +
	            "FROM roles WHERE role_id = #{roleId}")
	    RoleVO findById(@Param("roleId") int roleId);
	  
	  @Insert("INSERT INTO roles(role_name, project_create, project_read, project_update, project_delete, " +
	            "task_create, task_read, task_update, task_delete) " +
	            "VALUES(#{roleName}, #{projectCreate}, #{projectRead}, #{projectUpdate}, #{projectDelete}, " +
	            "#{taskCreate}, #{taskRead}, #{taskUpdate}, #{taskDelete})")
	    @Options(useGeneratedKeys = true, keyProperty = "roleId")
	  void insert(RoleVO role);

	  @Update("UPDATE roles SET " +
	            "role_name = #{roleName}, " +
	            "project_create = #{projectCreate}, " +
	            "project_read = #{projectRead}, " +
	            "project_update = #{projectUpdate}, " +
	            "project_delete = #{projectDelete}, " +
	            "task_create = #{taskCreate}, " +
	            "task_read = #{taskRead}, " +
	            "task_update = #{taskUpdate}, " +
	            "task_delete = #{taskDelete} " +
	            "WHERE role_id = #{roleId}")
	    void update(RoleVO role);

	    @Delete("DELETE FROM roles WHERE role_id = #{roleId}")
	    void delete(@Param("roleId") int roleId);
}
