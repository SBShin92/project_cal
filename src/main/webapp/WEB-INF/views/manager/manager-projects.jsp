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
                <input type="text" class="search-bar" placeholder="프로젝트 또는 사용자 검색...">
            </div>
            
            <div class="tab">
            	<a class="tablinks" href="<c:url value='/manager/projects' />">프로젝트</a>
            	<a class="tablinks" href="<c:url value='/manager/users' />">사용자</a>
            	<a class="tablinks" href="<c:url value='/manager/roles' />">권한</a>
            </div>

            <div id="Projects" class="tabcontent">
                <h2>프로젝트 관리</h2>
                <button class="btn">새 프로젝트 생성</button>
                <table>
                    <thead>
                        <tr>
                            <th>프로젝트명</th>
                            <th>팀장</th>
                            <th>시작일</th>
                            <th>종료일</th>
                            <th>상태</th>
                            <th>작업</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>프로젝트 A</td>
                            <td>홍길동</td>
                            <td>2024-07-17</td>
                            <td>2024-08-17</td>
                            <td>진행중</td>
                            <td>
                                <button class="btn">상세</button>
                                <button class="btn">수정</button>
                                <button class="btn">삭제</button>
                            </td>
                        </tr>
                        <!-- 추가 프로젝트 행 -->
                    </tbody>
                </table>
            </div>

            
            
        </div>
    </div>
</body>
</html>