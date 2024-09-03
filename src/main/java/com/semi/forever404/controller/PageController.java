package com.semi.forever404.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.semi.forever404.model.dto.CalendarDTO;
import com.semi.forever404.model.dto.MoneyDTO;
import com.semi.forever404.model.vo.BigGroup;
import com.semi.forever404.model.vo.BigSchedule;
import com.semi.forever404.model.vo.Money;
import com.semi.forever404.model.vo.Photo;
import com.semi.forever404.model.vo.SmallGroup;
import com.semi.forever404.model.vo.SmallSchedule;
import com.semi.forever404.model.vo.Tip;
import com.semi.forever404.model.vo.User;
import com.semi.forever404.service.GroupService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class PageController {
	@Autowired
	private GroupService service;

	
	@GetMapping("/")
	public String index(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if(user!=null) return "movement";
		else return "index";
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
	public String main(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if(user!=null) {
		List<SmallGroup> list = service.selectSmallGroup(user.getId());
		// 한 그룹의 여러 회원
//		
		session.setAttribute("smlist", list);
//		session.setAttribute("userList", userList);
		
		if(list.isEmpty()) {
			session.setAttribute("check", false);
		} else {
			session.setAttribute("check", true);
		}
		}
		return "main";
	}
	
	@GetMapping("/kakao/map")
	public String kakaomap(HttpServletRequest request) {
		HttpSession session = request.getSession();
		int random = (int)(Math.random()*44);
		List<Tip> list = service.tip();
		
		session.setAttribute("tip", list.get(random).getTip());
		System.out.println(session.getAttribute("tip"));
		return "kakaomap2";
	}
	
	@GetMapping("/{groupName}")
	public String select(@PathVariable("groupName") String groupName, BigSchedule bigSchedule, HttpServletRequest request, Model model) {
		
		if(!groupName.equals("favicon.ico")) {
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
			
			BigGroup bg = service.searchBgCode(groupName);
			List<SmallGroup> userList = service.selectSmallGroup2(bg.getBgGroupCode());
			session.setAttribute("userList", userList);
			bigSchedule.setBigGroup(bg);
			List<BigSchedule> bsList = service.selectBg(bigSchedule);
			model.addAttribute("bsList", bsList);
			//System.out.println("86 : " + groupName);
			
			if(user!=null) return "main";
			else if(user==null) return "redirect:/";
			else return null;
		}
		return null;
	}
	
	@GetMapping("/{groupName}/detail")
	public String detail(@PathVariable String groupName, @RequestParam int bsCode, HttpServletRequest request, Model model) {
	
		HttpSession session = request.getSession();

		List<SmallSchedule> smallSchedule = service.selectOneSc(bsCode);
		BigSchedule tmp = service.selectOneBs(bsCode);
		
		String startDate = tmp.getStartDate();
		String endDate = tmp.getEndDate();
		
		LocalDate startDate2 = LocalDate.parse(startDate);
		LocalDate endDate2 = LocalDate.parse(endDate);
		List<CalendarDTO> list = new ArrayList<CalendarDTO>(); // 최종 리스트
		List<LocalDate> dateRange = getDateRange(startDate2, endDate2);
		List<String> stringDates = dateRange.stream().map(LocalDate::toString).collect(Collectors.toList());
		List<SmallSchedule> addList = new ArrayList<SmallSchedule>();
		for(int i=0; i<dateRange.size(); i++) {
			addList = service.curDateSchedule(new SmallSchedule(stringDates.get(i), tmp));
			list.add(new CalendarDTO(stringDates.get(i), addList));
		}
		List<MoneyDTO> MoneyList = new ArrayList<MoneyDTO>();
		if(!smallSchedule.isEmpty()) {
			for(int i=0; i<smallSchedule.size(); i++) {
				MoneyList.add(new MoneyDTO(smallSchedule.get(i).getSsCode(), service.selectMoney(smallSchedule.get(i).getSsCode())));
				session.setAttribute("moneyL", MoneyList);
			}
		}
		session.setAttribute("selectSRange", stringDates);

		session.setAttribute("selectS", smallSchedule);
		session.setAttribute("totalList", list);
	
		
		if(session.getAttribute("user")!=null) return "detail2";
		else return "redirect:/";
	}
	
	public List<LocalDate> getDateRange(LocalDate startDate, LocalDate endDate) {
		List<LocalDate> dates = new ArrayList<>();
		LocalDate currentDate = startDate;

		// 날짜 목록에 포함할 날짜가 끝 날짜보다 이전이거나 같을 때까지 반복
		while (!currentDate.isAfter(endDate)) {
			dates.add(currentDate);
			currentDate = currentDate.plusDays(1); // 하루씩 더하기
		}

		return dates;
	}
	@PostMapping("/mola")
    @ResponseBody
    public Map<String, Object> processGroupName(@RequestBody Map<String, String> requestData) {
        String groupName = requestData.get("groupName");
    
        // 응답 데이터 준비
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("groupName", groupName);
        return response;
    }
	
	@ResponseBody
	@PostMapping("/selectMyImg")
	public List<Photo> photoList(HttpServletRequest request, int bsCode) {
		List<Photo> photoList = service.selectMyImg(bsCode);
		HttpSession session = request.getSession();
		
		session.setAttribute("photoL", photoList);
		return photoList;
	}
}
