<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Sign In Failed</title>
    <meta http-equiv="refresh" content="5;<c:url value="/home"/>">
    <jsp:include page="bootstrap.jsp"/>
    <link href="<c:url value="/css/base.css"/>" rel="stylesheet">
    <script type="text/javascript" src="<c:url value="/javascript/countdown.js" />"></script>
</head>
<body>
<div class="jumbotron text-center welcome container" style="font-size: 150%; padding-top: 6rem;">
    <p>The page you visited does not exist.</p>
    <p>Automatically return to <a href="<c:url value="/home"/>">home</a> page in <span id="countdown">5</span> seconds
    </p>
</div>
</body>
</html>
