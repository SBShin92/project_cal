<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt"%>
<%@ page session="true"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link type="text/css" rel="stylesheet"
	href='<c:url value="/css/manager.css" />' />
</head>
<body>
	<button class="menu-toggle" onclick="toggleMenu()">☰</button>
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
				<input type="text" class="search-bar"
					placeholder="프로젝트 또는 사용자 검색...">
			</div>

			<div class="tab">
				<a class="tablinks" href="<c:url value='/manager/projects' />">프로젝트</a>
				<a class="tablinks" href="<c:url value='/manager/users' />">사용자</a>
				<a class="tablinks" href="<c:url value='/manager/roles' />">권한</a>
			</div>
			<h1>사용자 관리</h1>
			<table>
				<thead>
					<tr>
						<th>ID</th>
						<th>이름</th>
						<th>이메일</th>
						<th>권한</th>
						<th>직책</th>
						<th>작업</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="user" items="${userVOs}">
						<tr>
							<td>${user.userId}</td>
							<td>${user.userName}</td>
							<td>${user.userEmail}</td>
							<td>${user.userAuthority}</td>
							<td>${user.userPosition}</td>
							<td><a
								href="<c:url value='/manager/users/edit/${user.userId}'/>"
								class="btn btn-edit">수정</a> 
								
								<a href="<c:url value='/manager/users/delete/${user.userId}'/>"
								class="btn btn-delete" data-user-id="${user.userId}"
								onclick="return confirmDelete(${user.userId});">삭제</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<!-- a href="<c:url value='/manager/users/add'/>" class="btn btn-add">새 사용자 추가</a> -->
		</div>
		<script src="<c:url value='/js/manager.js'/>"></script>
</body>
</html>