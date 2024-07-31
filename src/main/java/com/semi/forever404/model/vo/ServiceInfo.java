package com.semi.forever404.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class ServiceInfo {
	private int siCode;
	private String serviceName;
	private String serviceLocation;
	private String openClosed;
	private int serviceLike;
	private String serviceList;
}
