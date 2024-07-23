<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ page session="true"%>
<header>
	<div class="logo">
	<a href="<c:url value='/calendar'/>">CompanyCal</a>
						
	</div>

	<div class="move-buttons">
		<c:choose>
			<c:when test="${ sessionScope.viewMonth <= 1 }">
				<a class="prev-button btn btn-dark"
					href="<c:url value='/calendar/${ sessionScope.viewYear - 1 }12' />">◀
				</a>
			</c:when>
			<c:otherwise>
				<a class="prev-button btn btn-dark"
					href="<c:url value='/calendar/${ sessionScope.viewYear }${sessionScope.viewMonth - 1 }' />">◀
				</a>
			</c:otherwise>
		</c:choose>
		
		
		
		
			<button id="monthYearSelector" class="view-date nav-btn">${ sessionScope.viewYear }년 ${ sessionScope.viewMonth }월</button>
				<div id="monthYearPicker" style="display: none;">
					<select id="yearSelect">
						<!-- 년도 옵션들은 JavaScript로 동적 생성 -->
					</select>
					<select id="monthSelect">
						<option value="1">1월</option>
						<option value="2">2월</option>
						<option value="3">3월</option>
						<option value="4">4월</option>
						<option value="5">5월</option>
						<option value="6">6월</option>
						<option value="7">7월</option>
						<option value="8">8월</option>
						<option value="9">9월</option>
						<option value="10">10월</option>
						<option value="11">11월</option>
						<option value="12">12월</option>
					</select>
					<button id="applyDateButton">적용</button>
				</div>
		
		<c:choose>
			<c:when test="${ sessionScope.viewMonth >= 12 }">
				<a class="next-button btn btn-dark"
					href="<c:url value='/calendar/${ sessionScope.viewYear + 1 }1' />">
					▶</a>
			</c:when>
			<c:otherwise>
				<a class="next-button btn btn-dark"
					href="<c:url value='/calendar/${ sessionScope.viewYear }${sessionScope.viewMonth + 1 }' />">
					▶</a>
			</c:otherwise>
		</c:choose>

		<a class="today-button btn btn-dark"
			href="<c:url value='/calendar/${ sessionScope.todayYear }${sessionScope.todayMonth }/${ sessionScope.todayDate }' />">
			Today</a>
	</div>

	<!-- header.jsp에 검색기능 추가 0717 (테스크타이틀, -->
	<!--  -이 부분 우선 추가함 .. 0717 18:13 -->

	<div class="user-profile-container">
		<div class="user-profile">${sessionScope.userName != null ? sessionScope.userName : '사용자'}
			▼</div>
		<div class="user-menu" style="display: none;">
			<a href="<c:url value='/login/logout'/>" class="menu-item"
				id="logoutButton">로그아웃</a>
		</div>
	</div>
</header>