<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

    <title>班级管理</title>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="${base_path}/lib/h-ui/css/H-ui.min.css"/>
    <%--<link rel="stylesheet" href="${base_path}/lib/Hui-iconfont/1.0.8/iconfont.css"/>--%>
    <link rel="stylesheet" href="${base_path}/lib/Hui-iconfont/1.0.8/iconfont.min.css"/>
    <link rel="stylesheet" href="${base_path}/lib/font-awesome-4.7.0/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="${base_path}/lib/h-ui/icheck.css"/>
    <link rel="stylesheet" href="${base_path}/lib/h-ui.admin/css/H-ui.admin.css"/>

    <%--<link rel="stylesheet" href="${base_path}/resources/common/h-ui/css/H-ui.css"/>--%>
    <%--<link rel="stylesheet" href="${base_path}/resources/common/h-ui/css/H-ui.min.css"/>--%>
    <%--&lt;%&ndash;<link rel="stylesheet" href="${base_path}/resources/common/Hui-iconfont/1.0.8/iconfont.css"/>&ndash;%&gt;--%>
    <%--<link rel="stylesheet" href="${base_path}/resources/common/Hui-iconfont/1.0.8/iconfont.min.css"/>--%>
    <%--<link rel="stylesheet" href="${base_path}/resources/common/layui/layui.css" media="all"/>--%>

    <link rel="stylesheet" href="${base_path}/resources/css/student/mystyle.css"/>
    <link rel="stylesheet" href="${base_path}/resources/css/student/reset.css"/>
    <link rel="stylesheet" href="${base_path}/resources/css/student/style.css"/>

    <%--<link rel="stylesheet" href="${base_path}/resources/common/datatables/1.10.0/jquery.dataTables.min.css">--%>
    <%--<link rel="stylesheet" href="${base_path}/resources/common/datatables/1.10.0/dataTables.jqueryui.min.css">--%>


</head>

<body>
<jsp:include page="../common/head.jsp" flush="false"/>
<%--<div class="c-white text-c f-24">--%>
<%--<div class="header1">四川省中小学生艺术素质测评系统</div>--%>
<%--</div>--%>

<%--<div class="container">--%>
<%--<div class="row">--%>
<%--<div class="col-xs-1 col-md-5 col-sm-6 hidden-xs">--%>
<%--<img src="${base_path}/resources/img/sun.png" alt="" class="img-responsive"/>--%>
<%--</div>--%>
<%--<div class="col-xs-1 col-md-5 col-sm-6 mt-50 hidden-xs">--%>
<%--<div id="nav">--%>
<%--<ul>--%>
<%--<li><a href="#" style="background-color: #FF633C;border-radius: 10px 0 0 10px;">首页</a></li>--%>
<%--<li><a href="#">考试类型</a>--%>
<%--<ul>--%>
<%--<li><a href="#">美术考试</a></li>--%>
<%--<li><a href="#">音乐考试</a></li>--%>
<%--</ul>--%>
<%--</li>--%>
<%--<li><a href="#">练习中心</a>--%>
<%--<ul>--%>
<%--<li><a href="#">美术练习</a></li>--%>
<%--<li><a href="#">音乐练习</a></li>--%>
<%--</ul>--%>
<%--</li>--%>
<%--<li><a href="#">积分榜单</a></li>--%>
<%--<li><a href="#">我的空间</a></li>--%>
<%--<li><a href="#">个人中心</a>--%>
<%--<ul>--%>
<%--<li><a href="#">考试通知</a></li>--%>
<%--<li><a href="#">切换账户</a></li>--%>
<%--<li><a href="#">退出登录</a></li>--%>
<%--</ul>--%>
<%--</li>--%>
<%--</ul>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--<div class="row">--%>
<%--<div class="col-xs-3 visible-xs ">--%>
<%--<div id="nav-a">--%>
<%--<ul>--%>
<%--<li><a href="#" style="background-color: #FF633C;border-radius: 10px 10px 0 0;">首页</a></li>--%>
<%--<li><a href="#">考试类型</a>--%>
<%--<ul>--%>
<%--<li><a href="#">美术考试</a></li>--%>
<%--<li><a href="#">音乐考试</a></li>--%>
<%--</ul>--%>
<%--</li>--%>
<%--<li><a href="#">练习中心</a>--%>
<%--<ul>--%>
<%--<li><a href="#">美术练习</a></li>--%>
<%--<li><a href="#">音乐练习</a></li>--%>
<%--</ul>--%>
<%--</li>--%>
<%--<li><a href="#">积分榜单</a></li>--%>
<%--<li><a href="#">我的空间</a></li>--%>
<%--<li><a href="#">个人中心</a>--%>
<%--<ul>--%>
<%--<li><a href="#">考试通知</a></li>--%>
<%--<li><a href="#">切换账户</a></li>--%>
<%--<li><a href="#">退出登录</a></li>--%>
<%--</ul>--%>
<%--</li>--%>
<%--</ul>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>

