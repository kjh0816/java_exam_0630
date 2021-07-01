package com.sbs.exam.app.controller;

import java.util.Scanner;

import com.sbs.exam.app.Rq;
import com.sbs.exam.app.container.Container;
import com.sbs.exam.app.dto.Article;
import com.sbs.exam.app.dto.Like;
import com.sbs.exam.app.service.ArticleService;
import com.sbs.exam.app.service.LikeService;

public class UsrLikeController extends Controller {
	private ArticleService articleService;
	private LikeService likeService;
	private Scanner sc;
	public UsrLikeController() {
		articleService = Container.getArticleService();
		likeService = Container.getLikeService();
		sc = Container.getSc();
	}

	@Override
	public void performAction(Rq rq) {
		if (rq.getActionPath().equals("/usr/like/like")) {

			String relTypeCode = rq.getStrParam("relTypeCode", "");
			if (relTypeCode.isEmpty()) {
				System.out.println("좋아요 대상(relTypeCode)을 입력해주세요.");
				return;
			}
			if (relTypeCode.equals("article")) {
				actionLike(rq);
			} else {
				System.out.printf("%s는(은) 존재하지 않는 대상(relTypeCode)입니다.\n", relTypeCode);
				return;
			}

		} else if (rq.getActionPath().equals("/usr/like/cancleLike")) {

			String relTypeCode = rq.getStrParam("relTypeCode", "");
			if (relTypeCode.isEmpty()) {
				System.out.println("좋아요 대상(relTypeCode)을 입력해주세요.");
				return;
			}
			if (relTypeCode.equals("article")) {
				actionCancleLike(rq);
			} else {
				System.out.printf("%s는(은) 존재하지 않는 대상(relTypeCode)입니다.\n", relTypeCode);
				return;
			}
		} else if (rq.getActionPath().equals("/usr/like/dislike")) {

			String relTypeCode = rq.getStrParam("relTypeCode", "");
			if (relTypeCode.isEmpty()) {
				System.out.println("좋아요 대상(relTypeCode)을 입력해주세요.");
				return;
			}
			if (relTypeCode.equals("article")) {
				actionDislike(rq);
			} else {
				System.out.printf("%s는(은) 존재하지 않는 대상(relTypeCode)입니다.\n", relTypeCode);
				return;
			}
		} else if (rq.getActionPath().equals("/usr/like/cancleDislike")) {

			String relTypeCode = rq.getStrParam("relTypeCode", "");
			if (relTypeCode.isEmpty()) {
				System.out.println("좋아요 대상(relTypeCode)을 입력해주세요.");
				return;
			}
			if (relTypeCode.equals("article")) {
				actionCancleDislike(rq);
			} else {
				System.out.printf("%s는(은) 존재하지 않는 대상(relTypeCode)입니다.\n", relTypeCode);
				return;
			}
		}

	}

	private void actionLike(Rq rq) {
		int articleId = rq.getIntParam("id", 0);

		if (articleId == 0) {
			System.out.println("게시물 번호(id)를 입력해주세요.");
			return;
		}
		Article article = articleService.getArticleById(articleId);
		if (article == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다.\n", articleId);
			return;
		}
		int memberId = rq.getLoginedMember().getId();
		
		Like likeObj = likeService.getLikeObj(articleId, memberId);
		
		
		if (likeObj == null) {
//			객체가 생성되지 않은 경우
			
			int likeCount = 1;
			articleService.increaseArticleLike(true, 1, article);
			likeService.createLike(true, likeCount, memberId, articleId);
			System.out.printf("%d번 게시물에 좋아요를 눌렀습니다.\n", articleId);
			
		} else {
			if(likeObj.isArticleLike() && likeObj.isArticleDislike()) {
				System.out.println("좋아요와 싫어요를 동시에 누를 수 없습니다.");
				return;
			}
			
//			객체가 존재해서 확인했으나, 좋아요가 안 눌렸던 경우
			if (!likeObj.isArticleLike()) {
				int likeCount = 1;
				articleService.increaseArticleLike(true, 1, article);
				likeService.changeLike(true, likeCount, memberId, articleId);
				System.out.printf("%d번 게시물에 좋아요를 눌렀습니다.\n", articleId);

			} else {
//				객체가 존재해서 확인했으나, 좋아요가 이미 눌린 경우
				System.out.printf("%d번 게시물에 이미 좋아요를 눌렀습니다.\n", articleId);
				
			}
		}
	}

