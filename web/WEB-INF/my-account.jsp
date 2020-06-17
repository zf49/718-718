<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <jsp:include page="bootstrap.jsp"/>
    <link href="<c:url value="/css/form-validation.css"/>" rel="stylesheet">
    <link href="<c:url value="/css/album.css"/>" rel="stylesheet">
    <title>My Account - Setting</title>
</head>
<body class="bg-light">
<jsp:include page="nav.jsp"/>
<div class="container">

    <div class="text-center mb-4" style="padding-top: 6rem">
        <img class="d-block mx-auto" src="<c:url value="/${user.avatarPath}"/>" alt="avatar" height="200"><br>
        <a href="<c:url value="/avatar"/>" class="btn btn-primary my-2">Change Avatar</a>
    </div>

    <div class="row mb-4">
        <div class="col-md-8 order-md-2">
            <form action="" method="post">
                <fieldset>
                    <legend class="mb-3">Basic Information</legend>
                    <div class="mb-3">
                        <label for="username">Username: </label>
                        <input type="text" name="username" id="username" required value="${user.username}" class="form-control">
                    </div>
                    <div class="mb-3">
                        <label for="password">Password: </label>
                        <input type="password" name="password" id="password" required class="form-control">
                    </div>
                    <div class="mb-3">
                        <label for="confirmPassword">Confirm Password: </label>
                        <input type="password" name="confirmPassword" id="confirmPassword" required class="form-control">
                    </div>
                </fieldset>
                <hr class="mb-4">
                <fieldset>
                    <legend class="mb-3">Additional Information <span class="text-muted">(Optional)</span></legend>
                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label for="fname">First Name: </label>
                            <input type="text" name="fname" id="fname" value="${user.fname}" class="form-control">
                        </div>
                        <div class="col-md-6 mb-3">
                            <label for="lname">Last Name: </label>
                            <input type="text" name="lname" id="lname" value="${user.lname}" class="form-control">
                        </div>
                    </div>
                    <div class="mb-3">
                        <label for="date_birth">Date of Birth: </label>
                        <input type="date" name="dateBirth" id="date_birth" value="${user.date}" class="form-control">
                    </div>
                    <div class="mb-3">
                        <label for="description">Introduce Yourself: </label>
                        <textarea name="description" id="description" rows="5" class="form-control">${user.description}</textarea>
                    </div>
                    <input hidden name="detailId" value="${user.detailId}">
                </fieldset>
                <div>
                    <input type="submit" value="Update account settings" class="btn btn-primary btn-lg btn-block">
                </div>
            </form>
        </div>
        <div class="col-md-3 order-md-1 mb-4">
            <h4 class="mb-3">Setting</h4>
        </div>
    </div>

    <hr class="mb-4">
    <div class="row" style="padding-bottom: 6rem">
        <div class="col-md-8 order-md-2">
            <p>Deleting your account has the following effects:</p>
            <ul>
                <li>All of your articles will be removed.</li>
                <li>All of your comments will be removed.</li>
            </ul>
            <form action="${pageContext.request.contextPath}/delete/userId?userId=${user.id}" method="post">
                <input type="submit" value="Delete account" class="btn btn-danger my-2">
            </form>
        </div>
        <div class="col-md-3 order-md-1 mb-4">
            <h4 class="mb-3" style="color: #db3b21">Delete account</h4>
        </div>
    </div>

</div>
</body>
</html>
