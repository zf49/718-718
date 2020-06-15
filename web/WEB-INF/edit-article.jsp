<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit Article</title>
</head>
<body>

<jsp:include page="nav.jsp" />

<div>
    <form action="" method="post" class="editArticle">
        <div>
            <label for="titleArea">Title: </label>
            <input type="text" name="articleTitle" id="titleArea" required value="${article.title}">
        </div>
        <div>
            <label for="contentArea">Content:</label>
            <textarea name="articleContent" id="contentArea" rows="20" cols="80">${article.content}</textarea>
        </div>
        <input type="submit" value="Confirm">
    </form>
</div>

</body>
</html>
