<%@ page import="java.util.List" %>
<%@ page import="cn.edu.nsu.lib.bean.teacher.LabEntity" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title> 教师端首页</title>

    <meta name="keywords" content="">
    <meta name="description" content="">
    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie" />
    <![endif]-->
    <link rel="stylesheet" href="${base_path}/resources/common/boostrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${base_path}/resources/common/boostrap/css/font-awesome.min.css">
    <link rel="stylesheet" href="${base_path}/resources/common/nav_css/animate.css">
    <link rel="stylesheet" href="${base_path}/resources/common/nav_css/style.css">
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

                                        <strong class="font-bold"></strong>
                                    </span>
                                </span>
                        </a>
                    </div>
                    <div class="logo-element">实验室管理系统
                    </div>
                    <div id="stu_upload_result" class="alert" hidden>通知发布成功</div>
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
                    <span class="ng-scope">学生信息</span>
                </li>

                <li>
                    <a href="#"><i class="fa fa-edit"></i> <span class="nav-label">学生信息管理</span><span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li><a class="J_menuItem" href="${base_path}/Teacher/mainpage">主页面</a>
                        </li>
                        <li><a class="J_menuItem" href="${base_path}/resources/other/学生信息.xlsx">学生信息模板</a>
                        </li>
                    </ul>
                </li>
                <li class="line dk"></li>
                <li class="hidden-folded padder m-t m-b-sm text-muted text-xs">
                    <span class="ng-scope">添加学生</span>
                </li>
                <%
                    List<LabEntity> lablist = (List<LabEntity>) request.getAttribute("lablist");
                    if(lablist!=null){
                        for (int i = 0; i < lablist.size(); i++) {
                            LabEntity lab = lablist.get(i);
                 %>
                <li>
                    <a href="#"><i class="fa fa-flask"></i> <span class="nav-label"><%=lab.getName()%></span><span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li><a class="J_menuItem" href="javascript:void(0);" onclick="pop_file_window()">导入学生信息</a>
                            <div hidden><
                                <form id="form_stu_excel" method="post" enctype="multipart/form-data" action="${base_path}/Teacher/upload_stu?lab_id=<%=lab.getId()%>" ><input type="file" name="stu_excel" id="stu_excel" onchange="upload_excel()"></form></div>
                        </li>
                    </ul>
                </li>
                <%
                    }  }
                %>
                <c:if test="${lablist.isEmpty()}">
                <li>
                    <a href="#"><i class="fa fa-flask"></i> <span class="nav-label">未加入实验室</span><span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                    </ul>
                </li>
                </c:if>

                <%--<li class="line dk"></li>--%>
                <%--<li class="hidden-folded padder m-t m-b-sm text-muted text-xs">--%>
                    <%--<span class="ng-scope">公告</span>--%>
                <%--</li>--%>

                <%--<li>--%>
                    <%--<a href="#"><i class="glyphicon glyphicon-eye-open"></i> <span class="nav-label">公告管理</span><span class="fa arrow"></span></a>--%>
                    <%--<ul class="nav nav-second-level">--%>
                        <%--<li><a class="J_menuItem" href="../common/404">查看公告</a>--%>
                        <%--</li>--%>
                    <%--</ul>--%>
                <%--</li>--%>
                <%--<li class="hidden-folded padder m-t m-b-sm text-muted text-xs">--%>
                    <%--<span class="ng-scope">公告</span>--%>
                <%--</li>--%>

                <%--<li>--%>
                    <%--<a href="#"><i class="glyphicon glyphicon-eye-open"></i> <span class="nav-label">公告管理</span><span class="fa arrow"></span></a>--%>
                    <%--<ul class="nav nav-second-level">--%>
                        <%--<li><a class="J_menuItem" href="${base_path}/Teacher/up_noticePage">上传公告</a>--%>
                        <%--</li>--%>
                        <%--<li><a class="J_menuItem" href="${base_path}/Teacher/notice_page">查看公告</a>--%>
                        <%--</li>--%>
                    <%--</ul>--%>
                <%--</li>--%>
                <li class="line dk"></li>
                <li class="hidden-folded padder m-t m-b-sm text-muted text-xs">
                    <span class="ng-scope">修改密码</span>
                </li>

                <li>
                    <a href="#"><i class="fa fa-edit"></i> <span class="nav-label">修改密码</span><span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li><a class="J_menuItem" href="${base_path}/modifyPasswd/student">修改密码</a>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </nav>
    <!--左侧导航结束-->
    <!--右侧部分开始-->
    <div id="page-wrapper" class="gray-bg dashbard-1" style="background-color: #FFFFFF">
        <div class="row border-bottom" >
            <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
                <div class="navbar-header" >
                    <a class="navbar-minimalize minimalize-styl-2 btn btn-info" href="#"><i class="fa fa-bars"></i> </a>     <!--左上方收缩按钮-->
                    <div>&nbsp;</div>
                    <div class="form-group text-right">
                        <span style="font-size: 28px" class="text-center">实验室人员管理系统</span>
                    </div>
                </div>
                <div>&nbsp;</div>
                <ul class="nav navbar-top-links navbar-right" >
                    <li class="dropdown">
                        <a class="dropdown-toggle " data-toggle="dropdown" href="#">
                            <i class="glyphicon glyphicon-user"></i>${username}
                        </a>

                    </li>
                    <li class="dropdown">
                        <a href="${base_path}/exitLogin" class="dropdown-toggle count-info"  >
                            <i class="glyphicon glyphicon-off"></i>退出
                        </a>
                    </li>
                </ul>
            </nav>
        </div>
        <div class="row J_mainContent" id="content-main">
            <iframe id="J_iframe" width="100%" height="100%" src="${base_path}/Teacher/welcome" frameborder="0" data-id="index_v1" seamless></iframe><!--欢迎页放在这里-->
        </div>
    </div>
    <!--右侧部分结束-->
</div>

<!-- 全局js -->

<script src="${base_path}/resources/common/jquery/jquery.min.js"></script>
<script src="${base_path}/resources/common/plugins/layer/layer.min.js"></script>
<script src="${base_path}/resources/common/boostrap/js/bootstrap.min.js"></script>
<script src="${base_path}/resources/common/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="${base_path}/resources/common/plugins/slimscroll/jquery.slimscroll.min.js"></script>

<script src="${base_path}/resources/js/index.js"></script>
<!-- 自定义js -->

<script src="${base_path}/resources/common/nav_js/index.js"></script>
<script type="application/javascript" src="${base_path}/resources/js/teacher/jquery-form.js"></script>
<script>
    $(document).ready(function(){
        $('#form_stu_excel').ajaxForm({

            success: function (result) {
                console.log('result.data.count:'+result.data.count);
//                console.log('result.data.count:'+result.data.count);
                var count = result.data.count;
                var fail_count = result.data.fail_count;
//                var count = result.data.count;
                var stu_upload_result =  $('#stu_upload_result');
                stu_upload_result.addClass('alert-success');
                stu_upload_result.text("学生信息上传成功"+count+'条'+"失败"+fail_count+"条");
                stu_upload_result.show();
            },
            error: function () {
                $('#stu_upload_result').addClass('alert-danger').text("学生信息上传失败，请检查网络状态").show();
            }

        });
    })
   function pop_file_window() {
       $('#stu_excel').click();
   }
   function upload_excel() {
       console.log("上传excel表");
       $('#form_stu_excel').submit();//提交表单
   }
</script>
</body>
</html>

