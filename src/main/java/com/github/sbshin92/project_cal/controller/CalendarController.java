package com.github.sbshin92.project_cal.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.github.sbshin92.project_cal.data.vo.ProjectVO;
import com.github.sbshin92.project_cal.data.vo.RoleVO;
import com.github.sbshin92.project_cal.data.vo.UserVO;
import com.github.sbshin92.project_cal.service.CalendarService;
import com.github.sbshin92.project_cal.service.RoleService;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/calendar")
@Controller
public class CalendarController {
	@Autowired
	private CalendarService calendarService;
	
	@GetMapping({"", "/"})
	public String calendarPage(HttpSession session, Model model) {
		// 년 월 일 초기화
		LocalDate localDate = LocalDate.now();
		if (session.getAttribute("viewYear") == null)
			session.setAttribute("viewYear", localDate.getYear());
		if (session.getAttribute("viewMonth") == null)
			session.setAttribute("viewMonth", localDate.getMonthValue());
		// 세션에 오늘 날짜 추가
		session.setAttribute("todayYear", localDate.getYear());
		session.setAttribute("todayMonth", localDate.getMonthValue());
		session.setAttribute("todayDate", localDate.getDayOfMonth() < 10 ? "0" + localDate.getDayOfMonth() : localDate.getDayOfMonth());
		// 권한 확인
		UserVO authUser = (UserVO)session.getAttribute("authUser");
		RoleVO authUserRole = (RoleVO)session.getAttribute("authUserRole");
		if (!authUserRole.getProjectRead())
			return "calendar/calendar";
		
		// 개인 스케쥴인지 확인
		if (session.getAttribute("mySchedule") == null) {
			session.setAttribute("mySchedule", "false");
		}
		
		
		// 개인일정 가져오기
		String mySchedule = (String)session.getAttribute("mySchedule");
		if ("true".equals(mySchedule)) {
			List<ProjectVO> monthLst = calendarService.getProjectListByMonthWithUserId((Integer)session.getAttribute("viewYear"), 
											(Integer)session.getAttribute("viewMonth"), 
											authUser.getUserId());
			model.addAttribute("projectListByMonth", monthLst);
		} else {
			List<ProjectVO> monthLst = calendarService.getProjectListByMonth((Integer)session.getAttribute("viewYear"), 
											(Integer)session.getAttribute("viewMonth"));
			model.addAttribute("projectListByMonth", monthLst);			
		}
		return "calendar/calendar";
	}
	
	@GetMapping("/date/{yearMonth}")
	public String calendarPageWithMonth(@PathVariable("yearMonth") String yearMonth, 
			HttpSession session) {		
		LocalDate today = LocalDate.now();
		try {
			int year = Integer.parseInt(yearMonth.substring(0, 4));
			int month = Integer.parseInt(yearMonth.substring(4));
			if (month > 12 || month < 1)
				throw new NumberFormatException();
			session.setAttribute("viewYear", year);
			session.setAttribute("viewMonth", month);
		} catch (NumberFormatException e) {
			session.setAttribute("viewYear", today.getYear());
			session.setAttribute("viewMonth", today.getMonthValue());						
		}
		return "redirect:/calendar";
	}
	
	@GetMapping("/date/{yearMonth}/{date}")
	public String calendarPageWithDateProjectList(@PathVariable("yearMonth") String yearMonth, 
											@PathVariable("date") Integer date,
											HttpSession session,
											RedirectAttributes redirectAttributes) {
		LocalDate today = LocalDate.now();
		try {
			int year = Integer.parseInt(yearMonth.substring(0, 4));
			int month = Integer.parseInt(yearMonth.substring(4));
			if (month > 12 || month < 1)
				throw new NumberFormatException();
			session.setAttribute("viewYear", year);
			session.setAttribute("viewMonth", month);
			
			// 권한 설정
			UserVO authUser = (UserVO)session.getAttribute("authUser");
			RoleVO authUserRole = (RoleVO)session.getAttribute("authUserRole");
			if (!authUserRole.getProjectRead())
				return "redirect:/calendar";
			
			// 개인일 때는 개인일정 목록만 나오게
			String mySchedule = (String)session.getAttribute("mySchedule");
			List<ProjectVO> lst = null;
			if ("true".equals(mySchedule)) {
				lst = calendarService.getProjectListByDateWithUserId(year, month, date, authUser.getUserId());
			} else {
				lst = calendarService.getProjectListByDate(year, month, date);
			}
			redirectAttributes.addFlashAttribute("viewDate", date);
			redirectAttributes.addFlashAttribute("projectListByDate", lst);
		} catch (NumberFormatException e) {
			session.setAttribute("viewYear", today.getYear());
			session.setAttribute("viewMonth", today.getMonthValue());						
		}
		return "redirect:/calendar";
	}
	
	@GetMapping("/scheduleSwitch")
	public String scheduleSwitcher(HttpSession session) {
		String mySchedule = (String)session.getAttribute("mySchedule");
		if ("true".equals(mySchedule))
			session.setAttribute("mySchedule", "false");
		else
			session.setAttribute("mySchedule", "true");
		return "redirect:/calendar";
	}
	
	
}
