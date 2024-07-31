package com.semi.forever404.model.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class Photo {
	private int photoCode;
	private Date photoDate;
	private String location;
	private String id;
	private int albumCode;
}
