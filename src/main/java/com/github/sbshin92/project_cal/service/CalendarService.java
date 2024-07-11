package com.github.sbshin92.project_cal.service;

import java.util.Date;
import java.util.List;

import com.github.sbshin92.project_cal.data.vo.ProjectVO;

public interface CalendarService {
	List<ProjectVO> getProjectListByDate(Date date);
	
}
