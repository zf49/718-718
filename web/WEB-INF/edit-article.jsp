<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<html lang="en">
<head>
    <jsp:include page="bootstrap.jsp"/>
    <meta charset="UTF-8">
    <title>Edit Article</title>
</head>
<body>
<jsp:include page="nav.jsp"/>
<br><br><br>
<div class="editArticle card-body container about-author center-text homeArticle">
    <form action="" method="post" class="editArticle">
        <div>
            <p class = "blog-post-title"><label for="titleArea">Title: </label></p>
            <input type="text" class="form-control form-control-dark w-100" name="articleTitle" id="titleArea"
                   required value="${article.title}">
            <input hidden name="authorId" value="${article.authorId}">
        </div>
        <br>
        <div>
            <p class = "blog-post-title"><label for="contentArea">Content:</label></p>
            <textarea name="articleContent" class="form-control form-control-dark w-100" id="contentArea"
                      rows="20" cols="80">${article.content}</textarea>
        </div>
        <br>
        <input type="submit" class="btn btn-primary" value="Confirm">
    </form>
</div>
</body>
</html>
