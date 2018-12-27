<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>upload</title>
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
                        <h3 class="panel-title fontHeader insetHeaderWhite">Upload a picture</h3>
                    </div>
                    <div class="panel-body">

                        <img class="picture700x300" src="${pictureUrl}">
                        <form name="file-upload-form" id="file-upload-form" enctype="multipart/form-data">
                            <br>
                            <input type="file" name="files" id="files" multiple>
                            <br>
                            <button type="submit" id="submit-button">send</button>
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                        </form>

                    </div>
                </div>
            </div>
        </div>
    </div>
<script type="text/javascript">
    var form = document.getElementById("file-upload-form");
    var fileSelect = document.getElementById("files");
    var button = document.getElementById("submit-button");

    form.onsubmit = function(event) {
        event.preventDefault();

        // Update button text.
        button.innerHTML = 'Uploading...';

        var files = fileSelect.files;
        var formData = new FormData();

        for (var i = 0; i < files.length; i++) {
            var file = files[i];

            if (!file.type.match('image.*')) {
                continue;
            }

            formData.append('photos[]', file);
        }

        var xhr = new XMLHttpRequest();
        xhr.open('POST', '${destinationSocket}pictures', true);
        xhr.setRequestHeader("${_csrf.headerName}","${_csrf.token}")
        xhr.setRequestHeader("Authorization", "${auth}");
        xhr.onload = function () {
            if (xhr.status === 200) {
                // File(s) uploaded.
                button.innerHTML = 'Upload';
            } else {
                alert('An error occurred!');
            }
        };
        xhr.send(formData);

        window.location.replace('${redirectSocket}admin/pictures');
    }
</script>
</body>
</html>
