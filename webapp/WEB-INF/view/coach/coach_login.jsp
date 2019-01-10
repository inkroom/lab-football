<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE>
<html>
<head>
<title>教练员登录</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript"
	src="${base_path }/resources/js/coach/jquery-1.9.0.min.js"></script>
<script type="text/javascript"
	src="${base_path }/resources/img/coach/login.js"></script>
<link href="${base_path }/resources/css/coach/login2.css"
	rel="stylesheet" type="text/css" />
<link rel="stylesheet"
	href="${base_path}/resources/common/lib/jQuery-Validation-Engine/css/validationEngine.jquery.css" />
<style>

</style>
</head>
<body>
	<h1>四川省足球信息化系统教练员登录</h1>
	<div class="login" style="margin-top: 50px;">
		<div class="header">
			<div class="switch" id="switch">
				<a class="switch_btn_focus" id="switch_qlogin"
					href="javascript:void(0);" tabindex="7">快速登录</a> <a
					class="switch_btn" id="switch_login" href="javascript:void(0);"
					tabindex="8">快速注册</a>
				<div class="switch_bottom" id="switch_bottom"
					style="position: absolute; width: 64px; left: 0px;"></div>
			</div>
		</div>
		<div class="web_qr_login" id="web_qr_login"
			style="display: block; height: 330px;">
			<!--登录-->
			<div class="web_login" id="web_login">
				<div class="login-box">
					<div class="login_form">
						<form name="loginform" accept-charset="utf-8" id="login_form"
							class="loginForm" method="post"
							action="${base_path}/coach/login.action" onsubmit="ep();">
							<input type="hidden" name="did" value="0" /> <input type="hidden"
								name="to" value="log" />
							<div class="uinArea" id="uinArea">
								<label class="input-tips" for="u">帐号：</label>
								<div class="inputOuter" id="uArea">
									<input type="text" id="u" name="username" class="inputstyle"
										placeholder="请输入手机号" maxlength="11"/>
								</div>
							</div>
							<div class="pwdArea" id="pwdArea">
								<label class="input-tips" for="p">密码：</label>
								<div class="inputOuter" id="pArea">
									<input type="password" id="p" name="p" class="inputstyle" />
								</div>
							</div>
							<div class="pwdArea" id="wdArea">
								<label class="input-tips" for="password">验证码：</label>
								<div class="inputOuter" id="dArea">
									<input id="icode" type="text" name="idecode" class="inputstyle"
										style="width: 60px; float: left" /> <img id="loginCodeImg"
										src="${base_path }/verification/get_code.action"
										style="margin-top: 5px; float: right">
								</div>
							</div>
							<div class="pwdArea">
								<label class="input-tips"></label>
								<div class="inputOuter">
									<a href="${base_path }/forgetPassword/firstPassword/2.html"
										style="cursor: pointer;">忘记密码？点击找回</a>
								</div>
							</div>
							<div class="pwdArea">
								<input type="submit" id="login" value="登 录"
									style="width: 125px; margin-left: 10px;" class="button_blue" />
								<input type="button" onclick="goBack()" value="返 回"
									style="width: 125px; margin-left: 10px;" class="button_blue" />
							</div>
						</form>
					</div>
				</div>
			</div>
			<!--登录end-->
		</div>
		<!--注册-->
		<div class="qlogin" id="qlogin" style="display: none;">

			<div class="web_login">
				<form name="form2" id="regUser" accept-charset="utf-8">
					<input type="hidden" name="to" value="reg" /> <input type="hidden"
						name="did" value="0" />
					<ul class="reg_form" id="reg-ul">
						<li style="margin-top: 20px"><label for="user"
							class="input-tips2">身份证号：</label>
							<div class="inputOuter2">
								<input type="text" id="ID_Num" name="ID_Num" maxlength="18"
									class="inputstyle2" />
							</div></li>
						<li><label for="passwd" class="input-tips2">密码：</label>
							<div class="inputOuter2">
								<input type="password" id="passwd" name="passwd" maxlength="16"
									class="inputstyle2" onkeyup="change()"/>
							</div></li>
						<li><label for="passwd2" class="input-tips2">确认密码：</label>
							<div class="inputOuter2">
								<input type="password" id="conPasswd" name="conPasswd"
									maxlength="16" class="inputstyle2"  onkeyup="change()" onblur="changes()"/>
							</div></li>
						<li><label for="phone" class="input-tips2">手机号：</label>
							<div class="inputOuter2">

								<input type="text" id="phone" name="phone" maxlength="11"
									class="inputstyle2" />
							</div></li>
						<li><label for="phoneCheck" class="input-tips2">验证码：</label>
							<div class="inputOuter2">
								<input type="text" id=RegisterCode name="phoneCheck"
									 maxlength="10" class="inputstyle2"
									style="width: 70px; float: left; margin-right: 10px" /> <span
									id="Prompt1"></span> <input class="button"
									style="" type="button" value="获取验证码"
									id="sendMessage" onclick="sendPhonecode()" />

							</div></li>
						<li>
							<div class="inputArea">
								<input type="button" id="reg"
									style="margin-top: 10px; margin-left: 85px; width: 135px"
									class="button_blue" value="注册" />
							</div>

						</li>

					</ul>
				</form>
			</div>
		</div>
		<!--注册end-->
	</div>
	
	<script type="text/javascript"
		src="${base_path }/resources/common/lib/layer/2.4/layer.js"></script>
	<script type="text/javascript"
		src="${base_path }/resources/common/lib/md5.js"></script>
	<script>
		var msg = "${errmsg}";
		if (!(msg == null || msg == "")) {
			layer.msg(msg);
		}
	</script>
	<script type="text/javascript">
		function changes(){
            var pas=$("#passwd").val();
            var pas2=$("#conPasswd").val();
            if(pas!=pas2) {
                layer.tips('两次密码不一致','#conPasswd',{ tips: [1, '#FF0033']});
            }
		}

        function change(){
            var pas=$("#passwd").val();
            var  paw2=$("#conPasswd");
            var pas2=$("#conPasswd").val();
            if(pas!=pas2){
                paw2.css("border","1px solid red");
            }
            if(pas==pas2){
                paw2.css("border","1px solid #D7D7D7");
            }
        }
		//向手机发送验证码
		var base_path = $('#base_path').val();
		var InterValObj; //timer变量，控制时间
		var count = 120; //间隔函数，120秒执行
		var curCount;//当前剩余秒数
		function sendMessage(url) {
			var phone = $('#phone').val();

			curCount = count;
			//设置button效果，开始计时
			$("#sendMessage").attr("disabled", "true");
			//向后台发送处理数据
			$
					.ajax({
						contentType : "application/x-www-form-urlencoded;charset=utf-8",
						url : base_path + url,
						type : "POST",
						dataType : "json",
						data : {
							"phone" : phone,
							"type" : 2
						},
						success : function(data) {
							//alert(data);
							if (data.errorCode==1) {
								layer.msg(data.msg, {
									icon : 1
								});
								$("#sendMessage").val("重新获取(" + curCount + ")");
								InterValObj = window.setInterval(SetRemainTime,
										1000); //启动计时器，1秒执行一次

							} else {
								layer.msg(data.msg, {
									icon : 0
								});
								$("#sendMessage").removeAttr("disabled");
								$("#sendMessage").val("重新获取");
							}
						}
					});
		}
		//timer处理函数
		function SetRemainTime() {
			if (curCount == 0) {
				window.clearInterval(InterValObj);//停止计时器
				$("#sendMessage").removeAttr("disabled");//启用按钮
				$("#sendMessage").val("重新获取");
			} else {
				curCount--;
				$("#sendMessage").val("重新获取(" + curCount + ")");
			}
		}

		function verifyCodeAnswer() {
			//验证是否通过
			var answer = $('#answer').val();
			if (answer != null && answer.length > 0) {
				if (answer.length < 5) {
					$
							.ajax({
								contentType : "application/x-www-form-urlencoded;charset=utf-8",
								url : base_path
										+ "/verification/verify_code_answer.action",
								type : "POST",
								dataType : "json",
								data : {
									"code" : answer
								},
								success : function(data) {
									if (data.status == 200) {
										layer.closeAll();

										//验证通过发送手机验证码
										sendMessage(data.url);
										return true;
									} else {
										layer.tips('答案错误', '#answer', {
											tips : [ 1, '#FF0033' ]
										});
										return false;
									}
								}
							});
				} else {
					layer.tips('答案错误', '#answer', {
						tips : [ 1, '#FF0033' ]
					});
					return false;
				}
			} else {
				layer.tips('请填写答案', '#answer', {
					tips : [ 1, '#FF0033' ]
				});
				return false;
			}
		}
		//发送验证码
		var phone = $("#phone");
