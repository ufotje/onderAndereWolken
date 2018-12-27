<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="fb-root"></div>
<script>(function(d, s, id) {
    var js, fjs = d.getElementsByTagName(s)[0];
    if (d.getElementById(id)) return;
    js = d.createElement(s); js.id = id;
    js.src = 'https://connect.facebook.net/nl_NL/sdk.js#xfbml=1&version=v2.11';
    fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));</script>

<div class="col-md-4">
    <!-- Search Widget -->
    <div class="card mb-4">
        <h5 class="card-header backgroundDarkBrown insetHeaderWhite">Zoeken</h5>
        <div class="card-body">
            <form id="searchForm" action="/zoeken">
                <div class="input-group">
                    <input type="text" class="form-control" placeholder="Zoek..." name="waarde">
                    <span class="input-group-btn">
                                            <button class="btn backgroundDarkGrey colorVintageWhite" type="submit" form="searchForm" value="submit"><i class="fa fa-search"
                                                                                                                                aria-hidden="true"></i></button>
                    </span>
                </div>
            </form>
        </div>
    </div>
    <!-- Categories Widget -->
    <div class="card my-4">
        <h5 class="card-header backgroundDarkBrown insetHeaderWhite">Deel deze pagina</h5>
        <div class="card-body text-center">
            <div class="row">
                <div class="col-lg-3">
                    <ul class="list-unstyled mb-0" style="font-size: 30px;">
                        <li>
                            <div data-href="https://www.onder-andere-wolken.be/"><a class="fb-xfbml-parse-ignore colorDarkGrey" target="_blank" href="https://www.facebook.com/sharer/sharer.php?u=https%3A%2F%2Fwww.onder-andere-wolken.be%2F&amp;src=sdkpreparse">
                                <i class="fa fa-facebook-square" aria-hidden="true"></i>
                            </a></div>
                        </li>
                        <li>
                            <i class="fa fa-skype" aria-hidden="true"></i>
                        </li>
                    </ul>
                </div>
                <div class="col-lg-3">
                    <ul class="list-unstyled mb-0" style="font-size: 30px;">
                        <li>
                            <a href="https://twitter.com/share?ref_src=twsrc%5Etfw" class="colorDarkGrey" data-show-count="false"><i class="fa fa-twitter-square" aria-hidden="true"></i></a><script async src="https://platform.twitter.com/widgets.js" charset="utf-8"></script>
                        </li>
                        <li>
                            <i class="fa fa-envelope" aria-hidden="true"></i>
                        </li>
                    </ul>
                </div>
                <div class="col-lg-3">
                    <ul class="list-unstyled mb-0" style="font-size: 30px;">
                        <li>
                            <i class="fa fa-instagram" aria-hidden="true"></i>
                        </li>
                        <li>
                            <i class="fa fa-google-plus-square" aria-hidden="true"></i>
                        </li>
                    </ul>
                </div>
                <div class="col-lg-3">
                    <ul class="list-unstyled mb-0" style="font-size: 30px;">
                        <li>
                            <i class="fa fa-linkedin-square" aria-hidden="true"></i>
                        </li>
                        <li>
                            <i class="fa fa-reddit-square" aria-hidden="true"></i>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <!-- Travel locations -->
    <div class="card my-4">
        <div class="card-header backgroundDarkBrown insetHeaderWhite">
            <h5>Reis historiek</h5>
            <!--<h6 class="font-body" style="font-size: 12px;"><a class="linksWhite" href="#">Chronologisch</a> | <a class="linksWhite" href="#">Alfabetisch</a></h6>-->
        </div>
        <div class="card-body">
            <c:forEach items="${sidebarCountries}" var="sbCountry">
                <div class="row">
                    <div style="max-width: 45px;margin-left: 15px;margin-top: 4px;">
                        <div style="width: 100%;">
                            <svg id="cloud" x="0px" y="0px" width="100%" height="100%" viewBox="0 0 55 40.8" enable-background="new 0 0 55 40.8" style="filter: drop-shadow( -1px -1px 3px rgba(0,0,0,0.5) );">
                                <defs>
                                    <clipPath id="knockout${sbCountryId}">
                                        <path d="M54.3,29.4c0,3-1,5.5-3.1,7.6c-2.1,2.1-4.6,3.1-7.6,3.1H13.2c-3.4,0-6.4-1.2-8.8-3.7
                                            C1.9,34,0.7,31,0.7,27.6c0-2.5,0.7-4.7,2-6.7s3.1-3.6,5.2-4.6c0-0.5-0.1-0.9-0.1-1.2C7.9,11.2,9.3,7.8,12,5
                                            c2.8-2.8,6.2-4.2,10.1-4.2c2.9,0,5.6,0.8,8,2.5c2.4,1.6,4.1,3.8,5.2,6.4C36.7,8.5,38.2,8,40,8c2,0,3.7,0.7,5.1,2.1s2.1,3.1,2.1,5.1
                                            c0,1.4-0.4,2.7-1.1,3.9c2.4,0.6,4.4,1.8,5.9,3.8C53.5,24.7,54.3,26.9,54.3,29.4z"/>
                                    </clipPath>
                                </defs>
                                <image x="0%" y="0%" width="55" height="40.8" preserveAspectRatio="none" xlink:href="${sbCountry.flagUrl}" clip-path="url(#knockout${sbCountryId})" />
                            </svg>
                        </div>
                    </div>
                    <div style="margin-left: 15px;">
                        <h5 style="margin-bottom: 0px;font-size: 19px;"><a href="/trips?land=${sbCountry.name}" class="colorDarkGrey">${sbCountry.name}</a></h5>
                        <h6 style="margin-top: 6px;font-size: 12px;text-transform: uppercase;font-weight: bold;">
                        <c:forEach items="${countStoryCountries}" var="countStoryCountry">
                            <c:if test="${countStoryCountry.key == sbCountry.name and countStoryCountry.value != 0}"><a href="/verhalen?land=${sbCountry.name}" class="colorGreen linksHoverGold">Verhalen <span style="margin-right: 5px;">${countStoryCountry.value}</span></a></c:if>
                        </c:forEach>
                        <c:forEach items="${countAlbumsCountries}" var="countAlbumCountry">
                            <c:if test="${countAlbumCountry.key == sbCountry.name and countAlbumCountry.value != 0}"><a href="/kiekjes?land=${sbCountry.name}" class="colorOrangy linksHoverGold">Kiekjes <span style="margin-right: 5px;">${countAlbumCountry.value}</span></a></c:if>
                        </c:forEach>
                        <c:forEach items="${countMediaCountries}" var="countMediaCountry">
                            <c:if test="${countMediaCountry.key == sbCountry.name and countMediaCountry.value != 0}"><a href="/kijkjes?land=${sbCountry.name}" class="colorRosy linksHoverGold">Kijkjes <span style="margin-right: 5px;">${countMediaCountry.value}</span></a></c:if>
                        </c:forEach>



                        </h6>
                    </div>
                </div>
            <hr style="height: 1px; margin: 0 0 10px 0;">
            </c:forEach>
        </div>
    </div>
</div>