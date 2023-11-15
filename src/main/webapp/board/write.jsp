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
	form {
		padding-bottom: 10px ;
	}
</style>
</head>
<body>

<h2>✨새 글 작성하기</h2>
작성자 : <%= session.getAttribute("userId") %><br>
<hr>
<form name="newForm">
	제목 : <input type="text" name="title" placeholder="제목 입력"><br><br>
	내용 : <textarea rows="10" cols="100" name="content"></textarea>
	<hr>
	<button id="confirmBtn">확인</button>
</form>
<button id="cancelBtn">취소</button>

<script type="text/javascript">
	confirmBtn.addEventListener('click', function() {
		newForm.action = '/newWrite' ;
		newForm.submit() ;
	});
	
	cancelBtn.addEventListener('click', function() {
		history.back() ;
	});
</script>

</body>
</html>