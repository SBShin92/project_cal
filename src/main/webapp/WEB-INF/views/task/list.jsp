<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!--  테스크 목록 페이지 
1.task/list.jsp (태스크 목록)
2.task/view.jsp (태스크 상세 보기)
3.task/form.jsp (태스크 생성/수정 폼)

1)JSTL과 Spring Form 태그 라이브러리를 사용했습니다
2)삭제 기능은 JavaScript를 사용하여 확인 대화상자를 표시합니다.
3)form.jsp는 생성과 수정 두 가지 용도로 사용됩니다. 태스크 ID의 존재 여부에 따라 동작이 달라집니다.
4)멤버 추가 기능은 task/view.jsp에 간단한 폼으로 구현되어 있습니다.
5)컨트롤러의 @DeleteMapping과 @PostMapping("/{taskId}")에 대응하는 HTML 폼에서는 
hidden 필드를 사용하여 HTTP 메소드를 지정했습니다. 이는 HTML 폼이 GET과 POST만 지원하기 때문입니다.

-->

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Task List</title>
</head>

<body>

<dic id ="container" >

  <c:import url="/WEB-INF/includes/header.jsp"/>
  
    <h1>Task List</h1>
    <a href="<c:url value='/tasks/create'/>">Create New Task</a>
    
 <div id="wrapper">
         <div id="content">
            
            <!-- Content -->
            <table border="1">
              <thead>
                  <tr>
                      <th>TaskID</th>
                      <th>TaskTitle</th>
                      <th>TaskDescription</th>
                      <th>&nbsp;</th>
                  </tr>
              </thead>
              
              <tbody>
                  <c:forEach var="vo" items="${tasks}">
                      <tr>
                          <td>${vo.taskId}</td>
                          <td>${vo.taskTitle}</td>
                          <td>${vo.taskDescription}</td>
                          <td>
                              <a href="<c:url value='/tasks/${vo.taskId}/'/>">상세 VIEW</a> </br>
                              <a href="<c:url value='/tasks/${vo.taskId}/edit'/>"> TASK 수정</a> </br>
                              
                              <form action="<c:url value='/tasks/${vo.taskId}'/>" method="post" style="display:inline;">
                                  <input type="hidden" name="_method" value="DELETE"/>
                                  <button type="submit" onclick="return confirm('정말 이 task를 삭제하시겠습니까? Are you sure you want to delete this task?')">Delete</button>
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
 
 
 <!--
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Task Management</title>
</head>
<body>
    <h1>Task Management</h1>
    
    <div id="createTask">
        <h2>Create New Task</h2>
        <form action="/tasks" method="post">
            <input type="text" name="taskTitle" placeholder="Task Title" required>
            <textarea name="taskDescription" placeholder="Task Description" required></textarea>
            <input type="hidden" name="projectId" value="${projectId}">
            <input type="hidden" name="userId" value="${userId}">
            <button type="submit">Create Task</button>
        </form>
    </div>

    <div id="taskList">
        <h2>Tasks</h2>
        <c:forEach var="task" items="${tasks}">
            <div>
                <h3>${task.taskTitle}</h3>
                <p>${task.taskDescription}</p>
                <p>Status: ${task.taskStatus}</p>
                <a href="/tasks/${task.taskId}">View Details</a>
                <form action="/tasks/${task.taskId}/delete" method="post" style="display:inline;">
                    <input type="hidden" name="userId" value="${userId}">
                    <button type="submit">Delete</button>
                </form>
            </div>
        </c:forEach>
    </div>
</body>
</html>
 -->