<div class="cnt">
    <nav class="breadcrumb">
        <i class="Hui-iconfont">&#xe67f;</i>
        <a href="#">首页</a>
        <span class="c-666 en">&gt;</span>
        <a href="#">班级管理</a>
    </nav>

    <br>

    <a href="javascript:void(0);" class="btn btn-primary radius f-r" id="joinClass">
        <i class="Hui-iconfont">&#xe600;</i> 加入新班级</a></span>
    <table id="classManageTable" class="table table-border table-bordered pd-10 table-bg col-sm-12 mt-30">
        <thead class="text-c">
        <tr>
            <th><img src="${base_path}/resources/img/学校.png" style="width: 20px" alt=""/>学校名称</th>
            <th><img src="${base_path}/resources/img/班级.png" style="width: 20px" alt=""/>班级名称</th>
            <th><img src="${base_path}/resources/img/时间 .png" style="width: 20px" alt=""/>加入时间</th>
            <th><img src="${base_path}/resources/img/状态.png" style="width: 20px" alt=""/>状态</th>
        </tr>
        </thead>
        <thead>
        </thead>
    </table>
    <div class="classmanage-tip mt-50">
        <i class="icon Hui-iconfont" style="color:#660033; font-size:36px">&#xe692;</i>
        <span style="color:#660033; ">同学们请注意啦！</span>
        <ul style="font-size: 20px">
            <li>没有加入班级不能正常使用本系统哦</li>
            <li>加入新班级后，以前的班级不可再用</li>
        </ul>
    </div>
</div>
<jsp:include page="../common/foot.jsp" flush="false"/>
<%--<div class="foot1">--%>
<%--<input type="hidden" id="_csrf" value="${_csrf.getToken()}"/>--%>
<%--</div>--%>
<%--<div class="foot2">--%>
<%--<div style="width: 100%;height: 30px;">--%>
<%--<footer class="footer ">--%>
<%--<div class="container">--%>
<%--<div class="row footer-top">--%>
<%--<div class="col-sm-6  col-lg-6 col-lg-offset-1 f-28 c-white">--%>
<%--<div class="row about">--%>
<%--<div class="col-xs-3">--%>
<%--<h4>关于</h4>--%>
<%--<ul class="list-unstyled">--%>
<%--<li class="mt-30"><a href="#" class="c-white"><span class="c-white f-24">音乐考试</span></a>--%>
<%--</li>--%>
<%--<li class="mt-30"><a href="#" class="c-white"><span class="c-white f-24">美术考试</span></a>--%>
<%--</li>--%>
<%--<li class="mt-30"><a href="#" class="c-white"><span class="c-white f-24">音乐练习</span></a>--%>
<%--</li>--%>
<%--<li class="mt-30"><a href="#" class="c-white"><span class="c-white f-24">美术练习</span></a>--%>
<%--</li>--%>
<%--</ul>--%>
<%--</div>--%>
<%--<div class="col-xs-3">--%>
<%--<h4>下载中心</h4>--%>
<%--<ul class="list-unstyled">--%>
<%--<li class="mt-30"><a href="../web/filedownload.html" class="c-white"><span--%>
<%--class="c-white f-24">资料下载</span></a></li>--%>
<%--<li class="mt-30"><a href="../web/myfile.html" class="c-white"><span--%>
<%--class="c-white f-24">档案下载</span></a></li>--%>
<%--</ul>--%>
<%--</div>--%>
<%--<div class="col-xs-3">--%>
<%--<h4>个人中心</h4>--%>
<%--<ul class="list-unstyled">--%>
<%--<li class="mt-30"><a href="../web/mycertificate.html" class="c-white"><span--%>
<%--class="c-white f-24">我的证书</span></a></li>--%>
<%--<li class="mt-30"><a href="../web/upporject.html" class="c-white"><span--%>
<%--class="c-white f-24">课外学习</span></a></li>--%>
<%--<li class="mt-30"><a href="../web/upporject.html" class="c-white"><span--%>
<%--class="c-white f-24">实践活动</span></a></li>--%>
<%--<li class="mt-30"><a href="../web/upporject.html" class="c-white"><span--%>
<%--class="c-white f-24">特长专长</span></a></li>--%>
<%--</ul>--%>
<%--</div>--%>
<%--<div class="col-xs-3">--%>
<%--<h4><a href="scoreboard.scoreboard.jsp"><span class="c-white"> 积分榜单</span></a></h4>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--</footer>--%>
<%--</div>--%>
<%--<div id="datepicker" class="calendar visible-lg"></div>--%>

