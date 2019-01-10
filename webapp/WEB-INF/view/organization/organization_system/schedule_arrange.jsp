<%@ page import="com.nsu.common.Constants" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${base_path}/resources/common/lib/html5shiv.js"></script>
    <script type="text/javascript" src="${base_path}/resources/common/lib/respond.min.js"></script>
    <![endif]-->
    <link rel="stylesheet" type="text/css" href="${base_path}/resources/common/static/h-ui/css/H-ui.min.css"/>
    <link rel="stylesheet" type="text/css" href="${base_path}/resources/common/static/h-ui/css/H-ui.ie.css"/>
    <link rel="stylesheet" type="text/css" href="${base_path}/resources/common/static/h-ui.admin/css/H-ui.admin.css"/>
    <link rel="stylesheet" type="text/css" href="${base_path}/resources/common/lib/Hui-iconfont/1.0.8/iconfont.css"/>
    <link rel="stylesheet" type="text/css" href="${base_path}/resources/common/static/h-ui.admin/skin/green/skin.css"
          id="skin"/>
    <link rel="stylesheet" type="text/css" href="${base_path}/resources/common/static/h-ui.admin/css/style.css"/>
    <link rel="stylesheet" href="${base_path}/resources/common/lib/jQuery.cxCalendar-1.5.3/css/jquery.cxcalendar.css">
    <link rel="stylesheet" type="text/css" href="${base_path}/resources/common/laypage/skin/laypage.css"/>
    <link rel="stylesheet" href="${base_path}/resources/common/lib/jquery-flexselect-master/flexselect.css"
          type="text/css" media="screen"/>

    <!--[if IE 6]>
    <script type="text/javascript" src="${base_path}/resources/common/lib/DD_belatedPNG_0.0.8a-min.js"></script>
    <script>DD_belatedPNG.fix('*');</script>
    <![endif]-->
    <title>查看赛程</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 机构管理 <span class="c-gray en">&gt;</span> 赛程安排 <a
        class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="" title="刷新"><i
        class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
    <div class="mt-20">

        <div class="cl pd-5 bg-1 bk-gray mt-20">

            <demo class="text-r f-r">


                <form>

                    <input type="text" class="input-text" style="width:350px" placeholder="请输入要搜索的地区" id="search_info">
                    <span class="btn btn-success radius" onclick="search_info()"><i class="Hui-iconfont"></i>查询</span>
                </form>
            </demo>


        </div>
        <table class="table table-border table-bordered table-bg table-hover mt-20">
            <thead class="text-c">
            <tr>
                <th>编号</th>
                <th>主队队徽</th>
                <th>主队</th>
                <th>开始时间</th>
                <th>结束时间</th>
                <th>地区</th>
                <th>客队队徽</th>
                <th>客队</th>
                <th>现场管理员账号</th>
                <th>比分</th>
                <th>操作</th>
            </tr>
            </thead>


            <tbody class="text-c" ms-controller="for_w">
            <tr ms-for="($index,value) in json">


                <td>{{$index + 1}}</td>
                <td>

                    <img style="width:50px;height:50px;"

                         ms-attr="{src: basePath+value.team_h_badge}"
                         alt="LOGO" class="img_logo"></td>
                <td>
                    <span>{{value.r_h_team_name}}</span>
                </td>

                <td>
                    <!-- <input type="text" value="2012-05-15 21:05" id="datetimepicker"> -->
                    <span>{{value.r_start_time}}</span>
                </td>


                <td>
                    <span>{{value.r_end_time}}</span>
                </td>

                <td>
                    <span>{{value.r_region}}</span>
                </td>


                <td>
                    <img style="width:50px;height:50px" ms-attr="{src: basePath+value.team_v_badge}" alt="LOGO"

                         class="img_logo"/>
                </td>
                <td>
                    <span>{{value.r_v_team_name}}</span>
                </td>


                <td>
                    <span>{{value.a_username}}</span>
                </td>

                <td>
                    <span>{{value.h_score}}:{{value.v_score}}</span>

                </td>

                <%--状态位： 1.启用 2.结束 3.删除--%>
                <td>
                    <div ms-if="value.r_status ==1">
                        <a class="btn btn-success-outline radius mr-10" ms-click="@editSchedue(value.r_id)">修改赛程</a>
                        <a class="btn btn-success-outline radius mr-10" ms-click="@deleteSchedue(value.r_id)">删除赛程</a>
                        <a class="btn btn-success-outline radius mr-10"
                           ms-click="@resetPwd(value.a_username)">重置现场管理员密码</a>
                    </div>
                    <div ms-if="value.r_status ==2">
                        <button class="btn btn-warning radius">已结束</button>
                    </div>
                    <div ms-if="value.r_status ==3">
                        <button class="btn btn-warning radius">已删除</button>
                    </div>

                </td>
            </tr>

            </tbody>
        </table>

        <div id="page"></div>

    </div>

    <input type="text" id="base_path" hidden="" value="${base_path}">
    <%--后台校验错误信息提示--%>
    <input type="text" id="error" hidden="" value="${error}">
    <input type="text" id="com_id" hidden="" value="${com_id}">
    <input type="text" id="stage" hidden="" value="${stage}">
    <%--修改赛程信息模态框--%>
    <div id="modal-demo" class="modal fade" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog" style="width: 750px;">
            <div class="modal-content radius">
                <div class="modal-header">
                    <h3 class="modal-title">修改赛程</h3>
                </div>
                <div class="modal-body">


                    <form id="select_form" class="ml-30">

                        <div id="data_hidden"></div>

                        <div class="row cl mb-20">
                            主队名:
                            <select class="special-flexselect" id="school1" name="R_H_TEAM_ID" tabindex="1"
                                    data-placeholder="请输入请求名"></select>
                        </div>

                        <div class="row cl">
                            <span style="display:block; width: 345px;float:left; margin-bottom: 5px;">开始时间：</span>
                            <span style="display:block; width: 300px; float:left; margin-bottom: 5px;">结束时间：</span>
                        </div>
                        <div class="row cl">
                            <input class="input-text col-md-5 date_a leftstart" name="R_START_TIME" id="R_START_TIME"
                                   type="text" readonly>
                            <span class="f-l ml-15 mr-15">至</span>
                            <input class="input-text col-md-5 date_a rightend f-l" name="R_END_TIME" ID="R_END_TIME"
                                   type="text" readonly>
                        </div>

                        <div class="row cl mt-20">
                            <span class="f-l">地&nbsp;&nbsp;&nbsp;区:&nbsp;</span><input type="text" name="R_REGION"
                                                                                       class="input-text col-md-5"
                                                                                       id="R_REGION">
                        </div>
                        <div class="row cl mt-20">
                            客队名:
                            <select class="special-flexselect" id="school2" name="R_V_TEAM_ID" tabindex="1"
                                    data-placeholder="请输入球队名"></select>
                        </div>


                    </form>


                </div>
                <div class="modal-footer">
                    <button class="btn btn-primary" onclick="updateSchedue(${com_id})">确定</button>
                    <button class="btn" data-dismiss="modal" aria-hidden="true" onclick="reTips();">关闭</button>
                </div>
            </div>
        </div>
    </div>

    <%--重置现场管理员密码--%>
    <div id="modal-demm" class="modal fade" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog" style="width: 750px;">
            <div class="modal-content radius">
                <div class="modal-header">
                    <h3 class="modal-title">重置密码</h3>
                </div>
                <div class="modal-body" id="sub_info">


                    <div id="data_account" class="row cl mt-20 pl-30"></div>

                    <div class="row cl mt-20 pl-30">
                        <span class="f-l">输入密码:&nbsp;</span><input type="password" name="a_password"
                                                                   class="input-text col-md-5"
                                                                   id="a_password">
                    </div>

                    <div class="row cl mt-20 pl-30">
                        <span class="f-l ">重复密码:&nbsp;</span><input type="password" name="re_password"
                                                                    class="input-text col-md-5"
                                                                    id="re_password">
                    </div>

                </div>
                <div class="modal-footer">
                    <button class="btn btn-primary" onclick="edit_schedulePwd();">确定</button>
                    <button class="btn" data-dismiss="modal" aria-hidden="true" onclick="reTips();">关闭</button>
                </div>
            </div>
        </div>
    </div>

