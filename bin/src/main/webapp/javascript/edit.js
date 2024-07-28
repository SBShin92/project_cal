
//프로젝트 상세페이지 수정
document.addEventListener('DOMContentLoaded', function() {
    const editButton = document.getElementById('editButton');
    const cancelButton = document.getElementById('cancelButton');
    const editForm = document.getElementById('projectEditForm');
    const viewMode = document.getElementById('projectViewMode');

    editButton.addEventListener('click', function(e) {
        e.preventDefault();
        viewMode.style.display = 'none';
        editForm.style.display = 'block';
    });

    cancelButton.addEventListener('click', function() {
        editForm.style.display = 'none';
        viewMode.style.display = 'block';
    });

    editForm.addEventListener('submit', function(event) {
        let isValid = true;
        
        // 필수 필드 검사
        const requiredFields = editForm.querySelectorAll('[required]');
        requiredFields.forEach(field => {
            if (field.value.trim() === '') {
                isValid = false;
                field.classList.add('is-invalid');
            } else {
                field.classList.remove('is-invalid');
            }
        });

        // 날짜 유효성 검사
        const startDate = new Date(editForm.querySelector('[name="startDate"]').value);
        const endDate = new Date(editForm.querySelector('[name="endDate"]').value);
        if (endDate < startDate) {
            isValid = false;
            alert('종료일은 시작일보다 늦어야 합니다.');
        }

        if (!isValid) {
            event.preventDefault();
            alert('모든 필수 항목을 올바르게 입력해주세요.');
        }
    });
});

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