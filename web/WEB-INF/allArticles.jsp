<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Articles</title>
</head>
<body>

<jsp:include page="nav.jsp">
    <jsp:param name="user" value="${user}"/>
</jsp:include>

<a href="/userArticles">User Articles</a><br>

      <h1>all articles</h1>
      <c:forEach items="${a}" var="article">
          <div class="articles">
              <h3 class="title">Title:${article.title}</h3>
              <p class="date">Create Date: ${article.dateCreated}</p>
              <p class="authorId">authorId: ${article.authorId}</p>
              <p class="content">content: ${article.content}</p>

              <c:if test="${article.authorId == user.id}">
                  <span>
                      <a href="<c:url value="/edit/articleId?articleId=${article.id}"/>">Edit</a>
                  </span>
                  <span>
                      <a href="<c:url value="/articles/${article.id}/delete/articleId?articleId=${article.id}"/>">Delete</a>
                  </span>
              </c:if>
          </div>
      </c:forEach>

</body>
</html>
