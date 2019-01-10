<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>登录</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <!--<link rel="shortcut icon" href="favicon.ico">-->
	<link href="${ctx }/assets/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${ctx }/assets/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="${ctx }/assets/css/animate.css" rel="stylesheet">
    <link href="${ctx }/assets/css/style.css?v=4.1.0" rel="stylesheet">
	<link href="${ctx }/assets/css/login.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->
    <script>if(window.top != window.self){ window.top.location = window.location;}</script>
</head>
<body>
    <div class="middle-box text-center loginscreen  animated fadeInDown">
        <div>
            <div>
                <h1 class="logo-name">C</h1>
            </div>
            <h3>在线考试系统</h3>
            <!-- <form class="m-t" role="form" action=""> -->
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="学号" >
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="姓名" >
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="口令" >
                </div>
                <label style="color:red;display:none;"></label>
                <button type="button" class="btn btn-primary block full-width m-b">登 录</button>
            <!-- </form> -->
        </div>
    </div>
	<footer>
		<div style="text-align:center;">
			<p>&copy;成都东软学院</p>
		</div>
	</footer>
    <!-- 全局js -->
    <script type="text/javascript" src="${ctx }/assets/js/jquery.min.js?v=2.1.4"></script>
    <script type="text/javascript" src="${ctx }/assets/js/bootstrap.min.js?v=3.3.6"></script>
	<script type="text/javascript" src="${ctx }/assets/js/stuLogin.js"></script>


</body>
</html>
