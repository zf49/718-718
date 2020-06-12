<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<header>
    <c:choose>
        <c:when test="${user == null}">
            <ul>
                <li>You are not signed in.</li>
                <li>
                    <form action="./sign-in" method="get">
                        <input type="submit" value="Sign In">
                    </form>
                </li>
            </ul>
        </c:when>
        <c:otherwise>
            <ul>
                <li>Hi, ${user.username}</li>
                <li>
                    <form action="./sign-out" method="post">
                        <input type="submit" value="Sign Out">
                    </form>
                </li>
            </ul>
        </c:otherwise>
    </c:choose>
</header>
