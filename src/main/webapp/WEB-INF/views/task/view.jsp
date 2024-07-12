<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<!--  테스크 상세보기 페이지 -->

<html>
<head>
    <meta charset="UTF-8">
    <title>Task Details</title>
    
     <script>
      
    </script>
    
</head>
<body>
    <div id="container">

    <c:import url="/WEB-INF/includes/header.jsp"/>

    <h1>Task Details</h1>
    <a href="<c:url value='/tasks'/>">Back to Task List</a>

    <div id="wrapper">
        <div id="content">

            <!-- Task Details -->
            <table border="1">
                
               <thead> 
                <tr>
                    <th>taskId</th>
                    <th>taskId</th>
                    <th>projectId</th>
                    <th>taskTitle</th>
                    <th>taskDescription</th>
                    <th>createdAt</th>
                    <th>updatedAt</th>
                    <th>taskStatus</th>
                </tr>
              </thead> 
               
              <tbody>
                  <c:forEach var="vo" items="${tasks}" >
                      <tr>
                          <td>${vo.taskId}</td>
                          <td>${vo.userId}</td>
                          <td>${vo.projectId}</td>
                          <td>${vo.taskTitle}</td>
                          <td>${vo.taskDescription}</td>
                          <td>${vo.createdAt}</td>
                          <td>${vo.upadatedAt}</td>
                          <td>${vo.taskStatus}</td>
                      </tr>
                      
                  </c:forEach>
              </tbody>
                
                <!-- Add more task details as needed -->
            </table>

            <!-- Task Actions -->
            <h2>Actions</h2>
            <a href="<c:url value='/tasks/${vo.taskId}/edit'/>">Edit Task</a><br>
            <button onclick="deleteTask(${vo.taskId})">Delete Task</button>

            <!-- Add Member Form -->
            <h2>Add Member to Task</h2>
            <form id="addMemberForm" method="POST">
                <label for="userId">User ID:</label>
                <input type="number" id="userId" name="userId" required>
                <button type="button" onclick="addMember(${task.taskId})">Add Member</button>
            </form>

            <!-- Task Members List -->
            <h2>Task Members</h2>
            <table border="1">
                <thead>
                    <tr>
                        <th>User ID</th>
                        <th>User Name</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="member" items="${task.members}">
                        <tr>
                            <td>${member.userId}</td>
                            <td>${member.userName}</td>
                            <td>
                                <form action="<c:url value='/tasks/${task.taskId}/members/${member.userId}'/>" method="post">
                                    <input type="hidden" name="_method" value="DELETE"/>
                                    <button type="submit" onclick="return confirm('Are you sure you want to remove this member?')">Remove</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

        </div>
    </div>

</div>

</body>
</html>