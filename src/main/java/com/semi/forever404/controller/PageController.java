package com.semi.forever404.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.semi.forever404.model.vo.BigGroup;
import com.semi.forever404.model.vo.BigSchedule;
import com.semi.forever404.model.vo.User;
import com.semi.forever404.service.GroupService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class PageController {
	@Autowired
	private GroupService service;
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
	@GetMapping("/movement")
	public String movement() {
		return "movement";
	}
	
	@GetMapping("/main")
	public String main() {
		return "main";
	}
	@GetMapping("/kakaomap")
	public String kakaomap() {
		return "kakaomap2";
	}
	
	@GetMapping("/{groupName}")
	public String select(@PathVariable("groupName") String groupName, BigSchedule bigSchedule, HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		BigGroup bg = service.searchBgCode(groupName);
		bigSchedule.setBigGroup(bg);
		List<BigSchedule> bsList = service.selectBg(bigSchedule);
		model.addAttribute("bsList", bsList);
		session.setAttribute("groupName", groupName);
		return "main";
	}
	
	@GetMapping("/{groupName}/detail")
	public String detail(@PathVariable("groupName") String groupName, int bsCode) {
		System.out.println("groupName : " + groupName);
		System.out.println("bsCode : " + bsCode);
		return "detail2";
	}
		
}
