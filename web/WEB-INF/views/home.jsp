<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: inkbox
  Date: 18-2-23
  Time: 下午10:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8" />
    <title>首页</title>
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/home.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/menu.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/under.css" />
    <style type="text/css">

    </style>
</head>

<body>
<jsp:include page="/head.html"/>
<div id="myCarousel" class="carousel slide" style="margin-top: 6px;">

    <!-- 轮播（Carousel）指标 -->
    <ol class="carousel-indicators">
        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
        <li data-target="#myCarousel" data-slide-to="1"></li>
        <li data-target="#myCarousel" data-slide-to="2"></li>
    </ol>
    <!-- 轮播（Carousel）项目 -->
    <div class="carousel-inner">
        <div class="item active">
            <img src="${pageContext.request.contextPath}/resources/img/img12.jpg" alt="First slide">
        </div>
        <div class="item">
            <img src="${pageContext.request.contextPath}/resources/img/img12.jpg" alt="Second slide">
        </div>
        <div class="item">
            <img src="${pageContext.request.contextPath}/resources/img/img12.jpg" alt="Third slide">
        </div>
    </div>

    <!-- 轮播（Carousel）导航 -->
    <a class="carousel-control left" href="#myCarousel" data-slide="prev">
        <img src="${pageContext.request.contextPath}/resources/img/turnLf.png" style="width: 30%; padding-top:80%">
    </a>

    <a class="carousel-control right" href="#myCarousel" data-slide="next">
        <img src="${pageContext.request.contextPath}/resources/img/turnRt.png" style="width: 30%; padding-top:80%">
    </a>
</div>

<div class="middle">
    <div class="middle-bg">
        <div class="middle-info">
            <div class="middle-info-top">
                <div class="middle-info-top1">
                    <img src="${pageContext.request.contextPath}/resources/img/icoXW.png">
                    <a style="">综合新闻</a>
                </div>
                <a href="${pageContext.request.contextPath}/news/news.html">更多</a>
            </div>
            <div class="middle-img">
                <img src="${pageContext.request.contextPath}/resources/img/imgXW.jpg">
            </div>

            <div class="middle-txt">
                <ul>
                    <c:set var="count" value="0" scope="request"/>
                    <c:forEach var="item" items="${news}">
                        <c:if test="${(item.type eq 1)&&count<10}">
                            <c:set var="count" value="${count+1}" scope="request"/>
                            <li>
                                <a href="${pageContext.request.contextPath}/news/news/signel.html?type=${item.type}&id=${item.id}">》${item.title}</a>
                            </li>
                        </c:if>
                    </c:forEach>
                </ul>
            </div>

        </div>
    </div>
    <div class="middle-bg">
        <div class="middle-info">
            <div class="middle-info-top">
                <div class="middle-info-top1">
                    <img src="${pageContext.request.contextPath}/resources/img/icoKY.png">
                    <a>科研动态</a>
                </div>
                <a href="${pageContext.request.contextPath}/news/news.html">更多</a>
            </div>
            <div class="middle-img">
                <img src="${pageContext.request.contextPath}/resources/img/imgKY.jpg">
            </div>

            <div class="middle-txt">
                <ul>
                    <c:set var="count" value="0" scope="request"/>
                    <c:forEach var="item" items="${news}">
                        <c:if test="${(item.type eq 2)&&count<10}">
                            <c:set var="count" value="${count+1}" scope="request"/>
                            <li>
                                <a href="${pageContext.request.contextPath}/news/news/signel.html?type=${item.type}&id=${item.id}">》${item.title}</a>
                            </li>
                        </c:if>
                    </c:forEach>
                </ul>
            </div>

        </div>

    </div>
    <div class="middle-bg">
        <div class="middle-info">
            <div class="middle-info-top">
                <div class="middle-info-top1">
                    <img src="${pageContext.request.contextPath}/resources/img/icoZP.png">
                    <a>人才招聘</a>
                </div>
                <a href="${pageContext.request.contextPath}/news/news.html">更多</a>
            </div>
            <div class="middle-img">
                <img src="${pageContext.request.contextPath}/resources/img/imgZP.jpg">
            </div>

            <div class="middle-txt" style="color:#C9C9C9;">
                <ul>
                    <c:set var="count" value="0" scope="request"/>
                    <c:forEach var="item" items="${news}">
                        <c:if test="${(item.type eq 3)&&count<10}">
                            <c:set var="count" value="${count+1}" scope="request"/>
                            <li>
                                <a href="${pageContext.request.contextPath}/news/news/signel.html?type=${item.type}&id=${item.id}">》${item.title}</a>
                            </li>
                        </c:if>
                    </c:forEach>
                </ul>
            </div>
            <div style="clear:both;"> </div>
        </div>
        <div style="clear:both;"> </div>
    </div>
    <div style="clear:both;"> </div>
</div>

<iframe width="100%" name="" scrolling="no" src="under.html" frameborder="0" id="" ></iframe>

<script>
    //轮播间隔
    $('.carousel').carousel({
        interval: 6000
    });
</script>


</body>

</html>
