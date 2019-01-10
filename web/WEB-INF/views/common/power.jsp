<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title> - 权限错误</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link href="${pageContext.request.contextPath}/resources/common/boostrap/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/common/boostrap/css/font-awesome.css?v=4.4.0" rel="stylesheet">

    <link href="${pageContext.request.contextPath}/resources/common/nav_css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/common/nav_css/style.css?v=4.1.0" rel="stylesheet">

</head>

<body class="gray-bg">


    <div class="middle-box text-center animated fadeInDown">
        <h1 style="font-size: 100px">NO POWER</h1>
        <h3 class="font-bold">权限不足</h3>

        <div class="error-desc">
            您没有权限访问
            <br/>您可以<a href="javascript:;" class="btn btn-link">登录</a>
            <br/>
        </div>
    </div>

    <!-- 全局js -->
    <script src="${pageContext.request.contextPath}/resources/common/jquery/jquery.min.js?v=2.1.4"></script>
    <script src="${pageContext.request.contextPath}/resources/common/boostrap/js/bootstrap.min.js"></script>
    <%--<SCRIPT language=javascript>--%>
        <%--function go()--%>
        <%--{--%>
            <%--window.history.go(-1);--%>
            <%--location.reload();--%>
        <%--}--%>
        <%--setTimeout("go()",3000);--%>
    <%--</SCRIPT>--%>

<script type="text/javascript">
    $(function () {
        $('a').on('click',function () {
            if (window.parent!==null){
                window.parent.location.href = "${pageContext.request.contextPath}/login.html";
            }else{
                location.href  = "${pageContext.request.contextPath}/login.html";
            }
        })
    })
</script>



</body>

</html>
