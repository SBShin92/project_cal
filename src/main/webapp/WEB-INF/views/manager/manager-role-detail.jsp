<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ page session="true" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link type="text/css" rel="stylesheet" href='<c:url value="/css/manager.css" />' />
    <title>OurCalendar - 권한 상세</title>
</head>
<body>
    <button class="menu-toggle" onclick="toggleMenu()">☰</button>
    <div class="container">
        <jsp:include page="/WEB-INF/includes/manager-nav.jsp" />
        <div class="main-content">
            <div class="header">
                <h1>권한 상세</h1>
            </div>
            <form action="<c:url value='/manager/role/update/${roleVO.roleId}' />" method="post">
                <div>
                    <label for="roleName">권한 이름:</label>
                    <input type="text" id="roleName" name="roleName" value="${roleVO.roleName}" required>
                </div>
                <div>
                    <label for="roleDescription">권한 설명:</label>
                    <input type="text" id="roleDescription" name="roleDescription" value="${roleVO.roleDescription}" required>
                </div>
                <div>
                    <input type="checkbox" name="projectCreate" id="projectCreate" value="true" ${roleVO.projectCreate ? 'checked' : ''}>
                    <label for="projectCreate">PROJECT CREATE</label>
                    <input type="checkbox" name="projectRead" id="projectRead" value="true" ${roleVO.projectRead ? 'checked' : ''}>
                    <label for="projectRead">PROJECT READ</label>
                    <input type="checkbox" name="projectUpdate" id="projectUpdate" value="true" ${roleVO.projectUpdate ? 'checked' : ''}>
                    <label for="projectUpdate">PROJECT UPDATE</label>
                    <input type="checkbox" name="projectDelete" id="projectDelete" value="true" ${roleVO.projectDelete ? 'checked' : ''}>
                    <label for="projectDelete">PROJECT DELETE</label>
                    <br/>
                    <input type="checkbox" name="taskCreate" id="taskCreate" value="true" ${roleVO.taskCreate ? 'checked' : ''}>
                    <label for="taskCreate">TASK CREATE</label>
                    <input type="checkbox" name="taskRead" id="taskRead" value="true" ${roleVO.taskRead ? 'checked' : ''}>
                    <label for="taskRead">TASK READ</label>
                    <input type="checkbox" name="taskUpdate" id="taskUpdate" value="true" ${roleVO.taskUpdate ? 'checked' : ''}>
                    <label for="taskUpdate">TASK UPDATE</label>
                    <input type="checkbox" name="taskDelete" id="taskDelete" value="true" ${roleVO.taskDelete ? 'checked' : ''}>
                    <label for="taskDelete">TASK DELETE</label>
                </div>
                <button type="submit" class="btn">수정</button>
                <a href="<c:url value='/manager/role' />" class="btn">취소</a>
            </form>
        </div>
    </div>
    <script src="<c:url value='/javascript/manager.js'/>"></script>
</body>
</html>