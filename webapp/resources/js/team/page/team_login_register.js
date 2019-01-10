var base_path=$('#base_path').val();
//获取验证码
function getCode(){
	var url = base_path+"/verification/get_code.action?num="+Math.random();
	$("#codeimg").prop("src",url);
}
//获取数学表达式验证码
function getMathCode(){
	var url = base_path+"/verification/get_num_code.action?num="+Math.random();
	$("#codeimgMath").prop("src",url);
}

//数学表达式验证码弹窗
	//说明：请更改login2.css文件中样式
function MatchCodeDialog(){
	var html = "<div class='web_diago' style='margin-top:10px'>" +
					"<div >" +
						"<input type='text' id='answer' name='answer' class='inputstyled' placeholder='请输入计算结果'/>" +
						"<img id='codeimgMath' style='margin-rigt:90px;float: right' name='codeimgMath' src='"+base_path+"/verification/get_num_code.action'  onclick='getMathCode()'/>" +
					"</div>" +
					"<div >" +
							"<button class='button' style='margin-left:250px;margin-top:10px;margin-bottom:10px' onclick='verifyCodeAnswer()'>确定</button>" +
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

//登录
function loginFun(salt){
	//验证用户是否填写用户名
	if($('#u').val()==null || $('#u').val().length==0){
        layer.alert('请填写用户名', {
			skin: 'layer-bule-style'
		    ,closeBtn: 0
	  	});
        return false;
    }
	
	//密码
	if($('#p').val()==null || $('#p').val().length==0){
        layer.alert('请填写密码', {
			skin: 'layer-bule-style'
		    ,closeBtn: 0
	  	});
        return false;
    }
	//验证码
	if($('#code').val()==null || $('#code').val().length==0){
        layer.alert('请填写验证码', {
			skin: 'layer-bule-style'
		    ,closeBtn: 0
	  	});
        return false;
    }
	var value = $('#u').val();
	$('#u').val(value.trim());
	value = $('#p').val();
	$('#p').val(value.trim());
	value = $('#code').val();
	$('#code').val(value.trim());
	
    encryptPassword($("#p"), salt);
    return true;
}


//注册
var InterValObj; //timer变量，控制时间
var count = 120; //间隔函数，120秒执行
var curCount;//当前剩余秒数

//发送验证码
//发送手机验证码前验证
$('#sendMessage').click(function(){
	
	var phone = $('#phone').val();
	var regex = /^[1]{1}[2-578]{1}[0-9]{9}$/;
	if(phone!=null && phone.length>0){
		if(phone.length!=11 || regex.test(phone)==false){
			layer.tips('请填写正确的11位手机号','#phone',{ tips: [1, '#FF0033']});
			return false;
		}
	}else{
		layer.tips('请填写手机号','#phone',{ tips: [1, '#FF0033']});
		return false;
	}
	//弹出验证对话弹框
	MatchCodeDialog();
});

//弹出验证对话弹框验证码验证
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
		        		sendMessage(data.url);
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

//向手机发送验证码
function sendMessage(url){
	var phone = $('#phone').val();
	
	curCount = count;
	//设置button效果，开始计时
     $("#sendMessage").attr("disabled", "true");
    //向后台发送处理数据
	$.ajax({
		contentType: "application/x-www-form-urlencoded;charset=utf-8",
        url: base_path+url,
        type: "POST",
        dataType: "json",
        data: {"phone" : phone, "type":4},
        success: function (data) {
        	if(data.errorCode==1){
        		layer.msg(data.msg,{icon: 1});
        		$("#sendMessage").val("重新获取("+curCount+")");
        		InterValObj = window.setInterval(SetRemainTime, 1000); //启动计时器，1秒执行一次
        		
        	}else{
        		layer.msg(data.msg,{icon: 0});
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
     }
     else {
         curCount--;
         $("#sendMessage").val("重新获取("+curCount+")");
     }
}

$('#regUser').validationEngine({
    scroll: false,
    maxErrorsPerField: 1,
    showOneMessage: true,
    onValidationComplete: function (form, valid) {
    	if(valid== false){
    		return false;
    	}
    	
    	if($("#passwd").val() != $("#passwd2").val()){
    		layer.msg("两次输入密码不一致!",{icon: 1});
    		return false;
    	}
    	
    	var tmpValue = $("#passwd").val().trim();
    	$("#passwd").val(tmpValue);
    	tmpValue = $("#passwd2").val().trim();
    	$("#passwd2").val(tmpValue);
    	
    	encryptPassword($("#passwd"), '');
    	encryptPassword($("#passwd2"), '');
    	
    	var jsonStr = {"user":$('#user').val(), 
    			"passwd":$('#passwd').val(), 
    			"passwd2": $('#passwd2').val(), 
    			"Phone":$('#phone').val().trim(), 
    			"phoneCheck": $('#phoneCheck').val().trim()
    	};
    	$('#passwd').val("");
    	$('#passwd2').val("");
    	$.ajax({
    		contentType: "application/x-www-form-urlencoded;charset=utf-8",
            url: base_path+"/team/team_register.action",
            type: "POST",
            dataType: "json",
            data: jsonStr,
            async: true,
            success: function (data) {
                if(data.status==200){
                	
                	layer.alert('注册成功', {icon: 1});
                	setTimeout(function(){
                		window.location.replace(base_path+"/team/login_view.html");
                		},1500);
                	
                }else{
                	
                	layer.alert(data.message, {icon: 0});
                	return false;
                }
            },
    		error : function(req, status, reason) {
    			layer.alert('系统异常,请刷新重试', {
    				skin: 'layer-bule-style'
    			    ,closeBtn: 0
    		  	});
    			window.location.reload();
    		}
        });
    }
});