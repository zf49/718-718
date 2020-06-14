<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Edit Article</title>
</head>
<body>
<%--<p>${article}</p>--%>
<form action="./editArticle" method="post" class="editArticle">

    <div>
        <label for="title">Title: </label>
        <input type="text" name="title" id="title" required value="${article.title}">
    </div>
    <div>
        <label for="content">Content:</label>
        <textarea type="text" name="content" id="content" rows="20" cols="80">${article.content}
        </textarea>
        <input hidden type="text" name="id" id="id" required value="${article.id}">
        <input hidden type="text" name="authorId" id="authorId" required value="${article.authorId}">
    </div>
    <div>
        <input type="submit" value="updateArticle">
    </div>
</form>


</body>
</html>
