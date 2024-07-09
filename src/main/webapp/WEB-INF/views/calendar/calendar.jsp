<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>

<jsp:include page="/WEB-INF/includes/start.jsp" />

<main>
	<section class="calendar" id="calendar">
		<table>
			<thead>
				<tr>
					<th>Sun</th>
					<th>Mon</th>
					<th>Tue</th>
					<th>Wed</th>
					<th>Thu</th>
					<th>Fri</th>
					<th>Sat</th>
				</tr>
			</thead>
			<tbody>
				<!-- 캘린더 내용은 JavaScript로 동적 생성됩니다 -->
			</tbody>
		</table>
	</section>
	<aside class="right-panel">

		<div class="today-events">
			<h4>오늘의 일정</h4>
			<!-- 오늘의 일정 내용 -->
		</div>
	</aside>
</main>

<script src="<c:url value='/javascript/calendar.js' />"></script>


<jsp:include page="/WEB-INF/includes/end.jsp" />