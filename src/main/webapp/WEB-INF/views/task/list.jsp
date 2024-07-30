<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!--  태스크 목록 페이지 
1.task/list.jsp (태스크 목록)
2.task/view.jsp (태스크 상세 보기)
3.task/form.jsp (태스크 생성/수정 폼)

-->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Task List</title>
<link type="text/css" rel="stylesheet" href='<c:url value="/css/main.css" />' />
<link type="text/css" rel="stylesheet" href='<c:url value="/bootstrap-5.1.3/css/bootstrap.min.css" />' />
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" rel="stylesheet">
<link rel="stylesheet" href="<c:url value='/css/detail.css'/>" type="text/css">
<link rel="stylesheet" href="<c:url value='/css/task.css'/>" type="text/css"><!-- 0724추가 -->
</head>

<body>

  <jsp:include page="/WEB-INF/includes/header.jsp" />
  <div id="container">
    <h1>Task List</h1> 
    <a href="<c:url value='/tasks/createTaskForm'/>">테스트 생성</a>

    <div id="wrapper">
      <div id="task-list">

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
            <c:forEach var="vo" items="${listTasks}">
              <!-- controller 모델.어트리뷰트한것을 보낸것을 -> jsp에서 받은것 -->
              <!-- foreach for문 반복문을 통해 여러 태스크들을 하나씩 조회하기 -->
              <tr>
                <td>${vo.taskId}</td>
                <td>${vo.taskTitle}</td>
                <td>${vo.taskDescription}</td>
                <td>

                  <form
                    action="<c:url value='/tasks/viewTask/${vo.taskId}' />"
                    method="get" style="display: inline;">
                    <button type="submit">상세 VIEW</button>
                  </form>

                  <form action="<c:url value='/tasks/createTaskForm'/>"
                    method="get" style="display: inline;">
                    <input type="hidden" name="taskId" value="${vo.taskId}" />
                    <!-- pk -->
                    <input type="hidden" name="userId" value="${vo.userId}" /> 
                    <input type="hidden" name="projectId" value="${vo.projectId}" /> 
                    <input type="hidden" name="taskTitle" value="${vo.taskTitle}" /> 
                    <input type="hidden" name="taskDescription" value="${vo.taskDescription}" />

                    <button type="submit"
                      onclick="return confirm('정말 이 task를 수정 하시겠습니까? Are you sure you want to edit this task?')">TASK
                      수정</button>
                  </form>

                  <form
                    action="<c:url value='/tasks/deleteTask/${vo.taskId}'/>"
                    method="post" style="display: inline;">
                    <!--  <input type="hidden" name="_method" value="DELETE"/> 없애도 됨 -->
                    <button type="submit"
                      onclick="return confirm('정말 이 task를 삭제하시겠습니까? Are you sure you want to delete this task?')">TASK
                      삭제</button>
                  </form>

                </td>
              </tr>
            </c:forEach>
          </tbody>
        </table>

      </div>

    </div>
  
  </div>
  
<script src="<c:url value='/bootstrap-5.1.3/js/bootstrap.min.js' />"></script>
<script src="<c:url value='/javascript/calendar.js' />"></script>
</body>
</html>