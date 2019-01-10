<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
    <link href="lib/css/bootstrap.min.css" rel="stylesheet">
</head>
<style>
    body{
        background-image: url('${base}/images/login/bg.png');
    }

    #main{
        width: 1200px;
        height: 100vh;
        background-image: url('${base}/images/login/mainbg.jpg');
        margin: 0 auto;
        padding-top: 125px;
    }

    #title{
        width: 1200px;
        height: 176px;
        background-image: url('${base}/images/login/loginTitle.png');

    }

    #loginPanel{
        width: 587px;
        height: 400px;
        margin: 0 auto;
    }

    #loginBox{
        width: 587px;
        height: 380px;
        background-image: url("${base}/images/login/loginBox.png")  ;
        margin: 0 auto;
        position: relative;
    }

    #copyright{
        text-align: right;
    }

    input{
        border: none !important;
        -moz-box-shadow: none !important;
        -webkit-box-shadow: none !important;
        box-shadow: none !important;
        position: absolute;
        width: 290px;
        font-size: 18px;
        padding-left: 30px;
        /*
            透明
        */
        background-color: transparent;
    }

    input:focus{
        -moz-box-shadow: none !important;
        -webkit-box-shadow: none !important;
        box-shadow: none !important;
        border: none !important;
        outline:medium;
    }
</style>
<body>
    <div id="main">
        <div id="title"></div>

        <div id="loginPanel">
            <div id="loginBox">
                <form action="${base}/login.action" id="form" method="post">
                    <input type="text" id="userNameInput" name="userName" style="left: 200px; top: 112px;" value="${userName?if_exists}">
                    <input type="password" id="passwordInput" name="userPassword" style="left: 200px; top: 164px;">
                    <input type="proving" id="validcodeInput" name="randomCode" style="left: 200px; top:220px; width: 150px;">
                	<img id="imgObj" name="imgObj" title="点击图片刷新" style=" position: absolute;left: 365px; top:215px;" width="120px" height="40px" src="${base}/rCode!getCode.action" onclick="changeImage()" />
                    <input type="submit" id="loginBtn" class="btn btn-success" style="width: 373px; height: 46px; position: absolute; bottom: 50px; left: 120px; font-size: 24px; font-family:'宋体'; font-weight: bolder;" value="登&nbsp;&nbsp;录">
                </form>
            </div>
            <div id="copyright">
                主办：四川省教育厅体卫艺处  技术支持:成都东软学院
            </div>
        </div>
	
    </div>
</body>
 	<script src="${base}/lib/js/jquery-2.1.4.js" type="text/javascript"></script>
    <script src="${base}/lib/js/bootstrap.js" type="text/javascript"></script>
    <script src="lib/layer/2.1/layer.js"></script>
    <script type="text/javascript" src="${base}/js/md5.js"></script>
	 <script>
	 
    
    	function changeImage(){
			document.getElementById("imgObj").src = "${base}/rCode!getCode.action?"+Math.random();
			return false;
		}
    	
    	$(function(){
    		$('#loginBtn').click(function(){
    		
			if($('#userNameInput').val()==''){
			
				layer.msg("请填写账号!");
				
                return false;
            }
            
            if($('#passwordInput').val()==''){
             	layer.msg("请填写密码!");
                return false;
            }

            if($('#validcodeInput').val()==''){
           		layer.msg('请填写验证码!');
                return false;
            }
              
		});

	    	<#if errorInfo?exists && errorInfo!="">
				      layer.msg('${errorInfo?if_exists}');
			</#if>
			
		})
		
		$(function(){
			$('#loginBtn').click(function(){
				encryptPassword($("#passwordInput"),'${_SALT_IN_SESSION_?if_exists}');
				$('form').submit();
			})
			
		})
		
		
    	
    </script>
   
</html>