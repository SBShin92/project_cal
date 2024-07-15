<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link type="text/css" rel="stylesheet" href='<c:url value="/css/login.css" />' />
    <title>로그인</title>
</head>
<body>
    <div class="login-container">
        <h2>로그인</h2>
        <c:if test="${param.error != null}">
            <div class="error-message">
                로그인에 실패했습니다. 다시 시도해주세요.
            </div>
        </c:if>
        <form action="<c:url value="/login" />" method="post">
            <div class="form-group">
                <label for="username">Email:</label>
                <input type="name" id="username" name="username" required>
            </div>
            <div class="form-group">
                <label for="password">비밀번호:</label>
                <input type="password" id="password" name="password" required>
            </div>
            <div class="form-group">
                <button type="submit">로그인</button>
            </div>
        </form>
        <a href="<c:url value="/join" />">회원가입</a>
    </div>
</body>
</html>
