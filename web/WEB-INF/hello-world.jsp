<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Hello, world!</title>
</head>
<body>

<%-- TODO This is a test JSP, and should be deleted from the project. --%>

<h1>${message}</h1>
<h2>Chris changes, 666</h2>

<p><img src="<c:url value="${pageContext.request.contextPath}/images/${image}"/>"></p>

</body>
</html>