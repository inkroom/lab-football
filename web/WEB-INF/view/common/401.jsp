<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta charset="UTF-8">
    <title>权限不足</title>
    <link rel="stylesheet" type="text/css"
          href="${path}/resources/lib/bootstrap/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="${path}/resources/css/common/style.css"/>
    <link rel="stylesheet" type="text/css" href="${path}/resources/css/common/401.css"/>
    <script type="text/javascript">
        var w = window.parent;

        while (w !== w.parent) {
            w = w.parent;
        }
        if (w !== window)
            w.location.reload();
    </script>
</head>

<body>
<jsp:include page="head.jsp"/>
<div class="div-401-title">
    <div>
        <img src="${path}/resources/img/common/401title.png"/>
    </div>
</div>
<div class="div-401-font">
    <div>
        <img src="${path}/resources/img/common/401font.png"/>

    </div>
    <div>
        <img src="${path}/resources/img/common/401rocket.png"/>
    </div>
</div>
<div class="div-401-ast">
    <img src="${path}/resources/img/common/401astronaut.png"/>
</div>
<div class="div-401-under">
    <img src="${path}/resources/img/common/401under.png" style="width: 100%;">
</div>
<script src="${path}/resources/lib/jquery/jquery-3.1.1.min.js"></script>
<script src="${path}/resources/lib/bootstrap/bootstrap.min.js"></script>
<script src="${path}/resources/lib/layer/layer.js"></script>
</body>

</html>