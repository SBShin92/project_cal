package com.github.sbshin92.project_cal.data.dao;

import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.github.sbshin92.project_cal.data.vo.ProjectVO;

@Mapper
public interface CalendarDAO {

	@Select("SELECT project_id as projectId, "
			+ " user_id as userId, "
			+ " project_title as projectTitle, "
			+ " project_description as projectDescription, "
			+ " created_at as createdAt, "
			+ " updated_at as updatedAt, "
			+ " project_status as projectStatus, "
			+ " start_date as startDate, "
			+ " end_date as endDate "
			+ " FROM projects "
			+ " WHERE start_date <= #{date} AND end_date >= #{date}"
			+ " ORDER BY start_date ASC")
	public List<ProjectVO> getListByDate(LocalDate date);
	
	
	@Select("SELECT p.project_id as projectId, "
			+ " p.user_id as userId, "
			+ " p.project_title as projectTitle, "
			+ " p.project_description as projectDescription, "
			+ " p.created_at as createdAt, "
			+ " p.updated_at as updatedAt, "
			+ " p.project_status as projectStatus, "
			+ " p.start_date as startDate, "
			+ " p.end_date as endDate "
			+ " FROM projects p JOIN projects_users pu ON p.project_id = pu.project_id "
			+ " WHERE start_date <= #{date} AND end_date >= #{date} AND pu.user_id = #{userId} "
			+ " ORDER BY start_date ASC")
	public List<ProjectVO> getListByDateWithUserId(LocalDate date, int userId);
	
	

	@Select("SELECT project_id as projectId, "
			+ " user_id as userId, "
			+ " project_title as projectTitle, "
			+ " project_description as projectDescription, "
			+ " created_at as createdAt, "
			+ " updated_at as updatedAt, "
			+ " project_status as projectStatus, "
			+ " start_date as startDate, "
			+ " end_date as endDate "
			+ " FROM projects "
			+ " WHERE (start_date BETWEEN #{startOfMonth} AND #{endOfMonth}) OR "
			+ " (end_date BETWEEN #{startOfMonth} AND #{endOfMonth})"
			+ " ORDER BY start_date ASC")
	public List<ProjectVO> getListByMonth(LocalDate startOfMonth, LocalDate endOfMonth);
	
	@Select("SELECT p.project_id as projectId, "
			+ " p.user_id as userId, "
			+ " p.project_title as projectTitle, "
			+ " p.project_description as projectDescription, "
			+ " p.created_at as createdAt, "
			+ " p.updated_at as updatedAt, "
			+ " p.project_status as projectStatus,"
			+ " p.start_date as startDate,"
			+ " p.end_date as endDate "
			+ " FROM projects p JOIN projects_users pu ON p.project_id = pu.project_id "
			+ " WHERE ((start_date BETWEEN #{startOfMonth} AND #{endOfMonth}) OR "
			+ " (end_date BETWEEN #{startOfMonth} AND #{endOfMonth})) AND pu.user_id = #{userId} "
			+ " ORDER BY start_date ASC")
	public List<ProjectVO> getListByMonthWithUserId(LocalDate startOfMonth, LocalDate endOfMonth, int userId);
	
	
	
}
