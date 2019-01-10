<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta http-equiv="cache-control" content="no-cache"> 
    <title>特色学校后台管理</title>
    <link rel="shortcut icon" href="${basePath }/resources_new/favi/favicon.ico" type="image/x-icon" />
    <link href="${basePath }/resources/lib/css/bootstrap.min.css" rel="stylesheet">
</head>
<style> 
	
    body{
        background-image: url('${basePath }/resources/images/login/bg.png');
    }

    #main{
        width: 1200px;
        height: 100vh;
        background-image: url('${basePath }/resources/images/login/mainbg.jpg');
        margin: 0 auto;
        padding-top: 125px;
    }

    #title{
        width: 1200px;
        height: 176px;

    }

    #loginPanel{
        width: 587px;
        height: 400px;
        margin: 0 auto;
    }

    #loginBox{
        width: 587px;
        height: 380px;
        background-image: url("${basePath }/resources/images/login/loginBox.png")  ;
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
        background-color: transparent;
        max-width:200px;
    }

    input:focus{
        -moz-box-shadow: none !important;
        -webkit-box-shadow: none !important;
        box-shadow: none !important;
        border: none !important;
        outline:medium;
    }
    
    .proving{
    	width:120; 
    	height:40; 
    	position: absolute;
    	background-color:red;
    	left: 360px; 
    	top:215px;
    }
    
    .submitButton{
    	width: 373px; 
    	height: 46px; 
    	position: absolute; 
    	bottom: 50px;
    	left: 120px; 
    	font-size: 24px; 
    	font-family:'宋体'; 
    	font-weight: bolder;
    }
</style>
<body>
    <div id="main">
        <div id="title"></div>

        <div id="loginPanel">
            <div id="loginBox">
                <form action="${basePath }/school_admin/login" method="POST">
                    <input type="text" name="userName" id="userName" style="left: 230px; top: 112px;" value="${username }">
                    <input id="password" type="password" name="userPassword" style="left: 230px; top: 164px;">
                    <input type="text" name="randomCode" id="randomCode" style="left: 230px; top:220px; width: 150px;">
                    <img id="proving" src="${basePath }/random_code.action" class="proving" title="点击更换验证码" onclick="getProvingCode()">
                    <button id="login" type="submit" class="btn btn-success submitButton">登&nbsp;&nbsp;录</button>
                </form>
            </div>
            <div id="copyright">
                	主办：四川省教育厅体卫艺处  技术支持:成都东软学院
            </div>
        </div>
	
    </div>
</body>
    <script src="${basePath }/resources/lib/js/jquery-2.1.4.js" type="text/javascript"></script>
    <script src="${basePath }/resources/lib/js/bootstrap.js" type="text/javascript"></script>
    <script src="${basePath }/resources/lib/js/layer/2.1/layer.js"></script>
    <script src="${basePath }/resources/lib/js/md5.js"></script>
    <script type="text/javascript">
	   	$(function(){
	    	if( '${errorInfo}'!=null && '${errorInfo}'!=''){	
	    		layer.alert('${errorInfo}', {
					skin: 'layer-bule-style'
				    ,closeBtn: 0
			  	});
	    		
	    	}
	    });
	    function getProvingCode(){
	    		var url = "${basePath }/random_code.action?num="+Math.random();
	    		$("#proving").prop('src',url);
	    }
	    
	    $("#login").click(function(){
    		var userName = $("#userName").val();
    		if(userName == null || userName == ""){
    			layer.alert('请输入账号', {
					skin: 'layer-bule-style'
				    ,closeBtn: 0
			  	});
    			return false;
    		}else{
    			var password = $("#password").val();
        		if(password == null || password == ""){
        			layer.alert('请输入账号', {
    					skin: 'layer-bule-style'
    				    ,closeBtn: 0
    			  	});
        			return false;
        		}else{
        			var randomCode = $("#randomCode").val();
            		if(randomCode == null || randomCode == ""){
            			layer.alert('请输入验证码', {
        					skin: 'layer-bule-style'
        				    ,closeBtn: 0
        			  	});
            			return false;
            		}else{
            			encryptPassword($("#password"),'${_SALT_IN_SESSION_}');
            		}
        		}	
    		}
    	});
    </script>
</html>
