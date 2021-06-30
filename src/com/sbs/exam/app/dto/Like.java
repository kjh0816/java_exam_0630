package com.sbs.exam.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Like extends Object{
	private int id;
	private int memberId;
	private int articleId;
	private boolean articleLike;
	private boolean articleDislike;
	private int likeCount;
	private int dislikeCount;
	

}
