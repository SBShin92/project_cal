
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로젝트 제목</title>
<link rel="stylesheet" href="<c:url value='/css/detail.css'/>"
	type="text/css">
</head>
<body>

	<jsp:include page="/WEB-INF/includes/header.jsp" />
	<jsp:include page="/WEB-INF/includes/nav.jsp" />

	<div class="project-detail" data-project-id="${project.projectId}">
		<header>
			<h1 id="projectTitle">프로젝트 제목 : ${project.projectTitle}</h1>
			<p id="projectDate">
				프로젝트 기간:
				<fmt:formatDate value="${project.createdAt}" pattern="yyyy-MM-dd" />
				~
				<fmt:formatDate value="${project.updatedAt}" pattern="yyyy-MM-dd" />
			</p>
			<p id="projectStatus">상태: ${project.projectStatus}</p>
		</header>

		<main>
			<section class="project-content">
				<h2>상세 내용</h2>
				<div id="projectDescription">
					<p>${project.projectDescription}</p>
				</div>
				<!-- 이미지 부분 -->
				<div id="projectImages">
					<c:forEach var="image" items="${project.images}">
						<img src="${image.url}" alt="${image.description}" />
					</c:forEach>
				</div>
			</section>

			<section class="project-files">
				<h2>첨부 파일</h2>
				<ul id="fileList">
					<c:forEach var="file" items="${project.files}">
						<li><a href="${file.url}" download="${file.name}">${file.name}</a>
							<span class="file-size">(${file.size})</span></li>
					</c:forEach>
				</ul>
				<form id="uploadForm" enctype="multipart/form-data">
					<input type="file" id="fileUpload" name="file">
					<button type="submit" id="uploadFile" class="btn">파일 업로드</button>
				</form>
			</section>
		</main>

		<section class="project-tasks">
			<h2>작업 목록</h2>
			<ul id="taskList">
				<c:forEach var="task" items="${project.tasks}">
					<li><input type="checkbox" id="task${task.taskId}"
						${task.completed ? 'checked' : ''}> <label
						for="task${task.taskId}">${task.taskTitle}</label></li>
				</c:forEach>
			</ul>
			<div class="task-input">
				<input type="text" id="newTask" placeholder="새 작업 추가">
				<button id="addTask" class="btn btn-small">추가</button>
			</div>
		</section>

		<footer>
			<button id="editProject" class="btn btn-primary">수정</button>
			<button id="closeDetail" class="btn">닫기</button>
		</footer>
	</div>

	<script src="<c:url value='/javascript/detail.js' />"></script>
</body>
</html>

