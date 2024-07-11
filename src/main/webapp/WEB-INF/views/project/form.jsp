<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${project.projectId == null ? '새 프로젝트 생성' : '프로젝트 생성'}</title>
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
        <form:form action="${project.projectId == null ? '/project/create' : '/project/update/'.concat(project.projectId)}"
                   method="post" modelAttribute="project" enctype="multipart/form-data">
            
            <div class="form-group">
                <form:label path="projectTitle">프로젝트 제목:</form:label>
                <form:input path="projectTitle" required="required" />
                <form:errors path="projectTitle" cssClass="error" />
            </div>

            <div class="form-group">
                <form:label path="projectDescription">프로젝트 설명:</form:label>
                <form:textarea path="projectDescription" rows="4" required="required" />
                <form:errors path="projectDescription" cssClass="error" />
            </div>

            <div class="form-group">
                <form:label path="projectStatus">프로젝트 상태:</form:label>
                <form:select path="projectStatus">
                    <form:option value="none">선택하세요</form:option>
                    <form:option value="ongoing">진행 중</form:option>
                    <form:option value="completed">완료</form:option>
                    <form:option value="onhold">보류</form:option>
                </form:select>
                <form:errors path="projectStatus" cssClass="error" />
            </div>

            <div class="form-group">
                <form:label path="startDate">시작일:</form:label>
                <form:input path="startDate" type="date" required="required" />
                <form:errors path="startDate" cssClass="error" />
            </div>

            <div class="form-group">
                <form:label path="endDate">종료일:</form:label>
                <form:input path="endDate" type="date" required="required" />
                <form:errors path="endDate" cssClass="error" />
            </div>

            <div class="form-group">
                <label for="projectImage">프로젝트 이미지:</label>
                <input type="file" id="projectImage" name="projectImage" accept="image/*" multiple>
            </div>

            <div class="form-group">
                <label for="projectFiles">첨부 파일:</label>
                <input type="file" id="projectFiles" name="projectFiles" multiple>
            </div>

            <footer>
                <button type="submit" class="btn btn-primary">
                    ${project.projectId == null ? '프로젝트 생성' : '프로젝트 수정'}
                </button>
                <a href="<c:url value='/project/list'/>" class="btn">취소</a>
            </footer>
        </form:form>
    </main>
</div>

<script src="<c:url value='/javascript/form.js'/>"></script>
</body>
</html>