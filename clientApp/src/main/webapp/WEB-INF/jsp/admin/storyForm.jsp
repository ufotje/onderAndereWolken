<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Admin | Nieuw verhaal</title>
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
    <!-- Story tags -->
    <link href="${pageContext.request.contextPath}/css/admin/pages/storyTags.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/ckeditor/ckeditor.js"></script>
    <script src="${pageContext.request.contextPath}/js/ckEditor.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/ckEditor/ckEditor.css">
</head>
<body>
<%--@elvariable id="myStory" type=""--%>
<div id="wrapper">

    <!-- Navigation -->
    <%@include file="adminNavigation.jsp" %>

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
                        <i class="fa fa-pencil"
                           aria-hidden="true"></i> ${empty story ? 'Nieuw verhaal toevoegen' : 'Bewerk verhaal'}
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
                            <form:form method="post" modelAttribute="myStory">
                                <!-- Trip selection -->
                                <div class="form-group">
                                    <form:label path="trip" for="selectedTripId">Selecteer een trip
                                        <span style="color: red;"> * <form:errors path="trip"/>${tripSelectError}</span>
                                    </form:label>
                                    <select class="form-control" id="selectedTripId" name="selectedTripId">
                                        <option value="0" style="display: none;">Gelieve een trip te selecteren...
                                        </option>
                                        <c:forEach items="${trips}" var="trip">
                                            <option value="${trip.id}" ${trip.id == selectedTripId or trip.id == story.trip.id ? 'selected="selected"' : ''}>${trip.title}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <!-- /Trip selection -->
                                <!-- Titel -->
                                <div class="form-group">
                                    <form:label path="title">Titel
                                        <span style="color: red;"> * <form:errors path="title"/></span>
                                    </form:label>
                                    <form:input path="title" cssClass="form-control" type="text"
                                                value="${story.title}" placeholder="Voer een titel in..." size="100"/>
                                </div>
                                <!-- /Titel -->
                                <!-- Teaser -->
                                <div class="form-group">
                                    <form:label path="teaser">Teaser</form:label>
                                    <textarea name="teaser" id="teaser" class="form-control" type="text" rows="3"
                                              maxlength="300"
                                              placeholder="Schrijf eventueel een teaser... (Standaard worden de eerste 5 zinnen van het verhaal overgenomen)">${story.teaser}</textarea>
                                </div>
                                <!-- /Teaser -->
                                <!-- Story -->
                                <div class="form-group">
                                    <form:label path="story">Verhaal
                                        <span style="color: red;"> * <form:errors path="story"/></span>
                                    </form:label>
                                    <textarea id="editor" name="editor" class="form-control "
                                              rows="5">${story.story}</textarea>
                                </div>
                                <!-- /Story -->
                                <!-- Picture -- till picture upload works hidden field stores the picture url value. Upload button just for show -->
                                <form:hidden path="picUrl"/>
                                <input type="file" class="file">
                                <!-- CSFR possible problems with MultiPart filter
                                https://docs.spring.io/spring-security/site/docs/current/reference/html/csrf.html
                                <!--<div class="form-group">
                                <form method="post" action="/admin/verhalen/nieuw/upload"
                                enctype="multipart/form-data">
                                <label path="picture">Voeg minstens 1 afbeelding toe
                                <span style="color: red; font-weight: 900;"> *</span>
                                </label>
                                <input path="picture" type="file" class="form-control-file"
                                name="picture"/>
                                </form>
                                </div>-->
                                <!-- /Picture -->
                                <!-- Date -->
                                <div class="form-group">
                                    <form:label path="postDate">Datum
                                        <span style="color: red;"> * <form:errors path="postDate"/></span>
                                    </form:label>
                                    <form:input path="postDate" type="date" class="form-control"
                                                value="${story.postDate}"/>
                                </div>
                                <!-- /Date -->
                                <!-- Tags -->
                                <div class="form-group" style="margin-top: 15px;">
                                    <label for="storyTags">Voeg relevante tags toe:
                                        <input id="storyTags" type="text" class="tagged form-control"
                                               data-removeBtn="true"
                                               name="storyTags"
                                               value="${tags}"
                                               placeholder=""/>
                                    </label>
                                    <br/>
                                    <small id="tagHelp" class="form-text text-muted">Tags scheiden door gebruik te maken
                                        van de ENTER-toets.
                                    </small>
                                </div>
                                <!-- /Tags -->
                                <!-- Visibility -->
                                <div class="form-check" style="margin: 15px 0 15px 0;">
                                    <form:label path="visible">Verhaal op website weergeven?</form:label>
                                    <form:checkbox path="visible" cssClass="form-check-input"
                                                   cssStyle="transform: scale(1.3); margin-left: 1%;"
                                                   value="${story.visible}"
                                                   checked="${story.visible == true ? 'checked' : ''}"/>
                                </div>
                                <!-- /Visiblity -->
                                <!-- Submit -->
                                <div class="form-group" style="margin-top: 30px;">
                                    <input class="btn btnDef btnFocusWhite"
                                           style="background-color: #292b2c !important;" type="submit" name="action"
                                           value="Bewaren"/>
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
    <!-- /#page-wrapper -->

    <!-- jQuery -->
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

    <!-- Story tags -->
    <script src="${pageContext.request.contextPath}/js/admin/storyTags.js"></script>
    <script>
        initCkTextEditor();
    </script>
</body>
</html>
