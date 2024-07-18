<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Task List</title>
<link rel="stylesheet" href="<c:url value='/css/detail.css'/>"
  type="text/css">
</head>

<body>
    
  <div id="container">
    <c:import url="/WEB-INF/includes/header.jsp" />

    <section>
        <h1>Task List</h1>
        
        <table border="1">
              <thead>
                <tr>
                  <th>TaskID</th>
                  <th>TaskTitle</th>
                  <th>TaskDescription</th>
                  <th>CreatedAt</th>
                  <th>UpdatedAt</th>
                  <th>TaskStatus</th>
                  <th>&nbsp;</th>
                </tr>
              </thead>
            
              <tbody>
                  <c:forEach var="st" items="${SearchedTasks}">
                    <!-- controller 모델.어트리뷰트한것을 보낸것을 -> jsp에서 받은것 -->
                    <!-- foreach for문 반복문을 통해 여러 테스크들을 하나씩 조회하기 -->
                    <tr>
                      <td>${st.taskId}</td>
                      <td>${st.taskTitle}</td>
                      <td>${st.taskDescription}</td>
                      <td>${st.createdAt}</td>
                      <td>${st.updatedAt}</td>
                      <td>${st.taskStatus}</td>
                      <td>
                        <form
                          action="<c:url value='/tasks/viewTask/${st.taskId}' />"
                          method="get" style="display: inline;">
                          <button type="submit">상세 VIEW</button>
                        </form>
                      </td>
                   </tr>
                   
                  </c:forEach>
              </tbody>
              
        </table>
            
    </section>
    
    <section>
   
    </section>
    
  </div>
</body>
</html>