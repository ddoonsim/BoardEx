<%@page import="java.util.List"%>
<%@page import="com.login.dto.BoardDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
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
				// alert('loginBtn 클릭') ;
				location.href="/login.jsp" ;
			});
		}
		let createNew = document.querySelector('#createNew') ;
		if(createNew != null) {
			createNew.addEventListener('click', function() {
				// alert('새 글 작성') ;
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
		out.print("<button type='button' id='logoutBtn' class='btn btn-outline-secondary btn-sm'>로그아웃</button>") ;
	} else {
		// 로그인 전
		out.print("<button type='button' id='loginBtn' class='btn btn-outline-warning btn-sm'>로그인</button>") ;
	}
%>


<hr>
<h2>📋게시판</h2>

<table class="table table-hover">
  <thead>
    <tr>
      <th scope="col">번호</th>
      <th scope="col">제목</th>
      <th scope="col">작성자</th>
      <th scope="col">작성일</th>
      <th scope="col">조회수</th>
    </tr>
  </thead>
  <tbody>
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
  </tbody>
</table>

<br>
<button type="button" class="btn btn-outline-dark btn-sm" id='createNew'>새 글 작성✨</button>
<!-- <button id='createNew'>새 글 작성✨</button> -->
<br><hr>

<%@include file="pageNavi.jsp" %>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>

</body>
</html>