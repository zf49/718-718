<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Home</title>
</head>
<body>

<jsp:include page="nav.jsp" />

<p>TODO: articles here</p>
<a href="./articles">All Articles</a><br>
<p><a href="<c:url value="/articles/2"/>">Article 2</a></p>

</body>
</html>
