<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>New Article</title>
</head>
<body>

<jsp:include page="nav.jsp">
    <jsp:param name="user" value="${user}"/>
</jsp:include>
<div>
    <form action="" method="post">
        <div>
            <label for="titleArea">Title: </label>
            <input type="text" name="articleTitle" id="titleArea" required>
        </div>
        <div>
            <label for="contentArea">Content:</label>
            <textarea name="articleContent" id="contentArea" rows="20" cols="80"></textarea>
        </div>
        <input hidden type="text" name="authorId" value="${user.id}">
        <input type="submit" value="Confirm">
    </form>
</div>

</body>
</html>
