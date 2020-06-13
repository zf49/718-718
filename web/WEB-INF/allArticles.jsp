<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Articles</title>
</head>
<body>
      <h1>all articles</h1>
      <c:forEach items="${a}" var="a">
          <div class="articles">
             <h1 class="title">Title:${a.title}</h1>
              Create Date:<p class="date">${a.dateCreated}</p>
              authorId :<p class="title">${a.authorId}</p>
              content:<p class="title">${a.content}</p>
          </div>
      </c:forEach>

</body>
</html>
