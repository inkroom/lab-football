<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>


    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title> 修改密码</title>
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
    <!-- 全局js -->

</head>
<body class="gray-bg">
<div class="top">
    修改密码
</div>
<div class="middle-box text-center loginscreen   animated fadeInDown">
    <div class="register">
        <form class="m-t" role="form" id="modifyPasswordFrom">
            <div class="form-group">
                <input type="password" name="password" id="password" class="form-control" placeholder="原密码" required="">
            </div>
            <div class="form-group">
                <input type="password" name="newPassword" id="newPassword"class="form-control" placeholder="现密码" required="">
            </div>
            <div class="form-group">
                <input type="password" name="newPassword2" id="newPassword2" class="form-control" placeholder="确认密码" required="">
            </div>
            <button type="submit" class="btn btn-primary block full-width m-b" >确认修改</button>
        </form>
    </div>
</div>
<script src="${base_path}/resources/common/jquery/jquery.min.js?v=2.1.4"></script>
<script src="${base_path}/resources/common/jquery/jquery.validate.min.js"></script>
<script src="${base_path}/resources/common/jquery/messages_zh.min.js"></script>
<script src="${base_path}/resources/common/layer/2.4/layer.js"></script>
<script src="${base_path}/resources/common/boostrap/js/bootstrap.min.js"></script>
<script src="${base_path}/resources/js/student/studentLogin.js"></script>
<script src="${base_path}/resources/js/index.js"></script>
<!--，密码md5加密-->
<script type="text/ecmascript" src="${base_path}/resources/js/student/md5.js"></script>
<script>
    $.validator.setDefaults({
        submitHandler: function() {
            ModifyPasswd('${base_path}');
        }
    });
    $().ready(function() {
// 在键盘按下并释放及提交后验证提交表单
        $("#modifyPasswordFrom").validate({
                rules: {
                    password: {
                        required: true,
                        rangelength: [6, 18],
                    },
                    newPassword: {
                        required: true,
                        rangelength: [6, 18],
                    },
                    newPassword2: {
                        required: true,
                        rangelength: [6, 18],
                        equalTo:"#newPassword"
                    }

                },
                messages: {
                    password: {
                        required: '**********请输入原密码**********',
                        minlength: '**********密码长度6位到18位**********'
                    },
                    newPassword: {
                        required: '**********请输入现密码**********',
                        minlength: '**********密码长度6位到18位**********',
                    },
                    newPassword2: {
                        required: '**********请确认密码**********',
                        minlength: '**********密码长度6位到18位**********',
                        equalTo:"**********两次密码输入不一致**************"

                    }

                },
                showErrors: function (errorMap, errorList) {
                    var msg = "";
                    $.each(errorList, function (i, v) {
                        //msg += (v.message + "\r\n");
                        //在此处用了layer的方法,显示效果更美观
                        layer.tips(v.message, v.element, { tips:[1,"#cc2a32"],time: 2000 });
                        return false;
                    });
                }
            }
        )

    })
    function getCode()
    {
        $("#Imagecode").attr('src',"${base_path}/getCode/getImageCode?abc="+Math.random())//MAth.random是为了避免缓冲
    }
</script>
</body>

</html>
