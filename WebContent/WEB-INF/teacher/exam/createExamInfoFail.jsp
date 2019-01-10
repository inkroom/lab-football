<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>考试创建失败</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link rel="shortcut icon" href="favicon.ico"> <link href="${ctx}/assets/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${ctx}/assets/css/font-awesome.css?v=4.4.0" rel="stylesheet">

    <link href="${ctx}/assets/css/animate.css" rel="stylesheet">
    <link href="${ctx}/assets/css/style.css?v=4.1.0" rel="stylesheet">

</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content">
        <div class="row">
            <div class="col-sm-12">
                <div class="middle-box text-center animated fadeInRightBig">
                    <h3 class="font-bold">创建失败</h3>

                    <div class="error-desc">
                       很遗憾，本次考试创建失败。
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- 全局js -->
    <script src="${ctx}/assets/js/jquery.min.js?v=2.1.4"></script>
    <script src="${ctx}/assets/js/bootstrap.min.js?v=3.3.6"></script>

    <!-- 自定义js -->
    <script src="${ctx}/assets/js/content.js?v=1.0.0"></script>
</body>
</html>