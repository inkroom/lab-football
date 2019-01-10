<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>球员登录</title>
<script type="text/javascript" src="${base_path}/resources/js/player/jquery-1.9.0.min.js"></script>
<script type="text/javascript" src="${base_path}/resources/js/player/login.js"></script>
<script type="text/javascript" src="${base_path}/resources/js/player/page/login.js"></script>
<script type="text/javascript" src="${base_path}/resources/common/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="${base_path}/resources/common/lib/md5.js"></script>
<script type="text/javascript" src="${base_path}/resources/common/static/h-ui/js/H-ui.js"></script>
<link href="${base_path}/resources/css/player/login2.css" rel="stylesheet" type="text/css" />
</head>
<body>
<h1>四川省足球信息化系统球员登录<sup></sup></h1>
<div class="login" style="margin-top:50px;">
    <div class="header">
        <div class="switch" id="switch"><a class="switch_btn_focus" id="switch_qlogin" href="javascript:void(0);" tabindex="7">快速登录</a>
			<a class="switch_btn" id="switch_login" href="javascript:void(0);" tabindex="8">快速注册</a><div class="switch_bottom" id="switch_bottom" style="position: absolute; width: 64px; left: 0px;"></div>
        </div>
    </div>
    <div class="web_qr_login" id="web_qr_login" style="display: block; height: 330px;">
            <!--登录-->
            <div class="web_login" id="web_login">
               <div class="login-box">
			<div class="login_form">
				<form  name="loginform" accept-charset="utf-8" id="login_form" class="loginForm" action="${base_path}/player/login_judge.action" method="post"><input type="hidden" name="did" value="0"/>
               <input type="hidden" name="to" value="log"/>

                    <div class="uinArea" id="uinArea">
                <label class="input-tips" for="u">帐号：</label>
                <div class="inputOuter" id="uArea">
                    <input type="text" id="u" placeholder="请输入手机号" name="username" class="inputstyle"/>
                </div>
                </div>


                <div class="pwdArea" id="pwdArea">
               <label class="input-tips" for="p">密码：</label>
               <div class="inputOuter" id="pArea">
                    <input type="password" id="p" name="p" class="inputstyle"/>
                </div>


                </div>
                    <div class="pwdArea" id="wdArea">
                        <label class="input-tips" for="password">验证码：</label>
                        <div class="inputOuter" id="dArea">
                            <input type="text" id="password" name="password" class="inputstyle" style="width: 60px;float: left" onkeydown="KeyDown('${_SALT_IN_SESSION_}')"/>
                            <img id="code" src="${base_path}/verification/get_code.action" onclick="getCode()" style="margin-top: 5px;float: right" >
                    </div>
                    </div>
                    <div class="pwdArea">
                        <label class="input-tips"></label>
                        <div class="inputOuter">
                            <a href="${base_path }/forgetPassword/firstPassword/1.html" style="cursor: pointer;">忘记密码？点击找回</a>
                        </div>
                    </div>
                    <div class="pwdArea"><input type="button" onclick="loginOn('${_SALT_IN_SESSION_}')" value="登 录" style="width:125px;margin-left:10px;" class="button_blue"/>
                        <input type="button" onclick="goBack()" value="返 回" style="width: 125px;margin-left: 10px;"  class="button_blue"/>
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
     <form name="registeForm" id="regUser" method="post" accept-charset="utf-8">
	      <input type="hidden" name="to" value="reg"/>
		  <input type="hidden" name="did" value="0"/>
        <ul class="reg_form" id="reg-ul">
                <li  style="margin-top:20px">
                    <label for="user"  class="input-tips2">身份证号：</label>
                    <div class="inputOuter2">
                        <input type="text" id="user" name="user" maxlength="18" class="inputstyle2"/>
                    </div>
                </li>
                <li>
                <label for="passwd" class="input-tips2">密码：</label>
                    <div class="inputOuter2">
                        <input type="password" id="passwd"  name="passwd" maxlength="16" class="inputstyle2" onkeyup="change()"/>
                    </div>
                </li>
                <li>
                <label for="passwd2" class="input-tips2">确认密码：</label>
                    <div class="inputOuter2">
                        <input type="password" id="passwd2" name="passwd2" maxlength="16" class="inputstyle2"  onkeyup="change()" onblur="changes()"/>
                    </div>
                </li>
                <li>
                 <label for="phone" class="input-tips2">手机号：</label>
                    <div class="inputOuter2">
                        <input type="text" id="phone" name="phone" maxlength="11" class="inputstyle2"/>
                    </div>
                </li>
            	<li>
                <label for="phoneCheck" class="input-tips2">验证码：</label>
                <div class="inputOuter2">
                    <input type="text" id="phoneCheck" name="phoneCheck" maxlength="10" class="inputstyle2" style="width: 80px;float: left;margin-right: 10px"/>
<!--                      <a href="" style="display: inline-block;float: right;">  -->
                    <input  type="button" id="sendMessage" class="button" onclick="sendCode('${base_path}')" value="发送验证码" >
                    <!-- </a> -->
                </div>
           		</li>
           		<li>
                    <div class="inputArea">
                        <input type="button" id="reg" onclick="registe('${base_path}')" style="margin-top:10px;margin-left:85px;width: 135px" class="button_blue" value="注册"/>
                    </div>
                </li>
            </ul>
    </form>
    </div>
   </div>
    <!--注册end-->
</div>
<script type="text/javascript">
    function changes(){

        var pass=$("#passwd").val();
        var pass2=$("#passwd2").val();
        if(pass!=pass2){
            layer.tips('两次密码不一致','#passwd2',{ tips: [1, '#FF0033']});
        }

    }



    function change(){
    var pass=$("#passwd").val();
    var  password2=$("#passwd2");
    var pass2=$("#passwd2").val();
    if(pass!=pass2){
        password2.css("border","1px solid red");
    }
    if(pass==pass2){
        password2.css("border","1px solid #D7D7D7");
    }
}


function getCode() {
	document.getElementById("code").src = "${base_path}/verification/get_code.action?num="+Math.random();
}
$("#login").click(function(){
	encryptPassword($("#p"),'${_SALT_IN_SESSION_}');
});
$(function(){
	layer.ready(function(){
		if('${info}' != '' || '${info}' == null){
			layer.msg('${info}',{icon: 0});
		}
    });
});

function goBack() {
    window.location.href = "${base_path}/index.html";
}

</script>
</body>
</html>