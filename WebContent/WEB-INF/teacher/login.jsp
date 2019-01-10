<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>教师登录</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <!--<link rel="shortcut icon" href="favicon.ico">-->
	<link href="${ctx}/assets/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${ctx}/assets/css/font-awesome.css?v=4.4.0" rel="stylesheet">

    <link href="${ctx}/assets/css/animate.css" rel="stylesheet">
    <link href="${ctx}/assets/css/style.css?v=4.1.0" rel="stylesheet">
	<link href="${ctx}/assets/css/login.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->
    <script>if(window.top !== window.self){ window.top.location = window.location;}</script>
</head>

<body class="signin">

    <div class="middle-box text-center loginscreen  animated fadeInDown">
        <div>
            <div>
                <h1 class="logo-name">C</h1>
            </div>
            <h3 style="color:gray">在线考试系统</h3>

            <div class="form-group">
                    <input type="email" class="form-control" id="teacherName" placeholder="用户名" required="">
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" id="teacherPassword" placeholder="密码" required="">
                </div>
                <a href="javascript:void(0)" onclick="login()" class="btn btn-primary btn-rounded btn-block btn btn-w-m btn-success">登录</a>
                <br/>
                <p id="message" style="text-align:center;color:red"></p>
        </div>
    </div>
	<footer>
		<div style="text-align:center;color:gray">
			<p>&copy;成都东软学院</p>
		</div>
	</footer>
    <!-- 全局js -->
    <script src="${ctx}/assets/js/jquery.min.js?v=2.1.4"></script>
    <script src="${ctx}/assets/js/bootstrap.min.js?v=3.3.6"></script>
    <script type="text/javascript">
    function login(){
    	console.log("1");
    	var request = new XMLHttpRequest();
    	var teacherName = document.getElementById("teacherName").value;
    	var teacherPassword = document.getElementById("teacherPassword").value;
		request.open("POST","teacherLogin?teacherName="+teacherName+"&teacherPassword="+teacherPassword,true);
		request.send();
		request.onreadystatechange = function(){
			if(request.readyState===4){
				if(request.status===200){
					var data = JSON.parse(request.responseText);
					if("success"===data.state){
						window.location='teacherIndex';
					}
					if("fail"===data.state){
						document.getElementById("message").innerHTML="登录失败，用户名和密码不匹配";
					}
				}
			}
		}
    }
    </script>
</body>
</html>
