<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1.0, user-scalable=0">
    <title>修改分数</title>
    <link rel="stylesheet" type="text/css" href="${path}/resources/lib/bootstrap/bootstrap.min.css"/>
    <%--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">--%>
    <link rel="stylesheet" type="text/css" href="${path}/resources/css/referee/public.css">
    <link rel="stylesheet" type="text/css" href="${path}/resources/css/referee/score.css"/>

</head>

<body>
<jsp:include page="../common/head.jsp?v=2&title=修改比分"/>
<!--内容-->

<!--修改后-->
<div class="panel-body ">
    <div class="container">
        <!--赛事内容-->
        <div class="row">
            <span class="font-size1">赛事内容</span>
        </div>
        <!--比赛名称-->
        <div class="row margin-top1">
            <div class="col-xs-3 none-padding">
                <span class="color1 font-size2">比赛名称：</span>
            </div>
            <span class="color2 font-size3">${match.name}</span>
        </div>

        <!--比赛队伍-->
        <div class="row margin-top1">
            <div class="col-xs-3 none-padding">
                <span class="color1 font-size2">比赛队伍：</span>
            </div>
            <div class="col-xs-9">
                <div class="row">
                    <span class="color2 font-size3">${schedule.teamA.name}</span>
                    <img src="${path}/resources/img/referee/vs.png" class="img-style1"/>
                    <span class="color2 font-size3">${schedule.teamB.name}</span>
                </div>
            </div>
        </div>

        <!--比赛时间-->
        <div class="row margin-top1">
            <div class="col-xs-3 none-padding">
                <span class="color1 font-size2">比赛时间：</span>
            </div>
            <span class="color2 font-size3"><fmt:formatDate
                    value="${schedule.scheduleInformation.beginTime}" pattern="yyyy/MM/dd HH:mm"/></span>
        </div>

        <!--比赛情况-->
        <div class="row margin-top2">
            <span class="font-size1">比赛情况</span>
        </div>

        <!--修改比分-->
        <div class="row margin-top1">
            <div class="col-xs-6 text-center">
                <span class="color2 font-size3">${schedule.teamA.name}</span>
            </div>
            <div class="col-xs-6 text-center">
                <span class="color2 font-size3">${schedule.teamB.name}</span>
            </div>
        </div>


    </div>
</div>
<!--修改比分-->
<div class="container">

    <div class="row text-center">
        <!--主队-->
        <div class="col-xs-1 col-xs-offset-1 up-height none-padding div-border">
            <img class="img-style2" id="downscore1" src="${path}/resources/img/referee/mul.png"/>
        </div>
        <div class="col-xs-2 input-height none-padding">
            <input type="text" class="form-control text-center input-style" id="score1"
                   value="${schedule.scheduleInformation.goalA}">
        </div>
        <div class="col-xs-1 up-height none-padding div-border">
            <img class="img-style2" id="upscore1" src="${path}/resources/img/referee/add1.png"/>
        </div>

        <div class="col-xs-2 col-center font-weight">:</div>

        <div class="col-xs-1 up-height none-padding div-border">
            <img class="img-style2" id="downscore2" src="${path}/resources/img/referee/mul.png"/>
        </div>
        <div class="col-xs-2 input-height none-padding">
            <input type="text" class="form-control text-center input-style" id="score2"
                   value="${schedule.scheduleInformation.goalB}">
        </div>
        <div class="col-xs-1 up-height none-padding div-border">
            <img class="img-style2" id="upscore2" src="${path}/resources/img/referee/add1.png"/>
        </div>

    </div>

</div>
<!--按钮-->
<div class="container margin-top2" style="margin-bottom: 100px">
    <div class="col-xs-6">
        <input class="btn btn-danger btn-md center-block width" style="outline: none;" type="button" value="结束比赛"
               id="finish">
    </div>
    <div class="col-xs-6">
        <input class="btn btn-success btn-md center-block width" style="outline: none;" type="button" value="提交比分"
               id="confirm">
    </div>
</div>
<jsp:include page="bar.jsp"/>

<script src="${path}/resources/lib/jquery/jquery-3.1.1.min.js"></script>
<script src="${path}/resources/lib/bootstrap/bootstrap.min.js"></script>
<script src="${path}/resources/lib/layer_mobile/layer.js"></script>
<script src="${path}/resources/js/index.js"></script>
<script src="${path}/resources/js/referee/score.js"></script>
</body>
</html>
