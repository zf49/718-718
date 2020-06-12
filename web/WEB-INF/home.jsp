<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
</head>
<body>

<c:choose>
    <c:when test="${user == null}">
        <p>You are not signed in.</p>
        <form action="./sign-in" method="get">
            <input type="submit" value="Sign In">
        </form>
    </c:when>
    <c:otherwise>
        <p>Hi, ${user.username}</p>
        <form action="./sign-out" method="post">
            <input type="submit" value="Sign Out">
        </form>
    </c:otherwise>
</c:choose>

</body>
</html>
