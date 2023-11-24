package com.login.dao;

import java.sql.SQLException;

import com.login.common.DBConnPool;
import com.login.dto.MemberDto;

public class MemberDao extends DBConnPool{
	
	/**
	 * id, pw를 전달 받아 DB에 등록된 사용자가 있는지 조회 후 MemberDto를 반환
	 * @param id
	 * @param pw
	 * @return
	 */
	public MemberDto login(String id, String pw) {
		MemberDto dto = null ;
		String sql = "select * from member" ;
		
		try {
			stmt = con.createStatement() ;
			rs = stmt.executeQuery(sql) ;
			
			while(rs.next()) {
				if(id.equals(rs.getString("id")) && pw.equals(rs.getString("pass"))) {
					// 로그인 성공
					dto.setId(rs.getString("id"));
					dto.setPass(rs.getString("pass"));
					dto.setName(rs.getString("name"));
					dto.setRegidate(rs.getString("regidate"));
					dto.setEmail(rs.getString("email"));
				}
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return dto ;
	}
	
//	public MemberDto login2(String id, String pw) {
//		MemberDto dto = null ;
//		
//		try {
//			pstmt = con.prepareStatement("select * member where id = ? and pass = ?") ;
//			pstmt.setString(1, id);
//			pstmt.setString(2,  pw);
//			
//			rs = pstmt.executeQuery() ;
//			
//			if(rs.next()) {
//				// 로그인 성공
//				String name = rs.getString("name") ;
//				String regidate = rs.getString("regidate") ;
//				
//				// memberDto객체를 생성 후 반환
//				dto = new MemberDto() ;
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return dto ;
//	}
	
	/**
	 * Member 테이블에 회원 추가
	 */
	public void newMember(String id, String name, String email, String pw) {
		String sql = "insert into member (id, pass, name, regidate, email)\r\n"
				+ "values('"+id+"', '"+pw+"', '"+name+"', sysdate, '"+email+"')" ;
		
		try {
			stmt = con.createStatement() ;
			int n = stmt.executeUpdate(sql) ;
			System.out.println("Member테이블에 " + n + "건 추가되었습니다.");
		} 
		catch (SQLException e) {
			System.out.println("회원 추가 쿼리 실행 중 예외 발생");
			e.printStackTrace();
		}
		
	}

}
