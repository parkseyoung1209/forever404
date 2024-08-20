package com.semi.forever404.model.vo;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class SmallSchedule {
	private String groupName;
	private int ssCode;
	private String memo;
	private char isReservation;
	private int useMoney;
	private int leftMoney;
	private String buyingList;
	private Date curDate;
	private LocalDateTime curTime;
	private BigSchedule bigSchedule;
	private ServiceInfo siCode;

	
	
	public SmallSchedule(String memo, char isReservation, BigSchedule bigSchedule) {
		this.memo = memo;
		this.isReservation = isReservation;
		this.bigSchedule = bigSchedule;
	}



	public SmallSchedule(String memo, char isReservation, int useMoney, int leftMoney, String buyingList, Date curDate,
			LocalDateTime curTime, BigSchedule bigSchedule, ServiceInfo siCode) {
		this.memo = memo;
		this.isReservation = isReservation;
		this.useMoney = useMoney;
		this.leftMoney = leftMoney;
		this.buyingList = buyingList;
		this.curDate = curDate;
		this.curTime = curTime;
		this.bigSchedule = bigSchedule;
		this.siCode = siCode;
	}





}
