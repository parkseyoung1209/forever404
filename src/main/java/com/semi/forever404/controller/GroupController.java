package com.semi.forever404.controller;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
			System.out.println(list);
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

//		추가하기전에 BigSchedule 리스트를 일단 가져오고
//		내가 추가버튼을 누르는 순간 가져온 BigSchedule 객체 하나만 가지고 비교
		
		/*
		 * count = 0;
		  for(element : list) {
		    if(시작, 끝 날짜가 list의 시작날짜보다 작을 때){
		      continue; 하면은 for문이 처음으로 돌아가요 => 그 다음 리스트를 부르기 시작
		    } else if(시작, 끝 날짜가 list의 끝 날짜보다 클 때) {
		      continue; 하면은 for문이 처음으로 돌아가요 => 그 다음 리스트를 부르기 시작
		    } else {
		      count++;
		    }
		  }
		
		  if(count >= 1)
		  print("추가안됨");
		  추가안함
		  return redirect:/groupName
		  else if (count = 0)
		  service.scheduleAdd(bigSchedule);
		  추가
		  return redirect:/groupName
		 */
	}
	
	// 메서드
	public void selectAllSchedule(String groupName) {
		BigGroup bg = service.searchBgCode(groupName);
		int num = bg.getBgGroupCode();
		List<BigSchedule> bs = service.searchBsCode(num);
	}
	
	@ResponseBody
	@PostMapping("/scheduleAdd2")
	public void scheduleAdd2(HttpServletRequest request, SmallSchedule smallSchedule, String groupName) throws InterruptedException {
		BigGroup bg = service.searchBgCode(groupName);
		int num = bg.getBgGroupCode();
//		#{memo} o, #{isReservation} o, #{cur_date}x, #{cur_time}x, #{use_money}x, #{left_money}x, #{buying_list}, #{bigSchedule.bsCode}, #{serviceName}, 
//		#{serviceJibun}, #{serviceLat},#{serviceLng},#{servicePhone},#{serviceImg}
//		String url = crawling.getImgUrl(smallSchedule.getServiceName());
//		Thread.sleep(10000);
//		smallSchedule.setServiceImg(url);
	}

}
