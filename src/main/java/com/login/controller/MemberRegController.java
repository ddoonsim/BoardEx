package com.login.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.login.dao.MemberDao;

@WebServlet("/regProcess")
public class MemberRegController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 파라미터 수집
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String pw = request.getParameter("pw");
		
		// 회원 추가
		MemberDao dao = new MemberDao() ;
		int res = dao.newMember(id, name, email, pw) ;
		dao.close();
		
		if(res > 0) {
			// 로그인 성공
			request.setAttribute("msg", "회원가입 성공! 환영합니다🤗");
			request.setAttribute("url", "/login.jsp");
		} else {
			// 회원가입 실패 -> msg&뒤로가기
			request.setAttribute("msg", "회원가입 실패😢") ;
		}
		// 페이지 전환
		request.getRequestDispatcher("/msgbox.jsp").forward(request, response) ;
	}

}
