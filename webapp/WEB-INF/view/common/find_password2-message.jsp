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
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="${base_path }/resources/common/lib/html5shiv.js"></script>
<script type="text/javascript" src="${base_path }/resources/common/lib/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="${base_path }/resources/common/static/h-ui/css/H-ui.reset.css" />
<link rel="stylesheet" type="text/css" href="${base_path }/resources/common/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="${base_path }/resources/common/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="${base_path }/resources/common/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="${base_path }/resources/common/static/h-ui.admin/skin/green/skin.css" />
<link rel="stylesheet" href="${base_path }/resources/css/common/findPassword.css">
<!--[if lt IE 9]>
<script src="http://ie7-js.googlecode.com/svn/version/2.1(beta4)/IE9.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="${base_path }/resources/common/static/h-ui.admin/css/style.css" />
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
            <div class="pro1-main">
                <div class="pro1-content  message-check">
                    <div class="main-message">
                        <p class="message-title">请用手机 ${sessionScope.A_PHONE} 获取短信验证码</p>
                        <div class="pro-input">
                            <div>
                                <div class="inputbox">
                                    <input type="text" id="phonecode"  placeholder="输入验证码">
                                </div>
                                <div class="tips-error code-error" style="display: none;">
                                    <div>
                                        <span>验证码错误</span>
                                    </div>
                                </div>
                                <div class="code-input">
                                    <input type="button" class="code-btn"  onclick="MatchCodeDialog()" style="margin-top:10px" value="获取验证码"/>
                                </div>
                            </div>
                        </div>
                        <div class="pro-input">
                            <a class="pro-btn" onclick="ckYan()" style="margin-top:10px"><span>确定</span></a>
                        </div>
                        <p class="message_tips">短信用不了？<a href="${base_path}/forgetPassword/secondPassword.html">更换其他验证方式</a></p>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </div>
</body>

<input type="text" id="typePhone" hidden="" value="${sessionScope.A_TYPE }">
<!--_footer 作为公共模版分离出去-->
    <script type="text/javascript" src="${base_path }/resources/common/lib/jquery/1.9.1/jquery.min.js"></script>
    <script type="text/javascript" src="${base_path }/resources/common/lib/layer/2.4/layer.js"></script>
    <script type="text/javascript" src="${base_path }/resources/common/static/h-ui/js/H-ui.min.js"></script>
    <script type="text/javascript" src="${base_path }/resources/common/static/h-ui.admin/js/H-ui.admin.js"></script>
    <script type="text/javascript" src="${base_path }/resources/common/lib/jquery.contextmenu/jquery.contextmenu.r2.js"></script>
    <!--/_footer 作为公共模版分离出去-->

<script>
    $(function(){
        $.Huitab("#tab_demo .tabBar span","#tab_demo .tabCon","current","click","1")});
    
    function phone_code(){
    	var type = $('#typePhone').val();
    	var phone = ${sessionScope.phone };
    			$.ajax({
    				type : "POST",
    				url :"${base_path}/sms/getRegisterCode.action",
    				data : {
    					"phone" : phone,
    					"type": type,
    					"smsphone":1
    				},
    				dataType : 'json',
    				success : function(json) {
    					  if (json.success){
    							time(".code-btn");
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
    
    function ckYan(){
    	$.ajax({
    		type : "POST",
    		url : "${base_path}/forgetPassword/forgetPhoneCode.action",
    		data : {
    			"randomCode":$('#phonecode').val(),
    			"type":$('#typePhone').val()
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
	
    
	//获取数学表达式验证码
	function getMathCode(){
		var url = "${base_path}/verification/get_num_code.action?num="+Math.random();
		$("#codeimgMath").prop("src",url);
	}

	//数学表达式验证码弹窗
		//说明：请更改login2.css文件中样式
	function MatchCodeDialog(){
		var html = "<div class='web_diago' style='margin-top:10px'>" +
						"<div >" +
						"<input type='text' id='answer' name='answer' style='width:50%;margin-left:3%;height:40px;' class='input-text radius' placeholder='请输入计算结果'/>" +
							"<img id='codeimgMath' style='margin-rigt:90px;float: right' name='codeimgMath' src='${base_path}/verification/get_num_code.action'  onclick='getMathCode()'/>" +
						"</div>" +
						"<div >" +
								"<button class='btn btn-primary'  style='margin-left:250px;margin-top:10px;margin-bottom:10px' onclick='verifyCodeAnswer()'>确定</button>" +
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
			        url: "${base_path}/verification/verify_code_answer.action",
			        type: "POST",
			        dataType: "json",
			        data: {"code" : answer,"type":n},
			        success: function (data) {
			        	if(data.status==200){
			        		layer.closeAll();
			        		phone_code();
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
</script>
</html>