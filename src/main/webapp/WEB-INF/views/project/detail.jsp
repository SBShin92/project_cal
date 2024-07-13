<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${project.projectTitle}</title>
<link rel="stylesheet" href="<c:url value='/css/detail.css'/>"
	type="text/css">
</head>
<body>
	<header>
		<h1 id="projectTitle">프로젝트 제목 : ${projectVO.projectTitle}</h1>
	</header>

	<div class="project-detail" data-project-id="${projectVO.projectId}">
		<header>
			<h1 id="projectTitle">프로젝트 제목 : ${projectVO.projectTitle}</h1>
			<p id="projectDate">
				프로젝트 기간:
				<fmt:formatDate value="${projectVO.startDate}" pattern="yyyy-MM-dd" />
				~
				<fmt:formatDate value="${projectVO.endDate}" pattern="yyyy-MM-dd" />
			</p>
			<p id="projectStatus">상태: ${projectVO.projectStatus}</p>
		</header>

		<main>
			<section class="project-content">
				<h2>상세 내용</h2>
				<div id="projectDescription">
					<p>${projectVO.projectDescription}</p>
				</div>
				<!-- 이미지 부분 -->
				<!--div id="projectImages" -->
			</section>

			<section class="project-files">
				<h2>첨부 파일</h2>
				<ul id="fileList">
					<!-- forEach -->
				</ul>
				<!-- 만약 멤버 인증된 상태면 파일 업로드 가능 -->
				<!-- cIf -->
			</section>
		</main>
		<!-- project-tasks -->
		<!-- project-members -->

		<footer>
			<!-- c if test -->
			<!-- c if test2 -->

			<!-- 삭제 버튼 추가 -->
			<form
				action="<c:url value='/project/delete/${projectVO.projectId}'/>"
				method="post" style="display: inline;">
				<input type="hidden" name="_method" value="delete" />
				<button type="submit" class="btn btn-danger"
					onclick="return confirm('정말로 이 프로젝트를 삭제하시겠습니까?');">삭제</button>
			</form>

			<button id="closeDetail" class="btn">닫기</button>
		</footer>
		<a href="<c:url value='/calendar'/>" class="btn">캘린더로 돌아가기</a>
	</div>

	<script src="<c:url value='/javascript/detail.js'/>"></script>
</body>
</html>