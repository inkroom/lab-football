<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: inkbox
  Date: 18-3-21
  Time: 下午3:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>直播</title>
    <link rel="stylesheet" href="${path}/resources/lib/bootstrap/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="${path}/resources/css/common/style.css">
    <link rel="stylesheet" type="text/css" href="${path}/resources/css/referee/public.css">
    <link rel="stylesheet" type="text/css" href="${path}/resources/css/live/livePC.css">
</head>

<body>
<!--导航-->
<jsp:include page="../common/head.jsp"/>


<!--内容-->
<div class="container div-width">
    <div class="row"></div>
    <div class="row text-left">
        <div class="span6 col-xs-6 div-style1">
            正文
        </div>
        <div class="span6 col-xs-6 div-style1">
            列表
        </div>
    </div>
    <div class="row">
        <div class="col-xs-6 div-height">
            <!--比赛进程-->
            <div class="div-color">
                <div class="row">
                    <div class="col-xs-12 text-center">
                        <span id="status">${(schedule.scheduleInformation.status eq 2)?'比赛已结束':'正在比赛'}</span>
                    </div>
                </div>
                <!--比赛队伍-->
                <div class="div-style2 margin-top1">
                    <div class="text-center float-left margin-left1 width1">
                        <div class="color1">${schedule.teamA.name}</div>
                        <div class="color1 font-size1" id="scoreA">${schedule.scheduleInformation.goalA}</div>
                    </div>
                    <div class="float-left width1 div-height1">
                        <img src="${path}/resources/img/referee/vs.png" class="margin-top2 width2 margin1"/>
                    </div>
                    <div class="text-center float-left width1">
                        <div class="color1 font-size1">${schedule.teamB.name}</div>
                        <div class="color1 font-size1" id="scoreB">${schedule.scheduleInformation.goalB}</div>
                    </div>
                </div>
            </div>


            <!--左下-->
            <div id="message">
                <hr class="margin2"/>
                <c:if test="${!empty(message)}">
                    <!--用户名-->
                    <div class="div-height2 margin-top2">
                        <div class="width3 float-left">
                            <img src="${path}/resources/img/live/user1.png"/>
                        </div>
                        <div class="padding-top1">
                            <span class="color1">${message.phone}</span>
                            <span class="color1 float-right"><fmt:formatDate
                                    value="${message.time}" pattern="HH:mm:ss"/></span>
                        </div>
                    </div>
                    <c:if test="${!empty(message.content)}">
                        <!--文字-->
                        <div class="row margin-top2">
                            <div class="col-xs-8 col-xs-offset-2 none-padding color1 font-size2">
                                    ${message.content}
                            </div>
                        </div>
                    </c:if>
                    <fmt:setBundle basename="properties.upload"/>
                    <c:if test="${!empty(message.imgPath)}">
                        <!--图片-->
                        <div class="row text-center margin-top2">
                            <div class="col-xs-10 col-xs-offset-1">
                                <img src="${path}/<fmt:message key="upload.image.url.prefix"/>/${message.imgPath}"/>
                            </div>
                        </div>
                    </c:if>
                </c:if>
            </div>
        </div>

        <!--右边-->
        <div class="col-xs-6">
            <div class="row div-height div-over color2">
                <div class="col-xs-12 " id="messages">
                    <c:forEach var="m" items="${messages}" varStatus="status">
                        <c:if test="${!status.first}">
                            <hr class="margin2"/>
                        </c:if>
                        <c:choose>
                            <c:when test="${(!empty(m.content))&&(!m.content.isEmpty())&&empty(m.imgPath)}">
                                <!--纯文字-->
                                <div>
                                    <!--用户名-->
                                    <div class="div-height2 margin-top2">
                                        <div class="width3 float-left">
                                            <img src="${path}/resources/img/live/user1.png"/>
                                        </div>
                                        <div class="padding-top1">
                                            <span class="color1">${m.phone}</span>
                                            <span class="color1 float-right"><fmt:formatDate
                                                    value="${m.time}" pattern="HH:mm:ss"/></span>
                                        </div>
                                    </div>
                                    <!--文字-->
                                    <div class="row margin-top2">
                                        <div class="col-xs-8 col-xs-offset-2 none-padding color1 font-size2">
                                                ${m.content}
                                        </div>
                                    </div>
                                </div>
                            </c:when>
                            <c:when test="${(empty(m.content)||m.content.isEmpty())&&(!empty(m.imgPath))}">
                                <!--纯图片-->
                                <div>
                                    <!--用户名-->
                                    <div class="div-height2 margin-top2">
                                        <div class="width3 float-left">
                                            <img src="${path}/resources/img/live/user1.png"/>
                                        </div>
                                        <div class="padding-top1">
                                            <span class="color1">${m.phone}</span>
                                            <span class="color1 float-right"><fmt:formatDate
                                                    value="${m.time}" pattern="HH:mm:ss"/></span>
                                        </div>
                                    </div>
                                    <!--图片-->
                                    <div class="row text-center margin-top2">
                                        <div class="col-xs-10 col-xs-offset-1">
                                            <img src="${path}/<fmt:message key="upload.image.url.prefix"/>/${m.imgPath}"/>
                                        </div>
                                    </div>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <!--图文-->
                                <p>图文${m.imgPath}</p>
                                <div>
                                    <!--用户名-->
                                    <div class="div-height2 margin-top2">
                                        <div class="width3 float-left">
                                            <img src="${path}/resources/img/live/user1.png"/>
                                        </div>
                                        <div class="padding-top1">
                                            <span class="color1">${m.phone}</span>
                                            <span class="color1 float-right"><fmt:formatDate
                                                    value="${m.time}" pattern="HH:mm:ss"/></span>
                                        </div>
                                    </div>
                                    <!--文字-->
                                    <div class="row margin-top2">
                                        <div class="col-xs-8 col-xs-offset-2 none-padding color1 font-size2">
                                                ${m.content}
                                        </div>
                                    </div>
                                    <!--图片-->
                                    <div class="row text-center margin-top2">
                                        <div class="col-xs-10 col-xs-offset-1">
                                            <img src="${path}/<fmt:message key="upload.image.url.prefix"/>/${m.imgPath}"/>
                                        </div>
                                    </div>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</div>


