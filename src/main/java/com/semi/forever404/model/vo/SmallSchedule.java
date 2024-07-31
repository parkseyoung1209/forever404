package com.semi.forever404.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class SmallSchedule {
	private int ssCode;
	private String memo;
	private String items;
	private char isReservation;
	private int bsCode;
}
