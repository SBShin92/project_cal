<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ page session="true"%>
<header>
	<div class="logo">CompanyCal</div>

	<div class="move-buttons">
		<c:choose>
			<c:when test="${ sessionScope.viewMonth <= 1 }">
				<a class="prev-button"
					href="<c:url value='/calendar/${ sessionScope.viewYear - 1 }12' />">◀
				</a>
			</c:when>
			<c:otherwise>
				<a class="prev-button"
					href="<c:url value='/calendar/${ sessionScope.viewYear }${sessionScope.viewMonth - 1 }' />">◀
				</a>
			</c:otherwise>
		</c:choose>
		<a class="view-date">${ sessionScope.viewYear }년 ${ sessionScope.viewMonth }월</a>
		<c:choose>
			<c:when test="${ sessionScope.viewMonth >= 12 }">
				<a class="next-button"
					href="<c:url value='/calendar/${ sessionScope.viewYear + 1 }1' />">
					▶</a>
			</c:when>
			<c:otherwise>
				<a class="next-button"
					href="<c:url value='/calendar/${ sessionScope.viewYear }${sessionScope.viewMonth + 1 }' />">
					▶</a>
			</c:otherwise>
		</c:choose>

		<a class="today-button"
			href="<c:url value='/calendar/${ sessionScope.todayYear }${sessionScope.todayMonth }/${ sessionScope.todayDate }' />">
			Today</a>
	</div>

	<!-- header.jsp에 검색기능 추가 0717 (테스크타이틀, -->
	<!--  -이 부분 우선 추가함 .. 0717 18:13 -->
	<div>
		<form action="<c:url value='/tasks/SearchProjectTasks'/>" method="get"
			class="search-form">
			SEARCH <input type="text" name="query"
				placeholder="Put project title or task title" required>
			<button type="submit">검색</button>
		</form>

	</div>

	<div class="user-profile-container">
		<div class="user-profile">${sessionScope.userName != null ? sessionScope.userName : '사용자'}
			▼</div>
		<div class="user-menu" style="display: none;">
			<a href="<c:url value='/login/logout'/>" class="menu-item"
				id="logoutButton">로그아웃</a>
		</div>
	</div>
</header>