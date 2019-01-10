<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>机构登录</title>
    <link rel="stylesheet" type="text/css" href="${path}/resources/lib/bootstrap/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="${path}/resources/css/player/orgindex.css"/>

</head>
<body>
<p style="display: none" id="token">${token}</p>
<div class="cs-bg">
    <div class="container">
        <div class="row">
            <div class="box_OI">
                <form class="login_bg text-center">
                    <!--注册面板标题-->
                    <div class="logo-w2 col-xs-10 col-xs-offset-1 form-group" style="margin-top: 25px">
                        <span class="title" style="color: green; font-size: 20px;">四川省青少年足球信息化系统</span>
                    </div>
                    <div class="logo-w2 col-xs-10 col-xs-offset-1 form-group" style="margin-bottom: 20px">
                        <span class="title" style="color: green; font-size: 18px;">———— 机构登录 ————</span>
                    </div>
                    <!--注册面板表单-->
                    <div class="form-horizontal" role="form">
                        <!--机构编号-->
                        <div class="col-xs-10 col-xs-offset-1 put2">
                            <input class="input" id="schoolcode" placeholder="请输入机构编号：" minlength="6" maxlength="12"
                                   type="text">
                        </div>

                        <!--输入密码-->
                        <div class="col-xs-10 col-xs-offset-1 put2">
                            <input id="password" class="input" placeholder="请输入密码：" minlength="6" maxlength="20"
                                   type="password">
                        </div>

                        <%--<!--验证码-->
                        <div>
                            <div class="col-xs-6 col-xs-offset-1 put2" style="float: left">
                                <div class="" style="width: 90%">
                                    <input id="code" class="input" placeholder="请输入验证码" maxlength="6" type="text">
                                </div>
                            </div>
                            <div style="float: left ;width: 30%;height: 34px;margin-left: 10px;" class="col-xs-4">
                                <!--验证码图片-->
                                <img src="${path}/imageCode" id="imagecode" onclick="getCode()" title="点击图片刷新验证码">
                            </div>
                        </div>--%>
                        <!-- 验证码-->
                        <div class="col-xs-10 col-xs-offset-1 put0">
                            <div class="put0" style="width: 100%;">
                                <div class="input2">
                                    <input id="code" class="input2" placeholder="请输入验证码" maxlength="6" type="text"
                                           style="width: 100%;">
                                </div>
                                <div class="" style="float: left ;width: 40%;margin-left: 4px;margin-top: 1px">
                                    <!--验证码图片-->
                                    <img src="${path}/imageCode" id="imagecode" onclick="getCode();return false;">
                                </div>
                            </div>
                        </div>

                        <!--登录按钮-->
                        <div class="form-group">
                            <div class="col-xs-8" style="width: 100%">
                                <button id="button" type="button" class="btn1 btn-success form-group"
                                        onclick="reg();return false;">
                                    登录
                                </button>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script src="${path}/resources/lib/jquery/jquery-3.1.1.min.js"></script>
<script src="${path}/resources/lib/bootstrap/bootstrap.min.js"></script>
<script src="${path}/resources/lib/jquery/jquery-1.11.3.js"></script>
<script src="${path}/resources/lib/layer/layer.js"></script>
<script src="${path}/resources/js/index.js"></script>
<script type="text/javascript">
    $(document).ready(function () {

        /*layer的样式*/
        layer.config({
            skin: 'demo-class'//自定义样式demo-class
        });
        /*点击-input框变色*/
        $("#schoolcode").focus(function () {
            $("#schoolcode").css("background-color", "#ffffff");
            $("#schoolcode").css("border", "1px solid #dddddd");
        });
        $("#schoolcode").blur(function () {
            $("#schoolcode").css("background-color", "#f4f4f4");
            $("#schoolcode").css("border", "1px solid #ffffff");
        });

        $("#password").focus(function () {
            $("#password").css("background-color", "#FFFFFF");
            $("#password").css("border", "1px solid #dddddd");
        });
        $("#password").blur(function () {
            $("#password").css("background-color", "#f4f4f4");
            $("#password").css("border", "1px solid #ffffff");
        });
        $("#code").focus(function () {
            $("#code").css("background-color", "#FFFFFF");
            $("#code").css("border", "1px solid #dddddd");
        });
        $("#code").blur(function () {
            $("#code").css("border", "1px solid #ffffff");
            $("#code").css("background-color", "#f4f4f4");
        });
        $(document).keyup(function (event) {
            if (event.keyCode == 13) {
                reg();
            }
        });
    });

    function reg() {
        if (this.checkInput()) {
            doSubmit();
        } else {
            getCode();
        }
    }

    function checkInput() {
        var username = $("#schoolcode").val();
        var password = $("#password").val();
        var imagecode = $("#code").val();
        /*正则-数字字母*/
        var reg = /^[0-9a-zA-Z]+$/;
        /*机构编号判定*/
        if (username == "") {
            layer.msg('机构编号不能为空');
            return false;
        }
        else if (!reg.test(username)) {
            layer.msg('机构编号存在非法字符');
            return false;
        }
        else if (username.length < 6) {
            layer.msg('机构编号位数不足');
            return false;
        }
        /*密码判定*/
        else if (password == "") {
            layer.msg('密码不能为空');
            return false;
        }
        else if (password.length < 6) {
            layer.msg('密码位数不足');
            return false;
        }
        else if (!reg.test(password)) {
            layer.msg('密码含有非法字符');
            return false;
        }
        /*验证码判定*/
        else if (imagecode == "") {
            layer.msg('验证码不能为空');
            return false;
        }
        else if (imagecode.length < 6) {
            layer.msg('验证码位数不足');
            return false;
        }
        return true;
    }

    function doSubmit() {

        ajax({
            type: "POST",   //提交的方法
            url: "${path}/organization/login", //提交的地址
            data: {
                schoolcode: $("#schoolcode").val(),
                password: $("#password").val(),
                imagecode: $("#code").val(),
                token: $("#token").html()
            },
            dataType: 'json',
            success: function (data) {  //成功
                //就将返回的数据显示出来
                if (data.status != 0) {
                    getCode();
                }
                switch (data.status) {
                    case 0:
                        layer.msg("登录成功，即将跳转进入下一页面");
                        setTimeout(function () {
                            window.location.href = "${path}/match/turnMatchList";
                        }, 1000);
                        break;
                    case 1:
                        layer.msg("机构编号或密码输入错误");
                        break;
                    case 11:
                        layer.msg("验证码已失效，请重新刷新页面");
                        break;
                    case 12:
                        layer.msg("验证码输入错误");
                        break;
                }
            },
            error: function () {  //失败的话
                msg("请求错误");
            }

        });
    }

    function getCode() {
        $("#code").val("");
        $("#imagecode").attr('src', "${path}/imageCode?abc=" + Math.random())//MAth.random是为了避免缓冲

    }
</script>
</body>
</html>
