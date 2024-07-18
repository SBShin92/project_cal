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
    const deleteForms = document.querySelectorAll('.deleteForm');
    deleteForms.forEach(form => {
        form.addEventListener('submit', function(event) {
            event.preventDefault();
            const userId = this.getAttribute('data-user-id');
            const confirmDelete = confirm(`정말로 사용자 ID ${userId}를 삭제하시겠습니까?`);
            if (confirmDelete) {
                fetch(this.action, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded',
                    },
                })
                .then(response => response.json())
                .then(data => {
                    if (data.success) {
                        alert('사용자가 성공적으로 삭제되었습니다.');
                        // 삭제된 행을 DOM에서 제거
                        this.closest('tr').remove();
                    } else {
                        alert('사용자 삭제 실패: ' + data.message);
                    }
                })
                .catch(error => {
                    console.error('Error:', error);
                    alert('삭제 중 오류가 발생했습니다.');
                });
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


});