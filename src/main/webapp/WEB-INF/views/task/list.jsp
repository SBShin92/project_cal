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
                  <c:forEach var="vo" items="${tasks}"> <!-- controller 모델.어트리뷰트한것을 보낸것을 -> jsp에서 받은것 -->
                      <!-- foreach for문 반복문을 통해 여러 테스크들을 하나씩 조회하기 -->
                      <tr>
                          <td>${vo.taskId}</td>
                          <td>${vo.taskTitle}</td>
                          <td>${vo.taskDescription}</td>
                          <td>
                            
                              <form action="<c:url value='/tasks/viewTask/${vo.taskId}' />" method="get" style="display:inline;">
                                  <button type="submit" >상세 VIEW</button>
                              </form>
                                                            
                              <form action="<c:url value='/tasks/createTaskForm'/>" method="get" style="display:inline;">
                                  <input type="hidden" name="taskId" value="${vo.taskId}"/> <!-- pk -->
                                  <input type="hidden" name="userId" value="${vo.userId}"/>
                                  <input type="hidden" name="projectId" value="${vo.projectId}"/> 
                                  <input type="hidden" name="taskTitle" value="${vo.taskTitle}"/>
                                  <input type="hidden" name="taskDescription" value="${vo.taskDescription}"/>
                                  
                                  <button type="submit" onclick="return confirm('정말 이 task를 수정 하시겠습니까? Are you sure you want to edit this task?')">TASK 수정</button>
                              </form>
                                                            
                              <form action="<c:url value='/tasks/deleteTask/${vo.taskId}'/>" method="post" style="display:inline;">
                                  <!--  <input type="hidden" name="_method" value="DELETE"/> 없애도 됨 -->
                                  <button type="submit" onclick="return confirm('정말 이 task를 삭제하시겠습니까? Are you sure you want to delete this task?')">TASK 삭제</button>
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