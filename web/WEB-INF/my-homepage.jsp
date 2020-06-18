<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <jsp:include page="bootstrap.jsp"/>
    <title>${pageUser.username}'s Homepage</title>
</head>
<body>
<% pageContext.setAttribute("newLineChar", "\n"); %>

<jsp:include page="nav.jsp" />
<br><br><br>
<c:if test="${a == null || fn:length(a) == 0}">
    <div class="container about-author center-text homeArticle" style="padding: 9%">
        <p class="container blog-post-title" style="text-align: center">You need to write something!</p>
        <br><br>
        <a class="btn btn-lg btn-primary btn-block" href="<c:url value="/new-article"/>">Write Article</a><br>
    </div>
</c:if>
<c:forEach items="${a}" var="article">
    <div class="container about-author center-text homeArticle">
        <div class="articles card-body">
            <p class="blog-post-title">Title:${article.title}</p>
            <p class="blog-post-meta date">Create Date: ${article.date}</p>
            <p class="blog-post-meta authorId">Author Name: ${pageUser.username}</p>
            <p class="content text-truncate">${fn:replace(article.briefContent, newLineChar, "<br>")}</p>
            <div style="display: inline-block">
                <a class="btn btn-primary" href="<c:url value="/edit/articleId?articleId=${article.id}"/>">Edit</a>
                <a class="btn btn-primary" href="<c:url value="/articles/${article.id}"/>">Show Details</a>
            </div>
            <div style="display: inline-block">
                <form action="<c:url value="/delete/articleId?articleId=${article.id}"/>" method="post">
                    <input type="submit" class="btn btn-danger my-2" value="Delete">
                </form>
            </div>
        </div>
        <hr>
    </div>
</c:forEach>
</body>
</html>