// 		var state = false;
		var RegisterCode = $("#RegisterCode");
		var base_path = "${base_path}";
		function sendPhonecode() {
			var phone = $('#phone').val();
			var regex = /^[1]{1}[2-578]{1}[0-9]{9}$/;
			if (phone != null && phone.length > 0) {
				if (phone.length != 11 || regex.test(phone) == false) {
					layer.tips('请填写正确的11位手机号', $('#phone'), {
						tips : [ 1, '#FF0033' ],
						time : 40000
					});
					return false;
				}
			} else {
				layer.tips('请填写手机号', $('#phone'), {
					tips : [ 1, '#FF0033' ],
					time : 40000
				});
				return false;
			}
			MatchCodeDialog();

		}
	</script>
	<script type="text/javascript">
		function ep() {
			encryptPassword($("#p"), '${_SALT_IN_SESSION_}');
			return true;
		}

		$("#loginCodeImg")
				.click(
						function() {
							document.getElementById("loginCodeImg").src = '${base_path }/verification/get_code.action?'
									+ Math.random();
						});
		function goBack() {
			window.location.href = "${base_path}/index.html"
		}
	</script>
	<script type="text/javascript">
		var path = "${base_path}";
		$("#reg")
				.click(
						function() {
							if (!(/^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/
									.test($("#ID_Num").val()))) {
								layer.msg("请输入正确的身份证号");
								return;
							}
							if (!(/^1[34578]\d{9}$/.test($("#phone").val()))) {
								layer.msg("手机号码有误，请重填");
								return;
							}
							if ($("#phoneCheck").val() == "") {
								layer.msg("验证码不能为空");
								return;
							}
							if ($("#passwd").val() == "") {
								layer.msg("密码不能为空");
								return;
							}
							if ($("#passwd").val() != $("#conPasswd").val()) {
								layer.msg("两次密码输入不同");
								return;
							}
							if (!/^[a-zA-Z0-9]{6,18}$/.test($("#passwd").val())) {
								layer.msg('密码必须6到18位！');
								return;
							}
							if (!/^[a-zA-Z0-9]{6,18}$/.test($("#conPasswd")
									.val())) {
								layer.msg('密码必须6到18位！');
								return;
							}
// 							if (!state) {
// 								layer.msg('验证码错误');
// 								return;
// 							}
							encryptPassword($("#passwd"));
							encryptPassword($("#conPasswd"));
							$.ajax({
								url : path + '/coach/register.action',
								type : 'POST',
								data : $('#regUser').serialize(),
								dataType : 'text',
								success : function(msg) {
									$("#passwd").val("");
									$("#conPasswd").val("");
									layer.msg(msg);
									if (msg == "注册成功") {
										$('#switch_login').removeClass(
												"switch_btn_focus").addClass(
												'switch_btn');
										$('#switch_qlogin').removeClass(
												"switch_btn").addClass(
												'switch_btn_focus');
										$('#switch_bottom').animate({
											left : '0px',
											width : '70px'
										});
										$('#qlogin').css('display', 'none');
										$('#web_qr_login').css('display',
												'block');
									}
								},
								error : function() {
									layer.msg("未知原因注册失败，请重试");
									$("#passwd").val("");
									$("#conPasswd").val("");
								}
							});
						});
		function getMathCode() {
			var url = base_path + "/verification/get_num_code.action?num="
					+ Math.random();
			$("#codeimgMath").prop("src", url);
		}
		function MatchCodeDialog() {
			var html = "<div class='web_diago' style='margin-top:10px'>"
					+ "<div >"
					+ "<input type='text' id='answer' style='margin-left:15px' name='answer' class='inputstyled' placeholder='请输入计算结果'/>"
					+ "<img id='codeimgMath' style='margin-rigt:90px;float: right' name='codeimgMath' src='"
					+ base_path
					+ "/verification/get_num_code.action'  onclick='getMathCode()'/>"
					+ "</div>"
					+ "<div >"
					+ "<button class='button' style='margin-left:240px;margin-top:10px;margin-bottom:10px' onclick='verifyCodeAnswer()'>确定</button>"
					+ "</div>" + "</div>";
			layer.open({
				type : 1,
				title : '请填写一个数字使图片中的式子成立',
				skin : 'layui-layer-lan', //样式类名
				closeBtn : 0, //不显示关闭按钮
				anim : 2,
				shadeClose : true, //开启遮罩关闭
				content : html
			});
			getMathCode();
		}

		$("#login").click(
				function() {

					var myreg = /^1\d{10}$/;
					if (!myreg.test($("#u").val().replace(/^(\s|\u00A0)+/, '')
							.replace(/(\s|\u00A0)+$/, ''))) {
						layer.msg('请输入有效的手机号码！');
						return false;
					}
				})
		$("#login").click(
				function() {
					if ($("#u").val().replace(/^(\s|\u00A0)+/, '').replace(
							/(\s|\u00A0)+$/, '') == ""
							|| $("#u").val().replace(/^(\s|\u00A0)+/, '')
									.replace(/(\s|\u00A0)+$/, '') == null) {
						layer.msg('手机号码不能为空！');
						return false;
					}
				})
		$("#login").click(function() {
			if ($("#p").val() == "" || $("#p").val() == null) {
				layer.msg('密码不能为空！');
				return false;
			}
			if ($("#icode").val() == "" || $("#icode").val() == null) {
				layer.msg("验证码不能为空");
				return false;
			}
			if (!/^[a-zA-Z0-9]{6,18}$/.test($("#p").val())) {
				layer.msg('密码必须6到18位！');
				return false;
			}
		})
	</script>
</body>
</html>