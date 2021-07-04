package com.javaex.ex02;

import java.util.List;

public class AuthorApp {

	//메소드 일반
	public static void main(String[] args) {
		
		AuthorDao authorDao = new AuthorDao();
		List<AuthorVo> authorList;
		
		//리스트출력     0x777
		//DB에서 데이터 가져오기
		authorList = authorDao.getAuthorList();
		//리스트를 for문으로 출력 --> 메소드로 정의
		printList(authorList);
		
		
		//작가등록
		AuthorVo iAuthorVo = new AuthorVo("황일영", "하이미디어");
		int iCount = authorDao.authorInsert(iAuthorVo);
		if(iCount>0) {
			System.out.println("[등록되었습니다.]");
		}else {
			System.out.println("[관리자에게 문의하세요(" + iCount + ")]");
		}
		
		
		//리스트출력     0x777
		//DB에서 데이터 가져오기
		authorList = authorDao.getAuthorList();
		//리스트를 for문으로 출력 --> 메소드로 정의
		printList(authorList);
		
		
		
		
		//작가수정  iCount 사용은 생략했음
		AuthorVo uAuthorVo = new AuthorVo(3, "김일영", "강남하이디미어");
		int uCount = authorDao.authorUpdate(uAuthorVo);
		
		//리스출력
		//DB에서 가져오기
		authorList = authorDao.getAuthorList();
		//리스트를 for문으로 출력 --> 메소드로 정의
		printList(authorList);
		
	
		
			
		//작가삭제
		
		int dCount = authorDao.authorDelete(7);
		
		//리스출력
		//DB에서 가져오기
		authorList = authorDao.getAuthorList();
		//리스트를 for문으로 출력 --> 메소드로 정의
		printList(authorList);
		
		
		/*
		/*
		//작가 1명의 정보
		??? = authorDao.getAuthorOne(3);
		*/

		
	}
	
	
	//리스트 출력 메소드
	public static void printList(List<AuthorVo> authorList) {
		
		for(int i=0; i<authorList.size(); i++) {
			
			
			AuthorVo authorVo = authorList.get(i);
			System.out.println(authorVo.getAuthorId() + "," + authorVo.getAuthorName() + "," + authorVo.getAuthorDesc());
			
			/*
			AuthorVo authorVo = authorList.get(i);
			
			int authorId = authorVo.getAuthorId();
			String authorName = authorVo.getAuthorName();
			String authorDesc = authorVo.getAuthorDesc();
			
			System.out.println(authorId + "\t" + authorName + "\t\t" + authorDesc);
			*/
			
			
			/*
			int authorId = authorList.get(i).getAuthorId();
			String authorName = authorList.get(i).getAuthorName();
			String authorDesc = authorList.get(i).getAuthorDesc();
			
			System.out.println(authorId + "\t" + authorName + "\t\t" + authorDesc);
			*/
		}
		
		System.out.println("=================================");
		System.out.println("");
		
	}
	
	
	
	

}