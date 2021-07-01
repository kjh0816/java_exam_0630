package com.sbs.exam.app.service;

import com.sbs.exam.app.container.Container;
import com.sbs.exam.app.dto.Member;
import com.sbs.exam.app.repository.MemberRepository;

public class MemberService {

	private MemberRepository memberRepository;

	public MemberService() {
		memberRepository = Container.getMemberRepository();
	}

	public void makeTestData() {
		for (int i = 0; i < 100; i++) {
			String loginId = "user" + (i + 1);
			String loginPw = loginId;
			String name = "지후_" + (i + 1);
			String nickname = "언더테이커_" + (i + 1);

			join(loginId, loginPw, name, nickname);
		}
	}

	public int join(String loginId, String loginPw, String name, String nickname) {
		return memberRepository.join(loginId, loginPw, name, nickname);
	}

	public Member getMemberByLoginId(String loginId) {
		return memberRepository.getMemberByLoginId(loginId);
	}

	public Member getMemberById(int memberId) {
		return memberRepository.getMemberById(memberId);
	}

}
