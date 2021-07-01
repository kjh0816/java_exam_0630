package com.sbs.exam.app.controller;

import java.util.List;
import java.util.Scanner;

import com.sbs.exam.app.Rq;
import com.sbs.exam.app.container.Container;
import com.sbs.exam.app.dto.Article;
import com.sbs.exam.app.dto.Board;
import com.sbs.exam.app.dto.Member;
import com.sbs.exam.app.service.ArticleService;
import com.sbs.exam.app.service.BoardService;
import com.sbs.exam.app.service.MemberService;
import com.sbs.exam.util.Util;

public class UsrArticleController extends Controller {
	private ArticleService articleService;
	private MemberService memberService;
	private BoardService boardService;
	private Scanner sc;

	public UsrArticleController() {
		articleService = Container.getArticleService();
		memberService = Container.getMemberService();
		boardService = Container.getBoardService();
		sc = Container.getSc();

		// 테스트 게시물 만들기
		makeTestData();
	}

	private void makeTestData() {
		articleService.makeTestData();
		boardService.makeTestData();
	}

	@Override
	public void performAction(Rq rq) {
		if (rq.getActionPath().equals("/usr/article/write")) {
			actionWrite(rq);
		} else if (rq.getActionPath().equals("/usr/article/list")) {
			actionList(rq);
		} else if (rq.getActionPath().equals("/usr/article/detail")) {
			actionDetail(rq);
		} else if (rq.getActionPath().equals("/usr/article/delete")) {
			actionDelete(rq);
		} else if (rq.getActionPath().equals("/usr/article/modify")) {
			actionModify(rq);
		}
	}

	private void actionModify(Rq rq) {
		int id = rq.getIntParam("id", 0);

		if (id == 0) {
			System.out.println("id를 입력해주세요.");
			return;
		}

		Article article = articleService.getArticleById(id);

		if (article == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
			return;
		}
		int memberId = rq.getLoginedMember().getId();
		if (memberId != article.getMemberId()) {
			System.out.println("권한이 없습니다.");
			return;
		}
		
		

		System.out.printf("새 제목 : ");
		String title = sc.nextLine().trim();
		System.out.printf("새 내용 : ");
		String body = sc.nextLine().trim();
		
		articleService.modify(title, body, article);

		System.out.printf("%d번 게시물을 수정하였습니다.\n", id);
	}

	private void actionDelete(Rq rq) {
		int id = rq.getIntParam("id", 0);

		if (id == 0) {
			System.out.println("id를 입력해주세요.");
			return;
		}

		Article article = articleService.getArticleById(id);

		if (article == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
			return;
		}
		int memberId = rq.getLoginedMember().getId();
		if (memberId != article.getMemberId()) {
			System.out.println("권한이 없습니다.");
			return;
		}

		articleService.deleteArticleById(article.getId());

		System.out.printf("%d번 게시물을 삭제하였습니다.\n", id);
	}

	private void actionDetail(Rq rq) {
		int id = rq.getIntParam("id", 0);

		if (id == 0) {
			System.out.println("id를 입력해주세요.");
			return;
		}

		
		Article article = articleService.getArticleById(id);

		if (article == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
			return;
		}
//		조회할 때마다 조회수 1 증가
		articleService.increaseArticleHitCount(article);
		
		Member member = memberService.getMemberById(article.getMemberId());
		Board board = boardService.getBoardById(article.getBoardId());
		List<String> tags = Util.getKeywordsFromStr(article.getKeywordsStr());
		

		System.out.printf("번호 : %d\n", article.getId());
		System.out.printf("게시판 : %s\n", board.getName());
		System.out.printf("작성자 : %s\n", member.getNickname());
		System.out.printf("좋아요 : %d\n", article.getLike());
		System.out.printf("싫어요 : %d\n", article.getDislike());
		System.out.printf("조회수 : %d\n", article.getHitCount());
		System.out.printf("작성날짜 : %s\n", article.getRegDate());
		System.out.printf("수정날짜 : %s\n", article.getUpdateDate());
		System.out.printf("제목 : %s\n", article.getTitle());
		System.out.printf("내용 : %s\n\n", article.getBody());
		for(int i = 0; i < tags.size(); i++) {
			System.out.printf("# " + tags.get(i) + "  ");
			if(i % 5 == 0 && i != 0) {
				System.out.println();
			}
		}
		System.out.println("\n\n");
		
	}

