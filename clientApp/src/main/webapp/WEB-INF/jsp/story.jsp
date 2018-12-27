<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <!-- Favorite icon -->
    <link href="${pageContext.request.contextPath}/css/customUtilities.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/visitor/story.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Pacifico" rel="stylesheet">
    <link rel="icon" href="${pageContext.request.contextPath}/images/oaw.ico" type="image/x-icon"/>
</head>
<body>
<!-- Navigation -->
<%@include file="navigation.jsp" %>

<!-- Page Content -->
<div class="container">

    <!-- Page Heading/Breadcrumbs -->
    <div class="row" style="margin-top: 60px;">

        <div class="col-md-8">
            <div >
                <h1 class="mt-4 mb-3 fontHeader"><c:out value="${story.title}"/></h1>
                <h5><c:out value="${story.postDate}"/></h5>
            </div>
            <hr>

            <div class="bodyText">${story.story}</div>

            <div class="row">
                <div class="col-md-12" style="font-size: 17px;">
                    <span class="colorGreen">Aantal likes: ${story.likes}</span>
                    <form action="${pageContext.request.contextPath}/verhalen/addLike" method = "post" style="display: inline;"><input type = "hidden" name= "storyId" value ="${story.id}"/><input type = "submit" class="fontAwesome buttonAsLink colorGreen linksHoverGold backgroundVintageWhite" style="display: inline;font-size: 25px;" value ="&#xf164"/> <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"> </form>
                </div>
            </div>

            <img class="mx-auto d-block img-fluid rounded" src="${story.picUrl}" alt="">
            <div class="row">
                <div class="col-md-12 text-middle" style="font-size: 17px;">
                    <div style="margin: 20px 0 25px 0;">
                        <a style="padding-top: 35px;" href="${pageContext.request.contextPath}/verhalen/trip/${story.trip.id}" class="colorDarkGrey linksHoverGold"><i class="fa fa-plane colorBlue" style="font-size: 25px;"></i> ${story.trip.title}</a>
                    </div>
                </div>
            </div>

            <p class="card-text taglist" style="margin-bottom: 25px;">
                <c:forEach items="${story.tags}" var="tag">
                    <a href="${pageContext.request.contextPath}/verhalen?tag=${tag}" class="card-link">#${tag}</a>
                </c:forEach>
            </p>


            <div class="card-body">
               <form method="post" action="${pageContext.request.contextPath}/commentaar/nieuw/#newcomments">
                <div class="form-group">
                  <label for="message">Laat een berichtje achter</label>
                  <textarea type="text" id="message" class="form-control" name="body" rows="4"></textarea>
                 </div>
                   <input type = "hidden" name= "storyId" value ="${story.id}"/>
                   <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                   <input type="submit" class="btn btn btnDef backgroundRed" value="Post">
                </form>
              </div>

            <c:if test="${not empty comments}">
                <hr>
            </c:if>

            <c:forEach items="${comments}" var="comment" varStatus="commentIndex">
                <div class="media mb-4">
                    <div class="media-body" style="background-color: white;">
                        <h5 class="mt-0">${comment.body}</h5>
                        <div class="row">
                            <div class="col-md-6">
                                <small><strong>Geplaatst op</strong> <em>${comment.postDate}</em></small>
                            </div>
                            <!-- Condition check -> link to remove comments -->
                            <c:if test="${not empty pageContext.request.userPrincipal}">
                                <div class="col-md-6" style="text-align: right;">
                                    <small><strong><a
                                            href="${pageContext.request.contextPath}/verhalen/${story.id}/verwijder_commentaar/${comment.id}">Verwijder
                                        commentaar</a></strong></small>
                                </div>
                            </c:if>
                        </div>
                    </div>
                </div>
            </c:forEach>
            <a name="newcomments"></a>
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
