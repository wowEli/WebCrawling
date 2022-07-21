package day21;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Test02 {
	final String url="https://comic.naver.com/webtoon/weekdayList?week=mon&view=list&order=User";
	Document doc=null;

	public void main2() {
		try {
			doc = Jsoup.connect(url).get(); // html코드를 불러와서 doc의 담는다
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String str="div > a > img"; // 위 url의 이미지 경로

		Elements eles=doc.select(str); // 원하는 경로의 있는 요소들을 모아서 Elements타입 eles에 담기
		Iterator<Element> itr=eles.iterator(); // 컬렉션 프레임워크 iterator의 한개씩 저장

		URL url = null;
		InputStream in = null;
		OutputStream out = null;
		
		int num=1; // 이름을 변경할 변수

		while(itr.hasNext()) {
			String img=itr.next().attr("src");
			System.out.println(img);
			try {
				url = new URL(img); // url을 담는 객체
				in = url.openStream(); // inputStream의 객체를 생성해주고 생성한 객체를 이용하여 자원(url을 통한 데이터)을 가져올 수 있다
				out = new FileOutputStream("/Users/dongwook/0607jang/photo/"+num+".png"); // 파일 저장 위치 설정
				num++;
				while (true) {
					// 루프를 돌면서 이미지데이터를 읽어들이게 됩니다.
					int data = in.read(); // url 안에 있는 데이터를 읽는다(다운받는다) (복사)
					
					// 데이터값이 -1이면 루프를 종료하고 나오게 됩니다.
					if (data == -1) { // 읽다가 -1일 경우 마지막임
						break;
					}
					
					// 읽어들인 이미지 데이터값을 컴퓨터 또는 서버공간에 저장하게 됩니다.
					out.write(data); // 읽은 데이터를 그대로 설정한 저장 경로에 다시 써준다 (붙여넣기)
				}
				in.close();
	            out.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
