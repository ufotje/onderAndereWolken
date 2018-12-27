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
    <link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Pacifico" rel="stylesheet">
    <link rel="icon" href="${pageContext.request.contextPath}/images/oaw.ico" type="image/x-icon">
</head>
<body>
<!-- Navigation -->
<%@include file="navigation.jsp" %>

<header>
    <div class="carousel-inner" role="listbox">
        <!-- Slide One - Set the background image for this slide in the line below -->
        <iframe src="https://www.google.com/maps/embed?pb=!1m10!1m8!1m3!1d53324424.89402556!2d13.471037499999998!3d35.339149292688035!3m2!1i1024!2i768!4f13.1!5e0!3m2!1snl!2sus!4v1507280637919"
                width="100%" height="300px" frameborder="0" style="border:0; margin-top: 18px;"
                allowfullscreen></iframe>
        <!--<div style="background-color: rgba(255, 255, 255, 0.5); color: black;"
             class="carousel-caption d-none d-md-block">
            <h2>Toon laatste <i>n</i> bestemmingen op map?</h2>
        </div>-->
    </div>
</header>
<!-- Page Content -->
<div class="container" style="padding-top: 20px;">
    <div class="row">
        <c:choose>
            <c:when test="${not empty searchTerms}">
                <div class="col-md-12 searchFrame">
                    <p>Zoek resultaten voor <span>${searchTerms}</span></p>
                </div>
            </c:when>
            <c:otherwise>
                <div class="col-md-8" style="margin-bottom: 20px;">
                    <span class="align-middle headerNeutralTitle" style="text-shadow: 1px 1px #ffffff;">Nieuw onder de wolken</span>
                    <span class="float-right" style="margin-top: 15px;">
                        Sorteer
                        <select class="dropdownNeutral" onchange= "location=this.value;">
                            <option value="${pageContext.request.contextPath}/">keuze:</option>
                            <option value="${pageContext.request.contextPath}/pagina/1">recent</option>
                            <option value="${pageContext.request.contextPath}/populair/1">populair</option>
                        </select>
                    </span>
                </div>
            </c:otherwise>
        </c:choose>
    </div>

    <div class="row">

        <div class="col-md-8">

            <!-- Hard coded example of image album -->
          <!--  <div class="card mb-4">
                <div class="card-header backgroundOrangy">
                    <div class="row">
                        <div class="col">
                            <div class="albumImage-container" style="margin-top: 15px;margin-bottom: 7px;">
                                <img src="https://www.solidsurfacekitchens.co.uk/ekmps/shops/ssk2010/images/artis-crystal-pure-white-plain-worktops-accessories-wu-products-adhesive-bb-complete-290ml-17063-p[ekm]303x303[ekm].jpg" class="img-fluid rounded" style="height: 100px;object-fit: cover;">
                            </div>
                        </div>
                    </div>
                </div>
                <div style="overflow: hidden;">
                    <div style="text-overflow: clip;position: relative;background-color: #1b6d85;" ><i class="fa fa-image colorOrangy" style="font-size: 120px;position: absolute;right: 10px;top: 15px;z-index: 0;opacity: 0.10;"></i></div>
                    <div class="card-body" style="z-index: 1;">
                        <div>
                            <h2 class="card-title headerNeutralTitle fontHeader" >Yayoi Kusama: Life is the heart of a rainbow</h2>
                            <p class="card-subtitle mb-2" style="color: #251605;font-style: italic;padding-bottom: 10px;">Naam van de trip | laatst aangepast</p>
                            <div class="row">
                                <div class="col text-left">
                                    <a href="${pageContext.request.contextPath}/fotoalbums/${object.id}" class="btn btnDef backgroundOrangy">Ga naar &rarr;</a>
                                </div>
                                <div class="col text-right colorOrangy" style="padding-top: 10px;">0 <i class="fa fa-hashtag" style="margin-right: 10px;"></i> 0 <i class="fa fa-thumbs-up" style="margin-right: 10px;"></i> 0 <i class="fa fa-comment" style="margin-right: 10px;"></i></div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="card-footer backgroundOrangy colorDarkBrown">Land belgie | postdatum</div>
            </div>
            <!-- Hard coded example of image album -->



            <c:choose>
                <c:when test="${not empty mainItems}">
                    <c:forEach items="${mainItems}" var="resultObject">
                        <c:set var="objectType"  value="${resultObject.value}" />
                        <c:set var="object"  value="${resultObject.key}" />
                        <c:choose>
                            <c:when test="${objectType == 'story'}">
                                <div class="card mb-4">
                                    <img class="card-img-top picture700x300" src="${object.picUrl}" alt="Card image cap">
                                    <div style="overflow: hidden;">
                                        <div style="text-overflow: clip;position: relative;background-color: #1b6d85;" ><i class="fa fa-book colorGreen" style="font-size: 150px;position: absolute;right: 10px;top: 25px;z-index: 0;opacity: 0.10;"></i></div>
                                        <div class="card-body">
                                            <h2 class="card-title fontHeader">${object.title}</h2>
                                            <p class="card-text">${object.teaser}</p>
                                            <a href="${pageContext.request.contextPath}/verhalen/${object.id}" class="btn btnDef backgroundGreen">Lees meer &rarr;</a>
                                            <p class="card-text taglist">
                                                <c:forEach items="${object.tags}" var="tag">
                                                    <a href="${pageContext.request.contextPath}/verhalen?tag=${tag}" class="card-link">#${tag}</a>
                                                </c:forEach>
                                            </p>
                                        </div>
                                    </div>
                                    <div class="card-footer backgroundGreen colorDarkBrown">
                                        <div class="colorDarkBrown">Toegevoegd op ${object.postDate} door Anneleen
                                            <span class="float-right"></i> ${object.likes} <i class="fa fa-thumbs-up"></i></span>
                                        </div>
                                    </div>

                                </div>
                            </c:when>
                            <c:when test="${objectType == 'trip'}">
                                <div class="card mb-4 backgroundBlue" style="overflow: hidden;">
                                    <div style="text-overflow: clip;position: relative;background-color: #1b6d85;" ><i class="fa fa-plane" style="font-size: 175px;position: absolute;right: 10px;top: 0;z-index: 0;color: #251605;opacity: 0.05;"></i></div>
                                    <div class="card-body" style="z-index: 1;">
                                        <div>
                                            <h2 class="card-title insetHeaderWhite" >${object.title}</h2>
                                            <p class="card-subtitle mb-2" style="color: #251605;font-style: italic;padding-bottom: 10px;">Van ${object.startDate} tot ${object.endDate}  |  laatste aanpassing ${object.latestEdit}
                                             | </i> ${object.likes} <i class="fa fa-thumbs-up"></i></p>
                                            <a href="${pageContext.request.contextPath}/trips/${object.id}" class="btn btnDef backgroundBlue">Ga naar &rarr;</a>
                                        </div>
                                    </div>
                                </div>
                            </c:when>
                            <c:when test="${objectType == 'media'}">
                                <div class="card mb-4">
                                    <div style="overflow: hidden;">
                                        <div style="text-overflow: clip;position: relative;background-color: #1b6d85;" ><i class="fa fa-film colorRosy" style="font-size: 120px;position: absolute;right: 10px;top: 15px;z-index: 0;opacity: 0.10;"></i></div>
                                        <div class="card-body" style="z-index: 1;">
                                            <div>
                                                <h2 class="card-title headerNeutralTitle fontHeader" ><i class="fa fa-film colorRosy"></i> ${object.title}</h2>
                                                <p class="card-subtitle mb-2" style="color: #251605;font-style: italic;padding-bottom: 10px;">${object.trip.title} | Laatst aangepast: ${object.latestEdit}</p>
                                                <div class="row">
                                                    <div class="col text-left">
                                                        <a href="https://www.youtube.com/watch?v=${object.url}" target="_blank" class="btn btnDef backgroundRosy">Afspelen &rarr;</a>
                                                    </div>
                                                    <div class="col text-right colorRosy" style="padding-top: 10px;"></i> ${object.likes}</div>
                                                    <form action="${pageContext.request.contextPath}/kijkjes/addLike" method = "post" style="display: inline;z-index: 2;margin-right: 10px;"><input type = "hidden" name= "mediaId" value ="${object.id}"/><input type = "submit" class="fontAwesome buttonAsLink colorRosy linksHoverGold" style="display: inline;font-size: 25px;background-color: transparent;" value ="&#xf164"/> <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"> </form>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-footer backgroundRosy colorDarkBrown">${object.postDate}</div>
                                </div>
                            </c:when>
                        </c:choose>
                    </c:forEach>
                <!-- Blog Entries Column -->
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
                </c:when>
                <c:otherwise>
                    <div class="card mb-4">
                        <div class="card-body">
                            <div>
                                <h2 class="card-text" >Niets gevonden :´(</h2>
                            </div>
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
        <!-- Sidebar Widgets Column -->
        <%@include file="sidebars.jsp" %>
    </div>
    <!-- /.row -->
</div>
<!-- Footer -->
<%@include file="footer.jsp" %>

<!-- Bootstrap core JavaScript -->
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>