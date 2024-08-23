package com.semi.forever404.model.vo;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class SmallSchedule {
	private int ssCode;
	private String memo;
	private char isReservation;
	private int useMoney;
	private int leftMoney;
	private String buyingList;
	private String curDate;
	private String curTime;
	private BigSchedule bigSchedule;
	private String serviceName;
	private String serviceJibun;
	private Double serviceLat;
	private Double serviceLng;
	private String servicePhone;
	private String serviceImg;
	
	public SmallSchedule(String memo, char isReservation, int useMoney, int leftMoney, String buyingList,
			String curDate, String curTime) {
		this.memo = memo;
		this.isReservation = isReservation;
		this.useMoney = useMoney;
		this.leftMoney = leftMoney;
		this.buyingList = buyingList;
		this.curDate = curDate;
		this.curTime = curTime;
	}
	
	public SmallSchedule(BigSchedule bigSchedule) {
		this.bigSchedule = bigSchedule;
	}
	
	public SmallSchedule(String serviceName, String serviceJibun, Double serviceLat, Double serviceLng,
			String servicePhone, String serviceImg) {
		this.serviceName = serviceName;
		this.serviceJibun = serviceJibun;
		this.serviceLat = serviceLat;
		this.serviceLng = serviceLng;
		this.servicePhone = servicePhone;
		this.serviceImg = serviceImg;
	}
	
}
