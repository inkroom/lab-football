<%--
  Created by IntelliJ IDEA.
  User: inkbox
  Date: 18-2-23
  Time: 下午10:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8" />
    <title>新闻中心</title>
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/menu-left.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/home.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/menu.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/under.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/menu-left.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/prodAndTech.css" />
    <style>

    </style>


</head>

<body>
<jsp:include page="/head.html"/>
<div class="top-img" id="top-img">
    <img src="${pageContext.request.contextPath}/resources/img/img2.jpg">
</div>

<div class="technical-middle">
    <div class="pt-menu-left">
        <!--<iframe name="mainFrame" src="left.html"></iframe>-->

        <ul id="accordion" class="accordion">
            <li>
                <div class="link" style="background: #C9151E;">
                    <a href="#" target="mainFrame">新闻中心</a>
                </div>
            </li>
            <li>
                <div class="link link-button" id="linkbutton1">
                    <a href="${pageContext.request.contextPath}/news/list.html?type=1&page=1" target="mainFrame">综合新闻</a>
                </div>
                <ul class="submenu">
                </ul>
            </li>
            <li>
                <div class="link link-button" id="linkbutton2">
                    <a href="${pageContext.request.contextPath}/news/list.html?type=2&page=1" target="mainFrame">科研动态</a>
                </div>
                <ul class="submenu">

                </ul>
            </li>
            <li>
                <div class="link link-button" id="linkbutton3">
                    <a href="${pageContext.request.contextPath}/news/list.html?type=3&page=1" target="mainFrame">人才招聘</a>
                </div>
                <ul class="submenu">

                </ul>
            </li>
        </ul>
    </div>

    <iframe id="iframeContent" class="mainFrame" name="mainFrame" scrolling="no" src="${pageContext.request.contextPath}/news/list.html?type=1&page=1" target="mainFrame" frameborder="0"  onload="this.height=mainFrame.document.body.scrollHeight"></iframe>
    <div style="clear:both;"> </div>
</div>

<iframe style="width: 100%;" name="" scrolling="no" src="${pageContext.request.contextPath}/under.html" frameborder="0" ></iframe>

</body>
<script type="text/javascript">
    <c:if test="${newsId!=null}">
    $(document).ready(function(){
        $("#linkbutton${newsType}").click();
        $("#iframeContent").attr("src","${pageContext.request.contextPath}/showNews.html?id=${newsId}");
    });
    </c:if>
</script>

</html>
