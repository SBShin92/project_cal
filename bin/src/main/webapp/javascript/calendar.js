const today = new Date();
const viewYearMonthFromHeaderJSP = document.getElementsByClassName("view-date")[0];

document.addEventListener('DOMContentLoaded', () => {
	let year = parseInt(viewYearMonthFromHeaderJSP.textContent);
	let month = parseInt(viewYearMonthFromHeaderJSP.textContent.split("년 ")[1]);

	createCalendar(year, month);
});


const createCalendar = async (year, month) => {
	// yearString, monthString
	let yearString = year.toString();
	let monthString = "";
	if (month < 10)
		monthString += "0";
	monthString += month.toString();

	// 클릭한 date는 몇일?
	const getClickedDate = document.getElementById("view-date");
	let clickedDate = 0;
	if (getClickedDate !== null) {
		clickedDate = parseInt(getClickedDate.textContent);
	}

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
				
				// td에 yyyyMMdd 속성 추가
				td.setAttribute("data-date-str", dateString);

				// 주말이면 sunday, saturday 클래스 추가
				if (j === 0)
					td.classList.add("sunday");
				else if (j === 6)
					td.classList.add("saturday");
				
				// 날짜 숫자 표시 태그
				const divDate = document.createElement("div");
				divDate.textContent = date;
				td.classList.add("calendar-date");
				if (date == clickedDate)
					td.classList.add("clicked");

				// 공휴일이면 holiday 클래스 추가 및 공휴일 글자 삽입
				if (holiday.some(h => h.locdate === dateString)) {
					td.classList.add("holiday");
					const holidayName = holiday.find(h => h.locdate === dateString).dateName;
					divDate.textContent += ` ${holidayName}`;
				}
				
				// 날짜에 클릭 이벤트 추가(프로젝트 리스트 출력)
				(function(date) {
					td.addEventListener("click", () => {
						const urlYearMonth = String(year) + String(month < 10 ? "0" + month : month);
						const urlDate = String(date);
						window.location.href = "calendar/" + urlYearMonth + "/" + urlDate;
					});
				})(date);
				td.appendChild(divDate);

				// 프로젝트 기간 바 추가
				const currentDate = new Date(year, month - 1, date);
				const projectBars = createProjectBars(currentDate);
				td.appendChild(projectBars);

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

// 프로젝트 기간 바 생성
const createProjectBars = (date) => {
    const projectBarsContainer = document.createElement("div");
    projectBarsContainer.className = "project-bars";
	const formattedDate = formatDate(date);
    projectList.forEach(project => {
        if (formattedDate >= project.startDate && formattedDate <= Date(project.endDate)) {
            const projectBar = document.createElement("div");
            projectBar.className = "project-bar";
            projectBar.style.backgroundColor = getRandomColor();
            projectBar.title = project.title;
            projectBarsContainer.appendChild(projectBar);
        }
    });

    return projectBarsContainer;
};


// 랜덤 색상 생성 (프로젝트 바 구분을 위해)
const getRandomColor = () => {
    const letters = '0123456789ABCDEF';
    let color = '#';
    for (let i = 0; i < 6; i++) {
        color += letters[Math.floor(Math.random() * 16)];
    }
    return color;
};

// 공휴일 체크 API
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