<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title> 实验室教师情况</title>
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
                    <h5>全部教师</h5>
                </div>
                <table class="table table-bordered table-hover text-center">
                    <thead>
                    <tr>
                        <!-- <th class="text-center">全选</th> -->
                        <th class="text-center">姓名</th>
                        <th class="text-center">性别</th>
                        <th class="text-center">实验室</th>
                        <th class="text-center">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="el" items="${teacherInfoList}">
                        <tr>
                            <td hidden id="${el.id}">${el.id}</td>
                            <td>${el.name}</td>
                            <c:if test="${el.gender==0}">
                                <td>男</td>
                            </c:if>
                            <c:if test="${el.gender==1}">
                                <td>女</td>
                            </c:if>
                            <td>
                                <c:forEach var="es" items="${el.ltLabBeans}">
                                    ${es.name}&nbsp;
                                </c:forEach>
                            </td>
                            <td>
                                <button class="btn btn-danger " onclick="delTeacher(${el.id})">删除</button>
                                <a class="btn btn-danger"
                                   href="${base_path}/leader/teacher_cipher/<c:out value="${el.id}"/>">重置密码</a>
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

<script src="${base_path}/resources/common/jquery/jquery.js"></script>
<script src="${base_path}/resources/common/plugins/layer/layer.min.js"></script>
<script src="${base_path}/resources/js/index.js"></script>
<script>
    function delTeacher(id) {
        ajax( {
                contentType: "application/x-www-form-urlencoded;charset=utf-8",
                async: true,
                type: 'post',
                url: '${base_path}/leader/delTeacher/'       + id,
                success: function (data) {
                    if (data.status == 200) {
                        layer.msg("删除成功请刷新页面")
                    }
                    else if (data.status == 1) {
                        layer.msg("删除失败");
                    }

                }
            })
    }
</script>


</body>

</html>