<script src="${path}/resources/lib/jquery/jquery-3.1.1.min.js"></script>
<script src="${path}/resources/lib/bootstrap/bootstrap.min.js"></script>
<script src="${path}/resources/lib/layer/layer.js"></script>
<script src="${path}/resources/js/index.js"></script>
<c:if test="${!(schedule.scheduleInformation.status eq 2)}">
    <script type="text/javascript">
        $(function () {
            var reConnTime = 0;

            function getSch() {
                var regex = new RegExp('live/([1-9]+[0-9]*)/');
                return parseInt(regex.exec(location.pathname)[1]);
            }

            function transDate(value) {
                var date = new Date(value);
                return (date.getHours() < 10 ? ('0' + date.getHours()) : date.getHours()) + ':' +
                    ((date.getMinutes()) < 10 ? ('0' + date.getMinutes()) : (date.getMinutes())) + ':' +
                    (date.getSeconds() < 10 ? ('0' + date.getSeconds()) : date.getSeconds());
            }

            function createWebSocket() {
                var $message = $('#message');
                var $messages = $('#messages');
                var socket = new WebSocket('ws://' + location.host + '${path}/test');
                socket.onmessage = function (evt) {
                    var message = JSON.parse(evt.data);
                    if (message.status === 0 && message.data.schId === getSch()) {
                        if (message.data.type === 1) {//消息
                            <fmt:bundle basename="properties.upload">
                            if ((message.data.message.imgPath === null || message.data.message.imgPath === '') && message.data.message.content !== null && message.data.message.content !== '') {
                                //文字
                                $message.html('<hr class="margin2"/>' +
                                    '<!--用户名-->\n' +
                                    '                <div class="div-height2 margin-top2">\n' +
                                    '                    <div class="width3 float-left">\n' +
                                    '                        <img src="${path}/resources/img/live/user1.png"/>\n' +
                                    '                    </div>\n' +
                                    '                    <div class="padding-top1">\n' +
                                    '                        <span class="color1">' + message.data.message.phone + '</span>\n' +
                                    '                        <span class="color1 float-right">' + transDate(message.data.message.time) + '</span>\n' +
                                    '                    </div>\n' +
                                    '                </div>'
                                    + ' <!--文字-->\n' +
                                    '                    <div class="row margin-top2">\n' +
                                    '                        <div class="col-xs-8 col-xs-offset-2 none-padding color1 font-size2">\n' +
                                    '                                ' + message.data.message.content + '\n' +
                                    '                        </div>\n' +
                                    '                    </div>');

                                if ($messages.find('hr').length !== 0)
                                    $messages.append('<hr class="margin2"/>');

                                $messages.append('<!--纯文字-->\n' +
                                    '                                <div>\n' +
                                    '                                    <!--用户名-->\n' +
                                    '                                    <div class="div-height2 margin-top2">\n' +
                                    '                                        <div class="width3 float-left">\n' +
                                    '                                            <img src="${path}/resources/img/live/user1.png"/>\n' +
                                    '                                        </div>\n' +
                                    '                                        <div class="padding-top1">\n' +
                                    '                                            <span class="color1">' + message.data.message.phone + '</span>\n' +
                                    '                                            <span class="color1 float-right">' + transDate(message.data.message.time) + '</span>\n' +
                                    '                                        </div>\n' +
                                    '                                    </div>\n' +
                                    '                                    <!--文字-->\n' +
                                    '                                    <div class="row margin-top2">\n' +
                                    '                                        <div class="col-xs-8 col-xs-offset-2 none-padding color1 font-size2">\n' +
                                    '                                                ' + message.data.message.content + '\n' +
                                    '                                        </div>\n' +
                                    '                                    </div>\n' +
                                    '                                </div>');
                            }
                            else if ((message.data.message.imgPath !== null && message.data.message.imgPath !== '' && (message.data.message.content === null || message.data.message.content === ''))) {//图片
                                $message.html('<hr class="margin2"/>' +
                                    '<!--用户名-->\n' +
                                    '                <div class="div-height2 margin-top2">\n' +
                                    '                    <div class="width3 float-left">\n' +
                                    '                        <img src="${path}/resources/img/live/user1.png"/>\n' +
                                    '                    </div>\n' +
                                    '                    <div class="padding-top1">\n' +
                                    '                        <span class="color1">' + message.data.message.phone + '</span>\n' +
                                    '                        <span class="color1 float-right">' + transDate(message.data.message.time) + '</span>\n' +
                                    '                    </div>\n' +
                                    '                </div>'
                                    + ' <!--图片-->\n' +
                                    '                    <div class="row text-center margin-top2">\n' +
                                    '                        <div class="col-xs-10 col-xs-offset-1">\n' +
                                    '                            <img src="${path}/<fmt:message key="upload.image.url.prefix"/>/' + message.data.message.imgPath + '"/>\n' +
                                    '                        </div>\n' +
                                    '                    </div>');
                                if ($messages.find('hr').length !== 0)
                                    $messages.append('<hr class="margin2"/>');

                                $messages.append('<!--纯图片-->\n' +
                                    '                                <div>\n' +
                                    '                                    <!--用户名-->\n' +
                                    '                                    <div class="div-height2 margin-top2">\n' +
                                    '                                        <div class="width3 float-left">\n' +
                                    '                                            <img src="${path}/resources/img/live/user1.png"/>\n' +
                                    '                                        </div>\n' +
                                    '                                        <div class="padding-top1">\n' +
                                    '                                            <span class="color1">' + message.data.message.phone + '</span>\n' +
                                    '                                            <span class="color1 float-right">' + transDate(message.data.message.time) + '</span>\n' +
                                    '                                        </div>\n' +
                                    '                                    </div>\n' +
                                    '                                    <!--图片-->\n' +
                                    '                                    <div class="row text-center margin-top2">\n' +
                                    '                                        <div class="col-xs-10 col-xs-offset-1">\n' +
                                    '                                            <img src="${path}/<fmt:message key="upload.image.url.prefix"/>/' + message.data.message.imgPath + '"/>\n' +
                                    '                                        </div>\n' +
                                    '                                    </div>\n' +
                                    '                                </div>');
                            } else {//图文
                                $message.html('<hr class="margin2"/>' +
                                    '<!--用户名-->\n' +
                                    '                <div class="div-height2 margin-top2">\n' +
                                    '                    <div class="width3 float-left">\n' +
                                    '                        <img src="${path}/resources/img/live/user1.png"/>\n' +
                                    '                    </div>\n' +
                                    '                    <div class="padding-top1">\n' +
                                    '                        <span class="color1">' + message.data.message.phone + '</span>\n' +
                                    '                        <span class="color1 float-right">' + transDate(message.data.message.time) + '</span>\n' +
                                    '                    </div>\n' +
                                    '                </div>' +
                                    '<!--文字-->\n' +
                                    '                            <div class="row margin-top2">\n' +
                                    '                                <div class="col-xs-8 col-xs-offset-2 none-padding color1 font-size2">\n' +
                                    '                                       ' + message.data.message.content + '\n' +
                                    '                                </div>\n' +
                                    '                            </div>'
                                    + ' <!--图片-->\n' +
                                    '                    <div class="row text-center margin-top2">\n' +
                                    '                        <div class="col-xs-10 col-xs-offset-1">\n' +
                                    '                            <img src="${path}/<fmt:message key="upload.image.url.prefix"/>/' + message.data.message.imgPath + '"/>\n' +
                                    '                        </div>\n' +
                                    '                    </div>');

                                if ($messages.find('hr').length !== 0)
                                    $messages.append('<hr class="margin2"/>');

                                $messages.append('<!--图文-->\n' +
                                    '                                <div>\n' +
                                    '                                    <!--用户名-->\n' +
                                    '                                    <div class="div-height2 margin-top2">\n' +
                                    '                                        <div class="width3 float-left">\n' +
                                    '                                            <img src="${path}/resources/img/live/user1.png"/>\n' +
                                    '                                        </div>\n' +
                                    '                                        <div class="padding-top1">\n' +
                                    '                                            <span class="color1">' + message.data.message.phone + '</span>\n' +
                                    '                                            <span class="color1 float-right">' + transDate(message.data.message.time) + '</span>\n' +
                                    '                                        </div>\n' +
                                    '                                    </div>\n' +
                                    '                                    <!--文字-->\n' +
                                    '                                    <div class="row margin-top2">\n' +
                                    '                                        <div class="col-xs-8 col-xs-offset-2 none-padding color1 font-size2">\n' +
                                    '                                                ' + message.data.message.content + '\n' +
                                    '                                        </div>\n' +
                                    '                                    </div>\n' +
                                    '                                    <!--图片-->\n' +
                                    '                                    <div class="row text-center margin-top2">\n' +
                                    '                                        <div class="col-xs-10 col-xs-offset-1">\n' +
                                    '                                            <img src="${path}/<fmt:message key="upload.image.url.prefix"/>/' + message.data.message.imgPath + '"/>\n' +
                                    '                                        </div>\n' +
                                    '                                    </div>\n' +
                                    '                                </div>');
                            }
                            </fmt:bundle>
                            // scroll();
                        } else if (message.data.type === 2) {//状态改变
                            $('#status').html('已结束');
                        } else if (message.data.type === 3) {//比分修改
                            $('#scoreA').html(message.data.scoreA);
                            $('#scoreB').html(message.data.scoreB);
                        }

                    }
                }
                socket.onclose = function (ev) {
                    if (reConnTime >= 3) {
                        msg('连接关闭');
                        $('#status').html('连接关闭');
                    } else {
                        createWebSocket();
                        reConnTime++;
                    }

                }
                socket.onerror = function (ev) {
                    msg('连接中断，刷新重连')
                    $('#status').html('连接中断');
                }
            }

            //创建web socket
            if (!("WebSocket" in window)) {
                msg('您的浏览器版本过低，暂不支持观看直播')
            } else {
                createWebSocket();
            }
        })
    </script>
</c:if>
</body>

</html>