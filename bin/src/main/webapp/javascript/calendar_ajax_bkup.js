// const today = new Date();

// document.addEventListener('DOMContentLoaded', () => {
//     // Get the current year and month
//     let currentYear = today.getFullYear();
//     let currentMonth = today.getMonth() + 1;
    
//     createCalendar(currentYear, currentMonth); // default: today's date
//     document.getElementsByClassName("view-date")[0].textContent = `${currentYear}년 ${currentMonth}월`;
//     console.log(document.getElementsByClassName("view-date"));
// });

    
// const createCalendar = (year, month) => {
//     // 기존 tbody 제거
//     if (document.querySelector(".calendar table tbody") !== null)
//         document.querySelector(".calendar table tbody").remove();

//     // 지난 달 마지막 날짜, 요일
//     const lastMonthEndDate = new Date(year, month - 1, 0).getDate();
//     const lastMonthEndDay = new Date(year, month - 1, 0).getDay();

//     // 이번 달 마지막 날짜, 요일
//     const currentMonthEndDate = new Date(year, month, 0).getDate();
//     const currentMonthEndDay = new Date(year, month, 0).getDay();

//     // 로그(지워야함)
//     console.log("Last month: ", lastMonthEndDate, lastMonthEndDay, "This month: ", currentMonthEndDate, currentMonthEndDay);

//     // 캘린더 tbody테이블 생성
//     let date = 1;
//     const tbody = document.createElement("tbody");
//     for (let i = 0; i < 6; i++) {
//         const tr = document.createElement("tr");
//         for (let j = 0; j < 7; j++) {
//             const td = document.createElement("td");
//             if (i === 0 && j <= lastMonthEndDay && lastMonthEndDay != 6) {
//                 tr.appendChild(td);
//             } else if (date > currentMonthEndDate) {
//                 tr.appendChild(td);
//             } else {
//                 td.className = j === 0 || j === 6 ? "weekend" : "";
//                 const divDate = document.createElement("div");
//                 divDate.textContent = date;
//                 td.appendChild(divDate);

//                 td.addEventListener("click", getProjectListHandler)
//                 tr.appendChild(td);
//                 date++;
//             }
//         }
//         tbody.appendChild(tr);
//         if (date > currentMonthEndDate)
//             break;
//     }

//     // tbody 쏴주기
//     document.querySelector(".calendar table").appendChild(tbody);
// };


// const getProjectListHandler = async () => {
//     try {
//         const response = await fetch('http://localhost:8080/project_cal/api/projects'); // API_URL을 실제 API 주소로 변경해주세요.
//         const projects = await response.json();

//         const projectList = document.getElementById('projectList');

//         // 기존의 프로젝트 목록 비우기
//         while (projectList.firstChild) {
//             projectList.removeChild(projectList.firstChild);
//         }
		
// 		// 타이틀
// 		const listTitle = document.createElement("h4");
//         listTitle.innerText = "오늘의 프로젝트";
//         projectList.appendChild(listTitle);

//         // 새로운 프로젝트 목록 추가
//         projects.forEach(project => {
//             const projectItem = document.createElement("div");
//             projectItem.classList.add("project-item")

//             const projectTitle = document.createElement("div");
//             projectTitle.classList.add("project-title");
//             projectTitle.classList.add("event");
//             projectTitle.classList.add("blue");
//             projectTitle.textContent = project.projectTitle; // 'title'은 실제 프로젝트 객체의 속성에 따라 변경해야 합니다.

//             projectItem.appendChild(projectTitle);
//             projectList.appendChild(projectItem);
//         });
//     } catch (error) {
//         console.error("db를 불러오는 데 실패했습니다:", error);
//     }
// };


// function getEventsForDate(date) {
//     // 예시 이벤트 데이터
//     const events = {
//         5: '<div class="event blue">회의 09:30</div>',
//         7: '<div class="event green">식사 21:00</div>',
//         14: '<div class="event yellow">약속 15:30</div>',
//         21: '<div class="event pink">점심 12:30</div>',
//         28: '<div class="event blue">미팅 14:00</div>'
//     };
//     return events[date] || '';
// }
