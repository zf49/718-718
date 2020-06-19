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
<c:if test="${articles == null || fn:length(articles) == 0}">
    <div class="container about-author center-text homeArticle" style="padding: 9%">
        <p class="container blog-post-title" style="text-align: center">There's nothing here!</p>
        <br><br>
    <c:if test="${user != null && user.id == pageUser.id}">
        <a class="btn btn-info btn-primary btn-block" href="<c:url value="/new-article"/>">Write Article</a><br>
    </c:if>
    </div>
</c:if>
<c:forEach items="${articles}" var="article">
    <div class="container about-author center-text homeArticle">
        <div class="articles card-body">
            <p class="blog-post-title text-truncate">${article.title}</p>
            <p class="blog-post-meta date">Create Date: ${article.date}</p>
            <p class="blog-post-meta authorId">By ${pageUser.username}</p>
            <p class="content text-truncate">${fn:replace(article.briefContent, newLineChar, "<br>")}</p>
            <div style="display: inline-block">
                <a class="btn btn-outline-info" href="<c:url value="/articles/${article.id}"/>">Show Details</a>
                <c:if test="${user != null && user.id == pageUser.id}">
                <a class="btn btn-outline-info" href="<c:url value="/edit/articleId?articleId=${article.id}"/>">Edit</a>
            </div>
            <div style="display: inline-block">
                <form action="<c:url value="/delete/articleId?articleId=${article.id}"/>" method="post">
                    <input type="submit" class="btn btn-outline-danger my-2" value="Delete">
                </form>
                </c:if>
            </div>
        </div>
        <hr>
    </div>
</c:forEach>
</body>
</html>
