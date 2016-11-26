<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>密码修改</title>
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
                    <h3 class="font-bold">修改密码-<%=session.getAttribute("teacherEmail") %></h3>

                    <div class="error-desc">
                    <div class="modal-body">
                	<input id="oldPasswd" type="password" class="form-control" placeholder="请输入原密码" />
                	<br/>
                	<input id="newPasswd1" type="password" class="form-control" placeholder="请输入新密码" /><br/>
                	<input id="newPasswd2" type="password" class="form-control" placeholder="请确认新密码"/><br/>
                	<p id="formContent"></p>
                </div>
                <div class="modal-footer">
                    <button type="button" onclick="changePasswd('<%=session.getAttribute("teacherEmail")%>')" class="btn btn-default" data-dismiss="modal">确定</button>
                </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- 全局js -->
    <script src="js/jquery.min.js?v=2.1.4"></script>
    <script src="js/bootstrap.min.js?v=3.3.6"></script>

    <!-- 自定义js -->
    <script src="js/content.js?v=1.0.0"></script>
    <script>
  //提交修改密码的请求
    function changePasswd(teacherEmail){
    	console.log(teacherEmail);
		//获取原始密码
		var oldPasswd = $("#oldPasswd").val();
		//获取新密码1
		var newPasswd1 = $("#newPasswd1").val();
		//获取新密码2
		var newPasswd2 = $("#newPasswd2").val();
		//检查输入是否为空
		if(oldPasswd===null||oldPasswd===""){
			document.getElementById("formContent").innerHTML="原密码不能为空";
			return;
		}
		if(newPasswd1===null||newPasswd1===""){
			document.getElementById("formContent").innerHTML="新密码不能为空";
			return;
		}
		if(newPasswd2===null||newPasswd2===""){
			document.getElementById("formContent").innerHTML="新密码验证不能为空";
			return;
		}
		if(newPasswd1===newPasswd2){
			//两次密码匹配则调配ajax提交修改请求
			//使用XMLHttpRequest方法，实际上在上面的jquery中也是用的这个方法，只不过已经给我们封装好了
            var request = new XMLHttpRequest();
            //使用open方法填写参数，最后一个true表示使用使用异步提交，可以省略，默认值就是true
            request.open("POST","teacherChangePasswordDo?oldPasswd="+oldPasswd+"&newPasswd="+newPasswd1,true);
            //发送ajax请求
            request.send();
            //监听请求的状态，主要有0 1 2 3 4 这几种，但是一边只会监听2 3 4 ，其中4表示成功
            request.onreadystatechange = function(){
                //判断只有当请求成功时才进行下一步
                if(request.readyState===4){
                //判断只有当网页的返回码为200 OK时才进行下一步
                    if(request.status===200){
                        //使用JSON.parse方法格式化返回的json数据
                        var data = JSON.parse(request.responseText);
                        //创建成功
                        if("success"===data.state){
                        	document.getElementById("formContent").innerHTML="密码修改成功！<a href='teacher'>点我重新登录</a>";
                        }else{
                        	//创建失败
                        	document.getElementById("formContent").innerHTML="密码修改失败，原密码可能输入不正确";
                        }
                    }
			}else{
			//提示密码两次密码有误
			document.getElementById("formContent").innerHTML="两次输入的新密码不匹配，请检查";
			}
   		 };
		}
  }
    </script>
</body>
</html>