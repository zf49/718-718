<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Sign Up</title>
</head>
<body>
<jsp:include page="nav.jsp" />
<form action="./sign-up" method="post" class="form-example">
    <div>
        <label for="username">Username: </label>
        <input type="text" name="username" id="username" required>
    </div>
    <div>
        <label for="password">Password: </label>
        <input type="password" name="password" id="password" required>
    </div>
    <div>
        <label for="confirmPassword">Password: </label>
        <input type="password" name="confirmPassword" id="confirmPassword" required>
    </div>
    <div>
        <input type="submit" value="Sign Up">
    </div>
</form>
</body>
</html>