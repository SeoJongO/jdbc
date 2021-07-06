package com.javaex.ex03;

import java.util.List;
import java.util.Scanner;

public class BookApp {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		String word = sc.next();
		
		// 작가테이블 책테이블 완성
		// 작가테이블 시퀀스, 책테이블 시퀀스 완성
		
		// authorDao.authorInsert(); <-- 작가 6명 추가
		// bookDao.bookInsert(); <-- 책 6권 추가
		
		// 등록 수정 삭제 출력
		BookDao bookDao = new BookDao();
		List<BookVo> BookList;

		// 출력
		BookList = bookDao.BookList();
		printList(BookList);

		// 등록
		BookVo iBookVo = new BookVo("삼국지","마로니에","2002-06-07", 1);
		int iCount = bookDao.bookInsert(iBookVo);
		if (iCount > 0) {
			System.out.println("[등록되었습니다.]");
		} else {
			System.out.println("[관리자에게 문의하세요(" + iCount + ")]");
		}

		// 수정
		int uCount = bookDao.bookUpdate("노인과바다","몰라","2021-07-05",8);

		// 삭제
		int dCount = bookDao.bookDelete(9);
		
		// 검색
		printList(bookDao.bookSearch(word));
		
		sc.close();

	}

	// 리스트 출력 메소드
	public static void printList(List<BookVo> bookList) {

		for (int i = 0; i < bookList.size(); i++) {

			BookVo bookVo = bookList.get(i);
			System.out.println(bookVo.getBook_id()
					   + "," + bookVo.getTitle()
					   + "," + bookVo.getPubs()
					   + "," + bookVo.getPub_date()
					   + "," + bookVo.getAuthor_id()
					   + "," + bookVo.getAuthor_name()
					   + "," + bookVo.getAuthor_desc());
		}

		System.out.println("=================================");
		System.out.println("");

	}

}