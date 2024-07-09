package com.github.sbshin92.project_cal.data.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.github.sbshin92.project_cal.data.vo.ProjectVO;

@Mapper
public interface ProjectsDAO {

	public List<ProjectVO> findAll();
	
}
