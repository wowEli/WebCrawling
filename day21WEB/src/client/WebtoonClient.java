package client;

import java.util.Scanner;

import controller.WebtoonController;
import day21.Test01;
import day21.Test02;
import model.WebtoonDAO;

public class WebtoonClient {

	public static void main(String[] args) {
		WebtoonController app=new WebtoonController();
		WebtoonDAO model=new WebtoonDAO();
		Test01 main=new Test01();
		Test02 main2=new Test02();
		
//		main2.main2();

		if(model.countDB()) {
			Scanner sc =new Scanner(System.in);
			System.out.print("저장할 데이터 개수 입력: ");
			int num=sc.nextInt(); // 몇개의 데이터를 db에 저장할 지 입력받고
			main.main(num); // 입력 값만큼 데이터를 db에 웹크롤링
		}
		app.startApp();
	}

}
