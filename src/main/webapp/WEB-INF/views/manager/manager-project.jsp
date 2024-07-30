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
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" rel="stylesheet">
<link type="text/css" rel="stylesheet" href='<c:url value="/css/manager.css" />' />
<link type="text/css" rel="stylesheet" href='<c:url value="/css/main.css" />' />

<title>OurCalendar</title>
</head>
<body>
<jsp:include page="/WEB-INF/includes/header.jsp" />
	<button class="menu-toggle" onclick="toggleMenu()">☰</button>
	<div class="container">
		<jsp:include page="/WEB-INF/includes/manager-nav.jsp" />
		<div class="main-content">
			<div class="header">
				<h1>프로젝트 관리</h1>
				<input type="text" class="search-bar"
					placeholder="프로젝트 또는 사용자 검색...">
			</div>
			<div id="Projects" class="tabcontent">
				<a class="btn" href="<c:url value='/project/create' />">새 프로젝트 생성</a>
				<table>
					<thead>
						<tr>
							<th class="title-column">프로젝트명</th>
							<th>팀장</th>
							<th>시작일</th>
							<th>종료일</th>
							<th>상태</th>
							<th class="status-column">작업</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${ projectVOs }" var="projectVO"
							varStatus="status">
							<tr>
								<td class="title-column">${ projectVO.projectTitle }</td>
								<td>${ projectVO.userName }</td>
								<td><fmt:formatDate value="${projectVO.startDate}"
										pattern="yyyy-MM-dd" /></td>
								<td><fmt:formatDate value="${projectVO.endDate}"
										pattern="yyyy-MM-dd" /></td>
								<td>${ projectVO.projectStatus }</td>
								<td  class="status-column">
									<form class="deleteForm" action="<c:url value='/manager/project/delete/${ projectVO.projectId }' />" method="POST">
										<input type="hidden" name="page" value="${param.page == null ? 1 : param.page}" />
										<a class="btn" href="<c:url value='/project/${projectVO.projectId}' />">상세</a>
										<input type="submit" class="btn" value="삭제" />
									</form>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>

			<nav>
			  <ul class="pagination">
			    <li class="page-item"><a class="page-link" href="?page=${param.page == 1 || param.page == null ? 1 : param.page - 1}">Previous</a></li>

			    <c:forEach begin="1" end="${(projectCount / 10) + 1}" varStatus="num">
			      <li class="page-item"><a class="page-link" href="?page=${num.index}">${num.index}</a></li>
			    </c:forEach>
			    <li class="page-item"><a class="page-link" href="?page=${ totalPages != 10 ? (param.page == null ? 1 : param.page) : (param.page == null ? 1 : param.page) + 1}">Next</a></li>
			  </ul>
			</nav>
		</div>
	</div>
	<script src="<c:url value='/javascript/manager.js'/>"></script>
	<script src="<c:url value='/javascript/main.js'/>"></script>
	<script src="<c:url value='/javascript/calendar.js' />"></script>
</body>
</html>