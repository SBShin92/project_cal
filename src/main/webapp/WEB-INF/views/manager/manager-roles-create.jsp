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
<title>OurCalendar - 사용자 ${role.roleId == null ? '생성' : '수정'}</title>
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
			<h2>사용자 ${role.roleId == null ? '생성' : '수정'}</h2>
			<form
				action="<c:url value='/manager/users/${role.roleId == null ? "create" : "edit/"}${role.roleId}' />"
				method="post">
				<div>
					<label for="roleName">역할 이름:</label> <input type="text"
						id="roleName" name="roleName" value="${role.roleName}" required>
				</div>
				<div>
					<label for="userEmail">이메일:</label> <input type="email"
						id="userEmail" name="userEmail" value="${role.userEmail}" required>
				</div>
				<div>
					<label for="userAuthority">권한:</label> <select id="userAuthority"
						name="userAuthority">
						<option value="ROLE_USER"
							${role.userAuthority == 'ROLE_USER' ? 'selected' : ''}>일반
							사용자</option>
						<option value="ROLE_ADMIN"
							${role.userAuthority == 'ROLE_ADMIN' ? 'selected' : ''}>관리자</option>
					</select>
				</div>
				<button type="submit" class="btn">${role.roleId == null ? '생성' : '수정'}</button>
				<a href="<c:url value='/manager/users' />" class="btn">취소</a>
			</form>
		</div>
	</div>
</body>
</html>