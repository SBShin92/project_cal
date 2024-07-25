<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="<c:url value='/css/main.css'/>"
	type="text/css">
<title>Invite Member</title>
</head>
<body>
	<jsp:include page="/WEB-INF/includes/header.jsp" />
	<h2>Invite Member</h2>

	<c:if test="${not empty message}">
		<div style="color: green;">${message}</div>
	</c:if>

	<c:if test="${not empty error}">
		<div style="color: red;">${error}</div>
	</c:if>

	<h3>All Users</h3>
	<table border="1">
		<thead>
			<tr>
				<th>User ID</th>
				<th>User Name</th>
				<th>User Email</th>
				<th>User Position</th>
				<th>Actions</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="user" items="${allUsers}">
				<tr>
					<td>${user.userId}</td>
					<td>${user.userName}</td>
					<td>${user.userEmail}</td>
					<td>${user.userPosition}</td>
					<td>
						<form action="${pageContext.request.contextPath}/inviteMember/add"
							method="post" style="display:inline;">
							<input type="hidden" name="userId" value="${user.userId}" />
							<input type="hidden" name="projectId" value="${projectId}" /> 								
							<button type="submit">Add</button>
						</form>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<h3>Project Members</h3>
	<table border="1">
		<thead>
			<tr>
				<th>User ID</th>
				<th>User Name</th>
				<th>User Email</th>
				<th>User Position</th>
				<th>Actions</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="user" items="${projectMembers}">
				<tr>
					<td>${user.userId}</td>
					<td>${user.userName}</td>
					<td>${user.userEmail}</td>
					<td>${user.userPosition}</td>
					<td>
						<form action="${pageContext.request.contextPath}/inviteMember/remove"
							method="post" style="display:inline;">
							<input type="hidden" name="userId" value="${user.userId}" />
							<input type="hidden" name="projectId" value="${projectId}" />
							<button type="submit">Remove</button>
						</form>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>
