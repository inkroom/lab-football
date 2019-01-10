<%@ page import="com.nsu.controller.student.classmanage.ClassManagerController" %>
<%@ page import="com.nsu.controller.student.score.ScoreController" %>
<%@ page import="java.util.Map" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

    <title>积分榜</title>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%--<link rel="stylesheet" href="${base_path}/resources/common/h-ui/css/H-ui.css"/>--%>
    <link rel="stylesheet" href="${base_path}/resources/common/h-ui/css/H-ui.min.css"/>
    <%--<link rel="stylesheet" href="${base_path}/resources/common/Hui-iconfont/1.0.8/iconfont.css"/>--%>
    <link rel="stylesheet" href="${base_path}/resources/common/Hui-iconfont/1.0.8/iconfont.min.css"/>
    <link rel="stylesheet" href="${base_path}/resources/common/layui/layui.css" media="all"/>
    <%--<link rel="stylesheet" href="${base_path}/resources/common/datatables/1.10.0/jquery.dataTables.min.css"/>--%>
    <%--<link rel="stylesheet" href="${base_path}/resources/common/datatables/1.10.0/dataTables.jqueryui.min.css"/>--%>

    <%--<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/ju-1.11.4/jqc-1.12.4/dt-1.10.15/se-1.2.2/datatables.min.css"/>--%>


    <link rel="stylesheet" href="${base_path}/resources/css/student/mystyle.css"/>
    <link rel="stylesheet" href="${base_path}/resources/css/student/reset.css"/>
    <link rel="stylesheet" href="${base_path}/resources/css/student/style.css"/>



    <style type="text/css">
        .page {
            display: inline-block;
            border: 1px rgb(225, 226, 227) solid;
            padding: 5px;
            margin: 10px;
        }

        .page:hover {
            text-decoration: none;
            border-color: blueviolet;
        }

        .page-none {
            color: black;
            font-weight: bolder;
            padding: 5px;
            margin: 10px;
        }

        .page-none:hover {
            text-decoration: none;
            color: black;
            cursor: text;
        }
    </style>
</head>

<body>

<div class="c-white text-c f-24">
    <div class="header1">四川省中小学生艺术素质测评系统</div>
</div>

<div class="container">
    <div class="row">
        <div class="col-xs-1 col-md-5 col-sm-6 hidden-xs">
            <img src="${base_path}/resources/img/sun.png" alt="" class="img-responsive"/>
        </div>
        <div class="col-xs-1 col-md-5 col-sm-6 mt-50 hidden-xs">
            <div id="nav">
                <ul>
                    <li><a href="#" style="background-color: #FF633C;border-radius: 10px 0 0 10px;">首页</a></li>
                    <li><a href="#">考试类型</a>
                        <ul>
                            <li><a href="#">美术考试</a></li>
                            <li><a href="#">音乐 考试</a></li>
                        </ul>
                    </li>
                    <li><a href="#">练习中心</a>
                        <ul>
                            <li><a href="#">美术练习</a></li>
                            <li><a href="#">音乐练习</a></li>
                        </ul>
                    </li>
                    <li><a href="#">积分榜单</a></li>
                    <li><a href="#">我的空间</a></li>
                    <li><a href="#">个人中心</a>
                        <ul>
                            <li><a href="#">考试通知</a></li>
                            <li><a href="#">切换账户</a></li>
                            <li><a href="#">退出登录</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-3 visible-xs ">
            <div id="nav-a">
                <ul>
                    <li><a href="#" style="background-color: #FF633C;border-radius: 10px 10px 0 0;">首页</a></li>
                    <li><a href="#">考试类型</a>
                        <ul>
                            <li><a href="#">美术考试</a></li>
                            <li><a href="#">音乐 考试</a></li>
                        </ul>
                    </li>
                    <li><a href="#">练习中心</a>
                        <ul>
                            <li><a href="#">美术练习</a></li>
                            <li><a href="#">音乐练习</a></li>
                        </ul>
                    </li>
                    <li><a href="${base_path}/student/score/index.html">积分榜单</a></li>
                    <li><a href="#">我的空间</a></li>
                    <li><a href="#">个人中心</a>
                        <ul>
                            <li><a href="#">考试通知</a></li>
                            <li><a href="#">切换账户</a></li>
                            <li><a href="#">退出登录</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>

