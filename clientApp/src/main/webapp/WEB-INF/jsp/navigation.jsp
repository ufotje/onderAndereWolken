<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar fixed-top navbar-expand-lg backgroundDarkBrown fixed-top">
    <div class="container">
        <a class="navbar-brand" href="/"><img src="<c:url value='/images/logoOaw.svg' />" alt="Onder andere wolken"
                                              style="width: 400px;"></a>
        <button class="navbar-toggler navbar-toggler-right linksWhite" type="button" data-toggle="collapse"
                data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false"
                aria-label="Toggle navigation">
            <span class="navbar-toggler-icon linksWhite"><i class="fa fa-bars"></i></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive" style="margin-top: 25px;">
            <ul class="navbar-nav ml-auto align-bottom ">
                <li class="nav-item align-bottom ">
                    <a class="nav-link linksWhite" href="/"><i class="fa fa-home"></i></a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle linksWhite" href="#" id="navbarDropdownPortfolio"
                       data-toggle="dropdown"
                       aria-haspopup="true" aria-expanded="false">Mijn Reizen</a>
                    <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdownPortfolio">
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/trips">Routes</a>
                        <a class="dropdown-item" href="#">Foto's</a>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/verhalen">Verhalen</a>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/kijkjes">Filmpjes</a>
                    </div>
                </li>
                <!--
                <li class="nav-item">
                    <a class="nav-link linksWhite" href="#">Profiel</a>
                </li>
                -->
                <li class="nav-item">
                    <a class="nav-link linksWhite" href="${pageContext.request.contextPath}/links">Links</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link linksWhite" href="${pageContext.request.contextPath}/gastenboek">Gastenboek</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link linksWhite" href="${pageContext.request.contextPath}/contact">Contact</a>
                </li>
            </ul>
        </div>
    </div>
</nav>