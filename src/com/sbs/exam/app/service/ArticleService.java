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
		String body = "서울에 올여름 첫 폭염주의보가 내려졌습니다.\r\n"
				+ "\r\n"
				+ "기상청은 오늘 오전 11시를 기해 서울 대부분 지역과 경기도, 광주와 전남 일부 지역에 폭염주의보를 발령했습니다.\r\n"
				+ "\r\n"
				+ "폭염주의보는 체감 온도가 33도 이상인 날이 이틀 이상 이어질 때 내려집니다.\r\n"
				+ "\r\n"
				+ "기상청은 장마전선의 북상을 앞두고 맑은 날씨에 푄현상이 겹치며 태백산맥 서쪽 지역을 중심으로 무더위가 나타나고 있다고 밝혔습니다.\r\n"
				+ "\r\n"
				+ "장마전선은 주말 오전에 북상해 제주도부터 비를 뿌리겠고, 휴일에는 전국에 장맛비가 내릴 전망입니다.";
		int ran1 = (int) (Math.random() * 100) + 1;
		int ran2 = (int) (Math.random() * 100) + 1;
		int ran3 = (int) (Math.random() * 100) + 1;
		write(title, body, 1, 1, ran1, ran2, ran3);
	}

	private List<Article> bubleSortAsc(List<Article> filteredArticles, String standard) {
		List<Article> filterFilteredArticles = filteredArticles;

//		정렬을 한번하게 되면, 값의 위치가 맨 처음 id순에서 다 바뀌기 때문에 출력 시, 항상 이 과정을 거쳐야한다.
		if (standard.equals("hitCount")) {
//			값은 잘 들어옴
			for (int i = 0; i < filterFilteredArticles.size(); i++) {

				for (int j = 0; j < filterFilteredArticles.size() - i - 1; j++) {

					Article article1 = filterFilteredArticles.get(j);
					Article article2 = filterFilteredArticles.get(j + 1);
					Article temp2 = filterFilteredArticles.get(j);
					Article temp = filterFilteredArticles.get(j + 1);
//					System.out.println("i = " + i + ", j = " + j + "      " + article1.toString());
//					System.out.println("i = " + i + ", j = " + j + "      " + article2.toString());
//					동일한 값이 존재하는 경우
					if (article1.getHitCount() > article2.getHitCount()) {
//						Article temp = article2;
//						Article temp2 = article1;

						// 나중에 테스트할 때, 기존 article 객체가 없어지고 새로운 article객체가 깔끔하게 들어가는지

						filterFilteredArticles.get(j + 1).setId(temp2.getId());
						filterFilteredArticles.get(j + 1).setRegDate(temp2.getRegDate());
						filterFilteredArticles.get(j + 1).setUpdateDate(temp2.getUpdateDate());
						filterFilteredArticles.get(j + 1).setMemberId(temp2.getMemberId());
						filterFilteredArticles.get(j + 1).setBoardId(temp2.getBoardId());
						filterFilteredArticles.get(j + 1).setHitCount(temp2.getHitCount());
						filterFilteredArticles.get(j + 1).setLike(temp2.getLike());
						filterFilteredArticles.get(j + 1).setDislike(temp2.getDislike());
						filterFilteredArticles.get(j + 1).setTitle(temp2.getTitle());
						filterFilteredArticles.get(j + 1).setBody(temp2.getBody());
//
//						// 나중에 테스트할 때, 기존 article 객체가 없어지고 새로운 article객체가 깔끔하게 들어가는지
						filterFilteredArticles.get(j).setId(temp.getId());
						filterFilteredArticles.get(j).setRegDate(temp.getRegDate());
						filterFilteredArticles.get(j).setUpdateDate(temp.getUpdateDate());
						filterFilteredArticles.get(j).setMemberId(temp.getMemberId());
						filterFilteredArticles.get(j).setBoardId(temp.getBoardId());
						filterFilteredArticles.get(j).setHitCount(temp.getHitCount());
						filterFilteredArticles.get(j).setLike(temp.getLike());
						filterFilteredArticles.get(j).setDislike(temp.getDislike());
						filterFilteredArticles.get(j).setTitle(temp.getTitle());
						filterFilteredArticles.get(j).setBody(temp.getBody());
					}
				}
			}

		}

//		if (standard.equals("hitCount")) {
////			값은 잘 들어옴
//			for (int i = 0; i < filterFilteredArticles.size(); i++) {
//
//				for (int j = 0; j < filterFilteredArticles.size() - i - 1; j++) {
//					Article article1 = filterFilteredArticles.get(j);
//					Article article2 = filterFilteredArticles.get(j + 1);
//					if (article1.getHitCount() > article2.getHitCount()) {
//						Article temp = article2;
//						System.out.println(article2.toString());
//						article2.setBoardId(i); // 나중에 테스트할 때, 기존 article 객체가 없어지고 새로운 article객체가 깔끔하게 들어가는지
//						article2.setId(article1.getId());
//						article2.setRegDate(article1.getRegDate());
//						article2.setUpdateDate(article1.getUpdateDate());
//						article2.setMemberId(article1.getMemberId());
//						article2.setBoardId(article1.getBoardId());
//						article2.setHitCount(article1.getHitCount());
//						article2.setLike(article1.getLike());
//						article2.setTitle(article1.getTitle());
//						article2.setBody(article1.getBody());
//
//						
//
//						article1.setBoardId(i); // 나중에 테스트할 때, 기존 article 객체가 없어지고 새로운 article객체가 깔끔하게 들어가는지
//						article1.setId(temp.getId());
//						article1.setRegDate(temp.getRegDate());
//						article1.setUpdateDate(temp.getUpdateDate());
//						article1.setMemberId(temp.getMemberId());
//						article1.setBoardId(temp.getBoardId());
//						article1.setHitCount(temp.getHitCount());
//						article1.setLike(temp.getLike());
//						article1.setTitle(temp.getTitle());
//						article1.setBody(temp.getBody());
//					}
//				}
//			}
//
//		} 
//		else if (standard.equals("memberId")) {
//			for (int i = 0; i < filterFilteredArticles.size(); i++) {
//
//				for (int j = 0; j < filterFilteredArticles.size() - i - 1; j++) {
//					Article article1 = filterFilteredArticles.get(j);
//					Article article2 = filterFilteredArticles.get(j + 1);
//					if (article1.getMemberId() > article2.getMemberId()) {
//						Article temp = article2;
//						article2.setBoardId(i); // 나중에 테스트할 때, 기존 article 객체가 없어지고 새로운 article객체가 깔끔하게 들어가는지
//						article2.setId(article1.getId());
//						article2.setRegDate(article1.getRegDate());
//						article2.setUpdateDate(article1.getUpdateDate());
//						article2.setMemberId(article1.getMemberId());
//						article2.setBoardId(article1.getBoardId());
//						article2.setHitCount(article1.getHitCount());
//						article2.setLike(article1.getLike());
//						article2.setTitle(article1.getTitle());
//						article2.setBody(article1.getBody());
//
//						article1 = temp;
//
//						article1.setBoardId(i); // 나중에 테스트할 때, 기존 article 객체가 없어지고 새로운 article객체가 깔끔하게 들어가는지
//						article1.setId(temp.getId());
//						article1.setRegDate(temp.getRegDate());
//						article1.setUpdateDate(temp.getUpdateDate());
//						article1.setMemberId(temp.getMemberId());
//						article1.setBoardId(temp.getBoardId());
//						article1.setHitCount(temp.getHitCount());
//						article1.setLike(temp.getLike());
//						article1.setTitle(temp.getTitle());
//						article1.setBody(temp.getBody());
//					}
//				}
//			}
//		}else if (standard.equals("boardId")) {
//			for (int i = 0; i < filterFilteredArticles.size(); i++) {
//
//				for (int j = 0; j < filterFilteredArticles.size() - i - 1; j++) {
//					Article article1 = filterFilteredArticles.get(j);
//					Article article2 = filterFilteredArticles.get(j + 1);
//					if (article1.getBoardId() > article2.getBoardId()) {
//						Article temp = article2;
//						article2.setBoardId(i); // 나중에 테스트할 때, 기존 article 객체가 없어지고 새로운 article객체가 깔끔하게 들어가는지
//						article2.setId(article1.getId());
//						article2.setRegDate(article1.getRegDate());
//						article2.setUpdateDate(article1.getUpdateDate());
//						article2.setMemberId(article1.getMemberId());
//						article2.setBoardId(article1.getBoardId());
//						article2.setHitCount(article1.getHitCount());
//						article2.setLike(article1.getLike());
//						article2.setTitle(article1.getTitle());
//						article2.setBody(article1.getBody());
//
//						article1 = temp;
//
//						article1.setBoardId(i); // 나중에 테스트할 때, 기존 article 객체가 없어지고 새로운 article객체가 깔끔하게 들어가는지
//						article1.setId(temp.getId());
//						article1.setRegDate(temp.getRegDate());
//						article1.setUpdateDate(temp.getUpdateDate());
//						article1.setMemberId(temp.getMemberId());
//						article1.setBoardId(temp.getBoardId());
//						article1.setHitCount(temp.getHitCount());
//						article1.setLike(temp.getLike());
//						article1.setTitle(temp.getTitle());
//						article1.setBody(temp.getBody());
//					}
//				}
//			}
//		}else if (standard.equals("like")) {
//			for (int i = 0; i < filterFilteredArticles.size(); i++) {
//
//				for (int j = 0; j < filterFilteredArticles.size() - i - 1; j++) {
//					Article article1 = filterFilteredArticles.get(j);
//					Article article2 = filterFilteredArticles.get(j + 1);
//					if (article1.getLike() > article2.getLike()) {
//						Article temp = article2;
//						article2.setBoardId(i); // 나중에 테스트할 때, 기존 article 객체가 없어지고 새로운 article객체가 깔끔하게 들어가는지
//						article2.setId(article1.getId());
//						article2.setRegDate(article1.getRegDate());
//						article2.setUpdateDate(article1.getUpdateDate());
//						article2.setMemberId(article1.getMemberId());
//						article2.setBoardId(article1.getBoardId());
//						article2.setHitCount(article1.getHitCount());
//						article2.setLike(article1.getLike());
//						article2.setTitle(article1.getTitle());
//						article2.setBody(article1.getBody());
//
//						article1 = temp;
//
//						article1.setBoardId(i); // 나중에 테스트할 때, 기존 article 객체가 없어지고 새로운 article객체가 깔끔하게 들어가는지
//						article1.setId(temp.getId());
//						article1.setRegDate(temp.getRegDate());
//						article1.setUpdateDate(temp.getUpdateDate());
//						article1.setMemberId(temp.getMemberId());
//						article1.setBoardId(temp.getBoardId());
//						article1.setHitCount(temp.getHitCount());
//						article1.setLike(temp.getLike());
//						article1.setTitle(temp.getTitle());
//						article1.setBody(temp.getBody());
//					}
//				}
//			}
//		}else{
//			for (int i = 0; i < filterFilteredArticles.size(); i++) {
//
//				for (int j = 0; j < filterFilteredArticles.size() - i - 1; j++) {
//					Article article1 = filterFilteredArticles.get(j);
//					Article article2 = filterFilteredArticles.get(j + 1);
//					if (article1.getId() > article2.getId()) {
//						Article temp = article2;
//						article2.setBoardId(i); // 나중에 테스트할 때, 기존 article 객체가 없어지고 새로운 article객체가 깔끔하게 들어가는지
//						article2.setId(article1.getId());
//						article2.setRegDate(article1.getRegDate());
//						article2.setUpdateDate(article1.getUpdateDate());
//						article2.setMemberId(article1.getMemberId());
//						article2.setBoardId(article1.getBoardId());
//						article2.setHitCount(article1.getHitCount());
//						article2.setLike(article1.getLike());
//						article2.setTitle(article1.getTitle());
//						article2.setBody(article1.getBody());
//
//						article1 = temp;
//
//						article1.setBoardId(i); // 나중에 테스트할 때, 기존 article 객체가 없어지고 새로운 article객체가 깔끔하게 들어가는지
//						article1.setId(temp.getId());
//						article1.setRegDate(temp.getRegDate());
//						article1.setUpdateDate(temp.getUpdateDate());
//						article1.setMemberId(temp.getMemberId());
//						article1.setBoardId(temp.getBoardId());
//						article1.setHitCount(temp.getHitCount());
//						article1.setLike(temp.getLike());
//						article1.setTitle(temp.getTitle());
//						article1.setBody(temp.getBody());
//					}
//				}
//			}
//		}

		return filterFilteredArticles;

	}

//	public class ArticleSortById implements Comparable<Article> {
//		public int id;		
//		public ArticleSortById(int id) {
//			
//			this.id = id;
//		}
//		@Override
//		public int compareTo(Article o) {
//			if(this.id < o.id) {
//				return -1;
//			} else if(this.id == o.id) {
//				return 0;
//			} else {
//				return 1;
//			}
//		}	
//	}

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
			}

		}
		List<Article> articlesBeforePaging = new ArrayList<Article>();
		if (filteredArticles.size() != 0) {

//			bubleSortAsc(filteredArticles, "hitCount");

			articlesBeforePaging = filteredArticles;

		} else {

			articlesBeforePaging = articles;
		}
		if(page != 0) {
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
}
