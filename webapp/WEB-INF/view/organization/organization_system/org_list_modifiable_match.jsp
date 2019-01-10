<%@ page import="java.util.Date" %>
<%@ page import="com.nsu.common.Constants" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:formatDate value="<%=new Date()%>" pattern="yyyy-MM-dd HH:mm:ss" var="nowTime"/>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${base_path }/resources/common/lib/html5shiv.js"></script>
    <script type="text/javascript" src="${base_path }/resources/common/lib/respond.min.js"></script>
    <![endif]-->
    <link rel="stylesheet" type="text/css" href="${base_path }/resources/common/static/h-ui/css/H-ui.reset.css"/>
    <link rel="stylesheet" type="text/css" href="${base_path }/resources/common/static/h-ui/css/H-ui.min.css"/>
    <link rel="stylesheet" type="text/css" href="${base_path }/resources/common/static/h-ui.admin/css/H-ui.admin.css"/>
    <link rel="stylesheet" type="text/css" href="${base_path }/resources/common/lib/Hui-iconfont/1.0.8/iconfont.css"/>
    <link rel="stylesheet" type="text/css" href="${base_path }/resources/common/static/h-ui.admin/skin/green/skin.css"/>

    <!--[if lt IE 9]>
    <script src="http://ie7-js.googlecode.com/svn/version/2.1(beta4)/IE9.js"></script>
    <![endif]-->
    <link rel="stylesheet" type="text/css" href="${base_path }/resources/common/static/h-ui.admin/css/style.css"/>
    <!--[if IE 6]>
    <script type="text/javascript" src="${base_path }/resources/common/lib/DD_belatedPNG_0.0.8a-min.js"></script>
    <script>DD_belatedPNG.fix('*');</script>
    <![endif]-->
    <link rel="stylesheet" href="${base_path }/resources/common/lib/jQuery.cxCalendar-1.5.3/css/jquery.cxcalendar.css">
    <link rel="stylesheet" type="text/css" href="${base_path }/resources/common/jquery/jquery_page/jquery.page.css"/>
    <link rel="stylesheet" type="text/css" href="${base_path}/resources/common/static/h-ui/css/laypage.css"/>
    <title>修改赛事</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 机构管理 <span class="c-gray en">&gt;</span> 修改赛事 <a
        class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px"
        href="javascript:location.replace(location.href='${base_path }/org/to_modify_match_view.html');" title="刷新"><i
        class="Hui-iconfont">&#xe68f;</i></a></nav>

<div class="page-container">
    <div class="cl pd-5 bg-1 bk-gray mt-20">
        <a class="text-r f-r">

            <input type="text" name="match_name" id="selectMatch" class="input-text" style="width:350px"
                   placeholder="请输入需要修改的赛事全称">
            <button type="submit" id="select" class="btn btn-success radius" name=""><i
                    class="Hui-iconfont">&#xe665;</i> 查询
            </button>

        </a>

    </div>


    <table class="table table-border table-bordered table-bg radius table-hover mt-20" id="listPage"
           ms-controller="listPage">
        <thead>
        <tr class="text-c">
            <th>赛事名称</th>
            <th>创建人</th>
            <th>所属机构</th>
            <th>创立时间</th>
            <th>操作</th>
        </tr>

        </thead>


        <tbody class="text-c" id="imgtbody">
        <tr ms-for="($index,value) in list">
            <td>{{value.match_name}}</td>
            <td>{{value.a_name}}</td>
            <td>{{value.o_name}}</td>
            <td>{{value.create_date}}</td>
            <td>
                <div ms-if="value.status ==1">
                    <button class="btn btn-success-outline radius" ms-click="modifyMatch(value.match_id)">修改</button>
                    <button class="btn btn-success-outline radius"
                            ms-click="deleteMatch(value.match_id,value.match_name)">删除
                    </button>
                </div>
                <div ms-if="value.status ==2">
                    <button class="btn btn-warning radius">已结束</button>
                </div>
                <div ms-if="value.status ==3">
                    <button class="btn btn-warning radius">已删除</button>
                </div>
            </td>
        </tr>
        </tbody>
    </table>
    <div class="page" id="page"></div>
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
<script type="text/javascript"
        src="${base_path }/resources/common/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script src="${base_path }/resources/common/lib/jQuery.cxCalendar-1.5.3/js/jquery.cxcalendar.min.js"></script>
