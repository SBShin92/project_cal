package com.github.sbshin92.project_cal.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.github.sbshin92.project_cal.data.vo.ProjectVO;
import com.github.sbshin92.project_cal.service.CalendarService;

@RequestMapping("/calendar")
@Controller
public class CalendarController {
	@Autowired
	private CalendarService calendarService;
	
	@GetMapping({"", "/"})
	public String calendarPage() {
		return "calendar/calendar";
	}
	
	@GetMapping("/{date}")
	public String calendarPageWithDateProjectList(@PathVariable("date") String dateString, 
			RedirectAttributes redirectAttributes) {
		Date date = stringToDate(dateString);
		List<ProjectVO> lst = calendarService.getProjectListByDate(date);
		redirectAttributes.addFlashAttribute("projectListByDate", lst);
		return "redirect:/calendar";	
	}
	
	
	private Date stringToDate(String dateString) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		try {
			Date convertedDate = formatter.parse(dateString);
			return convertedDate;
		} catch (ParseException e) {
			System.err.println("잘못된 date형식 (yyyyMMdd)");
			return null;
		}
	}
	
	

}
