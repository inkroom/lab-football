<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>入场验证</title>
    <link rel="stylesheet" href="${path}/resources/lib/bootstrap/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="${path}/resources/css/referee/public.css">
    <link rel="stylesheet" type="text/css" href="${path}/resources/css/referee/test.css"/>
</head>


<body>
<jsp:include page="../common/head.jsp?v=2&title=入场验证"/>

<!--内容-->
<div  class="container">
    <div class="row margin-top1">
        <div class="col-xs-12">
            <span class="font-size1">请选中本场比赛中的运动员</span>
        </div>
    </div>
</div>
<%--<h3 class="text-center">请选中本场比赛中的运动员</h3>--%>
<div class="container">
    <!--主队-->
    <div class="home">
        <div class="row margin1">
            <div class="col-xs-12 left"><span class="margin-left1 color1">${Pdto1.team.name}</span></div>
        </div>

        <div id="master">
            <fmt:setBundle basename="properties.upload"/>
            <c:forEach items="${Pdto1.player}" var="P1" varStatus="status">
                <c:if test="${status.index % 3 eq 0}">
                    <div class="row">
                </c:if>
                <span class="col-xs-3 width3 ${(status.index % 3 eq 0)?'span-style':''}">
						<div class="img1 scorer img1-float" playerId="${P1.idPlayer}">
							<img src="${path}/<fmt:message key="upload.image.url.prefix"/>/${P1.headPic}"
                                 class="width1"/>
							<figcaption class="text-center">${P1.name}</figcaption>
						</div>
						<div class="img2 invisible img2-float">
							<img src="${path}/resources/img/mark.png" class="width2"/>
						</div>
					</span>

                <c:if test="${status.index % 3 eq 2||status.last}">
                    </div>
                </c:if>
            </c:forEach>
        </div>
    </div>
    <!--客队-->
    <div class="visiting">
        <div class="row margin1">
            <div class="col-xs-12 left"><span class="margin-left1 color1">${Pdto2.team.name}</span></div>
        </div>
        <div id="servlet">
            <c:forEach items="${Pdto2.player}" var="P2" varStatus="status">
                <c:if test="${status.index % 3 eq 0}">
                    <div class="row">
                </c:if>
                <span class="col-xs-3 width3 ${(status.index % 3 eq 0)?'span-style':''}">
						<div class="img1 scorer img1-float" playerId="${P2.idPlayer}">
							<img src="${path}/<fmt:message key="upload.image.url.prefix"/>/${P2.headPic}"
                                 class="width1"/>
							<figcaption class="text-center">${P2.name}</figcaption>
            </div>
            <div class="img2 invisible img2-float">
                <img src="${path}/resources/img/mark.png" class="width2"/>
            </div>
            </span>
                <c:if test="${(status.index % 3 eq 2)||status.last}">
                    </div>
                </c:if>
            </c:forEach>

        </div>
    </div>
</div>

<div class="button btn-distance margin-top1">
    <input type="button" class="btn btn-success btn-lg radius center-block cancel" onclick="submit()" value="确定"
           style="outline: none;"/>
</div>
</div>

<jsp:include page="bar.jsp"/>

<script src="${path}/resources/lib/jquery/jquery-3.1.1.min.js"></script>
<script src="${path}/resources/lib/bootstrap/bootstrap.min.js"></script>
<script src="${path}/resources/lib/layer_mobile/layer.js"></script>
<script src="${path}/resources/js/index.js"></script>
<script src="${path}/resources/js/referee/test.js"></script>

</body>
</html>
