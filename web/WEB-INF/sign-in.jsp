<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Sign In</title>
</head>
<body>
<jsp:include page="nav.jsp" />
<form action="./sign-in" method="post" class="form-example">
    <div>
        <label for="username">Username: </label>
        <input type="text" name="username" id="username" required>
    </div>
    <div>
        <label for="password">Password: </label>
        <input type="password" name="password" id="password" required>
    </div>
    <div>
        <input type="submit" value="Sign In">
    </div>
</form>
</body>
</html>