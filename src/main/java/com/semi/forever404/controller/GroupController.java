package com.semi.forever404.controller;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.ResponseBody;

import com.semi.forever404.model.vo.BigGroup;
import com.semi.forever404.model.vo.BigSchedule;
import com.semi.forever404.model.vo.SmallGroup;
import com.semi.forever404.model.vo.SmallSchedule;
import com.semi.forever404.model.vo.User;
import com.semi.forever404.service.GroupService;
import com.semi.forever404.service.ServiceService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class GroupController {
	@Autowired
	private GroupService service;
	
	@Autowired
	private ServiceService crawling;
	
	@ResponseBody
	@PostMapping("/addGroup")
	public boolean addGroup(BigGroup bigGroup, String groupName, HttpServletRequest request) {
			List<BigGroup> list = service.userGroup();
			for(BigGroup bg : list) {
				if(groupName.equals(bg.getGroupName()) && bg!=null) {
					return false;
				}
			}
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
			service.addGroup(bigGroup);
			BigGroup bg = service.searchBgCode(groupName);
			String id =user.getId();
			int bgGroupCode = bg.getBgGroupCode();
			SmallGroup smGroup = new SmallGroup(new User(id), new BigGroup(bgGroupCode));
			service.addSmGroup(smGroup);
			return true;
	}
	
	@ResponseBody
	@PostMapping("/userGroup")
	public List<SmallGroup> userGroup(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		String id =user.getId();
		List<SmallGroup> list = service.allInfoGroup(id);
		model.addAttribute("list", list);
		return list;
	}
	
	
	@ResponseBody
	@PostMapping("/scheduleAdd")
	public void schduleAdd(HttpServletRequest request, BigSchedule bigSchedule, Model model) throws ParseException {	
		String groupName = request.getHeader("referer").substring(22);
		
		BigGroup bg = service.searchBgCode(groupName);
		int num = bg.getBgGroupCode();
		List<BigSchedule> bs = service.searchBsCode(num);
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		String id = user.getId();
		bigSchedule.setBigGroup(bg);
		bigSchedule.setUser(user);
		

		String addStartDate = bigSchedule.getStartDate();
		String addEndDate = bigSchedule.getEndDate();
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		Date adStartDate = new Date(dateFormat.parse(addStartDate).getTime());
		Date adEndDate = new Date(dateFormat.parse(addEndDate).getTime());
		
		int count = 0;
		
		for(int i = 0; i<bs.size(); i++) {
			String originalStartDate = bs.get(i).getStartDate();
			String originalEndDate = bs.get(i).getEndDate();
			
			Date ogStartDate = new Date(dateFormat.parse(originalStartDate).getTime());
			Date ogEndDate = new Date(dateFormat.parse(originalEndDate).getTime());
		
			int compareStart1 = adStartDate.compareTo(ogStartDate);
			int compareStart2 = adEndDate.compareTo(ogStartDate);
			
			int compareEnd1 = adStartDate.compareTo(ogEndDate);
			int compareEnd2 = adEndDate.compareTo(ogEndDate);
			
			if((compareStart1 < 0) && (compareStart2 < 0)) {
				
			} else if((compareEnd1 > 0) && (compareEnd2 > 0)) {
				
			} else if((compareStart1 == 0) || (compareStart2 == 0)) {
				count++;
			} else if((compareEnd1 ==0 ) || (compareEnd2 == 0)) {
				count++;
			} else {
				count++;
			}
		}
		
		if(count >= 1) {
			System.out.println("추가 불가");
		} else {
			System.out.println("추가 성공");
			service.scheduleAdd(bigSchedule);
		}

	}
	
	@ResponseBody
	@PostMapping("/mola")
	public BigSchedule mola(String groupName, String localDate, HttpServletRequest request) throws ParseException {
		BigSchedule selectB = selectAllSchedule(groupName, localDate);
		HttpSession session = request.getSession();
		session.setAttribute("selectB",selectB);
//		System.out.println(selectB);
		return selectB;
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
	
	// 메서드
	public BigSchedule selectAllSchedule(String groupName, String localDate) throws ParseException {
		BigGroup bg = service.searchBgCode(groupName);
		int num = bg.getBgGroupCode();
		List<BigSchedule> bs = service.searchBsCode(num);
		
		for(BigSchedule b : bs) {
			String startDate = b.getStartDate();
			String endDate = b.getEndDate();
			
			LocalDate startDate2 = LocalDate.parse(startDate);
	        LocalDate endDate2 = LocalDate.parse(endDate);
	        LocalDate localDate2 = LocalDate.parse(localDate);
	        
	        List<LocalDate> dateRange = getDateRange(startDate2,endDate2);
	        
	        for(LocalDate d : dateRange) {
	        	if(d.equals(localDate2)) {
	        		return b;
	        	}
	        }
		}
		return null;
	}
	
	@ResponseBody
	@PostMapping("/scheduleAdd2")
	public void scheduleAdd2(HttpServletRequest request, SmallSchedule smallSchedule)  {
		HttpSession session = request.getSession();
		BigSchedule bigSchedule = (BigSchedule)session.getAttribute("selectB");
		
		String url;
//		try {
//			url = crawling.getImgUrl(smallSchedule.getServiceName());
//			smallSchedule.setServiceImg(url);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		smallSchedule.setBigSchedule(bigSchedule);
		smallSchedule.setCurDate("2024-08-22");
		System.out.println(smallSchedule);
		service.scheduleAdd2(smallSchedule);
//		#{memo} o, #{isReservation} o, #{cur_date}x, #{cur_time}x, #{use_money}x, #{left_money}x, #{buying_list}, #{bigSchedule.bsCode}, #{serviceName}, 
//		#{serviceJibun}, #{serviceLat},#{serviceLng},#{servicePhone},#{serviceImg}
	
//		Thread.sleep(10000);
		
	}

}
