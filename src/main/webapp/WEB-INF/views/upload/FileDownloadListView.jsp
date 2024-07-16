<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 다운로드 목록</title>
</head>
<body>

	<h3>폴더 내의 모든 파일 목록 출력</h3>

	<c:forEach var="file" items="${fileList }">
		<a href="<c:url value='/fileDownload/${file }' />">${file } 파일
			다운로드 </a>
		<br>
		<br>
	</c:forEach>

</body>
</html>