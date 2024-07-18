document.addEventListener('DOMContentLoaded', function() {
    // 사이드바 토글 기능
    const menuToggle = document.querySelector('.menu-toggle');
    const sidebar = document.getElementById('sidebar');
    
    if (menuToggle && sidebar) {
        menuToggle.addEventListener('click', function() {
            sidebar.classList.toggle('active');
        });
    }

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

    // 사용자 생성 폼 제출
    const createForm = document.getElementById('createRoleForm');
    if (createForm) {
        createForm.addEventListener('submit', function(e) {
            e.preventDefault();
            const formData = new FormData(this);
            
            fetch('/manager/users/create', {
                method: 'POST',
                body: formData
            })
            .then(response => response.json())
            .then(data => {
                if (data.success) {
                    alert('사용자 생성 성공');
                    window.location.href = '/manager/users';
                } else {
                    alert('사용자 생성 실패: ' + data.message);
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert('오류가 발생했습니다.');
            });
        });
    }

    // 사용자 수정 폼 제출
    const editForm = document.getElementById('editRoleForm');
    if (editForm) {
        editForm.addEventListener('submit', function(e) {
            e.preventDefault();
            const formData = new FormData(this);
            const roleId = this.dataset.roleId;
            
            fetch(`/manager/users/edit/${roleId}`, {
                method: 'POST',
                body: formData
            })
            .then(response => response.json())
            .then(data => {
                if (data.success) {
                    alert('사용자 수정 성공');
                    window.location.href = '/manager/users';
                } else {
                    alert('사용자 수정 실패: ' + data.message);
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert('오류가 발생했습니다.');
            });
        });
    }

    // 사용자 삭제 기능
    const deleteButtons = document.querySelectorAll('.delete-role');
    deleteButtons.forEach(button => {
        button.addEventListener('click', function(e) {
            e.preventDefault();
            if (confirm('정말로 이 사용자를 삭제하시겠습니까?')) {
                const roleId = this.dataset.roleId;
                
                fetch(`/manager/users/delete/${roleId}`, {
                    method: 'POST'
                })
                .then(response => response.json())
                .then(data => {
                    if (data.success) {
                        alert('사용자 삭제 성공');
                        this.closest('tr').remove();
                    } else {
                        alert('사용자 삭제 실패: ' + data.message);
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