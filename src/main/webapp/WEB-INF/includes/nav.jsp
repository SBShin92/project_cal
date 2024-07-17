<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<nav class="top-nav">
<!-- 예은추가 -->
	  <button id="monthYearSelector" class="nav-btn">월/년</button>
    <div id="monthYearPicker" style="display: none;">
        <select id="monthSelect">
            <option value="1">1월</option>
            <option value="2">2월</option>
            <option value="3">3월</option>
            <option value="4">4월</option>
            <option value="5">5월</option>
            <option value="6">6월</option>
            <option value="7">7월</option>
            <option value="8">8월</option>
            <option value="9">9월</option>
            <option value="10">10월</option>
            <option value="11">11월</option>           
            <option value="12">12월</option>
        </select>
        <select id="yearSelect">
            <!-- 년도 옵션들은 JavaScript로 동적 생성 -->
        </select>
        <button id="applyDateButton">적용</button>
    </div>
  <!-- 여기까지 예은추가 -->
    
	<button class="nav-btn">개인/전체 일정</button>
	<button class="nav-btn" id="inviteMemberBtn">멤버 초대</button>
	<button class="nav-btn" id="message-btn">쪽지함</button>
	<button class="nav-btn">휴지통</button>
</nav>