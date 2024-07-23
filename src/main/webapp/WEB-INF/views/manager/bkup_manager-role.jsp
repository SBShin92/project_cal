<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ page session="true" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link type="text/css" rel="stylesheet" href='<c:url value="/css/manager.css" />' />
    <title>OurCalendar</title>
</head>
<body>
    <button class="menu-toggle" onclick="toggleMenu()">☰</button>
    <div class="container">
        <jsp:include page="/WEB-INF/includes/manager-nav.jsp" />
        <div class="main-content">
            <div class="header">
                <h1>권한 관리</h1>
                <input type="text" class="search-bar" placeholder="프로젝트 또는 사용자 검색...">
            </div>
            <div id="Permissions" class="tabcontent">
                <a class="btn" href="<c:url value='/manager/role/create' />">새 권한 그룹 생성</a>
                <table>
                    <thead>
                        <tr>
                            <th>권한 그룹</th>
                            <th>설명</th>
                            <th>사용자 수</th>
                            <th>작업</th>
                        </tr>
                    </thead>
                    <tbody>
						<c:forEach items="${ roleVOs }" var="roleVO" varStatus="status">
							<tr>
								<td>${ roleVO.roleName }</td>
								<td>${ roleVO.roleDescription }</td>
								<td>${ roleVO.roleUsersCount }</td>
								<td>
									<form class="deleteForm" action="<c:url value='/manager/role/delete/${ roleVO.roleId }' />" method="POST">
										<a class="btn" href="<c:url value='/manager/role/${roleVO.roleId}' />">상세</a>
										<input type="submit" class="btn" value="삭제">
									</form>
								</td>
							</tr>
						</c:forEach>
					</tbody>
                </table>
            </div>
        </div>
    </div>
    <script src="<c:url value='/javascript/manager.js'/>"></script>
</body>
</html>