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
			// 폼 제출 이벤트가 발생했을 때 처리
			e.preventDefault(); // 기본 제출 동작 방지

			const userId = this.id.split('_')[1]; // 폼 ID에서 사용자 ID 추출
			if (confirm('정말로 삭제하시겠습니까?')) {
				// 삭제 확인 시 해당 사용자의 폼 제출
				this.submit();
			}
			// 사용자가 취소를 선택한 경우 아무 작업도 하지 않음
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