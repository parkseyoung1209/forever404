package com.semi.forever404;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("mapper") // MyBatis 매퍼 인터페이스 위치 패키지
public class Forever404Application {

	public static void main(String[] args) {
		SpringApplication.run(Forever404Application.class, args);
	}

}
