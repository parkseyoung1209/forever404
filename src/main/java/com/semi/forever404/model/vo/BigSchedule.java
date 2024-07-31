package com.semi.forever404.model.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class BigSchedule {
	private int bsCode;
	private int bgGroupCode;
	private String id;
	private Date startDate;
	private Date endDate;
	private String bigLocation;
	private int entireMoney;
	private String scheduleColor;
}
