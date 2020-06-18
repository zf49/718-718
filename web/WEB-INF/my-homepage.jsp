<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <jsp:include page="bootstrap.jsp" />
    <link href="<c:url value="/css/blog.css"/>" rel="stylesheet">
    <title>${pageUser.username}'s Homepage</title>
</head>
<body>
<% pageContext.setAttribute("newLineChar", "\n"); %>
<jsp:include page="nav.jsp" />
<br>
<br>
<div class="container about-author center-text homeArticle">
<c:forEach items="${a}" var="article">
    <div class="articles card-body">
        <h1 class="title">Title:${article.title}</h1>
        <p class="blog-post-meta date">Create Date: ${article.date}</p>
        <p class="blog-post-meta authorId">Author Name: ${pageUser.username}</p>
        <p class="content">content:${fn:replace(article.briefContent, newLineChar, "<br>")}</p>
            <span class = "homepageButton">
            <a class="btn btn-sm btn-outline-secondary homepageButton" href="<c:url value="/edit/articleId?articleId=${article.id}"/>">Edit</a>
            </span>
            <span class = "homepageButton">
            <form action="<c:url value="/delete/articleId?articleId=${article.id}"/>" method="post">
            <input type="submit" class="btn btn-sm btn-outline-secondary " value="Delete">
            </form>
            </span>
            <span class = "homepageButton">
             <a class="btn btn-sm btn-outline-secondary" href="<c:url value="/articles/${article.id}"/>">Show Details</a>
            </span>
    </div>
    <hr>
</c:forEach>
</div>
</body>
</html>
