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
<%--<h2>Add new Article</h2>
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
</form>--%>
<jsp:include page="nav.jsp" />
<c:forEach items="${a}" var="article">
    <div class="articles">
        <h1 class="title">Title:${article.title}</h1>
<%--        TODO: format the date--%>
        <p class="date">Create Date: ${article.dateCreated}</p>
        <p class="date" id="${article.id}" name="id"> Article Id: ${article.id}</p>

<%--        TODO: load author name instead of id--%>

        <p class="authorId">authorId: ${article.authorId}</p>
<%--        TODO: split into paragraphs--%>
        <p class="content">content:${article.content}</p>
        <span>
            <a href="<c:url value="${pageContext.request.contextPath}/edit/articleId?articleId=${article.id}"/>">Edit</a>
        </span>
        <span>
<%--            TODO: use post or delete method --%>
            <a href="<c:url value="${pageContext.request.contextPath}/articles/${article.id}/delete/articleId?articleId=${article.id}"/>">Delete</a>
        </span>
<%--        TODO: add link to article details--%>
    </div>
</c:forEach>

</body>
</html>
