const projectColors = [
	"#A8E6CF", // 연한 민트
	"#FFDFD3", // 연한 살구색
	"#B8D8F5", // 연한 하늘색
	"#FFD9DA", // 연한 분홍
	"#E0BBE4", // 연한 라벤더
	"#FFF5BA", // 연한 레몬
	"#DCEDC1", // 연한 라임
	"#C4E0F9", // 연한 파랑
	"#FFCAAF", // 연한 복숭아
	"#D5AAFF"  // 연한 보라
];

const today = new Date();
const viewYearMonthFromHeaderJSP = document.getElementsByClassName("view-date")[0];

document.addEventListener('DOMContentLoaded', () => {
	let year = parseInt(viewYearMonthFromHeaderJSP.textContent);
	let month = parseInt(viewYearMonthFromHeaderJSP.textContent.split("년 ")[1]);

	createCalendar(year, month);

	setupMonthYearPicker(year, month);

	// 토글버튼
	createProjectListToggleButton();
});

// 프로젝트 리스트 토글버튼 생성
function createProjectListToggleButton() {
	const rightPanel = document.querySelector('.right-panel');

	// 토글 버튼 생성
	const toggleButton = document.createElement('button');
	toggleButton.style.display = "none";
	toggleButton.textContent = '패널 닫기';
	toggleButton.classList.add('toggle-panel', 'btn', 'btn-primary');
	rightPanel.prepend(toggleButton);

	// 패널 토글 함수
	function togglePanel() {
		rightPanel.classList.toggle('show');
	}

	// 토글 버튼 이벤트 리스너
	toggleButton.addEventListener('click', function(e) {
		togglePanel();
	});

	// 패널 외부 클릭 시 패널 닫기
	document.addEventListener('click', function(e) {
		if (window.innerWidth <= 870 &&
			!rightPanel.contains(e.target) &&
			!toggleButton.contains(e.target) &&
			rightPanel.classList.contains('show')) {
			togglePanel();
		}
	});

	// 패널 내부 클릭 시 이벤트 전파 방지
	rightPanel.addEventListener('click', function(e) {
		e.stopPropagation();
	});

	const changeButton = document.getElementsByClassName("change-button")[0];
	
	// 화면 크기 변경 감지
	window.addEventListener('resize', function() {
		if (window.innerWidth > 870) {
			toggleButton.style.display = "none";
		} else {
			toggleButton.style.display = "flex";
			changeButton.innerHTML = "<i class='fas fa-arrows-rotate'></i>";
		}
	});

	// 초기 화면 크기에 따른 설정
	const getClickedDate = document.getElementById("clicked-date");
	console.log(getClickedDate);
	if (window.innerWidth <= 870 && getClickedDate != null) {
		toggleButton.style.display = "flex";
		togglePanel();
	}
	if (window.innerWidth <= 870) {
		changeButton.innerHTML = "<i class='fas fa-arrows-rotate'></i>";		
	}
}



function getProjectColor(projectId) {
	return projectColors[projectId % projectColors.length];
};

const createCalendar = async (year, month) => {
	// yearString, monthString
	let yearString = year.toString();
	let monthString = "";
	if (month < 10)
		monthString += "0";
	monthString += month.toString();

	// 클릭한 date는 몇일?
	const getClickedDate = document.getElementById("clicked-date");
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
						window.location.href = "calendar/date/" + urlYearMonth + "/" + urlDate;
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

// 예은 추가
const createProjectBars = (date) => {
	// 프로젝트 바를 담을 컨테이너 생성
	const projectBarsContainer = document.createElement("div");
	projectBarsContainer.className = "project-bars";

	// 주어진 날짜를 형식화 (예: "20240715" 형태로 변환)
	const formattedDate = formatDate(date);

	// 현재 날짜에 해당하는 프로젝트만 필터링
	const dateProjects = projectList.filter(project =>
		formattedDate >= project.startDate && formattedDate <= project.endDate
	);

	// 한 번에 표시할 최대 프로젝트 수 설정
	const maxVisibleProjects = 3;

	// 최대 표시 가능한 프로젝트 수만큼만 처리
	dateProjects.slice(0, maxVisibleProjects).forEach((project) => {
		// 각 프로젝트에 대한 바 요소 생성
		const projectBar = document.createElement("div");
		projectBar.className = "project-bar";

		// 프로젝트 ID를 기반으로 고유한 색상 지정
		projectBar.style.backgroundColor = getProjectColor(project.id);

		// 프로젝트 제목을 바 내부에 표시
		projectBar.textContent = project.title;

		// 마우스 오버 시 전체 제목을 툴팁으로 표시
		projectBar.title = project.title;

		// 생성한 프로젝트 바를 컨테이너에 추가
		projectBarsContainer.appendChild(projectBar);
	});


	// 표시할 수 있는 최대 수를 초과하는 프로젝트가 있다면
	if (dateProjects.length > maxVisibleProjects) {
		// '더 보기' 요소 생성
		const moreProjectsDiv = document.createElement("div");
		moreProjectsDiv.className = "more-projects";
		// 초과된 프로젝트 수를 표시
		moreProjectsDiv.textContent = `+${dateProjects.length - maxVisibleProjects}`;
		// '더 보기' 요소를 컨테이너에 추가
		projectBarsContainer.appendChild(moreProjectsDiv);
	}

	// 완성된 프로젝트 바 컨테이너 반환
	return projectBarsContainer;
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

	// 항상 표시되도록 설정
	monthYearPicker.style.display = 'block';

	applyDateButton.addEventListener('click', () => {
		const selectedYear = parseInt(yearSelect.value);
		const selectedMonth = parseInt(monthSelect.value);
		createCalendar(selectedYear, selectedMonth);
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