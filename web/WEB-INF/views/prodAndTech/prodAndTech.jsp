
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8" />
    <title>技术产品</title>
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/home.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/menu.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/under.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/menu-left.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/prodAndTech.css" />
    <link rel="prerender" href="${pageContext.request.contextPath}/3dModel.html">
    <style>

    </style>
</head>

<body>

<jsp:include page="/head.html"/>

<div class="technical-middle">
    <div class="pt-menu-left">
        <!--<iframe name="mainFrame" src="left.html"></iframe>-->

        <ul id="accordion" class="accordion">
            <li>
                <div class="link" style="background: #C9151E;">
                    <span>技术产品</span>
                </div>
            </li>
            <li>
                <div class="link link-button" id="linkbutton2">
                    <a href="${pageContext.request.contextPath}/ProdAndTech/technicals.html?te_set=${te_set}&page=1&flag=0" target="mainFrame">技术</a>
                </div>
                <ul class="submenu">
                    <c:forEach items="${settech}" var="settech">
                        <li >
                            <a id="liButton2Id${settech.ts_id}" class="a" href="${pageContext.request.contextPath}/ProdAndTech/technicals.html?te_set=${settech.ts_id}&page=1&flag=0" target="mainFrame">${settech.ts_name}</a>
                        </li>
                    </c:forEach>

                </ul>
            </li>
            <li>
                <div class="link link-button"  id="linkbutton1">
                    <a href="${pageContext.request.contextPath}/ProdAndTech/products.html?pr_set=${pr_set}&pr_page=1&flag=0" target="mainFrame">产品</a>
                </div>
                <ul class="submenu">
                   <c:forEach items="${setproduct}" var="setproduct">
                       <li >
                           <a id="liButton1Id${setproduct.ps_id}" class="a" href="${pageContext.request.contextPath}/ProdAndTech/products.html?pr_set=${setproduct.ps_id}&pr_page=1&flag=0" target="mainFrame">${setproduct.ps_name}</a>
                       </li>
                    </c:forEach>
                </ul>
            </li>
            <li>
                <div class="link link-button">
                    <a href="${pageContext.request.contextPath}/3dModel.html" target="mainFrame">倾斜模型</a>
                </div>
                <ul class="submenu">
                </ul>
            </li>
        </ul>
    </div>

    <c:choose>
        <c:when test="${type eq 1}">
           <iframe id="iframeContent" class="mainFrame" name="mainFrame" scrolling="no" src="${pageContext.request.contextPath}/ProdAndTech/technicals.html?te_set=${set}&page=1&flag=0" frameborder="0" onload="this.height=mainFrame.document.body.scrollHeight"></iframe>
        </c:when>
        <c:otherwise>
            <iframe id="iframeContent" class="mainFrame" name="mainFrame" scrolling="no" src="${pageContext.request.contextPath}/ProdAndTech/products.html?pr_set=${set}&pr_page=1&flag=0" frameborder="0" onload="this.height=mainFrame.document.body.scrollHeight"></iframe>
        </c:otherwise>
    </c:choose>



    <div style="clear:both;"> </div>
</div>


<iframe style="width: 100%;" name="" scrolling="no" src="${pageContext.request.contextPath}/under.html" frameborder="0" id=""></iframe>

<script src="${pageContext.request.contextPath}/resources/js/menu-left.js"></script>
</body>
<script type="text/javascript">
    $(document).ready(function(){
         <c:if test="${type eq 1 }">

    $("#linkbutton2").click();
    $("#liButton2Id${set}").click();
        </c:if>
        <c:if test="${type eq 2 }">

        $("#linkbutton1").click();
        $("#liButton1Id${set}").click();
        </c:if>
    <c:if test="${id2!=null}">
   $("#linkbutton${type2}").click();
   $("#liButton${type2}Id${setType}").click();
   $("#iframeContent").attr('src',"${pageContext.request.contextPath}/showProAndTech.html?type=${type2}&id=${id2}");
    </c:if>

    });
</script>
</html>
