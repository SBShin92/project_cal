<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt"%>    

<!-- 0725 추가 -->
<%@ page import="com.github.sbshin92.project_cal.data.vo.UserVO" %> 
<%
  UserVO userVO = (UserVO) session.getAttribute("authUser");
%>

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
   <section class="project-content">
        
        <h1>Task List</h1>
        
        <c:if test="${not empty searchedTasks}">
        
        <table border="1">
              <thead>
                <tr>
                  <th>Task ID</th>
                  <th>Task Title</th>
                  <th>Task Creator User Name</th>
                  <th>Task Creator User Position</th>
                  <th>Task Status</th>
                  <th>&nbsp;</th>
                </tr>
              </thead>
            
              <tbody>
                  <c:forEach var="st" items="${searchedTasks}">
                    <!-- controller 모델.어트리뷰트한것을 보낸것을 -> jsp에서 받은것 -->
                    <!-- foreach for문 반복문을 통해 여러 태스크들을 하나씩 조회하기 -->
                    <tr>
                      <td>${st.taskId}</td>
                      <td>${st.taskTitle}</td> 
                      <td>${st.userName}</td><!--0725 -->
                      <td>${st.userPosition}</td><!--0725 -->
                      <td>${st.taskStatus}</td>
                      <td>
                        <form action="<c:url value='/tasks/viewTask/${st.taskId}' />"
                          method="get" style="display: inline;">
                              <button type="submit" class="btn btn-primary">상세 VIEW</button>             
                        </form>   
                      </td>
                   </tr>
                   
                  </c:forEach>
              </tbody>
              
        </table>
     </c:if>   
     
     <c:if test="${empty searchedTasks}">
        <p>No Tasks Found. </p>
     </c:if>
     
     	<!--0724-->
	    <nav>
		  <ul class="pagination">
		    <li class="page-item"><a class="page-link" href="?taskPage=${param.taskPage == 1 || param.taskPage == null ? 1 : param.taskPage - 1}&taskProjectTitle=${taskProjectTitle}">Previous</a></li> <!-- 재세팅해준 파람이여기오게됨 -->
	
		    <c:forEach begin="1" end="${(tasksCount / 10) + 1}" varStatus="num">
		      <li class="page-item"><a class="page-link" href="?taskPage=${num.index}&taskProjectTitle=${taskProjectTitle}">${num.index}</a></li>
		    </c:forEach>
		    <li class="page-item"><a class="page-link" href="?taskPage=${ totalPages != 10 ? (param.taskPage == null ? 1 : param.taskPage) : (param.taskPage == null ? 1 : param.taskPage) + 1}&taskProjectTitle=${taskProjectTitle}">Next</a></li>
		  </ul>
		</nav>
     </section>  
     

		
     <section class="project-content">
    <h1>Projects List</h1>
        <c:if test="${not empty searchedProjects}">
        <table border="1">
              <thead>
                <tr>
                  <th>projectId</th>
                  <th>projectTitle</th> 
                  <th>project Creator User Name</th>
                  <th>project Creator User Position</th>
                  <th>projectStatus</th>
                  <th>&nbsp;</th>
                </tr>
              </thead>
            
              <tbody>
                  <c:forEach var="sp" items="${searchedProjects}">
                    <!-- controller 모델.어트리뷰트한것을 보낸것을 -> jsp에서 받은것 -->
                    <!-- foreach for문 반복문을 통해 여러 태스크들을 하나씩 조회하기 -->
                    <tr>
                      <td>${sp.projectId }</td>
                      <td>${sp.projectTitle}</td>
                      <td>${sp.userName}</td><!--0725 -->
                      <td>${sp.userPosition}</td><!--0725 -->
                      <td>${sp.projectStatus}</td> 
                      <td>
                        <form action="<c:url value='/project/${sp.projectId}' />"
                          method="get" style="display: inline;">
                          <button type="submit" class="btn btn-primary">상세 VIEW</button>
                        </form>
                      </td>          
                   </tr>
                   
                  </c:forEach>
              </tbody>
              
        </table>
     </c:if>   
     
     <c:if test="${empty searchedProjects}">
        <p>No Projects Found. </p>
     </c:if>
     
     	<!--0725-->
	    <nav>
		  <ul class="pagination">
		    <li class="page-item"><a class="page-link" href="?projectPage=${param.projectPage == 1 || param.projectPage == null ? 1 : param.projectPage - 1}&taskProjectTitle=${taskProjectTitle}">Previous</a></li>
	
		    <c:forEach begin="1" end="${(projectCount / 10) + 1}" varStatus="num">
		      <li class="page-item"><a class="page-link" href="?projectPage=${num.index}&taskProjectTitle=${taskProjectTitle}">${num.index}</a></li>
		    </c:forEach>
		    <li class="page-item"><a class="page-link" href="?projectPage=${ totalProjectPages != 10 ? (param.projectPage == null ? 1 : param.projectPage) : (param.projectPage == null ? 1 : param.projectPage) + 1}&taskProjectTitle=${taskProjectTitle}">Next</a></li>
		  </ul>
		</nav>     
    </section>
    
   <a href="<c:url value='/calendar'/>" class="btn btn-secondary">캘린더로
            돌아가기</a> 
  </div>
  
  <script src="<c:url value='/bootstrap-5.1.3/js/bootstrap.min.js' />"></script>
  <script src="<c:url value='/javascript/main.js' />"></script>
  <script src="<c:url value='/javascript/calendar.js' />"></script>

</body>
</html>