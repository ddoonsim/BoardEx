package com.login.dao;

import java.sql.SQLException;

import com.login.common.DBConnPool;

public class HelloDao extends DBConnPool{

	/**
	 * DB로부터 현재 시간을 조회 후 반환하는 메서드
	 * @return
	 */
	public String getTime() {
		String time= "" ;
		String sql = "select sysdate from dual" ;
		try {
			stmt = con.createStatement() ;
			rs = stmt.executeQuery(sql) ;
			if(rs.next()) {
				// 변수에 담아서 반환
				time = rs.getString(1) ;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return time ;
	}
}
