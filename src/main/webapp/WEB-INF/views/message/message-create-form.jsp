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

<h1>${ sessionScope.authUser.userName } 님의 쪽지 보내기</h1>
<a href="<c:url value='/message/received' />">받은 쪽지</a>
<a href="<c:url value='/message/sended' />">보낸 쪽지</a>
<a href="<c:url value='/message/create' />">쪽지 보내기</a>
	<div>
		<form action="<c:url value='/message/create' />" method="POST">
			<!-- TODO: 보내는 이 아이디값 고쳐야함 -->
			<input type="hidden" name="senderUserName" id="senderUserName" value="1"></br>
			
			<!-- 유저이름? 별명? 실명? 이메일? 뭘로 보내야 할까 -->
			<label for="receiverUserNameOrEmail">받는 이</label>
			<input type="text" name="receiverUserNameOrEmail" id="receiverUserNameOrEmail" /></br>
			
			<label for="messageTitle">타이틀</label>
			<input type="text" name="messageTitle" id="messageTitle" /></br>
			
			<label for="messageDescription">내용</label>
			<textarea name="messageDescription" id="messageDescription" cols="50" rows="10"></textarea></br>
			
			<input type="submit" value="제출">
		
		</form>
	</div>
</body>
</html>