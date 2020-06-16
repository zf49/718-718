<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Sign Up</title>
    <jsp:include page="bootstrap.jsp" />
    <link rel="stylesheet" href="<c:url value="/css/signin.css" />">
</head>
<body>
<jsp:include page="nav.jsp" />
<form class="form-signin" action="${pageContext.request.contextPath}/sign-up" method="post" class="form-example">
    <h1 class="h3 mb-3 font-weight-normal">Sign Up</h1>
    <div class="form-group">
        <label for="username" class="sr-only">Username: </label>
        <input class="form-control" type="text" name="username" id="username" placeholder="Username" required autocomplete="on">
    </div>
    <div class="form-group">
        <label for="password" class="sr-only">Password: </label>
        <input class="form-control" type="password" name="password" id="password" placeholder="Password" required autocomplete="on">
    </div>
    <div class="form-group">
        <label for="confirmPassword" class="sr-only">Confirm Password: </label>
        <input class="form-control" type="password" name="confirmPassword" id="confirmPassword" placeholder="Confirm Password" required autocomplete="on">
    </div>
    <div>
        <input class="btn btn-lg btn-primary btn-block" type="submit" value="Sign Up">
    </div>
</form>
</body>
</html>