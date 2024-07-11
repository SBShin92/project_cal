const today = new Date();

document.addEventListener('DOMContentLoaded', () => {
    // Get the current year and month
    let currentYear = today.getFullYear();
    let currentMonth = today.getMonth() + 1;
    
    createCalendar(currentYear, 1); // default: today's date
});

// function createCalendar(year, month) {
//     // Last month's last date and day
//     const lastMonthStartDate = new Date(year, month - 1, 0).getDate();
//     const lastMonthStartDay = new Date(year, month - 1, 0).getDay();

//     // This month's last date and day
//     const currentMonthEndDate = new Date(year, month, 0).getDate();
//     const currentMonthEndDay = new Date(year, month, 0).getDay();

//     console.log("지난 달: ", lastMonthStartDate, lastMonthStartDay, "이번 달: ", currentMonthEndDate, currentMonthEndDay);
// }

    
const createCalendar = (year, month) => {
    // 지난 달 마지막 날짜, 요일
    const lastMonthEndDate = new Date(year, month - 1, 0).getDate();
    const lastMonthEndDay = new Date(year, month - 1, 0).getDay();

    // 이번 달 마지막 날짜, 요일
    const currentMonthEndDate = new Date(year, month, 0).getDate();
    const currentMonthEndDay = new Date(year, month, 0).getDay();

    console.log("Last month: ", lastMonthEndDate, lastMonthEndDay, "This month: ", currentMonthEndDate, currentMonthEndDay);

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
                tr.appendChild(td);
                date++;
            }
        }
        tbody.appendChild(tr);
        if (date > currentMonthEndDate)
            break;
    }

    document.querySelector(".calendar table").appendChild(tbody);
}


// const clickDateHandler = () => {



// }

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