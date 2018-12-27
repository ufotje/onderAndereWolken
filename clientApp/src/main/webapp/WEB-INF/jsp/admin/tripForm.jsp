<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Admin | trip</title>
    <!-- Bootstrap Core CSS -->
    <link href="${pageContext.request.contextPath}/css/admin/bootstrap.min.css" rel="stylesheet">
    <!-- MetisMenu CSS -->
    <link href="${pageContext.request.contextPath}/css/admin/metisMenu.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="${pageContext.request.contextPath}/css/admin/sb-admin-2.css" rel="stylesheet">
    <!-- Morris Charts CSS -->
    <link href="${pageContext.request.contextPath}/css/admin/morris.css" rel="stylesheet">
    <!-- Bootstrap-select -->
    <link href="${pageContext.request.contextPath}/css/bootstrap-select.css" rel="stylesheet">
    <!-- Custom Fonts -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Pacifico" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/customUtilities.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/admin/pages/adminCustomUtilities.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/admin/pages/main.css" rel="stylesheet">
</head>
<body>
<%--@elvariable id="myTrip" type=""--%>

<div id="wrapper">

    <%@include file="adminNavigation.jsp"%>

    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header fontHeader">Dashboard</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->

        <div class="row">
            <div class="col-lg-8">

                <div class="panel panel-default">
                    <div class="panel-heading fontHeader">
                        <i class="fa fa-pencil" aria-hidden="true"></i> ${empty trip ? 'Nieuwe trip toevoegen' : 'Bewerk trip'}
                    </div>

                    <!-- Input form -->
                    <div style="padding: 2% 0 0 2%;" class="row">
                        <div class="col-lg-12">
                            <h4 style="font-weight: 900;">Velden met
                                <span style="color: red;"> *</span> zijn verplicht
                            </h4>
                        </div>
                    </div>
                    <div style="padding: 2%;" class="row">
                        <div class="col-lg-12">
                            <form:form method="post" modelAttribute="myTrip">
                                <!-- Titel -->
                                <div class="form-group">
                                    <form:label path="title">Titel
                                        <span style="color: red;"> * <form:errors path="title"/></span>
                                    </form:label>
                                    <form:input path="title" cssClass="form-control" type="text"
                                                value="${trip.title}" placeholder="Voer een titel in..."/>
                                </div>
                                <!-- /Titel -->
                                <!-- Country selection -->
                                <div class="form-group">
                                    <label for="tripCountries">Selecteer één of meerdere bestemmingen
                                        <span style="color: red;"> * ${tripCountriesError}</span>
                                    </label>
                                    <select class="form-control selectpicker" id="tripCountries" name="tripCountries" multiple="true" data-selected-text-format="count > 3" data-live-search="true">
                                        <c:forEach items="${trip.countries}" var="country">
                                            <option selected="true" value="${country.name}">${country.name}</option>
                                        </c:forEach>
                                        <c:forEach items="${countries}" var="country">
                                            <option value="${country.name}">${country.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <!-- /Country selection -->
                                <!-- StartDate -->
                                <div class="form-group">
                                    <form:label path="startDate">Datum begin trip
                                        <span style="color: red;"> * <form:errors path="startDate"/></span>
                                    </form:label>
                                    <form:input path="startDate" type="date" class="form-control" value="${trip.startDate}"/>
                                </div>
                                <!-- /StartDate -->
                                <!-- EndDate -->
                                <div class="form-group">
                                    <form:label path="endDate">Datum eind trip
                                        <span style="color: red;"> * <form:errors path="endDate"/></span>
                                    </form:label>
                                    <form:input path="endDate" type="date" class="form-control" value="${trip.endDate}"/>
                                </div>
                                <!-- /EndDate -->
                                <!-- Visibility -->
                                <div class="form-check" style="margin: 15px 0 15px 0;">
                                    <form:label path="visible">Trip op website weergeven?</form:label>
                                    <form:checkbox path="visible" cssClass="form-check-input"
                                                   cssStyle="transform: scale(1.3); margin-left: 1%;"
                                                   value="${trip.visible}"
                                                   checked="${trip.visible == true ? 'checked' : ''}"/>
                                </div>
                                <!-- /Visiblity -->
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                <!-- Submit -->
                                <div class="form-group" style="margin-top: 30px;">
                                    <input type="submit" name="action" value="Bewaren"/>
                                </div>
                                <!-- /Submit -->
                            </form:form>
                        </div>
                    </div>
                </div>
            </div>

            <!-- /.col-lg-8 -->
            <%@include file="sidebar.jsp"%>
            <!-- /.col-lg-4 -->
            <!-- /.row -->
        </div>
        <!-- /. row -->
    </div>


    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="${pageContext.request.contextPath}/js/metisMenu.min.js"></script>

    <!-- Morris Charts JavaScript -->
    <script src="${pageContext.request.contextPath}/js/raphael.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/morris.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/morris-data.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="${pageContext.request.contextPath}/js/sb-admin-2.js"></script>

    <script src="${pageContext.request.contextPath}/js/admin/main.js"></script>

    <script src="${pageContext.request.contextPath}/js/bootstrap-select.js"></script>

</body>
</html>