<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>More User's Information</title>
    <jsp:include page="bootstrap.jsp"/>

</head>
<body>
<jsp:include page="nav.jsp">
    <jsp:param name="user" value="${user}"/>
</jsp:include>
<br><br><br>
<div class="container homeArticle editArticle card-body container">
    <p class="blog-post-title" style="color: black">Welcome ${user.username}! Below is a list of information to let others know more about you. </p>

    <button class="btn btn-primary" onclick="location.href='<c:url value="/home"/>'">Skip
    </button>
    <form action="" method="post">
        <div class="row">
        <div class="col-md-6 mb-3">
            <label for="fname">First Name: </label>
            <input type="text" name="fname" id="fname" class="form-control" required>
        </div>
        <div class="col-md-6 mb-3">
            <label for="lname">Last Name: </label>
            <input type="text" name="lname" id="lname" class="form-control" required>
        </div></div>
        <div class="mb-3">
            <label for="date_birth">Date of Birth: </label>
            <input type="date" name="dateBirth" id="date_birth" class="form-control" required>
        </div>
        <div class="mb-3">
            <label for="description">Introduce Yourself: </label>
            <textarea name="description" class="form-control form-control-dark w-100" id="description" cols="80"
                      rows="5" required></textarea>
        </div>
        <div class="mb-4">
            <input type="submit" class=" btn btn-danger my-2" value="Confirm">
        </div>
    </form>
</div>
<br><br><br>
</body>
</html>
