const today = new Date();
const viewYearMonthFromHeaderJSP = document.getElementsByClassName("view-date")[0];

document.addEventListener('DOMContentLoaded', () => {
	let year = parseInt(viewYearMonthFromHeaderJSP.textContent);
	let month = parseInt(viewYearMonthFromHeaderJSP.textContent.split("년 ")[1]);

	createCalendar(year, month);
	
	setupMonthYearPicker(year, month);
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
        if (formattedDate >= project.startDate && formattedDate <= project.endDate) {
            const projectBar = document.createElement("div");
            projectBar.className = "project-bar";
			
			if (formattedDate == project.startDate)
				projectBar.classList.add("start-bar");
			if (formattedDate == project.endDate)
				projectBar.classList.add("end-bar");
				
            projectBar.style.backgroundColor = projectStatusToColor(project.projectStatus);
            projectBar.title = project.title;
            projectBarsContainer.appendChild(projectBar);
        }
    });

    return projectBarsContainer;
};


// 랜덤 색상 생성 (프로젝트 바 구분을 위해)
function projectStatusToColor(projectStatus) {
	let hex = "";
	if (projectStatus === "none")
		hex = "808080";
	else if (projectStatus === "진행중")
		hex = "FF0000";
	else if (projectStatus === "완료")
		hex = "000000";
	else if (projectStatus === "보류")
		hex = "000000";
    return '#' + hex;
}

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

// 예은추가


function setupMonthYearPicker(initialYear, initialMonth) {
    const monthYearSelector = document.getElementById('monthYearSelector');
    const monthYearPicker = document.getElementById('monthYearPicker');
    const monthSelect = document.getElementById('monthSelect');
    const yearSelect = document.getElementById('yearSelect');
    const applyDateButton = document.getElementById('applyDateButton');

    // 년도 옵션 생성 (현재 년도 기준 ±10년)
    const currentYear = new Date().getFullYear();
    for (let year = currentYear - 10; year <= currentYear + 10; year++) {
        const option = document.createElement('option');
        option.value = year;
        option.textContent = year + '년';
        yearSelect.appendChild(option);
    }

    // 초기 값 설정
    monthSelect.value = initialMonth;
    yearSelect.value = initialYear;

    monthYearSelector.addEventListener('click', () => {
        monthYearPicker.style.display = monthYearPicker.style.display === 'none' ? 'block' : 'none';
    });

    applyDateButton.addEventListener('click', () => {
        const selectedYear = parseInt(yearSelect.value);
        const selectedMonth = parseInt(monthSelect.value);
        createCalendar(selectedYear, selectedMonth);
        monthYearPicker.style.display = 'none';
        viewYearMonthFromHeaderJSP.textContent = `${selectedYear}년 ${selectedMonth}월`;
    });
}

//로그아웃
// 문서 로드 완료 시 실행
document.addEventListener('DOMContentLoaded', function() {
  const userProfile = document.querySelector('.user-profile');
  const userMenu = document.querySelector('.user-menu');
  const logoutButton = document.getElementById('logoutButton');

  // 사용자 프로필 클릭 시 메뉴 토글
  userProfile.addEventListener('click', function(e) {
    e.stopPropagation();
    userMenu.style.display = userMenu.style.display === 'none' ? 'block' : 'none';
  });

  // 문서 클릭 시 메뉴 닫기
  document.addEventListener('click', function(e) {
    if (!userProfile.contains(e.target) && !userMenu.contains(e.target)) {
      userMenu.style.display = 'none';
    }
  });

  // 로그아웃 버튼 클릭 이벤트
  if (logoutButton) {
    logoutButton.addEventListener('click', function(e) {
      e.preventDefault();
      // 로그인 페이지로 리다이렉트
      window.location.href = '/project_cal';
    });
  }
});