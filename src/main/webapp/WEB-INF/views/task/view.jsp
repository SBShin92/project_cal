<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<!--  테스크 상세보기 페이지 -->

<html>
<head>
    <meta charset="UTF-8">
    <title>Task Details</title>
</head>
<body>
    <h1>Task Details</h1>
    <table border="1">
        <tr>
            <th>ID</th>
            <td>${task.taskId}</td>
        </tr>
        <tr>
            <th>Title</th>
            <td>${task.title}</td>
        </tr>
        <tr>
            <th>Description</th>
            <td>${task.description}</td>
        </tr>
        <!-- Add more task details as needed -->
    </table>
    <a href="<c:url value='/tasks/${task.taskId}/edit'/>">Edit Task</a>
    <a href="<c:url value='/tasks'/>">Back to List</a>
    
    <h2>Add Member to Task</h2>
    <form action="<c:url value='/tasks/${task.taskId}/members'/>" method="post">
        <label for="userId">User ID:</label>
        <input type="number" id="userId" name="userId" required>
        <button type="submit">Add Member</button>
    </form>
</body>
</html>

