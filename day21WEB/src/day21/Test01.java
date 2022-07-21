package day21;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import model.WebtoonDAO;
import model.WebtoonVO;

public class Test01 {

	// jar파일을 모듈패스에서 
	public void main(int num) {
		// Jsoup.jar 을 추가해야함(모듈 패스넣어보고 실행시 오류나면 클래스에 넣어보기)
		final String url="https://comic.naver.com/webtoon/weekdayList?week=mon&view=list&order=User";
		Document doc=null;
		try {
			doc=Jsoup.connect(url).get();
			// url을 이클립스의 연동을 하기위해 Jsoup(외부 클래스)를 이용해서 커넥션 연결
			// get() 함수를 사용해서 html코드들을 보여주는데 아웃풋이 Document 임 (모든 코드들이 doc의 저장)
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 태그들은 클래스라는 속성과 아이디 라는 속성이 있다(속성이 없는 태그도 있음)
		// 로고 같이 하나의 특색이 있고 다른 데는 없는 유일한 것을 출력할 땐 아이디를 속성으로 갖고 비슷한 특징을 갖고 다른데도 있을 거 같은 건 클래스로 속성을 갖음
		String str="tr > td > a "; // 내가 보고싶은 html 코드의 경로를 지정
		
		
		Elements eles=doc.select(str); 
		// doc.select는 html전체 코드에서 내가 보고싶은 경로의 태그들만 모아서 Elements타입으로 리턴을 해줌
		
		Iterator<Element> itr=eles.iterator();
		// eles의 내가 보고싶은 태그들이 차례로 모여서 1개의 데이터로 저장되어있는데
		// iterator()함수를 이용해 그 요소들을 한 개의 요소당 하나의 데이터로 Element타입으로 리턴 
		// 컬렉션 프레임워크인 Iterator 배열에 Element타입으로 하나씩 저장
	
		WebtoonDAO dao = new WebtoonDAO();
		for(int i=0;i<num;i++) { // .hasNext() = 다음 데이터가 있니?? 있으면 true
			//System.out.println(itr.next());
			String title=itr.next().text();
			String author=itr.next().text();
			// itr 의 next() 함수를 사용해 위에서 Element타입으로 하나씩 저장한 요소들을 리턴해준다
			// 그러나 우리는 출력함수를 사용해 결과를 눈으로 보고 싶으므로 text() 함수를 사용해서
			// String 타입으로 리턴되게 만들고 출력 시 태그들은 안보이게 만들어 준다
			
			System.out.println(title);
			System.out.println(author);
			
			WebtoonVO vo=new WebtoonVO();
			vo.setTitle(title);
			vo.setAuthor(author);
			dao.insert(vo);
		}

	}

}