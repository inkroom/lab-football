<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>实验室管理系统.登录</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link rel="shortcut icon" href="favicon.ico">
    <link href="${base_path}/resources/css/student/cgbackground.css" rel="stylesheet">
    <link href="${base_path}/resources/common/boostrap/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${base_path}/resources/common/boostrap/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="${base_path}/resources/css/teacher/iCheck/custom.css" rel="stylesheet">
    <link href="${base_path}/resources/common/nav_css/animate.css" rel="stylesheet">
    <link href="${base_path}/resources/common/nav_css/style.css?v=4.1.0" rel="stylesheet">
    <link href="${base_path}/resources/css/student/student_amend.css" rel="stylesheet">
    <script>if(window.top !== window.self){ window.top.location = window.location;}</script>
    <!-- 全局js -->
    <script src="${base_path}/resources/common/jquery/jquery.min.js?v=2.1.4"></script>
    <script src="${base_path}/resources/common/jquery/jquery.validate.min.js"></script>
    <script src="${base_path}/resources/js/index.js"></script>
    <script src="${base_path}/resources/common/jquery/messages_zh.min.js"></script>
    <script src="${base_path}/resources/common/layer/2.4/layer.js"></script>
    <script src="${base_path}/resources/common/boostrap/js/bootstrap.min.js"></script>
    <!-- iCheck -->
    <script src="${base_path}/resources/js/teacher/icheck.min.js"></script>
    <script src="${base_path}/resources/js/student/studentLogin.js"></script>
    <!--，密码md5加密-->
    <script type="text/ecmascript" src="${base_path}/resources/js/student/md5.js"></script>
    <script>
        $.validator.setDefaults({
            submitHandler: function() {
                checklogin();
                getCode();
            }
        });
        $().ready(function() {
// 在键盘按下并释放及提交后验证提交表单
            $("#loginFrom").validate({

                    rules: {

                        username: {
                            required: true,
                            rangelength: [11, 11],
                            digits: true//必须为整数

                        },
                        password: {
                            required: true,
                            minlength: 6
                        },
                        code: {
                            required: true,
                            rangelength: [4, 4]
                        }

                    },
                    messages: {
                        username: {
                            required: '请输入手机号或学号',
                            rangelength: '必须为11位纯数字',
                            digits: '必须为11位纯数字',
                        },
                        password: {
                            required: '请输入密码',
                            minlength: '密码长度不能小于6位'
                        },
                        code: {
                            required:'验证码不能为空',
                            rangelength: '验证码格式有误',
                        }
                    },
                //重写showErrors
                showErrors: function (errorMap, errorList) {
                    var msg = "";
                    $.each(errorList, function (i, v) {
                        //msg += (v.message + "\r\n");
                        //在此处用了layer的方法,显示效果更美观
                        layer.tips(v.message, v.element, { tips:[1,"#cc2a32"],time: 2000 });
//                        getCode();//重设验证码
                        return false;
                    });
                },
                /* 失去焦点时不验证 */
                onfocusout: false
                } 
            )

        })
        function getCode()
        {
            $("#code").val("");
            $("#Imagecode").attr('src',"${base_path}/getCode/getImageCode?abc="+Math.random())//MAth.random是为了避免缓冲

        }


    </script>
</head>
<body class="bkTop">
<div class="top" style="color: #ffffff">
    实验室管理系统·登录首页
</div>
<%--<div class="middle-box text-center loginscreen   animated fadeInDown">--%>
    <%--<div class="register">--%>

        <%--<form class="m-t" role="form" method="post" id="loginFrom">--%>
            <%--<div class="form-group">--%>
                <%--<input type="text" name="username" id="username" class="form-control" placeholder="用户名" >--%>
            <%--</div>--%>
            <%--<div class="form-group">--%>
                <%--<input type="password" name="password" id="password" class="form-control" placeholder="密码" >--%>
            <%--</div>--%>

            <%--<div class="form-group">--%>
                <%--<input  name="code"   class="yanzhengma" style="float: left;width:130px;" id="code" type="text"  placeholder="验证码" >--%>
            <%--</div>--%>
            <%--<div class="yanzhengma">--%>
                <%--<img id="Imagecode"  src="${base_path}/getCode/getImageCode.action" title="点击图片更新验证码" onclick="getCode()"  >--%>
            <%--</div>--%>

            <%--<button   id="buttonlogin" type="submit" class="btn btn-primary block full-width m-b">登录</button>--%>
        <%--</form>--%>
    <%--</div>--%>
<%--</div>--%>

<div id="modal-form" class="register" aria-hidden="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-6 b-r">
                        <form role="form" method="post" id="loginFrom">
                            <div class="form-group">
                                <label>学号/手机号：</label>
                                <input type="tel" name="username" id="username" placeholder="请输入学号" class="form-control">
                            </div>
                            <div class="form-group">
                                <label>密码：</label>
                                <input type="password" name="password"  id="password"placeholder="请输入密码" class="form-control">
                            </div>
                            <div class="form-group">
                                <label>验证码：</label>
                                <input  name="code" id="code" type="text" class="form-control" placeholder="验证码" >
                            </div>
                            <div class="form-group">
                                <label>点击图片更换：</label>
                                <img id="Imagecode"  style="float: right" src="${base_path}/getCode/getImageCode" title="点击图片更新验证码" onclick="getCode()"  >
                            </div>
                            <div class="form-group">
                                <button   id="buttonlogin" type="submit" class="btn btn-primary block full-width m-b">登录</button>
                            </div>
                        </form>
                    </div>
                    <div class="col-sm-6">
                        <h4>注意事项</h4>
                        <p style="padding-top: 20px ;padding-left: 20px ;color: #cc5965">
学生请输入学号，老师请输入手机号，忘记密码可以找上一级重置，这里是注意事项-----巴拉巴拉

                        </p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>