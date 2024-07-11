const today = new Date();

document.addEventListener('DOMContentLoaded', () => {
    // Get the current year and month
    let currentYear = today.getFullYear();
    let currentMonth = today.getMonth() + 1;
    
    createCalendar(currentYear, currentMonth); // default: today's date
    document.getElementsByClassName("prev-button")[0].addEventListener("click", () => {
        currentMonth -= 1;
        if (currentMonth === 0) {
            currentMonth = 12;
            currentYear -= 1;
        }
        createCalendar(currentYear, currentMonth);
    });
    document.getElementsByClassName("next-button")[0].addEventListener("click", () => {
        currentMonth += 1;
        if (currentMonth === 13) {
            currentMonth = 1;
            currentYear += 1;
        }
        createCalendar(currentYear, currentMonth);
    });
});


const createCalendar = (year, month) => {
    // 기존 tbody 제거
    if (document.querySelector(".calendar table tbody") !== null)
        document.querySelector(".calendar table tbody").remove();

    // 지난 달 마지막 날짜, 요일
    const lastMonthEndDate = new Date(year, month - 1, 0).getDate();
    const lastMonthEndDay = new Date(year, month - 1, 0).getDay();

    // 이번 달 마지막 날짜, 요일
    const currentMonthEndDate = new Date(year, month, 0).getDate();
    const currentMonthEndDay = new Date(year, month, 0).getDay();

    // 캘린더 tbody테이블 생성
    let date = 1;
    const tbody = document.createElement("tbody");
    for (let i = 0; i < 6; i++) {
        const tr = document.createElement("tr");
        for (let j = 0; j < 7; j++) {
            const td = document.createElement("td");
            if (i === 0 && j <= lastMonthEndDay && lastMonthEndDay != 6) {
                tr.appendChild(td);
            } else if (date > currentMonthEndDate) {
                tr.appendChild(td);
            } else {
                td.className = j === 0 || j === 6 ? "weekend" : "";
                const divDate = document.createElement("div");
                divDate.textContent = date;
                td.appendChild(divDate);

                td.addEventListener("click", () => {
                    const clickedDateFormat = new Date(year, month, (i * 7) + j);
                    window.location.href = "calendar/" + formatDate(clickedDateFormat)});
                tr.appendChild(td);
                date++;
            }
        }
        tbody.appendChild(tr);
        if (date > currentMonthEndDate)
            break;
    }
    // tbody 쏴주기
    document.querySelector(".calendar table").appendChild(tbody);

    // 달력 날짜 갱신
    document.getElementsByClassName("view-date")[0].textContent = `${year}년 ${month}월`;
};

const formatDate = (date) => {
    var d = new Date(date),
        month = '' + (d.getMonth()),
        day = '' + d.getDate(),
        year = d.getFullYear();

    if (month.length < 2) 
        month = '0' + month;
    if (day.length < 2) 
        day = '0' + day;

    return [year, month, day].join('');
}




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
