<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head lang="en">
<meta charset="UTF-8">
<title>找回密码</title>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="${base_path }/resources/common/lib/html5shiv.js"></script>
<script type="text/javascript" src="${base_path }/resources/common/lib/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css"
	href="${base_path }/resources/common/static/h-ui/css/H-ui.reset.css" />
<link rel="stylesheet" type="text/css"
	href="${base_path }/resources/common/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css"
	href="${base_path }/resources/common/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css"
	href="${base_path }/resources/common/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css"
	href="${base_path }/resources/common/static/h-ui.admin/skin/green/skin.css" />
<link rel="stylesheet" href="${base_path }/resources/css/common/findPassword.css">
<!--[if lt IE 9]>
<script src="http://ie7-js.googlecode.com/svn/version/2.1(beta4)/IE9.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css"
	href="${base_path }/resources/common/static/h-ui.admin/css/style.css" />
<!--[if IE 6]>
<script type="text/javascript" src="${base_path }/resources/common/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->

</head>
<body>
	<div class="pro-head">
		<div class="pro-title">
			<div>
				<span>足球管理系统</span> <span>找回密码</span>
			</div>
		</div>

		<div class="pro-box">
			<ul class="process">
				<li class="active">
					<div>
						<i>1</i>
						<p>填写帐号</p>
					</div> <i></i>
				</li>
				<li>
					<div>
						<i>2</i>
						<p>身份验证</p>
					</div> <i></i>
				</li>
				<li>
					<div>
						<i>3</i>
						<p>设置新密码</p>
					</div> <i></i>
				</li>
				<li class="last">
					<div>
						<i>4</i>
						<p>完成</p>
					</div>
				</li>
			</ul>
		</div>
	</div>
	<div class="pro1-main">
		<div class="pro1-content">
			<div class="pro-input">
				<label>帐号： <select id="forgetPassword">
						<option value="1">球员</option>
						<option value="4">球队</option>
						<option value="3">机构</option>
						<option value="2">教练员</option>
						<option value="5">现场管理员</option>
				</select>
				</label>
				<div>
					<div class="inputbox">
						<input type="text" placeholder="登录账号" id="username"
							name="username">
					</div>

				</div>
			</div>
			<div class="pro-input">
				<label>验证码：</label>
				<div>
					<div class="inputbox">
						<input type="text" placeholder="验证码" id="code" name="code">
					</div>
					<div class="vercode" id="">
						<img id="codeimg" name="codeimg"
							src="${base_path}/verification/get_code.action" onclick="getCode()" />
					</div>
					<div class="tips-error code-error" style="display: none;">
						<div>
							<span>验证码错误</span>
						</div>
					</div>
				</div>
			</div>
			<div class="pro-input">
				<a class="pro-btn f-l" id="button" style="margin-top: 10px"><span>确定</span></a>
				<a class="pro-btn f-r" onclick="backlogin()"
					style="margin-top: 10px"><span>返回</span></a>
			</div>
		</div>
	</div>
</body>

<!--_footer 作为公共模版分离出去-->
<script type="text/javascript"
	src="${base_path }/resources/common/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript"
	src="${base_path }/resources/common/lib/layer/2.4/layer.js"></script>
<script type="text/javascript"
	src="${base_path }/resources/common/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript"
	src="${base_path }/resources/common/static/h-ui.admin/js/H-ui.admin.js"></script>
<script type="text/javascript"
	src="${base_path }/resources/common/lib/jquery.contextmenu/jquery.contextmenu.r2.js"></script>
<!--/_footer 作为公共模版分离出去-->

<script type="text/javascript"
	src="${base_path }/resources/common/lib/md5.js"></script>
<script type="text/javascript"
	src="${base_path}/resources/common/lib/jQuery-Validation-Engine/js/jquery.validationEngine.min.js"></script>
<script type="text/javascript"
	src="${base_path}/resources/common/lib/jQuery-Validation-Engine/js/jquery.validationEngine-zh_CN.js"></script>
<script type="text/javascript"
	src="${base_path}/resources/common/lib/jQuery-Validation-Engine/js/jquery.validationEngine.js"></script>
<script type="text/javascript">
	$(function() {
		layer.ready(function() {
			if ('${error}' != '' || '${error}' == null) {
				layer.msg('${error}');
			}
		});
	});

	$(function() {
		// $("#jumpMenu").val(要选中的option的value值即可);
		$("#forgetPassword").val('${TYPE}');
	});

	//获取验证码
	function getCode() {
		var url = "${base_path}/verification/get_code.action?num="
				+ Math.random();
		$("#codeimg").prop("src", url);
	}

	function backlogin() {
		if ('${TYPE}' == 1) {
			window.parent.location.href = '${base_path}/player/login_view.html';
		}
		if ('${TYPE}' == 2) {
			window.parent.location.href = '${base_path}/coach/log_view.html';
		}
		if ('${TYPE}' == 3) {
			window.parent.location.href = '${base_path}/org_login/org_login_view.html';
		}
		if ('${TYPE}' == 4) {
			window.parent.location.href = '${base_path}/team/login_view.html';
		}
		if ('${TYPE}' == 5) {
			window.parent.location.href = '${base_path}/site/login_view.html';
		}
	}

	//填写账号验证码
	$('#button').click(function() {
						var code = $("#code").val();
						var username = $("#username").val();
						var type = $("#forgetPassword").val();
						if (username == "" || code == "" || username == null
								|| code == null) {
							layer.alert("账号验证码不能为空！");
							getCode();
							return;
						}
						$.ajax({
									type : "POST",
									url : "${base_path}/forgetPassword/firstPassword_check.action",
									data : {
										code : $('#code').val(),
										username : $('#username').val(),
										type : $("#forgetPassword").val()
									},
									dataType : 'json',
									success : function(result) {
										if ((result.error) == "账号错误!") {
											layer.alert(result.error,function() {
																$("#code")[0].value = "";
																getCode();
																layer.closeAll('dialog');
															});
										} else if ((result.error) == "验证码错误!") {
											layer.alert(result.error,function() {
																$("#code")[0].value = "";
																getCode();
																layer.closeAll('dialog');
															});
										} else {
											window.parent.location.href = '${base_path}/forgetPassword/secondPassword.html';
										}
									},
									error : function(error) {
										console.log(error);
									}
								});
					});
</script>

</html>