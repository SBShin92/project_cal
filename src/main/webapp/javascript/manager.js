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
	
	// 사용자 수정
// 수정 버튼에 대한 이벤트 리스너 추가
    document.querySelectorAll('.btn-edit').forEach(button => {
        button.addEventListener('click', function(e) {
            const userId = this.dataset.userId;
            const row = this.closest('tr');
            const editableCells = row.querySelectorAll('.editable');
            
            if (this.textContent === '수정') {
                // 수정 모드로 전환
                editableCells.forEach(cell => {
                    const currentValue = cell.textContent;
                    cell.innerHTML = `<input type="text" value="${currentValue}">`;
                });
                this.textContent = '저장';
            } else {
                // 저장 모드
                const updatedData = {};
                editableCells.forEach(cell => {
                    const field = cell.dataset.field;
                    const input = cell.querySelector('input');
                    updatedData[field] = input.value;
                });

                // 서버로 데이터 전송
                fetch(`/manager/users/edit/${userId}`, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded',
                    },
                    body: `name=${encodeURIComponent(updatedData.name)}&email=${encodeURIComponent(updatedData.email)}&position=${encodeURIComponent(updatedData.position)}`
                })
                .then(response => response.json())
                .then(data => {
                    if (data.success) {
                        alert(data.message);
                        // 수정된 데이터로 셀 업데이트
                        editableCells.forEach(cell => {
                            const field = cell.dataset.field;
                            cell.textContent = updatedData[field];
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