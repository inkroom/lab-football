<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>完善个人信息</title>
    <link rel="stylesheet" href="${path}/resources/lib/bootstrap/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="${path}/resources/css/referee/public.css">
    <link rel="stylesheet" type="text/css" href="${path}/resources/css/referee/perferInfo.css">
</head>
<body>
<jsp:include page="../common/head.jsp?v=2&title=完善个人信息"/>
<!--导航-->

<!--完善个人信息-->
<div class="panel-body ">
    <div class="container">
        <!--真实姓名-->
        <div class="row text-distance">
            <div class="col-xs-4 font-size2 color2">真实姓名：</div>
        </div>
        <div class="row text-distance">
            <div class="col-xs-12">
                <input type="text" class="font-size3 color1 bor-bottom  input-style" id="name" value=""/>
            </div>
        </div>

        <!--身份证号-->
        <div class="row text-distance">
            <div class="col-xs-4 font-size2 color2">身份证号：</div>
        </div>
        <div class="row text-distance">
            <div class="col-xs-12">
                <input type="number" id="card" class="font-size3 color1 bor-bottom input-style"/>
            </div>
        </div>

        <!--手机号码-->
        <div class="row  text-distance">
            <div class="col-xs-4 font-size2 color2">手机号码：</div>
        </div>
        <div class="row text-distance">
            <div class="col-xs-12">
                <input type="number" id="phone" class="font-size3 color1 bor-bottom input-style"/>
            </div>
        </div>

        <!--验证码-->
        <div class="row  text-distance">
            <div class="col-xs-4 font-size2 color2">验证码：</div>
        </div>
        <div class="row text-distance">
            <div class="col-xs-7">
                <input type="text" id="verification" class="font-size3 color1 bor-bottom input-style" />
            </div>
            <div class="col-xs-3 " >
                <input type="button" class="btn btn-md center-block bor-bottom btn-style" id="send" value="发送验证码" style="outline: none;" />
            </div>
        </div>
    </div>
</div>

<!--提交-->
<div>
    <input class="btn btn-success btn-md center-block sub-style" style="outline: none;" type="button" id="submit" value="提交">
</div>

<script src="${path}/resources/lib/jquery/jquery-3.1.1.min.js"></script>
<script src="${path}/resources/lib/bootstrap/bootstrap.min.js"></script>
<script src="${path}/resources/lib/layer_mobile/layer.js"></script>
<script src="${path}/resources/js/index.js"></script>
<script src="${path}/resources/js/referee/consummate.js"></script>

</body>
</html>
