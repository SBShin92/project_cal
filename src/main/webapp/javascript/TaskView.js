



        function deleteTask(taskId) {
            if (confirm('Are you sure you want to delete this task?')) {
                var form = document.createElement('form');
                form.method = 'POST';
                form.action = '/tasks/' + taskId;
                
                var methodInput = document.createElement('input');
                methodInput.type = 'hidden';
                methodInput.name = '_method';
                methodInput.value = 'DELETE';
                form.appendChild(methodInput);
                
                document.body.appendChild(form);
                form.submit();
            }
        }

        function addMember(taskId) {
            var userId = document.getElementById('userId').value;
            var form = document.getElementById('addMemberForm');
            form.action = '/tasks/' + taskId + '/members';
            form.submit();
        }
