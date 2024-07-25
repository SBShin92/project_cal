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
        <div class="message-box">
            <h1 class="message-title mb-4">
                <i class="fas fa-envelope me-2"></i>${sessionScope.authUser.userName} 님의 쪽지 보내기
            </h1>
            <div class="btn-group mb-4" role="group" aria-label="Message actions">
            	<a class="btn btn-custom btn-outline-danger" href="<c:url value='/message/alarm' />">
                    <i class="fas fa-cake-candles me-2"></i>알람
                </a>
                <a class="btn btn-custom btn-outline-dark" href="<c:url value='/message/received' />">
                    <i class="fas fa-inbox me-2"></i>받은 쪽지
                </a>
                <a class="btn btn-custom btn-outline-dark" href="<c:url value='/message/sended' />">
                    <i class="fas fa-paper-plane me-2"></i>보낸 쪽지
                </a>
                <a class="btn btn-custom btn-outline-dark active" href="<c:url value='/message/create' />">
                    <i class="fas fa-pen me-2"></i>쪽지 보내기
                </a>
            </div>
                
            <form action="<c:url value='/message/create' />" method="POST">
                <input type="hidden" name="senderUserName" id="senderUserName" value="1">
                
                <div class="mb-3">
                    <label for="receiverUserNameOrEmail" class="form-label">
                        <i class="fas fa-user me-2"></i>받는 이
                    </label>
                    <input type="text" class="form-control" name="receiverUserNameOrEmail" id="receiverUserNameOrEmail" placeholder="이름, 별명 또는 이메일" required>
                </div>
                
                <div class="mb-3">
                    <label for="messageTitle" class="form-label">
                        <i class="fas fa-heading me-2"></i>제목
                    </label>
                    <input type="text" class="form-control" name="messageTitle" id="messageTitle" placeholder="쪽지 제목" required>
                </div>
                
                <div class="mb-3">
                    <label for="messageDescription" class="form-label">
                        <i class="fas fa-envelope-open-text me-2"></i>내용
                    </label>
                    <textarea class="form-control" name="messageDescription" id="messageDescription" rows="5" placeholder="쪽지 내용을 입력하세요" required></textarea>
                </div>
                
                <button type="submit" class="btn btn-primary btn-lg">
                    쪽지 보내기
                </button>
            </form>
        </div>
    </div>
	<script src="<c:url value='/bootstrap-5.1.3/js/bootstrap.min.js' />"></script>
</body>
</html>