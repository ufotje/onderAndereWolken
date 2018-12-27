<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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

    <div class="row" style="margin-bottom: 2%;">
        <div class="col-md-8">
            <h1 class="mt-4 mb-3 fontHeader">Pagina niet gevonden</h1>
        </div>
    </div>

    <div class="row">
        <div class="col-md-8">
            <img class="img-fluid rounded"
                 src="https://previews.123rf.com/images/lineartestpilot/lineartestpilot1410/lineartestpilot141005062/32629591-cartoon-thunder-cloud-Stock-Photo.jpg"/>
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