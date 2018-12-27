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
    <title>OAW - Mijn kijkjes overzicht</title>
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
                    <c:when test="${not empty mediaForCountry}">
                        Kijkjes voor: <span class="colorRed"><i class="fa fa-flag"></i> ${mediaForCountry}</span>
                    </c:when>
                    <c:otherwise>Overzicht kijkjes</c:otherwise>
                </c:choose>
            </h1>
        </div>


        <div class="col-md-8">
            <c:forEach items="${mainItems}" var="item">
                <c:set var="media" value="${item.key}"></c:set>
                <!-- Project One -->
                <div class="row" style="margin-bottom: 30px;">
                    <div class="col-9" style="overflow: hidden;">
                        <h3 class="fontHeader colorRosy" style="position: absolute;padding: 10px 10px 10px 10px;font-size: 20px;z-index: 1;background-color: rgba(0,0,0,0.7);"><c:out value="${media.title}"/></h3>
                        <div class="embed-responsive embed-responsive-16by9">
                            <iframe class="embed-responsive-item" style="z-index: 0;" src="https://www.youtube.com/embed/${media.url}?rel=0&amp;showinfo=0" frameborder="0" allowfullscreen></iframe>
                        </div>
                    </div>
                    <div class="col-3">
                        <c:if test="${not empty media.postDate}">
                            <p><fmt:formatDate value="${media.postDate}" pattern="dd MMMM yyyy"/></p>
                        </c:if>
                        <div style="font-size: 14px;display: inline;">
                            <span class="colorRosy">Aantal likes: ${media.likes}</span>
                            <form action="${pageContext.request.contextPath}/kijkjes/addLike" method = "post" style="display: inline;"><input type = "hidden" name= "mediaId" value ="${media.id}"/><input type = "submit" class="fontAwesome buttonAsLink colorRosy linksHoverGold backgroundVintageWhite" style="display: inline;font-size: 25px;" value ="&#xf164"/> <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"> </form>
                        </div>
                        <div style="position:absolute;bottom: 0;padding-right: 0px;">
                            <a class="btn btnDef backgroundRosy float-right" href="${media.url}" target="_blank">Ga naar &rarr;</a>
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