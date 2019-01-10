var base_path=$('#base_path').val();
//获取旧邮箱是否正确
	function old_email_code(){
		var em = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
		var old_email = $('#old_email').val();
		if(!em.exec(old_email)){
			layer.alert("邮箱格式错误");
			return;
		}
		$.ajax({
            url:base_path+ "/email/getCode.action",
            type: "POST",
            data:{
                "email" : old_email
            },
            dataType : "JSON",
            success : function (json) {
                if (json.success){
                	timeemail(".sendemailcode");
	    			var waitemail=0;
					 layer.alert(json.msg);
                }else {
                	layer.alert(json.msg);
                }
            },
            error : function () {
                alert("服务器错误！")
            }
        })
	  }
	
	//修改邮箱下一步验证
	$(document).on("click",'#next-step',function(){
		$(this).off("click");
		var em = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
		var old_email = $('#old_email').val();
		if(!em.exec(old_email)){
			layer.alert("邮箱格式错误");
			return;
		}
		 $.ajax({
				type: "POST",
				url: base_path + "/updateEmail/YzEmailCode.action",
				data: {oldEmailCode:$('#oldEmailCode').val()}, 
				dataType: 'json',
				success:function(result) {
					  if ((result.error)==1){
						  layer.alert("验证成功！");
						  nextstep();
		              }else {
		            	  layer.alert("验证码错误！");
		              }
					
				  },
		    	error:function(error) {
		    		console.log(error);
		  			}
				});
		
	  });
	
	
	//修改邮箱确定修改
	function change_email(){
		var em = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
		var new_email = $('#new_email').val();
		if(!em.exec(new_email)){
			layer.alert("邮箱格式错误");
			return;
		}
		 $.ajax({
				type: "POST",
				url: base_path+  "/updateEmail/lastEmailCheck.action",
				data: {new_email:$('#new_email').val(),newEmailCode:$('#newEmailCode').val()}, 
				dataType: 'json',
				success:function(result) {
					if((result.error) == "验证成功"){
						layer.alert("修改成功！");
						$('#modal-demo2').modal("hide");
						window.parent.location.reload(); 
		    		}else{
		    			layer.alert(result.error);
		    		}
				  },
		    	error:function(error) {
		    		console.log(error);
		  			}
				});
	  }
	
	//获取新邮箱验证码
	function new_email_code(){
		var em = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
		var new_email = $('#new_email').val();
		var email = $('#SMSEmail').val();
		if(!em.exec(new_email)){
			layer.alert("邮箱格式错误");
			return;
		}
		if(new_email==email){
			layer.alert("该邮箱与原邮箱相同");
			return;
		}
		$.ajax({
				type: "POST",
				url: base_path+ "/email/getCode.action",
				data: {email:$('#new_email').val()}, 
				dataType: 'json',
				success:function(json) {
					 if (json.success){
						   timeemail(".sendemailcode1");
							var waitemail=0;  
				    		layer.alert(json.msg);
				    		
		                }else {
		                	layer.alert(json.msg);
		                }
				  },
		    	error:function(error) {
		    		console.log(error);
		  			}
				});
	}
	
	
	//手机计时器
	var wait=120;  
	function time(e) {  
        if (wait == 0) {  
            $(e).removeAttr("disabled");            
            $(e).val("获取验证码");  
            wait = 120;  
        } else {  
            $(e).attr("disabled", "true");  
            $(e).val("重新发送(" + wait + ")");  
            wait--;  
            setTimeout(function() {  
                time(e) ;
            },  
            1000)  
        }  
    }
	
	//邮件计时器
	var waitemail=60;  
	function timeemail(e) {  
        if (waitemail == 0) {  
            $(e).removeAttr("disabled");            
            $(e).val("获取验证码");  
            waitemail = 60;  
        } else {  
            $(e).attr("disabled", "true");  
            $(e).val("重新发送(" + waitemail + ")");  
            waitemail--;  
            setTimeout(function() {  
            	timeemail(e) ;
            },  
            1000)  
        }  
    }
	
