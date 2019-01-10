<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title> - 基础表格</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link rel="shortcut icon" href="favicon.ico">

    <link href="${base_path}/resources/common/boostrap/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="${base_path}/resources/css/teacher/iCheck/custom.css" rel="stylesheet">
    <link href="${base_path}/resources/common/nav_css/animate.css" rel="stylesheet">
    <link href="${base_path}/resources/common/nav_css/style.css?v=4.1.0" rel="stylesheet">
    <link href="${base_path}/resources/common/boostrap/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <style type="text/css">
        .ms-controller{
            visibility: hidden
        }
    </style>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>学生信息</h5>
                </div>
                <div class="ms-controller" ms-controller="stuInfo">
                <div class="ibox-content" >
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>姓名</th>
                            <th>性别</th>
                            <th>学号</th>
                            <th>身份证号</th>
                            <th>电话</th>
                            <th>系部</th>
                            <th>专业</th>
                            <th>班级</th>
                            <th>辅导员</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>{{data1.name}}</td>
                            <td>{{data1.gender==0?'男':''}}{{data1.gender==1?'女':''}}</td>
                            <td>{{data1.id}}</td>
                            <td>{{data1.IDcard}}</td>
                            <td>{{data1.tel}}</td>
                            <td>{{data1.department}}</td>
                            <td>{{data1.majorName}}</td>
                            <td>{{data1.stuClass}}</td>
                            <td>{{data1.instructor}}</td>
                        </tr>
                        </tbody>
                    </table>
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>所属实验室</th>
                            <th>加入实验室时间</th>
                            <th>离开实验室时间</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>{{data1.labName}}</td>
                            <td>{{data1.time}}</td>
                            <td>{{data1.outTime}}</td>
                        </tr>
                        </tbody>
                    </table>
                    <%--<table class="table table-bordered">--%>
                        <%--<thead>--%>
                        <%--<tr>--%>
                            <%--<th style="text-align: center;" colspan=7>每期总成绩</th>--%>
                        <%--</tr>--%>
                        <%--</thead>--%>
                        <%--<tbody>--%>
                        <%--<tr>--%>
                            <%--<td width="5%"></td>--%>
                            <%--<td>大一上</td>--%>
                            <%--<td>大一下</td>--%>
                            <%--<td>大二上</td>--%>
                            <%--<td>大二下</td>--%>
                            <%--<td>大三上</td>--%>
                            <%--<td>大三下</td>--%>
                        <%--</tr>--%>

                        <%--<tr ms-for="el in @stuScoreList">--%>
                            <%--<td>{{el.course}}</td>--%>
                            <%--<td>{{el.one1}}</td>--%>
                            <%--<td>{{el.one2}}</td>--%>
                            <%--<td>{{el.two1}}</td>--%>
                            <%--<td>{{el.two2}}</td>--%>
                            <%--<td>{{el.three1}}</td>--%>
                            <%--<td>{{el.three2}}</td>--%>
                        <%--</tr>--%>
                        <%--</tbody>--%>
                    <%--</table>--%>

                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th style="text-align: center;" colspan=8>获奖信息</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td width="4%">编号</td>
                            <td>奖项名称</td>
                            <td>奖项级别</td>
                            <td>奖项等级</td>
                            <td>获奖类型</td>
                            <td>获奖官网</td>
                            <td>获奖时间</td>
                            <td>审核结果</td>
                        </tr>
                        <tr ms-for="el in @stuPrizeList">
                            <td width="2%">{{el.id}}</td>
                            <td>{{el.prize_name}}</td>
                            <td>{{el.region}}</td>
                            <td>{{el.rank}}</td>
                            <td>{{el.category}}</td>
                            <td>{{el.url}}</td>
                            <td>{{el.time}}</td>
                            <td>{{el.is_checked==1?'审核通过':'未审核'}}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- 全局js -->
<script src="${base_path}/resources/common/jquery.min.js"></script>
<script src="${base_path}/resources/common/boostrap/js/bootstrap.min.js?v=3.3.6"></script>
<script src="${base_path}/resources/js/index.js"></script>
<script src="${base_path}/resources/js/teacher/icheck.min.js"></script>
<script src="${base_path}/resources/common/avalon/avalon.js"></script>
<script src="${base_path}/resources/common/layer/2.4/layer.js"></script>
<script>
    var info = avalon.define({
        $id: "stuInfo",
        data: getStuInfo(),
        data1: {},
        stuPrizeList: {},
        stuScoreList: {},

    });
    info.data1 = info.data.stuInfoMap;
    info.stuPrizeList = info.data.stuPrizeList;
    info.stuScoreList = info.data.stuScoreList;
    //	avalon.scan();
    //alert(model.fileArray);
    function getStuInfo() {
        var jsonObj;
        ajax(
            {
                contentType: "application/x-www-form-urlencoded;charset=utf-8",
                async: false,
                type: 'get',
                url: 'viewStuInfo',
                success: function (data) {

                    jsonObj = data.data;
                },
                error: function () {
                    layer.msg("连接失败系统异常");
                    jsonObj = "";
                }
            })
        return jsonObj;
    }
</script>
</body>
</html>