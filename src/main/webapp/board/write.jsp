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
	<button type="button" id="confirmBtn" class="btn btn-outline-success btn-sm">확인</button>
</form>
<button type="button" id="cancelBtn" class="btn btn-outline-danger btn-sm">취소</button>

<script type="text/javascript">
	confirmBtn.addEventListener('click', function() {
		newForm.action = '/newWrite' ;
		newForm.submit() ;
	});
	
	cancelBtn.addEventListener('click', function() {
		history.back() ;
	});
</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>

</body>
</html>