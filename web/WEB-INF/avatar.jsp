<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Avatar Upload</title>
</head>
<body>

<img src="${pageContext.request.contextPath}/avatar/${user.avatarName}" alt="">
<form action="./avatar" method="post" enctype="multipart/form-data">
    <div>
        <div>
            <label for="avatar-input">Image:</label><br>
            <input type="file" name="avatar" id="avatar-input" required>
        </div>
        <button type="submit">Upload</button>
    </div>
</form>

</body>
</html>