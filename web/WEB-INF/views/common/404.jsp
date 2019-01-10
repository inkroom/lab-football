<%--
  Created by IntelliJ IDEA.
  User: inkbox
  Date: 18-3-1
  Time: 下午8:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title> - 404 页面</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <link href="${pageContext.request.contextPath}/resources/common/boostrap/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/common/boostrap/css/font-awesome.css?v=4.4.0" rel="stylesheet">

    <link href="${pageContext.request.contextPath}/resources/common/nav_css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/common/nav_css/style.css?v=4.1.0" rel="stylesheet">

</head>

<body class="gray-bg">


<div class="middle-box text-center animated fadeInDown">
    <h1>404</h1>
    <h3 class="font-bold">页面未找到！</h3>

    <div class="error-desc">
        抱歉，页面好像去火星了~
            <button type="button" onclick="history.go(-1)" class="btn btn-primary">点击返回</button>
    </div>
</div>

<!-- 全局js -->
<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js?v=2.1.4"></script>
<%--<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js?v=3.3.6"></script>--%>


</body>

</html>
