<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<%@ page session="true" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link type="text/css" rel="stylesheet" href='<c:url value="/css/main.css" />' />
<link type="text/css" rel="stylesheet" href='<c:url value="/css/calendar.css" />' />
<link type="text/css" rel="stylesheet" href='<c:url value="/bootstrap-5.1.3/css/bootstrap.min.css" />' />
<title>OurCalendar</title>
</head>
<body>
	<jsp:include page="/WEB-INF/includes/header.jsp" />
	<div class="move-buttons">
		<c:choose>
			<c:when test="${ sessionScope.viewMonth <= 1 }">
				<a class="prev-button btn btn-dark"
					href="<c:url value='/calendar/date/${ sessionScope.viewYear - 1 }12' />">◀
				</a>
			</c:when>
			<c:otherwise>
				<a class="prev-button btn btn-dark"
					href="<c:url value='/calendar/date/${ sessionScope.viewYear }${sessionScope.viewMonth - 1 }' />">◀
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
					href="<c:url value='/calendar/date/${ sessionScope.viewYear + 1 }1' />">
					▶</a>
			</c:when>
			<c:otherwise>
				<a class="next-button btn btn-dark"
					href="<c:url value='/calendar/date/${ sessionScope.viewYear }${sessionScope.viewMonth + 1 }' />">
					▶</a>
			</c:otherwise>
		</c:choose>

		<a class="today-button btn btn-dark"
			href="<c:url value='/calendar/date/${ sessionScope.todayYear }${sessionScope.todayMonth }/${ sessionScope.todayDate }' />">
			Today</a>
	</div>
	<main>
	
		<section class="calendar">
			<table>
				<thead>
					<tr>
						<th>일</th>
						<th>월</th>
						<th>화</th>
						<th>수</th>
						<th>목</th>
						<th>금</th>
						<th>토</th>
					</tr>
				</thead>
				<tbody>
				<!--  javascript로 생성 -->
				</tbody>
			</table>
		</section>
		<aside class="right-panel">
		
			<!-- 프로젝트 생성 버튼 추가 -->
			<div class="create-project">
				<a id="createProjectBtn" class="btn btn-primary"
					href="<c:url value='/project/create' />">프로젝트 생성</a>
			</div>
			<c:if test="${ not empty viewDate }">
				<p id="clicked-date">${ viewDate } 일의 프로젝트</p>
			</c:if>
			<c:if test="${ not empty projectListByDate }">
			    <c:forEach items="${ projectListByDate }" var="vo" varStatus="status">
			        <div class="card mb-3">
			            <div class="card-body">
			                <h5 class="card-title">
			                    <a href="<c:url value='/project' />/${vo.projectId}">${ vo.projectTitle }</a>
			                </h5>
			                <p class="card-text">
			                    <fmt:formatDate value="${vo.startDate}" pattern="MM/dd" />
			                    ~
			                    <fmt:formatDate value="${vo.endDate}" pattern="MM/dd" />
			                </p>
			            </div>
			        </div>
			    </c:forEach>
			</c:if>
		</aside>
	</main>
	 <script>
	 	// 뜨는 위치 고쳐야함

	 
	 
        // 서버에서 받은 프로젝트 데이터를 JavaScript 변수로 저장
        var projectList = [
            <c:forEach items="${projectListByMonth}" var="project" varStatus="status">
                {
                    id: ${project.projectId},
                    title: "${project.projectTitle}",
                    startDate: <fmt:formatDate value="${project.startDate}" pattern="yyyyMMdd" />,
                    endDate: <fmt:formatDate value="${project.endDate}" pattern="yyyyMMdd" />,
                    projectStatus: "${project.projectStatus}"
                }<c:if test="${!status.last}">,</c:if>
            </c:forEach>
        ];
    </script>
	<script src="<c:url value='/bootstrap-5.1.3/js/bootstrap.min.js' />"></script>
	<script src="<c:url value='/javascript/main.js' />"></script>
	<script src="<c:url value='/javascript/calendar.js' />"></script>
</body>
</html>