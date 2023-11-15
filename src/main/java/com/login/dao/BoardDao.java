package com.login.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.login.common.DBConnPool;
import com.login.dto.BoardDto;

public class BoardDao extends DBConnPool{
	
	/**
	 * DB로부터 게시글의 목록을 조회하여 list에 담아 반환하는 메서드
	 * @return List<BoardDto>타입
	 */
	public List<BoardDto> getList() {
		List<BoardDto> list = new ArrayList<>() ;
		
		String sql = "select * from board order by num" ;
		try {
			stmt = con.createStatement() ;
			rs = stmt.executeQuery(sql) ;
			
			while(rs.next()) {
				BoardDto dto = new BoardDto() ;
				dto.setNum(rs.getString(1));
				dto.setTitle(rs.getString(2));
				dto.setContent(rs.getString(3));
				dto.setId(rs.getString(4));
				dto.setPostdate(rs.getString(5));
				dto.setVisitcount(rs.getString(6));
				
				list.add(dto) ;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list ;
	}
	
	/**
	 * 새 글 작성 메서드
	 */
	public void newWrite(String title, String content, String id) {
		String sql = "insert into board  (num, title, content, id, postdate, visitcount) \r\n"
				+ "	values (seq_board_num.nextval, ?, ?, ?, sysdate, 0)" ;
		
		try {
			pstmt = con.prepareStatement(sql) ;
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setString(3, id);
			
			pstmt.executeUpdate() ;
			System.out.println("게시물을 생성했습니다.");
			
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 지정한 게시글 1개 얻는 메서드 
	 * @param num
	 * @return
	 */
	public BoardDto getOne(String num) {
		BoardDto dto = new BoardDto() ;
		String sql = "select * from board where num = ?" ;
		
		try {
			pstmt = con.prepareStatement(sql) ;
			pstmt.setString(1, num);
			rs = pstmt.executeQuery() ;
			
			if(rs.next()) {
				dto.setNum(rs.getString(1));
				dto.setTitle(rs.getString(2));
				dto.setContent(rs.getString(3));
				dto.setId(rs.getString(4));
				dto.setPostdate(rs.getString(5));
				dto.setVisitcount(rs.getString(6));
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return dto ;
	}
	
	/**
	 * 조회수 +1 하는 메서드
	 * @param num
	 */
	public void plusVisitCount(String num) {
		String sql = "update board set visitcount = visitcount + 1 where num = ?" ;
		
		try {
			pstmt = con.prepareStatement(sql) ;
			pstmt.setString(1, num);
			
			int cnt = pstmt.executeUpdate() ;
			
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 게시글 수정
	 */
	public void edit(String num, String title, String content) {
		String sql = "update board set title = ?, content = ? where num = ?" ;
		
		try {
			pstmt = con.prepareStatement(sql) ;
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setString(3, num);
			
			pstmt.executeUpdate() ;
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 게시글 삭제하는 메서드
	 */
	public void delete(String num) {
		String sql = "delete from board where num = ?" ;
		
		try {
			pstmt = con.prepareStatement(sql) ;
			pstmt.setString(1, num);
			pstmt.executeUpdate() ;
			System.out.println("게시물을 삭제했습니다.");
		} 
		catch (SQLException e) {
			System.out.println("⚠️삭제 중 예외가 발생하였습니다.");
			e.printStackTrace();
		}
	}

}
