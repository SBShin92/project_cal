<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link type="text/css" rel="stylesheet" href='<c:url value="/css/login.css" />' />
    <title>OurCalendar - Password_Reset</title>
</head>
<body>
	<h2>비밀번호 초기화</h2>
	<form action="<c:url value='/password/reset' />" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		<label for="email">이메일</label>
		<input type="email" id="email" name="email" required>
		<button type="submit">비밀번호 초기화 이메일 전송</button>
	</form>
		<c:if test="${not empty message}">
			<p>${message}</p>
		</c:if>
</body>
</html>