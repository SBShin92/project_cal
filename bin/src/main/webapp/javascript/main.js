document.addEventListener('DOMContentLoaded', () => {

	// 쪽지함버튼 활성화
	document.getElementById("message-btn").addEventListener("click", openMessageList);
});


function openMessageList() {
    window.open("/project_cal/message", 'newwindow', 'width=600, height=620, top=150, left=400');
}


