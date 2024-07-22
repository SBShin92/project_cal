document.addEventListener('DOMContentLoaded', function() {
    // 사이드바 토글 기능
    const menuToggle = document.querySelector('.menu-toggle');
    const sidebar = document.getElementById('sidebar');

    if (menuToggle && sidebar) {
        menuToggle.addEventListener('click', function() {
            sidebar.classList.toggle('active');
        });
    }

    // 모든 삭제 폼에 대해 이벤트 리스너 추가
    document.querySelectorAll('.deleteForm').forEach(form => {
        form.addEventListener('submit', function(e) {
            e.preventDefault();
            if (confirm('정말로 삭제하시겠습니까?')) {
                this.submit();
            }
        });
    });

    // 사용자 검색 기능
    const searchBar = document.querySelector('.search-bar');
    if (searchBar) {
        searchBar.addEventListener('input', function() {
            const searchTerm = this.value.toLowerCase();
            const rows = document.querySelectorAll('tbody tr');

            rows.forEach(row => {
                const text = row.textContent.toLowerCase();
                row.style.display = text.includes(searchTerm) ? '' : 'none';
            });
        });
    }

    // 사용자 수정
    document.querySelectorAll('.btn-edit').forEach(button => {
        button.addEventListener('click', function(e) {
            const userId = this.dataset.userId;
            const row = this.closest('tr');
            const editableCells = row.querySelectorAll('.editable');
            const permissionCheckboxes = row.querySelectorAll('.permissions input[type="checkbox"]');

            if (this.textContent === '수정') {
                // 수정 모드로 전환
                editableCells.forEach(cell => {
                    const currentValue = cell.textContent;
                    cell.innerHTML = `<input type="text" value="${currentValue}">`;
                });
                permissionCheckboxes.forEach(checkbox => {
                    checkbox.disabled = false;
                });
                this.textContent = '저장';
            } else {
                // 저장 모드
                const formData = new FormData();
                editableCells.forEach(cell => {
                    const field = cell.dataset.field;
                    const input = cell.querySelector('input');
                    formData.append(field, input.value);
                });
                permissionCheckboxes.forEach(checkbox => {
                    formData.append(checkbox.name, checkbox.checked);
                });

                // 서버로 데이터 전송
                fetch(`/manager/user/update/${userId}`, {
                    method: 'POST',
                    body: formData
                })
                .then(response => response.json())
                .then(data => {
                    if (data.success) {
                        alert(data.message);
                        // 수정된 데이터로 셀 업데이트
                        editableCells.forEach(cell => {
                            const field = cell.dataset.field;
                            cell.textContent = formData.get(field);
                        });
                        permissionCheckboxes.forEach(checkbox => {
                            checkbox.disabled = true;
                        });
                        this.textContent = '수정';
                    } else {
                        alert(data.message);
                    }
                })
                .catch(error => {
                    console.error('Error:', error);
                    alert('오류가 발생했습니다.');
                });
            }
        });
    });
});