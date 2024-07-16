<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${project.projectTitle}</title>
    <link rel="stylesheet" href="<c:url value='/css/detail.css'/>" type="text/css">
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
            <!-- 
            <div id="projectImages">
                <c:forEach var="image" items="${project.images}">
                    <img src="${image.url}" alt="${image.description}" />
                </c:forEach>
            </div>
             -->
        </section>

        <section class="project-files">
            <h2>첨부 파일</h2>
            <ul id="fileList">
            	<!-- 
                <c:forEach var="file" items="${projectVO.files}">
                    <li><a href="${file.url}" download="${file.name}">${file.name}</a>
                    <span class="file-size">(${file.size})</span></li>
                </c:forEach>
                 -->
            </ul>
            <!-- 만약 멤버 인증된 상태면 파일 업로드 가능 -->
            <!-- 
            <c:if test="${isProjectMember}">
                <form id="uploadForm" enctype="multipart/form-data">
                    <input type="file" id="fileUpload" name="file">
                    <button type="submit" id="uploadFile" class="btn">파일 업로드</button>
                </form>
            </c:if>
             -->
        </section>
    </main>
	<!-- 
    <section class="project-tasks">
        <h2>작업 목록</h2>
        <ul id="taskList">
            <c:forEach var="task" items="${projectTasks}">
                <li>
                    <input type="checkbox" id="task${task.taskId}" ${task.completed ? 'checked' : ''}>
                    <label for="task${task.taskId}">${task.taskTitle}</label>
                </li>
            </c:forEach>
        </ul>
        <c:if test="${isProjectMember}">
            <div class="task-input">
                <input type="text" id="newTask" placeholder="새 작업 추가">
                <button id="addTask" class="btn btn-small">추가</button>
            </div>
        </c:if>
    </section>

    <section class="project-members">
        <h2>프로젝트 멤버</h2>
        <ul id="memberList">
            <c:forEach var="member" items="${projectMembers}">
                <li>${member.userName} (${member.userEmail})</li>
            </c:forEach>
        </ul>
        <c:if test="${isProjectCreator}">
            <button id="addMember" class="btn">멤버 추가</button>
        </c:if>
    </section>
	 -->
	 
	 
    <footer>
    	<!-- 
        <c:if test="${isProjectCreator || isProjectMember}">
            <button id="editProject" class="btn btn-primary">수정</button>
        </c:if>
        <c:if test="${isProjectCreator}">
            <button id="deleteProject" class="btn btn-danger">삭제</button>
        </c:if>
        -->
        <button id="closeDetail" class="btn">닫기</button>
    </footer>
     <a href="<c:url value='/calendar'/>" class="btn">캘린더로 돌아가기</a>
</div>

<script src="<c:url value='/javascript/detail.js'/>"></script>
</body>
</html>