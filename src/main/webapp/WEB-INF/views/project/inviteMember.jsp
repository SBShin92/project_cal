<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Invite Member to Project</title>
</head>
<body>
    <h1>Invite Member to Project</h1>
    <table>
        <thead>
            <tr>
                <th>User ID</th>
                <th>User Name</th>
                <th>User Email</th>
                <th>User Position</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="user" items="${allUsers}">
                <tr>
                    <td>${user.userId}</td>
                    <td>${user.userName}</td>
                    <td>${user.userEmail}</td>
                    <td>${user.userPosition}</td>
                    <td>
                        <form action="/inviteMember/add" method="post" style="display:inline;">
                            <input type="hidden" name="userId" value="${user.userId}" />
                            <input type="hidden" name="projectId" value="${projectId}" />
                            <input type="submit" value="Add" />
                        </form>
                        <form action="/inviteMember/remove" method="post" style="display:inline;">
                            <input type="hidden" name="userId" value="${user.userId}" />
                            <input type="hidden" name="projectId" value="${projectId}" />
                            <input type="submit" value="Remove" />
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
<<<<<<< HEAD
</html>
=======
</html>
>>>>>>> refs/remotes/origin/develop
