<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>nieuw kiekjes album</title>
</head>
<body>

    <div class="container">
        <div class="col-md-4 col-md-offset-4">
            <form name="file-upload-form" id="file-upload-form" enctype="multipart/form-data">
                <input type="text" name="title" id="album-title">
                <input type="file" name="files" id="files" multiple>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                <button type="submit" id="submit-button">send</button>
            </form>
        </div>
    </div>

    <script type="text/javascript">
        var form = document.getElementById("file-upload-form");
        var fileSelect = document.getElementById("files");
        var button = document.getElementById("submit-button");
        var title = document.getElementById("album-title");

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

                formData.append('photos[]', file, file.name);
            }

            formData.append("title", title.value);

            var xhr = new XMLHttpRequest();
            xhr.open('POST', '${destinationSocket}upload', true);
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
        }
    </script>
</body>
</html>
