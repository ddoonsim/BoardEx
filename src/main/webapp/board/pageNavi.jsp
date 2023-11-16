<%@page import="com.login.dto.Criteria"%>
<%@page import="com.login.dto.PageDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 부트스트랩을 사용하기 위해 cdn 추가 -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
<style type="text/css">
	body {
		margin: 100px ;
	}
	nav{
		padding: 20px ;
	}
</style>
</head>
<body>

<!-- 페이지네이션 시작 -->
<!-- class를 추가하여 화면을 구성
	active : 현재 페이지를 활성화
	disabled : 버튼의 비활성화
-->
<%
	// 페이지네비게이션을 생성하기 위해 필요한 데이터
	if(request.getAttribute("pageDto") != null &&
				!"".equals(request.getAttribute("pageDto"))) {
		PageDto pageDto = (PageDto)request.getAttribute("pageDto") ;
%>

<nav aria-label="...">
  <ul class="pagination justify-content-center">
  	<!-- 이전 페이지 블럭으로 이동 시작 -->
    <li class="page-item <%= pageDto.isPrev() ? "" : "disabled" %>">
      <a class="page-link" href="/list?pageNo=<%= pageDto.getStartNo() - 1 %>">Previous</a>
    </li>
    <!-- 이전 페이지 블럭으로 이동 끝 -->
    
    <!-- 페이지 버튼 시작 -->
    <%
    	// 페이지 번호를 출력하기 위해 반복문 사용
    	for(int i = pageDto.getStartNo(); i <= pageDto.getEndNo(); i++) {
    %>
    <li class="page-item <%= pageDto.getCri().getPageNo() == i ? "active" : "" %>">
    	<a class="page-link" href="/list?pageNo=<%= i %>"><%= i %></a>
    </li>
    <% } %>
    <!-- 페이지 버튼 끝 -->
    
    <!-- 다음 페이지 블럭으로 이동 시작 -->
    <li class="page-item <%= pageDto.isNext() ? "" : "disabled" %>">
      <a class="page-link" href="/list?pageNo=<%= pageDto.getEndNo() + 1 %>">Next</a>
    </li>
    <!-- 다음 페이지 블럭으로 이동 끝 -->
  </ul>
</nav>

<% } %>

<!-- 부트스트랩을 사용하기 위해 자바스크립트 cdn 추가 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>

</body>
</html>