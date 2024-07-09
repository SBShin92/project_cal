package com.github.sbshin92.project_cal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

	@GetMapping
	public String mainPage() {
		return "home";
	}

}
