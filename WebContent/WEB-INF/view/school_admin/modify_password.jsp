<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit"/> 
	<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1" >
	<link rel="shortcut icon" href="${basePath }/resources_new/favi/favicon.ico" type="image/x-icon" />
    <link href="${basePath }/resources/lib/css/bootstrap.min.css" rel="stylesheet">
    <link href="${basePath }/resources/lib/js/layer/2.1/skin/layer.css">
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<form method="post">
	<input type="hidden" name="token" id="token" value="${token }">
	<div>
	 <table class="table table-bordered" style="margin-bottom:10px;">
	 <tbody>
	     <tr class="text-center">
	         <td>原密码</td>
	         <td><input type="password" name="OPassword" id="password0" class="input-text form-control  password0"/></td>
	     </tr>
	     <tr class="text-center">
	         <td>新密码</td>
	         <td><input type="password" name="MPassword" id="password1" class="input-text form-control  password"/></td>
	     </tr>
	     <tr class="text-center">
	         <td>确认密码</td>
	         <td><input type="password" name="MPassword1" id="password2" class="input-text form-control  password"/></td>
	     </tr>
	 </tbody>
	 </table>
	</div>
	<div class=" col-sm-12 text-center">
		<button id="button_s" type="button" class="btn btn-success radius"><strong>提交</strong></button>
		<button id="button" type="reset" class="btn btn-success radius"><strong>重置</strong></button>
	</div>
</form>
<script src="${basePath }/resources/lib/js/jquery-2.1.4.js" type="text/javascript"></script>
<script src="${basePath }/resources/lib/js/bootstrap.js" type="text/javascript"></script>
<script src="${basePath }/resources/lib/js/layer/2.1/layer.js"></script>
<script src="${basePath }/resources/lib/js/md5.js"></script>
<script>
    $("#button_s").click(function(){
        for(var i =0;i<$("input").length;i++){
            if($("input").eq(i).val()==""){
                $("input").eq(i).focus();
                var msg = "请填写完整信息";
                layer.alert(msg, {
     			   skin: 'layui-layer-lan'
     			   ,closeBtn: 0
     			});
                return false;
            }
        }
        var pw0=document.getElementById('password0').value;
        var Regx = /^[A-Za-z0-9]*$/;
        if(!Regx.test(pw0)) {
        	var msg = "原密码格式输入错误，原密码只能是数字和字母组成";
        	layer.alert(msg, {
 			   skin: 'layui-layer-lan'
 			   ,closeBtn: 0
 			});
            return false;
        }
        
        var pw1=document.getElementById('password1').value;
        var Regx = /^[A-Za-z0-9]*$/;
        if(!Regx.test(pw1)) {
        	var msg = "原密码格式输入错误，原密码只能是数字和字母组成";
        	layer.alert(msg, {
 			   skin: 'layer-bule-style'
 			   ,closeBtn: 0
 			});
            return false;
        }
        
        var pw2=document.getElementById('password2').value;
        var Regx = /^[A-Za-z0-9]*$/;
        if(!Regx.test(pw2)) {
        	var msg = "原密码格式输入错误，原密码只能是数字和字母组成";
        	layer.alert(msg, {
 			   skin: 'layui-layer-lan'
 			   ,closeBtn: 0
 			});
            return false;
        }
        
        if($(".password0").val().length>18){
        	var msg = "原密码过长，密码长度为6到18位";
        	layer.alert(msg, {
 			   skin: 'layui-layer-lan'
 			   ,closeBtn: 0
 			});
        	return false;
        }
        else if($(".password0").val().length<6){
        	var msg = "原密码过短，密码长度为6到18位";
        	layer.alert(msg, {
 			   skin: 'layui-layer-lan'
 			   ,closeBtn: 0
 			});
        	return false;
        }
        if($(".password").eq(0).val().length>18){
        	var msg = "新密码过长，密码长度为6到18位";
        	layer.alert(msg, {
 			   skin: 'layui-layer-lan'
 			   ,closeBtn: 0
 			});
        	return false;
        }
        else if($(".password").eq(0).val().length<6){
        	var msg = "新密码过短，密码长度为6到18位";
        	layer.alert(msg, {
 			   skin: 'layui-layer-lan'
 			   ,closeBtn: 0
 			});
        	return false;
        }
        if($(".password").eq(1).val().length>18){
        	var msg = "新密码过长，密码长度为6到18位";
        	layer.alert(msg, {
 			   skin: 'layui-layer-lan'
 			   ,closeBtn: 0
 			});
        	return false;
        }
        else if($(".password").eq(1).val().length<6){
        	var msg = "新密码过短，密码长度为6到18位";
        	layer.alert(msg, {
 			   skin: 'layui-layer-lan'
 			   ,closeBtn: 0
 			});
        	return false;
        }
        if($(".password0").val()==$(".password").eq(0).val()){
        	var msg = "新密码和原密码相同，请重新输入！";
        	layer.alert(msg, {
 			   skin: 'layui-layer-lan'
 			   ,closeBtn: 0
 			});
        	return false;
        }
        if($(".password").eq(0).val()!=$(".password").eq(1).val()){
        	var msg = "新密码和确认密码输入不一致";
        	layer.alert(msg, {
 			   skin: 'layui-layer-lan'
 			   ,closeBtn: 0
 			});
        	return false;
        }
        encryptPassword($("#password0"));
        encryptPassword($("#password1"));
	    encryptPassword($("#password2"));
	    
	    $.ajax({
            url:"${basePath}/school_admin/modify_password.action",    //请求的url地址
            dataType:"json",   //返回格式为json
            async:true,//请求是否异步，默认为异步，这也是ajax重要特性
            data:{"oldPassword":$("#password0").val(),"newPassword":$("#password1").val(),"confirmPassword":$("#password2").val(),"token":$("#token").val()},    //参数值
            type:"post",   //请求方式
            beforeSend:function(){
                //请求前的处理
                console.log("form submit");
            },
            success:function(data){
                //请求成功时处理
                if(data.status == 200){
                	alert(data.msg);
                	if(data.success){
                		window.parent.location.href = 'login_view.action';
                	}
                }
            },
            complete:function(){
                //请求完成的处理
            	location.reload();
            },
            error:function(){
                //请求出错处理
            	console.log("form submit");
            }
        });
    });
</script>
</body>
</html>