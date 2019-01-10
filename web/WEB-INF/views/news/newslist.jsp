<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>新闻中心</title>

    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/technology.css" />
</head>


<body>
<div class="prod-top">
    <div class="prod-top-title">
        <a><c:if test="${type eq 1}">综合新闻</c:if>
            <c:if test="${type eq 2}">科研动态</c:if>
            <c:if test="${type eq 3}">人才招聘</c:if>
        </a>
    </div>
    <div class="prod-top-right">
        <a><c:if test="${type eq 1}">综合新闻</c:if>
            <c:if test="${type  eq 2}">科研动态</c:if>
            <c:if test="${type  eq 3}">人才招聘</c:if>
        </a>
        <a>&gt;</a>
        <a href="javascript:void(0)" onclick="changeParentUrl('${pageContext.request.contextPath}/news/news.html')">新闻中心</a>
        <a>&gt;</a>
        <a href="javascript:void(0)" onclick="changeParentUrl('${pageContext.request.contextPath}/home.html')">首页</a>
        <a>&gt;</a>
        <a>当前位置：</a>
    </div>
    <div style="clear:both;"> </div>
</div>

<div class="tech-middle">
    <table class="table">
        <tbody>

                <c:forEach items="${newsListItem}" var="item">
                <tr>
                    <td>
                    <a>》</a>
                    <a href="${pageContext.request.contextPath}/showNews.html?id=${item.id}">${item.title}</a>
                    <%--//<span><fmt:formatDate value="${item.date}" pattern="yyyy-MM-dd"/></span>--%>
                    </td>
                </tr>
                </c:forEach>
        </tbody>
    </table>

    <ul class="pagination">
        <li>
            <a <c:if test="${page>1&&count>1}">href="${pageContext.request.contextPath}/news/list.html?type=1&page=${page - 1}"</c:if>>&laquo;</a>
        </li>
        <c:forEach var="i" begin="1" end="${count}">
            <li <c:if test="${i eq page}">class="active" </c:if> >
                <a href="${pageContext.request.contextPath}/news/list.html?type=1&page=${i}">${i}</a>
            </li>
        </c:forEach>
        <li>
            <a <c:if test="${page<count}">href="${pageContext.request.contextPath}/news/list.html?type=1&page=${page + 1}"</c:if>>&raquo;</a>
        </li>
    </ul>

</div>


</body>
<script type="text/javascript">
   function changeParentUrl(url)
   {
       window.parent.location.href=url;
   }
</script>
</html>
