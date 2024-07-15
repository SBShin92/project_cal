<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${projectVO.projectTitle}</title>
<link rel="stylesheet" href="<c:url value='/css/detail.css'/>"
	type="text/css">
</head>
<body>
	<div id="projectViewMode" class="project-detail"
		data-project-id="${projectVO.projectId}">
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
				<div id="projectDescription" class="editable"
					data-field="projectDescription">
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

		<footer>
			<div class="footer-content">
				<div class="back-to-calendar">
					<a href="<c:url value='/calendar'/>" class="btn btn-secondary">캘린더로
						돌아가기</a>
				</div>
				<div class="button-group">
					<button id="editButton" class="btn btn-primary">수정</button>
					<form
						action="<c:url value='/project/delete/${projectVO.projectId}'/>"
						method="post" style="display: inline;">
						<input type="hidden" name="_method" value="delete" />
						<button type="submit" class="btn btn-danger"
							onclick="return confirm('정말로 이 프로젝트를 삭제하시겠습니까?');">삭제</button>
					</form>
				</div>
			</div>
		</footer>
	</div>

	<form id="projectEditForm"
		action="<c:url value='/project/update/${projectVO.projectId}'/>"
		method="post" style="display: none;">
		<div class="project-detail" data-project-id="${projectVO.projectId}">
			<header>
				<h1>
					<input type="text" name="projectTitle" id="editProjectTitle"
						value="${projectVO.projectTitle}" required>
				</h1>
				<p>
					프로젝트 기간: <input type="date" name="startDate"
						value="<fmt:formatDate value="${projectVO.startDate}" pattern="yyyy-MM-dd" />"
						required> ~ <input type="date" name="endDate"
						value="<fmt:formatDate value="${projectVO.endDate}" pattern="yyyy-MM-dd" />"
						required>
				</p>
				<p>
					상태: <select name="projectStatus" required>
						<option value="진행중"
							${projectVO.projectStatus == '진행중' ? 'selected' : ''}>진행중</option>
						<option value="완료"
							${projectVO.projectStatus == '완료' ? 'selected' : ''}>완료</option>
						<option value="지연"
							${projectVO.projectStatus == '지연' ? 'selected' : ''}>지연</option>
						<option value="취소"
							${projectVO.projectStatus == '취소' ? 'selected' : ''}>취소</option>
					</select>
				</p>
			</header>

			<main>
				<section class="project-content">
					<h2>상세 내용</h2>
					<textarea name="projectDescription" required>${projectVO.projectDescription}</textarea>
				</section>
			</main>

			<footer>
				<button type="submit" id="saveButton" class="btn btn-primary">저장</button>
				<button type="button" id="cancelButton" class="btn btn-secondary">취소</button>
			</footer>
		</div>
		<!-- CSRF 토큰 (Spring Security 사용 시) -->
		<!-- input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" / -->
	</form>

	<script src="<c:url value='/javascript/detail.js'/>"></script>
	<script src="<c:url value='/javascript/edit.js'/>"></script>
</body>
</html>