<%@ page import="com.nsu.common.Constants" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${base_path}/resources/common/lib/html5shiv.js"></script>
    <script type="text/javascript" src="${base_path}/resources/common/lib/respond.min.js"></script>
    <![endif]-->
    <link rel="stylesheet" type="text/css" href="${base_path}/resources/common/static/h-ui/css/H-ui.min.css" />
    <link rel="stylesheet" type="text/css" href="${base_path}/resources/common/static/h-ui/css/H-ui.ie.css" />
    <link rel="stylesheet" type="text/css" href="${base_path}/resources/common/static/h-ui.admin/css/H-ui.admin.css" />
    <link rel="stylesheet" type="text/css" href="${base_path}/resources/common/lib/Hui-iconfont/1.0.8/iconfont.css" />
    <link rel="stylesheet" type="text/css" href="${base_path}/resources/common/static/h-ui.admin/skin/green/skin.css" id="skin" />
    <link rel="stylesheet" type="text/css" href="${base_path}/resources/common/static/h-ui.admin/css/style.css" />
    <link rel="stylesheet" type="text/css" href="${base_path}/resources/common/laypage/skin/laypage.css" />

    <!--[if IE 6]>
    <script type="text/javascript" src="${base_path}/resources/common/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
    <script>DD_belatedPNG.fix('*');</script>
    <![endif]-->
    <title>赛程安排</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 机构管理 <span class="c-gray en">&gt;</span> 赛程安排 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
    <div class="mt-20">

        <div class="cl pd-5 bg-1 bk-gray mt-20">

            <demo class="text-r f-r">


                <form >
                <input type="text" class="input-text" style="width:350px" placeholder="请输入要搜索的赛事"  id="search_info">
                    <span class="btn btn-success radius" onclick="search_info();">查询</span>
                </form>


            </demo>

        </div>
        <table class="table table-border table-bordered table-bg table-hover mt-20">
            <thead class="text-c">
            <tr>
                <th>编号</th>
                <th>赛事名称</th>
                <th>比赛级别</th>
                <th>比赛组别</th>
                <th>比赛赛制</th>
                <th>操作</th>
            </tr>
            </thead>

            <tbody class="text-c"    ms-controller="for_w">

            <tr  ms-for="($index,value) in json">

                <td>{{$index + 1}}</td>
                <td>{{value.com_name}}</td>

                <%--1省，2市，3县，4校，5不限--%>
                <td>
                    <div ms-if="value.com_big_level ==1">省级</div>
                    <div ms-if="value.com_big_level ==2">市级</div>
                    <div ms-if="value.com_big_level ==3">县级</div>
                    <div ms-if="value.com_big_level ==4">校级</div>
                    <div ms-if="value.com_big_level ==5">不限</div>
                </td>

                   <%--比赛组别：1小学，2中学，3高中，4大学  ，5不限--%>
                <td >
                    <div ms-if="value.com_level ==1">小学</div>
                    <div ms-if="value.com_level ==2">初中</div>
                    <div ms-if="value.com_level ==3">高中</div>
                    <div ms-if="value.com_level ==4">大学</div>
                    <div ms-if="value.com_level ==5">不限</div>
                </td>
                <%--足球组别：1、 5人， 2、7人  3、11人 4、不限--%>

                <td >

                    <div ms-if="value.com_grounp ==1">5人</div>
                    <div ms-if="value.com_grounp ==2">7人</div>
                    <div ms-if="value.com_grounp ==3">11人</div>
                    <div ms-if="value.com_grounp ==4">不限</div>

                </td>

                <td>
                    <div ms-if="value.com_status ==1">
                        <a ms-attr="{href:'${base_path}/org/'+value.com_id+'/'+value.com_level+'/'+value.com_start+'/'+value.com_end+'/arrangement_view.html'}" class="btn btn-success-outline radius mr-10">安排赛程</a>
                        <a ms-attr="{href:'${base_path}/org/'+value.com_id+'/'+value.com_level+'/schedule_view.html'}"  class="btn btn-success-outline radius mr-10">查看赛程</a>
                    </div>
                    <div ms-if="value.com_status ==2">
                        <button class="btn btn-warning radius">已结束</button>
                    </div>
                    <div ms-if="value.com_status ==3">
                        <button class="btn btn-warning radius">已删除</button>
                    </div>

                </td>
            </tr>

            </tbody>
        </table>

        <div id="page" class="mt-30"></div>
    </div>

    <input type="text" id="base_path" hidden="" value="${base_path}">
    <%--后台校验错误信息提示--%>
    <input type="text" id="error" hidden="" value="${error}">
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="${base_path }/resources/common/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="${base_path }/resources/common/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="${base_path }/resources/common/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="${base_path }/resources/common/static/h-ui.admin/js/H-ui.admin.js"></script>
<script type="text/javascript" src="${base_path }/resources/common/lib/jquery.contextmenu/jquery.contextmenu.r2.js"></script>

<!--/_footer 作为公共模版分离出去-->
<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="${base_path}/resources/common/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="${base_path}/resources/common/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${base_path}/resources/common/avalon/avalon.js"></script>
<script type="text/javascript" src="${base_path}/resources/common/laypage/laypage.js"></script>
<script type="text/javascript" src="${base_path}/resources/js/organization/page/competition_schedule.js"></script>
</body>
</html>