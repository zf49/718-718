<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Sign Up</title>
    <jsp:include page="bootstrap.jsp" />
    <link rel="stylesheet" href="<c:url value="/css/sign-in-floating-labels.css" />">
    <script>const servletContextPath = "${pageContext.request.contextPath}"</script>
    <script type="text/javascript" src="<c:url value="/javascript/sign-up.js" />"></script>
</head>
<body>
<jsp:include page="nav.jsp" />
<form class="form-signin" action="${pageContext.request.contextPath}/sign-up" method="post" class="form-example">
    <h1 class="h3 mb-3 font-weight-normal">Sign Up</h1>
    <div class="form-label-group">
        <input class="form-control" type="text" name="username" id="username" placeholder="Username" required autocomplete="on">
        <label for="username">Username</label>
        <small id="username-taken" class="form-text text-danger d-none">
            Username already taken
        </small>
    </div>
    <div class="form-label-group">
        <input class="form-control" type="password" name="password" id="password" placeholder="Password" required autocomplete="on">
        <label for="password">Password</label>
    </div>
    <div class="form-label-group">
        <input class="form-control" type="password" name="confirmPassword" id="confirmPassword" placeholder="Confirm Password" required autocomplete="on">
        <label for="confirmPassword">Confirm Password</label>
        <small id="password-dont-match" class="form-text text-danger d-none">
            Passwords don't match
        </small>
    </div>
    <div>
        <input class="btn btn-lg btn-primary btn-block" id="submit" type="submit" value="Sign Up" disabled>
    </div>
</form>
</body>
</html>