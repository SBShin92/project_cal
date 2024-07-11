<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>helllllllo</h1>
	<h2>SWAGGER-UI? : <a href="<c:url value='/swagger-ui.html' />">여기</a></h2>
	<h2>api JSON 페이지? : <a href="<c:url value='/api/users' />">여기</a></h2>
	<h2>Users페이지에 더미 추가 : <a href="<c:url value='/api/users/add' />">여기</a></h2>
	<br/>
	<h2>Calendar 가기 : <a href="<c:url value='/calendar' />">여기</a></h2>
	<h2>Project 가기 : <a href="<c:url value='/project' />">여기</a></h2>
</body>
</html>