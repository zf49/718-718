<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<header class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
    <ul class="navbar-nav bd-navbar-nav flex-row">
        <li class="nav-item">
            <a class="nav-link" style="font-size: 1.2em" href="<c:url value="/home"/>">Home</a>
        </li>
    </ul>
    <div class="container" style="padding-right: 0;margin-right: 0;width:auto">
        <ul class="navbar-nav bd-navbar-nav flex-row">
    <c:choose>
        <c:when test="${user == null}">
           
            <li class="nav-item">
                <button class="btn nav-link" onclick="location.href='<c:url value="/sign-in"/>'">Sign In</button>
            </li>
            <li class="nav-item">
                <button class="btn nav-link" onclick="location.href='<c:url value="/sign-up"/>'">Sign Up</button>
            </li>
        </c:when>
        <c:otherwise>
            <li class="nav-item">
                <a href="<c:url value="/avatar"/>">
                    <img class="avatar-img" src="<c:url value="/${user.avatarPath}"/>" alt="avatar">
                </a>
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
    </div>
</header>
