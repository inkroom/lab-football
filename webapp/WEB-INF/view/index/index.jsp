<%--
  Created by IntelliJ IDEA.
  User: MeiXiebing
  Date: 5/27/17
  Time: 10:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
    <head lang="en">
        <meta charset="UTF-8">
        <meta name="renderer" content="webkit"/>
        <title>四川省青少年校园足球信息网</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
        <link href="${base_path}/resources/css/index/index_style.css" rel="stylesheet" type="text/css">
        <link rel="stylesheet" href="${base_path}/resources/common/layui/css/layui.css"  media="all">
        <script type="text/javascript" src="${base_path }/resources/common/lib/jquery/1.9.1/jquery.min.js"></script>
        <script type="text/javascript" src="${base_path }/resources/common/avalon/avalon.js"></script>
        <script type="text/javascript" src="${base_path}/resources/common/myfocus/myfocus-2.0.4.min.js"></script>
        <script type="text/javascript" src="${base_path}/resources/js/index/index.js" ></script>
    </head>
    <body>
        <!--头部-->
        <div class="main" ms-controller="main" id="main">
            <jsp:include page="common/index_head.jsp"></jsp:include>
            <!--头部 end-->
            <!--中部-->
            <div class="warpper">
                <div class="orange2">
                    <p>全省注册数和举办赛事数: 截止今日，全省注册球队{{@teamCount}}支、球员{{@playerCount}}名、教练员{{@coachCount}}名、举办赛事{{@comCount}}场</p>
                </div>
                <!--菜单-->
                <div id="menu" class="brown2">
                    <p>政策发布</p>
                </div>
                <!--菜单 end-->
                <jsp:include page="common/index_middle.jsp"></jsp:include>
                <!--尾部-->
                <jsp:include page="common/index_foot.jsp"></jsp:include>
                <!--尾部 end-->
            </div>
            <!--中部 end-->
        </div>
        <script type="text/javascript" src="${base_path}/resources/common/layui/layui.js"></script>

    </body>
</html>