package com.sbs.exam.app.controller;

import java.util.Scanner;

import com.sbs.exam.app.Rq;
import com.sbs.exam.app.container.Container;
import com.sbs.exam.app.dto.Member;
import com.sbs.exam.app.service.MemberService;

public class UsrMemberController extends Controller {
	private Scanner sc;
	private MemberService memberService;

	public UsrMemberController() {
		sc = Container.getSc();
		memberService = Container.getMemberService();

		// 테스트 게시물 만들기
		memberService.makeTestData();
	}

	@Override
	public void performAction(Rq rq) {
		if (rq.getActionPath().equals("/usr/member/login")) {
			actionLogin(rq);
		} else if (rq.getActionPath().equals("/usr/member/logout")) {
			actionLogout(rq);
		}else if (rq.getActionPath().equals("/usr/member/join")) {
			actionJoin(rq);
		}
	}
	
	private void inputNotGiven(String usrInput) {
		if(usrInput.length() == 0) {
			System.out.println(usrInput+"가(이) 입력되지 않았습니다.");
			return;
		}
	}

	private void actionJoin(Rq rq) {
		System.out.println("=== 회원 가입 ===");
		System.out.println("로그인 아이디를 입력해주세요.");
		String loginId = sc.nextLine().trim();
		inputNotGiven(loginId);
		Member member = memberService.getMemberByLoginId(loginId);
		if(member != null) {
			System.out.printf("%s는(은) 이미 존재하는 로그인아이디입니다.\n", loginId);
			return;
		}
		System.out.println("로그인 비밀번호를 입력해주세요.");
		String loginPw = sc.nextLine().trim();
		inputNotGiven(loginPw);
		System.out.println("로그인 비밀번호를 한번 더 입력해주세요.");
		String loginPwConfirm = sc.nextLine().trim();
		inputNotGiven(loginPwConfirm);
		if(loginPw != loginPwConfirm) {
			System.out.println("입력하신 두 비밀번호가 다릅니다.");
			return;
		}
		
		System.out.println("이름을 입력해주세요.");
		String name = sc.nextLine().trim();
		inputNotGiven(name);
		System.out.println("별명을 입력해주세요.");
		String nickname = sc.nextLine().trim();
		inputNotGiven(nickname);
		
		memberService.join(loginId, loginPw, name, nickname);
		System.out.println(nickname+"님의 회원가입이 완료되었습니다.");
		
		
	}

	private void actionLogout(Rq rq) {
		rq.logout();
	}

	private void actionLogin(Rq rq) {
		System.out.printf("로그인아이디 : ");
		String loginId = sc.nextLine().trim();

		if (loginId.length() == 0) {
			System.out.println("로그인아이디를 입력해주세요.");
			return;
		}

		Member member = memberService.getMemberByLoginId(loginId);

		if (member == null) {
			System.out.println("해당 회원은 존재하지 않습니다.");
			return;
		}

		System.out.printf("로그인비밀번호 : ");
		String loginPw = sc.nextLine().trim();

		if (loginId.length() == 0) {
			System.out.println("로그인비밀번호를 입력해주세요.");
			return;
		}

		if (member.getLoginPw().equals(loginPw) == false) {
			System.out.println("비밀번호가 일치하지 않습니다.");
			return;
		}

		rq.login(member);

		System.out.printf("%s님 환영합니다.\n", member.getNickname());
	}
}
