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
				if(id.equals(rs.getString(1)) && pw.equals(rs.getString(2))) {
					dto = new MemberDto() ;
					dto.setId(rs.getString(1));
					dto.setPass(rs.getString(2));
					dto.setName(rs.getString(3));
					dto.setRegidate(rs.getString(4));
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

}
