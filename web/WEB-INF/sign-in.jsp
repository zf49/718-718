<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Sign In</title>
    <jsp:include page="bootstrap.jsp" />
    <link rel="stylesheet" href="<c:url value="/css/sign-in-floating-labels.css" />">
</head>
<body>
<jsp:include page="nav.jsp" />
<form class="form-signin homeArticle" action="<c:url value="/sign-in"/>" method="post">
    <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
    <div class="form-label-group">
        <input class="form-control" type="text" name="username" id="username" placeholder="Username" required autofocus autocomplete="on">
        <label for="username">Username</label>
    </div>
    <div class="form-label-group">
        <input class="form-control" type="password" name="password" id="password" placeholder="Password" required autocomplete="on">
        <label for="password">Password</label>
    </div>
    <div>
        <input class="btn btn-lg btn-primary btn-block" type="submit" value="Sign In">
    </div>
</form>
</body>
</html>