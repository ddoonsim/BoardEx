package com.login.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.login.dao.BoardDao;

@WebServlet("/update")
public class EditController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String num = request.getParameter("num") ;
		String title = request.getParameter("title") ;
		String content = request.getParameter("content") ;
		
		BoardDao dao = new BoardDao() ;
		dao.edit(num, title, content);
		
		request.getRequestDispatcher("/list").forward(request, response);
	}

}
