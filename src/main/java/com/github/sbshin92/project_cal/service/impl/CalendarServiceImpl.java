package com.github.sbshin92.project_cal.service.impl;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.sbshin92.project_cal.data.dao.CalendarDAO;
import com.github.sbshin92.project_cal.data.vo.ProjectVO;
import com.github.sbshin92.project_cal.service.CalendarService;

@Service
public class CalendarServiceImpl implements CalendarService {

	@Autowired
	private CalendarDAO calendarDAO;
	
	@Override
	public List<ProjectVO> getProjectListByDate(int year, int month, int date) {
		LocalDate intToLocalDate = LocalDate.of(year, month, date);
		return calendarDAO.getListByDate(intToLocalDate);
	}
	
	@Override
	public List<ProjectVO> getProjectListByDateWithUserId(int year, int month, int date, int userId) {
		LocalDate intToLocalDate = LocalDate.of(year, month, date);
		return calendarDAO.getListByDateWithUserId(intToLocalDate, userId);
	}

	@Override
	public List<ProjectVO> getProjectListByMonth(int year, int month) {
		
		YearMonth yearMonth = YearMonth.of(year, month);
		LocalDate startOfMonth = yearMonth.atDay(1);
		LocalDate endOfMonth = yearMonth.atEndOfMonth();
		
		return calendarDAO.getListByMonth(startOfMonth, endOfMonth);
	}

	@Override
	public List<ProjectVO> getProjectListByMonthWithUserId(int year, int month, int userId) {
		YearMonth yearMonth = YearMonth.of(year, month);
		LocalDate startOfMonth = yearMonth.atDay(1);
		LocalDate endOfMonth = yearMonth.atEndOfMonth();
		
		return calendarDAO.getListByMonthWithUserId(startOfMonth, endOfMonth, userId);
	}
	
}
