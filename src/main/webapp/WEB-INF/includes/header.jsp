<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<header>
	<div class="logo">CompanyCal</div>
	<div class="view-date">${ sessionScope.viewYear }년 ${ sessionScope.viewMonth }월</div>
	<div class="move-buttons">
		<c:choose>
			<c:when test="${ sessionScope.viewMonth <= 1 }">
				<a class="prev-button" href="<c:url value='/calendar/${ sessionScope.viewYear - 1 }12' />">←</a>
			</c:when>
			<c:otherwise>
				<a class="prev-button" href="<c:url value='/calendar/${ sessionScope.viewYear }${sessionScope.viewMonth - 1 }' />">←</a>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${ sessionScope.viewMonth >= 12 }">
				<a class="next-button" href="<c:url value='/calendar/${ sessionScope.viewYear + 1 }1' />">→</a>
			</c:when>
			<c:otherwise>
				<a class="next-button" href="<c:url value='/calendar/${ sessionScope.viewYear }${sessionScope.viewMonth + 1 }' />">→</a>
			</c:otherwise>
		</c:choose>
	</div>
	<div class="user-profile">사용자명 ▼</div>
</header>