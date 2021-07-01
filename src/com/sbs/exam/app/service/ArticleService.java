package com.sbs.exam.app.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.sbs.exam.app.container.Container;
import com.sbs.exam.app.dto.Article;
import com.sbs.exam.app.repository.ArticleRepository;

public class ArticleService {
	private ArticleRepository articleRepository;

	public ArticleService() {
		articleRepository = Container.getArticleRepository();
	}

	public int write(String title, String body, int memberId, int boardId, int hitCount, int like, int dislike) {
		return articleRepository.write(title, body, memberId, boardId, hitCount, like, dislike);
	}

	public Article getArticleById(int id) {
		return articleRepository.getArticleById(id);
	}

	public void deleteArticleById(int id) {
		articleRepository.deleteArticleById(id);
	}

	public List<Article> getArticles() {
		return articleRepository.getArticles();
	}

	public void makeTestData() {
		for (int i = 0; i < 99; i++) {
			String title = "제목 " + (i + 1);
			String body = "내용 " + (i + 1);
			int ran1 = (int) (Math.random() * 100) + 1;
			int ran2 = (int) (Math.random() * 100) + 1;
			int ran3 = (int) (Math.random() * 100) + 1;
			write(title, body, 1, 1, ran1, ran2, ran3);
		}

		String title = "제목 " + (100);
		String body = "서울에 올여름 첫 폭염주의보가 내려졌습니다.\r\n" + "\r\n"
				+ "기상청은 오늘 오전 11시를 기해 서울 대부분 지역과 경기도, 광주와 전남 일부 지역에 폭염주의보를 발령했습니다.\r\n" + "\r\n"
				+ "폭염주의보는 체감 온도가 33도 이상인 날이 이틀 이상 이어질 때 내려집니다.\r\n" + "\r\n"
				+ "기상청은 장마전선의 북상을 앞두고 맑은 날씨에 푄현상이 겹치며 태백산맥 서쪽 지역을 중심으로 무더위가 나타나고 있다고 밝혔습니다.\r\n" + "\r\n"
				+ "장마전선은 주말 오전에 북상해 제주도부터 비를 뿌리겠고, 휴일에는 전국에 장맛비가 내릴 전망입니다.";
		int ran1 = (int) (Math.random() * 100) + 1;
		int ran2 = (int) (Math.random() * 100) + 1;
		int ran3 = (int) (Math.random() * 100) + 1;
		write(title, body, 1, 1, ran1, ran2, ran3);
	}

	public List<Article> getSearchKeywordBoardFilteredArticles(String searchKeyword, String searchKeywordTypeCode,
			int boardId, int page, int itemCountInAPage) {

		List<Article> articles = articleRepository.getArticles();
		List<Article> filteredArticles = new ArrayList<Article>();

		boolean searchKeywordType = true;

		for (Article article : articles) {

			if (!searchKeyword.isEmpty() && searchKeywordTypeCode.equals("title")) {

				searchKeywordType = article.getTitle().contains(searchKeyword);

				if (searchKeywordType && boardId == 0) {
					filteredArticles.add(article);
				} else if (searchKeywordType && boardId != 0) {
					filteredArticles.add(article);
				}
			} else if (!searchKeyword.isEmpty() && searchKeywordTypeCode.equals("body")) {

				searchKeywordType = article.getBody().contains(searchKeyword);

				if (searchKeywordType && boardId == 0) {
					filteredArticles.add(article);
				} else if (searchKeywordType && boardId != 0) {
					filteredArticles.add(article);
				}
			} else if (!searchKeyword.isEmpty() && searchKeywordTypeCode.equals("keyword")) {

				searchKeywordType = article.getKeywordsStr().contains(searchKeyword);

				if (searchKeywordType && boardId == 0) {
					filteredArticles.add(article);
				} else if (searchKeywordType && boardId != 0) {
					filteredArticles.add(article);
				}
			}

		}
		List<Article> articlesBeforePaging = new ArrayList<Article>();
		if (filteredArticles.size() != 0) {

			articlesBeforePaging = filteredArticles;

		} else {

			articlesBeforePaging = articles;
		}
		if (page != 0) {
			int offsetCount = (page - 1) * itemCountInAPage;

			int endIndex = articlesBeforePaging.size() - 1 - offsetCount;
			int startIndex = endIndex - (itemCountInAPage - 1);
			System.out.println("startIndex : " + startIndex);
			System.out.println("endIndex : " + endIndex);

			if (startIndex < 0) {
				startIndex = 0;
			}

			List<Article> articlesAfterPaging = new ArrayList<Article>();

			for (int i = startIndex; endIndex >= i; i++) {
				articlesAfterPaging.add(articlesBeforePaging.get(i));

			}
			return articlesAfterPaging;

		}

		return articlesBeforePaging;

	}

	public void changeLikeCount(boolean likeOrDislike, int likeCount, int articleId) {
		articleRepository.changeLikeCount(likeOrDislike, likeCount, articleId);

	}

	public void increaseArticleHitCount(Article article) {
		articleRepository.increaseArticleHitCount(article);

	}

	public void increaseArticleLike(boolean likeOrDislike, int count, Article article) {
		articleRepository.increaseArticleLike(likeOrDislike, count, article);

	}

	public void modify(String title, String body, Article article) {
		articleRepository.modify(title, body, article);

	}

	public List<Article> getSortedArticles(List<Article> filteredArticles, String orderByColumn, String orderAscTypeCode) {
		
		List<Article> sortedArticles = null;
		if(orderByColumn.equals("id") && orderAscTypeCode.equals("asc")) {
			
			}
		}else {
//			id 의 desc를 기본 정렬로 한다.
		}
	}
}
