package com.github.sbshin92.project_cal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/calendar")
@Controller
public class CalendarController {

	@GetMapping
	public String calendarPage() {
		return "calendar/calendar";	
	}

}
