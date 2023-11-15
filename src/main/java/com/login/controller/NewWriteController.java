package com.login.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.login.dao.BoardDao;

@WebServlet("/newWrite")
public class NewWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession() ;
		
		// 제목, 내용, 아이디 가져오기
		String title = request.getParameter("title") ;
		String content = request.getParameter("content") ;
		String id = session.getAttribute("userId").toString() ;
		
		BoardDao dao = new BoardDao() ;
		dao.newWrite(title, content, id);
		dao.close();
		
		// 페이지 전환
		request.getRequestDispatcher("/list").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
