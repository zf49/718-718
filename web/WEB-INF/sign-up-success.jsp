<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>More User's Information</title>
</head>
<body>

<jsp:include page="nav.jsp">
    <jsp:param name="user" value="${user}"/>
</jsp:include>

<p>Welcome ${user.username}! Below is a list of information to let others know more about you. </p>

<button onclick="location.href='<c:url value="/home"/>'">Skip</button>

<form action="" method="post">
    <div>
        <label for="fname">First Name: </label>
        <input type="text" name="fname" id="fname">
    </div>
    <div>
        <label for="lname">Last Name: </label>
        <input type="text" name="lname" id="lname">
    </div>
    <div>
        <label for="date_birth">Date of Birth: </label>
        <input type="date" name="dateBirth" id="date_birth">
    </div>
    <div>
        <label for="description">Introduce Yourself: </label>
        <textarea name="description" id="description" cols="80" rows="20"></textarea>
    </div>
    <div>
        <input type="submit" value="Confirm">
    </div>
</form>

</body>
</html>
