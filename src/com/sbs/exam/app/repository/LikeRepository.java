package com.sbs.exam.app.repository;

import java.util.ArrayList;
import java.util.List;

import com.sbs.exam.app.dto.Like;


public class LikeRepository {
	private List<Like> likes;
	private int lastId;

	public LikeRepository() {
		likes = new ArrayList<>();
		lastId = 0;
	}

	public Like getLikeObj(int articleId, int memberId) {
			
		for(Like likeObj : likes) {
			
			
			if(likeObj.getArticleId() == articleId && likeObj.getMemberId() == memberId) {
				return likeObj;
			}
		}
		return null;
	}

	public void changeLike(boolean like, int likeCount, int memberId, int articleId) {
		for(Like likeObj : likes) {
			if(likeObj.getArticleId() == articleId && likeObj.getMemberId() == memberId) {
				likeObj.setLikeCount(likeObj.getLikeCount() + likeCount);
				likeObj.setArticleLike(like);
				if(likeObj.getLikeCount() < 0) {
					likeObj.setLikeCount(0);
				}
			}
		}
		
	}

	public void createLike(boolean likeOrDislike, int likeOrDislikeCount, int memberId, int articleId) {
		
		if(likeOrDislike) {
//			좋아요인 경우

//			private int id;
//			private String relTypeCode;
//			private int memberId;
//			private int articleId;
//			private boolean articleLike;
//			private boolean articleDislike;
//			private int likeCount;
//			private int dislikeCount;

			int id = ++lastId;
			String relTypeCode = "article";
			Like newLikeObj = new Like(id, relTypeCode, memberId, articleId, likeOrDislike, false, likeOrDislikeCount, 0);
			likes.add(newLikeObj);
		}else {
//			싫어요인 경우
			
			int id = ++lastId;
			String relTypeCode = "article";
			Like newLikeObj = new Like(id, relTypeCode, memberId, articleId, false, true, 0, likeOrDislikeCount);
			likes.add(newLikeObj);
			System.out.println(newLikeObj.toString());
		}
		
	}

	public void changeDislike(boolean likeOrDislike, int likeCount, int memberId, int articleId) {
		for(Like likeObj : likes) {
			if(likeObj.getArticleId() == articleId && likeObj.getMemberId() == memberId) {
				likeObj.setLikeCount(likeObj.getLikeCount() + likeCount);
				likeObj.setArticleDislike(likeOrDislike);
				if(likeObj.getDislikeCount() < 0) {
					likeObj.setLikeCount(0);
				}
			}
		}
		
	}

	

	

		

		
		
		

		
	
}
