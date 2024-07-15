package com.github.sbshin92.project_cal.service;

import java.util.Date;
import java.util.List;

import com.github.sbshin92.project_cal.data.vo.ProjectVO;

public interface CalendarService {
	public List<ProjectVO> getProjectListByDate(int year, int month, int date);
	List<ProjectVO> getProjectListByMonth(int year, int month);
}
