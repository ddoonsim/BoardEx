<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

<h2>로그인!</h2>

<c:if test="${ success eq '1' }">
	<script type="text/javascript">
		alert('회원가입되었습니다😊 \r\n 로그인 후 진행해주세요!') ;
	</script>
</c:if>

<!-- 
	get방식 : queryString을 이용해 데이터를 전달(주소를 이용)
	post방식 : body영역을 통해 데이터를 전달
 -->

<form action="/loginAction" method="post">
	<div class='loginbox'>
		<div id='id'>
		        아이디 : <input type="text" name="id" id="id" required="required" value="test"/><br />
		        패스워드 : <input type="password" name="pw" id="pw" required="required" value="1234"/><br />
		</div>
	</div>        
	<div id='button'>
		<input type="submit" value="로그인" />
	</div>
</form> 

</body>
</html>