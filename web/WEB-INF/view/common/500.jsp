<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta charset="UTF-8">
    <title>系统错误</title>
    <link rel="stylesheet" type="text/css"
          href="${path}/resources/lib/bootstrap/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="${path}/resources/css/match/style.1.0.css"/>
    <link rel="stylesheet" type="text/css" href="${path}/resources/css/common/style.css"/>
    <link rel="stylesheet" type="text/css" href="${path}/resources/css/common/500.css"/>
</head>

<body>
<jsp:include page="head.jsp"/>
<div>
    <div class="div-500">
        <div>
            <img src="${path}/resources/img/common/500title.png"/>
        </div>
    </div>
    <div class="div-500">

        <img src="${path}/resources/img/common/500font.png"/>

    </div>
    <div class="div-500-2">
        <div>
            <img src="${path}/resources/img/common/500rocket.png"/>
        </div>
    </div>
    <!--<img src="img/common/500bg.png" style="width: 100%;" />-->

    <div class="div-500-under">
        <img src="${path}/resources/img/common/500under.png" style="width: 100%;">
    </div>
</div>

<script src="${path}/resources/lib/jquery/jquery-3.1.1.min.js"></script>
<script src="${path}/resources/lib/bootstrap/bootstrap.min.js"></script>
<script src="${path}/resources/lib/layer/layer.js"></script>
</body>

</html>