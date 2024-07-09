<%@ page language="java" contentType="text/html; charset=UTF-8"
	 pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>

<jsp:include page="/WEB-INF/includes/start.jsp" />
<div class="project-detail">
    <header>
        <h1 id="projectTitle">${project.projectTitle}</h1>
        <p id="projectDate">
            프로젝트 기간: 
            <fmt:formatDate value="${project.createdAt}" pattern="yyyy-MM-dd" /> ~ 
            <fmt:formatDate value="${project.updatedAt}" pattern="yyyy-MM-dd" />
        </p>
    </header>

    <main>
        <section class="project-content">
            <h2>상세 내용</h2>
            <div id="projectDescription">
                <p>${project.projectDescription}</p>
            </div>
            <!-- 이미지 부분은 실제 데이터에 맞게 수정 필요 -->
        </section>

        <section class="project-files">
            <h2>첨부 파일</h2>
            <!-- 파일 목록 부분은 실제 데이터에 맞게 수정 필요 -->
            <button id="uploadFile" class="btn">파일 업로드</button>
        </section>
    </main>

    <section class="project-tasks">
        <h2>작업 목록</h2>
        <!-- 작업 목록 부분은 실제 데이터에 맞게 수정 필요 -->
        <div class="task-input">
            <input type="text" id="newTask" placeholder="새 작업 추가">
            <button id="addTask" class="btn btn-small">추가</button>
        </div>
    </section>

    <footer>
        <button id="editProject" class="btn btn-primary">수정</button>
        <button id="closeDetail" class="btn">닫기</button>
    </footer>
</div>

<script src="<c:url value='/javascript/detail.js' />"></script>

