<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%--TODO: add home link--%>
<%--TODO: No add article if not signed in--%>
<header style="background-color: gainsboro">
    <c:choose>
        <c:when test="${user == null}">
            <ul>
                <li>You are not signed in.</li>
                <li>
                    <button onclick="location.href='${pageContext.request.contextPath}/sign-up'">Sign Up</button>
                </li>
                <li>
                    <button onclick="location.href='${pageContext.request.contextPath}/sign-in'">Sign In</button>
                </li>
            </ul>
        </c:when>
        <c:otherwise>
            <ul>
                <li>Hi, <a href="<c:url value="/account/"/>">${user.username}</a></li>
                <li><a href="<c:url value="/new-article"/>">Add a New Article</a></li>
                <li value="${user.id}" name = "userId" id="userId">
                    <a href="<c:url value="/userArticles"/>">User Article</a>
                </li>
                <li>
                    <form action="/sign-out" method="post">
                        <input type="submit" value="Sign Out">
                    </form>
                </li>
            </ul>
        </c:otherwise>
    </c:choose>
</header>