<script src="${base_path }/resources/common/lib/jQuery.cxCalendar-1.5.3/js/jquery.cxcalendar.languages.js"></script>
<script type="text/javascript" src="${base_path}/resources/common/jquery/jquery_page/jquery.page.js"></script>
<script type="text/javascript" src="${base_path}/resources/js/player/page/laypage.js"></script>
<script type="text/javascript" src="${base_path}/resources/common/avalon/avalon.js"></script>
<script type="text/javascript">
    var token = '${_csrf.token }';
</script>
<script type="text/javascript">
    $(function () {
        layer.ready(function () {
            if ('${modifyMatch_msg}' != '') {
                layer.msg('${modifyMatch_msg}', {icon: 0});
            }
        });
    });

    $("#select").click(function () {
        if ($("#selectMatch").val() == "") {
            layer.alert("请输入要搜索的赛事名称");
            return false;
        }
        var ss = $("#selectMatch").val();
        $.ajax({
            url: '${base_path }/org/selectmatch.action',
            type: "POST",
            data: {match_name: $("#selectMatch").val()},
            dataType: "json",
            success: function (data) {
                if (data.info != null) {
                    vm.list = data.info; //给 json 赋值
                    $("#page").hide();
                } else if (data.msg != null) {
                    layer.alert(data.msg);
                }

            },
            error: function (er) {
                console.log(er)
            }
        });
    });


</script>
<script type="text/javascript">

    var pageNum = 1;

    var vm = avalon.define({
        $id: "listPage",
        list: [],
        startRow: 0,
        endRow: 0,
        total: 0,
        modifyMatch: function (id) {
            window.location.href = '${base_path }/org/' + id + '/modify_match.action';
        },
        deleteMatch: function (id, name) {
            layer.confirm('是否确认删除？', {
                btn: ['确定', '取消'] //按钮
            }, function () {
                $.ajax({
                    url: "${base_path }/org/delete_match.action",    //请求的url地址
                    type: "post",   //请求方式
                    dataType: "json",   //返回格式为json
                    async: true,//请求是否异步，默认为异步，这也是ajax重要特性
                    data: {'match_id': id, 'match_name': name, '_csrf': token},   //参数值
                    beforeSend: function () {
                        //请求前的处理
                        console.log("form submit");
                    }, success: function (data) {
                        //请求成功时处理
                        if (data.status == "500") {
                            layer.alert(data.msg, {closeBtn: 0}, function () {
                                window.location.reload();
                            });
                        } else {
                            if (data.deleteMatch_msg != null) {
                                layer.alert(data.deleteMatch_msg, function (index) {
                                    getPage(pageNum);
                                    layer.close(index);
                                    window.location.reload();
                                }, true);
                            } else if (data.deleteMatch_msg_error != null) {
                                layer.alert(data.deleteMatch_msg_error, function (index) {
                                    window.location.href = '${base_path}/org/to_modify_match_view.html';
                                }, true);

                            }

                        }
                    }, complete: function () {
                        //请求完成的处理
                    }, error: function (XMLHttpRequest, textStatus, errorThrown) {
                        //请求出错处理

                        console.log(XMLHttpRequest);
                        console.log(textStatus);
                        console.log(errorThrown);
                    }

                })
            }, function () {

            });
        }
    })


    function getPage(pageNum) {
        $.ajax({
            url: "${base_path}/org/to_modify_match.action",    //请求的url地址
            type: "post",   //请求方式
            dataType: "json",   //返回格式为json
            async: false,//请求是否异步，默认为异步，这也是ajax重要特性
            data: {"pageNum": pageNum},   //参数值
            beforeSend: function () {
                //请求前的处理
                console.log("form submit");
            }, success: function (data) {
                //请求成功时处
                vm.list = data.list;
                vm.startRow = data.startRow;
                vm.endRow = data.endRow;
                laypage({
                    cont: 'page', // 容器。值支持id名、原生dom对象，jquery对像
                    pages: data.pages, // 通过后台拿到的总页数
                    curr: pageNum || 1, // 当前页
                    jump: function (obj, first) { // 触发分页后的回调
                        if (!first) { // 点击跳页触发函数自身，并传递当前页：obj.curr
                            getPage(obj.curr);
                        }
                    }
                });
                myDate = data;
            }, complete: function () {
                //请求完成的处理
            }, error: function (XMLHttpRequest, textStatus, errorThrown) {
                //请求出错处理
                console.log(XMLHttpRequest);
                console.log(textStatus);
                console.log(errorThrown);
            }
        });
    }

    //加载
    $(function () {
        $("#page").show();
        getPage(pageNum);
    });

</script>
</body>
</html>