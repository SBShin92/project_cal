document.addEventListener("DOMContentLoaded", function() {
    var modal = document.getElementById("inviteMemberModal");
    var btn = document.getElementById("inviteMemberButton");
    var span = document.getElementsByClassName("close")[0];
    var memberList = document.getElementById("memberList");

    btn.onclick = function() {
        modal.style.display = "block";
        fetchMembers();
    }

    span.onclick = function() {
        modal.style.display = "none";
    }

    window.onclick = function(event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }

    document.getElementById("addSelectedMembers").onclick = function() {
        addSelectedMembers();
    }

    function fetchMembers() {
        fetch('/members/list')
            .then(response => response.json())
            .then(data => {
                memberList.innerHTML = "";
                data.forEach(member => {
                    let memberItem = document.createElement("div");
                    memberItem.innerHTML = `
                        <input type="checkbox" name="member" value="${member.userId}"> ${member.userName} (${member.userEmail})
                    `;
                    memberList.appendChild(memberItem);
                });
            })
            .catch(error => console.error('Error fetching member list:', error));
    }

    function addSelectedMembers() {
        var selectedMembers = document.querySelectorAll('input[name="member"]:checked');
        selectedMembers.forEach(member => {
            let input = document.createElement("input");
            input.type = "hidden";
            input.name = "members[]";
            input.value = member.value;
            document.querySelector("form").appendChild(input);
        });
        modal.style.display = "none";
    }
});
