<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html lang="en">
<head>
    <jsp:include page="bootstrap.jsp" />
    <link href="<c:url value="/css/blog.css"/>" rel="stylesheet">
    <meta charset="UTF-8">
    <title>${article.title}</title>

</head>
<body>
<% pageContext.setAttribute("newLineChar", "\n"); %>

<jsp:include page="nav.jsp">
    <jsp:param name="user" value="${user}"/>
</jsp:include>
<div class="container about-author center-text homeArticle">
<br><br>
<div class = "articlePage card-body">
    <div class = "articleDetails">
        <h1 class="title">${article.title}</h1>
        <p class="blog-post-meta">Author: ${article.authorName}</p>
        <p class="blog-post-meta">Create Date: ${article.date}</p>
        <c:if test="${article.authorId == user.id}">
            <div class="btn-group">
                <a class="btn btn-sm btn-outline-secondary" href="<c:url value="/edit/articleId?articleId=${article.id}"/>">Edit</a>

            <form action="<c:url value="/delete/articleId?articleId=${article.id}"/>" method="post">
                <input type="submit" class="deleteRightButton btn btn-sm btn-outline-secondary" value="Delete">
            </form>
            </div>
        </c:if>
    </div>
    <div class="content blog-main">
        <p>${fn:replace(article.content, newLineChar, "<br>")}</p>
    </div>
</div>
<hr>
    <div class="card-body">
        <h3>Comments:</h3>
        <dl>
            <c:forEach var="comment" items="${comments}">
                <div style="padding-left: ${comment.level * 3}rem">
                    <dt>${comment.authorName}:</dt>
                    <dd><span>${comment.content}</span><br>
                        <span class="blog-post-meta" style="margin-right: 5px">${comment.date}</span>
                        <c:if test="${user != null && comment.level < 2}">
                            <button type="button" data-toggle="collapse" data-target="#replyComment${comment.id}"
                                    class="btn btn-sm btn-outline-secondary" style="margin-bottom: 5px"
                                    aria-controls="navbarHeader" aria-expanded="false" aria-label="Toggle navigation">
                                Reply
                            </button>
                            <div class="collapse bg-light" id="replyComment${comment.id}" style="margin-bottom: 5px">
                                <form action="<c:url value="/reply-comment"/>" method="post">
                            <textarea id="replyArea" name="replyContent" rows="5" cols="80"
                                      class="form-control form-control-dark w-100" placeholder="Enter here"></textarea>
                                    <input hidden name="parentId" value="${comment.id}">
                                    <br>
                                    <input type="submit" class="btn btn-sm btn-outline-secondary" value="Comment">
                                </form>
                            </div>
                        </c:if>
                        <c:if test="${comment.authorId == user.id || article.authorId == user.id}">
                            <form action="<c:url value="/delete/commentId?commentId=${comment.id}"/>"
                                  method="post">
                                <input type="submit" class="btn btn-sm btn-outline-secondary" value="Delete">
                            </form>
                        </c:if>
                    </dd>
                </div>
            </c:forEach>
        </dl>
        <hr>
        <c:if test="${user != null}">
            <form action="./${article.id}/comment?userId=${user.id}" method="post">
                <textarea id="enterComment" name="commentContent" rows="5" cols="80"
                          class="form-control form-control-dark w-100" placeholder="Enter comments here"></textarea>
                <br>
                <input type="submit" class="btn btn-sm btn-outline-secondary" value="Comment">
            </form>
        </c:if>
    </div>
</div>
</body>
</html>
