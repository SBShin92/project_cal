package com.github.sbshin92.project_cal.data.dao;

import java.util.Date;
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
			+ " project_status as projectStatus,"
			+ " start_date as startDate,"
			+ " end_date as endDate "
			+ " FROM projects "
			+ " WHERE start_date <= #{date} AND end_date >= #{date}")
	public List<ProjectVO> getListByDate(Date date);
	
}
