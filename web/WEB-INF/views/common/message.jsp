<%@ page import="cn.edu.nsu.lib.config.Constants" %><%--
  User: 墨盒
  Date: 2017/8/12
  Time: 22:38
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<html>--%>
<%--<head>--%>
    <%--<link rel="shortcut icon" href="${path}/static/img/favicon.ico" type="image/x-icon"/>--%>
    <%--<title>提示</title>--%>
<%--</head>--%>
<%--<body>--%>
<%--<%request.setAttribute("key", Constants.KEY_REQUEST_MESSAGE);%>--%>
<%--<h2 style="text-align: center">${requestScope.get(requestScope.get('key'))}</h2>--%>
<%--</body>--%>
<%--</html>--%>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title> - 500错误</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link rel="shortcut icon" href="favicon.ico">
    <link href="${base_path}/resources/common/boostrap/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${base_path}/resources/common/boostrap/css/font-awesome.css?v=4.4.0" rel="stylesheet">

    <link href="${base_path}/resources/common/nav_css/animate.css" rel="stylesheet">
    <link href="${base_path}/resources/common/nav_css/style.css?v=4.1.0" rel="stylesheet">

</head>

<body class="gray-bg">


<div class="middle-box text-center animated fadeInDown">
    <h1>500</h1>
    <h3 class="font-bold">服务器内部错误</h3>

    <div class="error-desc">
        服务器好像出错了...
        <br/>您可以返回上一页看看
        <br/><a href="javascript:history.go(-1)" class="btn btn-primary m-t">主页</a>
    </div>
</div>

<!-- 全局js -->
<!-- 全局js -->
<script src="${base_path}/resources/common/jquery.min.js"></script>
<script src="${base_path}/resources/common/boostrap/js/bootstrap.min.js?v=3.3.6"></script>


</body>

</html>
