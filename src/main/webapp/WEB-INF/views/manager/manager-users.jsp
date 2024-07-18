<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ page session="true" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link type="text/css" rel="stylesheet" href='<c:url value="/css/manager.css" />' />
    <script src="<c:url value='/js/manager.js' />"></script>
    <title>OurCalendar - 사용자 관리</title>
</head>
<body>
    <button class="menu-toggle">☰</button>
    <div class="container">
        <div class="sidebar" id="sidebar">
            <h2>관리자 메뉴</h2>
            <div class="menu-item">대시보드</div>
            <div class="menu-item">프로젝트 관리</div>
            <div class="menu-item">사용자 관리</div>
            <div class="menu-item">권한 관리</div>
            <div class="menu-item">보고서</div>
            <div class="menu-item">설정</div>
        </div>
        <div class="main-content">
            <div class="header">
                <h1>프로젝트 관리 시스템</h1>
                <input type="text" class="search-bar" placeholder="사용자 검색...">
            </div>

            <div class="tab">
                <a class="tablinks" href="<c:url value='/manager/projects' />">프로젝트</a>
                <a class="tablinks active" href="<c:url value='/manager/users' />">사용자</a>
                <a class="tablinks" href="<c:url value='/manager/roles' />">권한</a>
            </div>

            <div id="Users" class="tabcontent">
                <h2>사용자 관리</h2>
                <button id="createUserBtn" class="btn">새 사용자 추가</button>
                <table>
                    <thead>
                        <tr>
                            <th>이름</th>
                            <th>이메일</th>
                            <th>역할</th>
                            <th>소속 프로젝트</th>
                            <th>작업</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="role" items="${roles}">
                            <tr>
                                <td>${role.roleName}</td>
                                <td>${role.userEmail}</td>
                                <td>${role.userAuthority}</td>
                                <td>${role.projects}</td>
                                <td>
                                    <button class="btn view-role" data-role-id="${role.roleId}">상세</button>
                                    <button class="btn edit-role" data-role-id="${role.roleId}">수정</button>
                                    <button class="btn delete-role" data-role-id="${role.roleId}">삭제</button>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

   <!-- 사용자 생성 모달 -->
<div id="createUserModal" class="modal">
    <div class="modal-content">
        <span class="close">&times;</span>
        <h2>새 사용자 추가</h2>
        <form action="<c:url value='/manager/users/create' />" method="post">
            <div>
                <label for="roleName">역할 이름:</label>
                <input type="text" id="roleName" name="roleName" required>
            </div>
            <div>
                <label for="userEmail">이메일:</label>
                <input type="email" id="userEmail" name="userEmail" required>
            </div>
            <div>
                <label for="userAuthority">권한:</label>
                <select id="userAuthority" name="userAuthority">
                    <option value="ROLE_USER">일반 사용자</option>
                    <option value="ROLE_ADMIN">관리자</option>
                </select>
            </div>
            <button type="submit" class="btn">생성</button>
        </form>
    </div>
</div>

    <!-- 사용자 수정 모달 -->
    <div id="editUserModal" class="modal">
        <div class="modal-content">
            <span class="close">&times;</span>
            <h2>사용자 수정</h2>
            <form id="editRoleForm">
                <input type="hidden" id="editRoleId" name="roleId">
                <div>
                    <label for="editRoleName">역할 이름:</label>
                    <input type="text" id="editRoleName" name="roleName" required>
                </div>
                <div>
                    <label for="editUserEmail">이메일:</label>
                    <input type="email" id="editUserEmail" name="userEmail" required>
                </div>
                <div>
                    <label for="editUserAuthority">권한:</label>
                    <select id="editUserAuthority" name="userAuthority">
                        <option value="ROLE_USER">일반 사용자</option>
                        <option value="ROLE_ADMIN">관리자</option>
                    </select>
                </div>
                <button type="submit" class="btn">수정</button>
            </form>
        </div>
    </div>
</body>
</html>