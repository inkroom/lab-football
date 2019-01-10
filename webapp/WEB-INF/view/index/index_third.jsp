<%--
  Created by IntelliJ IDEA.
  User: MeiXiebing
  Date: 5/27/17
  Time: 10:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head lang="en">
        <meta charset="UTF-8">
        <title>四川省青少年校园足球信息网</title>
        <meta name="renderer" content="webkit|ie-comp|ie-stand">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <link href="${base_path}/resources/css/index/index_third.css" rel="stylesheet" type="text/css">
    </head>
    <body>
    <!--头部-->
        <div class="main" ms-controller="main">
            <div class="header">
                <div class="brown1">
                    <img src="${base_path}/resources/img/index/logo7.png">
                    <p>四川省青少年校园足球信息网</p>
                </div>
                <div id="menu" class="orange1">
                    <p>${policy.title}</p>
                </div>
                <div class="content">
                    <div class="content_main">
                        <p align="center">${policy.createDate}</p>
                        ${policy.content}
                    </div>
                </div>
            </div>
            <!--头部 end-->
            <!--尾部-->
            <jsp:include page="common/index_foot.jsp"></jsp:include>
            <!--尾部 end-->
        </div>
    </body>
    <script type="text/javascript" src="${base_path }/resources/common/lib/jquery/1.9.1/jquery.min.js"></script>
    <script type="text/javascript" src="${base_path }/resources/common/avalon/avalon.js"></script>
    <script type="text/javascript" src="${base_path}/resources/js/index/index_third.js" ></script>
</html>
