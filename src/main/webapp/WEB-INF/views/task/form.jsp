<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!--  태스크 생성/수정 폼 -->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${createTaskForm.taskId == 0 ? 'Create' : 'Edit'}Task</title>

<link rel="stylesheet" href="<c:url value='/css/form.css'/>" type="text/css">

</head>  
<body>
    
    <jsp:include page="/WEB-INF/includes/header.jsp" />
 	<jsp:include page="/WEB-INF/includes/nav.jsp" />
  
  <div class="project-content">
  	<header>
  		<h1>${createTaskForm.taskId == 0 ? 'Create' : 'Edit'}Task</h1>
	</header>       
  
    <!--  createTaskForm.taskId가 0이면 /project_cal/tasks/createTask 호출(user,projectId 값이 필요함)
                           0이 아니면/ /project_cal/tasks/updateTask/'.concat(createTaskForm.taskId) -->
   <main>
    <form action="${createTaskForm.taskId == 0 ? '/project_cal/tasks/createTask' : '/project_cal/tasks/updateTask/'.concat(createTaskForm.taskId)}"
	      method="post" modelAttribute="task">
	      
	      <c:if test="${createTaskForm.taskId != 0}">
	        <input type="hidden" name="taskId" value="${createTaskForm.taskId} " />
	      </c:if>
	      
	      <input type="hidden" name="userId" value="${createTaskForm.userId}" /> 
	      <input type="hidden" name="projectId" value="${createTaskForm.projectId}" />
	      
	      <div class="form-group">
	        <label for="taskTitle">Title:</label> 
	        <input type="text" name="taskTitle" value="${createTaskForm.taskTitle}" />
	      </div>
	      
	      <div class="form-group">
	        <label for="taskDescription">Description:</label>
	        <textarea name="taskDescription">${createTaskForm.taskDescription}</textarea>
	      </div>
	      
	      <div class="form-group">
	          <label for="startDate">Start Date:</label>
	          <input type="date" id="startDate" name="startDate" required="required" />
	      </div>
	
	      <div class="form-group">
	          <label for="endDate">End Date:</label>
	          <input type="date" id="endDate" name="endDate" required="required" />
	      </div>    
	      
	      <div class="form-group">
		        <label for="taskPriority">Priority:</label>
		        <input type="text" name="taskPriority" value="${createTaskForm.taskPriority}" />
	      </div>  
	  
	      <div class="form-group">
	          <label for="taskStatus">테스크 상태:</label>
	          <select id="taskStatus" name="taskStatus">
	              <option value="none">선택하세요</option>
	              <option value="진행중">진행 중</option>
	              <option value="완료">완료</option>
	              <option value="보류">보류</option>
	          </select>
	      </div>   
	        
        <footer> 
		      <div>
		        <button type="submit">${createTaskForm.taskId == 0 ? 'Create' : 'Update'} Task</button>
		      </div>
		      <a href="<c:url value='/project/${createTaskForm.projectId}'/>">
		      Back to List</a>
      	</footer>
      	
    </form>
    </main>
  </div>

</body>
</html>