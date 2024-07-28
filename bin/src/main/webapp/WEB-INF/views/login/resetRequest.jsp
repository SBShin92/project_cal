<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link type="text/css" rel="stylesheet" href="<c:url value="/css/token.css" />" />
    <title>OurCalendar - 비밀번호 초기화 요청</title>
</head>
<body>
    <div class="password-reset-request-container">
        <h2>비밀번호 초기화</h2>
        <form action="${pageContext.request.contextPath}/password/resetrequest" method="post">
            <label for="email">이메일:</label>
            <input type="email" id="email" name="email" required><br>
            <button type="submit">비밀번호 초기화 이메일 전송</button>
        </form>
        <c:if test="${not empty message}">
            <div>${message}</div>
        </c:if>
    </div>
</body>
</html>
