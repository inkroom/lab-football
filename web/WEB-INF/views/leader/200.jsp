<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title> - 404 页面</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <link rel="stylesheet" href="${base_path}/resources/common/boostrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${base_path}/resources/common/boostrap/css/font-awesome.min.css">
    <link rel="stylesheet" href="${base_path}/resources/common/nav_css/animate.css">
    <link rel="stylesheet" href="${base_path}/resources/common/nav_css/style.css">

</head>

<body class="">


<div class="middle-box text-left animated fadeInDown">
    <h3>操作结果：</h3>
    <c:if test="${result.status==200}">
        <h2 class="font-bold">操作成功</h2>
    </c:if>
    <c:if test="${result.status==1}">
        <h2 class="font-bold">操作失败，请重试或联系管理员</h2>
    </c:if>
    <div class="error-desc">
     <a  href="javascript:history.back();"><span class="btn-group"> 返回上一级</span></a>
    </div>
</div>



</body>

</html>
