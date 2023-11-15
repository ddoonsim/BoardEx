<%@page import="java.util.List"%>
<%@page import="com.login.dto.BoardDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	body {
		margin: 100px ;
	}
	table, tr, th, td {
		border : 1px solid lightgray ;
		border-collapse: collapse;
		text-align: center ;
	}
	table {
		width: 700px ;
		height: 200px ;
	}
	a {
		text-decoration: none ;
	}
</style>
</head>
<body>

<script>
	window.onload = function() {
		let logoutBtn = document.querySelector('#logoutBtn') ;
		if(logoutBtn != null) {
			logoutBtn.onclick = function() {
				// alert('logoutBtn 클릭') ;
				location.href="/logoutAction" ;
			};
		}
		let loginBtn = document.querySelector('#loginBtn') ;
		if(loginBtn != null) {
			loginBtn.addEventListener('click', function() {
				alert('loginBtn 클릭') ;
				location.href="/login.jsp" ;
			});
		}
		let createNew = document.querySelector('#createNew') ;
		if(createNew != null) {
			createNew.addEventListener('click', function() {
				alert('새 글 작성') ;
				location.href="/board/write.jsp" ;
			});
		}
	}
</script>

<%
	if(session.getAttribute("userId") != null ) {
		// 로그인 사용자
		out.print("<h3>로그인 성공!</h3>") ;
		out.print(session.getAttribute("userId").toString() + "님 환영합니다😊") ;
		out.print("<button id='logoutBtn'>로그아웃</button>") ;
	} else {
		// 로그인 전
		out.print("<button id='loginBtn'>로그인</button>") ;
	}
%>


<hr>
<h2>📋게시판</h2>

<table>
	<tr>
		<th>일련번호</th>
		<th>제목</th>
		<th>작성자 아이디</th>
		<th>작성일</th>
		<th>조회수</th>
	</tr>

<%
	if(request.getAttribute("list") != null) {
		List<BoardDto> list = (List<BoardDto>)request.getAttribute("list") ;
	
		for(BoardDto dto : list) {
	%>
			<tr>
				<td><%= dto.getNum() %></td>
				<td><a href="/detail?num=<%= dto.getNum() %>"><%= dto.getTitle() %></a></td>
				<td><%= dto.getId() %></td>
				<td><%= dto.getPostdate() %></td>
				<td><%= dto.getVisitcount() %></td>
			</tr>
	<%
		}
	}
%>
</table>
<br><hr>
<button id='createNew'>새 글 작성✨</button>

</body>
</html>