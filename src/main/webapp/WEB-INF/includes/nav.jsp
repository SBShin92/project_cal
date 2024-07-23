<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ page session="true"%>
<nav class="top-nav">

	<button class="nav-btn">개인/전체 일정</button>
	<button class="nav-btn" id="inviteMemberBtn">멤버 초대</button>
	<button class="nav-btn" id="message-btn">쪽지함 (${ sessionScope.messageUnreadCount })</button>
	<a href="<c:url value='/manager' />" class="nav-btn btn-dark">관리자페이지</a>

		<div class="search-div">
		<form action="<c:url value='/tasks/SearchProjectTasks'/>" method="get" class="search-form">
			<input type="text" name="taskTitle" placeholder="search projects, tasks" required>
			<button type="submit" class="btn btn-dark">검색</button>
		</form>

	</div>

</nav>

