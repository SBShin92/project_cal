/**
 * 
 */
document.addEventListener('DOMContentLoaded', function() {
    // 전역 변수
    const projectId = document.querySelector('.project-detail').dataset.projectId;
    
    // 파일 업로드 버튼
    const uploadFileBtn = document.getElementById('uploadFile');
    uploadFileBtn.addEventListener('click', handleFileUpload);

    // 새 작업 추가
    const addTaskBtn = document.getElementById('addTask');
    const newTaskInput = document.getElementById('newTask');
    addTaskBtn.addEventListener('click', handleAddTask);

    // 프로젝트 수정 버튼
    const editProjectBtn = document.getElementById('editProject');
    editProjectBtn.addEventListener('click', handleEditProject);

    // 닫기 버튼
    const closeDetailBtn = document.getElementById('closeDetail');
    closeDetailBtn.addEventListener('click', handleCloseDetail);

    // 파일 업로드 처리
    function handleFileUpload() {
        // 파일 업로드 로직 구현
        console.log('File upload clicked');
    }

    // 새 작업 추가 처리
    function handleAddTask() {
        const taskDescription = newTaskInput.value.trim();
        if (taskDescription) {
            addNewTask(taskDescription);
            newTaskInput.value = '';
        }
    }

    // 새 작업을 서버에 추가하고 화면에 표시
    function addNewTask(description) {
        // AJAX 요청으로 서버에 새 작업 추가
        fetch(`/api/projects/${projectId}/tasks`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ description: description })
        })
        .then(response => response.json())
        .then(task => {
            // 새 작업을 화면에 추가
            const taskList = document.getElementById('taskList');
            const li = document.createElement('li');
            li.innerHTML = `
                <input type="checkbox" id="task${task.id}">
                <label for="task${task.id}">${task.description}</label>
            `;
            taskList.appendChild(li);
        })
        .catch(error => console.error('Error:', error));
    }

    // 프로젝트 수정 처리
    function handleEditProject() {
        // 프로젝트 수정 페이지로 이동 또는 모달 띄우기
        console.log('Edit project clicked');
    }

    // 상세 페이지 닫기 처리
    function handleCloseDetail() {
        // 이전 페이지로 돌아가기
        window.history.back();
    }

    // 작업 상태 변경 (체크박스 클릭 시)
    document.getElementById('taskList').addEventListener('change', function(e) {
        if (e.target.type === 'checkbox') {
            const taskId = e.target.id.replace('task', '');
            const completed = e.target.checked;
            updateTaskStatus(taskId, completed);
        }
    });

    // 작업 상태 업데이트
    function updateTaskStatus(taskId, completed) {
        fetch(`/api/tasks/${taskId}`, {
            method: 'PATCH',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ completed: completed })
        })
        .then(response => response.json())
        .then(data => console.log('Task updated:', data))
        .catch(error => console.error('Error:', error));
    }
});