<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Admin | Berichtjes van: </title>
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

        <%@include file="adminNavigation.jsp"%>

        <!-- Navigation -->
        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header fontHeader">Dashboard</h1>
                </div>
            </div>
            <!-- /.row -->

            <div class="row">
                <div class="col-lg-8">
                    <c:if test="${not empty statusMsg}">
                        <div class="alert ${statusMsg.key ? 'alert-success ' : 'alert-danger '} alert-dismissable">
                            ${statusMsg.value}
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                        </div>
                    </c:if>
                    <!-- Collapseable panels most recent trips/stories/comments -->
                    <div class="panel-group" id="accordion">
                        <!-- Collapseable panel most recent trips-->
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <!--<div class="pull-left"> -->
                                    <h4 class="panel-title">
                                        <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" class="collapseHeader fontHeader">Contact Info</a>
                                    </h4>
                                <!--</div>-->
                            </div>
                            <div id="collapseOne" class="panel-collapse collapse in">
                                <div class="panel-body noPadding">
                                    <div class="list-group">

                                            <span id="listGroupTrips${trip.id}" class="list-group-item noPadding ${cssVisibility}">
                                                <div class="flex-parent-row">
                                                    <div class="flex-child-start">
                                                        <p class="flex-center-vertical truncate-text" style="margin: 5px 0 0 5px;">Naam: ${contact.name}</p>
                                                    </div>
                                                    <div class="flex-child-start btn-group" role="group">
                                                    </div>
                                                </div>
                                            </span>



                                    <span id="listGroupTrips${trip.id}" class="list-group-item noPadding ${cssVisibility}">
                                        <div class="flex-parent-row">
                                         <div class="flex-child-start">
                                          <p class="flex-center-vertical truncate-text" style="margin: 5px 0 0 5px;">Email: ${contact.eMail}</p>
                                          </div>
                                          <div class="flex-child-start btn-group" role="group">
                                          </div>
                                         </div>
                                      </span>





                                   <span id="listGroupTrips${trip.id}" class="list-group-item noPadding ${cssVisibility}">
                                      <div class="flex-parent-row">
                                        <div class="flex-child-start">
                                           <p class="flex-center-vertical truncate-text" style="margin: 5px 0 0 5px;">Bericht: ${contact.body}</p>
                                          </div>
                                          <div class="flex-child-start btn-group" role="group">
                                          </div>
                                        </div>
                                      </span>

                                      </div>
                                </div>
                            </div>
                        </div>
                        <!-- End collapseable panel most recent trips-->
                    </div>
                    <!-- End collapseable panels most recent trips/stories/comments -->
                </div>
               
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

</body>
</html>
