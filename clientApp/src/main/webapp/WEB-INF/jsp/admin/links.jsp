<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Admin | main</title>
    <!-- Bootstrap Core CSS -->
    <link href="${pageContext.request.contextPath}/css/admin/bootstrap.min.css" rel="stylesheet">
    <!-- MetisMenu CSS -->
    <link href="${pageContext.request.contextPath}/css/admin/metisMenu.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="${pageContext.request.contextPath}/css/admin/sb-admin-2.css" rel="stylesheet">
    <!-- Morris Charts CSS -->
    <link href="${pageContext.request.contextPath}/css/admin/morris.css" rel="stylesheet">
    <!-- Custom Fonts -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Pacifico" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/customUtilities.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/admin/pages/adminCustomUtilities.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/admin/pages/main.css" rel="stylesheet">
</head>
<body>

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

        <c:if test="${not empty formFeedback}">
            <div class="row">
                <div class="col-lg-8">
                    <div class="alert alert-dismissable"
                         style="background-color: #f5f5f5; border: 1px solid #ddd; border-radius: 4px;">
                        <a href="#" class="close" data-dismiss="alert" aria-label="close">×</a>
                        <strong>${formFeedback}</strong>
                    </div>
                </div>
            </div>
        </c:if>

        <div class="row">
            <div class="col-lg-8">
                <!-- Collapseable panels most recent trips/stories/comments -->
                <div class="panel-group" id="accordion">
                    <!-- Collapseable panel most recent trips-->
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <!--<div class="pull-left"> -->
                            <h4 class="panel-title">
                                <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne"
                                   class="collapseHeader fontHeader">Huidige links</a>
                            </h4>
                            <!--</div>-->
                        </div>
                        <div id="collapseOne" class="panel-collapse collapse in">
                            <div class="panel-body noPadding">
                                <div class="list-group">
                                    <c:choose>
                                        <c:when test="${empty links}">
                                            <span class="list-group-item noPadding">
                                                <div class="flex-parent-row">
                                                    <p style="margin: 5px 0 5px 5px;">Geen links gevonden</p>
                                                </div>
                                            </span>
                                        </c:when>
                                        <c:otherwise>
                                            <c:forEach items="${links}" var="link">
                                                <span class="list-group-item noPadding">
                                                <div class="flex-parent-row">
                                                    <div class="flex-child-start">
                                                        <p class="flex-center-vertical truncate-text"
                                                           style="margin: 5px 0 0 5px;">${link.linkUrl}</p>
                                                    </div>
                                                    <div class="flex-child-start btn-group" role="group">
                                                        <button onclick="bewerken('${link.id}')"
                                                                class="btn btn-primary paddingButtons straightBordersLeft borderTopBottomTransp">
                                                            <i class="fa fa-edit fa-fw"></i>
                                                        </button>
                                                        <a href="${pageContext.request.contextPath}/admin/links/verwijder/${link.id}"
                                                           class="btn btn-danger paddingButtons borderTopBottomTransp"><i
                                                                class="fa fa-trash-o fa-fw"></i></a>
                                                    </div>
                                                </div>
                                                </span>
                                            </c:forEach>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- End collapseable panel most recent trips-->
                </div>
                <!-- End collapseable panels most recent trips/stories/comments -->
                <div class="panel panel-default">
                    <div class="panel-heading fontHeader">
                        <h4 class="panel-title">Link toevoegen</h4>
                    </div>

                    <div style="padding: 2%;" class="row">
                        <div class="col-lg-12">
                            <form:form method="post" modelAttribute="addLink"
                                       action="${pageContext.request.contextPath}/admin/links/nieuw">
                                <!-- Link URL -->
                                <div class="form-group">
                                    <form:label path="linkUrl">Website url
                                        <span style="color: red;"> * <form:errors path="linkUrl"/></span>
                                    </form:label>
                                    <form:input path="linkUrl" cssClass="form-control" type="text"
                                                value="${addLink.linkUrl}" placeholder="http://www.eenwebsite.be"/>
                                </div>
                                <!-- /Link URL -->
                                <!-- Description -->
                                <div class="form-group">
                                    <form:label path="description">Beschrijving
                                        <span style="color: red;"> * <form:errors path="description"/></span>
                                    </form:label>
                                    <form:textarea path="description" cssClass="form-control" cssStyle="resize: none;"
                                                   type="text"
                                                   value="${addLink.description}" rows="3"
                                                   placeholder="Maximaal 255 karakters..."/>
                                </div>
                                <!-- /Link URL -->
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

            <div class="col-lg-4">
                <div class="panel panel-default">
                    <div class="panel-heading fontHeader">
                        <i class="fa fa-info fa-fw"></i> Site gegevens
                    </div>
                    <!-- /.panel-heading -->
                    <div class="panel-body">
                        <div class="list-group">
                            <a href="#" class="list-group-item">
                                <i class="fa fa-suitcase fa-fw"></i> Aantal reizen
                                <span class="pull-right text-muted small"><em>1</em>
                                    </span>
                            </a>
                            <a href="#" class="list-group-item">
                                <i class="fa fa-flag fa-fw"></i> Bezochte landen
                                <span class="pull-right text-muted small"><em>1</em>
                                    </span>
                            </a>
                            <a href="#" class="list-group-item">
                                <i class="fa fa-picture-o fa-fw"></i> Aantal foto's
                                <span class="pull-right text-muted small"><em>1</em>
                                    </span>
                            </a>
                            <a href="#" class="list-group-item">
                                <i class="fa fa-book fa-fw"></i> Aantal verhalen
                                <span class="pull-right text-muted small"><em>1</em>
                                    </span>
                            </a>
                            <a href="#" class="list-group-item">
                                <i class="fa fa-film fa-fw"></i> Aantal filmpjes
                                <span class="pull-right text-muted small"><em>1</em>
                                    </span>
                            </a>
                            <a href="#" class="list-group-item">
                                <i class="fa fa-user fa-fw"></i> Aantal bezoekers
                                <span class="pull-right text-muted small"><em>1</em>
                                    </span>
                            </a>
                            <a href="#" class="list-group-item">
                                <i class="fa fa-comment fa-fw"></i> Aantal reacties
                                <span class="pull-right text-muted small"><em>1</em>
                                    </span>
                            </a>
                        </div>
                    </div>
                    <!-- /.panel-body -->
                </div>
                <!-- /.panel -->
            </div>
            <!-- /.col-lg-4 -->
        </div>
        <!-- /.row -->
    </div>
    <!-- /#page-wrapper -->
</div>
<!-- /#wrapper -->

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

<!-- Function to handle edit buttons next to link item -->
<script>
    function bewerken(linkId) {
        window.location.href = "${pageContext.request.contextPath}/admin/links/bewerk/" + linkId;
    }
</script>

</body>
</html>