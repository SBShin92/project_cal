<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="<c:url value='/css/form.css'/>" type="text/css">
</head>
<body>
    <h1>Project Members for Project: ${project.projectTitle}</h1>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Email</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="member" items="${members}">
                <tr>
                    <td>${member.userId}</td>
                    <td>${member.userName}</td>
                    <td>${member.userEmail}</td>
                    <td>
                        <form action="/projects/${project.projectId}/members/delete" method="post" style="display:inline;">
                            <input type="hidden" name="userId" value="${member.userId}" />
                            <button type="submit">Delete</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <h2>Add Member</h2>
    <form action="/projects/inviteMember" method="post">
        <input type="hidden" name="projectId" value="${project.projectId}" />
        <label for="userId">Select User:</label>
        <select id="userId" name="userId">
            <c:forEach var="user" items="${users}">
                <option value="${user.userId}">${user.userName}</option>
            </c:forEach>
        </select>
        <button type="submit">Add</button>
    </form>
</body>
</html>