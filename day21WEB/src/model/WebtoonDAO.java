package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class WebtoonDAO {
	Connection conn;
	PreparedStatement pstmt;

	final String sql_insert="INSERT INTO WEBTOON (TITLE,AUTHOR)VALUES (?,?)";
	final String sql_selectOne="SELECT * FROM WEBTOON WHERE TITLE = ?";
	final String sql_update="UPDATE WEBTOON SET CNT=CNT+1 WHERE TITLE=?";
	final String sql_delete="DELETE FROM WEBTOON WHERE TITLE =?";
	final String sql_selectAll="SELECT * FROM WEBTOON";
	final String sql_countDB="SELECT COUNT(*) AS CNT FROM WEBTOON";

	public boolean insert(WebtoonVO vo) { //사용자에게 제목을 입력받은 객체를 인풋으로 받음
		conn=JDBCUtil.connect(); // 이거 conn 하는 정확한 이유가 뭐지 커넥션 개념 필요

		try {
			pstmt=conn.prepareStatement(sql_insert); // 이 sql문장을 사용할거야
			pstmt.setString(1, vo.getTitle()); // 1번째 물음표에 제목을 넣어줘
			pstmt.setString(2, vo.getAuthor()); // 2번째 물음표에 작가를 넣어줘
			pstmt.executeUpdate(); // 실행해줘
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn); // 종료해줘
		}
		return false;
	}

	public boolean update(WebtoonVO vo) { // 조회수 cnt를 1씩 증가할거야
		conn=JDBCUtil.connect(); 
		try {
			pstmt=conn.prepareStatement(sql_update); // 이 문장을 사용
			pstmt.setString(1, vo.getTitle()); // 1번째 물음표에 제목 기입
			pstmt.executeUpdate(); // 실행
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn); // 종료
		}
		return false;
	}
	public boolean delete(WebtoonVO vo) { // 삭제
		conn=JDBCUtil.connect(); // 이거 conn 하는 정확한 이유가 뭐지 커넥션 개념 필요

		try {
			pstmt=conn.prepareStatement(sql_delete);
			pstmt.setString(1, vo.getTitle());
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return false;
	}

	public WebtoonVO selectOne(WebtoonVO vo) { // read 1
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(sql_selectOne); // 사용자가 입력한 제목의 행을 보여줘
			pstmt.setString(1, vo.getTitle());
			ResultSet rs = pstmt.executeQuery(); // 그 정보를 rs에 저장
			if(rs.next()) {
				WebtoonVO wvo=new WebtoonVO(); // 빈객체에다가
				wvo.setTitle(rs.getString("TITLE")); // 제목과
				wvo.setAuthor(rs.getString("AUTHOR")); // 작가와
				wvo.setCnt(rs.getInt("CNT")); // 조회수를 저장해서
				rs.close();
				return wvo; // 그 객체를 리턴
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return null;
	}

	public ArrayList<WebtoonVO> selectAll(WebtoonVO vo) { // read 2
		conn=JDBCUtil.connect();
		try {
			ArrayList<WebtoonVO> datas =new ArrayList<WebtoonVO>(); // 빈 배열 만들기

			pstmt=conn.prepareStatement(sql_selectAll); // 테이블에 모든 정보를 읽어와
			ResultSet rs = pstmt.executeQuery(); // 읽은 내용을 rs 배열에 저장해
			while(rs.next()) { // 다음 데이터가 없을 때까지 실행
				WebtoonVO wvo=new WebtoonVO(); // 빈객체에다가
				wvo.setTitle(rs.getString("TITLE")); // 제목과
				wvo.setAuthor(rs.getString("AUTHOR")); // 작가와
				wvo.setCnt(rs.getInt("CNT")); // 조회수를
				datas.add(wvo); // 빈배열에 객체로 저장해
			}
			rs.close();
			return datas;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return null;
	}

	public boolean countDB() {
		conn=JDBCUtil.connect(); // 이거 conn 하는 정확한 이유가 뭐지 커넥션 개념 필요

		try {
			pstmt=conn.prepareStatement(sql_countDB);
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getInt("CNT")==0) {
					return true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return false;
	}

}
