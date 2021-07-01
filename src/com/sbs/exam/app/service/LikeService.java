package com.sbs.exam.app.service;

import com.sbs.exam.app.container.Container;
import com.sbs.exam.app.dto.Like;
import com.sbs.exam.app.repository.LikeRepository;

public class LikeService {
	private LikeRepository likeRepository;

	public LikeService() {
		likeRepository = Container.getLikeRepository();
	}

	public Like getLikeObj(int articleId, int memberId) {
		return likeRepository.getLikeObj(articleId, memberId);
	}

	public void changeLike(boolean like, int likeCount,  int memberId, int articleId) {
		likeRepository.changeLike(like, likeCount, memberId, articleId);
		
	}

	public void createLike(boolean likeOrDislike, int likeOrDislikeCount, int memberId, int articleId) {
		likeRepository.createLike(likeOrDislike, likeOrDislikeCount, memberId, articleId);
		
	}

	public void changeDislike(boolean likeOrDislike, int likeCount, int memberId, int articleId) {
		likeRepository.changeDislike(likeOrDislike, likeCount, memberId, articleId);
		
	}

	

	
}
