package com.sbs.exam.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Article extends Object{
	public int id;
	private String regDate;
	private String updateDate;
	public int memberId;
	public int boardId;
	public int hitCount;  // 조회수
	public int like;
	public int dislike;
	private String title;
	private String body;
	private String keywordsStr;
}
