<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link type="text/css" rel="stylesheet" href="<c:url value="/css/token.css" />" />
    <title>OurCalendar - 비밀번호 초기화 토큰 입력</title>
</head>
<body>
    <div class="token-verify-container">
        <h2>토큰 입력</h2>
        <form action="${pageContext.request.contextPath}/password/verifytoken" method="post">
            <label for="token">토큰:</label>
            <input type="text" id="token" name="token" required><br>
            <button type="submit">확인</button>
        </form>
        <c:if test="${not empty message}">
            <div>${message}</div>
        </c:if>
    </div>
</body>
</html>
