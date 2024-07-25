/**
 * 프로젝트 상세 페이지 JavaScript
 */
document.addEventListener('DOMContentLoaded', function() {
    // 전역 변수
    const projectId = document.querySelector('.project-detail').dataset.projectId;

    // 엘리먼트 참조
    const uploadFileBtn = document.getElementById('uploadFile');
    const addTaskBtn = document.getElementById('addTask');
    const newTaskInput = document.getElementById('newTask');
    const editProjectBtn = document.getElementById('editProject');
    const closeDetailBtn = document.getElementById('closeDetail');
    const taskList = document.getElementById('taskList');
    const fileInput = document.getElementById('fileUpload');

    // 이벤트 리스너 설정
    uploadFileBtn.addEventListener('click', handleFileUpload);
    addTaskBtn.addEventListener('click', handleAddTask);
    editProjectBtn.addEventListener('click', handleEditProject);
    closeDetailBtn.addEventListener('click', handleCloseDetail);
    taskList.addEventListener('change', handleTaskStatusChange);

    // 파일 업로드 처리
    function handleFileUpload(event) {
        event.preventDefault();
        if (!fileInput.files.length) {
            alert('파일을 선택해주세요.');
            return;
        }
        const formData = new FormData();
        formData.append('file', fileInput.files[0]);
        formData.append('projectId', projectId);

        fetch('/api/projects/upload-file', {
            method: 'POST',
            body: formData
        })
        .then(response => response.json())
        .then(data => {
            console.log('File uploaded:', data);
            // 파일 목록 업데이트 로직 추가
        })
        .catch(error => console.error('Error:', error));
    }

    // 새 작업 추가 처리
    function handleAddTask(event) {
        event.preventDefault();
        const taskDescription = newTaskInput.value.trim();
        if (taskDescription) {
            addNewTask(taskDescription);
        }
    }

    // 새 작업을 서버에 추가하고 화면에 표시
    function addNewTask(description) {
        fetch(`/api/projects/${projectId}/tasks`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ description: description })
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(task => {
            const li = createTaskElement(task);
            taskList.appendChild(li);
            newTaskInput.value = '';
        })
        .catch(error => {
            console.error('Error:', error);
            alert('작업 추가에 실패했습니다.');
        });
    }

    // 작업 요소 생성
    function createTaskElement(task) {
        const li = document.createElement('li');
        li.innerHTML = `
            <input type="checkbox" id="task${task.id}" ${task.completed ? 'checked' : ''}>
            <label for="task${task.id}">${task.description}</label>
        `;
        return li;
    }

    // 프로젝트 수정 처리
    function handleEditProject() {
        window.location.href = `/project/edit/${projectId}`;
    }

    // 상세 페이지 닫기 처리
    function handleCloseDetail() {
        window.history.back();
    }

    // 작업 상태 변경 (체크박스 클릭 시)
    function handleTaskStatusChange(event) {
        if (event.target.type === 'checkbox') {
            const taskId = event.target.id.replace('task', '');
            const completed = event.target.checked;
            updateTaskStatus(taskId, completed);
        }
    }

    // 작업 상태 업데이트
    function updateTaskStatus(taskId, completed) {
        fetch(`/api/tasks/${taskId}`, {
            method: 'PATCH',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ completed: completed })
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => console.log('Task updated:', data))
        .catch(error => {
            console.error('Error:', error);
            alert('작업 상태 업데이트에 실패했습니다.');
        });
    }
});

