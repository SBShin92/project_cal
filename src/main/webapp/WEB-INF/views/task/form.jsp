<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!--  태스크 생성/수정 폼 -->    

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${task.taskId == 0 ? 'Create' : 'Edit'} Task</title>
</head>
<body>
    <h1>${task.taskId == 0 ? 'Create' : 'Edit'} Task</h1>
    
    <form action="${task.taskId == 0 ? '/project_cal/tasks/createTask' : '/project_cal/tasks/updateTask/'.concat(task.taskId)}" method="post" modelAttribute="task">
        <c:if test="${task.taskId != 0}">
            <input type="hidden" name="taskId" value="${task.taskId} "/>                   
        </c:if>
        <input type="hidden" name="userId" value="${task.userId }"/>
        <input type="hidden" name="projectId" value="${task.projectId }"/>
        <div>        
            <label path="taskTitle">Title:</label>
            <input type="text" name="taskTitle" value="${task.taskTitle }" />
        </div>          
        <div>
            <label path="taskDescription">Description:</label>
            <textarea name="taskDescription" >${task.taskDescription }</textarea>
        </div>
        <!-- Add more form fields as needed -->
        <div>
            <button type="submit">${task.taskId == 0 ? 'Create' : 'Update'} Task</button>
        </div>
    </form>
    
    <a href="<c:url value='/tasks/listTasks'/>">Back to List</a>  
    
</body>
</html>