	private void actionCancleLike(Rq rq) {
		int articleId = rq.getIntParam("id", 0);

		if (articleId == 0) {
			System.out.println("게시물 번호(id)를 입력해주세요.");
			return;
		}
		Article article = articleService.getArticleById(articleId);
		if (article == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다.\n", articleId);
			return;
		}
		int memberId = rq.getLoginedMember().getId();
		Like likeObj = likeService.getLikeObj(articleId, memberId);
		if (likeObj == null) {
//			객체가 생성되지 않은 경우

			System.out.printf("%d번 게시물에 좋아요를 누른적이 없습니다.\n", articleId);
			return;
		} else {
			
			
//			객체가 존재해서 확인했으나, 좋아요가 안 눌렸던 경우
			if (likeObj.isArticleLike()) {
				int likeCount = -1;
				articleService.increaseArticleLike(true, -1, article);
				likeService.changeLike(false, likeCount, memberId, articleId);
				System.out.printf("%d번 게시물에 좋아요를 취소했습니다.\n", articleId);
			} else {
//				객체가 존재해서 확인했으나, 좋아요가 이미 눌린 경우
				System.out.printf("%d번 게시물에 좋아요를 누르지 않았습니다.\n", articleId);
				return;
			}
		}
	}

	private void actionDislike(Rq rq) {
		int articleId = rq.getIntParam("id", 0);

		if (articleId == 0) {
			System.out.println("게시물 번호(id)를 입력해주세요.");
			return;
		}
		Article article = articleService.getArticleById(articleId);
		if (article == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다.\n", articleId);
			return;
		}
		int memberId = rq.getLoginedMember().getId();
		Like likeObj = likeService.getLikeObj(articleId, memberId);
		if (likeObj == null) {
//			객체가 생성되지 않은 경우
			
			int likeCount = 1;
			articleService.increaseArticleLike(false, 1, article);
			likeService.createLike(false, likeCount, memberId, articleId);
			System.out.printf("%d번 게시물에 싫어요를 눌렀습니다.\n", articleId);

		} else {
			
			if(likeObj.isArticleLike() && likeObj.isArticleDislike()) {
				System.out.println("좋아요와 싫어요를 동시에 누를 수 없습니다.");
				return;
			}
//			객체가 존재해서 확인했으나, 좋아요가 안 눌렸던 경우
			if (!likeObj.isArticleDislike()) {
				System.out.println(likeObj.isArticleDislike());
				
				int likeCount = 1;
				articleService.increaseArticleLike(false, 1, article);
				likeService.changeDislike(true, likeCount, memberId, articleId);
				System.out.printf("%d번 게시물에 싫어요를 눌렀습니다.\n", articleId);

			} else {
//				객체가 존재해서 확인했으나, 좋아요가 이미 눌린 경우
				System.out.printf("%d번 게시물에 싫어요를 누르지 않았습니다.\n", articleId);
				return;
			}
		}
	}

	private void actionCancleDislike(Rq rq) {
		int articleId = rq.getIntParam("id", 0);

		if (articleId == 0) {
			System.out.println("게시물 번호(id)를 입력해주세요.");
			return;
		}
		Article article = articleService.getArticleById(articleId);
		if (article == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다.\n", articleId);
			return;
		}
		int memberId = rq.getLoginedMember().getId();
		Like likeObj = likeService.getLikeObj(articleId, memberId);
		if (likeObj == null) {
//			객체가 생성되지 않은 경우

			System.out.printf("%d번 게시물에 싫어요를 누른적이 없습니다.\n", articleId);
			return;
		} else {
//			객체가 존재해서 확인했으나, 좋아요가 안 눌렸던 경우
			if (likeObj.isArticleDislike()) {
				int likeCount = -1;
				articleService.increaseArticleLike(false, -1, article);
				likeService.changeDislike(false, likeCount, memberId, articleId);
				System.out.printf("%d번 게시물에 싫어요를 취소했습니다.\n", articleId);
			} else {
//				객체가 존재해서 확인했으나, 좋아요가 이미 눌린 경우
				System.out.printf("%d번 게시물에 이미 싫어요를 눌렀습니다.\n", articleId);
				return;
			}
		}
	}

}
