var base_path=$('#base_path').val();
//控制发送验证码按钮变化以及发送验证   
var InterValObj; //timer变量，控制时间
var count = 120; //间隔函数，1秒执行
var curCount;//当前剩余秒数

//发送验证码
$('#sendEmail1').click(function(){
	var email = $('#email1').val();
	var regex = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
	if(email!=null && email.length>0){
		
		if(email.length>100 || regex.test(email)==false){
			layer.tips('请填写正确的邮箱','#email1',{ tips: [1, '#FF0033']});
			return false;
		}
	}else{
		layer.tips('请填写邮箱','#email1',{ tips: [1, '#FF0033']});
		return false;
	} 
	curCount = count;
	//设置button效果，开始计时
     $("#sendEmail1").attr("disabled", "true");
    
    //向后台发送处理数据
	$.ajax({
		contentType: "application/x-www-form-urlencoded;charset=utf-8",
        url: base_path+"/email/getCode.action",
        type: "POST",
        dataType: "json",
        data: {"email" : email},
        success: function (data) {
        	if(data.success){
        		$("#sendEmail1").val(curCount + "秒后重新获取");
        		InterValObj = window.setInterval(SetRemainTime, 1000); //启动计时器，1秒执行一次
        		layer.msg(data.msg,{icon: 1});
        	}else{
        		$("#sendEmail1").removeAttr("disabled");
        	    $("#sendEmail1").val("重新获取");
        	    layer.msg(data.msg,{icon: 0});
        	}
        }
    });
});
//timer处理函数
function SetRemainTime() {
     if (curCount == 0) {                
         window.clearInterval(InterValObj);//停止计时器
         $("#sendEmail1").removeAttr("disabled");//启用按钮
         $("#sendEmail1").val("重新发送验证码");
     }
     else {
         curCount--;
         $("#sendEmail1").val(curCount + "秒后重新获取");
     }
}


  function coach_edit(title,url,id,w,h){
        layer_show(title,url,w,h);
   }

//初登录绑定邮箱
$('#editInfoBtn').click(function(){
	$("#modal-demo_team_info").modal("show");
});
//提交初此绑定邮箱
$('#checkPtNum1').click(function(){
	var code = $('#code1').val();
	var email = $('#email1').val();
	var regex1 = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
	var regex2 = /^[a-zA-Z0-9]+$/;
	
	if(email == null || email.length==0){
		layer.tips('请填写邮箱','#email1',{ tips: [1, '#FF0033']});
		return false;
	}
	if(email.length>100 || regex1.test(email)==false){
		layer.tips('请填写正确的邮箱','#email1',{ tips: [1, '#FF0033']});
		return false;
	}
	
	if(code == null || code.length==0){
		layer.tips('请填写验证码','#code1',{ tips: [1, '#FF0033']});
		return false;
	}
	if(code.length>10 || regex2.test(code)==false){
		layer.tips('验证码错误','#code1',{ tips: [1, '#FF0033']});
		return false;
	}
	$.ajax({
		contentType: "application/x-www-form-urlencoded;charset=utf-8",
        url: base_path+"/team/team_improve_info_email.action",
        type: "POST",
        dataType: "json",
        data: {"email":email, "code":code},
        async: true,
        success: function (data) {
            if(data.status==200){
            	layer.msg(data.message,{icon: 1});
            	$('#modal-demo_team_info').modal("hide");
            	$('#modal-demo_info').modal({
        	        show:true,
        	        backdrop:true
        	        });	
            	//coach_edit('球队编辑信息','team_edit.html','1','800','500');
            }else{
            	layer.msg(data.message,{icon: 0});
            	if(data.status==500){
        	    	setTimeout(function(){
                		window.location.replace(base_path+"/team/login_view.html");
                		},2000);
        	    }
            	return false;
            }
        },
		error : function(req, status, reason) {
			layer.alert('系统异常,请刷新重试', {
				skin: 'layer-bule-style'
			    ,closeBtn: 0
		  	});
		}
    });
	
});