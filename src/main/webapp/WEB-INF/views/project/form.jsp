<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${project.projectId == null ? '새 프로젝트 생성' : '프로젝트 수정'}</title>
    <link rel="stylesheet" href="<c:url value='/css/form.css'/>" type="text/css">
</head>
<body>

<jsp:include page="/WEB-INF/includes/header.jsp" />
<jsp:include page="/WEB-INF/includes/nav.jsp" />

<div class="project-form">
    <header>
        <h1>${project.projectId == null ? '새 프로젝트 생성' : '프로젝트 수정'}</h1>
    </header>

    <main>
        <form action="<c:url value='/project/create'/>" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label for="projectTitle">프로젝트 제목:</label>
                <input type="text" id="projectTitle" name="projectTitle" required="required" />
            </div>

            <div class="form-group">
                <label for="projectDescription">프로젝트 설명:</label>
                <textarea id="projectDescription" name="projectDescription" rows="4" required="required"></textarea>
            </div>

            <div class="form-group">
                <label for="projectStatus">프로젝트 상태:</label>
                <select id="projectStatus" name="projectStatus">
                    <option value="none">선택하세요</option>
                    <option value="ongoing">진행 중</option>
                    <option value="completed">완료</option>
                    <option value="onhold">보류</option>
                </select>
            </div>

            <div class="form-group">
                <label for="startDate">시작일:</label>
                <input type="date" id="startDate" name="startDate" required="required" />
            </div>

            <div class="form-group">
                <label for="endDate">종료일:</label>
                <input type="date" id="endDate" name="endDate" required="required" />
            </div>

            <div class="form-group">
                <label for="projectFiles">첨부 파일:</label>
                <input type="file" id="projectFiles" name="projectFiles" multiple>
            </div>

            <footer>
                <button type="submit" class="btn btn-primary">
                    ${project.projectId == null ? '프로젝트 생성' : '프로젝트 수정'}
                </button>
                <a href="<c:url value='/calendar'/>" class="btn">취소</a>
            </footer>
        </form>
    </main>
</div>

<script src="<c:url value='/javascript/form.js'/>"></script>
</body>
</html>