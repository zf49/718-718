<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
<% pageContext.setAttribute("newLineChar", "\n"); %>
<jsp:include page="nav.jsp" />
<c:forEach items="${a}" var="article">
    <div class="articles">
        <h1 class="title">Title:${article.title}</h1>
        <p class="date">Create Date: ${article.date}</p>
        <p class="date" id="${article.id}" name="id"> Article Id: ${article.id}</p>
        <p class="authorId">author Name: ${name}</p>
        <p class="content">content:${fn:replace(article.content, newLineChar, "<br>")}</p>
        <span>
            <a href="<c:url value="${pageContext.request.contextPath}/edit/articleId?articleId=${article.id}"/>">Edit</a>
        </span>
        <form action="${pageContext.request.contextPath}/delete/articleId?articleId=${article.id}" method="post">
            <input type="submit" value="Delete">
        </form>
        <a href="<c:url value="${pageContext.request.contextPath}/articles/${article.id}"/>">Show Details</a>

    </div>
</c:forEach>

</body>
</html>
