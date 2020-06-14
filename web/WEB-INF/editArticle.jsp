<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Edit Article</title>
</head>
<body>

    <jsp:include page="nav.jsp">
        <jsp:param name="user" value="${user}"/>
    </jsp:include>

    <div>
        <c:choose>
            <c:when test="${article == null}">
                <form action="${pageContext.request.contextPath}/edit/newArticle">
                    <label for="titleArea">Title: </label>
                    <input type="text" name="articleTitle" id="titleArea" required value="Enter title here">
                    <br>
                    <label for="contentArea">Content: </label>
                    <textarea name="articleContent" id="contentArea" rows="20" cols="80">Enter content here</textarea>
                    <input hidden type="text" name="authorId" value="${user.id}">
                    <br>
                    <input type="submit" value="Create">
                </form>
            </c:when>
            <c:otherwise>
                <form action="${pageContext.request.contextPath}/edit/articleId?articleId=${article.id}" method="post" class="editArticle">
                    <label for="newTitle">Title: </label>
                    <input type="text" name="newTitle" id="newTitle" required value="${article.title}">
                    <br>
                    <label for="newContent">Content:</label>
                    <textarea name="newContent" id="newContent" rows="20" cols="80">${article.content}</textarea>
                    <br>
                    <input type="submit" value="Update">
                </form>
            </c:otherwise>
        </c:choose>
    </div>

</body>
</html>
