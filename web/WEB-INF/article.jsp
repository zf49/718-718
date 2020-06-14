<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <title>${article.title}</title>
</head>
<body>
<div>

    <jsp:include page="nav.jsp">
        <jsp:param name="user" value="${user}"/>
    </jsp:include>

    <h1>${article.title}</h1>
    <div>
        <span>${author.username}</span>
        <span>${article.dateCreated}</span>
        <c:if test="${author.id == user.id}">
            <span>Edit</span>
            <span>Delete</span>
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
            <dd>${comment.content}<br>
                    ${comment.dateCreated}
                <c:if test="${comment.authorId == user.id || author.id == user.id}">
                    <span> <a href="<c:url value="/articles/${article.id}/commentId?commentId=${comment.id}"/>" methods="delete">Delete</a></span>
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
