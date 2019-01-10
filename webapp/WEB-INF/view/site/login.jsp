<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<title>现场管理员登录</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript"
	src="${base_path}/resources/js/site/jquery-1.9.0.min.js"></script>
<script type="text/javascript"
	src="${base_path}/resources/img/site/login.js"></script>
<script type="text/javascript"
	src="${base_path}/resources/common/lib/md5.js"></script>

<link href="${base_path}/resources/css/site/login2.css" rel="stylesheet"
	type="text/css" />
</head>
<body>
	<h1>
		现场管理员登录<sup></sup>
	</h1>
	<div class="login" style="margin-top: 50px;">
		<div class="header">
			<div class="switch" id="switch"
				style="left: 40%; top: 12px; font-size: 20px">快速登录</div>
		</div>
		<div class="web_qr_login" id="web_qr_login"
			style="display: block; height: 330px;">
			<!--登录-->
			<div class="web_login" id="web_login">
				<div class="login-box">
					<div class="login_form">
						<form name="loginform" accept-charset="utf-8" id="login_form"
							class="loginForm" method="post"
							action="${base_path }/site/login_checking.action">
							<input type="hidden" name="did" value="0" /> <input
								type="hidden" name="to" value="log" />
							<div class="uinArea" id="uinArea">
								<label class="input-tips" for="u">帐号：</label>
								<div class="inputOuter" id="uArea">
									<input type="text" id="user_name" name="user_name"
										class="inputstyle" />
								</div>
							</div>
							<div class="pwdArea" id="pwdArea">
								<label class="input-tips" for="p">密码：</label>
								<div class="inputOuter" id="pArea">
									<input type="password" id="user_password" name="user_password"
										class="inputstyle" />
								</div>
							</div>
							<div class="pwdArea" id="wdArea">
								<label class="input-tips" for="password">验证码：</label>
								<div class="inputOuter" id="dArea">
									<input type="text" id="identifying_code"
										name="identifying_code" class="inputstyle"
										style="width: 60px; float: left" /> <img id="yanz"
										src="${base_path }/verification/get_code.action"
										style="margin-top: 5px; float: right" onclick="get_code()">

								</div>
							</div>
							<div class="pwdArea">
								<label class="input-tips" for="password"></label>

								<div class="inputOuter">
									<a href="${base_path }/forgetPassword/firstPassword/5.html"  style="cursor: pointer;">忘记密码？点击找回</a>
								</div>
							</div>
							<div>
								<input type="submit" value="登 录" style="width: 125px;margin-left: 10px;"
									id="login" class="button_blue" />
								<input type="button" onclick="goBack()" value="返 回"
									style="width: 125px; margin-left: 10px;" class="button_blue" />
							</div>
						</form>
					</div>
				</div>
			</div>
			<!--登录end-->
		</div>
	</div>

</body>

<script type="text/javascript"
	src="${base_path}/resources/js/site/jquery-1.9.0.min.js"></script>
<script type="text/javascript"
	src="${base_path}/resources/js/site/layer/2.4/layer.js"></script>

<script type="text/javascript">
	$(function() {
		layer.ready(function() {
			if ('${error}' != '' || '${error}' == null) {
				layer.msg('${error}');
			}
		});
	});

	function goBack(){
		window.location.href="${base_path}/index.html";
	}
	$('#login').click(function() {

		var user_name = $("#user_name").val();
		var user_password = $("#user_password").val();
		var identifying_code = $("#identifying_code").val();

		if (user_name == null || user_name == "") {
			layer.msg('账号密码不能为空！');
			return false;
		}
		if (user_password == null || user_password == "") {
			layer.msg('账号密码不能为空！');
			return false;
		}
		if (identifying_code == null || identifying_code == "") {
			layer.msg('验证码不能为空！');
			return false;
		}
		encryptPassword($("#user_password"), '${_SALT_IN_SESSION_}');
	});

	function get_code() {
		document.getElementById("yanz").src = "${base_path}/verification/get_code.action?num="
				+ Math.random();
		return false;
	}
</script>
</html>