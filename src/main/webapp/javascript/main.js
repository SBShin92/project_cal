document.addEventListener('DOMContentLoaded', () => {

	// 쪽지함버튼 활성화
	document.getElementById("message-btn").addEventListener("click", openMessageList);
});


function openMessageList() {
    window.open("/project_cal/message", 'newwindow', 'width=500, height=400, top=150, left=900');
}



// 멤버 초대 활성화
document.addEventListener("DOMContentLoaded", () => {
    document.getElementById("inviteMemberBtn").onclick = function() {
        var projectId = projectId;
        window.open("/project_cal/inviteMember", 'newwindow', 'width=600,height=600');
    }
});
