<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <jsp:include page="bootstrap.jsp"/>
    <link href="<c:url value="/css/album.css"/>" rel="stylesheet">
    <title>Avatar Upload</title>
</head>
<body>
<jsp:include page="nav.jsp"/>
<br><br><br>
 <div class="container about-author center-text homeArticle">
<section class="jumbotron text-center bg-light ">
    <div class="container">
        <img class="d-block mx-auto" src="<c:url value="/avatar/${user.avatarName}"/>" alt="${user.avatarName}"
             title="${user.avatarName}">
        <p class="lead text-muted">You can <a href="#selectAvatar">select</a> one from the list or <a href="#uploadAvatar">upload</a> your own avatar</p>
    </div>
</section>

<div class="album py-5" id="selectAvatar">
    <form action="<c:url value="/avatar-select"/>" method="post">
        <div class="container">
            <div class="row" style="justify-content: space-evenly">
                <c:forEach var="name" items="${predefinedAvatarNames}">
                    <div class="card mb-4 shadow-sm">
                        <div class="bd-placeholder-img card-img-top">
                            <label for="predefined-${name}" title="${name}">
                                <img height="225" src="<c:url value="/avatar/${name}"/>" alt="${name}">
                            </label>
                        </div>
                        <div class="card-body">
                            <input type="radio" id="predefined-${name}" name="avatarName" value="${name}" required>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <div class="container" style="display: flex; justify-content: center;">
                <button type="submit" class="btn btn-info my-2" style="margin-bottom: 20px">Confirm</button>
            </div>
        </div>
    </form>
</div>

<div class="album py-5 bg-light" id="uploadAvatar">
    <form action="<c:url value="/avatar"/>" method="post" enctype="multipart/form-data">
        <div class="container">
            <div class="row">
                <fieldset>
                    <legend class="lead text-muted">Upload Image</legend>
                    <%--            <label for="avatar-input" >Image:</label><br>--%>
                    <input type="file" name="avatar" id="avatar-input" required class="btn btn-secondary my-2">
                    <button type="submit" class="btn btn-info my-2 float-right" style="margin: 20px">Upload and
                        Confirm
                    </button>
                </fieldset>
            </div>
        </div>
    </form>
</div>

<footer class="text-muted">
    <div class="container">
        <p class="float-right">
            <a href="#">Back to top</a>
        </p>
    </div>
</footer>
 </div>
</body>
</html>