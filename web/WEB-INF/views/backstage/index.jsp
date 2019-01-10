<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title>后台管理</title>

    <meta name="keywords" content="">
    <meta name="description" content="">
    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html"/>
    <![endif]-->

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/common/boostrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/common/boostrap/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/common/nav_css/animate.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/common/nav_css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/common/ali/iconfont.css">
</head>

<body class="fixed-sidebar full-height-layout gray-bg" style="overflow:hidden">
<div id="wrapper">
    <!--左侧导航开始-->
    <nav class="navbar-default navbar-static-side" role="navigation">
        <div class="nav-close"><i class="fa fa-times-circle"></i>
        </div>
        <div class="sidebar-collapse">
            <ul class="nav" id="side-menu">
                <li class="nav-header">
                    <div class="dropdown profile-element">
                        <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                                <span class="clear">
                                    <span class="block m-t-xs" style="font-size:40px;">
                                        <!--<i class="glyphicon glyphicon-check"></i>-->
                                        <strong class="font-bold"></strong>
                                    </span>
                                </span>
                        </a>
                    </div>
                    <div class="logo-element">后台管理系统
                    </div>
                </li>
                <li class="hidden-folded padder m-t m-b-sm text-muted text-xs">
                </li>
                <li>
                    <span class="nav-label">&nbsp;</span>
                </li>
                <li>
                    <span class="nav-label">&nbsp;</span>
                </li>
                <li>
                    <span class="nav-label">&nbsp;</span>
                </li>
                <li class="line dk"></li>
                <li class="hidden-folded padder m-t m-b-sm text-muted text-xs">
                    <span class="ng-scope">板块信息管理</span>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/backstage/welcome.html" class="J_menuItem"><i class="fa fa-home"></i>首页</a>
                    <ul></ul>
                </li>
                <li>
                    <a href="#"><span class="nav-label"><i class="iconfont icon-guanyu"></i>关于我们</span><span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a class="J_menuItem" href="${pageContext.request.contextPath}/backstage/text.html?owner=5&type=1&url=html/aboutUs.html">本部介绍 </a>
                            <a class="J_menuItem" href="${pageContext.request.contextPath}/backstage/text.html?owner=5&type=2&url=html/aboutUs.html">研究院介绍 </a>
                            <a class="J_menuItem" href="${pageContext.request.contextPath}/backstage/text.html?owner=5&type=3&url=html/aboutUs.html">组织架构</a>
                        </li>
                    </ul>
                </li>

                <li>
                    <a href="#"><i class="fa fa-wrench"></i><span class="nav-label">技术产品</span><span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a class="J_menuItem" href="${pageContext.request.contextPath}/backstage/technical/index.html?flag=0&page=1">技术 </a>
                            <a class="J_menuItem" href="${pageContext.request.contextPath}/backstage/product/index.html?flag=0&page=1">产品 </a>
                        </li>
                    </ul>
                </li>

                <li>
                    <a class="J_menuItem" href="${pageContext.request.contextPath}/backstage/cooperator.html"><i class="iconfont icon-xinwen"></i><span class="nav-label">合作伙伴</span></a>
                    <ul></ul>
                </li>



                <li>
                    <a href="#"><i class="iconfont icon-fl-shuju"></i><span class="nav-label">开源数据</span><span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a class="J_menuItem" href="${pageContext.request.contextPath}/backstage/text.html?owner=2&type=1&url=html/OSdata.html">光学 </a>
                            <a class="J_menuItem" href="${pageContext.request.contextPath}/backstage/text.html?owner=2&type=2&url=html/OSdata.html">电子学 </a>
                            <a class="J_menuItem" href="${pageContext.request.contextPath}/backstage/text.html?owner=2&type=3&url=html/OSdata.html">机械学 </a>
                        </li>
                    </ul>

                </li>
                <li>
                    <a class="J_menuItem" href="${pageContext.request.contextPath}/backstage/news.html"><i class="iconfont icon-xinwen"></i><span class="nav-label">新闻中心</span></a>
                    <ul></ul>
                </li>
                <li>
                    <a href="#"><i class="iconfont icon-gonghui"></i><span class="nav-label">党群工会</span><span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a class="J_menuItem" href="${pageContext.request.contextPath}/backstage/text.html?owner=1&type=1&url=html/partyAndUnion.html">党建 </a>
                            <a class="J_menuItem" href="${pageContext.request.contextPath}/backstage/text.html?owner=1&type=2&url=html/partyAndUnion.html">工会 </a>
                        </li>
                    </ul>

                </li>
                <li>
                    <a href="#"><i class="iconfont icon-zhaopin-"></i><span class="nav-label">招贤纳士</span><span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a class="J_menuItem" href="${pageContext.request.contextPath}/backstage/text.html?owner=3&type=1&url=html/recruit.html">硬件 </a>
                            <a class="J_menuItem" href="${pageContext.request.contextPath}/backstage/text.html?owner=3&type=2&url=html/recruit.html">软件 </a>
                            <a class="J_menuItem" href="${pageContext.request.contextPath}/backstage/text.html?owner=3&type=3&url=html/recruit.html">工程 </a>
                            <a class="J_menuItem" href="${pageContext.request.contextPath}/backstage/text.html?owner=3&type=4&url=html/recruit.html">其他 </a>
                        </li>
                    </ul>

                </li>
                <li>
                    <a href="#"><i class="iconfont icon-zoulu-"></i><span class="nav-label">走进大江东</span><span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a class="J_menuItem" href="${pageContext.request.contextPath}/backstage/text.html?owner=4&type=1&url=html/goInto.html">人才引进政策 </a>
                            <a class="J_menuItem" href="${pageContext.request.contextPath}/backstage/text.html?owner=4&type=2&url=html/goInto.html">区域规划 </a>
                        </li>
                    </ul>

                </li>

                <li>
                    <a class="J_menuItem" href="${pageContext.request.contextPath}/contact.html"><i class="iconfont icon-lianxiwomen"></i><span class="nav-label">联系我们</span></a>
                    <ul></ul>
                </li>
                <li class="line dk"></li>
                <li class="hidden-folded padder m-t m-b-sm text-muted text-xs">
                    <span class="ng-scope">密码</span>
                </li>

                <li>
                    <a class="J_menuItem" href="${pageContext.request.contextPath}/changepsw.html"> <i class="fa fa-pencil"></i><span class="nav-label">修改密码</span></a>
                    <ul></ul>
                </li>
            </ul>
        </div>
    </nav>
    <!--左侧导航结束-->
    <!--右侧部分开始-->
    <div id="page-wrapper" class="gray-bg dashbard-1" style="background-color: #FFFFFF">
        <div class="row border-bottom">
            <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
                <div class="navbar-header">
                    <a class="navbar-minimalize minimalize-styl-2 btn btn-info" href="#"><i class="fa fa-bars"></i> </a>
                    <!--左上方收缩按钮-->
                    <div>&nbsp;</div>
                    <div class="form-group text-right">
                        <span style="font-size: 28px" class="text-center">后台管理系统</span>
                    </div>
                </div>
                <div>&nbsp;</div>
                <ul class="nav navbar-top-links navbar-right">
                    <li class="dropdown">
                        <a class="dropdown-toggle " data-toggle="dropdown" href="#">
                            <i class="glyphicon glyphicon-user"></i>系统管理员
                        </a>
                    </li>
                    <li class="dropdown">
                        <a class="dropdown-toggle count-info" href="${pageContext.request.contextPath}/logout.html">
                            <i class="glyphicon glyphicon-off"></i>退出
                        </a>
                    </li>
                </ul>
            </nav>
        </div>
        <div class="row J_mainContent" id="content-main">
            <iframe id="J_iframe" width="100%" height="100%" src="${pageContext.request.contextPath}/backstage/welcome.html" frameborder="0"
                    data-id="welcome.html" ></iframe><!--欢迎页放在这里-->
        </div>
    </div>
    <!--右侧部分结束-->
</div>

<!-- 全局js -->

<script src="${pageContext.request.contextPath}/resources/common/jquery/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/common/boostrap/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/common/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="${pageContext.request.contextPath}/resources/common/plugins/slimscroll/jquery.slimscroll.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/common/plugins/layer/layer.min.js"></script>

<!-- 自定义js -->

<script src="${pageContext.request.contextPath}/resources/common/nav_js/index.js"></script>

</body>
</html>
