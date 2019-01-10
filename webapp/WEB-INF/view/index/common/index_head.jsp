<%--
  Created by IntelliJ IDEA.
  User: MeiXiebing
  Date: 4/24/17
  Time: 09:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div class="header">
    <div class="orange1"></div>
    <div class="brown1">
        <img src="${base_path}/resources/img/index/logo7.png">
        <p>四川省青少年校园足球信息网</p>
    </div>
    <!--轮播-->
    <div id="myFocus" style="height: 530px"><!--焦点图盒子-->

        <div class="pic" id="rollPic" ms-controller="rollPic"><!--图片列表-->
            <ul id="rollGroup">
                <c:forEach items="${imgList}" var="item">
                    <li><a href="#1"><img src="${base_path}/${item.imgAddr}" /></a></li>
                </c:forEach>
            </ul>
        </div>

        <!--轮播 end-->
        <!--导航-->
        <div class="nav">
            <ul>
                <li class="nth"><a href="javascript:void(0);">首页</a></li>
                <li><a href="javascript:window.location.href='${base_path}/org/login_view.html';">机构管理</a></li>
                <li><a href="javascript:window.location.href='${base_path}/player/login_view.html';">球员管理</a></li>
                <li><a href="javascript:window.location.href='${base_path}/coach/login_view.html';">教练员管理</a></li>
                <li><a href="javascript:window.location.href='${base_path}/team/login_view.html';">球队管理</a></li>
                <li><a href="javascript:window.location.href='${base_path}/site/login_view.html';">现场管理</a></li>
                <li><a href="javascript:window.location.href='${base_path}/live/live_index.html';">比赛现场直播</a></li>
                <li><a href="javascript:window.location.href='${base_path}/info/index.html';">比赛信息公示</a></li>
            </ul>
        </div>
        <!--导航 end-->
    </div>
</div>