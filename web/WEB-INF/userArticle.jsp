<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
