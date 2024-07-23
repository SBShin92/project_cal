document.addEventListener('DOMContentLoaded', function() {
   toggleSidebar();
   addDeleteFormListener();
   searchUser();
   updateUser();
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

// '유저' 수정 버튼
function updateUser() {
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

function enterEditMode(editableCells, permissionCheckboxes, button) {
   editableCells.forEach(cell => {
      const currentValue = cell.textContent;
      cell.innerHTML = `<input type="text" value="${currentValue}">`;
   });
   permissionCheckboxes.forEach(checkbox => {
      checkbox.disabled = false;
   });
   button.textContent = '저장';
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
    })
    .catch(error => {
        console.error('Error:', error);
        alert('오류 발생: ' + error.message);
    });
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