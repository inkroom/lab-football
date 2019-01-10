<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE>
<html>
    <head>
        <title></title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <link href="${base_path }/resources/common/lib/login/css/login2.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <h1>四川省足球信息化系统后台登录</h1>

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
                            <form  name="loginform" accept-charset="utf-8" id="login_form" class="loginForm" ><input type="hidden" name="did" value="0"/>
                                <input type="hidden" name="to" value="log"/>
                                <input type="hidden" name="salt" value="${_SALT_IN_SESSION_}" id="salt">

                                <div class="uinArea" id="uinArea">
                                    <label class="input-tips" for="username">帐号：</label>
                                    <div class="inputOuter" id="uArea">
                                        <input type="text" id="username" name="username" class="inputstyle" />
                                    </div>
                                </div>




                                <div class="pwdArea" id="pwdArea">
                                    <label class="input-tips" for="password">密码：</label>
                                    <div class="inputOuter" id="pArea">
                                        <input type="password" id="password" name="password" class="inputstyle"  />
                                    </div>


                                </div>

                                <div class="pwdArea" id="wdArea">
                                    <label class="input-tips" for="password">验证码：</label>
                                    <div class="inputOuter" id="dArea">
                                        <input type="text" id="code" name="code" class="inputstyle" style="width: 60px;float: left"  />
                                        <img  id="r_code" src="${base_path }/verification/get_code.action" onclick="javascript:this.src='${base_path }/verification/get_code.action?num='+Math.random()" style="margin-top: 5px;float: right"   >
                                    </div>

                                </div>

                                <div class="pwdArea"><input type="botton" value="登 录" id="butt"  style="width:135px;margin-left:70px;margin-top: 20px" class="button_blue"/>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <!--登录end-->
            </div>
        </div>

    <script type="text/javascript" src="${base_path }/resources/common/lib/jquery/1.9.1/jquery.js"></script>
    <script type="text/javascript" src="${base_path }/resources/common/lib/layer/2.4/layer.js"></script>
    <script type="text/javascript" src="${base_path }/resources/common/lib/md5.js"></script>
    <script type="text/javascript" src="${base_path }/resources/js/admin/admin_login.js"></script>
    </body>
</html>
