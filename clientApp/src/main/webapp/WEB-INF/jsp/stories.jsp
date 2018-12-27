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
    <title>OAW - Reizen overzicht</title>
    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/css/modern-business.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link href="${pageContext.request.contextPath}/css/customUtilities.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Pacifico" rel="stylesheet">
    <!-- Favorite icon -->
    <link rel="icon" href="${pageContext.request.contextPath}/images/oaw.ico" type="image/x-icon"/>
</head>
<body>
<!-- Navigation -->
<%@include file="navigation.jsp" %>

<!-- Page Content -->
<div class="container" style="margin-top: 50px;">
    <div class="row" style="margin-bottom: 15px;">
    <!-- Page Heading/Breadcrumbs -->
        <div class="col-md-12">
            <h1 class="headerNeutralTitle" style="text-shadow: 1px 1px #ffffff;margin:10px 0 25px 0px;">
            <c:choose>
                <c:when test="${not empty tripname}">
                    Verhalen voor: <span class="colorBlue"><i class="fa fa-plane"></i> ${tripname}</span>
                </c:when>
                <c:when test="${not empty storiesForCountry}">
                    Verhalen voor: <span class="colorRed"><i class="fa fa-flag"></i> ${storiesForCountry}</span>
                </c:when>
                <c:otherwise>Overzicht verhalen</c:otherwise>
            </c:choose>
            </h1>
        </div>

        <div class="col-md-8">
            <c:forEach items="${mainItems}" var="item">
                <c:set var="story" value="${item.key}" />
                <!-- Project One -->
                <div class="row">
                    <div class="col-md-12">
                        <img class="picture700x300" src="${story.picUrl}" alt="">
                        <div style="position: absolute;bottom: 0; left: 0;background-color: rgba(111,143,114,0.7);width: calc(100% - 30px);min-height: 20px;margin-left: 15px;padding-left: 10px; padding-right: 10px;">
                            <h3 style="margin-top: 10px; margin-bottom: 3px;">
                                <a href="${pageContext.request.contextPath}/verhalen/${story.id}" class="fontHeader insetHeaderWhite linksHoverGold"><c:out value="${story.title}"/></a>
                            </h3>
                            <p class="colorVintageWhite" style="margin-top: 0; margin-bottom: 3px;font-weight: 100;">
                                <fmt:formatDate value="${story.postDate}" pattern="dd MMMM yyyy"/>
                                <span class="pull-right">laatst bewerkt <fmt:parseDate value="${story.latestEdit}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both"/>
                                    <fmt:formatDate value="${parsedDateTime}" pattern="dd MMMM yyyy"/></span>
                            </p>
                        </div>
                    </div>
                      <div class="col-md-12">
                          <div style="margin-top: 10px;margin-bottom: 10px;">${story.teaser}</div>
                          <p class="taglist">
                              <c:forEach items="${story.tags}" var="tag">
                                  <a href="${pageContext.request.contextPath}/verhalen?tag=${tag}" class="card-link">#${tag}</a>
                              </c:forEach>
                          </p>
                          <div class="row" style="padding-right: 12px;">
                              <div class="col text-left colorGreen" style="padding-top: 10px;">${story.likes} <i class="fa fa-thumbs-up" style="margin-right: 10px;"></i> ${fn:length(story.comments)} <i class="fa fa-comment" style="margin-right: 10px;"></i></div>
                              <a class="btn btnDef backgroundGreen pull-right" href="${pageContext.request.contextPath}/verhalen/${story.id}">Lees meer &rarr;</a>
                          </div>
                      </div>
                </div>
                <!-- /.row -->
                <hr>
            </c:forEach>


            <!-- Pagination -->
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