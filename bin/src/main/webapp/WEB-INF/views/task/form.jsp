<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!--  태스크 생성/수정 폼 -->    

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${createTaskForm.taskId == 0 ? 'Create' : 'Edit'} Task</title>
</head>
<body>
    <h1>${createTaskForm.taskId == 0 ? 'Create' : 'Edit'} Task</h1>
    
    <!--  createTaskForm.taskId가 0이면 /project_cal/tasks/createTask 호출(user,projectId 값이 필요함)
                       0이 아니면/ /project_cal/tasks/updateTask/'.concat(createTaskForm.taskId) -->
    <form action="${createTaskForm.taskId == 0 ? '/project_cal/tasks/createTask' : '/project_cal/tasks/updateTask/'.concat(createTaskForm.taskId)}" method="post" modelAttribute="task">
        <c:if test="${createTaskForm.taskId != 0}">
            <input type="hidden" name="taskId" value="${createTaskForm.taskId} "/>                   
        </c:if>
        <input type="hidden" name="userId" value="${createTaskForm.userId }"/>
        <input type="hidden" name="projectId" value="${createTaskForm.projectId }"/>
        <div>        
            <label path="taskTitle">Title:</label>
            <input type="text" name="taskTitle" value="${createTaskForm.taskTitle }" />
        </div>          
        <div>
            <label path="taskDescription">Description:</label>
            <textarea name="taskDescription" >${createTaskForm.taskDescription }</textarea>
        </div>
        <!-- Add more form fields as needed -->
        <div>
            <button type="submit">${createTaskForm.taskId == 0 ? 'Create' : 'Update'} Task</button>
        </div>
    </form>
    
    <a href="<c:url value='/project/${createTaskForm.projectId}'/>">Back to List</a>  
    
</body>
</html>

