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
		// 회원가입 성공 -> msg&로그인페이지.jsp
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String pw = request.getParameter("pw");
		
		MemberDao dao = new MemberDao() ;
		dao.newMember(id, name, email, pw) ;
		dao.close();
		
		request.setAttribute("success", "1");
		request.getRequestDispatcher("/lib/loginForm.jsp").forward(request, response) ;
		
		// 회원가입 실패 -> msg&뒤로가기
		request.setAttribute("msg", "회원정보가 올바르지 않습니다.") ;
		request.getRequestDispatcher("/msgbox.jsp").forward(request, response) ;
	}

}
