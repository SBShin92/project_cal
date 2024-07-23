<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ page session="true"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link type="text/css" rel="stylesheet"
	href='<c:url value="/css/manager.css" />' />
<script src="<c:url value='/js/manager.js' />"></script>
<title>OurCalendar - 사용자 상세</title>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link type="text/css" rel="stylesheet" href='<c:url value="/css/manager.css" />' />
    <script src="<c:url value='/js/manager.js' />"></script>
    <title>OurCalendar - 사용자 상세</title>
</head>
<body>
    <button class="menu-toggle">☰</button>
    <div class="container">
        <jsp:include page="/WEB-INF/includes/manager-nav.jsp" />
        <div class="main-content">
            <div class="header">
                <h1>사용자 관리 시스템</h1>
            </div>

            <h2>사용자 상세 정보</h2>
            <table>
                <tr>
                    <th>사용자 ID</th>
                    <td>${user.userId}</td>
                </tr>
                <tr>
                    <th>이름</th>
                    <td>${user.userName}</td>
                </tr>
                <tr>
                    <th>이메일</th>
                    <td>${user.userEmail}</td>
                </tr>
                <tr>
                    <th>직책</th>
                    <td>${user.userPosition}</td>
                </tr>
                <tr>
                    <th>권한</th>
                    <td>${user.userAuthority}</td>
                </tr>
                <tr>
                    <th>프로젝트 생성 권한</th>
                    <td>${user.role.projectCreate ? '있음' : '없음'}</td>
                </tr>
                <tr>
                    <th>프로젝트 읽기 권한</th>
                    <td>${user.role.projectRead ? '있음' : '없음'}</td>
                </tr>
                <tr>
                    <th>프로젝트 수정 권한</th>
                    <td>${user.role.projectUpdate ? '있음' : '없음'}</td>
                </tr>
                <tr>
                    <th>프로젝트 삭제 권한</th>
                    <td>${user.role.projectDelete ? '있음' : '없음'}</td>
                </tr>
            </table>
            <div class="button-group">
                <a href="<c:url value='/manager/user/edit/${user.userId}' />" class="btn edit-user">수정</a>
                <form action="<c:url value='/manager/user/delete/${user.userId}' />" method="post" style="display: inline;">
                    <button type="submit" class="btn delete-user" onclick="return confirm('정말로 이 사용자를 삭제하시겠습니까?');">삭제</button>
                </form>
                <a href="<c:url value='/manager/users' />" class="btn">목록으로</a>
            </div>
        </div>
    </div>
</body>
</html>