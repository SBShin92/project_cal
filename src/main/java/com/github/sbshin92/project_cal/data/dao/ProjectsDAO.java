package com.github.sbshin92.project_cal.data.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.sbshin92.project_cal.data.vo.ProjectVO;

@Mapper
public interface ProjectsDAO {

	public List<ProjectVO> findAll();


	void update(ProjectVO project);

	void insert(ProjectVO project);

	void delete(int projectId);

	public ProjectVO findById(int projectId);

}
