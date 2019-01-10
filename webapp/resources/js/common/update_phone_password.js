var base_path = $('#base_path').val();
var type = $('#typePhone').val();
var phone = $('#SMSPhone').val();
var SMSPhoneCode = $('#SMSPhoneCode').val();


// 确定旧手机号码
$(document).on("click",'#next-step-phone',function() {
	$(this).off("click");
	var oldPhoneCode = $("#oldPhoneCode").val();
	if(oldPhoneCode == ""|| oldPhoneCode == null){
		layer.alert("验证码不能为空！");
		return;
	}
	$.ajax({
		type : "POST",
		url : base_path + "/updatePhone/YzPhoneCode.action",
		data : {
			"oldPhoneCode":$('#oldPhoneCode').val(),
			"type": type
		},
		dataType : 'json',
		success : function(result) {
			  if ((result.error)==1){
				  layer.alert("验证成功！");
                  nextstep_phone();
              }else {
            	  layer.alert("验证码错误！");
              }
		},
		error : function(error) {
			
		}
	});
});

// 发送新手机验证码
function new_phone_code() {
	var new_phone = $('#new_phone').val();
	var phone = $('#SMSPhone').val();
	if (new_phone == "" || new_phone == null){
		layer.alert("手机号码不能为空！");
		return;
	}
	if (!(/^1[34578]\d{9}$/.test($("#new_phone").val()))) {
		layer.alert("新手机号码有误，请重填");
		return;
	}
	if(new_phone==phone){
		layer.alert("该手机号码与原手机相同");
		return;
	}
	$.ajax({
		type : "POST",
		url : base_path+ "/sms/getRegisterCode.action",
		data : {
			"phone" : $('#new_phone').val(),
			"type": type
		},
		dataType : 'json',
		success : function(json) {
		    if (json.success){
				time(".sendcode1");
				var wait = 0;
				layer.alert(json.msg);
            }else {
            	layer.alert(json.msg);
            }
		
		},
		error : function(error) {
			console.log(error);
		},
	});
}


//修改手机确定修改
function change_phone(){
	var new_phone = $('#new_phone').val();
	var newPhoneCode = $('#newPhoneCode').val();
	if (new_phone == "" || new_phone == null){
		layer.alert("手机号码不能为空！");
		return;
	}
	if (!(/^1[34578]\d{9}$/.test($("#new_phone").val()))) {
		layer.alert("新手机号码有误，请重填");
		return;
	}
	if(newPhoneCode == ""|| newPhoneCode == null){
		layer.alert("验证码不能为空！");
		return;
	}
	 $.ajax({
			type: "POST",
			url: base_path+  "/updatePhone/CheckNewCode.action",
			data: {new_phone:$('#new_phone').val(),newPhoneCode:$('#newPhoneCode').val(),"type": type}, 
			dataType: 'json',
			success:function(json) {
				  if (json.status==2){
						layer.alert(json.error);
						$('#modal-demo').modal("hide");
						window.parent.location.reload(); 
	                    state = true;
	                }else {
	                	layer.alert(json.error);
	                    state = false;
	                }
			  },
	    	error:function(error) {
	    		console.log(error);
	  			}
			});
  }



























