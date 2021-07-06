package com.javaex.ex03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao {

	// 필드
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "webdb";
	private String pw = "webdb";

	List<BookVo> bookList = new ArrayList<BookVo>();
	
	// DB연결
	private void getConnection() {
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);

			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);
			System.out.println("접속성공");

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}

	// 자원정리
	private void close() {
		// 5. 자원정리
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}
	
	// Search
		public List<BookVo> bookSearch(String word) {

			
			
			getConnection();
			
			try {
				// 3. SQL문 준비 / 바인딩 / 실행
				String query = "";
				query += " select b.book_id, ";
				query += "        b.title, ";
				query += "        b.pubs, ";
				query += "        to_char(b.pub_date,'yyyy-mm-dd'), ";
				query += "        b.author_id, ";
				query += "        a.author_name, ";
				query += "        a.author_desc ";
				query += " from author a, book b ";
				query += " where a.author_id = b.author_id ";
				query += " and (author_name like '%"+word+"%' ";
				query += " 	    or title like '%"+word+"%' ";
				query += " 		or pubs like '%"+word+"%'";
				query += " 		or author_desc like '%"+word+"%') ";
				query += " order by book_id asc";
				
				pstmt = conn.prepareStatement(query);
				
				rs = pstmt.executeQuery();
				
				// 4.결과처리
				while (rs.next()) {
					int book_id = rs.getInt("book_id");
					String title = rs.getString("title");
					String pubs = rs.getString("pubs");
					String pub_date = rs.getString("to_char(b.pub_date,'yyyy-mm-dd')");
					int author_id = rs.getInt("author_id");
					String author_name = rs.getString("author_name");
					String author_desc = rs.getString("author_desc");

					BookVo bookVo = new BookVo(book_id, title, pubs, pub_date, author_id, author_name, author_desc);

					bookList.add(bookVo);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}

			close();

			return bookList;

		}
	
	// Delete
	public int bookDelete(int book_id) {
		int count = -1;

		this.getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " delete from book ";
			query += " where book_id = ? ";

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, book_id);

			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건 삭제");

		} catch (SQLException e) {
			e.printStackTrace();
		}

		this.close();

		return count;
	}

	// Update
	public int bookUpdate(String title, String pubs, String pub_date, int book_id) {

		int count = -1;

		getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " update book ";
			query += " set title = ?, ";
			query += "     pubs = ?, ";
			query += "     pub_date = ? ";
			query += " where book_id = ? ";

			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, title);
			pstmt.setString(2, pubs);
			pstmt.setString(3, pub_date);
			pstmt.setInt(4, book_id);

			count = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		// 4.결과처리
		System.out.println(count + "건 수정");

		close();

		return count;
	}

	// Insert
	public int bookInsert(BookVo bookVo) {

		int count = -1;

		getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " insert into book ";
			query += " values(seq_book_id.nextval, ?, ?, ?, ?) ";

			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, bookVo.getTitle());
			pstmt.setString(2, bookVo.getPubs());
			pstmt.setString(3, bookVo.getPub_date());
			pstmt.setInt(4, bookVo.getAuthor_id());

			count = pstmt.executeUpdate();
			
			

			// 4.결과처리
			System.out.println(count + "건 등록");

		} catch (SQLException e) {
			e.printStackTrace();
		}

		close();

		return count; // 성공갯수 리턴
	}
	
	// List
	public List<BookVo> BookList() {
		// DB값을 가져와서 ArrayList로 전달

		// 리스트 생성

		
		getConnection();
		
		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " select b.book_id, ";
			query += "        b.title, ";
			query += "        b.pubs, ";
			query += "        to_char(b.pub_date,'yyyy-mm-dd'), ";
			query += "        b.author_id, ";
			query += "        a.author_name, ";
			query += "        a.author_desc ";
			query += " from author a, book b ";
			query += " where a.author_id = b.author_id ";
			query += " order by book_id asc";
			
			pstmt = conn.prepareStatement(query);
			
			rs = pstmt.executeQuery();
			
			// 4.결과처리
			while (rs.next()) {
				int book_id = rs.getInt("book_id");
				String title = rs.getString("title");
				String pubs = rs.getString("pubs");
				String pub_date = rs.getString("to_char(b.pub_date,'yyyy-mm-dd')");
				int author_id = rs.getInt("author_id");
				String author_name = rs.getString("author_name");
				String author_desc = rs.getString("author_desc");

				BookVo bookVo = new BookVo(book_id, title, pubs, pub_date, author_id, author_name, author_desc);

				bookList.add(bookVo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		close();

		return bookList;

	}


}
