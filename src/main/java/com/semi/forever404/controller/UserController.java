package com.semi.forever404.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.semi.forever404.model.vo.User;
import com.semi.forever404.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService service;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	@PostMapping("/register")
	public String register(User vo) {
		service.register(vo);
		return "redirect:/";
	}
}
