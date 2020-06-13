<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<header>
    <c:choose>
        <c:when test="${user == null}">
            <ul>
                <li>You are not signed in.</li>
                <li>
                    <button onclick="location.href='./sign-up'">Sign Up</button>
                </li>
                <li>
                    <button onclick="location.href='./sign-in'">Sign In</button>
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
