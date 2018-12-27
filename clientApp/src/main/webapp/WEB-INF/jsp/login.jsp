<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
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
    <link href="${pageContext.request.contextPath}/css/admin/pages/main.css" rel="stylesheet">
</head>
<body>
    <hr>
    <br>
    <script src="http://mymaplist.com/js/vendor/TweenLite.min.js"></script>
    <!-- This is a very simple parallax effect achieved by simple CSS 3 multiple backgrounds, made by http://twitter.com/msurguy -->

    <div class="container">
        <div class="row vertical-offset-100">
            <div class="col-md-4 col-md-offset-4">
                <div class="panel panel-default">
                    <div class="panel-heading" style="background-color: #ed6a5a;">
                        <h3 class="panel-title fontHeader insetHeaderWhite">Please sign in</h3>
                    </div>
                    <div class="panel-body">
                            <form id="login-form" method="post">
                                <fieldset>
                                <div class="form-group">
                                    <input class="form-control" type="text" name="username" id="username" placeholder="username" value="abc"/>
                                </div>
                                <div class="form-group">
                                    <input class="form-control" type="password" name="password" id="password" placeholder="password" value="abc"/>
                                </div>
                                <div class="checkbox">
                                    <label>
                                        <input name="remember" type="checkbox" value="Remember Me"> Remember Me
                                    </label>
                                </div>
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                <input class="btn btn-block btnDef backgroundBlue" type="submit" value="Login">
                                </fieldset>
                            </form>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
