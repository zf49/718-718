<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <title>${article.title}</title>
</head>
<body>
<div>
    <h1>${article.title}</h1>
    <div>
        <span>author</span>
        <span>${article.dateCreated}</span>
    </div>
    <div>
        <p>${article.content}</p>
    </div>
</div>
<div>
    <h3>Comments:</h3>
    <dl>
        <c:forEach var="comment" items="comments">
            <dt>Username:</dt>
            <dd>${comment.content} ${comment.dateCreated}</dd>
        </c:forEach>
    </dl>
    <form>
        <label for="enterComment">Username: </label>
        <textarea id="enterComment" rows="5" cols="80">enter comment here</textarea>
        <input type="submit" value="Comment">
    </form>
</div>
</body>
</html>
