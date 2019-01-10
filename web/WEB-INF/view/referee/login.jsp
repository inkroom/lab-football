<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>裁判员登录</title>
    <link rel="stylesheet" href="${path}/resources/lib/bootstrap/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="${path}/resources/css/common/style.css">
    <link rel="stylesheet" type="text/css" href="${path}/resources/css/referee/public.css">
    <link rel="stylesheet" type="text/css" href="${path}/resources/css/referee/login.css"/>
</head>

<body>
<!--导航-->
<jsp:include page="../common/head.jsp?v=2"/>

<div class="container" style="padding: 0 15px">
    <div class="row">
        <div class="col-xs-12 text-center text-top">
            <h3 class="color font-weight">裁判员登录</h3>
        </div>
    </div>
    <div class="row text-center">
        <div class="col-xs-12 font-color invisible " id="msg">信息错误</div>
    </div>

    <!--用户名-->
    <div class="div-border div-bottom">
        <div class="row text-center  ">
            <div class="col-xs-3 col-xs-offset-1 none-padding">
                <img src="${path}/resources/img/referee/user.png" class="margin img-left margin-left"/>
            </div>
            <div class="col-xs-6 none-padding">
                <input type="text" id="username" value="${username}" class="input-border margin inpback"
                       placeholder="请输入用户名"/>
            </div>
        </div>
    </div>

    <!--密码-->
    <div class="div-border">
        <div class="row text-center">
            <div class="col-xs-3 col-xs-offset-1">
                <img src="${path}/resources/img/referee/password.png" class="margin1 img-left img-width"/>
            </div>
            <div class="col-xs-6 none-padding">
                <input type="password" id="password" value="${password}" class="input-border margin inpback"
                       placeholder="请输入密码"/>
            </div>
        </div>
    </div>
    <form class="margin-top">
        <div class="row">
            <div class="col-xs-6 ">
                <div class='checkbox div-right'>
                    <input type='checkbox' id='isRemember'  ${isRemember?'checked':''} >
                    <label for='isRemember' id="nopadding" class="color font-size1 none-padding">记住密码</label>
                </div>
            </div>
            <div class="col-xs-6">
                <div class='checkbox'>
                    <input type='checkbox' id='isAuto' ${isAuto?'checked':''}>
                    <label for='isAuto' id="nopadding" class="color font-size1">自动登录</label>
                </div>
            </div>
        </div>
    </form>

</div>


<div class="panel-distance div-btn">
    <input class="btn btn-success btn-lg center-block btn-style" type="button" style="outline: none" value="提交"
           id="submit">
</div>
<script src="${path}/resources/lib/jquery/jquery-3.1.1.min.js"></script>
<script src="${path}/resources/lib/bootstrap/bootstrap.min.js"></script>
<script src="${path}/resources/lib/layer_mobile/layer.js"></script>
<script src="${path}/resources/js/index.js"></script>
<script src="${path}/resources/js/referee/login.js"></script>
</body>
</html>
