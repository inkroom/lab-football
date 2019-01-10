<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title> 登陆页面 </title>

    <link href="${pageContext.request.contextPath}/resources/common/boostrap/css/bootstrap.min.css?v=3.3.6"
          rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/common/boostrap/css/font-awesome.css?v=4.4.0"
          rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/common/nav_css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/common/nav_css/style.css?v=4.1.0" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/backstyle/css/login.css"/>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.validate.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/common/Login.js"></script>
    <script src="${pageContext.request.contextPath}/resources/common/plugins/layer/layer.min.js"></script>

</head>

<body class="gray-bg">

<div class="backg">
    <div class="board">
        <div class="box">
        </div>
        <h1>管理系统登陆</h1>
        <div class="login">
            <form class="m-t" method="post" id="loginFrom">
                <div class="form-group">
                    <input type="text" name="username" id="username" class="form-control" placeholder="用户名">
                </div>
                <div class="form-group">
                    <input type="password" name="password" id="password" class="form-control" placeholder="密码">
                </div>

                <div class="form-group">
                    <input name="code" class="yanzhengma" style="float: left;width:130px;" id="code" type="text"
                           placeholder="验证码">
                </div>
                <div class="yanzhengma" style="float: right;">
                    <img id="Imagecode" src="${pageContext.request.contextPath}/getCode/getImageCode.action" title="点击图片更新验证码"
                         onclick="getCode()">
                </div>
                <div style="padding-top:40px ;">
                    <button id="buttonlogin" type="submit" class="btn btn-primary block full-width m-b">登录</button>
                </div>
            </form>

        </div>
    </div>
</div>


<script >
    function checklogin() {
        $.ajax(
            {
                contentType: "application/x-www-form-urlencoded;charset=utf-8",
                async: false,
                type: 'post',
                url: 'login.action',
                data: {
                    'username': $("#username").val(),
                    'password': $("#password").val(),
                    'code': $("#code").val()
                },
                success: function (data) {
                    switch(data.status)
                    {
                        case 1:
                            layer.msg("账号或者密码错误");
//                            $("#Imagecode").trigger('click');
                            getCode();
                            break;
                        case 11:
                            layer.msg("验证码过期");
                            getCode();
                            break;

                        case 12:
                            layer.msg("验证码错误");
                            getCode();
                            break;

                        case 200:
                            layer.msg("登陆成功：正在跳转");
                            //跳转页面
                            location.href = 'backstage/index.html';
                            break;

                        default:
                            layer.msg("系统错误请联系管理员或重试");
                            getCode();
                            break
                    }
                },
                error: function () {
                    layer.msg("连接失败系统异常");
                    $("#Imagecode").trigger("click");
                    getCode();
                }
            })
    }

    //表单提交时调用方法
        $.validator.setDefaults({
            submitHandler: function() {
                checklogin();
            }
        });
        //表单验证
        $().ready(function() {
            $("#loginFrom").validate({
                    rules: {

                        username: {
                            required: true
                        },
                        password: {
                            required: true,
                            minlength:6
                        },
                        code: {
                            required: true,
                            rangelength: [4, 4]
                        }

                    },
                    messages: {
                        username: {
                            required: '管理员账号'
                        },
                        password: {
                            required: '请输入密码',
                            minlength: '密码长度不能小于6位'
                        },
                        code: {
                            required:'验证码不能为空',
                            rangelength: '验证码格式有误'
                        }
                    },
                //layer重写showErrors，提示信息的位置
                    showErrors: function (errorMap, errorList) {
                        $.each(errorList, function (i, v) {
                            //用了layer让显示效果更美观
                            layer.tips(v.message, v.element, { tips:[1,"#cc2a32"],time: 2000 });
                            return false;
                        });
                    },
                /* 失去焦点时不验证 */
                onfocusout: false
                }
            )
        });

    //点击切换验证码
    function getCode()
    {
        console.log("点击了验证码");
        $("#Imagecode").attr('src',"${pageContext.request.contextPath}/getCode/getImageCode.action?abc="+Math.random());//MAth.random是为了避免缓冲
    }

</script>

</body>

</html>