package com.semi.forever404.model.dto;

import java.util.List;

import com.semi.forever404.model.vo.Money;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MoneyDTO {
	private int ssCode;
	List<Money> list;
}
