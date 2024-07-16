<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link type="text/css" rel="stylesheet" href='<c:url value="/css/login.css" />' />
    <title>회원가입 성공</title>
</head>
<body>
    <div>
        <h2>회원가입 성공</h2>
        <p>가입에 성공하셨습니다!</p>
        <a href="<c:url value="/login" />">로그인 페이지로 돌아가기</a>
    </div>
</body>
</html>
