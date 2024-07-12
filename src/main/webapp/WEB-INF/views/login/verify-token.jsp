<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link type="text/css" rel="stylesheet" href='<c:url value="/css/login.css" />' />
    <title>OurCalendar - Login</title>
</head>
<body>
    <h2>Verify Token</h2>
    <form method="post" action="<c:url value='/verify-token'/>">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <input type="hidden" name="email" value="${email}"> <!-- 이메일 필드 (숨김) -->
        <div>
            <label for="token">Token:</label>
            <input type="text" id="token" name="token" required> <!-- 토큰 입력 필드 -->
        </div>
        <div>
            <button type="submit">Verify Token</button> <!-- 토큰 검증 버튼 -->
        </div>
        <c:if test="${not empty error}">
            <div class="error">${error}</div> <!-- 오류 메시지 표시 -->
        </c:if>
    </form>
</body>
</html>
