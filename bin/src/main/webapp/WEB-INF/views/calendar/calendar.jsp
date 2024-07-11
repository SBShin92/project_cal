<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link type="text/css" rel="stylesheet" href='<c:url value="/css/calendar.css" />' />
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
            </table>
        </section>
        <aside class="right-panel">
            <!--  projects, taks 프로젝트에서 가져와야 -->
            <div class="today-events">
                <h4>오늘의 프로젝트</h4>
                <div class="project-item">
                    <div class="project-title">UI 디자인 회의</div>
                    <div class="project-status">진행 중 - 60% 완료</div>
                </div>
            </div>
            <!-- 전체일정 projects 가져와야 -->
            <div class="project-list" id="projectList">
                <h4>선택한 날짜의 프로젝트</h4>
                <!-- 프로젝트 목록은 JavaScript로 동적 생성됩니다 -->
            </div>
        </aside>
    </main>
    
	<script src="<c:url value='/javascript/calendar.js' />"></script>
</body>
</html>