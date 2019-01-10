<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>权限错误</title>
    <link rel="stylesheet" type="text/css" href="${base_path}/resources/css/other/error.css" />


</head>
<body style="text-align:center">
<div style="margin:0 auto;width:auto;height:auto;margin-top: 40px">
    <div id="errorfrm">
        <div id="error">
            <div class="privIcon"></div>
            <div class="errorText">
                <h1>权限错误，请重新登录</h1>
                <h1>将在 <span id="mes">5</span> 秒钟后返回<a href="javascript:reLogin()">登录页面</a>！</h1>
            </div>
        </div>

    </div>
</body>
<script type="text/javascript">

    var i = 5;
    var intervalid;
    intervalid = setInterval("fun()", 1000);
    function fun() {
        if (i == 0) {
            reLogin();
            clearInterval(intervalid);
        }
        document.getElementById("mes").innerHTML = i;
        i--;
    }


    function reLogin() {
        window.parent.location.href = '${base_path}/${path}/login_view.html';
    }

</script>
</html>