<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Onder Andere Wolken - Reisblog Anneleen</title>
    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/css/modern-business.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <!-- Favorite icon -->
    <link href="${pageContext.request.contextPath}/css/customUtilities.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Pacifico" rel="stylesheet">
    <link rel="icon" href="${pageContext.request.contextPath}/images/oaw.ico" type="image/x-icon">
</head>
<body>
<!-- Navigation -->
<%@include file="navigation.jsp" %>

<!-- Page Content -->
<div class="container" style="padding-top: 20px;">

    <div class="row" style="margin-top: 40px;margin-bottom: 20px;">
        <div class="col-md-8">
            <div class="card card-group">
                <div class="card-header backgroundRed">
                    <h2 class="fontHeader insetHeaderWhite">Contact</h2>
                </div>
                <div class="card-body">
                    <form method="post">
                        <c:forEach items="${errors}" var="error">
                            ${error.defaultMessage}<br/>
                        </c:forEach>
                        ${saveTheMessage}
                        <div class="form-group">
                            <label for="name">Naam</label>
                            <input type="text" class="form-control" name="name" id="name" value = "${contact.name}">
                        </div>
                        <div class="form-group">
                            <label for="eMail">Email</label>
                            <input type="text" class="form-control" name="eMail" id="eMail" value = "${contact.eMail}">
                        </div>
                        <div class="form-group">
                            <label for="message">Bericht</label>
                            <textarea id="message" class="form-control" name="body" rows="4">${contact.body}</textarea>
                        </div>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                        <input type="submit" class="btn btn btnDef backgroundRed" value="Verzenden">
                    </form>
                </div>
            </div>

        </div>

        <%@include file="sidebars.jsp" %>

    </div>

</div>
<!-- /.container -->
<!-- Footer -->
<%@include file="footer.jsp" %>
<!-- Bootstrap core JavaScript -->
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>