<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: chris
  Date: 13/06/20
  Time: 3:47 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Article</title>
</head>
<body>
<h2>Add new Article</h2>
<form action="/addnewarticle" method="post" class="addArticle">

    <div>
        <label for="title_AddArticle">Title: </label>
        <input type="text" name="title_AddArticle" id="title_AddArticle" required>
    </div>
    <div>
        <label for="content_AddArticle">Content:</label>
        <textarea type="text" name="content" id="content_AddArticle" rows="20" cols="80">
        </textarea>
        <input hidden type="text" name="authorId_AddArticle" id="authorId_AddArticle" required value="${authorId}">
    </div>
    <div>
        <input type="submit" value="addArticle">
    </div>
</form>



<c:forEach items="${a}" var="a">
    <div class="articles">
        <h1 class="title">Title:${a.title}</h1>
        Create Date:<p class="date">${a.dateCreated}</p>
        Article Id: <p class="date" id = "${a.id}" name = "id">${a.id}</p>
        authorId :<p class="authorId">${a.authorId}</p>
        content:<p class="content">${a.content}</p>
        <form action="/editArticle">
        <input type="hidden" name="session" value="${a.id}">
        <input type="submit" value="Update">
        </form>
        <form action="/delete">
            <input type="hidden" name="articleId" value="${a.id}">
            <input type="submit" value="Delete">
        </form>
    </div>
</c:forEach>




</body>
</html>
