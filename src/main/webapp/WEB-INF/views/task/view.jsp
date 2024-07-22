<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<!--  테스크 상세보기 페이지 -->

<html>
<head>
<meta charset="UTF-8">
<title>Task Details</title>

<link rel="stylesheet" href="<c:url value='/css/detail.css'/>"
  type="text/css">

</head>
<body>
  <div id="container">

    <c:import url="/WEB-INF/includes/header.jsp" />

    <section class="project-content">
      <h1>Task Details</h1>
      <a href="<c:url value='/project/${viewTask.projectId}'/>">Back
        to Task List</a>

      <div id="wrapper" class="editable">
        <!-- <div id="content"> -->

        <!-- Task Details -->
        <table border="1">

          <thead>
            <tr>
              <th>Task ID</th>
              <th>User ID</th>
              <th>Project ID</th>
              <th>Title</th>
              <th>Description</th>
              <th>Status</th>
              <th>Priority</th>
              <th>Start Date</th>
               <th>End Date</th>
              <th>Created At</th>
              <th>Updated At</th>
            </tr>
          </thead>

          <tbody>
            <tr>
              <td>${viewTask.taskId}</td>
              <td>${viewTask.userId}</td>
              <td>${viewTask.projectId}</td>
              <td>${viewTask.taskTitle}</td>
              <td>${viewTask.taskDescription}</td>
              <td>${viewTask.taskStatus == null ? '' : viewTask.taskStatus}</td>
              <td>${viewTask.taskPriority}</td>
              <td>${viewTask.startDate}</td>
              <td>${viewTask.endDate}</td>
              <td>${viewTask.createdAt}</td>
              <td>${viewTask.updatedAt == null ? '' : viewTask.updatedAt}</td>
              
            </tr>
          </tbody>

          <!-- Add more task details as needed -->
        </table>

      </div>
    </section>
    <!-- Add Member Form -->
    <h2>Add Member to Task</h2>
    <form id="addMemberForm"
      action="<c:url value='/tasks/members/${viewTask.taskId}' />"
      method="POST">
      <input type="hidden" name="taskId" value="${viewTask.taskId}">
      <input type="hidden" name="projectId" value="${viewTask.projectId}"> 
      <input type="hidden" name="userId" value="${viewTask.userId}"> 
      <label for="addUserId">User ID:</label> 
      <input type="number" name="addUserId" value="">
      <button type="submit">멤버 추가</button>
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

        <c:forEach var="member" items="${usersTasks}">
          <tr>
            <td>${member.userId}</td>
            <td>${member.userName}</td>
            <td>
              <form
                action="<c:url value='/tasks/deleteUsersTask/${viewTask.taskId}'/>"
                method="post">
                <input type="hidden" name="userId" value="${member.userId}" />
                <button type="submit" onclick="return confirm('Are you sure you want to remove this member?')">Remove</button>
              </form>
            </td>
          </tr>
        </c:forEach>

      </tbody>
    </table>

  </div>

  <script type="application/javascript"></script>




</body>
</html>