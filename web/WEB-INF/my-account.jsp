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

<form action="" method="post">
    <fieldset>
        <legend>Basic Information</legend>
        <div>
            <label for="username">Username: </label>
            <input type="text" name="username" id="username" required value="${user.username}">
        </div>
        <div>
            <label for="password">New Password: </label>
            <input type="password" name="password" id="password" required>
        </div>
        <div>
            <label for="confirmPassword">Confirm Password: </label>
            <input type="password" name="confirmPassword" id="confirmPassword" required>
        </div>
    </fieldset>
    <fieldset>
        <legend>Additional Information</legend>
        <div>
            <label for="fname">First Name: </label>
            <input type="text" name="fname" id="fname" value="${user.fname}">
        </div>
        <div>
            <label for="lname">Last Name: </label>
            <input type="text" name="lname" id="lname" value="${user.lname}">
        </div>
        <div>
            <label for="date_birth">Date of Birth: </label>
            <input type="date" name="dateBirth" id="date_birth" value="${user.dateBirth}">
        </div>
        <div>
            <label for="description">Description: </label>
            <textarea name="description" id="description" cols="80" rows="20">${user.description}</textarea>
        </div>
    </fieldset>
    <div>
        <input type="submit" value="Update (unfinished)">
    </div>
</form>

<form action="${pageContext.request.contextPath}/delete/userId?userId=${user.id}" method="post">
    <input type="submit" value="Delete Account">
</form>

</body>
</html>
