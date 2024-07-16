<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 업로드 결과</title>
</head>
<body>
	${originalFileName } 파일을 업로드 하였습니다.
	<br>

	<c:forEach var="file" items="${originalFileNameList }">
		${file }<br>
		<br>
	</c:forEach>


</body>
</html>