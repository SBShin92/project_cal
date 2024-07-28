<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
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
            	<a class="btn btn-custom btn-outline-danger" href="<c:url value='/message/alarm' />">
                    <i class="fas fa-cake-candles me-2"></i>알람
                </a>
                <a class="btn btn-custom btn-outline-dark active" href="<c:url value='/message/received' />">
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
                <div class="card-header bg-light">
                    <h2 class="card-title h4 mb-0">${messageVO.messageTitle}</h2>
                </div>
                <div class="card-body">
                    <p class="card-text">${messageVO.messageDescription}</p>
                    <hr>
                    <div class="row">
                        <div class="col-md-6">
                            <p><strong><i class="fas fa-user me-2"></i>보낸이:</strong> ${messageVO.senderUserName}</p>
                            <p><strong><i class="fas fa-user me-2"></i>받는이:</strong> ${messageVO.receiverUserName}</p>
                        </div>
                        <div class="col-md-6 text-md-end">
                            <p><strong><i class="fas fa-clock me-2"></i>보낸 시간:</strong> ${messageVO.createdAt}</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
	<script src="<c:url value='/bootstrap-5.1.3/js/bootstrap.min.js' />"></script>
</body>
</html>