package com.login.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.login.dao.BoardDao;
import com.login.dto.BoardDto;

/**
 * 게시글 목록을 조회하고 반환
 */
@WebServlet("/list")
public class BoardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardDao dao = new BoardDao() ;
		List<BoardDto> list = dao.getList() ;
		dao.close();
		
		request.setAttribute("list", list);
		request.getRequestDispatcher("/board/board.jsp").forward(request, response);
	}

}
