<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Articles</title>
</head>
<body>
<a href="/userArticles">User Articles</a><br>

      <h1>all articles</h1>
      <c:forEach items="${a}" var="a">
          <div class="articles">
             <h1 class="title">Title:${a.title}</h1>
              Create Date:<p class="date">${a.dateCreated}</p>
              Article Id: <p class="date">${a.id}</p>
              authorId :<p class="authorId">${a.authorId}</p>
              content:<p class="content">${a.content}</p>
          </div>
      </c:forEach>

</body>
</html>
