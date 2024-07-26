<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link type="text/css" rel="stylesheet" href="<c:url value="/css/token.css" />" />
    <title>OurCalendar - Login</title>
</head>
<body>
   <div class="container">
   		<h2> 토큰이 이메일로 발송되었습니다</h2>
        <p>이메일에서 토큰을 확인해 주세요</p>
        <a href="<c:url value='/login' />" class="button">로그인페이지로 돌아가기</a>
    </div>
</body>
</html>