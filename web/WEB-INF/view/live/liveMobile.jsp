<%@ page import="cn.nsu.edu.web.four.config.BaseStatic" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1.0, user-scalable=0">
    <title>比赛直播</title>
    <link rel="stylesheet" type="text/css" href="${path}/resources/lib/bootstrap/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="${path}/resources/css/referee/public.css"/>
    <link rel="stylesheet" type="text/css" href="${path}/resources/css/live/liveMobile.css"/>
    <link rel="stylesheet" type="text/css" href="${path}/resources/lib/bootstrap/bootstrap-fileinput.css"/>
</head>

<body>
<% request.setAttribute("isVer", BaseStatic.KEY_SESSION_LIVE_PHONE);%>
<div id="box">
    <!--顶部标题-->
    <div id="header">
        <nav class="navbar bor-bottom font-size1 nav-style">
            <div class="container">
                <div class="row">
                    <div class="col-lg-4 col-md-5 col-sm-11 col-xs-12 text-center div-place">
                        <a href="#">
                            <img src="${path}/resources/img/referee/return.png" class="img-style float margin"/>
                        </a>
                        <span class=" color1  font-size4">评论圈</span>
                        <a href="javascript:${empty(sessionScope.get(requestScope.get('isVer')))?'location.href=\'../verification\'':''};" ${empty(sessionScope.get(requestScope.get('isVer')))?'':'data-toggle="modal" data-target="#myModal"'}>
                            <img src="${path}/resources/img/live/add.png"
                                 class="margin img-style1"   />
                        </a>
                    </div>
                </div>
            </div>
        </nav>
    </div>
    <p id="token" style="display: none;">${token}</p>
    <!--直播比分-->
    <div id="middle">
        <div class="panel-body div-height">
            <div class="container bor-bottom1">
                <!--比赛进程-->
                <div class="row">
                    <div class="col-xs-12 text-center">
                        <span id="status">${(info.scheduleInformation.status eq 2)?'比赛已结束':'正在比赛'}</span>
                    </div>
                </div>
                <!--比赛队伍-->
                <div class="div-style1">
                    <div class="text-center float div-style2 width">
                        <div class="color1 font-size2">${info.teamA.name}</div>
                        <div class="color1 font-size2" id="scoreA">${info.scheduleInformation.goalA}</div>
                    </div>
                    <div class="float div-style3 width">
                        <img src="${path}/resources/img/referee/vs.png" class="margin img-style2"/>
                    </div>
                    <div class="text-center float width">
                        <div class="color1 font-size2">${info.teamB.name}</div>
                        <div class="color1 font-size2" id="scoreB">${info.scheduleInformation.goalB}</div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div id="main">
        <div class="panel" id="wrap">
            <%--<div class="panel-heading text-center">--%>
            <%--<h3 class="panel-title">实时直播</h3>--%>
            <%--</div>--%>
            <div class="panel-body body-height" style="overflow-x: hidden;border-bottom: none;">
                <div class="container" id="messages">
                    <fmt:setBundle basename="properties.upload"/>
                    <c:forEach var="item" items="${messages}" varStatus="status">
                        <div>
                            <c:if test="${!status.first}">
                                <hr class="hr-style"/>
                            </c:if>
                            <!--用户名-->
                            <div class="height margin">
                                <div class="width1 float">
                                    <img src="${path}/resources/img/live/user1.png"/>
                                </div>
                                <div class="padding-top">
                                    <span class="color1">${item.phone}</span>
                                    <span class="color1 float-right"><fmt:formatDate value="${item.time}"
                                                                                     pattern="HH:mm:ss"/></span>
                                </div>
                            </div>
                            <c:if test="${!empty (item.content) && !item.content.isEmpty()}">
                                <!--文字-->
                                <div class="row margin">
                                    <div class="col-xs-8 col-xs-offset-2 none-padding color1 font-size3"> ${item.content} </div>
                                </div>
                            </c:if>
                            <c:if test="${!empty (item.imgPath)}">
                                <!--图片-->
                                <div class="row text-center margin">
                                    <div class="col-xs-10 col-xs-offset-1">
                                        <img src="${path}/<fmt:message key="upload.image.url.prefix"/>/${item.imgPath}"/>
                                    </div>
                                </div>
                            </c:if>
                        </div>

                    </c:forEach>
                    <div id="hash"></div>
                    <div id="nextB" style="display: none;"></div>
                </div>
            </div>
        </div>
    </div>
    <!--模态框-->
    <div class="modal fade"
         style="position: absolute;left:50%;top:50%;transform: translate(-50%,-50%); width: 100%;height: 90%;display: none"
         id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        &times;
                    </button>
                    <h4 class="modal-title text-center" id="myModalLabel">
                        发送框
                    </h4>
                </div>
                <div class="modal-body">
                    <div class="panel-body ">
                        <!--输入文字-->
                        <div class="row">
                            <div class="col-xs-12">
                                <textarea class="bor text-style font-size1" rows="4"
                                          placeholder="记录此刻精彩瞬间..." id="content"></textarea>
                            </div>
                        </div>

                        <!--上传图片-->
                        <form>
                            <div class="form-group" id="uploadForm" >
                                <div class="fileinput fileinput-new float" data-provides="fileinput"
                                     id="exampleInputUpload">
                                    <div id="hidden" style="display: none"></div>
                                    <!--图片-->
                                    <div class="fileinput-new thumbnail div-style4" id="imageBox">
                                        <img id='picImg' src="${path}/resources/img/live/img.png" alt=""/>
                                        <%--<input type="file" name="file" id="picID"--%>
                                               <%--accept="image/gif,image/jpeg,image/x-png" class="input-style"/>--%>
                                    </div>
                                    <div class="fileinput-preview fileinput-exists thumbnail div-style5"></div>
                                    <!--按钮-->
                                    <div class="float-right" id="del">
                                        <a href="javascript:;">
                                            <img src="${path}/resources/img/live/del.png" class="img-style3"/>
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal" id="hide" style="outline: none;">关闭
                    </button>
                    <button type="button" class="btn btn-primary" style="outline: none;" id="send">发送</button>
                </div>
            </div>
        </div>
    </div>

</div>
<input type="hidden" value="<fmt:message key="upload.image.url.prefix"/>" id="prefix">
<script src="${path}/resources/lib/jquery/jquery-3.1.1.min.js"></script>
<script src="${path}/resources/lib/bootstrap/bootstrap.min.js"></script>
<script src="${path}/resources/lib/webuploader/webuploader.withoutimage.min.js"></script>
<script src="${path}/resources/lib/layer_mobile/layer.js"></script>
<script src="${path}/resources/js/index.js"></script>
<script src="${path}/resources/js/live/liveMobile.js"></script>
</body>

</html>
