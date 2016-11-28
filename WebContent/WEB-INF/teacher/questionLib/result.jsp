<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>考试创建失败</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link rel="shortcut icon" href="favicon.ico"> <link href="css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="css/font-awesome.css?v=4.4.0" rel="stylesheet">

    <link href="css/animate.css" rel="stylesheet">
    <link href="css/style.css?v=4.1.0" rel="stylesheet">

</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content">
        <div class="row">
            <div class="col-sm-12">
                <div class="middle-box text-center animated fadeInRightBig">
                <%if(request.getAttribute("state").equals("success")){ %>
                    <h3 class="font-bold">创建成功</h3>
                    <div class="error-desc">
						您可以在左侧继续选择您想要执行的操作哦！
                    </div>
                <% }else if(request.getAttribute("state").equals("exist")){%>
	                <h3 class="font-bold">创建失败</h3>
	                    <div class="error-desc">
							题库名已经存在，请重新填写题库名。
	                    </div>
                <%}else{ %>
                	<h3 class="font-bold">创建失败</h3>
	                    <div class="error-desc">
							很遗憾，本次题库创建失败。
	                    </div>
	                    <%} %>
                </div>
            </div>
        </div>
    </div>

    <!-- 全局js -->
    <script src="js/jquery.min.js?v=2.1.4"></script>
    <script src="js/bootstrap.min.js?v=3.3.6"></script>

    <!-- 自定义js -->
    <script src="js/content.js?v=1.0.0"></script>
</body>
</html>