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



// 페이지네이션 기능
function changePage(page) {
    // AJAX를 사용하여 새로운 페이지 데이터를 가져오는 로직
    fetch(`/manager/users?page=${page}`)
        .then(response => response.json())
        .then(data => {
            // 테이블 내용 업데이트
            updateTable(data.users);
            // 페이지네이션 업데이트
            updatePagination(data.currentPage, data.totalPages);
        })
        .catch(error => console.error('Error:', error));
}

function updateTable(users) {
    const tbody = document.querySelector('table tbody');
    tbody.innerHTML = '';
    users.forEach(user => {
        tbody.innerHTML += `
            <tr>
                <td>${user.userId}</td>
                <td>${user.userName}</td>
                <td>${user.userEmail}</td>
                <td>${user.userAuthority}</td>
                <td>${user.userPosition}</td>
                <td>
                    <button onclick="editUser(${user.userId})">수정</button>
                    <button onclick="deleteUser(${user.userId})">삭제</button>
                </td>
            </tr>
        `;
    });
}

function updatePagination(currentPage, totalPages) {
    const pagination = document.querySelector('.pagination');
    pagination.innerHTML = '';
    for (let i = 1; i <= totalPages; i++) {
        pagination.innerHTML += `
            <a href="#" onclick="changePage(${i})" ${i === currentPage ? 'class="active"' : ''}>${i}</a>
        `;
    }
}

// 사용자 수정 함수
function editUser(userId) {
    // 사용자 정보를 가져와 모달에 채우는 로직
    fetch(`/manager/users/${userId}`)
        .then(response => response.json())
        .then(user => {
            document.getElementById('editUserId').value = user.userId;
            document.getElementById('editUserName').value = user.userName;
            document.getElementById('editUserEmail').value = user.userEmail;
            document.getElementById('editUserAuthority').value = user.userAuthority;
            document.getElementById('editUserPosition').value = user.userPosition;
            openModal('editUserModal');
        })
        .catch(error => console.error('Error:', error));
}

// 사용자 삭제 함수
function deleteUser(userId) {
    if (confirm('정말로 이 사용자를 삭제하시겠습니까?')) {
        fetch(`/manager/users/delete/${userId}`, { method: 'POST' })
            .then(response => response.json())
            .then(data => {
                if (data.success) {
                    alert('사용자가 성공적으로 삭제되었습니다.');
                    // 현재 페이지 새로고침
                    location.reload();
                } else {
                    alert('사용자 삭제에 실패했습니다: ' + data.message);
                }
            })
            .catch(error => console.error('Error:', error));
    }
}