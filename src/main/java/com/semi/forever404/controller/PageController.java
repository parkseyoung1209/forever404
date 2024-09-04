package com.semi.forever404.controller;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.openqa.selenium.html5.SessionStorage;
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

		List<CalendarDTO> list = new ArrayList<CalendarDTO>(); // 최종 리스트
		List<SmallSchedule> smallSchedule = service.selectOneSc(bsCode);
		
		BigSchedule tmp = service.selectOneBs(bsCode);
		
		String startDate = tmp.getStartDate();
		String endDate = tmp.getEndDate();
		
		LocalDate startDate2 = LocalDate.parse(startDate);
		LocalDate endDate2 = LocalDate.parse(endDate);
		
		List<LocalDate> dateRange = getDateRange(startDate2, endDate2);
		List<String> stringDates = dateRange.stream().map(LocalDate::toString).collect(Collectors.toList());
		
		for(int i=0; i<dateRange.size(); i++) {
			List<MoneyDTO> addList = new ArrayList<>();
			for(SmallSchedule ss : smallSchedule) {
				if(ss.getCurDate().equals(stringDates.get(i))) {
					addList.add(new MoneyDTO(ss, service.selectMoney(smallSchedule.get(i).getSsCode())));
				}
			}
			list.add(new CalendarDTO(stringDates.get(i), addList));
		}
		
	
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
	

	@ResponseBody
	@PostMapping("/deletePhoto")
	public void deletePhoto(String photoUrl, int photoCode) {
		//System.out.println("URL : " + photoUrl);
		System.out.println("code : " + photoCode);
		String url = photoUrl.replace("http://192.168.10.28:8080/storage/","\\\\192.168.10.28\\forever404\\storage\\");
		System.out.println("storage : " + url);
		if(photoUrl!=null) {
			File file = new File (url);
			file.delete();
		}
		
		service.deleteImg(photoCode);
		
	}
	
	// 세부스케줄 삭제
	@ResponseBody
	@GetMapping("/deleteSc")
	public void deleteSc(int ssCode) {
		//System.out.println(ssCode);
		
		service.deleteGroup1(ssCode);
		service.deleteSc(ssCode);
	}
	
	
}
