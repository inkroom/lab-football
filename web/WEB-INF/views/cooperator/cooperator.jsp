<%--
  Created by IntelliJ IDEA.
  User: inkbox
  Date: 18-3-20
  Time: 下午7:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8"/>
    <title>${cooperator.name}</title>
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/home.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/menu.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/under.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/menu-left.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/prodAndTech.css"/>

    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/lib/layer/layer.js" type="text/javascript"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/prodAndTech.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/menu-left.js"></script>

</head>

<body>
<jsp:include page="/head.html"/>
<div class="top-img" id="top-img">
    <img src="${pageContext.request.contextPath}/resources/img/img2.jpg">
</div>

<div class="technical-middle">
    <%--<div class="pt-menu-left">--%>

        <%--<ul id="accordion" class="accordion">--%>
            <%--<c:forEach var="item" items="${item}" varStatus="status">--%>
                <%--<c:if test="${status.first}">--%>
                    <%--<li>--%>
                        <%--<div class="link" style="background: #C9151E;cursor: default">--%>
                            <%--<span>${item}</span>--%>
                        <%--</div>--%>
                    <%--</li>--%>
                <%--</c:if>--%>
                <%--<c:if test="${!status.first}">--%>
                    <%--<li>--%>
                        <%--<div class="link link-button">--%>
                            <%--<a href="javascript:void(0);" onclick="change(${unions[status.index-1].id},this,'${pageContext.request.contextPath}')">${item}</a>--%>
                        <%--</div>--%>
                        <%--<ul class="submenu">--%>
                        <%--</ul>--%>
                    <%--</li>--%>
                <%--</c:if>--%>
            <%--</c:forEach>--%>
        <%--</ul>--%>
    <%--</div>--%>
    <div class="" id="external-html" style="width: 75%;margin: auto">
        <h1 style="text-align: center">${cooperator.name}</h1>
        ${cooperator.content}
        <p style="text-align: right">最后编辑于 <fmt:formatDate value="${cooperator.date}" pattern="yyyy-MM-dd"/></p>
        <%--内容加载中...--%>
    </div>

    <%--<iframe class="mainFrame" name="mainFrame" scrolling="no" src="${pageContext.request.contextPath}/prodAndTech/technology.html" frameborder="0" id="external-frame" onload="this.height=mainFrame.document.body.scrollHeight"></iframe>--%>
    <div style="clear:both;"></div>
</div>

<iframe style="width: 100%;" name="" scrolling="no" src="../under.html" frameborder="0" id=""></iframe>

<%--<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/html.js">--%>

<%--</script>--%>
</body>

</html>

