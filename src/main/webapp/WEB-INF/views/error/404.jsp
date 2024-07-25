<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>404 - 페이지를 찾을 수 없어요!</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f0f0f0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .container {
            text-align: center;
            background-color: white;
            padding: 2rem;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            max-width: 400px;
            width: 100%;
        }
        h1 {
            color: #ff6b6b;
            font-size: 24px;
        }
        p {
            color: #4a4a4a;
            font-size: 16px;
        }
        .character {
            width: 120px;
            margin: 1rem 0;
            animation: float 3s ease-in-out infinite;
        }
        @keyframes float {
            0%, 100% { transform: translateY(0); }
            50% { transform: translateY(-10px); }
        }
        .button {
            background-color: #4a4a4a;
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            text-decoration: none;
            transition: background-color 0.3s;
            display: inline-block;
            margin-top: 1rem;
        }
        .button:hover {
            background-color: #ff6b6b;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>404 - 페이지를 찾을 수 없어요!</h1>
        <p>앗! 우리의 귀여운 해골 친구도 </p>
        <p>당신이 찾는 페이지를 발견하지 못했어요.</p>
        <img class="character" src="<c:url value='/assets/images/skull.png' />" alt="귀여운 해골 캐릭터">
        <p>걱정마세요! 홈으로 돌아가면 새로운 모험을 시작할 수 있어요.</p>
        <a href="<c:url value='/calendar' />" class="button">홈으로 돌아가기</a>
    </div>
</body>
</html>