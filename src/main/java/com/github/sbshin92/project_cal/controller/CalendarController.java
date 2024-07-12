package com.github.sbshin92.project_cal.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
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

import jakarta.servlet.http.HttpSession;

@RequestMapping("/calendar")
@Controller
public class CalendarController {
	@Autowired
	private CalendarService calendarService;
	
	@GetMapping({"", "/"})
	public String calendarPage(HttpSession session) {
		if (session.getAttribute("viewMonth") == null && session.getAttribute("viewDate") == null) {
			LocalDate localDate = LocalDate.now();
			int year = localDate.getYear();
			int month = localDate.getMonthValue();
			session.setAttribute("viewYear", year);
			session.setAttribute("viewMonth", month);
		}
		return "calendar/calendar";
	}
	
	@GetMapping("/{yearMonth}")
	public String calendarPageWithMonth(@PathVariable("yearMonth") String yearMonth, HttpSession session) {
		
		try {
			int year = Integer.parseInt(yearMonth.substring(0, 4));
			int month = Integer.parseInt(yearMonth.substring(4));
			if (month > 12 || month < 1)
				month = 0;
			session.setAttribute("viewYear", year);
			session.setAttribute("viewMonth", month);			
		} catch (NumberFormatException e) {
			session.setAttribute("viewYear", 0);
			session.setAttribute("viewMonth", 0);						
		}
		return "redirect:/calendar";
	}
	
	@GetMapping("/{yearMonth}/{date}")
	public String calendarPageWithDateProjectList(@PathVariable("yearMonth") String yearMonth, 
											@PathVariable("date") String date,
											RedirectAttributes redirectAttributes) {
		
		String fullDate = yearMonth + date;
		Date dateTypeDate = stringToDate(fullDate);
		List<ProjectVO> lst = calendarService.getProjectListByDate(dateTypeDate);
		redirectAttributes.addFlashAttribute("viewDate", date);
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
