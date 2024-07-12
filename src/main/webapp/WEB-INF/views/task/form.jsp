<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!--  태스크 생성/수정 폼 -->    

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${task.taskId == null ? 'Create' : 'Edit'} Task</title>
</head>
<body>
    <h1>${task.taskId == null ? 'Create' : 'Edit'} Task</h1>
    <form:form action="${task.taskId == null ? '/tasks' : '/tasks/'.concat(task.taskId)}" method="post" modelAttribute="task">
        <c:if test="${task.taskId != null}">
            <form:hidden path="taskId"/>
        </c:if>
        <div>
            <form:label path="taskTitle">Title:</form:label>
            <form:input path="taskTitle" required="required"/>
        </div>
        <div>
            <form:label path="taskDescription">Description:</form:label>
            <form:textarea path="taskDescription" required="required"/>
        </div>
        <!-- Add more form fields as needed -->
        <div>
            <button type="submit">${task.taskId == null ? 'Create' : 'Update'} Task</button>
        </div>
    </form:form>
    <a href="<c:url value='/tasks'/>">Back to List</a>
</body>
</html>

