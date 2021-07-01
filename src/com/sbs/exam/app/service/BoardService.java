package com.sbs.exam.app.service;

import com.sbs.exam.app.container.Container;
import com.sbs.exam.app.dto.Board;
import com.sbs.exam.app.repository.BoardRepository;

public class BoardService {
	private BoardRepository boardRepository;

	public BoardService() {
		boardRepository = Container.getBoardRepository();
	}

	public Board getBoardById(int id) {
		return boardRepository.getBoardById(id);
	}

	public void makeTestData() {
		make( 1,"공지", "notice");
		make( 1,"자유", "free");
	}

	private int make(int memberId, String code, String name) {
		return boardRepository.make(memberId, code, name);
	}

}