	private void actionList(Rq rq) {
		List<Article> articles = articleService.getArticles();
//		(/usr/article/list
//				?searchKeyword=""&
//				boardId=&
//				orderByColumn= *id, *hitCount, *likedCount&
//				orderAscTypeCode=*asc, *desc
//				searchKeywordTypeCode=keyword
		int boardId = rq.getIntParam("id", 0);
		int page = rq.getIntParam("page", 0);
		String searchKeyword = rq.getStrParam("searchKeyword", "");
		String searchKeywordTypeCode = rq.getStrParam("searchKeywordTypeCode", "");

		String orderByColumn = rq.getStrParam("orderByColumn", ""); // sorting 필요
		String orderAscTypeCode = rq.getStrParam("orderAscTypeCode", ""); // sorting 필요

		String boardName = "전체";
		String keyword = "";
		String keywordType = "";
		if (!searchKeywordTypeCode.isEmpty() && !searchKeyword.isEmpty()) {
			keywordType = "( 검색 타입: " + searchKeywordTypeCode + " )";
			keyword = "( 검색 키워드: " + searchKeyword + " )";

		}

		if (boardId != 0) {
			Board board = boardService.getBoardById(boardId);
			boardName = board.getName().trim();
		}

		int itemCountInAPage = 10;
//		1) searchKeyword와 boardId로 필터링
//		2) orderByColumn, orderAscTypeCode, searchKeywordTypeCode 로 필터링 및 페이징

		List<Article> filteredArticles = articleService.getSearchKeywordBoardFilteredArticles(searchKeyword,
				searchKeywordTypeCode, boardId, page, itemCountInAPage);
		
		int articleCount = filteredArticles.size();
		
		System.out.println("=== " + boardName + " 게시물 목록 ===");
		if (!searchKeywordTypeCode.isEmpty()) {
			System.out.println(keywordType);
		}
		if (!searchKeyword.isEmpty()) {
			System.out.println(keyword);
		}
		System.out.println("( 총 게시물 수: " + articleCount + " )");
		System.out.printf("번호 / 작성날짜 / 제목 / 작성자 / 조회수 / 좋아요 / 싫어요 \n");
		for (Article article : filteredArticles) {
			String writerName = memberService.getMemberById(article.getMemberId()).getNickname();
			System.out.printf("%d / %s / %s / %s / %d / %d / %d\n",
					article.getId(), article.getRegDate(), article.getTitle(), writerName
					,article.getHitCount(), article.getLike(), article.getDislike() );
		}
//		for (int i = articles.size() - 1; i >= 0; i--) {
//			Article article = articles.get(i);
//			System.out.printf("%d / %s / %s\n", article.getId(), article.getRegDate(), article.getTitle());
//		}
	}

	private void actionWrite(Rq rq) {
		int boardId = rq.getIntParam("boardId", 0);
		if (boardId == 0) {
			System.out.println("게시판 번호를 입력해주세요.");
			return;
		}
		Board board = boardService.getBoardById(boardId);
		if (board == null) {
			System.out.printf("%d번 게시판은 존재하지 않습니다.\n", boardId);
		}
		System.out.printf("제목 : ");
		String title = sc.nextLine().trim();
		System.out.printf("내용 : ");
		String body = sc.nextLine().trim();

		int memberId = rq.getLoginedMember().getId();

		int id = articleService.write(title, body, memberId, boardId, 0, 0, 0);

		System.out.printf("%s게시판에 %d번 게시물이 생성되었습니다.\n", board.getName(), id);
	}

}
