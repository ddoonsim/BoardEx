package com.login.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.login.dao.BoardDao;

@WebServlet("/detail")
public class BoardDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 게시물 일련번호 얻어오기
		String num = request.getParameter("num") ;
		// 게시물 1개 호출
		BoardDao dao = new BoardDao() ;
		// 조회수 +1
		dao.plusVisitCount(num) ;
		request.setAttribute("one", dao.getOne(num));
		dao.close();
		
		// 페이지 전환
		request.getRequestDispatcher("/board/boardDetail.jsp").forward(request, response);
		
	}

}
