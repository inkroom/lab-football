<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title>在线考试系统</title>

    <meta name="keywords" content="">
    <meta name="description" content="">

    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->

    <link rel="shortcut icon" href="favicon.ico"> <link href="css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="css/animate.css" rel="stylesheet">
    <link href="css/style.css?v=4.1.0" rel="stylesheet">
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
                            <a data-toggle="dropdown" class="dropdown-toggle" href="teacherIndex">
                                <span class="clear">
                                    <span class="block m-t-xs" style="font-size:20px;">
                                        <strong class="font-bold">在线考试系统</strong>
                                        <span class="font-bold" style="font-size:13px;">教师端</span>
                                    </span>
                                </span>
                            </a>
                        </div>
                        <div class="logo-element"><span class="glyphicon glyphicon-home" aria-hidden="true"></span>
                        </div>
                    </li>
                    <li class="hidden-folded padder m-t m-b-sm text-muted text-xs">
                        <span class="ng-scope">考试管理</span>
                    </li>
                    <li>
                    <a href="#">
                    	<i class="fa fa-list-alt"></i>
                        <span class="nav-label">查看考试</span>
                        <span class="fa arrow"></span>
                    </a>
                        <ul class="nav nav-second-level">
                            <li>
                                <a class="J_menuItem" href="teacherEditQuestionLib">编辑现有题库</a>
                            </li>
                            <li>
                                <a class="J_menuItem" href="teacherEditExam">编辑现有考试</a>
                            </li>
                        </ul>
                    </li>
                    
                    <li>
                        <a href="#">
                        	<i class="fa fa-edit"></i>
                            <span class="nav-label">创建考试</span>
                            <span class="fa arrow"></span>
                        </a>
                        <ul class="nav nav-second-level">
                            <li>
                                <a class="J_menuItem" href="javascript:void(0);" onclick="downloadExamDemo()">下载题库模版</a>
                            </li>
                            <li>
                                <a class="J_menuItem" href="teacherToCreatelib">导入题库数据</a>
                            </li>
                            <li>
                                <a class="J_menuItem" href="javascript:void(0);" onclick="downloadStudentDemo()">下载考生模版</a>
                            </li>
                            <li>
                                <a class="J_menuItem" href="teacherCreateExam">创建考试信息</a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a class="J_menuItem" href="teacherStartExam">
                        	<i class="fa fa-play-circle-o"></i>
                        	<span class="nav-label">开始考试</span>
                        </a>
                    </li>
                    <li class="line dk"></li>
                    <li class="hidden-folded padder m-t m-b-sm text-muted text-xs">
                        <span class="ng-scope">成绩管理</span>
                                                <ul class="nav nav-second-level">
                            <li>
                                <a class="J_menuItem" href="javascript:void(0);" onclick="downloadExamDemo()">下载题库模版</a>
                            </li>
                            <li>
                                <a class="J_menuItem" href="teacherToCreatelib">导入题库数据</a>
                            </li>
                            <li>
                                <a class="J_menuItem" href="javascript:void(0);" onclick="downloadStudentDemo()">下载考生模版</a>
                            </li>
                            <li>
                                <a class="J_menuItem" href="teacherCreateExam">创建考试信息</a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a href="teacherShowMark" class="J_menuItem">
                        <i class="fa fa-cloud-download"></i>
                        <span class="nav-label">成绩管理</span></a>
                        <ul class="nav nav-second-level">
                            <li>
                                <a class="J_menuItem" href="teacherToLookAnswer">阅卷</a>
                            </li>
                            <li>
                                <a class="J_menuItem" href="teacherToCreatelib">导出成绩</a>
                            </li>
                        </ul>
                    </li>
                 </ul>
            </div>
        </nav>
        <!--右侧部分开始-->
        <div id="page-wrapper" class="gray-bg dashbard-1">
            <div class="row border-bottom">
                <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
                <div class="navbar-header"><a class="navbar-minimalize minimalize-styl-2 btn btn-info " href="#"><i class="fa fa-bars"></i> </a>
                    </div>
                    <ul class="nav navbar-top-links navbar-right">
                        <li class="dropdown">
                            <a class="dropdown-toggle count-info" data-toggle="dropdown" href="#">
                                <i class="fa fa-user"></i>&nbsp;<%=session.getAttribute("teacherEmail")%>
                            </a>
                            <ul class="dropdown-menu dropdown-alerts">
                                <li>
                                    <a class="J_menuItem" href="teacherChangePassword" >
                                         <i class="fa fa-unlock-alt"></i>&nbsp;&nbsp;&nbsp;&nbsp;修改密码
                                    </a>
                                </li>
                                <li class="divider"></li>
                                <li>
                                    <a href="teacherExit">
                                          <i class="fa fa-sign-out"></i>&nbsp;&nbsp;&nbsp;&nbsp;退出登录
                                    </a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </nav>
            </div>
            <div class="row J_mainContent" id="content-main">
                <iframe id="J_iframe" width="100%" height="100%" src="teacherWelcome" frameborder="0" data-id="index_v1.html" seamless></iframe>
            </div>
        </div>
        <!--右侧部分结束-->
    </div>
<div style="text-align:center;">
<p>&copy;成都东软学院</p>
</div>
    <!-- 全局js -->
    <script src="js/jquery.min.js?v=2.1.4"></script>
    <script src="js/bootstrap.min.js?v=3.3.6"></script>
    <script src="js/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
    <script src="js/plugins/layer/layer.min.js"></script>

    <!-- 自定义js -->
    <script src="js/hAdmin.js?v=4.1.0"></script>
    <script type="text/javascript" src="js/index.js"></script>

    <!-- 第三方插件 -->
    <script src="js/plugins/pace/pace.min.js"></script>
    <script type="text/javascript">
    function downloadExamDemo(){
    	window.open("teacherDownloadQuestionLibDemo");
    };
    function downloadStudentDemo(){
    	window.open("teacherDownloadStudentDemo");
    };
    </script>
</body>
</html>
