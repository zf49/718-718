<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Avatar Upload</title>
</head>
<body>

<<<<<<< HEAD:web/WEB-INF/avatar-upload.jsp
<form action="${pageContext.request.contextPath}/avatar-upload" method="post" enctype="multipart/form-data">
=======
<form action="./avatar" method="post" enctype="multipart/form-data">
>>>>>>> 858708db0a573e1d1decf25f8ffa912f50b70170:web/WEB-INF/avatar.jsp
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