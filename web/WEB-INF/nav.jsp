<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%--TODO: add home link--%>
<%--TODO: No add article if not signed in--%>
<header class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
    <ul class="navbar-nav bd-navbar-nav flex-row">
        <li class="nav-item">
            <a class="nav-link" href="<c:url value="/home"/>">Home</a>
        </li>
    <c:choose>
        <c:when test="${user == null}">
            <li class="nav-item">
                <button class="btn nav-link" onclick="location.href='<c:url value="sign-in"/>'">Sign In</button>
            </li>
            <li class="nav-item">
                <button class="btn nav-link" onclick="location.href='<c:url value="sign-up"/>'">Sign Up</button>
            </li>
        </c:when>
        <c:otherwise>
            <li class="nav-item">
<%--                <a href="<c:url value="/avatar"/>">--%>
<%--                    <img src="<c:url value="/${user.avatarPath}"/>" alt="avatar">--%>
<%--                </a>--%>
            </li>
            <li class="nav-item"><a class="nav-link" href="<c:url value="/account"/>">${user.username}</a></li>
            <li class="nav-item"><a class="nav-link" href="<c:url value="/new-article"/>">Write Article</a></li>
            <li class="nav-item">
                <a class="nav-link" href="<c:url value="/user/${user.id}"/>">My Articles</a>
            </li>
            <li class="nav-item">
                <form class="form-inline" action="<c:url value="/sign-out"/>" method="post">
<%--                    <input class="btn nav-link" type="submit" value="Sign Out">--%>
                    <button class="btn nav-link" type="submit">Sign Out</button>
                </form>
            </li>
        </c:otherwise>
    </c:choose>
    </ul>
</header>
