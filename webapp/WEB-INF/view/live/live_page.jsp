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
    <link rel="stylesheet" href="${base_path}/resources/common/lib/Hui-iconfont/1.0.8/iconfont.css">
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
            <input type="hidden" id="rId" data-rid="${map.regularId}">
            <div class="live-team f-l text-c">
                <a><img src="${base_path}/${map.homeTeamBadgePach}" alt=""
                        class="avatar size-XXXL ml-10 box-shadow"></a>
                <a><h4 class="text-c c-white mt-30"><strong class="c-primary">主&nbsp;队:</strong>${map.homeTeamName}</h4>
                </a>
            </div>
            <div class="live-score f-l hui-fadein">
                <h1 class="text-c text-shadow"><span
                        id="l-score">${map.hNowScore}</span><strong>&nbsp;:&nbsp;</strong><span
                        id="r-score">${map.vNowScore}</span></h1>
                <h4 class="live-head-place c-success text-l text-c mt-20">${map.raceName}</h4>
                <!--<h5 class="c-orange text-c">在线人数：<span id="liveNumber"></span></h5>-->
                <h5 class="live-head-time c-999 text-r f-l mt-10 ml-20">开始时间：${map.startTime}</h5>
                <h5 class="live-head-time c-999 text-r f-r mt-10 mr-20">当前时间：<p id="showTime" style="display: inline;">
                    0000-00-00 00:00:00</p></h5>
            </div>
            <div class="live-team f-r text-c">
                <a><img src="${base_path}/${map.visitingTeamBadgePach}" alt=""
                        class="avatar size-XXXL ml-10 box-shadow"></a>
                <a><h4 class="text-c c-white mt-30"><strong class="c-error">客&nbsp;队:</strong>${map.visitingTeamName}
                </h4></a>
            </div>
        </div>
    </div>
    <div id="Prompt" style="display: none;" class="Huialert Huialert-error text-c f-20"><i class='Hui-iconfont mt-5'>&#xe6a6;</i>您的浏览器不支持自动直播，请升级到IE10、谷歌、火狐、360浏览器，或者手动刷新页面观看!
    </div>
    <div class="live-content pt-20 pb-20 mt-50 mb-50" id="msg-live">
        <c:if test="${not empty list}">
            <c:forEach items="${list}" var="data">
                <c:if test="${data.lvieType == 1}">
                    <div class="live-mod live-normal pos-r hui-fadeinB">
                        <em class="live-normal-em pos-a round"></em>
                        <div class="live-time f-20 pos-a  c-666"><strong>${data.liveTime}</strong></div>
                        <div class="live-normal-area">
                            <div class="live-text f-20 pb-20 c-666">
                                <div class="panel panel-default">
                                    <div class="panel-header "><strong class="f-20">普通信息</strong>></div>
                                    <div class="panel-body">${data.liveText}</div>
                                    <br/>
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
                                    <div class="panel-body">${data.liveText}</div>
                                    <br/>
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
                                    <div class="panel-body">${data.liveText}</div>
                                    <br/>
                                    <c:if test="${not empty data.liveImages}">
                                        <img src="${base_path}/${data.liveImages}">
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:if>
            </c:forEach>
        </c:if>
        <c:if test="${empty list}">
            <div class="live-mod live-warn pos-r hui-fadeinB">
                <em class="live-warn-em pos-a round"></em>
                <div class="live-time f-20 pos-a  c-warning"><strong>${data.liveTime}</strong></div>
                <div class="live-warn-area">
                    <div class="live-text f-20 pb-20 c-warning">
                        <strong>
                            暂无数据...
                        </strong><br>
                    </div>
                </div>
            </div>
        </c:if>
    </div>
</div>

</body>
<script type="application/javascript" src="${base_path}/resources/common/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="application/javascript" src="${base_path}/resources/common/static/h-ui/js/H-ui.min.js"></script>
<script type="application/javascript" src="${base_path}/resources/common/lib/layer/2.4/layer.js"></script>
<%--<script type="application/javascript" src="${base_path}/resources/common/static/h-ui/js/vue1.0.28.js"></script>--%>
<script type="application/javascript" src="${base_path}/resources/common/avalon/avalon.js"></script>
<script type="text/javascript">
    var websocket = null;
    var wsServer = 'ws://' + window.location.host + '<%=request.getContextPath()%>/live';
    //判断当前浏览器是否支持WebSocket
    if ('WebSocket' in window) {
//        alert(new WebSocket('we://live'));
        try {
            websocket = new WebSocket(wsServer);
        }catch (e){
            layer.alert('连接过多，请关闭浏览器重新打开该页面！');
//            console.log(e);
        }

    } else {
        $("#Prompt").css("display", "block");
    }

    $(function () {
        setInterval("getTime();", 1000); //每隔一秒执行一次
    })

    //取得系统当前时间
    function getTime() {

        var myDate = new Date();
        var date = myDate.toLocaleDateString();
        var hours = myDate.getHours();
        var minutes = myDate.getMinutes();
        var seconds = myDate.getSeconds();
        if (seconds < 10) {
            seconds = '0' + seconds;
        }
        $("#showTime").html(date + " " + hours + ":" + minutes + ":" + seconds); //将值赋给div
    }
</script>
<script src="${base_path}/resources/js/live/ReceiveWebSocket.js" type="text/javascript"></script>


</html>
