<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.functions" prefix="fn" %>
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
	<div>
		<ul>
			<c:forEach var="messageVO" items="${messageVOs}">
				 <li><a href="<c:url value='/message/${url}/${ messageVO.messageId }' />">${messageVO.messageTitle}</a></li>
			</c:forEach>
		</ul>
	</div>

</body>
</html>