// 修改密码
function updatepassword(salt) {
	
					var old_password = $("#old_password").val();
					var new_password = $("#new_password").val();
					var new_password_n = $("#new_password_n").val();
					var phone_dynamic_code = $("#phone_dynamic_code").val();
					if (new_password == "" || new_password_n == ""
							|| old_password == "" || new_password == null
							|| new_password_n == null || old_password == null) {
						layer.alert("密码不能为空！");
						return;
					}
					if(phone_dynamic_code == "" || phone_dynamic_code == null){
						layer.alert("手机动态码不能为空！");
						return;
					}
					if (new_password != new_password_n) {
						layer.alert("两次输入不一致！");
						return;
					}
					if (new_password == old_password) {
						layer.alert("新密码与旧密码相同");
						return;
					}
					var re = /^([^\u4e00-\u9fa5]){6,18}$/;
					if (!re.exec(new_password)) {
						layer.alert("密码只能为6-18位且不能为中文！");
						return;
					}

					encryptPassword($("#new_password"));
					encryptPassword($("#new_password_n"));
					encryptPassword($("#old_password"), salt);

				
					$.ajax({
								type : "get",
								url : base_path+ "/updatePassword/update_password.action",
								data : {
									old_password : $('#old_password').val(),
									new_password : $('#new_password').val(),
									phone_dynamic_code : $('#phone_dynamic_code').val()
								},
								dataType : 'json',
								success : function(result) {
									if ((result.error) == "原密码错误!") {
										layer.alert(result.error, function() {
											$("#old_password")[0].value = "";
											$("#new_password")[0].value = "";
											$("#new_password_n")[0].value = "";
											$("#phone_dynamic_code")[0].value = "";
											layer.closeAll('dialog');
										});
									} 
									if ((result.error) == "新密码格式错误!") {
										layer.alert(result.error, function() {
											$("#old_password")[0].value = "";
											$("#new_password")[0].value = "";
											$("#new_password_n")[0].value = "";
											$("#phone_dynamic_code")[0].value = "";
											layer.closeAll('dialog');
										});
									}
									if ((result.error) == "手机动态码错误!") {
										layer.alert(result.error, function() {
											$("#old_password")[0].value = "";
											$("#new_password")[0].value = "";
											$("#new_password_n")[0].value = "";
											$("#phone_dynamic_code")[0].value = "";
											layer.closeAll('dialog');
										});
									} 
									if(result.error == "修改成功！请重新登录！"){
										 if(result.status==1){
											 layer.alert("修改成功！请重新登录！");
											 window.parent.location.href =base_path + '/player/login_view.html';
										 }
										 if(result.status==2){
											 layer.alert("修改成功！请重新登录！");
											 window.parent.location.href = base_path +'/coach/log_view.html';
										 }
										 if(result.status==3){
											 layer.alert("修改成功！请重新登录！");
											 window.parent.location.href = base_path + '/org_login/org_login_view.html';
										 }
										 if(result.status==4){
											 layer.alert("修改成功！请重新登录！");
											 window.parent.location.href = base_path + '/team/login_view.html';
										 }
										 if(result.status==5){
											 layer.alert("修改成功！请重新登录！");
											 window.parent.location.href = base_path + '/site/login_view.html';
										 }
											
													
									}
								},
								error : function(error) {
									console.log(error);
								}

							});
				}



function old_phone_code(){
			$.ajax({
				type : "POST",
				url :base_path+ "/sms/getRegisterCode.action",
				data : {
					"phone" : $('#SMSPhone').val(),
					"type": type,
					"smsphone":1
				},
				dataType : 'json',
				success : function(json) {
					  if (json.success){
							time(".sendcode");
							var wait = 0;
							layer.alert(json.msg);
		              }else {
		            	  layer.alert(json.msg);
		              }
				},
				error : function(error) {
					layer.alert("服务器错误！")
					console.log(error);
				},
			});
		
}

//获取数学表达式验证码
function getMathCode(){
	var url = base_path+"/verification/get_num_code.action?num="+Math.random();
	$("#codeimgMath").prop("src",url);
}

//数学表达式验证码弹窗
	//说明：请更改login2.css文件中样式
function MatchCodeDialog(n){
	var html = "<div class='web_diago' style='margin-top:10px'>" +
					"<div >" +
					"<input type='text' id='answer' name='answer' style='width:50%;margin-left:3%;height:40px;' class='input-text radius' placeholder='请输入计算结果'/>" +
						"<img id='codeimgMath' style='margin-rigt:90px;float: right' name='codeimgMath' src='"+base_path+"/verification/get_num_code.action'  onclick='getMathCode()'/>" +
					"</div>" +
					"<div >" +
							"<button class='btn btn-primary'  style='margin-left:250px;margin-top:10px;margin-bottom:10px' onclick='verifyCodeAnswer("+n+")'>确定</button>" +
					"</div>" +
				"</div>";
	layer.open({
		  type: 1,
		  title:'请填写一个数字使图片中的式子成立',
		  skin: 'layui-layer-lan', //样式类名
		  closeBtn: 0, //不显示关闭按钮
		  anim: 2,
		  shadeClose: true, //开启遮罩关闭
		  content: html
		});
	getMathCode();
}

//弹出验证对话弹框验证码验证
function verifyCodeAnswer(n){
	//验证是否通过
	var answer = $('#answer').val();
	if(answer!=null && answer.length>0){
		if(answer.length<5){
			$.ajax({
				contentType: "application/x-www-form-urlencoded;charset=utf-8",
		        url: base_path+"/verification/verify_code_answer.action",
		        type: "POST",
		        dataType: "json",
		        data: {"code" : answer,"type":n},
		        success: function (data) {
		        	if(data.status==200){
		        		layer.closeAll();
		        		if(data.type==1){
		        			//验证通过发送旧手机验证码
			        		old_phone_code();
		        		}
		        		if(data.type==2){
		        			new_phone_code();
		        		}
		        		
		        		return true;
		        	}else{
		        		layer.tips('答案错误','#answer',{ tips: [1, '#FF0033']});
		    			return false;
		        	}
		        }
		    });
		}else{
			layer.tips('答案错误','#answer',{ tips: [1, '#FF0033']});
			return false;
		}
	}else{
		layer.tips('请填写答案','#answer',{ tips: [1, '#FF0033']});
		return false;
	}
}
