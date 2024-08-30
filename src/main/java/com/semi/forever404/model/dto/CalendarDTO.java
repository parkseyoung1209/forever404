package com.semi.forever404.model.dto;

import java.util.List;

import com.semi.forever404.model.vo.BigSchedule;
import com.semi.forever404.model.vo.SmallSchedule;

public class CalendarDTO {

	private String curDate;
	List<SmallSchedule> list;
	
}

// model에 담을 때 List<CalendarDTO> <--- 이렇게 보내줘!
