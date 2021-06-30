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
		make( 1,"notice", "공지사항");
		make( 1,"free", "자유");
	}

	private int make(int memberId, String code, String name) {
		return boardRepository.make(memberId, code, name);
	}

}
