package com.github.sbshin92.project_cal.service.impl;

import java.util.Date;
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
	public List<ProjectVO> getProjectListByDate(Date date) {
		return calendarDAO.getListByDate(date);
	}
	
}
