<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${article.title}</title>
</head>
<body>

    <jsp:include page="nav.jsp">
        <jsp:param name="user" value="${user}"/>
    </jsp:include>

<div>
    <h1>${article.title}</h1>
    <div>
        <span>${author.username}</span>
        <span>${article.dateCreated}</span>
        <c:if test="${article.authorId == user.id}">
            <span>
                <a href="<c:url value="/edit/articleId?articleId=${article.id}"/>">Edit</a>
            </span>
            <span>
                <a href="<c:url value="/articles/${article.id}/delete/articleId?articleId=${article.id}"/>">Delete</a>
            </span>
        </c:if>
    </div>
    <div>
        <p>${article.content}</p>
    </div>
</div>
<div>
    <h3>Comments:</h3>
    <dl>
        <c:forEach var="comment" items="${comments}">
            <dt>${comment.authorName}:</dt>
            <dd><span>${comment.content}</span><br>
                <span>${comment.dateCreated}</span>
                <c:if test="${comment.authorId == user.id || author.id == user.id}">
                    <span>
                        <a href="<c:url value="/articles/${article.id}/delete/commentId?commentId=${comment.id}"/>">Delete</a>
                    </span>
                </c:if>
            </dd>
        </c:forEach>
    </dl>
    <form action="/articles/${article.id}/userId?userId=${user.id}" method="post">
        <label for="enterComment">${user.username}: </label>
        <textarea id="enterComment" name="commentContent" rows="5" cols="80">enter comments here</textarea>
        <input type="submit" value="Comment">
    </form>
</div>
</body>
</html>
