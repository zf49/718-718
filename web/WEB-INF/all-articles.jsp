<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Articles</title>
</head>
<body>

<jsp:include page="nav.jsp">
    <jsp:param name="user" value="${user}"/>
</jsp:include>
      <h1>all articles</h1>
      <c:forEach items="${a}" var="article">
          <div class="articles">
              <h3 class="title">Title:${article.title}</h3>
              <p class="date">Create Date: ${article.date}</p>
              <p class="authorId">author Name: ${article.authorName}</p>
              <p class="content">content: ${article.content}...</p>
              <p><a href="<c:url value="${pageContext.request.contextPath}/articles/${article.id}"/>">Show Details</a></p>
              <c:if test="${article.authorId == user.id}">
                  <p>
                      <a href="<c:url value="${pageContext.request.contextPath}/edit/articleId?articleId=${article.id}"/>">Edit</a>
                  </p>
                  <form action="${pageContext.request.contextPath}/delete/articleId?articleId=${article.id}" method="post">
                      <input type="submit" value="Delete">
                  </form>
              </c:if>
          </div>
      </c:forEach>

</body>
</html>
