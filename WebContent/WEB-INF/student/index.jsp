<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title>主页</title>

    <meta name="keywords" content="">
    <meta name="description" content="">

    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->

    <link href="${ctx }/assets/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${ctx }/assets/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="${ctx }/assets/css/animate.css" rel="stylesheet">
    <link href="${ctx }/assets/css/style.css?v=4.1.0" rel="stylesheet">
    <link href="${ctx }/assets/css/question.css" rel="stylesheet" type="text/css">
    <link href="${ctx }/assets/css/plugins/toastr/toastr.min.css" rel="stylesheet">
    
    <link href="${ctx }/assets/css/start.css" rel="stylesheet" type="text/css" >
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
                                    <span class="block m-t-xs" style="font-size:20px;">
                                        <i class="fa fa-area-chart"></i>
                                        <strong class="font-bold">重新登录</strong>
                                    </span>
                                </span>
                            </a>
                        </div>
                        <div class="logo-element">登录</div>
                    </li>
                    <li>
                        <a class="J_menuItem" href="javascript:void(0);">
                            <i class="fa fa-home"></i>
                            <span class="nav-label">交卷</span>
                        </a>
                    </li>
                </ul>
            </div>
        </nav>
        <!--左侧导航结束-->
        <!--右侧部分开始-->
        <div id="page-wrapper" class="gray-bg dashbard-1">
            <div class="row border-bottom">
                <nav class="navbar navbar-static-top head-center" role="navigation" style="margin-bottom: 0">
                    <!--<div class="navbar-header">-->
                    <div>
                        <a class="navbar-minimalize minimalize-styl-3 btn btn-info " href="javascript:void(0);"><i class="fa fa-bars"></i> </a>
                    </div>
                    <div class="head-title">
                        <span style="float: left;margin-left: 2%;" id="studentID">学号：</span>
                        <span id="examName">考试名称</span>
                        <span style="float: right;margin-right:2%;">
                            <span id="t_d"></span>
                            <span id="t_h">00时</span>
                            <span id="t_m">00分</span>
                            <span id="t_s">00秒</span>
                        </span>
                    </div>
                </nav>
            </div>
            <div class="row J_mainContent" id="content-main">
                <!-- 题目信息    -->
                <div id="question">
                    
                </div>
                <!-- 切换按钮 -->
                <div id="buttonGroup" >
                    <div><a href="javascript:;" id="pre" class="btn btn-w-m btn-primary btn-lg">上一题</a></div>
                    <div><a href="javascript:;" id="next" class="btn btn-w-m btn-primary btn-lg">下一题</a></div>
                </div>
            </div>
        </div>
        <!--右侧部分结束-->
    </div>

    <!-- 全局js -->
    <script src="${ctx }/assets/js/jquery.min.js?v=2.1.4" type="text/javascript" ></script>   
    <script src="${ctx }/assets/js/bootstrap.min.js?v=3.3.6" type="text/javascript" ></script>
    <script src="${ctx }/assets/js/plugins/metisMenu/jquery.metisMenu.js" type="text/javascript" ></script>
    <script src="${ctx }/assets/js/plugins/slimscroll/jquery.slimscroll.min.js" type="text/javascript" ></script>
    <script src="${ctx }/assets/js/plugins/layer/layer.min.js" type="text/javascript" ></script>
	<script src="${ctx }/assets/js/plugins/toastr/toastr.min.js" type="text/javascript" ></script>
    <!-- 自定义js -->
    <!-- <script src="${ctx }/assets/js/hAdmin.js?v=4.1.0"></script>-->
    <!-- <script type="text/javascript" src="js/index.js"></script> -->
    <script src="${ctx }/assets/js/plugins/layer/layer.min.js" type="text/javascript" ></script>
    <script src="${ctx }/assets/js/examIndex.js" type="text/javascript" ></script>
    <!-- 第三方插件 -->
 <!--    <script src="${ctx }/assets/js/plugins/pace/pace.min.js"></script> -->
    <script type="text/javascript">
        init(${result});
        $("body").toggleClass("mini-navbar");
        SmoothlyMenu();
    </script>
</body>
</html>