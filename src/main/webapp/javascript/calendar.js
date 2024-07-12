const today = new Date();

document.addEventListener('DOMContentLoaded', () => {
	// Get the current year and month
	let currentYear = today.getFullYear();
	let currentMonth = today.getMonth() + 1;

	createCalendar(currentYear, currentMonth); // default: today's date

	// 다음 달 버튼
	document.getElementsByClassName("prev-button")[0].addEventListener("click", () => {
		currentMonth -= 1;
		if (currentMonth === 0) {
			currentMonth = 12;
			currentYear -= 1;
		}
		createCalendar(currentYear, currentMonth);
	});

	// 이전 달 버튼
	document.getElementsByClassName("next-button")[0].addEventListener("click", () => {
		currentMonth += 1;
		if (currentMonth === 13) {
			currentMonth = 1;
			currentYear += 1;
		}
		createCalendar(currentYear, currentMonth);
	});
});



const createCalendar = async (year, month) => {
	// yearString, monthString
	let yearString = year.toString();
	let monthString = "";
	if (month < 10)
		monthString += "0";
	monthString += month.toString();


	// 지난 달 마지막 요일
	const lastMonthEndDay = new Date(year, month - 1, 0).getDay();

	// 이번 달 마지막 날짜
	const currentMonthEndDate = new Date(year, month, 0).getDate();

	// 년 월 공휴일 정보 받기
	const holiday = await getHolidayMonth(yearString, monthString);

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
				// 날짜를 문자열로 변환
				let dateString = formatDate(new Date(year, month - 1, date));

				// 주말이거나 공휴일이면 weekend 클래스 추가
				td.className = (j === 0 || j === 6) ? "weekend" : "";
				
				// 날짜 숫자 표시 태그
				const divDate = document.createElement("div");
				divDate.textContent = date;

				
				if (holiday.some(h => h.locdate === dateString)) {
					td.classList.add("weekend");
					const holidayName = holiday.find(h => h.locdate === dateString).dateName;
					divDate.textContent += ` (${holidayName})`;
				}
				(function(date) {
					td.addEventListener("click", () => {
						const clickedDateFormat = new Date(year, month - 1, date);
						window.location.href = "calendar/" + formatDate(clickedDateFormat);
					});
				})(date);
				td.appendChild(divDate);
				tr.appendChild(td);
				date++;
			}
		}
		tbody.appendChild(tr);
		if (date > currentMonthEndDate)
			break;
	}

	// 기존 tbody 제거
	const prevTbody = document.querySelector(".calendar table tbody");
	if (prevTbody !== null)
		prevTbody.remove();
	// tbody 쏴주기
	document.querySelector(".calendar table").appendChild(tbody);
	// 달력 날짜 갱신
	document.getElementsByClassName("view-date")[0].textContent = `${year}년 ${month}월`;
};

const formatDate = (date) => {
	returnYear = String(date.getFullYear());
	returnMonth = String(date.getMonth() + 1);
	returnDate = String(date.getDate());

	if (returnMonth.length < 2) 
		returnMonth = '0' + returnMonth;
	if (returnDate.length < 2)
		returnDate = '0' + returnDate;

	return [returnYear, returnMonth, returnDate].join('');
}



// 공휴일 체크
const getHolidayMonth = async (year, month) => {
	const holiday = [];
	const url = 'http://apis.data.go.kr/B090041/openapi/service/SpcdeInfoService/getRestDeInfo';
	let queryParams = '?' + encodeURIComponent('serviceKey') + '=' + 'sBPfuffF30IEOA0wWBrEhs6qPxJtvKZTsZFbjNT2SeEp1lTXBgGPES1b%2FxcnObIXvV4lQBxlRD0GI%2FLM%2Fb9GJQ%3D%3D';
	queryParams += '&' + encodeURIComponent('solYear') + '=' + encodeURIComponent(year);
	queryParams += '&' + encodeURIComponent('solMonth') + '=' + encodeURIComponent(month);

	try {
		const response = await fetch(url + queryParams);
		if (!response.ok) {
			console.error("공휴일 api를 불러오는 데 실패했습니다.");
			return;
		}
		const text = await response.text();
		const parser = new DOMParser();
		const xmlDoc = parser.parseFromString(text, "text/xml");

		const items = xmlDoc.getElementsByTagName("item");

		if (items.length === 0)
			return holiday;
		for (let i = 0; i < items.length; i++) {
			const locdate = items[i].getElementsByTagName("locdate")[0].textContent;
			const dateName = items[i].getElementsByTagName("dateName")[0].textContent;
			holiday.push({ "locdate": locdate, "dateName": dateName });
		}
	} catch (error) {
		console.error(error);
	}
	return holiday;
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