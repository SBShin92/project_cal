<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link type="text/css" rel="stylesheet" href="<c:url value="/css/token.css" />" />
    <title>OurCalendar - Verify Token</title>
</head>
<body>
    <h2>Verify Token</h2>
    <form action="${pageContext.request.contextPath}/login/token" method="post">
        <label for="token">Token:</label>
        <input type="text" id="token" name="token" required><br>
        <button type="submit">확인</button>
    </form>
    <c:if test="${not empty error}">
        <div>${error}</div>
    </c:if>
</body>
</html>
