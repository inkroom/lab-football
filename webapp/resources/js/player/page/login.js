function registe(path){
	if(!(/^\d{6}(18|19|20)?\d{2}(0[1-9]|1[12])(0[1-9]|[12]\d|3[01])\d{3}(\d|X)$/i.test($("#user").val()))){

		layer.msg("请输入正确的身份证号",{icon: 0});
		return ;
	} 
	var passwd=$("#passwd").val();
	if($("#passwd").val()==""){
		layer.msg("密码不能为空",{icon: 0});
		return ;
	}
	if(passwd.length<6||passwd.length>18){
		layer.msg("密码长度为6-18位",{icon: 0});
		return ;
	}
	if($("#passwd").val()!=$("#passwd2").val()){
		layer.msg("两次密码输入不同",{icon: 0});
		return ;
	}
	if(!(/^1[34578]\d{9}$/.test($("#phone").val()))){
		layer.msg("手机号码有误，请重新填写",{icon: 0});
		return ;
	} 
	if($("#phoneCheck").val()==""){
		layer.msg("验证码不能为空",{icon: 0});
		return ;
	}
	encryptPassword($("#passwd"));
	encryptPassword($("#passwd2"));
	$.ajax({
		url:path+'/player/register.action',
		type:'POST',
		data:$('#regUser').serialize(),
		dataType:'text',
		success:function(msg){
			var massage=msg+'';
			if(massage=='success'){
				layer.msg('注册成功',{icon: 1});
				toLogin();
				clearValue();
			}else{
				layer.msg(msg+'',{icon: 2});
				$('#passwd').val('');
				$('#passwd2').val('');
			}	
		},
		error:function(){
			
		}
	});
}
function toLogin(){
	$('#switch_login').removeClass("switch_btn_focus").addClass('switch_btn');
	$('#switch_qlogin').removeClass("switch_btn").addClass('switch_btn_focus');
	$('#switch_bottom').animate({left:'0px',width:'70px'});
	$('#qlogin').css('display','none');
	$('#web_qr_login').css('display','block');
}
function clearValue(){
	$('#passwd').val('');
	$('#passwd2').val('');
	$('#user').val('');
	$('#phone').val('');
	$('#phoneCheck').val('');
}

//去掉首尾空格
function trimStr(str){
    return str.replace(/(^\s*)|(\s*$)/g,"");
}

function loginOn(salt) {
	var value = trimStr($("#u").val());
	if(value == ""){
		layer.msg("账号不能为空",{icon: 0});
		return ;
	}
	if($("#p").val()==""){
		layer.msg("密码不能为空",{icon: 0});
		return ;
	}
	if($("#password").val()==""){
		layer.msg("验证码不能为空",{icon: 0});
		return ;
	}
	encryptPassword($("#p"),salt);
	$("#login_form").submit();
}
//注册
var InterValObj; //timer变量，控制时间
var count = 120; //间隔函数，120秒执行
var curCount;//当前剩余秒数
//发送手机验证码
function sendCode(path){
	var phone=$("#phone");
	var RegisterCode = $("#phoneCheck");
	if(!(/^1[34578]\d{9}$/.test(phone.val()))){
		layer.msg("手机号码有误，请重新填写",{icon: 0});
		return ;
	} 
	$.ajax({
		url:path+'/player/register_add_check.action',
		type:'GET',
		data:{phone:phone.val()},
		dataType:'text',
		success:function(msg){
			var massage=msg+'';
			if(massage=='success'){
				MatchCodeDialog(path);
			}else{
				layer.msg(msg+'',{icon:0});
			}	
		}
	});
}
//timer处理函数
function SetRemainTime() {
     if (curCount == 0) {                
         window.clearInterval(InterValObj);//停止计时器
         $("#sendMessage").removeAttr("disabled");//启用按钮
         $("#sendMessage").val("发送验证码");
     }
     else {
         curCount--;
         $("#sendMessage").val("重新发送("+curCount+")");	
     }
}
//脑残加法
function getMathCode(path){
	var url = base_path+"/verification/get_num_code.action?num="+Math.random();
	$("#codeimgMath").prop("src",url);
}
var base_path;
function MatchCodeDialog(path){
	base_path=path;
	var html = "<div class='web_diago' style='margin-top:10px'>" +
					"<div >" +
						"<input type='text' id='answer' name='answer' class='inputstyled' placeholder='请输入计算结果'/>" +
						"<img id='codeimgMath' style='margin-rigt:90px;float: right' name='codeimgMath' src='"+path+"/verification/get_num_code.action'  onclick='getMathCode()'/>" +
					"</div>" +
					"<div >" +
							"<button class='button' style='margin-left:240px;margin-top:10px;margin-bottom:10px' onclick='verifyCodeAnswer()'>确定</button>" +
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
	getMathCode(path);
}
function verifyCodeAnswer(){
	//验证是否通过
	var answer = $('#answer').val();
	if(answer!=null && answer.length>0){
		if(answer.length<5){
			$.ajax({
				contentType: "application/x-www-form-urlencoded;charset=utf-8",
		        url: base_path+"/verification/verify_code_answer.action",
		        type: "POST",
		        dataType: "json",
		        data: {"code" : answer},
		        success: function (data) {
		        	if(data.status==200){
		        		layer.closeAll();
		        		//验证通过发送手机验证码
		        		sendMessage();
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
//发送验证码
function sendMessage(){
	var phone=$("#phone");
	var RegisterCode = $("#phoneCheck");
	if(!(/^1[34578]\d{9}$/.test(phone.val()))){
		layer.msg("手机号码有误，请重新填写",{icon: 0});
		return ;
	} 
	$.ajax({
        url: base_path+"/sms/getRegisterCode.action",
        type: "POST",
        data:{
            "phone" : phone.val(),
            "type" : "1"
        },
        dataType : "JSON",
        success : function (json) {
        	if(json.errorCode==1){
        		layer.msg(json.msg,{icon: 1});
        		curCount=count;
        		$("#sendMessage").attr("disabled", "true");
        		$("#sendMessage").val("重新发送("+curCount+")");	
        		InterValObj = window.setInterval(SetRemainTime, 1000); //启动计时器，1秒执行一次
        	}else{
        		layer.msg(json.msg,{icon: 0});
        		$("#sendMessage").removeAttr("disabled");
        	    $("#sendMessage").val("重新获取");
        	}
        },
        error : function () {
        	layer.msg("服务器错误！",{icon: 0});
        }
    });
}



//回车登录
function KeyDown(salt){
	if (event.keyCode == 13){
		event.returnValue=false;
		event.cancel = true;
		loginOn(salt)
	}
}