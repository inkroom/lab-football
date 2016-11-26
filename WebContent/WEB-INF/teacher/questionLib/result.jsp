<%@page import="cn.nsu.ccl.teacher.entity.QuestionLibListEntity"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title> 结果页面</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link rel="shortcut icon" href="favicon.ico"> <link href="css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="css/font-awesome.css?v=4.4.0" rel="stylesheet">

    <link href="css/animate.css" rel="stylesheet">
    <link href="css/style.css?v=4.1.0" rel="stylesheet">

</head>

<body class="gray-bg">


    <div class="middle-box text-center animated fadeInDown">
        <h2 id="message">创建失败</h2>
        

       
    </div>

    <!-- 全局js -->
    <script src="js/jquery.min.js?v=2.1.4"></script>
    <script src="js/bootstrap.min.js?v=3.3.6"></script>
<script type="text/javascript">
document.ready=function(){
	var request = new XMLHttpRequest();
	request.onreadystatechange = function(){
		var data = JSON.parse(request.responseText);
		if("success"===data.state){
			document.getElementById("message").innerHTML="创建成功";
		}
		if("fail"===data.state){
			document.getElementById("message").innerHTML="创建失败";
		}
		if("error"===data.state){
			document.getElementById("message").innerHTML="题库重名";
		}
	}
}

</script>
    
    

</body>

</html>
