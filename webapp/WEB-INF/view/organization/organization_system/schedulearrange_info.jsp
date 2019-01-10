<%@ page import="com.nsu.common.Constants" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <!--[if IE 6]>
    <script type="text/javascript" src="${base_path}/resources/common/lib/DD_belatedPNG_0.0.8a-min.js"></script>
    <script>DD_belatedPNG.fix('*');</script>
    <![endif]-->
    <style type="text/css">
        .height {
            height: 71px;
            line-height: 71px
        }

        .w-150 {
            width: 150px;
            height: 35px
        }

        .img_logo {
            width: 50px;
            height: 50px
        }

        .input-text {
            height: 35px;
            border: none;
            text-align: center;
        }

        .btn-default:hover a {
            text-decoration: none;
            color: black
        }
    </style>
    <title>赛程安排表</title>
</head>
<body>

<div class="page-container text-c">
    <form id="myform">
        <table class="table table table-border table-bordered table-bg mt-20" id="match">
            <thead class="text-c">
            <tr class="text-c">
                <th>队名</th>
                <th>VS</th>
                <th>地区</th>
                <th>队名</th>
                <th>管理员账号</th>
                <th>管理员密码</th>
            </tr>
            </thead>
            <tbody>

            <c:forEach items="${list}" var="ls">

                <tr class="text-c">
                    <td class="text-l">
                        <img src="${base_path}/${ls.R_H_TEAM_Pic}" alt="LOGO" class="img_logo">
                        <span>${ls.R_H_TEAM_NAME}</span>
                    </td>
                    <td>
                        <!-- <input type="text" value="2012-05-15 21:05" id="datetimepicker"> -->
                        <span>${ls.R_START_TIME}</span>
                        <span>至</span>
                        <span>${ls.R_END_TIME}</span>
                    </td>
                    <td>${ls.R_REGION}</td>
                    <td class="text-l">
                        <img src="${base_path}/${ls.R_V_TEAM_Pic}" alt="LOGO" class="img_logo">
                        <span>${ls.R_V_TEAM_NAME}</span>
                    </td>
                    <td>${ls.A_USERNAME}</td>
                    <td>${ls.A_PASSWORD}</td>
                </tr>

            </c:forEach>
            </tbody>
        </table>
        <div class="btn-group mt-30">
            <span class="btn btn-default radius"><a onClick="modaldemo()">返回</a></span>
            <a class="btn btn-success radius" id="exportExcel" name="exportExcel">导出excle</a>
        </div>
    </form>

    <input type="text" id="base_path" hidden="" value="${base_path}">
    <%--后台校验错误信息提示--%>
    <input type="text" id="error" hidden="" value="${error}">
</div>


<div id="modal-demo" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content radius">
            <div class="modal-header">
                <h3 class="modal-title">警告:</h3>
                <a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
            </div>
            <div class="modal-body">
                <p class="label label-danger"
                   style="background-color: #ffffff;padding: 5px 4px;font-size: 14.844px;color: #dd514c;">
                    请确认已保存现场管理员账号,离开本页面后将不能再次进入此页面!</p>
            </div>
            <div class="modal-footer">
                <button class="btn" data-dismiss="modal" aria-hidden="true">返回</button>
                <button class="btn btn-primary" id="return_info">确定</button>
            </div>
        </div>

    </div>
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
    <script type="text/javascript" src="${base_path}/resources/js/organization/page/schedulearrange_info.js"></script>

    <script>
        function modaldemo() {
            $("#modal-demo").modal("show")
        }
    </script>
</body>
</html>
