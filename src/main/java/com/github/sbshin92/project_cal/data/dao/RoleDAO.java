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
	
	@Select("SELECT r.role_id as roleId, "
			+ "r.user_id as userId, " +
            " r.project_create as projectCreate, " +
            " r.project_read as projectRead, " +
            " r.project_update as projectUpdate, " +
            " r.project_delete as projectDelete " +
            " FROM roles r")
	 List<RoleVO> findAll();
	 
	@Select("SELECT r.role_id as roleId, "
			+ "r.user_id as userId, " +
            " r.project_create as projectCreate, " +
            " r.project_read as projectRead, " +
            " r.project_update as projectUpdate, " +
            " r.project_delete as projectDelete " +
            " FROM roles r " +
            " WHERE role_id = #{roleId}")
	  RoleVO findById(@Param("roleId") int roleId);
	  
	  
	  
	  
	  @Insert("INSERT INTO roles(user_id, project_create, project_read, project_update, project_delete) " +
	            "VALUES(#{userId}, #{projectCreate}, #{projectRead}, #{projectUpdate}, #{projectDelete}")
	    @Options(useGeneratedKeys = true, keyProperty = "roleId")
	  void insert(RoleVO role);

	  @Update(" UPDATE roles SET " +
			    " user_id = #{userId}, " +
	            " project_create = #{projectCreate}, " +
	            " project_read = #{projectRead}, " +
	            " project_update = #{projectUpdate}, " +
	            " project_delete = #{projectDelete}, " +
	            " WHERE role_id = #{roleId}")
	    void update(RoleVO role);

	    @Delete("DELETE FROM roles WHERE role_id = #{roleId}")
	    void delete(@Param("roleId") int roleId);
}