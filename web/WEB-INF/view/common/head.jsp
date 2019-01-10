<%@ page import="cn.nsu.edu.web.four.enums.Role" %>
<%@ page import="cn.nsu.edu.web.four.utils.http.RequestUtil" %>
<%--
  顶部导航栏
  User: inkbox
  Date: 18-3-28
  Time: 下午4:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Role role = RequestUtil.getRole(request);
    String v = request.getParameter("v");
//    通用的导航栏
    if (v == null) {
%>

<nav class="nav-bg navbar" style="${!url.contains('index')?'rgba(116,216,230,0.5);color:#6997a5':''}">
    <div class="container boot2-1">
        <div class="logo-w col-lg-4 col-md-4 col-sm-12 col-xs-12">
            <img src="${path}/resources/img/logo.png"/>
            <span>四川省青少年校园足球信息网</span>
        </div>
        <%
            if (RequestUtil.getLogin(request) != null) {
        %>
        <div class="nav-w col-lg-8 col-md-8 hidden-sm hidden-xs boot2-2 visible-desktop">
            <ul>
                <%--<li onclick="location.href='${path}/index'"><a href="javascript:;"
                                                               style="${url.contains('index')?'color: #87efd1;':''}">首页</a>
                </li>--%>
                <%
                    if (role == Role.ORGANIZATION || role == Role.SCHOOL) {
                %>
                <li onclick="location.href='${path}/match/turnMatchList'"><a href="javascript:;"
                                                                             style="${url.contains('match')?'color: #87efd1;':''}">赛事赛程</a>
                </li>
                <%}%>
                <%
                    if (role == Role.SCHOOL) {
                %>
                <li onclick="location.href='${path}/coach/getAllCoach?pn=1&status=0'"><a href="javascript:;"
                                                                                         style="${url.contains('coach')?'color: #87efd1;':''}">我的教练</a>
                </li>
                <li onclick="location.href='${path}/team/display'"><a href="javascript:;"
                                                                      style="${url.contains('team')?'color: #87efd1;':''}">我的球队</a>
                </li>
                <%}%>
                <%
                    if (request.getAttribute("url").toString().contains("team")) {
                %>
                <li class="move2"
                    style="left: ${url.contains('team')?'-80px':'15px'};"></li>
                <%} else {%>
                <li class="move"
                    style="left: ${url.contains('index')?'15px':(url.contains('match')?'15px':(url.contains('coach')?'95px':(url.contains('team')?'175px':'15px')))};"></li>
                <%
                    }
                    if (role != null) {
                %>

                <li class="boot2-3" style="margin-left: 145px;margin-top: -1.75px">
                    <span class="glyphicon glyphicon-user" aria-hidden="true"
                          style="vertical-align: middle; color: #fff"></span>
                    <%--<ul class="ul-1">--%>
                    <%----%>
                    <%--</ul>--%>
                    <%--<ul id="userOperator" class="ul-2">--%>
                    <%----%>
                    <%--</ul>--%>
                </li>
                <li class="boot2-3" style="color: #fff;margin-left: -20px" data-toggle="tooltip" data-placement="bottom"
                    title="<%=RequestUtil.getLogin(request).get("name")%>"><%=RequestUtil.getLogin(request).get("name")%>
                </li>
                <li class="boot2-3" style="margin-left: -10px"
                    onclick="layer.confirm('确认注销？', {btn: ['确定', '取消'] },function(){ location.href='${path}/organization/logout'}, function(){});">
                    <a href="javascript:void(0);">注销</a>
                </li>
                <%}%>
            </ul>
        </div>
        <%}%>
    </div>
</nav>


<%} else if (v.equals("1")) { //bootstrap2版本的导航栏 %>

<nav class="nav-bg">
    <div class="container" style="width:1170px;">
        <div class="logo-w">
            <img src="${path}/resources/img/logo.png"/>
            <span><a href="#">四川省青少年校园足球信息网</a></span>
        </div>
        <div class="nav-w span9" style="height:60px;">
            <ul id="nav-list">
                <li onclick="location.href='${path}/index'"><a href="javascript:;">首页</a></li>
                <%
                    if (role == Role.ORGANIZATION || role == Role.SCHOOL) {
                %>
                <li onclick="location.href='${path}/match/turnMatchList'">
                    <a href="javascript:;" style="${url.contains('match')?'color: #339;':''}">赛事赛程</a>
                </li>
                <%}%>
                <%
                    if (role == Role.SCHOOL) {
                %>
                <li onclick="location.href='${path}/coach/getAllCoach?pn=1&status=0'">
                    <a href="javascript:;" style="${url.contains('coach')?'color: #339;':''}">教练管理</a>
                </li>
                <li onclick="location.href='${path}/team/index'">
                    <a href="javascript:;" style="${url.contains('team')?'color: #339;':''}">球队管理</a>
                </li>
                <%
                    if (role == Role.ORGANIZATION || role == Role.SCHOOL) {
                %>
                <li class="move"></li>
                <%}%>
                <li id="userPass" onmouseenter="userOne()" onmouseleave="userTwo()"
                    style="position: relative;float:right;">
                    <img src="${path}/resources/img/team/user.png" width="17px" aria-hidden="true"/>
                    <ul id="user2">
                        <li data-toggle="tooltip" data-placement="bottom"
                            title="<%=RequestUtil.getLogin(request).get("name")%>"><%=RequestUtil.getLogin(request).get("name")%>
                        </li>
                        <li onclick="layer.confirm('确认注销？', {btn: ['确定', '取消'] },function(){ location.href='${path}/organization/logout'}, function(){});">
                            注销
                        </li>
                    </ul>
                </li>
                <%}%>
            </ul>
        </div>
    </div>
</nav>

<%} else if (v.equals("2")) { //移动端导航栏%>
<!--顶部标题-->
<nav class="navbar" style="font-size: 16px;height:55px;margin: 0;border-bottom:#eee 1px solid; position: relative;">
    <div class="container">
        <div class="row">
            <div class="col-lg-4 col-md-5 col-sm-11 col-xs-12 text-center"
                 style="position: absolute; top: 50%; transform: translateY(-50%);">
                <a href="javascript:history.go(-1)">
                    <img src="${path}/resources/img/referee/return.png"
                         style="height: 20px;width: 20px; float: left; margin-top: 5px;"/>
                </a>
                <span style="font-size: 20px;color: #333;">${param.get('title')}</span>
            </div>
        </div>
    </div>
</nav>
<%}%>
<p id="token" style="display: none" value="${token}">${token}</p>
<input type="hidden" id="refreshed" value="no">
<script type="text/javascript">
    // if (window.name !== "hasLoad") {
    //     location.reload();
    //     window.name = "hasLoad";
    // } else {
    //     window.name = "";
    // }
    var e = document.getElementById("refreshed");
    if (e.value === "no") e.value = "yes";
    else {
        e.value = "no";
        // alert('开始刷新');
        // location.reload();
    }
    window.onload = function (ev) {
        $('[data-toggle="tooltip"]').tooltip()
    }
</script>
