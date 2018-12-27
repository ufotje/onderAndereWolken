<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>OAW - Trip ${trip.title}</title>
    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/css/modern-business.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/customUtilities.css" rel="stylesheet">
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
<div class="container">

    <!-- Page Heading/Breadcrumbs -->
    <div class="row" style="margin-top: 40px;">
        <div class="col-md-8">
            <h1 class="mt-4 mb-3 fontHeader" style="margin-top: 20px;"><c:out value="${trip.title}"/></h1>
            <h5><c:out value="${trip.startDate}"/> - <c:out value="${trip.endDate}"/></h5>
        </div>
    </div>

    <hr>

    <div class="row">
        <div class="col-md-8">
            <h5><c:forEach items="${trip.countries}" var="country">
                -${country.name}-
            </c:forEach>
            </h5>
        </div>
    </div>

    <div class="row">
        <div class="col-md-8">
            <!-- replace with map -->
            <div id="map" style="height: 400px; width: 100%;"></div>
            <%--<img class="img-fluid" src="http://placehold.it/750x500" alt="">--%>
            <div class="row">
                <div class="col-md-4 text-center"><a href="#">Locaties</a></div>
                <div class="col-md-4 text-center"><a href="${pageContext.request.contextPath}/verhalen/trip/${trip.id}">Verhalen</a>
                </div>
                <div class="col-md-4 text-center"><a href="#">Foto's</a></div>
            </div>
        </div>

        <!-- Sidebar Widgets Column -->
        <%@include file="sidebars.jsp" %>

    </div>

</div>

</div>
<!-- /.container -->

<!-- Footer -->
<%@include file="footer.jsp" %>
<!-- Bootstrap core JavaScript -->
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script>
    function initialize() {
        var locations = [
            <c:forEach items="${tripLocations}" var="tripLocation" varStatus="status">
                ['DESCRIPTION', ${tripLocation.latitude}, ${tripLocation.longitude}, status],
            </c:forEach>
        ];

        window.map = new google.maps.Map(document.getElementById('map'), {
            mapTypeId: google.maps.MapTypeId.ROADMAP
        });

        var infowindow = new google.maps.InfoWindow();

        var bounds = new google.maps.LatLngBounds();

        var mIcon = {
            path: google.maps.SymbolPath.CIRCLE,
            fillOpacity: 1,
            fillColor: '#fff',
            strokeOpacity: 1,
            strokeWeight: 1,
            strokeColor: '#333',
            scale: 12
        };

        for (i = 0; i < locations.length; i++) {
            marker = new google.maps.Marker({
                icon: mIcon,
                label: {color: '#000', fontSize: '12px', fontWeight: '600'},
                position: new google.maps.LatLng(locations[i][1], locations[i][2]),
                map: map
            });

            bounds.extend(marker.position);

            google.maps.event.addListener(marker, 'click', (function (marker, i) {
                return function () {
                    infowindow.setContent(locations[i][0]);
                    infowindow.open(map, marker);
                }
            })(marker, i));
        }

        map.fitBounds(bounds);

        var listener = google.maps.event.addListener(map, "idle", function () {
            map.setZoom(5);
            google.maps.event.removeListener(listener);
        });
    }
</script>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAhrSgCf3NDTC_HSyKFsUq_ULL8mbY36qM&callback=initialize"
        async defer></script>
</body>
</html>