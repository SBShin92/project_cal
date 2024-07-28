<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ page session="true"%>
<header>
	<div class="logo">
		<a href="<c:url value='/calendar'/>" class="logo-link"><i class="fa-regular fa-calendar-days"></i> Rose Cal</a>
	</div>
	
	
	<div class="user-profile-container">
	  <nav class="top-nav">
	  
		<c:if test="${ sessionScope.authUserRole.projectRead == true }">
			<div class="search-div">
				<form action="<c:url value='/SearchProjectTasks'/>" method="get" class="search-form">
					<input type="text" name="taskProjectTitle" placeholder="search projects, tasks" required>
					<button type="submit" class="btn-search">검색</button>
				</form>
			</div>
		</c:if>
		<c:choose>
			<c:when test="${ sessionScope.messageUnreadCount != 0 }">
				<button class="nav-btn btn-sky" id="message-btn">Message <span class="text-danger"><b>${ sessionScope.messageUnreadCount }</b></span></button>
			</c:when>
			<c:otherwise>
				<button class="nav-btn btn-sky" id="message-btn"><span><b>Message ${ sessionScope.messageUnreadCount }</b></span></button>
			</c:otherwise>
		</c:choose>
	    
	    <c:if test="${authUser.userAuthority == 'admin'}">
	      <a href="<c:url value='/manager' />" class="nav-btn btn-sky">관리자페이지</a>
	    </c:if>
	    

		<div class="user-profile">${sessionScope.userName != null ? sessionScope.userName : '사용자'}	▼</div>
		<div class="user-menu" style="display: none;">
			<a href="<c:url value="/logout" />" class="menu-item" id="logoutButton">로그아웃</a>
		</div>
	  </nav>
	</div>
</header>