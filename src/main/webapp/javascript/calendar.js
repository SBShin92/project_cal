document.addEventListener('DOMContentLoaded', function() {
    createCalendar(2024, 7); // 2024년 7월 캘린더 생성
});
function createCalendar(year, month) {
    const daysInMonth = new Date(year, month, 0).getDate();
    const firstDay = new Date(year, month - 1, 1).getDay();
    
    let date = 1;
    let calendarHtml = '';

    for (let i = 0; i < 6; i++) {
        let row = '<tr>';
        for (let j = 0; j < 7; j++) {
            if (i === 0 && j < firstDay) {
                row += '<td></td>';
            } else if (date > daysInMonth) {
                row += '<td></td>';
            } else {
                row += `<td>
                    <div class="date-content">
                        <div class="date${j === 0 || j === 6 ? ' weekend' : ''}">${date}</div>
                        ${getEventsForDate(date)}
                    </div>
                </td>`;
                date++;
            }
        }
        row += '</tr>';
        calendarHtml += row;
        if (date > daysInMonth) break;
    }

    document.querySelector('.calendar tbody').innerHTML = calendarHtml;
}

function getEventsForDate(date) {
    // 예시 이벤트 데이터
    const events = {
        5: '<div class="event blue">회의 09:30</div>',
        7: '<div class="event green">식사 21:00</div>',
        14: '<div class="event yellow">약속 15:30</div>',
        21: '<div class="event pink">점심 12:30</div>',
        28: '<div class="event blue">미팅 14:00</div>'
    };
    return events[date] || '';
}