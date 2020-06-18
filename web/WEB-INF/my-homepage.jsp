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

<c:if test="${a == null || fn:length(a) == 0}">
<div class="container about-author center-text homeArticle" style="padding: 9%">
    <h1 class="container" style="text-align: center">You need to write something!</h1>
</div>
</c:if>
<c:forEach items="${a}" var="article">
    <div class="container about-author center-text homeArticle">
    <div class="articles card-body">
        <h1 class="title">Title:${article.title}</h1>
        <p class="blog-post-meta date">Create Date: ${article.date}</p>
        <p class="blog-post-meta authorId">Author Name: ${pageUser.username}</p>
        <p class="content">content:${fn:replace(article.content, newLineChar, "<br>")}</p>
        <div style="display: inline-block">
            <a class="btn btn-primary" href="<c:url value="/edit/articleId?articleId=${article.id}"/>">Edit</a>
        <a class="btn btn-primary" href="<c:url value="/articles/${article.id}"/>">Show Details</a>
        </div>
           <div style="display: inline-block"><form action="<c:url value="/delete/articleId?articleId=${article.id}"/>" method="post">
            <input type="submit" class="btn btn-danger my-2" value="Delete">
            </form>
           </div>
        </div>
    <hr>
    </div>
</c:forEach>
</body>
</html>
