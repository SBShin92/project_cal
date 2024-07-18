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
</head>
<body>
	<button class="menu-toggle">☰</button>
	<div class="container">
		<div class="sidebar" id="sidebar">
			<h2>관리자 메뉴</h2>
			<div class="menu-item">대시보드</div>
			<div class="menu-item">프로젝트 관리</div>
			<div class="menu-item">사용자 관리</div>
			<div class="menu-item">권한 관리</div>
			<div class="menu-item">보고서</div>
			<div class="menu-item">설정</div>
		</div>
		<div class="main-content">
			<div class="header">
				<h1>프로젝트 관리 시스템</h1>
			</div>

			<h2>사용자 상세 정보</h2>
			<table>
				<tr>
					<th>역할 이름</th>
					<td>${role.roleName}</td>
				</tr>
				<tr>
					<th>이메일</th>
					<td>${role.userEmail}</td>
				</tr>
				<tr>
					<th>권한</th>
					<td>${role.userAuthority}</td>
				</tr>
				<tr>
					<th>프로젝트 생성 권한</th>
					<td>${role.projectCreate ? '있음' : '없음'}</td>
				</tr>
				<tr>
					<th>프로젝트 읽기 권한</th>
					<td>${role.projectRead ? '있음' : '없음'}</td>
				</tr>
				<tr>
					<th>프로젝트 수정 권한</th>
					<td>${role.projectUpdate ? '있음' : '없음'}</td>
				</tr>
				<tr>
					<th>프로젝트 삭제 권한</th>
					<td>${role.projectDelete ? '있음' : '없음'}</td>
				</tr>
				<!-- 나머지 권한들도 같은 방식으로 표시 -->
			</table>
			<form action="<c:url value='/manager/users/delete/${role.roleId}' />"
				method="post" style="display: inline;">
				<button type="submit" class="btn delete-role"
					onclick="return confirm('정말로 이 사용자를 삭제하시겠습니까?');">삭제</button>
			</form>
			<a href="<c:url value='/manager/users/edit/${role.roleId}' />"
				class="btn edit-role" data-role-id="${role.roleId}">수정</a> <a
				href="<c:url value='/manager/users' />" class="btn">목록으로</a>
		</div>
	</div>
</body>
</html>