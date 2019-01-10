<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>技术</title>

    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/technology.css" />
</head>

<body>
<div class="prod-top">
    <div class="prod-top-title">
        <a>技术</a>
    </div>
    <div class="prod-top-right">
        <a>技术</a>
        <a>&gt;</a>
        <a>技术产品</a>
        <a>&gt;</a>
        <a>首页</a>
        <a>&gt;</a>
        <a>当前位置：</a>
    </div>
    <div style="clear:both;"> </div>
</div>

<div class="tech-middle">
    <table class="table">
        <tbody>
        <c:forEach items="${techs}" var="techs">
         <tr>
            <td>
                <a>》</a>
                <a href="${pageContext.request.contextPath}/showProAndTech.html?type=2&id=${techs.te_id}">${techs.te_name}</a>
            </td>
        </tr>
               </c:forEach>
        </tbody>
    </table>

    <ul class="pagination">
        <c:choose>
            <c:when test="${maxpage eq 0}">

            </c:when>
            <c:otherwise>
                <li>
                    <a href="${pageContext.request.contextPath}/ProdAndTech/technicals.html?te_set=${te_set}&page=${page}&flag=-1">&laquo;</a>
                </li>

                <c:forEach var="i" begin="1" end="${maxpage}" step="1">

                    <c:choose>
                        <c:when test="${page==i}">
                            <li class="active">
                                <a href="${pageContext.request.contextPath}/ProdAndTech/technicals.html?te_set=${te_set}&page=${i}&flag=0">${i}</a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li>
                                <a href="${pageContext.request.contextPath}/ProdAndTech/technicals.html?te_set=${te_set}&page=${i}&flag=0">${i}</a>
                            </li>
                        </c:otherwise>

                    </c:choose>
                </c:forEach>


                <li>
                    <a href="${pageContext.request.contextPath}/ProdAndTech/technicals.html?te_set=${te_set}&page=${page}&flag=1">&raquo;</a>
                </li>
            </c:otherwise>

        </c:choose>

    </ul>

</div>

</body>
<script type="text/javascript">
    
</script>
</html>
