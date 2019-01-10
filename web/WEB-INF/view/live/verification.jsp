<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>手机认证</title>
    <link rel="stylesheet" href="${path}/resources/lib/bootstrap/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="${path}/resources/css/referee/public.css">
    <link rel="stylesheet" type="text/css" href="${path}/resources/css/referee/phontVerification.css">
</head>

<body>
<jsp:include page="../common/head.jsp?v=2&title=手机验证"/>

<!--中间-->
<div class="panel-body ">
    <div class="container">
        <!--手机号码-->
        <div class="row text-distance">
            <div class="col-xs-4 font-size3 color2" style="display: block;width: 100%">手机号码：</div>
        </div>
        <div class="row text-distance">
            <div class="col-xs-12">
                <input type="text" id="phone" class="font-size4 color1 bor-bottom input-style"/>
            </div>
        </div>

        <!--验证码-->
        <div class="row text-distance">
            <div class="col-xs-4 font-size3 color2" style="display: block;width: 100%">验证码：</div>
        </div>
        <div class="row text-distance">
            <div class="col-xs-7">
                <input type="text" id="validate" class="font-size4 color1 bor-bottom input-style"/>
            </div>
            <div class="col-xs-3">
                <input type="button" class="btn btn-md center-block bor-bottom btn-style" id="send" onclick="send()"
                       value="发送验证码" style="outline: none;"/>
            </div>
        </div>

    </div>
</div>

<!--提交-->
<div>
    <input class="btn btn-success btn-md center-block sub-style" style="outline: none;" type="button" id="submit"
           value="提交">
</div>

<!--跳过-->
<div class="container">
    <div class="row">
        <div class="col-xs-12 text-right panel-distance">
            <a href="m/index#hash" id="skip">跳过&nbsp;>></a>
        </div>
    </div>
</div>

<script src="${path}/resources/lib/jquery/jquery-3.1.1.min.js"></script>
<script src="${path}/resources/lib/bootstrap/bootstrap.min.js"></script>
<script src="${path}/resources/lib/layer_mobile/layer.js"></script>
<script src="${path}/resources/js/index.js"></script>
<script src="${path}/resources/js/live/verification.js"></script>

</body>
</html>
