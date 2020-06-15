<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2020/6/15
  Time: 18:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>More User's Information</title>
</head>
<body>

<jsp:include page="nav.jsp">
    <jsp:param name="user" value="${user}"/>
</jsp:include>

<p>Welcome ${user.uesrname}! Below is a list of information to let others know more about you. </p>

</body>
</html>
