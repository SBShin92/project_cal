<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<!--  태스크 상세보기 페이지 -->

<html>
<head>
    <meta charset="UTF-8">
    <title>Task Details</title>
    
     <link rel="stylesheet" href="<c:url value='/css/detail.css'/>"
	type="text/css">
    
</head>
<body>
    <div id="container">

    <c:import url="/WEB-INF/includes/header.jsp"/>
	<section class="project-content">
    <h1>Task Details</h1>
    <a href="<c:url value='/project/${viewTask.projectId}'/>">Back to Task List</a>

    <div id="wrapper"  class="editable">
        <!-- <div id="content"> -->

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
	                  <td>${viewTask.taskId}</td>
	                  <td>${viewTask.userId}</td>     
	                  <td>${viewTask.projectId}</td>
	                  <td>${viewTask.taskTitle}</td>
	                  <td>${viewTask.taskDescription}</td>
	                  <td>${viewTask.createdAt}</td>
	                  <td>${viewTask.updatedAt == null ? '' : viewTask.updatedAt}</td>
	                  <td>${viewTask.taskStatus == null ? '' : viewTask.taskStatus}</td>
	              </tr>
              </tbody>
                
                <!-- Add more task details as needed -->
            </table>
			
			<!-- 지움
      
            <h2>Actions</h2>
            <form action="<c:url value='/tasks/createTaskForm'/>" method="get" style="display:inline;">
                <input type="hidden" name="taskId" value="${task.taskId}"/>
                <input type="hidden" name="userId" value="${task.userId}"/>
                <input type="hidden" name="projectId" value="${task.projectId}"/>
                <input type="hidden" name="taskTitle" value="${task.taskTitle}"/>
                <input type="hidden" name="taskDescription" value="${task.taskDescription}"/>
                
                <button type="submit" onclick="return confirm('정말 이 task를 수정 하시겠습니까? Are you sure you want to edit this task?')">TASK 수정</button>
            </form>
        
 			<form action="<c:url value='/tasks/deleteTask/${task.taskId}'/>" method="post" style="display:inline;">
                <button type="submit" onclick="return confirm('정말 이 task를 삭제하시겠습니까? Are you sure you want to delete this task?')">TASK 삭제</button>
            </form>       
     -->
     	</div>
	</section>
            <!-- Add Member Form -->
            <h2>Add Member to Task</h2>
            <form id="addMemberForm" action="<c:url value='/tasks/members/${viewTask.taskId}' />" method="POST">
            	<input type="hidden" name="taskId"  value="${viewTask.taskId}">
            	<input type="hidden" name="projectId"  value="${viewTask.projectId}">
				<input type="hidden" name="userId" value="${viewTask.userId}">            	
                <label for="userId">User ID:</label>
                <input type="text" name="addUserId" value="">
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
                                <form action="<c:url value='/tasks/deleteUsersTask/${viewTask.taskId}'/>" method="post">
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