package com.github.sbshin92.project_cal.data.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.github.sbshin92.project_cal.data.vo.TaskVO;

@Mapper
public interface TasksDAO {
	
	public List<TaskVO> findAll();
	
}
