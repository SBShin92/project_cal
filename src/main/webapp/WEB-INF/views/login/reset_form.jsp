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
	<h2>비밀번호 재설정</h2>
	<form action="<c:url value='/password/reset' />" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		<input type="hidden" name="token" value="${token}">
		<label for="newPassword">새로운 비밀번호</label>
		<input type="password" id="newPassword" name="newPassword" required>
		<button type="submit">재설정</button>
	</form>
</body>
</html>