<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ page session="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>쪽지함</title>
</head>
<body>

<h1>${ sessionScope.authUser.userName } 님의 쪽지함</h1>
<a href="<c:url value='/message/received' />">받은 쪽지</a>
<a href="<c:url value='/message/sended' />">보낸 쪽지</a>
<a href="<c:url value='/message/create' />">쪽지 보내기</a>
<c:forEach var="message" items="${messages}">
	<div>
		<h2>${message.messageTitle}</h2>
		<p>${message.messageDescription}</p>
		<p>보낸이: ${message.senderUserName}</p>
		<p>받는이: ${message.receiverUserName}</p>
		<p>보낸 시간: ${message.createdAt}</p>
	</div>
</c:forEach>

</body>
</html>