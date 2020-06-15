<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${article.title}</title>
</head>
<body>
<% pageContext.setAttribute("newLineChar", "\n"); %>
    <jsp:include page="nav.jsp">
        <jsp:param name="user" value="${user}"/>
    </jsp:include>
<div>
    <h1>${article.title}</h1>
    <div>
        <p>Author: ${author.username}</p>
        <p>Create Date: ${article.date}</p>
        <c:if test="${article.authorId == user.id}">
            <p>
                <a href="<c:url value="${pageContext.request.contextPath}/edit/articleId?articleId=${article.id}"/>">Edit</a>
            </p>
            <form action="${pageContext.request.contextPath}/delete/articleId?articleId=${article.id}" method="post">
                <input type="submit" value="Delete">
            </form>
        </c:if>
    </div>
    <div>
        <p>${fn:replace(article.content, newLineChar, "<br>")}</p>
    </div>
</div>
<div>
    <h3>Comments:</h3>
    <dl>
        <c:forEach var="comment" items="${comments}">
            <dt>${comment.authorName}:</dt>
            <dd><span>${comment.content}</span><br>
                <span>${comment.date}</span>
                <c:if test="${comment.authorId == user.id || author.id == user.id}">
                    <form action="${pageContext.request.contextPath}/delete/commentId?commentId=${comment.id}" method="post">
                        <input type="submit" value="Delete">
                    </form>
                </c:if>
            </dd>
        </c:forEach>
    </dl>
    <c:if test="${user != null}">
        <form action="./${article.id}/comment?userId=${user.id}" method="post">
            <label for="enterComment">${user.username}: </label>
            <textarea id="enterComment" name="commentContent" rows="5" cols="80">Enter comments here</textarea>
            <input type="submit" value="Comment">
        </form>
    </c:if>
</div>
</body>
</html>
