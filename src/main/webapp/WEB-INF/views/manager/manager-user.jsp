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
<title>프로젝트 관리 시스템 - 사용자 관리</title>
<link type="text/css" rel="stylesheet"
	href='<c:url value="/css/manager.css" />' />
</head>
<body>
	<button class="menu-toggle" onclick="toggleMenu()" aria-label="메뉴 토글">☰</button>
	<div class="container">
		<jsp:include page="/WEB-INF/includes/manager-nav.jsp" />
		<div class="main-content">
			<header class="header">
				<h1>사용자 관리</h1>
				<input type="text" id="searchBar" name="searchBar"
					class="search-bar" placeholder="프로젝트 또는 사용자 검색..." aria-label="검색">
			</header>
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
							<td class="editable" data-field="name">${user.userName}</td>
							<td class="editable" data-field="email">${user.userEmail}</td>
							<td>${user.userAuthority}</td>
							<td class="editable" data-field="position">${user.userPosition}</td>
							<td>
								<button class="btn btn-edit" data-user-id="${user.userId}">수정</button>
								
								<form id="deleteForm_${user.userId}" class="deleteForm"
									action="<c:url value='/manager/users/delete/${user.userId}'/>"
									method="post" style="display: inline;">
									<input class="btn btn-delete" type="submit" value="삭제">
								</form>
							</td>
						</tr>
					</c:forEach>

				</tbody>
			</table>
			<!-- <a href="<c:url value='/manager/users/add'/>" class="btn btn-add">새 사용자 추가</a> -->
		</div>
	</div>
	<script src="<c:url value='/javascript/manager.js'/>"></script>
</body>
</html>