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

    <div class="row" style="margin-bottom: 2%;">
        <div class="col-md-8">
            <h1 class="mt-4 mb-3 fontHeader">Gastenboek</h1>
        </div>
    </div>

    <div class="row">
        <div class="col-md-8">
            <!-- Collapseable panels most recent trips/stories/comments -->
            <div class="panel-group" style="margin-bottom: 4%;" id="accordion">
                <!-- Collapseable panel most recent trips-->
                <div class="panel panel-default">
                    <div class="card-mb-8">
                        <!--<div class="pull-left"> -->
                        <h5 class="card-header backgroundDarkBrown insetHeaderWhite">
                            <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne"
                               class="collapseHeader fontHeader linksWhite"
                               style="text-decoration: none;">${empty formFeedback ? 'Laat een boodschap na' : formFeedback}
                                <i class="fa fa-expand" style="float: right;" aria-hidden="true"></i>
                            </a>
                        </h5>
                        <!--</div>-->
                    </div>
                    <div id="collapseOne" class="panel-collapse collapse in ${showFormWithErrors}">
                        <div class="panel-body noPadding">
                            <div class="list-group">
                                <span class="list-group-item noPadding">
                                    <div class="card-body">
                                    <h6 style="font-weight: 900;">Velden met
                                        <span style="color: red;"> *</span> zijn verplicht
                                    </h6>
                                    <form:form modelAttribute="guestbookEntry" cssStyle="margin-top: 4%;">
                                        <!-- Naam -->
                                        <div class="form-group">
                                            <form:label path="name">Naam
                                                <span style="color: red;"> * <form:errors path="name"/></span>
                                            </form:label>
                                            <form:input path="name" cssClass="form-control" type="text"
                                                        value="${guestbook.name}"/>
                                        </div>
                                        <!-- /Naam -->
                                        <!-- Email -->
                                        <div class="form-group">
                                            <form:label path="email">E-mail
                                                <span style="color: red;"> * <form:errors path="email"/></span>
                                            </form:label>
                                            <form:input path="email" cssClass="form-control" type="text"
                                                        value="${guestbook.email}"/>
                                        </div>
                                        <!-- /Email -->
                                        <!-- Message -->
                                        <div class="form-group">
                                            <form:label path="message">Uw bericht
                                                <span style="color: red;"> * <form:errors path="message"/></span>
                                            </form:label>
                                            <form:textarea path="message" cssClass="form-control"
                                                           cssStyle="resize: none;" type="text"
                                                           value="${guestbook.message}" rows="5"
                                                           placeholder="Maximaal 255 karakters."/>
                                        </div>
                                        <!-- /Message -->
                                        <!-- Winnie -->
                                        <input type="text" id="winnie" name="winnie" value=""/>
                                        <!-- /Winnie -->
                                        <!-- Submit -->
                                        <div class="form-group" style="margin-top: 30px;">
                                            <input class="btn btnDef" style="background-color: black;" type="submit"
                                                   name="action" value="Verzenden"/>
                                            <button class="btn btnDef" style="background-color: black;" type="button"
                                                    onclick="window.location.href = '${pageContext.request.contextPath}/gastenboek'">Annuleren</button>
                                        </div>
                                        <!-- /Submit -->
                                    </form:form>
                                    </div>
                                </span>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- End collapseable panel most recent trips-->
            </div>
            <!-- End collapseable panels most recent trips/stories/comments -->
            <!-- Delete confirmation message, delete option only visible for logged in users -->
            <c:if test="${not empty deleteSuccess}">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="alert alert-dismissable"
                             style="background-color: #f5f5f5; border: 1px solid #ddd; border-radius: 4px;">
                            <a href="#" class="close" data-dismiss="alert" aria-label="close">×</a>
                            <strong>${deleteSuccess}</strong>
                        </div>
                    </div>
                </div>
            </c:if>
            <!-- /end delete message -->
            <!-- Show all guestbook entries -->
            <c:forEach items="${gbEntries}" var="gbEntry">
                <div class="card mb-8" style="margin-bottom: 2%; background-color: white;">
                    <h6 class="card-header backgroundDarkBrown insetHeaderWhite fontHeader"
                        style="font-size: 20px;">Geplaatst op
                        <span style="color: #eacc16;"><fmt:parseDate value="${gbEntry.postDate}" pattern="yyyy-MM-dd"
                                                                     var="parsedDate" type="date"/>
                        <fmt:formatDate value="${parsedDate}" type="date" dateStyle="long"/></span> door <span
                                style="color: #eacc16;">${gbEntry.name}</span>
                        <c:if test="${not empty pageContext.request.userPrincipal}">
                            <a class="nav-link linksWhite" style="float: right; padding: 0;"
                               href="${pageContext.request.contextPath}/gastenboek/verwijder/${gbEntry.id}"><i
                                    class="fa fa-trash-o" style="font-size: 22px;" aria-hidden="true"></i></a>
                        </c:if>
                    </h6>
                    <div class="media-body" style="padding: 2%;">
                        <p style="margin: 1%;">${gbEntry.message}</p>
                    </div>
                </div>
            </c:forEach>
            <!-- /end of guestbook entries -->
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