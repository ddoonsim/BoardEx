package com.login.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.login.dao.MemberDao;
import com.login.dto.MemberDto;

// clean... 한 이후에 context root 확인!!!
@WebServlet("/loginAction")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * 로그인 처리 
	 *  - 요청 데이터 확인
	 *  	id, pw가 일치하는 사용자가 있는지 확인 
	 *  	 - 사용자가 있으면 => 로그인 성공 -> 사용자 정보를 세션에 저장 후 board.jsp 페이지로 이동
	 *  	 - 사용자가 없으면 => 로그인 실패 -> 'id, 비밀번호를 확인해주세요' 메세지 띄우고 이전페이지로 이동
	 *  - 요청 메서드에 따라 doGet, doPost 메서드가 실행
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 요청 데이터 수집
		String id = request.getParameter("id") ;
		String pw = request.getParameter("pw") ;
		
		// 2. 사용자 정보 조회(MemeberDao.login(id, pw))
		MemberDao dao = new MemberDao() ;
		MemberDto user = dao.login(id, pw) ;
		dao.close();
		
		// 3. 화면 전환
		if(user != null) {
			// 로그인 성공
			HttpSession session = request.getSession() ;
			session.setAttribute("userId", user.getId());
			session.setAttribute("memberDto", user);
			
			// 서블릿을 요청 시 주의 사항
			// forward 방식으로 페이지를 전환할 시 405 오류가 발생할 수 있다.
			// /loginAction -> post
			// /list -> get
			// 요청 받을 당시 방식(method)가 유지되므로 리다이렉트 방식을 사용하면 이런 에러를 예방할 수 있다.
			response.sendRedirect("/list");
			// request.getRequestDispatcher("/list").forward(request, response);
		} else {
			// 로그인 실패
			request.setAttribute("msg", "아이디/비밀번호를 확인해주세요.") ;
			request.getRequestDispatcher("/msgbox.jsp").forward(request, response) ;
//			response.setContentType("text/html; charset=UTF-8") ;
//			response.getWriter().append("<script>") ;
//			response.getWriter().append("	alert('아이디/비밀번호를 확인해주세요.') ;") ;
//			response.getWriter().append("	history.back() ;") ;
//			response.getWriter().append("</script>") ;
		}
	}

}
