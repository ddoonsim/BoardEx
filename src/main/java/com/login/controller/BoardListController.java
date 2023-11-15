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
		
		// 페이징 처리
		int pageNo = 1 ;
		int amount = 10 ;
		// 전달된 값이 있으면 변경, 없으면 기본값
		if(request.getParameter("pageNo") != null && 
				!"".equals(request.getParameter("pageNo"))) {
			pageNo = Integer.parseInt(request.getParameter("pageNo")) ;
		}
		if(request.getParameter("amount") != null && 
				!"".equals(request.getParameter("amount"))) {
			amount = Integer.parseInt(request.getParameter("amount")) ;
		}
		// 시작 번호, 끝 번호
		int endNum = pageNo * amount ;
		int startNum = endNum - (amount - 1) ;
		
		List<BoardDto> list = dao.getList(startNum, endNum) ;
		dao.close();
		
		request.setAttribute("list", list);
		request.getRequestDispatcher("/board/board.jsp").forward(request, response);
	}

}
