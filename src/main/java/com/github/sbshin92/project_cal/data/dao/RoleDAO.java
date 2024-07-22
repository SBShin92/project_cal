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
	
	@Select("SELECT r.role_id as roleId, " +
            "r.role_name as roleName, " +
            "r.role_description as roleDescription, " +
            "r.project_create as projectCreate, " +
            "r.project_read as projectRead, " +
            "r.project_update as projectUpdate, " +
            "r.project_delete as projectDelete, " +
            "r.task_create as taskCreate, " +
            "r.task_read as taskRead, " +
            "r.task_update as taskUpdate, " +
            "r.task_delete as taskDelete, " +
            "(SELECT COUNT(*) FROM users_roles ru WHERE ru.role_id = r.role_id) as role_users_count " +
            "FROM roles r")
	 List<RoleVO> findAll();
	 
	@Select("SELECT r.role_id as roleId, " +
            "r.role_name as roleName, " +
            "r.role_description as roleDescription, " +
            "r.project_create as projectCreate, " +
            "r.project_read as projectRead, " +
            "r.project_update as projectUpdate, " +
            "r.project_delete as projectDelete, " +
            "r.task_create as taskCreate, " +
            "r.task_read as taskRead, " +
            "r.task_update as taskUpdate, " +
            "r.task_delete as taskDelete, " +
            "(SELECT COUNT(*) FROM users_roles ru WHERE ru.role_id = r.role_id) as role_users_count " +
            "FROM roles r WHERE r.role_id = #{roleId}")
	  RoleVO findById(@Param("roleId") int roleId);
	  
	  
	  
	  
	  @Insert("INSERT INTO roles(role_name, role_description, project_create, project_read, project_update, project_delete, " +
	            "task_create, task_read, task_update, task_delete) " +
	            "VALUES(#{roleName}, #{roleDescription}, #{projectCreate}, #{projectRead}, #{projectUpdate}, #{projectDelete}, " +
	            "#{taskCreate}, #{taskRead}, #{taskUpdate}, #{taskDelete})")
	    @Options(useGeneratedKeys = true, keyProperty = "roleId")
	  void insert(RoleVO role);

	  @Update("UPDATE roles SET " +
	            "role_name = #{roleName}, " +
	            "role_description = #{roleDescription}, " +
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