</div>
<!--_footer 作为公共模版分离出去-->

<script type="text/javascript" src="${base_path }/resources/common/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="${base_path }/resources/common/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="${base_path }/resources/common/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="${base_path }/resources/common/static/h-ui.admin/js/H-ui.admin.js"></script>
<script type="text/javascript"
        src="${base_path }/resources/common/lib/jquery.contextmenu/jquery.contextmenu.r2.js"></script>

<!--/_footer 作为公共模版分离出去-->
<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="${base_path}/resources/common/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript"
        src="${base_path}/resources/common/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${base_path}/resources/common/avalon/avalon.js"></script>
<script type="text/javascript" src="${base_path}/resources/common/laypage/laypage.js"></script>
<script src="${base_path}/resources/common/lib/jquery-flexselect-master/liquidmetal.js" type="text/javascript"></script>
<script src="${base_path}/resources/common/lib/jquery-flexselect-master/jquery.flexselect.js"
        type="text/javascript"></script>
<script src="${base_path}/resources/common/lib/jQuery.cxCalendar-1.5.3/js/jquery.cxcalendar.min.js"></script>
<script src="${base_path}/resources/common/lib/jQuery.cxCalendar-1.5.3/js/jquery.cxcalendar.languages.js"></script>

<script type="text/javascript">
    var token = '${_csrf.token }';
</script>
<script src="${base_path}/resources/js/organization/page/schedule_arrange.js" type="text/javascript"></script>
<script>
    function reTips() {
        $(".layui-layer-tips").remove();
    }
</script>
</body>
</html>