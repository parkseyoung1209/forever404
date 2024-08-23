package com.semi.forever404.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class ScheduleController {
	
	@ResponseBody
	@PostMapping("/dateInfo")
	public void dateInfo(HttpServletRequest request) {
		HttpSession session = request.getSession();
		
	}
}
