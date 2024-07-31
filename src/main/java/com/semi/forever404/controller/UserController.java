package com.semi.forever404.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.semi.forever404.model.vo.User;
import com.semi.forever404.service.UserService;

@Controller
public class UserController {
	private UserService service;
	
	@GetMapping("/register")
	public String register() {
		return "";
	}
	@PostMapping("/register")
	public String register(User vo) {
		service.register(vo);
		return "redirect:/";
	}
}
