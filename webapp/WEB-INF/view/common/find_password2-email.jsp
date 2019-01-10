<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
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
            <li class="passed">
                <div>
                    <i>1</i>
                    <p>填写帐号</p>
                </div>
                <i></i>
            </li>
            <li class="active">
                <div>
                    <i>2</i>
                    <p>身份验证</p>
                </div>
                <i></i>
            </li>
            <li>
                <div>
                    <i>3</i>
                    <p>设置新密码</p>
                </div>
                <i></i>
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
	<div class="pro2-main">
		<div class="pro2-content">
			<div class="pro1-content  email-check">
				<div class="main-email">
					<div class="pro-input">
						<div class="inputbox" style="width: 235px;">
							<p class="message-title">请用邮箱 ${sessionScope.A_EMAIL} 获取邮箱验证码</p>
						</div>
					</div>
					<div class="pro-input">
						<div>
							<div class="inputbox">
								<input type="text" placeholder="输入验证码" id="emailCode">
							</div>
							<div class="code-input">
								<input class="code-btn btn radius  sendcode" type="button"
									id="button" value="获取验证码">
							</div>
						</div>
					</div>
					<div class="pro-input">
						<a class="pro-btn" style="margin-top: 10px" id="button1"
							onclick="tijiao()"><span>确定</span></a>
					</div>
					<p class="message_tips">
						邮箱用不了？<a href="${base_path}/forgetPassword/secondPassword.html">更换其他验证方式</a>
					</p>
				</div>
			</div>
		</div>
	</div>
	</div>
</body>

<input type="text" id="email" hidden="" value="${sessionScope.email }">
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

<script>
	//填写账号验证码
	$('#button').click(function() {
		var em = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
		var email = $("#email").val();
		if (email == null || email == "") {
			layer.alert("邮箱不能为空！");
			return;
		}
		if (!em.exec(email)) {
			layer.alert("邮箱格式错误");
			return;
		}
		$.ajax({
			type : "post",
			url : "${base_path}/email/getCode.action",
			data : {
				email : $("#email").val()
			},
			dataType : 'json',
			success : function(json) {
				 if (json.success){
						time(".sendcode");
						var wait = 60;
						layer.alert(json.msg);
	                }else {
	                	layer.alert(json.msg);
	                }
				
			},
			error : function(error) {
				console.log(error);
			}
		});
	});

	//验证邮箱以及邮箱验证码是否正确
	function tijiao() {
		var emailCode = $("#emailCode").val();
		if (emailCode == null || emailCode == "") {
			layer.alert("验证码不能为空！");
			return;
		}
		$.ajax({
					async : false,
					type : "post",
					url : "${base_path}/forgetPassword/forgetEmailCode.action",
					data : {
						oldEmailCode : $('#emailCode').val()
					},
					dataType : 'json',
					success : function(result) {
						if (result.success==200){
							layer.alert("验证成功！");
							window.location.href = '${base_path}/forgetPassword/thirdPassword.action';
		                }else {
		                	 layer.alert("验证码错误！");
		                }
					},
					error : function(error) {
						console.log(error);
					}
				});
	}

	//计时器
	var wait = 60;
	function time(e) {
		if (wait == 0) {
			$(e).removeAttr("disabled");
			$(e).val("获取验证码");
			wait = 60;
		} else {
			$(e).attr("disabled", "true");
			$(e).val("重新发送(" + wait + ")");
			wait--;
			setTimeout(function() {
				time(e);
			}, 1000)
		}
	}
</script>
</html>