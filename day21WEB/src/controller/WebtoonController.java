package controller;

import java.util.ArrayList;

import model.WebtoonDAO;
import model.WebtoonVO;
import view.WebtoonView;

public class WebtoonController {
	WebtoonDAO model;
	WebtoonView view;
	
	public WebtoonController(){
		model=new WebtoonDAO();
		view=new WebtoonView();
	}

	public void startApp() {
		while(true) {
			view.menuView(); // 메뉴 출력 해주기
			if(view.action==1) { // 1번 전체 출력일 경우
				WebtoonVO vo=new WebtoonVO(); // 빈객체를 만들어서
				ArrayList<WebtoonVO> datas=model.selectAll(vo); // 넣어주면 DB에있는 데이터들이 리턴됨
				view.allView(datas); // 그 배열을 넣어주면 출력
			}
			else if(view.action==2) { // 2번 검색일 경우
				String title=view.inputString(); // 사용자에게 제목을 입력받고
				WebtoonVO vo=new WebtoonVO(); // 빈객체를 만들어서
				vo.setTitle(title); // 입력받은 제목을 넣어주고
				WebtoonVO vo1=model.selectOne(vo); // 그 객체를 모델에 주면 db에서 찾아서 그 행을 리턴
				view.oneView(vo1); // 리턴 된 행의 정보를 출력
				model.update(vo1); // 리턴 된 행의 조회수를 +1

			}
			else if(view.action==3) { // 3번 삭제하기일 경우
				String title=view.inputString(); // 사용자에게 제목을 입력받고
				WebtoonVO vo=new WebtoonVO(); // 빈객체를 만들어서 
				vo.setTitle(title); // 제목을 저장
				model.delete(vo); // 그 제목과 같은 행을 db에서 삭제
				view.success(); // 성공하였습니다
			}
			else if(view.action==4) { // 4번 종료하기
				view.end(); // 종료
				break;
			}
		}
	}
}
