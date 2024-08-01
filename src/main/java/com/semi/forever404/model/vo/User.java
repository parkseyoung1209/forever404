package com.semi.forever404.model.vo;



import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class User {
	private String id;
	private String password;
	private String phone;
	private String name;
	private String email;
	private Date birth;
	
	
	
	public User(String id, String password, String name, String email, String phone) {
		super();
		this.id=id;
		this.password=password;
		this.name=name;
		this.phone=phone;
		this.email=email;
	}
	
}
