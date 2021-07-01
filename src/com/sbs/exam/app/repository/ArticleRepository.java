package com.sbs.exam.app.repository;

import java.util.ArrayList;
import java.util.List;

import com.sbs.exam.app.dto.Article;
import com.sbs.exam.util.Util;

public class ArticleRepository {
	private List<Article> articles;
	private int lastId;

	public ArticleRepository() {
		articles = new ArrayList<>();
		lastId = 0;
	}

	public int write(String title, String body, int memberId, int boardId, int hitCount, int like, int dislike) {
		
		++lastId;
		int id = lastId;
		
		String regDate = Util.getNowDateStr();
		String updateDate = Util.getNowDateStr();
		String keywordsStr = "";
		if(body.length() > 9) {
			keywordsStr = (Util.getKeywordsStrFromStr(body)).trim();
		}
		
				
		
		

		Article article = new Article(id, regDate, updateDate, memberId, boardId, hitCount, like, dislike, title, body, keywordsStr);
		articles.add(article);


		return id;
	}

	public void deleteArticleById(int id) {
		Article article = getArticleById(id);
		
		if ( article != null ) {
			articles.remove(article);
		}
	}

	public Article getArticleById(int id) {
		for ( Article article : articles ) {
			if ( article.getId() == id ) {
				return article;
			}
		}
		
		return null;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void changeLikeCount(boolean likeOrDislike, int likeCount, int articleId) {
		if(likeOrDislike) {
//			좋아요에 대한 카운트 증가

			Article article = getArticleById(articleId);
			article.setLike(article.getLike() + likeCount);
		}else {
//			싫어요에 대한 카운트 증가
			
			Article article = getArticleById(articleId);
			article.setDislike(article.getDislike() + likeCount);
		}
		
	}

	public void increaseArticleHitCount(Article article) {
		article.setHitCount(article.getHitCount() + 1);
	}

	public void increaseArticleLike(boolean likeOrDislike, int count, Article article) {
		
		if(likeOrDislike) {
			article.setLike(article.getLike() + count);
		}else {
			article.setDislike(article.getDislike() + count);
		}
		
		
	}

	public void modify(String title, String body, Article article) {
		
		String keywordsStr = "";
		if(body.length() > 9) {
			keywordsStr = (Util.getKeywordsStrFromStr(body)).trim();
		}
		
		article.setTitle(title);
		article.setBody(body);
		article.setKeywordsStr(keywordsStr);
		article.setUpdateDate(Util.getNowDateStr());
		
	}

}
