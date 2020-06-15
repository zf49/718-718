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
<form action="./avatar-select" method="post">
    <div>
        <div>
            <input type="radio" id="bul" name="avatarName" value="Bulbasaur.png">
            <label for="bul">
                <img src="${pageContext.request.contextPath}/avatar/Bulbasaur.png" alt="">
            </label><br>
            <input type="radio" id="cha" name="avatarName" value="Charmander.png">
            <label for="cha">
                <img src="${pageContext.request.contextPath}/avatar/Charmander.png" alt="">
            </label><br>
            <input type="radio" id="far" name="avatarName" value="Farfetchd.png">
            <label for="far">
                <img src="${pageContext.request.contextPath}/avatar/Farfetchd.png" alt="">
            </label>
        </div>
        <button type="submit">Confirm</button>
    </div>
</form>
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