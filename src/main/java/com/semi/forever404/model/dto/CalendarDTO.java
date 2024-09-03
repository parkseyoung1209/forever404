package com.semi.forever404.model.dto;

import java.util.List;
import com.semi.forever404.model.vo.SmallSchedule;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class CalendarDTO {
	private String curDate;
	List<MoneyDTO> list;
}

// model에 담을 때 List<CalendarDTO> <--- 이렇게 보내줘!
