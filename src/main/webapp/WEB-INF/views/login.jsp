<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link type="text/css" rel="stylesheet" href="<c:url value='/css/login.css' />">
</head>
<body>
    <div class="login-container">
        <h2>Login</h2>
        <form action="<c:url value='/login'/>" method="post">
            <div class="form-input">
                <label for="username">Username:</label>
                <input type="text" id="username" name="username" required>
            </div>
            <div class="form-input">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required>
            </div>
            <button type="submit" class="login-button">Log In</button>
        </form>
    </div>
</body>
</html>
