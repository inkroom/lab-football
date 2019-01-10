<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title> 修改密码</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link rel="shortcut icon" href="favicon.ico">
    <link href="${pageContext.request.contextPath}/resources/common/boostrap/css/bootstrap.min.css?v=3.3.6"
          rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/common/boostrap/css/font-awesome.css?v=4.4.0"
          rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/common/nav_css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/common/nav_css/style.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.validate.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/common/Login.js"></script>
    <script src="${pageContext.request.contextPath}/resources/common/plugins/layer/layer.min.js"></script>

</head>

<body class="gray-bg">

<div class="middle-box text-center loginscreen   animated fadeInDown">
    <div class="text-center">
        修改密码
    </div>
    <div class="register">
        <form class="m-t" role="form" id="modifyPasswordFrom">
            <div class="form-group">
                <input type="password" name="password" id="password" class="form-control" placeholder="原密码" required="">
            </div>
            <div class="form-group">
                <input type="password" name="newPassword" id="newPassword" class="form-control" placeholder="新密码"
                       required="">
            </div>
            <div class="form-group">
                <input type="password" name="newPassword2" id="newPassword2" class="form-control" placeholder="确认密码"
                       required="">
            </div>
            <button type="submit" class="btn btn-primary block full-width m-b">确认修改</button>
        </form>

    </div>
</div>

<script>
    $.validator.setDefaults({
        submitHandler: function() {
            ModifyPasswd();
        }
    });
    $().ready(function() {
// 在键盘按下并释放及提交后验证提交表单
        $("#modifyPasswordFrom").validate({
                rules: {
                    password: {
                        required: true
                    },
                    newPassword: {
                        required: true
                    },
                    newPassword2: {
                        required: true,
                        equalTo:"#newPassword"
                    }

                },
                messages: {
                    password: {
                        required: '请输入原密码'
                    },
                    newPassword: {
                        required: '请输入现密码'
                    },
                    newPassword2: {
                        required: '请确认密码',
                        equalTo:"两次密码输入不一致"
                    }

                },
                showErrors: function (errorMap, errorList) {
                    $.each(errorList, function (i, v) {
                        //msg += (v.message + "\r\n");
                        //在此处用了layer的方法,显示效果更美观
                        layer.tips(v.message, v.element, { tips:[1,"#cc2a32"],time: 2000 });
                        return false;
                    });
                }
            }
        )
    });
</script>

</body>

</html>