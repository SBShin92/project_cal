<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ page session="true"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link type="text/css" rel="stylesheet" href='<c:url value="/css/manager.css" />' />
<script src="<c:url value='/js/manager.js' />"></script>
<title>OurCalendar - 사용자 생성</title>
</head>
<body>
	<button class="menu-toggle">☰</button>
	<div class="container">
		<jsp:include page="/WEB-INF/includes/manager-nav.jsp" />
		<div class="main-content">
			<div class="header">
				<h1>새 권한 그룹 생성</h1>
			</div>
			<h2>권한 생성</h2>
			<form action="<c:url value='/manager/role/create' />" method="post">
				<div>
					<label for="roleName">권한 이름:</label>
					<input type="text" id="roleName" name="roleName" required>
				</div>
				<div>
					<label for="roleDescription">권한 설명:</label>
					<input type="text" id="roleDescription" name="roleDescription" required>
				</div>
				<div>
					<input type="checkbox" name="projectCreate" id="projectCreate" value="true">
					<label for="projectCreate">PROJECT CREATE</label>
					<input type="checkbox" name="projectRead" id="projectRead" value="true">
					<label for="projectRead">PROJECT READ</label>
					<input type="checkbox" name="projectUpdate" id="projectUpdate" value="true">
					<label for="projectUpdate">PROJECT UPDATE</label>
					<input type="checkbox" name="projectDelete" id="projectDelete" value="true">
					<label for="projectDelete">PROJECT DELETE</label>
					<br/>
					<input type="checkbox" name="taskCreate" id="taskCreate" value="true">
					<label for="taskCreate">TASK CREATE</label>
					<input type="checkbox" name="taskRead" id="taskRead" value="true">
					<label for="taskRead">TASK READ</label>
					<input type="checkbox" name="taskUpdate" id="taskUpdate" value="true">
					<label for="taskUpdate">TASK UPDATE</label>
					<input type="checkbox" name="taskDelete" id="taskDelete" value="true">
					<label for="taskDelete">TASK DELETE</label>
					
					
				</div>
				<button type="submit" class="btn">생성</button>
				<a href="<c:url value='/manager/role' />" class="btn">취소</a>
			</form>
		</div>
	</div>
</body>
</html>