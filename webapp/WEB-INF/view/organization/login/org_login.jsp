<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE>
<html><head>
    <title>机构登陆</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script type="text/javascript" src="${base_path }/resources/js/organization/jquery-1.9.0.min.js"></script>
    <script type="text/javascript" src="${base_path }/resources/common/lib/layer/2.4/layer.js"></script>
    <script type="text/javascript" src="${base_path }/resources/common/lib/md5.js"></script>

    <script type="text/javascript" src="${base_path }/resources/common/lib/login/images/login.js"></script>
    <link href="${base_path }/resources/common/lib/login/css/login2.css" rel="stylesheet" type="text/css" />

</head>
<body>
<h1>四川省足球信息化系统机构登录</h1>

<div class="login">
    <div class="header">
        <div class="switch" id="switch"><a class="switch_btn_focus" id="switch_qlogin" href="javascript:void(0);" tabindex="7" style="margin-left: 80px">快速登录</a>
        </div>
    </div>
    <div class="web_qr_login" id="web_qr_login" style="display: block; height: 330px;">
        <!--登录-->
        <div class="web_login" id="web_login">
            <div class="login-box">
                <div class="login_form">
                    <form  name="loginform" accept-charset="utf-8" id="login_form" class="loginForm" method="post" action="${base_path }/org/login.html"><input type="hidden" name="did" value="0"/>
                        <input type="hidden" name="to" value="log"/>

                        <div class="uinArea" id="uinArea">
                            <label class="input-tips" for="u">帐号：</label>
                            <div class="inputOuter" id="uArea">
                                <input type="text" id="u" name="username" class="inputstyle"/>
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
                                <input type="text" id="password" name="code" class="inputstyle" style="width: 60px;float: left"/>
                                <img  id="yanz" src="${base_path }/verification/get_code.action" onclick="get_code()" style="margin-top: 5px;float: right" >
                            </div>

                        </div>

                        <div class="pwdArea">
                            <label class="input-tips"></label>
                            <div class="inputOuter">
                                <a href="${base_path }/forgetPassword/firstPassword/3.html" style="cursor: pointer;">忘记密码？点击找回</a>
                            </div>
                        </div>


                        <div class="pwdArea"><input type="submit" value="登 录" id="sub"  style="width:125px;" class="button_blue"/>
                            <input type="button" onclick="goBack()" value="返 回" style="width: 125px;margin-left: 10px;"  class="button_blue"/>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!--登录end-->
    </div>
</div>


<script type="text/javascript">


    $(function(){

        layer.ready(function(){
            if('${error}' != '' || '${error}' == null){
                layer.msg('${error}');
            }
        });

    });



    function get_code(){
        document.getElementById("yanz").src ="${base_path }/verification/get_code.action?num="+Math.random();
    }



    $("#sub").click(function () {

        if($("#username").val()==""){
            layer.msg("用户名不能为空");
            return;
        }


        if($("#p").val()==""){
            layer.msg("密码不能为空");
            return ;
        }

        if($("#code").val()==""){
            layer.msg("验证码不能为空");
            return ;
        }


        encryptPassword($('#p'),'${_SALT_IN_SESSION_}');
    });

    function goBack() {
        window.location.href = "${base_path}/index.html";
    }

</script>
</body></html>
