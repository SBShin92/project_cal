<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt"%> 
<!-- 0725 추가 -->
<%@ page import="com.github.sbshin92.project_cal.data.vo.UserVO" %> 
   
<!DOCTYPE html>

<!--  태스크 상세보기 페이지 -->

<html>
<head>
<meta charset="UTF-8">
<title>Task 상세뷰 페이지</title>
<link type="text/css" rel="stylesheet" href='<c:url value="/bootstrap-5.1.3/css/bootstrap.min.css" />' />
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" rel="stylesheet">
<link rel="stylesheet" href="<c:url value='/css/detail.css'/>" type="text/css">
<link rel="stylesheet" href="<c:url value='/css/task.css'/>" type="text/css"><!-- 0724추가 -->
<link type="text/css" rel="stylesheet" href='<c:url value="/css/main.css" />' />
</head>
<body>
  <jsp:include page="/WEB-INF/includes/header.jsp" />
  <div id="container">
    <section class="project-content">
      <h1>Task Details</h1>
            
      <div id="wrapper" class="editable">
        <!-- <div id="content"> -->

        <!-- Task Details -->
           <table border="1">
          <tr>
            <th>Task ID</th>
            <th>Task Creator User Name</th>
            <th>Task Creator User Position</th>
            <th>Task Status</th>
          </tr>
          <tr>
            <td>${Task.taskId}</td>
            <td>${Task.userName}</td><!-- 수정필요 -->
            <td>${Task.userPosition}</td><!-- 수정필요 -->
            <td>${Task.taskStatus == null ? '' : Task.taskStatus}</td>
          </tr>
          <tr>
            <th>Task Title</th>
            <th colspan="3">Task Description</th>
          </tr>
          <tr>
            <td>${Task.taskTitle}</td>
            <td colspan="3">${Task.taskDescription}</td>
          </tr>
        </table>


      </div>
    </section>
    
    <div>
    <a href="<c:url value='/calendar'/>" class="btn btn-secondary">캘린더로
            돌아가기</a>
    </div>
      
  </div>

  <script src="<c:url value='/bootstrap-5.1.3/js/bootstrap.min.js' />"></script>
  <script src="<c:url value='/javascript/main.js' />"></script>
  <script src="<c:url value='/javascript/calendar.js' />"></script>

</body>
</html>