<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<%@ taglib uri="jakarta.tags.functions" prefix="fn" %>
<%@ page session="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link type="text/css" rel="stylesheet" href='<c:url value="/bootstrap-5.1.3/css/bootstrap.min.css" />' />
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" rel="stylesheet">
<title>메시지함</title>
</head>
<body>
    <div class="container py-5">
        <div class="message-box p-4">
            <h1 class="message-title mb-4">
                <i class="fas fa-envelope me-2"></i>${sessionScope.authUser.userName} 님의 메시지함
            </h1>
            <div class="btn-group mb-4" role="group" aria-label="Message actions">
                <a class="btn btn-custom btn-outline-danger active" href="<c:url value='/message/alarm' />">
                    <i class="fas fa-cake-candles me-2"></i>알람
                </a>
                <a class="btn btn-custom btn-outline-dark" href="<c:url value='/message/received' />">
                    <i class="fas fa-inbox me-2"></i>받은 쪽지
                </a>
                <a class="btn btn-custom btn-outline-dark" href="<c:url value='/message/sended' />">
                    <i class="fas fa-paper-plane me-2"></i>보낸 쪽지
                </a>
                <a class="btn btn-custom btn-outline-dark" href="<c:url value='/message/create' />">
                    <i class="fas fa-pen me-2"></i>쪽지 보내기
                </a>
            </div>
            <div class="card">
                <ul class="list-group list-group-flush">
                    <c:forEach var="messageVO" items="${messageVOs}">
                    	<c:if test="${ messageVO.isAlarm == true }">
	                        <li class="list-group-item">
	                        	<div class="row">
								    <a href="<c:url value='/message/${url}/${messageVO.messageId}' />" class="text-decoration-none text-danger col-8">
								    	<c:choose>
								    		<c:when test="${ messageVO.readStatus.equals('unread') }"><b><i class="fas fa-inbox me-2"></i>${messageVO.messageTitle}</b></c:when>
								    		<c:otherwise><i class="fas fa-envelope-open-text me-2"></i>${messageVO.messageTitle}</c:otherwise>
								    	</c:choose>
								    </a>
								    <span class="col-4"><fmt:formatDate value="${messageVO.createdAt}" pattern="MM/dd HH:mm" />, ${messageVO.readStatus}</span>
							    </div>
							</li>
						</c:if>
                    </c:forEach>
                </ul>
            </div>
        </div>
        <nav>
		  <ul class="pagination mx-4">
		  <c:if test="${param.page > 1 }">
		    <li class="page-item"><a class="page-link" href="?page=${param.page - 1}">Previous</a></li>
		  </c:if>
		  <c:if test="${totalPages == 10}">
		    <li class="page-item"><a class="page-link" href="?page=${param.page == null ? 2 : param.page + 1}">Next</a></li>
		  </c:if>
		  </ul>
		</nav>
    </div>
<script src="<c:url value='/bootstrap-5.1.3/js/bootstrap.min.js' />"></script>
</body>
</html>