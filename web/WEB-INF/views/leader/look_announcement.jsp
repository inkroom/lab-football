<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
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
<script src="${base_path}/resources/js/student/footable.all.min.js"></script>
<!-- 自定义js -->
<script src="${base_path}/resources/js/student/content.js?v=1.0.0"></script>

<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">

    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>公告文档</h5>

                    <div class="ibox-tools">

                        <ul class="dropdown-menu dropdown-user">
                            <li><a href="#">选项 01</a>
                            </li>
                            <li><a href="#">选项 02</a>
                            </li>
                        </ul>

                    </div>
                </div>
                <div class="ibox-content" ms-controller="fileList" >

                    <table class="footable table table-stripped toggle-arrow-tiny" data-page-size="8">
                        <thead>
                        <tr>
                            <th>发布时间</th>
                            <th >公告</th>
                            <th >详情</th>
                            <th >文档名称</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody >
                        <c:forEach var="el"  items="${noticeList}">
                        <tr>

                            <td>${el.time}</td>
                            <td>${el.title}</td>
                            <td>${el.text}</td>
                            <c:if test="${el.file_name!=null&&el.file_name!=''}">
                                <td>${el.file_name}</td>
                            </c:if>
                            <c:if test="${el.file_name==''}">
                                <td>----------  </td>
                            </c:if>
                            <c:if test="${el.file_path!=null&&el.file_path!=''}">
                            <td><a href="${base_path}/student/downLoad?filePath=${el.file_path}">下载</a> </td>
                            </c:if>
                            <c:if test="${el.file_path==''}">
                                <td>-------</td>
                            </c:if>
                        </tr>
                        </c:forEach>
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