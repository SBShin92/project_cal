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
	              <tr>
	                  <td>${task.taskId}</td>
	                  <td>${task.userId}</td>     
	                  <td>${task.projectId}</td>
	                  <td>${task.taskTitle}</td>
	                  <td>${task.taskDescription}</td>
	                  <td>${task.createdAt}</td>
	                  <td>${task.updatedAt == null ? '' : task.updatedAt}</td>
	                  <td>${task.taskStatus == null ? '' : task.taskStatus}</td>
	              </tr>
              </tbody>
                
                <!-- Add more task details as needed -->
            </table>

            <!-- Task Actions -->
            <h2>Actions</h2>
            <a href="<c:url value='/tasks/${task.taskId}/edit'/>">Edit Task</a><br>
            <button onclick="deleteTask(${task.taskId})">Delete Task</button>

            <!-- Add Member Form -->
            <h2>Add Member to Task</h2>
            <form id="addMemberForm" action="<c:url value='/tasks/members/${task.taskId}' />" method="POST">
            	<input type="hidden" name="taskId"  value="${task.taskId}">
            	<input type="hidden" name="projectId"  value="${task.projectId}">
                <label for="userId">User ID:</label>
                <input type="text" id="userId" name="userId" value="">
                <button type="submit" >멤버 추가</button>
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
                                <form action="<c:url value='/tasks/deleteUsersTask/${task.taskId}'/>" method="post">
                                    <input type="hidden" name="userId" value="${member.userId}"/>
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

<script type="application/javascript">
/*
 function addMember(taskId){
     xhr.open("POST", "http://localhost:8090/project_cal/members/" + taskId); //HTTP Method, URL 정의
     xhr.setRequestHeader("content-type", "application/json; charset=UTF-8"); //헤더값 중 content-type 정의

     xhr.send(JSON.stringify({taskId: taskId}))  //요청 전송

     xhr.onload = () => {
         if(xhr.status === 201) {
             //201 상태코드는 요청이 성공적으로 처리 됬다는말/ 일반적으로 POST요청에 대한 응답
             const res = JSON.parse(xhr.response); // 응답데이터를 JSON.parse 함수의 JSON 객체로 변경
             console.log(res); //응답데이터 출력
         } else {
             //에러발생
             console.error(xhr.status, xhr.statusText); //응답상태와 응답 메시지 출력
         }
 }
     */
</script>
</body>
</html>