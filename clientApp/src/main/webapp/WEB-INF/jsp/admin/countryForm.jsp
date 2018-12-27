<%--
  Created by IntelliJ IDEA.
  User: DieterB
  Date: 16/11/2017
  Time: 13:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<!-- Add/Alter country-->
<div class="panel panel-default">
    <div class="panel-heading fontHeader">
        <i class="fa fa-info fa-fw"></i> Land beheer
    </div>
    <div class="panel-body">
        <div class="list-group">
            <%--@elvariable id="countryForm" type=""--%>
            <form:form commandName="countryForm" method="post">
                <div class="row">
                    <button class="col-6 btn">Update</button>
                    <button class="col-6 btn">Voeg toe</button>
                </div>
                <form:input path="name" value="Land benaming..." />
                <form:input path="flagUrl" value="Url naar een .." />
            </form:form>
        </div>
    </div>
    <!-- / Add/Alter country-->
</div>
</body>
</html>
