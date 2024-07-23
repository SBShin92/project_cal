package com.github.sbshin92.project_cal.service;

import java.util.List;

import com.github.sbshin92.project_cal.data.vo.ProjectVO;

public interface CalendarService {
	public List<ProjectVO> getProjectListByDate(int year, int month, int date);
	public List<ProjectVO> getProjectListByDateWithUserId(int year, int month, int date, int userId);
	List<ProjectVO> getProjectListByMonth(int year, int month);
	List<ProjectVO> getProjectListByMonthWithUserId(int year, int month, int userId);
}
