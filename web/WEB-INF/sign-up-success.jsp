<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>More User's Information</title>
    <jsp:include page="bootstrap.jsp" />
    <link href="<c:url value="/css/base.css"/>" rel="stylesheet">
    <link href="<c:url value="/css/blog.css"/>" rel="stylesheet">
</head>
<body>

<jsp:include page="nav.jsp">
    <jsp:param name="user" value="${user}"/>
</jsp:include>
<div class="container homeArticle editArticle card-body container">
<p>Welcome ${user.username}! Below is a list of information to let others know more about you. </p>

<button class="btn btn-danger my-2" onclick="location.href='<c:url value="/home"/>'">Skip</button>

<form action="" method="post">
    <div>
        <label for="fname">First Name: </label>
        <input type="text" name="fname" id="fname" required>
    </div>
    <div>
        <label for="lname">Last Name: </label>
        <input type="text" name="lname" id="lname" required>
    </div>
    <div>
        <label for="date_birth">Date of Birth: </label>
        <input type="date" name="dateBirth" id="date_birth" required>
    </div>
    <div>
        <label for="description">Introduce Yourself: </label>
        <textarea name="description" class="form-control form-control-dark w-100" id="description" cols="80" rows="20" required></textarea>
    </div>
    <div>
        <input type="submit" class=" btn btn-primary" value="Confirm">
    </div>
</form>
</div>
<br><br><br>
</body>
</html>
