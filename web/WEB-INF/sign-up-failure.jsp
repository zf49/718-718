<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Sign Up Failed</title>
    <meta http-equiv="refresh" content="5;<c:url value="/sign-up"/>">
    <script type="text/javascript">
        window.addEventListener("load", function () {
            const time = document.getElementById("countdown");
            function countdown() { time.innerText = (parseInt(time.innerText) - 1) + ""; }
            setInterval(countdown, 1000);
        })
    </script>
</head>
<body>
<p>Incorrect input. Please try again.</p>
<p>Automatically return to <a href="<c:url value="/sign-up"/>">sign-up</a> page in <span id="countdown">5</span> seconds</p>
</body>
</html>
