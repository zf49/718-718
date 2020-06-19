<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html lang="en">
<head>
    <jsp:include page="bootstrap.jsp"/>
    <meta charset="UTF-8">
    <title>${article.title}</title>
    <script type="text/javascript">
        function showOrHideComments() {
            const button = document.getElementById("showOrHideButton");
            if (button.innerText === "Hide Comments")
                button.innerText = "Show comments";
            else
                button.innerText = "Hide Comments";
        }
    </script>
</head>
<body>
<% pageContext.setAttribute("newLineChar", "\n"); %>
<jsp:include page="nav.jsp">
    <jsp:param name="user" value="${user}"/>
</jsp:include>
<br><br><br>
<div class="container about-author center-text homeArticle">
    <div class="articlePage card-body">
        <div class="articleDetails">
            <p class = "blog-post-title text-break">${article.title}</p>
            <p class="blog-post-meta">Author: <a href="<c:url value="/user/${article.authorId}"/>">${article.authorName}</a></p>
            <p class="blog-post-meta">Create Date: ${article.date}</p>
            <c:if test="${article.authorId == user.id}">
                <a class="btn btn-outline-info" style="font-weight: 600;" href="<c:url value="/edit/articleId?articleId=${article.id}"/>">Edit</a>
                <form style="display: contents" action="<c:url value="/delete/articleId?articleId=${article.id}"/>"
                      method="post">
                    <input type="submit" class="btn btn-outline-danger" value="Delete">
                </form>
            </c:if>
        </div>
        <div class="content blog-main text-break">
            <p>${fn:replace(article.content, newLineChar, "<br>")}</p>
        </div>
    </div>
    <div class="container">
        <button class="btn btn-outline-info" type="button" data-toggle="collapse" data-target="#collapseExample"
                aria-expanded="false" aria-controls="collapseExample" id="showOrHideButton"
                onclick="showOrHideComments();">Hide Comments
        </button>
        <hr>
    </div>
    <div class="collapse card-body show" id="collapseExample">
        <h3>Comments:</h3>
        <dl>
            <c:forEach var="comment" items="${comments}">
                <div>
                    <div style="padding-left: ${comment.level * 3}rem">
                        <dt><a href="<c:url value="/user/${comment.authorId}"/>">${comment.authorName}</a>:</dt>
                        <dd class="text-break"><span>${comment.content}</span><br>
                            <span class="blog-post-meta" style="margin-right: 5px">${comment.date}</span>

                            <c:if test="${comment.canReply}">
                                <div style="display: contents">
                                    <button type="button" data-toggle="collapse"
                                            data-target="#replyComment${comment.id}"
                                            class="btn btn-outline-info" style="font-weight: 600;"
                                            aria-controls="navbarHeader" aria-expanded="false"
                                            aria-label="Toggle navigation">Reply
                                    </button>
                            </c:if>

                                    <c:if test="${comment.canDelete(user.id, article)}">
                                        <form style="display: unset; margin-top: 1em;" action="<c:url value="/delete/commentId?commentId=${comment.id}"/>"
                                              method="post">
                                            <input hidden name="articleAuthorId" value="${article.authorId}">
                                            <input type="submit" class="btn btn-outline-danger" value="Delete">
                                        </form>
                                    </c:if>

                            <c:if test="${comment.canReply}">
                                </div>
                                <div class="collapse bg-light" id="replyComment${comment.id}"
                                     style="margin-bottom: 5px">
                                    <form action="" method="post">
                                    <textarea id="replyArea" name="commentContent" rows="5" cols="80"
                                              class="form-control form-control-dark w-100 text-wrap"
                                              placeholder="Enter here"></textarea>
                                        <input hidden name="parentId" value="${comment.id}">
                                        <br>
                                        <input type="submit" class="btn btn-info" value="Comment">
                                    </form>
                                </div>
                            </c:if>

                        </dd>
                    </div>
                </div>
            </c:forEach>
        </dl>
        <hr>
    </div>
    <div class="container" style="padding-bottom: 1em">
    <c:if test="${user != null}">
            <form action="" method="post">
                <textarea id="enterComment" name="commentContent" rows="5" cols="80" placeholder="Enter comments here"
                          class="form-control form-control-dark w-100 text-wrap"></textarea>
                <input hidden name="parentId" value="0">
                <br>
                <input type="submit" class="btn btn-info" value="Comment">
            </form>
        </c:if>
    </div>
</div>
</body>
</html>
