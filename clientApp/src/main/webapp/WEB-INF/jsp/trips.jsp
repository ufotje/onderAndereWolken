<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Onder Andere Wolken - Mijn Reizen overzicht</title>
    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/css/modern-business.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Pacifico" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/customUtilities.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <!-- Favorite icon -->
    <link rel="icon" href="${pageContext.request.contextPath}/images/oaw.ico" type="image/x-icon"/>

</head>
<body>
<!-- Navigation -->
<%@include file="navigation.jsp" %>

<!-- Page Content -->
<div class="container">


    <div class="row" style="margin-bottom: 15px;margin-top: 40px;">

        <div class="col-md-12">
            <h1 class="headerNeutralTitle" style="text-shadow: 1px 1px #ffffff;margin:10px 0 25px 0px;">
                <c:choose>
                    <c:when test="${not empty tripsForCountry}">
                        Reizen voor: <span class="colorRed"><i class="fa fa-flag"></i> ${tripsForCountry}</span>
                    </c:when>
                    <c:otherwise>Overzicht reizen</c:otherwise>
                </c:choose>
            </h1>
        </div>


        <div class="col-md-8">
            <c:forEach items="${mainItems}" var="item">
                <c:set var="trip" value="${item.key}"></c:set>
                <!-- Project One -->
                <div class="row" style="margin-bottom: 30px;">
                    <h3 class="col-md-12" style="margin-bottom: 15px;"><a href="${pageContext.request.contextPath}/trips/${trip.id}" class="fontHeader colorBlue linksHoverRed"><c:out value="${trip.title}"/></a></h3>
                    <div class="col-md-6">
                        <img class="img-fluid rounded mb-3 mb-md-0 picture700x300" src="/images/mapsPlaceholder.png" alt="">
                    </div>
                    <div class="col-md-6">
                        <c:if test="${not empty trip.startDate and not empty trip.endDate}">
                            <p><fmt:formatDate value="${trip.startDate}" pattern="dd MMMM yyyy"/> - <fmt:formatDate value="${trip.endDate}" pattern="dd MMMM yyyy"/></p>
                        </c:if>
                        <div style="margin-bottom: 45px;"><c:forEach items="${trip.countries}" var="country">
                            <span class="colorDarkGrey" style="margin-right: 15px;font-weight: bold;font-size: 13px;"><i class="fa fa-flag"></i> ${country.name}</span>
                        </c:forEach></div>
                        <div style="position:absolute;bottom: 0;">
                            <!--<a class="btn btnDef backgroundBlue" href="${pageContext.request.contextPath}/verhalen/trip/${trip.id}">Verhalen (${not empty trip.stories ? fn:length(trip.stories) : 0})</a>
                            <a class="btn btnDef backgroundBlue" href="#">Foto's (${not empty trip.photoAlbums ? fn:length(trip.photoAlbums) : 0})</a>-->
                            <a class="btn btnDef backgroundBlue" href="${pageContext.request.contextPath}/verhalen/trip/${trip.id}">Verhalen</a>
                            <a class="btn btnDef backgroundBlue" href="${pageContext.request.contextPath}/kijkjes">Media</a>
                            <a class="btn btnDef backgroundBlue" href="#">Foto's</a>
                        </div>
                    </div>
                </div>
                <hr>
                <!-- /.row -->
            </c:forEach>
            <c:if test="${showPagination}">
                <ul class="pagination justify-content-center mb-4">
                    <li class="page-item ${previousNext eq 'first' or previousNext eq 'only' ? 'disabled' : ''}">
                        <a class="page-link" href="${pageContext.request.contextPath}${url}${pageNr - 1}">&larr; Nieuwer</a>
                    </li>
                    <li class="page-item ${previousNext eq 'last' or previousNext eq 'only' ? 'disabled' : ''}">
                        <a class="page-link" href="${pageContext.request.contextPath}${url}${pageNr + 1}">Ouder &rarr;</a>
                    </li>
                </ul>
            </c:if>
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