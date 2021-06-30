package com.sbs.exam.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Article extends Object {
	private int id;
	private String relTypeCode; // =article
	private String regDate;
	private String updateDate;
	private String keywordStr;
	private int boardId;
	private int memberId;
	private int hitCount;  // 조회수
	private String title;
	private String body;
}
