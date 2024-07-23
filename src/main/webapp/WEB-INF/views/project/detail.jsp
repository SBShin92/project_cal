<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${projectVO.projectTitle}</title>
<link rel="stylesheet" href="<c:url value='/css/calendar.css'/>"
	type="text/css">
<link rel="stylesheet" href="<c:url value='/css/detail.css'/>"
	type="text/css">
</head>
<body>
	 <jsp:include page="/WEB-INF/includes/header.jsp" />
	<jsp:include page="/WEB-INF/includes/nav.jsp" />

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
					<c:forEach var="file" items="${fileVOs}">
						<li><a href="<c:url value='/files/download/${file.fileId}'/>">${file.originalFileName}</a>
							(${file.fileSize } bytes)<br /> <span class="file-upload-date">
								업로드 날짜: <fmt:formatDate value="${file.uploadedAt}"
									pattern="yyyy-MM-dd HH:mm:ss" />
						</span></li>
					</c:forEach>
				</ul>
				<c:if test="${empty fileVOs}">
					<p>업로드된 파일이 없습니다.</p>
				</c:if>
				<c:if test="${isProjectMember}">
					<form action="<c:url value='/files/upload'/>" method="post"
						enctype="multipart/form-data">
						<input type="hidden" name="projectId"
							value="${projectVO.projectId}"> <input type="file"
							name="file" multiple>
						<button type="submit">파일 업로드</button>
					</form>
				</c:if>
			</section>

			<!-- task목록 띄우기 위해 추가한 내용 (지원 0718 03:00) -->
			<section class="project-tasks" id="project-tasks">

				<h2>테스크 페이지</h2>
				<div>
					<!-- Content -->
					<table border="1">
						<form action="<c:url value='/tasks/createTaskForm'/>" method="get"
							style="display: inline;">
							<!--  원래는 로그인한 사용자의 아이디를 담아야한다 (보내야한다) -->
							<input type="hidden" name="projectId"
								value="${projectVO.projectId}" />
							<!-- projectTasks[0].projectId가 어차피 값이1개일것이라 인덱스는 [0]해도 상관없 -->
							<button type="submit">새 테스크 생성</button>
						</form>

						<thead>
							<tr>
								<th>TaskID</th>
								<th>TaskTitle</th>
								<th>TaskDescription</th>
								<th>TaskStatus</th>
								<th>TaskPriority</th>
								<th>StartDate</th>
								<th>EndDate</th>
								<th>&nbsp;</th>
							</tr>
						</thead>

						<tbody>
							<c:forEach var="pt" items="${projectTasks}">
								<!-- controller 모델.어트리뷰트한것을 보낸것을 -> jsp에서 받은것 -->
								<!-- foreach for문 반복문을 통해 여러 테스크들을 하나씩 조회하기 -->
								<tr>
									<td>${pt.taskId}</td>
									<td>${pt.taskTitle}</td>
									<td>${pt.taskDescription}</td>
									<td>${pt.taskStatus}</td>
									<td>${pt.taskPriority}</td>
									<td><fmt:formatDate value="${pt.startDate}"
											pattern="yyyy-MM-dd" /></td>

									<td><fmt:formatDate value="${pt.endDate}"
											pattern="yyyy-MM-dd" /></td>

									<td>

										<form action="<c:url value='/tasks/viewTask/${pt.taskId}'/>"
											method="get" style="display: inline;">
											<button type="submit">상세VIEW</button>
										</form>

										<form action="<c:url value='/tasks/createTaskForm'/>"
											method="get" style="display: inline;">
											<input type="hidden" name="taskId" value="${pt.taskId}" />
											<!-- pk -->

											<input type="hidden" name="userId" value="${pt.userId}" /> 
                                            <input type="hidden" name="projectId" value="${pt.projectId}" /> 
                                            <input type="hidden" name="taskTitle" value="${pt.taskTitle}" /> 
                                            <input type="hidden" name="taskDescription" value="${pt.taskDescription}" />
                                            <input type="hidden" name="taskPriority" value="${pt.taskPriority}" />
                                            <input type="hidden" name="startDate" value="${pt.startDate}" />
                                            <input type="hidden" name="endDate" value="${pt.endDate}" />
                                            
											<button type="submit" onclick="return confirm('정말 이 task를 수정 하시겠습니까? Are you sure you want to edit this task?')">
											EDIT</button>

										</form>

										<form action="<c:url value='/tasks/deleteTask/${pt.taskId}'/>"
											method="post" style="display: inline;">
											<!--  <input type="hidden" name="_method" value="DELETE"/> 없애도 됨 -->
											<input type="hidden" name="projectId" value="${pt.projectId}" />
											<input type="hidden" name="userId" value="${pt.userId}" />
											<button type="submit"
												onclick="return confirm('정말 이 task를 삭제하시겠습니까? Are you sure you want to delete this task?')">
												DELETE</button>
										</form>

									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</section>

			<!-- 프로젝트 멤버 목록 -->
			<section class="project-members">
			    <h2>프로젝트 멤버</h2>
			    <ul id="memberList">
			        <c:forEach var="member" items="${projectMembers}">
			            <li>
			                ${member.userName}
			                <form action="<c:url value='/project/removeMember'/>" method="post" style="display: inline;">
			                    <input type="hidden" name="projectId" value="${projectVO.projectId}">
			                    <input type="hidden" name="userId" value="${member.userId}">
			                    <button type="submit">삭제</button>
			                </form>
			            </li>
			        </c:forEach>
			    </ul>
			    <c:if test="${empty projectMembers}">
			        <p>초대된 멤버가 없습니다</p>
			    </c:if>
			    <form action="<c:url value='/project/inviteMember'/>" method="post">
			        <input type="hidden" name="projectId" value="${projectVO.projectId}">
			        <select name="userId">
			            <c:forEach var="user" items="${allUsers}">
			                <option value="${user.userId}">${user.userName}</option>
			            </c:forEach>
			        </select>
			        <button type="submit">멤버 추가</button>
			    </form>
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
					프로젝트 제목: <input type="text" name="projectTitle"
						id="editProjectTitle" value="${projectVO.projectTitle}" required>
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
					<textarea id="projectDescription" name="projectDescription"
						rows="4" required>${projectVO.projectDescription}</textarea>
				</section>

				<section class="project-files">
					<h2>첨부 파일</h2>
					<ul id="editFileList">
						<c:forEach var="file" items="${fileVOs}">
							<li>${file.originalFileName}(${file.fileSize} bytes) <input
								type="checkbox" name="deleteFiles" value="${file.fileId}">
								삭제
							</li>
						</c:forEach>
					</ul>
					<input type="file" name="newFiles" multiple>
				</section>
			</main>

			<footer>
				<button type="submit" id="saveButton" class="btn btn-primary">저장</button>
				<button type="button" id="cancelButton" class="btn btn-secondary">취소</button>
			</footer>
		</div>
	</form>

	<script src="<c:url value='/javascript/detail.js'/>"></script>
	<script src="<c:url value='/javascript/edit.js'/>"></script>
	<script src="<c:url value='/javascript/calendar.js'/>"></script>
	<script src="<c:url value='/javascript/main.js'/>"></script>
</body>
</html>