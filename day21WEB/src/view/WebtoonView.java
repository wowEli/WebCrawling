package view;

import java.util.ArrayList;
import java.util.Scanner;

import model.WebtoonVO;

public class WebtoonView {
	Scanner sc = new Scanner(System.in);
	public int action;
	
	public String inputString() { // 제목 입력
		System.out.print("제목 입력: ");
		String title=sc.nextLine();
		return title;
	}
	public void menuView() { // 메뉴 출력 + 번호 입력
		System.out.println();
		System.out.println("1. 전체 웹툰 보기");
		System.out.println("2. 웹툰 검색");
		System.out.println("3. 웹툰 삭제");
		System.out.println("4. 종료");
		System.out.print("번호 입력: ");
		action=sc.nextInt();
		sc.nextLine(); // 버퍼문제로 인한 스캐너
	}
	public void oneView(WebtoonVO vo) { // 객체를 받으면 정보 출력
		System.out.println();
		System.out.println("===웹툰 정보===");
		System.out.println("웹툰 제목: "+vo.getTitle());
		System.out.println("작가: "+vo.getAuthor());
		System.out.println("조회 수"+vo.getCnt());
	}
	public void allView(ArrayList<WebtoonVO> datas) { // 배열을 받으면 객체 한 개씩 출력
		for(WebtoonVO vo:datas) {
			System.out.println();
			System.out.println("웹툰 제목: "+vo.getTitle());
			System.out.println("작가: "+vo.getAuthor());
			System.out.println("조회 수: "+vo.getCnt());
		}
	}
	public void success() { // 성공
		System.out.println();
		System.out.println("성공하였습니다.");
	}
	public void fail() { // 실패
		System.out.println();
		System.out.println("실패하였습니다.");
	}
	public void end() { // 종료
		System.out.println();
		System.out.println("종료합니다.");
	}
}
