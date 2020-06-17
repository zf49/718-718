<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Home</title>
    <jsp:include page="bootstrap.jsp" />
<%--    <link href="<c:url value="/css/homepage.css"/>" rel="stylesheet">--%>
    <link href="<c:url value="/css/blog.css"/>" rel="stylesheet">
    <link href="<c:url value="/css/base.css"/>" rel="stylesheet">

</head>

<body>

<jsp:include page="nav.jsp">
    <jsp:param name="user" value="${user}"/>
</jsp:include>
    <br><br><br>
<section class="jumbotron text-center welcome container">
    <p class = "blog-post-title">OLD IRON</p>
    <p>
        Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's <br>standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but<br> also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum <br> passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.
    </p>
    <img src="css/pic.jpg" class="container">

</div>
</section>
<div >
<c:forEach items="${a}" var="article">
    <div class="container about-author center-text homeArticle ">
        <p class="title blog-post-title">${article.title}</p>
        <p class="date blog-post-meta">Create Date: ${article.date}  By ${article.authorName}</p>
        <p class="content blog-main"> ${article.content}...</p>
        <span>
        <a class="btn btn-lg btn-primary btn-block" href="<c:url value="/articles/${article.id}"/>">Show Details</a>
        </span>
        <c:if test="${article.authorId == user.id}">
            <span>
                <a class="btn btn-lg btn-primary btn-block homeButton" href="<c:url value="/edit/articleId?articleId=${article.id}"/>">Edit</a>
            </span>
            <span>
                <a class="btn btn-danger my-2 homeButton" style="display: block" href="<c:url value="/articles/${article.id}/delete/articleId?articleId=${article.id}"/>">Delete</a>
            </span>
        </c:if>
        <hr>
    </div>
</c:forEach>
</div>
<footer class="text-muted">
    <div class="container">
        <p class="float-right">
            <a href="#">Back to top</a>
        </p>
    </div>
</footer>
</body>
</html>
