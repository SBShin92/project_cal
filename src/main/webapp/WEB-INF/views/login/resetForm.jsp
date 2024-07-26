<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link type="text/css" rel="stylesheet" href="<c:url value="/css/token.css" />" />
    <title>비밀번호 재설정</title>
</head>
<body>
    <div class="password-reset-container">
        <h2>비밀번호 재설정</h2>
        <form action="${pageContext.request.contextPath}/password/reset" method="post">
            <input type="hidden" name="token" value="${token}">
            <label for="password">새 비밀번호:</label>
            <input type="password" id="password" name="password" required><br>
            <button type="submit">비밀번호 재설정</button>
        </form>
        <c:if test="${not empty message}">
            <div>${message}</div>
        </c:if>
    </div>
</body>
</html>
