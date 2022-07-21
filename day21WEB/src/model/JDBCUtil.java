package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCUtil {
	//JDBCUtil
	// 1) C R R U D
	// 2) DAO가 증가하면 또 CRUD도 증가됨 -> ...
	// => 복 붙을 하게 됨 == 코드재사용 ,중복코드 최소화 , 모듈화, Util클래스 생성!
//	public conn 객체반환
//	public conn 객체 받아서 연결해
	// Util 류 class 객체를 만들려고 생긴 것이 아닌 오직 로직을 보관하기 위해 static 객체와는 무관
	static final String driverName="com.mysql.cj.jdbc.Driver";
	static final String url="jdbc:mysql://localhost:3306/jang";
	static final String user="root";
	static final String password="7418569";
	public static Connection connect() {
		Connection conn=null;
		try {
			Class.forName(driverName);
			
			conn=DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	public static void disconnect(PreparedStatement pstmt, Connection conn) {
		try {
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