<div class="cnt" style="padding-bottom: 5%">
    <nav class="breadcrumb">
        <i class="Hui-iconfont">&#xe67f;</i>
        <a href="#">首页</a>
        <span class="c-666 en">&gt;</span>
        <a href="#">积分榜</a>
    </nav>
    <div class="text-r mr-20 mb-30">
        <p><i class="icon Hui-iconfont" style="color:#555555; font-size:40px">&#xe60a;</i><% request.setAttribute("key",ClassManagerController.KEY);
        request.setAttribute("number",ScoreController.KEY_NUMBER);request.setAttribute("score",ScoreController.KEY_SCORE);%>
            <i class="icon Hui-iconfont"
               style="color:#999999; font-size:20px">&#xe647;</i>刷题数：${sessionScope.get(requestScope.get("key")).get(requestScope.get("number"))}
            <i class="icon Hui-iconfont"
               style="color:#990000; font-size:20px">&#xe656;</i>积分：${sessionScope.get(requestScope.get("key")).get(requestScope.get("score"))}
    </div>

    <div class="clearfix text-c">
        <label>
            <input type="checkbox" class="radio_btn" id="blur">模糊搜索
        </label>
        <span class="select-box radius" style="width:130px;">
                <select class="select" id="type">
    				<option value="0" selected>按学校名字搜索</option>
                    <option value="1">按学生姓名搜索</option>
  				</select>
                </span>
        <input type="text" placeholder="请输入关键词" class="input-text ac_input" name="search_text" value="${school}"
               id="content" autocomplete="off" style="width:180px">
        <button type="submit" class="btn btn-primary" id="search_button">搜索</button>

        <a class="text-c f-r mt-50">
                <span class="select-box radius">
                     <select class="select" size="1" id="school">
    					<option value="">查看学校排行</option>
                         <c:forEach var="school" items="${requestScope.get('schools')}">
                             <option value="${school.getName()}" ${requestScope.get("school").equals(school.getName())?'selected':''}>
                                     ${school.getName()}
                             </option>
                         </c:forEach>
  					</select>
                </span>
        </a>
    </div>
    <p class="text-c f-24">
        <i class="icon Hui-iconfont" style="color:#FF0; font-size:36px">&#xe630;</i>
        <i class="icon Hui-iconfont" style="color:#FF0; font-size:48px">&#xe630;</i>
        <i class="icon Hui-iconfont" style="color:#FF0; font-size:36px">&#xe630;</i>
        <i class="icon Hui-iconfont" style="color:#FF0; font-size:36px">&#xe630;</i>
        <i class="icon Hui-iconfont" style="color:#FF0; font-size:48px">&#xe630;</i>
        <i class="icon Hui-iconfont" style="color:#FF0; font-size:36px">&#xe630;</i>
        积分榜
        <i class="icon Hui-iconfont" style="color:#FF0; font-size:36px">&#xe630;</i>
        <i class="icon Hui-iconfont" style="color:#FF0; font-size:48px">&#xe630;</i>
        <i class="icon Hui-iconfont" style="color:#FF0; font-size:36px">&#xe630;</i>
        <i class="icon Hui-iconfont" style="color:#FF0; font-size:36px">&#xe630;</i>
        <i class="icon Hui-iconfont" style="color:#FF0; font-size:48px">&#xe630;</i>
        <i class="icon Hui-iconfont" style="color:#FF0; font-size:36px">&#xe630;</i>
    </p>
    <br>

    <table id="scoreBoardTable" class="display table table-border table-bordered table-hover scoreboard-table"
           cellspacing="0" width="100%">
        <thead>
        <tr class="text-c">
            <th>姓名<i class="icon Hui-iconfont" style="color:#999999; font-size:20px">&#xe70d;</i></th>
            <th>年级<i class="icon Hui-iconfont" style="color:#999999; font-size:21px">&#xe611;</i></th>
            <th>学校<i class="icon Hui-iconfont" style="color:#999999; font-size:21px">&#xe643;</i></th>
            <th>刷题数<i class="icon Hui-iconfont" style="color:#999999; font-size:20px">&#xe647;</i></th>
            <th>积分<i class="icon Hui-iconfont" style="color:#990000; font-size:20px">&#xe656;</i></th>
            <th>排行<i class="icon Hui-iconfont" style="color:#990000; font-size:20px">&#xe66d;</i></th>
        </tr>
        </thead>
    </table>
