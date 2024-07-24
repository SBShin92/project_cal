document.addEventListener('DOMContentLoaded', function() {
    toggleSidebar();
    addDeleteFormListener();
    searchUser();
    setupUserEditing();
    loadUserData();
});


// 왼쪽 사이드바(반응형 웹)
function toggleSidebar() {
    const menuToggle = document.querySelector('.menu-toggle');
    const sidebar = document.getElementById('sidebar');

    if (menuToggle && sidebar) {
        menuToggle.addEventListener('click', function() {
            sidebar.classList.toggle('active');
        });
    }
}

// 삭제 버튼 눌렀을 때 alert
function addDeleteFormListener() {
    document.querySelectorAll('.deleteForm').forEach(form => {
        form.addEventListener('submit', function(e) {
            e.preventDefault();
            if (confirm('정말로 삭제하시겠습니까?')) {
                this.submit();
            }
        });
    });
}

// 유저 서치

function searchUser() {
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
}

function setupUserEditing() {
    document.querySelectorAll('.btn-edit').forEach(button => {
        button.addEventListener('click', function(e) {
            const userId = this.dataset.userId;
            const row = this.closest('tr');
            const editableCells = row.querySelectorAll('.editable');
            const permissionCheckboxes = row.querySelectorAll('.permissions input[type="checkbox"]');

            if (this.textContent === '수정') {
                enterEditMode(editableCells, permissionCheckboxes, this);
            } else {
                saveChanges(userId, editableCells, permissionCheckboxes, this);
            }
        });
    });
}




function loadUserData() {
    const userRows = document.querySelectorAll('tbody tr');
    userRows.forEach(row => {
        const userId = row.querySelector('.btn-edit')?.dataset.userId;
        if (!userId) {
            console.warn('User ID not found for row', row);
            return;
        }
        fetch(`/project_cal/manager/user/${userId}`)
            .then(response => {
                if (!response.ok) {
                    throw new Error(`HTTP error! status: ${response.status}`);
                }
                return response.json();
            })
            .then(data => {
                if (data.user && data.role) {
                    updateUserInfo(row, data.user, data.role);
                } else {
                    throw new Error('Invalid data structure');
                }
            })
            .catch(error => {
                console.error('Error:', error);
                row.querySelector('[data-field="name"]').textContent = 'Error loading data';
            });
    });
}
function saveChanges(userId, editableCells, permissionCheckboxes, button) {
    const formData = new FormData();
    formData.append('userId', userId);

    editableCells.forEach(cell => {
        const field = cell.dataset.field;
        const input = cell.querySelector('input');
        formData.append(field, input.value);
    });

    permissionCheckboxes.forEach(checkbox => {
        formData.append(checkbox.name, checkbox.checked);
    });

    const isAdminCheckbox = document.querySelector(`input[name="isAdmin"][data-user-id="${userId}"]`);
    if (isAdminCheckbox) {
        formData.append('isAdmin', isAdminCheckbox.checked);
        // 관리자 체크박스 상태에 따라 authority 설정
        formData.set('authority', isAdminCheckbox.checked ? 'admin' : 'user');
    }

    fetch(`/project_cal/manager/user/edit/${userId}`, {
        method: 'POST',
        body: formData
    })
    .then(response => {
        if (!response.ok) {
            return response.text().then(text => { throw new Error(text || response.statusText); });
        }
        return response.text();
    })
    .then(data => {
        alert('사용자 정보가 성공적으로 업데이트되었습니다.');
        updateCellsAfterSave(editableCells, permissionCheckboxes, formData, button);
        loadUserData();
    })
    .catch(error => {
        console.error('Error:', error);
        alert('오류 발생: ' + error.message);
    });
}

function updateUserInfo(row, user, role) {
    const updateField = (selector, value) => {
        const element = row.querySelector(selector);
        if (element) {
            element.textContent = value;
        } else {
            console.warn(`Element not found: ${selector}`);
        }
    };

    updateField('[data-field="name"]', user.userName);
    updateField('[data-field="email"]', user.userEmail);
    updateField('[data-field="position"]', user.userPosition);
    updateField('[data-field="authority"]', user.userAuthority);
    
    const updateCheckbox = (name, checked) => {
        const checkbox = row.querySelector(`input[name="${name}"]`);
        if (checkbox) {
            checkbox.checked = checked;
        } else {
            console.warn(`Checkbox not found: ${name}`);
        }
    };

    updateCheckbox('projectCreate', role.projectCreate);
    updateCheckbox('projectRead', role.projectRead);
    updateCheckbox('projectUpdate', role.projectUpdate);
    updateCheckbox('projectDelete', role.projectDelete);

    const isAdminCheckbox = row.querySelector('input[name="isAdmin"]');
    if (isAdminCheckbox) {
        isAdminCheckbox.checked = user.userAuthority === 'admin';
    }
}
function enterEditMode(editableCells, permissionCheckboxes, button) {
    editableCells.forEach(cell => {
        const currentValue = cell.textContent;
        cell.innerHTML = `<input type="text" value="${currentValue}">`;
		if (cell.dataset.field == 'authority') {
		    var inputs = cell.getElementsByTagName('input');
		    for (var i = 0; i < inputs.length; i++) {
		        inputs[i].disabled = true;
		    }
		}
    });
    permissionCheckboxes.forEach(checkbox => {
        checkbox.disabled = false;
    });
    button.textContent = '저장';
}

function updateCellsAfterSave(editableCells, permissionCheckboxes, formData, button) {
    editableCells.forEach(cell => {
        const field = cell.dataset.field;
        cell.textContent = formData.get(field);
    });
    permissionCheckboxes.forEach(checkbox => {
        checkbox.disabled = true;
    });
    button.textContent = '수정';
}