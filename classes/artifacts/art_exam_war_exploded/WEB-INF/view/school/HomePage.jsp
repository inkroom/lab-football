<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <script type="text/javascript" src="#{base_path}/lib/respond.min.js"></script>
    <link rel="stylesheet" type="text/css" href="#{base_path}/static/h-ui/css/H-ui.min.css" />
    <link rel="stylesheet" type="text/css" href="#{base_path}/static/h-ui.admin/css/H-ui.admin.css" />
    <link rel="stylesheet" type="text/css" href="#{base_path}/lib/Hui-iconfont/1.0.8/iconfont.css" />
    <link rel="stylesheet" type="text/css" href="#{base_path}/static/h-ui.admin/css/style.css" />
    <title>四川省中小学生艺术测评系统</title>
    <style>
        .welcome{
            background-color: rgba(0,0,0,0.2);
            position: absolute;
            bottom: 150px;
            width: 100%;
            text-align: center;
            color: #ffffff;
            height: 200px;
            line-height: 100px
        }
    </style>
</head>
<body>
<header class="navbar-wrapper">
    <div class="navbar navbar-fixed-top">
        <div class="container-fluid cl">
            <a class="logo navbar-logo-m f-l mr-10 visible-xs" href="index.html">四川省中小学生艺术测评系统</a>
            <span class="logo navbar-slogan f-l mr-10 hidden-xs">四川省中小学生艺术测评系统</span> <a aria-hidden="false" class="nav-toggle Hui-iconfont visible-xs" href="javascript:;">&#xe667;</a>
            <nav class="nav navbar-nav">
                <ul class="cl">
                </ul>
            </nav>
            <nav id="Hui-userbar" class="nav navbar-nav navbar-userbar hidden-xs">
                <ul class="cl">
                    <li class="dropDown dropDown_hover"> <a href="#" class="dropDown_A">学校端<i class="Hui-iconfont">&#xe6d5;</i></a>
                        <ul class="dropDown-menu menu radius box-shadow">
                            <li><a href="#">退出</a></li>
                        </ul>
                    </li>
                </ul>
            </nav>
        </div>
    </div>
</header>
<aside class="Hui-aside">
    <div class="menu_dropdown bk_2">
        <dl id="menu-article">
            <dt><i class="Hui-iconfont">&#xe705;</i> 账号管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            <dd>
                <ul>
                    <li><a href="修改密码.jsp" title="修改密码">修改密码</a>
                </ul>
            </dd>
        </dl>
        <dl id="menu-picture">
            <dt><i class="Hui-iconfont">&#xe61a;</i> 数据分析<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            <dd>
                <ul>
                    <li><a href="DataAnalysis.jsp" title="数据分析">数据分析</a></li>
                </ul>
            </dd>
        </dl>
        <dl id="menu-product">
            <dt><i class="Hui-iconfont">&#xe62b;</i> 教师认证<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            <dd>
                <ul>
                    <li><a href="TeacherAuthentication.jsp" title="教师认证">教师认证</a></li>
                </ul>
            </dd>
        </dl>
    </div>
</aside>
<div class="dislpayArrow hidden-xs"><a class="pngfix" href="javascript:void(0);" onClick="displaynavbar(this)"></a></div>
<section class="Hui-article-box">
    <nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> <a href="HomePage.jsp">首页</a> <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
    <div class="right-c"><img src="../images/background2.jpg" width="100%" height="850px">
    </div>
    <div class="welcome">
        <div style="font-size: 48px;background-color: transparent">欢迎使用四川省中小学生 <br>艺术测评系统</div>
    </div>
</section>
<script type="text/javascript" src="#{base_path}/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="#{base_path}/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="#{base_path}/static/h-ui/js/H-ui.js"></script>
<script type="text/javascript" src="#{base_path}/static/h-ui.admin/js/H-ui.admin.page.js"></script>
</body>
</html>