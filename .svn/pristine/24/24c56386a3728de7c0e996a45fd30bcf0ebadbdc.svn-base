<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
    <title>后台首页</title>
    <link rel="stylesheet" href="${path}/resources/lib/bootstrap/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="${path}/resources/css/referee/public.css">
    <link rel="stylesheet" type="text/css" href="${path}/resources/css/referee/index.css">

</head>
<body>
<jsp:include page="../common/head.jsp?v=2&title=后台首页"/>


<!--中间-->

<div class="panel-body ">
    <div class="container">
        <!--赛事内容-->
        <div class="row">
            <div class="col-xs-12">
                <span class="font-size2">今日赛事</span>
            </div>
        </div>
        <div class="row text-center div-top">
            <div class="col-xs-6 color1 font-size3">主队</div>
            <div class="col-xs-6 color1 font-size3">客队</div>
        </div>
        <div class="text-center div-top">
            <span class="color font-size4">${dto.teamA.name}</span>
            <img src="${path}/resources/img/referee/vs.png" class="img-style1"/>
            <span class="color font-size4">${dto.teamB.name}</span>
        </div>

        <%----%>
        <!--比赛时间-->
        <div class="row div-top1">
            <div class="col-xs-5 none-padding font-style">
                <span class="color1 font-size3">比赛时间：</span>
            </div>
            <span class="color font-size5"> <fmt:formatDate value="${dto.scheduleInformation.beginTime}"
                                                            pattern="yyyy/MM/dd HH:mm"/></span>
        </div>

        <!--比赛地点-->
        <div class="row div-top2">
            <div class="col-xs-5 none-padding font-style">
                <span class="color1 font-size3">比赛地点：</span>
            </div>
            <span class="color font-size5">${dto.scheduleInformation.place}</span>
        </div>
    </div>
</div>
<jsp:include page="bar.jsp"/>
<script src="${path}/resources/lib/jquery/jquery-3.1.1.min.js"></script>
<script src="${path}/resources/lib/bootstrap/bootstrap.min.js"></script>
</html>
