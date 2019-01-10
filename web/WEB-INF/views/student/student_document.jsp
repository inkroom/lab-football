<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title> 公告文档</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link rel="shortcut icon" href="favicon.ico">
    <link href="${base_path}/resources/common/boostrap/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${base_path}/resources/common/boostrap/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="${base_path}/resources/css/student/footable.core.css" rel="stylesheet">

    <link href="${base_path}/resources/common/nav_css/animate.css" rel="stylesheet">
    <link href="${base_path}/resources/common/nav_css/style.css?v=4.1.0" rel="stylesheet">

</head>

<!-- 全局js -->
<script src="${base_path}/resources/common/jquery/jquery.min.js?v=2.1.4"></script>
<script src="${base_path}/resources/common/boostrap/js/bootstrap.min.js?v=3.3.6"></script>
<script src="${base_path}/resources/common/avalon/avalon.js"></script>
<script src="${base_path}/resources/js/index.js"></script>
<script src="${base_path}/resources/js/student/footable.all.min.js"></script>
<script src="${base_path}/resources/common/layer/2.4/layer.js"></script>

<!-- 自定义js -->
<script src="${base_path}/resources/js/student/content.js?v=1.0.0"></script>
<script>

    avalon.config({debug: false});
    avalon.define({

        $id: "fileList",
        data1: getArreyList(),
    });
    avalon.scan();

    //alert(model.fileArray);
    function getArreyList() {
        var jsonObj;
        ajax(
            {
                contentType: "application/x-www-form-urlencoded;charset=utf-8",
                async: false,
                type: 'get',
                url: 'stuFileList',
                success: function (data) {

                    jsonObj = data.data.noticeList;
                },
                error: function () {
                    layer.msg("连接失败系统异常");
                    jsonObj = "";
                }

            })
        return jsonObj;
    }

    function viewInfo(xx) {

        layer.alert(xx);
    }

</script>

<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">

    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>公告文档</h5>
                </div>
                <div class="ibox-content" ms-controller="fileList">

                    <table class="footable table table-stripped toggle-arrow-tiny" data-page-size="8">
                        <thead>
                        <tr>

                            <th data-toggle="true">发布时间</th>
                            <th>公告</th>
                            <th>详情</th>
                            <th>文档名称</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr ms-for="el in @data1">
                            <td>{{el.time}}</td>
                            <td>{{el.title}}</td>
                            <td>{{el.text}}</td>
                            <td>{{el.file_name}}</td>
                            <td>
                                <a ms-attr="{href: '${base_path}/student/downLoad?filePath='+@el.file_path}">下载</a>
                            </td>
                        </tr>
                        </tbody>

                        <tfoot>
                        <tr>
                            <td colspan="5">
                                <ul class="pagination pull-right"></ul>
                            </td>
                        </tr>
                        </tfoot>
                    </table>

                </div>
            </div>
        </div>
    </div>
</div>
</body>

</html>
