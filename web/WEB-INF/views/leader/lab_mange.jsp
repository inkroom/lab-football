<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>实验室管理</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link rel="shortcut icon" href="${base_path}/resources/common/favicon.ico">
    <link rel="stylesheet" href="${base_path}/resources/common/boostrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${base_path}/resources/common/boostrap/css/font-awesome.min.css">
    <link rel="stylesheet" href="${base_path}/resources/common/nav_css/animate.css">
    <link rel="stylesheet" href="${base_path}/resources/common/nav_css/style.css">

</head>

<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-lg-10 col-lg-offset-1">
            <div class="ibox">
                <div class="ibox-title">
                </div>
                <div class="ibox-content">
                    <h2 class="text-center"> ${labInfo.name}</h2>
                    <div class="row">
                        <div class="col-lg-5 col-md-5 col-sm-5 col-xs-5 text-right m-t-lg">指导老师：</div>
                        <div class="col-lg-7 col-md-7 col-sm-7  col-xs-7   m-t-lg">
                            <c:forEach var="el" items="${labInfo.lTeacherBeans}">
                                ${el.name}   &nbsp; &nbsp; &nbsp; &nbsp;
                                <button class="btn btn-primary btn-xs m-l-lg" id="addButton" type="button"
                                        onclick="removeTeacher(${el.id},${labInfo.id})">移除
                                </button>
                                <br>
                            </c:forEach>
                            &nbsp;
                        </div>
                        <div class="col-lg-5  col-md-5 col-sm-5  col-xs-5  text-right m-t-lg">实验室简介：</div>
                        <div class="col-lg-7 col-md-7 col-sm-7  col-xs-7   m-t-lg">
                            &nbsp;
                            ${labInfo.labDescribe}
                        </div>
                        <div class="col-lg-5  col-md-5 col-sm-5  col-xs-5  text-right m-t-lg">实验室地址：</div>
                        <div class="col-lg-7 col-md-7 col-sm-7  col-xs-7   m-t-lg">
                            &nbsp;
                            ${labInfo.address}
                        </div>
                        <div class="col-lg-5  col-md-5 col-sm-5  col-xs-5  text-right m-t-lg">实验室qq群：</div>
                        <div class="col-lg-7 col-md-7 col-sm-7  col-xs-7   m-t-lg">
                            ${empty (labInfo.qqGrop)?'&nbsp;':labInfo.qqGrop}
                        </div>
                        <div class="col-lg-5 col-md-5 col-sm-5 col-xs-5 text-right m-t-lg">添加指导老师：</div>
                        <div class="col-lg-7 col-md-7 col-sm-7  col-xs-7   m-t-lg">
                            <form id="addTeacherInfo">
                                <input type="hidden" id="lId" name="lId" value="${labInfo.id}"/>
                                <select id="tId" name="tId">
                                    <c:forEach var="el" items="${teacherInfoList}">
                                        <option value="${el.id}">${el.name}</option>
                                    </c:forEach>
                                </select>
                                <button class="btn btn-primary btn-xs m-l-lg" id="" type="button"
                                        onclick=" addTeacher()">确认添加
                                </button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- 全局js -->
    <script src="${base_path}/resources/common/jquery/jquery.js"></script>
    <script src="${base_path}/resources/common/plugins/layer/layer.min.js"></script>
    <script src="${base_path}/resources/js/index.js"></script>
    <script>
        function addTeacher() {
            ajax(
                {
                    contentType: "application/x-www-form-urlencoded;charset=utf-8",
                    async: false,
                    type: 'get',
                    url: '${base_path}/leader/addLabTeacher',
                    data: $('#addTeacherInfo').serialize(),
                    success: function (data) {
                        if (data.status === 200) {
                            layer.msg("创建成功正在刷新页面")
                            setTimeout(function () {  //使用  setTimeout（）方法设定定时2000毫秒
                                window.location.reload();//页面刷新
                            }, 800);
                        }
                        else if (data.status == 1) {
                            layer.msg("创建失败");
                        }

                    },
                    error: function () {
                        layer.msg("网络错误");
                    }
                })
        }

        function removeTeacher(tId, labId) {
            ajax(
                {
                    contentType: "application/x-www-form-urlencoded;charset=utf-8",
                    async: false,
                    type: 'post',
                    url: '${base_path}/leader/removeTeacher',
                    data: {
                        t_id: tId,
                        lab_id: labId
                    },
                    success: function (data) {
                        if (data.status === 200) {
                            layer.msg("移除成功，正在刷新页面")
                            setTimeout(function () {  //使用  setTimeout（）方法设定定时2000毫秒
                                window.location.reload();//页面刷新
                            }, 800);
                        }
                        else if (data.status == 1) {
                            layer.msg("移除失败");
                        }

                    },
                    error: function () {
                        layer.msg("网络错误");
                    }
                })
        }
    </script>
</div>

</body>


</html>
