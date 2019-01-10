<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <%--<script type="text/javascript" src="../../../resources/js/TeacherAndSchoolAndMechanism/lib/respond.min.js"></script>--%>
    <%--<link rel="stylesheet" type="text/css" href="../../../resources/js/TeacherAndSchoolAndMechanism/static/h-ui/css/H-ui.min.css" />--%>
    <%--<link rel="stylesheet" type="text/css" href="../../../resources/js/TeacherAndSchoolAndMechanism/static/h-ui.admin/css/H-ui.admin.css" />--%>
    <%--<link rel="stylesheet" type="text/css" href="../../../resources/js/TeacherAndSchoolAndMechanism/lib/Hui-iconfont/1.0.8/iconfont.css" />--%>
    <%--<link rel="stylesheet" type="text/css" href="../../../resources/js/TeacherAndSchoolAndMechanism/static/h-ui.admin/css/style.css" />--%>
    <title>四川省中小学生艺术测评系统</title>
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
                    <li><a href="修改密码.html" title="修改密码">修改密码</a>
                </ul>
            </dd>
        </dl>
        <dl id="menu-picture">
            <dt><i class="Hui-iconfont">&#xe61a;</i> 数据分析<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            <dd>
                <ul>
                    <li><a href="数据分析.html" title="数据分析">数据分析</a></li>
                </ul>
            </dd>
        </dl>
        <dl id="menu-product">
            <dt><i class="Hui-iconfont">&#xe62b;</i> 教师认证<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            <dd>
                <ul>
                    <li><a href="教师认证.html" title="教师认证">教师认证</a></li>
                </ul>
            </dd>
        </dl>
    </div>
</aside>
<div class="dislpayArrow hidden-xs"><a class="pngfix" href="javascript:void(0);" onClick="displaynavbar(this)"></a></div>
<section class="Hui-article-box">
    <nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> <a href="首页.html">首页</a> <span class="c-gray en">&gt;</span> 教师认证 <span class="c-gray en">&gt;</span> 教师认证 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
    <div class="Hui-article">
        <article class="cl pd-20">
            <div class="cl pd-5 bg-1 bk-gray"> <span class="l">
                <a href="javascript:;" onclick="" class="btn btn-primary radius"><i class="Hui-iconfont"></i> 批量同意</a>
                               <a href="javascript:;" onclick="" class="btn btn-danger radius"><i class="Hui-iconfont"></i> 批量拒绝</a>
            </span>
                <span class="r">共有数据：<strong>54</strong> 条</span> </div>
            <div class="mt-10">
                <table class="table table-border table-bordered table-hover table-bg">
                    <thead>
                    <tr class="text-c">
                        <th><input type="checkbox" value="" name=""></th>
                        <th>教师编号</th>
                        <th>教师姓名</th>
                        <th>教授年级</th>
                        <th>教师照片</th>
                        <th>审批</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr class="text-c">
                        <td><input type="checkbox" value="" name=""></td>
                        <td>0001</td>
                        <td>张三</td>
                        <td>一年级</td>
                        <td></td>
                        <td>
                            <a href="javascript:;" onclick="" class="btn btn-primary radius">通过</a>
                            <a href="javascript:;" onclick="" class="btn btn-danger radius">拒绝</a>
                    </tr>
                    <tr class="text-c">
                        <td><input type="checkbox" value="" name=""></td>
                        <td>0002</td>
                        <td>李四</td>
                        <td>二年级</td>
                        <td></td>
                        <td>
                            <a href="javascript:;" onclick="" class="btn btn-primary radius">通过</a>
                            <a href="javascript:;" onclick="" class="btn btn-danger radius">拒绝</a>
                    </tr>
                    <tr class="text-c">
                        <td><input type="checkbox" value="" name=""></td>
                        <td>0003</td>
                        <td>王五</td>
                        <td>三年级</td>
                        <td></td>
                        <td>
                            <a href="javascript:;" onclick="" class="btn btn-primary radius">通过</a>
                            <a href="javascript:;" onclick="" class="btn btn-danger radius">拒绝</a>
                    </tr>
                    <tr class="text-c">
                        <td><input type="checkbox" value="" name=""></td>
                        <td>0004</td>
                        <td>赵六</td>
                        <td>四年级</td>
                        <td></td>
                        <td>
                            <a href="javascript:;" onclick="" class="btn btn-primary radius">通过</a>
                            <a href="javascript:;" onclick="" class="btn btn-danger radius">拒绝</a>
                    </tr>
                    <tr class="text-c">
                        <td><input type="checkbox" value="" name=""></td>
                        <td>0005</td>
                        <td>李七</td>
                        <td>五年级</td>
                        <td></td>
                        <td>
                            <a href="javascript:;" onclick="" class="btn btn-primary radius">通过</a>
                            <a href="javascript:;" onclick="" class="btn btn-danger radius">拒绝</a>
                    </tr>
                    <tr class="text-c">
                        <td><input type="checkbox" value="" name=""></td>
                        <td>0006</td>
                        <td>刘八</td>
                        <td>六年级</td>
                        <td></td>
                        <td>
                            <a href="javascript:;" onclick="" class="btn btn-primary radius">通过</a>
                            <a href="javascript:;" onclick="" class="btn btn-danger radius">拒绝</a>
                    </tr>
                    </tbody>
                </table>
                <div class="pt-10 text-c">
                    <button class="my-btn" onclick="">首页</button>
                    <button class="my-btn" onclick="">&lt;&lt;</button>
                    <button class="my-btn" onclick="">1</button>
                    <button class="my-btn" onclick="">2</button>
                    <button class="my-btn" onclick="">&gt;&gt;</button>
                    <button class="my-btn" onclick="">尾页</button>
                </div>
            </div>
        </article>
    </div>
</section>
<%--<script type="text/javascript" src="../../../resources/js/TeacherAndSchoolAndMechanism/lib/jquery/1.9.1/jquery.min.js"></script>--%>
<%--<script type="text/javascript" src="../../../resources/js/TeacherAndSchoolAndMechanism/lib/layer/2.4/layer.js"></script>--%>
<%--<script type="text/javascript" src="../../../resources/js/TeacherAndSchoolAndMechanism/static/h-ui/js/H-ui.js"></script>--%>
<%--<script type="text/javascript" src="../../../resources/js/TeacherAndSchoolAndMechanism/static/h-ui.admin/js/H-ui.admin.page.js"></script>--%>
</body>
</html>