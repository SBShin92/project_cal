document.addEventListener('DOMContentLoaded', () => {

	// 쪽지함버튼 활성화
	document.getElementById("message-btn").addEventListener("click", openMessageList);
});


function openMessageList() {
    window.open("/project_cal/message", 'newwindow', 'width=500, height=400, top=150, left=900');
}