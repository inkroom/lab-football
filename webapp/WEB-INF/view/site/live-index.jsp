<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>现场管理系统</title>
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
	<header class="navbar-wrapper">
		<div class="navbar navbar-fixed-top">
			<div class="container-fluid cl">
				<a class="logo navbar-logo f-l mr-10 hidden-xs">现场管理</a>
				<nav id="Hui-userbar"
					class="nav navbar-nav navbar-userbar hidden-xs">
					<ul class="cl">
						<li class="dropDown dropDown_hover"><a href="#"
							class="dropDown_A">${userName}<i class="Hui-iconfont">&#xe6d5;</i></a>
							<ul class="dropDown-menu menu radius box-shadow">
								<li><a href="javascript:;" onClick="modaldemo1()">修改密码</a></li>
								<li><a href="${base_path}/site/loginOut.html">退出</a></li>
							</ul></li>
						<li id="Hui-msg"><a onclick="check()"
							data-href="${base_path}/site/message.action"
							href="javascript:void(0)" title="消息中心"> <span
								class="badge badge-danger" id="countMessage"></span> <i
								class="Hui-iconfont" style="font-size: 18px">&#xe68a;</i>
						</a></li>
					</ul>
				</nav>
			</div>
		</div>
	</header>
	<aside class="Hui-aside">
		<input runat="server" id="divScrollValue" type="hidden" value="" />

		<div class="menu_dropdown bk_2">
			<dt id="menu-111">
				<a data-href="${base_path}/site/playerCheck.action"
					data-title="现场验证" href="javascript:void(0)"><i
					class="Hui-iconfont">&#xe627;</i>&nbsp;现场验证</a>
			</dt>
			<dt id="menu-gameLive">
				<a data-href="${base_path}/site/gameLive.action" data-title="比赛直播"
					href="javascript:void(0)"><i class="Hui-iconfont">&#xe66f;</i>&nbsp;比赛直播</a>
			</dt>
			<dt id="menu-judgment">
				<a data-href="${base_path}/site/judement.action" data-title="裁判员报告书"
					href="javascript:void(0)"><i class="Hui-iconfont">&#xe627;</i>&nbsp;裁判员报告书</a>
			</dt>

		</div>
	</aside>
	<div class="dislpayArrow hidden-xs">
		<a class="pngfix" href="javascript:void(0);"
			onClick="displaynavbar(this)"></a>
	</div>
	<section class="Hui-article-box">
		<div id="Hui-tabNav" class="Hui-tabNav hidden-xs">
			<div class="Hui-tabNav-wp">
				<ul id="min_title_list" class="acrossTab cl">
					<li class="active"><span title="消息中心"
						data-href="${base_path }/message/message-all.html">消息中心</span> <em></em></li>
				</ul>
			</div>
			<div class="Hui-tabNav-more btn-group">
				<a id="js-tabNav-prev" class="btn radius btn-default size-S"
					href="javascript:;"><i class="Hui-iconfont">&#xe6d4;</i></a><a
					id="js-tabNav-next" class="btn radius btn-default size-S"
					href="javascript:;"><i class="Hui-iconfont">&#xe6d7;</i></a>
			</div>
		</div>
		<div id="iframe_box" class="Hui-article">
			<div class="show_iframe">
				<div style="display: none" class="loading"></div>
				<iframe scrolling="yes" frameborder="0"
					src="${base_path }/message/message-all.html"></iframe>
			</div>
		</div>
	</section>

	<div class="contextMenu" id="Huiadminmenu">
		<ul>
			<li id="closethis">关闭当前</li>
			<li id="closeall">关闭全部</li>
		</ul>
	</div>
	<!--模态框-->
	<div id="modal-demo" class="modal fade" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content radius">
				<div class="modal-header">
					<h3 class="modal-title">更改手机号</h3>
					<a class="close" data-dismiss="modal" aria-hidden="true"
						href="javascript:void();">×</a>
				</div>
				<div class="modal-body">
					<form action="#">
						<table class="table">
							<tr>
								<td class="text-r"><p class="f-16 mr-10">原手机号码:</p></td>
								<td><input type="text" class="input-text radius"
									name="old_phone" id="old_phone" disabled="disabled" /></td>
							</tr>
							<tr>
								<td class="text-r"><p class="f-16 mr-10">验证码:</p></td>
								<td><input type="text" class="input-text radius"
									placeholder="验证码" id="oldPhoneCode" style="width: 70%" /> <input
									type="button" class="btn btn-default radius sendcode"
									onclick="old_phone_code()" value="获取验证码"></input></td>
							</tr>
						</table>
					</form>

				</div>
				<div class="modal-footer">
					<button class="btn btn-primary" id="next-step-phone">下一步</button>
					<button id="closed-1" class="btn" data-dismiss="modal"
						aria-hidden="true">关闭</button>
				</div>
			</div>
		</div>
	</div>

	<div id="modal-demo_info" class="modal fade" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true"
		data-keyboard="false">
		<div class="modal-dialog">
			<div class="modal-content radius">
				<div class="modal-header">
					<h3 class="modal-title">完善信息</h3>
				</div>
				<div class="modal-body">
					<form action="#">
						<table class="table">
							<tr>
								<td class="text-r"><p class="f-16 mr-10">姓名:</p></td>
								<td><input type="text" class="input-text radius"
									id="preName" placeholder="输入姓名" id="inputName"
									onblur="checkName(this)" /></td>
							</tr>
							<tr>
								<td class="text-r"><p class="f-16 mr-10">手机号:</p></td>
								<td><input type="text" class="input-text radius"
									id="prePhone" placeholder="输入手机号" id="inputPhone"
									onblur="checkPhone(this)" /></td>
							</tr>
							<tr>
								<td class="text-r"><p class="f-16 mr-10">验证码:</p></td>
								<td><input type="text" class="input-text radius"
									id="inCode" placeholder="输入验证码" style="width: 65%;" /> <input
									class="btn btn-success radius" type="button" id="sendMessage"
									onclick="valiCode()" value="发送验证码" /></td>
							</tr>
						</table>
					</form>
				</div>
				<div class="modal-footer">
					<button class="btn btn-primary" id="perInfoId" onclick="perInfo()">确认</button>
				</div>
			</div>
		</div>
	</div>

	<div id="modal-demo1" class="modal fade" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content radius">
				<div class="modal-header">
					<h3 class="modal-title">修改密码</h3>
					<a class="close" data-dismiss="modal" aria-hidden="true"
						href="javascript:void(0);">×</a>
				</div>
				<div class="modal-body">
					<div class="modal-body">
						<form class="form formControls">
							<div class="row cl ">
								<label class="form-label col-xs-4 col-sm-3">旧密码：</label>
								<div class="formControls col-xs-8 col-sm-8">
									<input type="password" class="input-text radius" value=""
										placeholder="" name="old_password" id="old_password">
								</div>
							</div>
							<div class="row cl ">
								<label class="form-label col-xs-4 col-sm-3">新密码：</label>
								<div class="formControls col-xs-8 col-sm-8">
									<input type="password" class="input-text radius" value=""
										placeholder="" name="new_password" id="new_password">
								</div>
							</div>
							<div class="row cl ">
								<label class="form-label col-xs-4 col-sm-3">确认密码：</label>
								<div class="formControls col-xs-8 col-sm-8">
									<input type="password" class="input-text radius" value=""
										placeholder="" name="new_password_n" id="new_password_n">
								</div>
							</div>
							<div class="row cl ">
							<label class="form-label col-xs-4 col-sm-3">手机动态码：</label>
							<div class="formControls col-xs-8 col-sm-8">
								<input type="text" class="input-text radius" value="" placeholder="" name="phone_dynamic_code"  id="phone_dynamic_code">
							</div>
						</div>
						</form>

					</div>

				</div>
				<div class="modal-footer">
					<button class="btn btn-primary"
						onclick="updatepassword('${_SALT_IN_SESSION_}')">确认修改</button>

					<button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
				</div>
			</div>
		</div>
	</div>

	//用户手册
	<div id="modal-demo3" class="modal fade" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true" data-keyboard="false">
		<div class="modal-dialog">
			<div class="modal-content radius">
				<div class="modal-header">
					<h3 class="modal-title">注意事项：</h3>
				</div>
				<div class="modal-body"  style="height:400px;overflow:scroll;">
					<div class="panel panel-default">
						<div class="panel-header">
							<span class="label label-success radius">5人：</span>
						</div>
						<div class="panel-body">
							一场五人制足球赛应由两队参加，每队上场队员不得多于5名，其中必须有1名为守门员。<br>
							在比赛中任何一队因队员被罚出场，使得其场上队员少于两名时，该场比赛为弃权。正式比赛，都可使用替补队员。各队替补队员不得超过7名。
						</div>
					</div>
					<div class="panel panel-default">
						<div class="panel-header">
							<span class="label label-success radius">7人：</span>
						</div>
						<div class="panel-body">
							一场比赛应由两队参加，每队上场队员不得多于7人，其中必须有1人为守门员。<br>如果比赛前任何一队队员少于5人或在比赛中队员被罚出场致使场内队员少于5人时，该场比赛队员少的队为弃权，对方2:0胜，如对方净胜球数超过2个，
							则按实际比分计。每场比赛准许换三个人。
						</div>
					</div>
					<div class="panel panel-default">
						<div class="panel-header">
							<span class="label label-success radius">11人：</span>
						</div>
						<div class="panel-body">
							每队上场队员不得多于11名,其中必须有一名守门员。<br>如果一队的场上队员少于7人,则比赛不能开始。
							奥运会足球比赛中,每场比赛最多可以使用3名替补队员;场外和场上队员未经裁判员许可不能擅自进出场地。<br>
							比赛时,守门员和其他队员的位置不能随意交换,如需要交换,须经过裁判员同意。
						</div>
					</div>
				</div>
				<div class="modal-footer" style="text-align:center;">
					<button class="btn btn-primary" id="readTime" onclick="ReadTime()">我知道了</button>
				</div>
			</div>
		</div>
	</div>
	<input type="text" id="base_path" hidden="" value="${base_path}">
	<input type="text" id="PreInfo" style="display: none;" name="${isPre}" />
	<input type="text" id="readTimeCount" style="display: none;" name="${readTime}" />
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
	<script type="text/javascript"
		src="${base_path }/resources/common/lib/md5.js"></script>
	<!--/_footer 作为公共模版分离出去-->
	<!--请在下方写此页面业务相关的脚本-->
	<script type="text/javascript"
		src="${base_path}/resources/common/lib/jquery.contextmenu/jquery.contextmenu.r2.js"></script>
	<script type="text/javascript"
		src="${base_path}/resources/js/common/update_phone_password.js"></script>
	<script type="text/javascript">
		//获取数学表达式验证码
		function getMathCode() {
			var url = "${base_path}/verification/get_num_code.action?num="
					+ Math.random();
			$("#codeimgMath").prop("src", url);
		}

		//数学表达式验证码弹窗
		function MatchCodeDialog() {
			var html = "<div class='web_diago' style='margin-top:10px'>"
					+ "<div >"
					+ "<input type='text' id='answer' name='answer' style='width:50%;margin-left:3%;height:40px;' class='input-text radius' placeholder='请输入计算结果'/>"
					+ "<img id='codeimgMath' style='margin-rigt:90px;float: right' name='codeimgMath' src='"
					+ base_path
					+ "/verification/get_num_code.action'  onclick='getMathCode()'/>"
					+ "</div>"
					+ "<div >"
					+ "<button class='btn btn-primary' style='margin-left:250px;margin-top:10px;margin-bottom:10px' onclick='verifyCodeAnswer()'>确定</button>"
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

		//弹出验证对话弹框验证码验证
		function verifyCodeAnswer() {
			//验证是否通过
			var answer = $('#answer').val();
			if (answer != null && answer.length > 0) {
				if (answer.length < 5) {
					$
							.ajax({
								contentType : "application/x-www-form-urlencoded;charset=utf-8",
								url : "${base_path}/verification/verify_code_answer.action",
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
		var InterValObj; //timer变量，控制时间
		var count = 120; //间隔函数，1秒执行
		var curCount;//当前剩余秒数
		var state = false;
		//发送验证码
		function valiCode() {
			var temp = false;
			var phone = $('#prePhone').val();
			var regex = /^[1]{1}[2-578]{1}[0-9]{9}$/;
			if (phone != null && phone.length > 0) {
				if (phone.length != 11 || regex.test(phone) == false) {
					layer.tips('请填写正确的11位手机号', '#prePhone', {
						tips : [ 1, '#FF0033' ]
					});
					temp = false;
				} else {
					temp = true;
				}
			} else {
				layer.tips('请填写手机号', '#prePhone', {
					tips : [ 1, '#FF0033' ]
				});
				return false;
			}
			if (temp == true) {
				//弹出验证对话弹框
				MatchCodeDialog();
			}
		}

		$("#inCode").blur(function() {
			$.ajax({
				url : "${base_path}/sms/validateMobileCode.action",
				type : "POST",
				data : {
					"mobile" : $("#prePhone").val(),
					"randomCode" : $("#inCode").val(),
					"type" : "5"
					
				},
				dataType : "JSON",
				success : function(json) {
					if (json.success) {
						state = true;
					} else {
						layer.tips('请填写正确的验证码', '#inCode', {
							tips : [ 1, '#FF0033' ]
						});
						$("#inCode").val('');
						state = false;
					}
				},
				error : function() {
					layer.msg("服务器错误！")
				}
			})
		});

		function sendMessage() {
			var phone = $('#prePhone').val();
			curCount = count;
			//设置button效果，开始计时
			$("#sendMessage").attr("disabled", "true");
			//向后台发送处理数据
			$.ajax({
				url : "${base_path}/sms/getRegisterCode.action",
				type : "POST",
				dataType : "json",
				data : {
					"phone" : phone,
					"type" : "5",
					"smsphone":"1",
					"flag":"1"
				},
				success : function(json) {
					if (json.success) {
						$("#sendMessage").val(curCount + "秒后重新获取");
						InterValObj = window.setInterval(SetRemainTime, 1000); //启动计时器，1秒执行一次	
					} else {
						layer.tips(json.msg, '#inCode', {
							tips : [ 1, '#FF0033' ]
						});
						$("#sendMessage").removeAttr("disabled");
					}
				}
			});
		}

		//timer处理函数
		function SetRemainTime() {
			if (curCount == 0) {
				window.clearInterval(InterValObj);//停止计时器
				$("#sendMessage").removeAttr("disabled");//启用按钮
				$("#sendMessage").val("重新发送验证码");
			} else {
				curCount--;
				$("#sendMessage").val(curCount + "秒后重试");
			}
		}

		//验证中英数字
		function validationCNZNNumber(value, len) {
			var regex = /^[\u4E00-\u9FA5A-Za-z0-9]+$/;
			if (value != null && value.length > 0 && value.length <= len
					&& regex.test(value) == true) {
				return true;
			} else {
				return false;
			}
		}

		//验证中文和点
		function validationCHS(value) {
			var regex = /^[\u4e00-\u9fa5a-zA-Z]+[\·]{0,1}[\u4e00-\u9fa5a-zA-Z]+$/;
			if (value == null || value.length == 0 || value.length > 11
					|| regex.test(value) == false) {
				return false;
			} else {
				return true;
			}
		}
		
		var readObj;
		var timeCount = 10;
		//timer处理函数
		function SetReadTime() {
			if (timeCount == 0) {
				window.clearInterval(readObj);//停止计时器
				$("#readTime").removeAttr("disabled");//启用按钮
				$("#readTime").text("我知道了");
			} else {
				$("#readTime").text(timeCount + "秒");
				timeCount--;
			}
		}
		
		$(function() {
			var info = document.getElementById("PreInfo").name;
			if (info == 'notPre') {
				$('#modal-demo_info').modal({
					show : true,
					backdrop : true
				})
			}
			var times = document.getElementById("readTimeCount").name;
			if(times==0){
				$('#modal-demo3').modal({
					show : true,
					backdrop : true
				})
				$("#readTime").attr("disabled", "true");//启动计时器，1秒执行一次	
				readObj = window.setInterval(SetReadTime, 1000); 
			}	
			
		});
	
		function ReadTime(){
			$.ajax({
				type : 'post',
				url : "${base_path}/site/readTime.html",
				data : {
					count:1
				},
				success:function(data){
					if(data=="error"){
						layer.msg("发生未知错误，请刷新页面");
					}else if(data=="success"){
						window.location.reload();
					}
				},
				error:function(){
					layer.msg("发生错误，请刷新页面");
				}
				});
			
		}
		
		function checkName() {
			var na = true;
			var name = $("#preName").val().replace(/\s/g, "");
			if (name.length > 40 || validationCHS(name) == false) {
				$("#preName").val("");
				layer.tips('请填写正确姓名格式', '#preName', {
					tips : [ 1, '#FF0033' ]
				});
				na = false;
			}
			return na;
		}
		function checkPhone() {
			var ph = true;
			var phone = $("#prePhone").val().replace(/\s/g, "");
			var regex = /^[1]{1}[3-578]{1}[0-9]{9}$/;
			if (phone.length != 11 || regex.test(phone) == false) {
				$("#prePhone").val("");
				layer.tips('请填写正确手机格式', '#prePhone', {
					tips : [ 1, '#FF0033' ]
				});
				ph = false;
			}
			return ph;
		}
		function perInfo() {
			var name = $("#preName").val().replace(/\s/g, "");
			var phone = $("#prePhone").val().replace(/\s/g, "");
//			if (state == false) {
//				layer.tips('请填写正确验证码', '#inCode', {
//					tips : [ 1, '#FF0033' ]
//				});
//			} else {
				if (checkName() && checkPhone()) {
					$.ajax({
						type : 'post',
						url : "${base_path}/site/preInfo.html",
						data : {
							randomCode : $("#inCode").val(), 
							name : name,
							phone : phone
						},
						success : function(data) {
							if (data == 'success') {
								window.location.reload();
							} else {
								layer.msg(data);
								$(function() {
									$('#modal-demo_info').modal({
										show : true,
										backdrop : true
									})
								});
							}
						},
						error : function(data) {
							window.location.reload();
						}
					});
				} else {
					layer.msg("请正确填写完善信息");
					window.location.reload();
				}
		}

		function modaldemo() {
			$.ajax({
				type : "get",
				url : "${base_path}/updatePhone/getPhone.action",
				dataType : 'json',
				success : function(data) {
					$("#old_phone").val(data.getPhone);
				},
				error : function(error) {
					console.log(error);
				}
			});
			$("#que-1").remove();
			$("#modal-demo").modal("show")
		}
		function modaldemo1() {
			$("#modal-demo1").modal("show")
		}

		function nextstep_phone() {
			$("#modal-demo form table").remove();
			$("#next-step-phone").remove();
			$("#modal-demo form")
					.append(
							"<table class='table'><tr><td class='text-r'><p class='f-16 mr-10'>新手机号码:</p></td><td><input type='text' class='input-text radius'  name='new_phone' id='new_phone'  placeholder='输入新手机号码'/></td></tr><tr><td class='text-r'><p class='f-16 mr-10'>验证码:</p></td><td><input type='text' class='input-text radius' placeholder='验证码' id = 'newPhoneCode' style='width: 70%'/><input type='button' class='btn btn-default radius sendcode' onclick='new_phone_code()' value='获取验证码'></input></td></tr></table>")
			$("#modal-demo .modal-footer")
					.append(
							"<button id='que-1' class='btn btn-primary' onclick='change_phone()'>确认更换</button>")
		}
		$("#modal-demo a.close,#closed-1")
				.click(
						function() {
							$("#modal-demo form table").remove();
							$("#next-step-phone").remove();
							$("#modal-demo form")
									.append(
											"<table class='table'><tr><td class='text-r'><p class='f-16 mr-10'>原手机号码:</p></td><td><input type='text' class='input-text radius'  name='old_phone'  id='old_phone'  disabled='disabled'/></td></tr><tr><td class='text-r'><p class='f-16 mr-10'>验证码:</p></td><td><input type='text' class='input-text radius' placeholder='验证码'  id = 'oldPhoneCode' style='width: 70%'/><input type='button' class='btn btn-default radius sendcode'  onclick='old_phone_code()' value='获取验证码'></input></td></tr></table>")
							$("#modal-demo .modal-footer #closed-1")
									.before(
											"<button class='btn btn-primary'  id='next-step-phone'>下一步</button>")
						})

		$(document).ready(function() {
			$.ajax({
				type : "get",
				url : "${base_path}/message/countMessage.action",
				dataType : 'json',
				success : function(data) {
					$("#countMessage").html(data.countMessage);
				},
				error : function(error) {
					console.log(error);
				}
			});
		});
		function countMessage(count) {
			$("#countMessage").html(count);
		}
	</script>
</body>
</html>