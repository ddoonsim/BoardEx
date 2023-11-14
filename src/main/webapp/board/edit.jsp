<%@page import="com.login.dto.BoardDto"%>
<%@page import="com.login.dao.BoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h2>게시글 수정</h2>
<hr>

<%
	String num = request.getParameter("num") ;
	BoardDao dao = new BoardDao() ;
	BoardDto dtoOne = dao.getOne(num) ;
%>

<form action="/update" name="editForm">
	<p style=display:none>게시글 번호:
		<input type="text" name="num" id="num" value="<%= dtoOne.getNum() %>"></p>
	제목 : <input type="text" name="title" id="title" value="<%= dtoOne.getTitle() %>"><br>
	내용 : <input type="text" name="content" id="content" value="<%= dtoOne.getContent() %>">
	<br>
	<hr>
	<button id='confirmBtn'>확인</button>
</form>

<script>
	confirmBtn.onclick = function(){
		alert('게시글을 수정하시겠습니까?') ;
		editForm.submit() ;
	};
</script>

</body>
</html>