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
	p {
		padding: 10px ;
	}
</style>
</head>
<body>

<h3>상세보기</h3>
<%
	if(request.getAttribute("one") != null) {
		BoardDto dto = (BoardDto)request.getAttribute("one") ;
%>
		<h2><%= dto.getTitle() %></h2>
		<p>작성자 : <%= dto.getId() %></p>
		<hr>
		<p><%= dto.getContent() %></p>
		<br>조회수 : <%= dto.getVisitcount() %><br>


<button type="button" id='listBtn' class="btn btn-outline-secondary btn-sm">목록</button>
<button type="button" id='editBtn' class="btn btn-outline-success btn-sm">수정</button>
<button type="button" class="btn btn-outline-danger btn-sm" data-bs-toggle="modal" data-bs-target="#exampleModal">
	삭제
</button>

<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5" id="exampleModalLabel">게시물 삭제</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        정말 삭제하시겠습니까?
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
        <button type="button" class="btn btn-primary" id='deleteBtn'>확인</button>
      </div>
    </div>
  </div>
</div>

<script type="text/javascript">
	listBtn.addEventListener('click', function() {
		location.href="/list" ;
	});
	
	editBtn.addEventListener('click', function() {
		location.href="/board/edit.jsp?num=<%= dto.getNum() %>" ;
	});
	
	deleteBtn.addEventListener('click', function() {
			location.href="/board/delete.jsp?num=<%= dto.getNum() %>" ;
	});
</script>

<% } %>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>

</body>
</html>