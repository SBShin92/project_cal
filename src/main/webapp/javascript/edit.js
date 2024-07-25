document.addEventListener('DOMContentLoaded', function() {
    const editButton = document.getElementById('editButton');
    const cancelButton = document.getElementById('cancelButton');
    const editForm = document.getElementById('projectEditForm');
    const viewMode = document.getElementById('projectViewMode');
    const editor = document.getElementById('editor');
    const projectDescriptionInput = document.getElementById('projectDescription');

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

        // 에디터 내용을 hidden input에 복사
        if (editor && projectDescriptionInput) {
            projectDescriptionInput.value = editor.innerHTML;
            console.log("Editor content saved: ", projectDescriptionInput.value);
        }

        if (!isValid) {
            event.preventDefault();
            alert('모든 필수 항목을 올바르게 입력해주세요.');
        }
    });

    // 툴바 기능 구현
    
    const toolbar = document.querySelector('.btn-toolbar');
    if (toolbar && editor) {
        toolbar.addEventListener('click', function(e) {
            const button = e.target.closest('button');
            if (button) {
                e.preventDefault();
                const command = button.dataset.command;
                if (command === 'insertImage') {s
                    const url = prompt('Enter the image URL:');
                    if (url) document.execCommand(command, false, url);
                } else {
                    document.execCommand(command, false, null);
                }
            }
        });
    }
});