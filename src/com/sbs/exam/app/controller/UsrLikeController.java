package com.sbs.exam.app.controller;

import java.util.Scanner;

import com.sbs.exam.app.Rq;
import com.sbs.exam.app.container.Container;
import com.sbs.exam.app.dto.Like;
import com.sbs.exam.app.service.ArticleService;
import com.sbs.exam.app.service.LikeService;

public class UsrLikeController extends Controller {
	private ArticleService articleService;
	private LikeService likeService;
	private Scanner sc;

	public UsrLikeController() {
		articleService = Container.getArticleService();
		sc = Container.getSc();
	}

	@Override
	public void performAction(Rq rq) {
		if (rq.getActionPath().equals("/usr/like/like")) {
			actionLike(rq);

		} else if (rq.getActionPath().equals("/usr/article/cancleLike")) {
			actionCancleLike(rq);
		} else if (rq.getActionPath().equals("/usr/article/dislike")) {
			actionDislike(rq);
		} else if (rq.getActionPath().equals("/usr/article/cancleLike")) {
			actionCancleDislike(rq);
		}

	}

	private void actionLike(Rq rq) {
		int id = rq.getIntParam("id", 0);
		String relTypeCode = rq.getStrParam("relTypeCode", "");
		if(id == 0 || relTypeCode.isEmpty()) {
			System.out.println("relTypeCode와 게시물 번호(id)를 입력해주세요.");
		}
		
		
	}

	private void actionCancleLike(Rq rq) {

	}

	private void actionDislike(Rq rq) {

	}

	private void actionCancleDislike(Rq rq) {

	}

}
