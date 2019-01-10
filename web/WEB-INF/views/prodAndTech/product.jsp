
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>产品</title>

    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/product.css" />

</head>

<body>
<div class="prod-top">
    <div class="prod-top-title">
        <a>产品</a>
    </div>
    <div class="prod-top-right">
        <a>产品</a>
        <a>&gt;</a>
        <a>技术产品</a>
        <a>&gt;</a>
        <a>首页</a>
        <a>&gt;</a>
        <a>当前位置：</a>
    </div>
    <div style="clear:both;"> </div>
</div>

<div class="prod-middle">
    <div class="row">
        <c:forEach items="${prolist}" var="prolist">
            <div class="col-sm-6 col-md-3">
                <div class="thumbnail">
                   <a href="${pageContext.request.contextPath}/showProAndTech.html?type=1&id=${prolist.pr_id}">
                   <img src="/image/${prolist.pr_photo}" />
                   </a>
                    <div class="caption">
                       <a href="${pageContext.request.contextPath}/showProAndTech.html?type=1&id=${prolist.pr_id}" >
                        <p>${prolist.pr_name}</p>
                        </a>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>

    <ul class="pagination">
        <c:choose>
            <c:when test="${maxpage eq 0}">
            </c:when>
            <c:otherwise>
                <li>
                    <a href="${pageContext.request.contextPath}/ProdAndTech/products.html?pr_set=${pr_set}&pr_page=${cupage}&flag=-1">&laquo;</a>
                </li>

                <c:forEach var="i" begin="1" end="${maxpage}" step="1">
                    <c:choose>
                        <c:when test="${cupage==i}">
                            <li class="active">
                                <a href="${pageContext.request.contextPath}/ProdAndTech/products.html?pr_set=${pr_set}&pr_page=${i}&flag=0">${i}</a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li>
                                <a href="${pageContext.request.contextPath}/ProdAndTech/products.html?pr_set=${pr_set}&pr_page=${i}&flag=0">${i}</a>
                            </li>
                        </c:otherwise>

                    </c:choose>


                </c:forEach>

                <li>
                    <a href="${pageContext.request.contextPath}/ProdAndTech/products.html?pr_set=${pr_set}&pr_page=${cupage}&flag=1">&raquo;</a>
                </li>
            </c:otherwise>

        </c:choose>

    </ul>

</div>

</body>


</html>
