<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title> 实验室基本情况</title>
    <meta name="keywords" content="">
    <meta name="description" content="">


    <link rel="shortcut icon" href="${base_path}/resources/common/favicon.ico">
    <link rel="stylesheet" href="${base_path}/resources/common/boostrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${base_path}/resources/common/boostrap/css/font-awesome.min.css">
    <link href="${base_path}/resources/css/teacher/iCheck/custom.css">
    <link rel="stylesheet" href="${base_path}/resources/common/nav_css/animate.css">
    <link rel="stylesheet" href="${base_path}/resources/common/nav_css/style.css">

</head>

<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12 col-lg-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>全部实验室</h5>
                </div>
                <table class="table table-bordered table-hover text-center">
                    <thead>
                    <tr>
                        <!-- <th class="text-center">全选</th> -->
                        <th class="text-center">实验室名称</th>
                        <th class="text-center">指导教师</th>

                        <th class="text-center">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="el" items="${labInfoList}">
                        <tr>
                            <td><a class="btn" href="lab_mange/${el.id}?">${el.name}</a></td>

                            <td>
                                <c:forEach var="es" items="${el.lTeacherBeans}">
                                    ${es.name}&nbsp;
                                </c:forEach>
                            </td>

                            <td>
                                <button class="btn btn-danger" onclick="delLab(${el.id},this)">删除</button>
                                <a class="btn btn-danger" href="lab_mange/${el.id}">详情</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

</div>

<!-- 全局js -->
<script src="${base_path}/resources/common/jquery/jquery.min.js"></script>
<script src="${base_path}/resources/common/boostrap/js/bootstrap.min.js"></script>

<script src="${base_path}/resources/js/index.js"></script>
<script src="${base_path}/resources/common/plugins/layer/layer.min.js"></script>

<script src="${base_path}/resources/js/teacher/jquery.peity.min.js"></script>

<!-- 自定义js -->

<script src="${base_path}/resources/js/teacher/content.js"></script>
<script>


    function delLab(ss,obj) {
        ajax({
                contentType: "application/x-www-form-urlencoded;charset=utf-8",
                async: false,
                type: 'post',
                url: '${base_path}/leader/delLib',
                data: {'lab_id': ss},
                success: function (data) {
                    if (data.status == 200) {
                        obj.parentNode.parentNode.removeChild(obj.parentNode);
                        layer.msg("删除成功")
                    }
                    else if (data.status == 1) {
                        layer.msg("删除失败");
                    }

                },
                error: function () {
                    layer.msg("网络错误");
                }
            })
    }
</script>


</body>

</html>
