<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Home</title>
    <jsp:include page="bootstrap.jsp" />
    <link href="<c:url value="/css/homepage.css"/>" rel="stylesheet">
    <link href="<c:url value="/css/responsive.css"/>" rel="stylesheet">
    <link href="<c:url value="/css/blog.css"/>" rel="stylesheet">
    <link href="<c:url value="/css/base.css"/>" rel="stylesheet">
</head>
<body>

<jsp:include page="nav.jsp">
    <jsp:param name="user" value="${user}"/>
</jsp:include>
    <br><br><br>
<section class="jumbotron text-center welcome container">
    <p class = "blog-post-title card-title">OLD IRON</p>
    <p>
        Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's <br>standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but<br> also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum <br> passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.
    </p>
    <img src="css/teamPic.png">
    <div class="container">
        <button class=" btn btn-2 discover-btn homeButton" type="button" data-toggle="collapse" data-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample">
    Show All Blog Articles
</button>
</div>
</section>
<div class="container collapse" id="collapseExample">
<c:forEach items="${a}" var="article">
    <div class="about-author center-text homeArticle">
        <br>
        <p class="title blog-post-title">${article.title}</p>
        <p class="date blog-post-meta">Create Date: ${article.date}  By ${article.authorName}</p>
        <p class="content blog-main"> ${article.content}...</p>
        <a class="read-more-link showDetails" href="<c:url value="/articles/${article.id}"/>">Show Details</a>
        <c:if test="${article.authorId == user.id}">
            <p>
                <a class="read-more-link showDetails" href="<c:url value="/edit/articleId?articleId=${article.id}"/>">Edit</a>
            </p>
            <p>
                <a class="read-more-link showDetails" href="<c:url value="/articles/${article.id}/delete/articleId?articleId=${article.id}"/>">Delete</a>
            </p>
        </c:if>
    </div>
</c:forEach>
</div>
</body>
</html>
