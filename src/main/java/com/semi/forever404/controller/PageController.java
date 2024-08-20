package com.semi.forever404.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
	@GetMapping("/")
	public String index() {
		return "index";
	}
	@GetMapping("/register")
	public String register() {
		return "register";
	}
	@GetMapping("/developer")
	public String developer() {
		return "developer";
	}
	@GetMapping("/main")
	public String main() {
		return "main";
	}
	@GetMapping("/kakaomap")
	public String kakaomap() {
		return "kakaomap2";
	}
	
	@GetMapping("/detail")
	public String detail() {
		return "detail2";
	}
	
}
