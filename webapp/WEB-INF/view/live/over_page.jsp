<%--
  Created by IntelliJ IDEA.
  User: yrge
  Date: 2017/4/21
  Time: 16:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-Frame-Options" content="deny">
    <title>赛事直播</title>
    <link rel="stylesheet" href="${base_path}/resources/common/static/h-ui/css/H-ui.reset.css">
    <link rel="stylesheet" href="${base_path}/resources/common/static/h-ui/css/H-ui.min.css">
    <link rel="stylesheet" href="${base_path}/resources/css/live/livePage.css">
    <link rel="stylesheet" href="${base_path}/resources/common/static/h-ui/css/laypage.css">
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${base_path}/resources/common/lib/html5shiv.js"></script>
    <script type="text/javascript" src="${base_path}/resources/common/lib/respond.min.js"></script>
    <script type="text/javascript" src="${base_path}/resources/common/lib/PIE_IE678.js"></script>
    <![endif]-->
</head>
<body>
<div class="livePage box-shadow">
    <div class="live-wrap ">
        <div class="live-head">
            <h2 class="live-head-title c-white">${map.competitionName}</h2>
        </div>
        <div class="live-info">
            <div class="live-team f-l text-c">
                <a><img src="${base_path}/${map.homeTeamBadgePach}" alt="" class="avatar size-XXXL ml-10 box-shadow"></a>
                <a><h4 class="text-c c-white mt-30"><strong class="c-primary">主&nbsp;队:</strong>${map.homeTeamName}</h4></a>
            </div>
            <div class="live-score f-l hui-fadein">
                <h1 class="text-c text-shadow">${map.hNowScore}<strong>&nbsp;:&nbsp;</strong>${map.vNowScore}</h1>
                <h4 class="live-head-place c-success text-l text-c mt-20">${map.raceName}</h4>
                <h5 class="live-head-time c-999 text-r f-l mt-20 ml-40">开始时间：${map.startTime}</h5>
                <h5 class="live-head-time c-999 text-r f-r mt-20 mr-40">结束时间：${map.endTime}</h5>
            </div>
            <div class="live-team f-r text-c">
                <a><img src="${base_path}/${map.visitingTeamBadgePach}" alt="" class="avatar size-XXXL ml-10 box-shadow"></a>
                <a><h4 class="text-c c-white mt-30"><strong class="c-error">客&nbsp;队:</strong>${map.visitingTeamName}</h4></a>
            </div>
        </div>
    </div>

    <div class="live-content pt-20 pb-20 mt-50 mb-50">
        <c:forEach items="${list}" var="data">
            <c:if test="${data.lvieType == 1}">
                <div class="live-mod live-normal pos-r hui-fadeinB">
                    <em class="live-normal-em pos-a round"></em>
                    <div class="live-time f-20 pos-a  c-666"><strong>${data.liveTime}</strong></div>
                    <div class="live-normal-area">
                        <div class="live-text f-20 pb-20 c-666">
                            <div class="panel panel-default">
                                <div class="panel-header "><strong class="f-20">普通信息</strong>></div>
                                <div class="panel-body">${data.liveText}</div><br/>
                                <c:if test="${not empty data.liveImages}">
                                    <img src="${base_path}/${data.liveImages}">
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>
            <c:if test="${data.lvieType == 2}">


                <div class="live-mod live-red pos-r hui-fadeinB">
                <em class="live-red-em pos-a round"></em>
                <div class="live-time f-20 pos-a  c-red"><strong>${data.liveTime}</strong></div>
                <div class="live-red-area">
                <div class="live-text f-20 pb-20 c-red">
                    <div class="panel panel-danger">
                        <div class="panel-header "><strong class="f-20">判罚信息</strong>></div>
                        <div class="panel-body">${data.liveText}</div><br/>
                        <c:if test="${not empty data.liveImages}">
                            <img src="${base_path}/${data.liveImages}">
                        </c:if>
                    </div>
                </div>
                </div>
                </div>
            </c:if>
            <c:if test="${data.lvieType == 3}">
                <div class="live-mod live-goal pos-r hui-fadeinB">
                    <em class="live-goal-em pos-a round"></em>
                    <div class="live-time f-20 pos-a  c-success"><strong>${data.liveTime}</strong></div>
                    <div class="live-goal-area">
                        <div class="live-text f-20 pb-20 c-success">
                            <div class="panel panel-success">
                                <div class="panel-header "><strong class="f-20">进球信息</strong>></div>
                                <div class="panel-body">${data.liveText}</div><br/>
                                <c:if test="${not empty data.liveImages}">
                                    <img src="${base_path}/${data.liveImages}">
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>
        </c:forEach>

    </div>
</div>

</body>
<script type="application/javascript" src="${base_path}/resources/common/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="application/javascript" src="${base_path}/resources/common/static/h-ui/js/H-ui.min.js"></script>
<script type="application/javascript" src="${base_path}/resources/common/lib/layer/2.4/layer.js"></script>

<script type="text/javascript">
    function myprependTo(){

        $("#new").prependTo(".live-content");   //将id为new的div 添加到  类为live-content的div 内部的第一个
    }

</script>
</html>
