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
import com.login.dto.Criteria;
import com.login.dto.PageDto;

/**
 * 게시글 목록을 조회하고 반환
 */
@WebServlet("/list")
public class BoardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardDao dao = new BoardDao() ;
		
		// 페이지 번호와 페이지 당 게시물 개수를 설정수 조회할 게시물의 시작 번호와 끝 번호 가져오기
		Criteria cri = new Criteria(request.getParameter("pageNo"), request.getParameter("amount")) ;
		int total = dao.getTotalCnt() ;
		// 페이지 블럭을 생성하기 위해 PageDto를 생성 및 저장
		PageDto pageDto = new PageDto(total, cri) ;

		request.setAttribute("list", dao.getList(cri));
		request.setAttribute("pageDto", pageDto);
		
		dao.close();
		request.getRequestDispatcher("/board/board.jsp").forward(request, response);
	}

}
