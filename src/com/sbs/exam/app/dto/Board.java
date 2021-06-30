package com.sbs.exam.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Board extends Object{
	private int id;
	private String regDate;
	private String updateDate;
	private int memberId;
	private String name;
	private String code;

}
