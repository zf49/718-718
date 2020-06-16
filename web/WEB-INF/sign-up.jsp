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
<form action="${pageContext.request.contextPath}/sign-up" method="post" class="form-example">
    <div>
        <label for="username">Username: </label>
        <input type="text" name="username" id="username" required autocomplete="on">
    </div>
    <div>
        <label for="password">Password: </label>
        <input type="password" name="password" id="password" required autocomplete="on">
    </div>
    <div>
        <label for="confirmPassword">Confirm Password: </label>
        <input type="password" name="confirmPassword" id="confirmPassword" required autocomplete="on">
    </div>
    <div>
        <input type="submit" value="Sign Up">
    </div>
</form>
</body>
</html>