package com.login.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.login.common.DBConnPool;
import com.login.dto.BoardDto;
import com.login.dto.Criteria;

public class BoardDao extends DBConnPool{
	
	/**
	 * DB로부터 게시글의 목록을 조회하여 list에 담아 반환하는 메서드
	 * + 페이징 처리
	 * @return List<BoardDto>타입
	 */
	public List<BoardDto> getList(Criteria cri) {
		List<BoardDto> list = new ArrayList<>() ;
		
		String sql = "select *\r\n"
				+ "from (select rownum rnum, b.*\r\n"
				+ "        from (select * from board \r\n"
				+ "                order by num desc) b)\r\n"
				+ "where rnum between ? and ?" ;
		
		try {
			pstmt = con.prepareStatement(sql) ;
			pstmt.setInt(1, cri.getStartNo());    // 시작 번호 = 끝 번호 -(페이지 당 게시물 개수 - 1)
			pstmt.setInt(2, cri.getEndNo());   // 끝 번호 = 페이지 번호 * 페이지 당 게시물 개수
			
			rs = pstmt.executeQuery() ;
			
			while(rs.next()) {
				BoardDto dto = new BoardDto() ;
				
				dto.setNum(rs.getString("NUM"));
				dto.setTitle(rs.getString("TITLE"));
				dto.setContent(rs.getString("CONTENT"));
				dto.setId(rs.getString("ID"));
				dto.setPostdate(rs.getString("POSTDATE"));
				dto.setVisitcount(rs.getString("VISITCOUNT"));
				
				list.add(dto) ;
			}
		} catch (SQLException e) {
			System.out.println("getList()메서드 예외 발생");
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
	public int delete(String num) {
		int res = 0 ;
		String sql = "delete from board where num = ?" ;
		
		try {
			pstmt = con.prepareStatement(sql) ;
			pstmt.setString(1, num);
			res = pstmt.executeUpdate() ;
		} 
		catch (SQLException e) {
			System.out.println("⚠️삭제 중 예외가 발생하였습니다.");
			e.printStackTrace();
		}
		return res ;
	}

	/**
	 * 게시물의 총 개수를 구하는 메서드
	 * @return
	 */
	public int getTotalCnt() {
		int total = 0 ;
		String sql = "select count(*) from board" ;
		
		try {
			pstmt = con.prepareStatement(sql) ;
			rs = pstmt.executeQuery() ;
			if(rs.next()) {
				total = rs.getInt(1) ;
			}
		} catch (SQLException e) {
			System.out.println("게시물 총 개수를 구하는 과정에서 에러 발생");
			e.printStackTrace();
		}
		return total;
	}

}
