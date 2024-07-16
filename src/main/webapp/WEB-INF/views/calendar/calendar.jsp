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
<link type="text/css" rel="stylesheet"
	href='<c:url value="/css/calendar.css" />' />
<title>OurCalendar</title>
</head>
<body>
	<jsp:include page="/WEB-INF/includes/header.jsp" />
	<jsp:include page="/WEB-INF/includes/nav.jsp" />

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
			<a href="#" onclick="openMessageList();">쪽지함</a>
		
			<!-- 프로젝트 생성 버튼 추가 -->
			<div class="create-project">
				<a id="createProjectBtn" class="btn btn-primary"
					href="<c:url value='/project/create' />">프로젝트 생성</a>
			</div>
			<c:if test="${ not empty viewDate }">
				<div class="project-list" id="projectList">
					<h4 id="view-date">${ viewDate }일 프로젝트</h4>
					<c:if test="${ not empty projectListByDate }">
						<c:forEach items="${ projectListByDate }" var="vo"
							varStatus="status">
							<div class="project-item">
								<div class="project-title">
									<a href="<c:url value='/project' />/${vo.projectId}">${ vo.projectTitle }</a>
								</div>
								<div class="project-duration">
								<fmt:formatDate value="${vo.startDate}" pattern="MM/dd" />
								~
								<fmt:formatDate value="${vo.endDate}" pattern="MM/dd" />
								</div>
							</div>
						</c:forEach>
					</c:if>
				</div>
			</c:if>
		</aside>
	</main>
	 <script>
	 	// 뜨는 위치 고쳐야함
	 	function openMessageList() {
		    window.open("/project_cal/message", 'newwindow', 'width=500, height=400, top=150, left=900');
		}
	 
	 
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
	<script src="<c:url value='/javascript/calendar.js' />"></script>
</body>
</html>