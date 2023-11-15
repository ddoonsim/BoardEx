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

<h2>게시글 삭제</h2>

<%
	String num = request.getParameter("num") ;
	BoardDao dao = new BoardDao() ;
	int res = dao.delete(num) ;
	
	out.print("<script type='text/javascript'>") ;
	out.print("	alert('게시물을 " + res + "건 삭제했습니다.') ;") ;
	out.print("	location.href='/list' ;") ;
	out.print("</script>") ;
	
%>

</body>
</html>