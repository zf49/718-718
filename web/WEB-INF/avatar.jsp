<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Avatar Upload</title>
</head>
<body>

<jsp:include page="nav.jsp" />

<img src="<c:url value="/avatar/${user.avatarName}"/>" alt="">
<form action="<c:url value="/avatar-select"/>" method="post">
    <div>
        <div>
            <c:forEach var="name" items="${predefinedAvatarNames}">
                <input type="radio" id="predefined-${name}" name="avatarName" value="${name}">
                <label for="predefined-${name}">
                    <img src="${pageContext.request.contextPath}/avatar/${name}" alt="${name}">
                </label>
            </c:forEach>
        </div>
        <button type="submit">Confirm</button>
    </div>
</form>

<form action="<c:url value="/avatar"/>" method="post" enctype="multipart/form-data">
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