<%--<div class="row footer-bottom col-sm-12  col-lg-12">--%>
<%--<hr/>--%>
<%--<ul class="list-inline text-center">--%>
<%--<li class="text-c">版权所有@四川省中小学生艺术测试系统</li>--%>
<%--</ul>--%>
<%--</div>--%>
<%--</div>--%>
<%--<div class="class_add">--%>
<%--<article class="page-container" style="display: none;">--%>
<%--<h2 style="background-color: #aee1fb" class="text-c">班级信息</h2>--%>
<%--<form class="mt-50" id="form-admin-add" method="">--%>
<%--<div>--%>
<%--<label><span class="c-red">*</span>班级号：</label>--%>
<%--<input type="text" class="input-text" value="" placeholder="" id="classId" name="" style="width: auto">--%>
<%--</div>--%>
<%--<div class="mt-30">--%>
<%--<label><span class="c-red">*</span>口令：</label>&nbsp;&nbsp;&nbsp;--%>
<%--<input type="password" class="input-text" autocomplete="off" value="" placeholder="" id="key" name=""--%>
<%--style="width: auto">--%>
<%--</div>--%>
<%--<div>--%>
<%--<div class="mt-30 text-c">--%>
<%--<input class="btn btn-primary radius" type="button" id="joinClassButton"--%>
<%--value="&nbsp;&nbsp;申请加入&nbsp;&nbsp;">--%>
<%--</div>--%>
<%--</div>--%>
<%--</form>--%>

<%--</article>--%>
<%--</div>--%>

<%--<script src="${base_path}/resources/common/jquery/1.9.1/jquery.js"></script>--%>
<%--<script src="${base_path}/resources/common/jquery/1.9.1/jquery.min.js"></script>--%>
<%--&lt;%&ndash;<script src="${base_path}/resources/common/h-ui/js/H-ui.js"></script>&ndash;%&gt;--%>
<%--<script src="${base_path}/resources/common/h-ui/js/H-ui.min.js"></script>--%>
<%--<script src="${base_path}/resources/common/jquery.SuperSlide/2.1.1/jquery.SuperSlide.min.js"></script>--%>
<%--&lt;%&ndash;<script src="${base_path}/resources/common/laypage/1.2/laypage.js"></script>&ndash;%&gt;--%>
<%--<script src="${base_path}/resources/common/other/respond.min.js"></script>--%>
<%--&lt;%&ndash;<script src="${base_path}/resources/common/layui/layui.js"></script>&ndash;%&gt;--%>
<%--<script src="${base_path}/resources/common/layer/2.4/layer.js"></script>--%>
<%--<script src="${base_path}/resources/common/datatables/1.10.0/jquery.dataTables.min.js"></script>--%>
<%--<script src="${base_path}/resources/common/datatables/1.10.0/dataTables.jqueryui.min.js"></script>--%>


<script src="${base_path}/lib/jquery/1.9.1/jquery.min.js"></script>
<%--<script src="${base_path}/lib/jquery/1.9.1/jquery.js"></script>--%>
<%--<script src="${base_path}/lib/jquery.validation/1.14.0/jquery.validate.js"></script>--%>
<script src="${base_path}/lib/jquery.validation/1.14.0/jquery.validate.min.js"></script>
<script src="${base_path}/lib/h-ui/js/H-ui.js"></script>
<script src="${base_path}/lib/layer/2.4/layer.js"></script>


<script src="${base_path}/resources/js/student/jquery-ui.js"></script>

<script src="${base_path}/resources/js/student/index.js"></script>
<script src="${base_path}/resources/js/student/dataTable.js"></script>
<script src="${base_path}/resources/js/student/classManager.js"></script>
</body>
</html>
