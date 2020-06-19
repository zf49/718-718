<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<header class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
    <ul class="navbar-nav mr-auto">
        <li class="nav-item">
            <a  class="btn nav-link" style="font-size:  1.3rem" href="<c:url value="/home"/>">Home</a>
        </li>
    </ul>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse form-inline mt-2 mt-md-0" id="navbarCollapse">
        <ul class="navbar-nav mr-auto">
<c:choose>
    <c:when test="${user == null}">
    <li class="nav-item">
        <button class="btn nav-link" style="font-size:  1.3rem" onclick="location.href='<c:url value="/sign-in"/>'">Sign In</button>
    </li>
    <li class="nav-item">
        <button class="btn nav-link" style="font-size:  1.3rem" onclick="location.href='<c:url value="/sign-up"/>'">Sign Up</button>
        </li>
    </c:when>
    <c:otherwise>
        <li class="nav-item">
        <a href="<c:url value="/avatar"/>">
        <img class="avatar-img" src="<c:url value="/${user.avatarPath}"/>" alt="avatar">
        </a>
        </li>
        <li class="nav-item"><a class="nav-link" style="font-size:  1.3rem" href="<c:url value="/account"/>">${user.username}</a></li>
        <li class="nav-item"><a class="nav-link" style="font-size:  1.3rem" href="<c:url value="/new-article"/>">Write Article</a></li>
        <li class="nav-item">
        <a class="nav-link" style="font-size:  1.3rem" href="<c:url value="/user/${user.id}"/>">My Articles</a>
        </li>
        <li class="nav-item">
            <form class="form-inline" action="<c:url value="/sign-out"/>" method="post">
                    <%--                    <input class="btn nav-link" type="submit" value="Sign Out">--%>
                <button class="btn nav-link" style="font-size:  1.3rem" type="submit">Sign Out</button>
            </form>
        </li>
    </c:otherwise>
</c:choose>
        </ul>
    </div>
</header>

