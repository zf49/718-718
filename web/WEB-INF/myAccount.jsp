<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>My Account</title>
</head>
<body>

<jsp:include page="nav.jsp" />
<form action="./account/update?userId=${user.id}" method="post" class="form-example">
    <div>
        <label for="username">Username: </label>
        <input type="text" name="username" id="username" required value="${user.username}">
    </div>
    <div>
        <label for="password">Password: </label>
        <input type="password" name="password" id="password" required>
    </div>
    <div>
        <label for="confirmPassword">Confirm Password: </label>
        <input type="password" name="confirmPassword" id="confirmPassword" required>
    </div>
    <div>
        <input type="submit" value="Update">
    </div>
</form>
<form action="./account/delete?userId=${user.id}">
    <input type="submit" value="Delete Account">
</form>
</body>
</html>
