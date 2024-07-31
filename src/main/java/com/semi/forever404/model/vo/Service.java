package com.semi.forever404.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class Service {
	private int serviceCode;
	private int useMoney;
	private int leftMoney;
	private String buyingList;
	private int siCode;
	private int ssCode;
}
