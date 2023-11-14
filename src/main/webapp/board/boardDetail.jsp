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
</style>
</head>
<body>

<%
	if(request.getAttribute("one") != null) {
		BoardDto dto = (BoardDto)request.getAttribute("one") ;
%>
		<h2><%= dto.getTitle() %></h2>
		<p>작성자 : <%= dto.getId() %></p>
		<hr>
		<p><%= dto.getContent() %></p>
		<br>조회수 : <%= dto.getVisitcount() %><br>


<button id='listBtn'>목록</button>
<button id='editBtn'>수정</button>
<button id='deleteBtn'>삭제</button>

<script type="text/javascript">
	listBtn.addEventListener('click', function() {
		location.href="/list" ;
	});
	
	editBtn.addEventListener('click', function() {
		location.href="/board/edit.jsp?num=<%= dto.getNum() %>" ;
	});
	
	deleteBtn.addEventListener('click', function() {
		alert('게시물을 삭제합니다!') ;
		location.href="/board/delete.jsp?num=<%= dto.getNum() %>" ;
	});
</script>

<% } %>

</body>
</html>