</div>

<div class="foot1">
    <input type="hidden" id="_csrf" value="${_csrf.getToken()}"/>
</div>
<div class="foot2">
    <div style="width: 100%;height: 30px;">
        <footer class="footer ">
            <div class="container">
                <div class="row footer-top">
                    <div class="col-sm-6  col-lg-6 col-lg-offset-1 f-28 c-white">
                        <div class="row about">
                            <div class="col-xs-3">
                                <h4>关于</h4>
                                <ul class="list-unstyled">
                                    <li class="mt-30"><a href="#" class="c-white"><span class="c-white f-24">音乐考试</span></a>
                                    </li>
                                    <li class="mt-30"><a href="#" class="c-white"><span class="c-white f-24">美术考试</span></a>
                                    </li>
                                    <li class="mt-30"><a href="#" class="c-white"><span class="c-white f-24">音乐练习</span></a>
                                    </li>
                                    <li class="mt-30"><a href="#" class="c-white"><span class="c-white f-24">美术练习</span></a>
                                    </li>
                                </ul>
                            </div>
                            <div class="col-xs-3">
                                <h4>下载中心</h4>
                                <ul class="list-unstyled">
                                    <li class="mt-30"><a href="../web/filedownload.html" class="c-white"><span
                                            class="c-white f-24">资料下载</span></a></li>
                                    <li class="mt-30"><a href="../web/myfile.html" class="c-white"><span
                                            class="c-white f-24">档案下载</span></a></li>
                                </ul>
                            </div>
                            <div class="col-xs-3">
                                <h4>个人中心</h4>
                                <ul class="list-unstyled">
                                    <li class="mt-30"><a href="../web/mycertificate.html" class="c-white"><span
                                            class="c-white f-24">我的证书</span></a></li>
                                    <li class="mt-30"><a href="../web/upporject.html" class="c-white"><span
                                            class="c-white f-24">课外学习</span></a></li>
                                    <li class="mt-30"><a href="../web/upporject.html" class="c-white"><span
                                            class="c-white f-24">实践活动</span></a></li>
                                    <li class="mt-30"><a href="../web/upporject.html" class="c-white"><span
                                            class="c-white f-24">特长专长</span></a></li>
                                </ul>
                            </div>
                            <div class="col-xs-3">
                                <h4><a href="${base_path}/student/score/index.html"><span
                                        class="c-white"> 积分榜单</span></a></h4>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </footer>
    </div>
    <div id="datepicker" class="calendar visible-lg"></div>

    <div class="row footer-bottom col-sm-12  col-lg-12">
        <hr/>
        <ul class="list-inline text-center">
            <li class="text-c">版权所有@四川省中小学生艺术测试系统</li>
        </ul>
    </div>
</div>

<%--<script src="${base_path}/resources/common/jquery/1.9.1/jquery.js"></script>--%>
<script src="${base_path}/resources/common/jquery/1.9.1/jquery.min.js"></script>
<%--<script src="${base_path}/resources/common/h-ui/js/H-ui.js"></script>--%>
<script src="${base_path}/resources/common/h-ui/js/H-ui.min.js"></script>
<script src="${base_path}/resources/common/jquery.SuperSlide/2.1.1/jquery.SuperSlide.min.js"></script>
<script src="${base_path}/resources/common/laypage/1.2/laypage.js"></script>
<script src="${base_path}/resources/common/other/respond.min.js"></script>
<script src="${base_path}/resources/common/layui/layui.js"></script>
<script src="${base_path}/resources/common/layer/2.4/layer.js"></script>
<%--<script src="${base_path}/resources/common/datatables/1.10.0/jquery.dataTables.min.js"></script>--%>
<%--<script src="${base_path}/resources/common/datatables/1.10.0/dataTables.jqueryui.min.js"></script>--%>
<script src="${base_path}/resources/js/student/jquery-ui.js"></script>
<script src="${base_path}/resources/js/student/index.js"></script>
<script src="${base_path}/resources/js/student/dataTable.js"></script>
<script type="text/javascript" src="${base_path}/resources/js/student/scoreBoard.js"></script>

</body>
</html>
