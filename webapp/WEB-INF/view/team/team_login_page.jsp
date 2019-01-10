<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE>
<html><head>
<title>球队登录</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<script type="text/javascript" src="${base_path}/resources/js/team/jquery-1.9.0.min.js"></script>
<script type="text/javascript" src="${base_path}/resources/js/team/login.js"></script>
<link href="${base_path}/resources/css/team/login2.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${base_path}/resources/common/lib/jQuery-Validation-Engine/css/validationEngine.jquery.css" />


</head>
<body>
<h1>四川省足球信息化系统球队管理员登录<sup></sup></h1>
<div class="login" style="margin-top:50px;">
    <div class="header">
        <div class="switch" id="switch"><a class="switch_btn_focus" id="switch_qlogin" href="javascript:void(0);" tabindex="7">快速登录</a>
			<a class="switch_btn" id="switch_login" href="javascript:void(0);" tabindex="8">注册</a><div class="switch_bottom" id="switch_bottom" style="position: absolute; width: 64px; left: 0px;"></div>
        </div>
    </div>
    <div class="web_qr_login" id="web_qr_login" style="display: block; height: 330px;">
    	<!--登录-->
        <div class="web_login" id="web_login">
        	<div class="login-box">
				<div class="login_form">
					<form name="loginform" accept-charset="utf-8" id="login_form" class="loginForm" method="post" action="${base_path}/team/login_check.action" onsubmit="return loginFun('${_SALT_IN_SESSION_}');">
						<input type="hidden" name="did" value="0"/>
	                	<input type="hidden" name="to" value="log"/>
	                	<div class="uinArea" id="uinArea">
	                		<label class="input-tips" for="u">帐号：</label>
	               			<div class="inputOuter" id="uArea">
	                    		<input type="text" placeholder="请输入手机号" id="u" name="username" value="${username}"  class="inputstyle"/>
	                		</div>
	                	</div>
	                	<div class="pwdArea" id="pwdArea">
	                		<label class="input-tips" for="p">密码：</label>
	                		<div class="inputOuter" id="pArea">
	                    		<input type="password" id="p" name="password" class="inputstyle"/>
	                		</div>
	                	</div>
	                    <div class="pwdArea" id="wdArea">
	                        <label class="input-tips" for="password">验证码：</label>
	                        <div class="inputOuter" id="dArea">
	                            <input type="text" id="code" name="code" class="inputstyle" style="width: 60px;float: left"/>
	                            <img id="codeimg" name="codeimg" src="${base_path}/verification/get_code.action" style="margin-top:5px;float: right" onclick="getCode()"/>
	                    	</div>
	                    </div>
	                    <div class="pwdArea">
	                        <label class="input-tips" for="password"></label>
	                        <div class="inputOuter">
	                            <a href="${base_path }/forgetPassword/firstPassword/4.html"  style="cursor: pointer;">忘记密码？点击找回</a>
	                        </div>
	                    </div>
	                	<div style="pwdArea">
	                		<input id="loginBtn" type="submit" value="登 录" style="width:125px;margin-left:10px;" class="button_blue"/>
	                		<input type="button" onclick="goBack()" value="返 回" style="width: 125px;margin-left: 10px;"  class="button_blue"/>
	                	</div>
                	</form>
           		</div>
            </div>
		</div>
	</div>
<!--登录end-->

<!--注册-->
    <div class="qlogin" id="qlogin" style="display: none; ">
    	<div class="web_login">
    		<form name="form2" id="regUser" accept-charset="utf-8" action="">
		      	<input type="hidden" name="to" value="reg"/>
			    <input type="hidden" name="did" value="0"/>
	        	<ul class="reg_form" id="reg-ul">
	            	<li>
	                	<label for="user"  class="input-tips2">身份证号：</label>
	                    <div class="inputOuter2">
	                        <input type="text" id="user" name="user" value="${user}" maxlength="18" class="inputstyle2 validate[required,custom[chinaId]]" />
	                    </div>
	                </li>
	                <li>
	                	<label for="passwd" class="input-tips2">密码：</label>
	                    <div class="inputOuter2">
	                        <input type="password" id="passwd"  name="passwd" onkeyup="change()"  class="inputstyle2 validate[required,minSize[6],maxSize[16]]"/>
	                    </div>
	                </li>
	                <li>
	                	<label for="passwd2" class="input-tips2">确认密码：</label>
	                    <div class="inputOuter2">
	                        <input type="password" id="passwd2" name="passwd2"  onkeyup="change()" class="inputstyle2 validate[required,minSize[6],maxSize[16],equals[passwd]]" />
	                    </div>
	                </li>
	                <li>
	                 	<label for="phone" class="input-tips2">手机号：</label>
	                    <div class="inputOuter2">
	                        <input type="text" id="phone" name="phone" value="${phone}" maxlength="11" class="inputstyle2 validate[required,custom[phone]]"/>
	                    </div>
	                </li>
	            	<li>
	                	<label for="phoneCheck" class="input-tips2">验证码：</label>
	                	<div class="inputOuter2">
	                    	<input type="text" id="phoneCheck" name="phoneCheck" maxlength="10" class="inputstyle2 validate[required]" style="width: 80px;float: left;margin-right: 10px"/>
	                    	<input class="button" type="button" id="sendMessage" value="获取验证码"/>
	                	</div>
	            	</li>
	            	<li>
	                    <div class="inputArea">
	                        <input type="submit" id="reg" style="margin-top:10px;margin-left:85px;width: 150px" class="button_blue" value="注册"/>
	                    </div>
	                </li>
	            	<div class="cl"></div>
	            </ul>
    		</form>
    	</div>
    </div>
    <!--注册end-->
</div>
<input type="text" id="base_path" hidden="" value="${base_path}">
</body>
<script type="text/javascript" src="${base_path }/resources/common/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="${base_path }/resources/common/static/h-ui/js/H-ui.js"></script>
<script type="text/javascript" src="${base_path }/resources/common/lib/md5.js"></script>
<script type="text/javascript" src="${base_path }/resources/common/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="${base_path}/resources/common/lib/jQuery-Validation-Engine/js/jquery.validationEngine.min.js"></script>
<script type="text/javascript" src="${base_path}/resources/common/lib/jQuery-Validation-Engine/js/jquery.validationEngine-zh_CN.js"></script>
<script type="text/javascript" src="${base_path}/resources/common/lib/jQuery-Validation-Engine/js/jquery.validationEngine.js"></script>
<script type="text/javascript" src="${base_path}/resources/js/team//page/team_login_register.js"></script>
<script type="text/javascript">
	function  change() {
		var pass=$("#passwd").val();
		var pass2=$("#passwd2").val();
		var passwd2=$("#passwd2");
		if(pass!=pass2){
		    passwd2.css("border","1px solid red");
		}
		if(pass==pass2){
            passwd2.css("border","1px solid #D7D7D7");
		}


    }
	
	$(function(){
		//初始化插件
		$("#formID").validationEngine(); 
		layer.ready(function(){
			if('${Msg}' != ''){
				layer.msg('${Msg}',{icon: 0});
				<% session.removeAttribute("Msg"); %>
			}
    	});
	});
	function goBack() {
	    window.location.href = "${base_path}/index.html";
	}
</script>

</html>