package com.semi.forever404.controller;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.ResponseBody;

import com.semi.forever404.model.vo.BigGroup;
import com.semi.forever404.model.vo.BigSchedule;
import com.semi.forever404.model.vo.Money;
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
			if(!bigGroup.getGroupName().equals("") && !bigGroup.getGroupName().contains(" ")) {
				service.addGroup(bigGroup);
				BigGroup bg = service.searchBgCode(groupName);
				String id =user.getId();
				int bgGroupCode = bg.getBgGroupCode();
				SmallGroup smGroup = new SmallGroup(new User(id), new BigGroup(bgGroupCode));
				service.addSmGroup(smGroup);
				return true;
			} else return false;

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
	
	// 그룹 참여
	@ResponseBody
	@PostMapping("/attendGroup")
	public boolean attendGroup(HttpServletRequest request, String groupName) {
		BigGroup bg = service.searchBgCode(groupName);
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		int check = 0;
		List<SmallGroup> list = service.selectSmallGroup(user.getId());
		if(bg != null) {
			for(SmallGroup sm : list) {
				if(sm.getBigGroup().getBgGroupCode() == bg.getBgGroupCode()) {
					check = 1;
					break;
				} 
			}
			if(check == 1) {
				return false;
			} else {
				SmallGroup sg = new SmallGroup(user, bg);
				service.addSmGroup(sg);
				return true;
			} 
		} else {
			return false;
		}
	}
	// 그룹 삭제
	@ResponseBody
	@PostMapping("/deleteGroup")
	public void deleteGroup(String groupName) {
		BigGroup bg = service.searchBgCode(groupName);
		int bgCode = bg.getBgGroupCode();
		
		List<BigSchedule> bs = service.searchBsCode(bgCode);
		for(int i=0; i<bs.size(); i++) {
			int bsCode = bs.get(i).getBsCode();
			List<SmallSchedule> smallSchedule = service.selectOneSc(bsCode);
			for(int j=0; j<smallSchedule.size(); j++) {
				// 스몰스케쥴 ss_code로 money테이블 조회해서 삭제하고
				service.deleteGroup1(smallSchedule.get(j).getSsCode()); // money 테이블
			}
			service.deleteGroup2(bsCode);
		}
		service.deleteGroup3(bgCode);
		service.deleteGroup4(bgCode);
		service.deleteGroup5(bgCode);
	}
	
	
	@ResponseBody
	@PostMapping("/scheduleAdd")
	public boolean schduleAdd(HttpServletRequest request, BigSchedule bigSchedule, Model model) throws ParseException {
		String groupName = request.getHeader("referer").substring(22);
		BigGroup bg = service.searchBgCode(groupName);
		int num = bg.getBgGroupCode();
		List<BigSchedule> bs = service.searchBsCode(num);
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		bigSchedule.setBigGroup(bg);
		bigSchedule.setUser(user);
		
		// 컬러 추가

		int rand1 = (int)(Math.random()*256);
		int rand2 = (int)(Math.random()*256);
		int rand3 = (int)(Math.random()*256);
		
		String color = "rgba(" + rand1 + "," + rand2 + "," + rand3 + ", 0.5)";
		bigSchedule.setScheduleColor(color);
		
		
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
			return false;
		} else {
			service.scheduleAdd(bigSchedule);
			return true;
		}

	}
	
	@ResponseBody
	@PostMapping("/scheduleAdd2")
	public SmallSchedule scheduleAdd2(HttpServletRequest request, SmallSchedule smallSchedule, int bsCode, String curDate)  {
	
		String url;
		try {
			url = crawling.getImgUrl(smallSchedule.getServiceName());
			smallSchedule.setServiceImg(url);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		smallSchedule.setBigSchedule(service.selectOneBs(bsCode));
			
//		CurDate 값 수정 필요(front한테 받기)
		smallSchedule.setCurDate(curDate);
		
		service.scheduleAdd2(smallSchedule);
		
		return smallSchedule;
//		#{memo} o, #{isReservation} o, #{cur_date}x, #{cur_time}x, #{use_money}x, #{left_money}x, #{buying_list}, #{bigSchedule.bsCode}, #{serviceName}, 
//		#{serviceJibun}, #{serviceLat},#{serviceLng},#{servicePhone},#{serviceImg}
	}

	@ResponseBody
	@PostMapping("/insertMoney")
	public void insertMoney(Money money, String curDate) {
//		List<SmallSchedule> list = service.curDateSchedule(curDate);
//		SmallSchedule sm = new SmallSchedule();
//		sm.setSsCode(ssCode);
//		money.setSmallSchedule(sm);
		service.insertMoney(money);
	}
}
