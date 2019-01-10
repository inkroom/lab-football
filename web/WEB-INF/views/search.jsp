<%@ page import="cn.inkroom.web.money.gate.utils.ParseUtil" %>
<%@ page import="cn.inkroom.web.money.gate.config.BaseStatic" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title> - 搜索</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link href="${pageContext.request.contextPath}/resources/common/boostrap/css/bootstrap.min.css?v=3.3.6"
          rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/common/boostrap/css/font-awesome.css?v=4.4.0"
          rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/common/nav_css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/common/nav_css/style.css?v=4.1.0" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/home.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/menu.css"/>

</head>

<body class="gray-bg">
<jsp:include page="/head.html"/>
<div class="wrapper wrapper-content animated fadeInRight" style="z-index: 0;">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-content">
                    <h2>
                        为您找到相关结果约${count}个： <span class="text-navy">“${param.get('key')}”</span>
                        <button type="button" class="btn btn-info pull-right" onclick="history.back()">返回</button>
                    </h2>

                    <small>搜索用时 (${time/1000}秒)</small>

                    <div class="search-form">
                        <%--<form action="index.html" method="get">--%>
                            <div class="input-group">
                                <input type="text" placeholder="${param.get('key')}" name="search" id="key" class="form-control input-lg">
                                <div class="input-group-btn">
                                    <button class="btn btn-sm btn-primary" type="button"
                                            onclick="if ($('#key').val().length>0) window.location.href=window.location.pathname+'?key='+$('#key').val()+'&page=0'"
                                    >
                                        搜索
                                    </button>
                                </div>
                            </div>
                        <%--</form>--%>
                    </div>
                    <c:set var="count" value="${all.size()}" scope="request"/>
                    <c:forEach var="item" items="${all}">
                        <div class="hr-line-dashed"></div>
                        <div class="search-result">
                            <h3><a href="${pageContext.request.contextPath}/${item.url}">${item.title}</a></h3>
                            <a href="${pageContext.request.contextPath}/${item.url}" class="search-link">${item.url}</a>
                            <p>
                                <c:set var="replace" value="<span style='color:red;'>${param.get('key')}</span>"/>
                                    ${fn:replace(item.content, param.get('key'),  replace)}
                            </p>
                        </div>
                    </c:forEach>

                    <div class="hr-line-dashed"></div>
                    <div class="text-center">
                        <div class="btn-group">
                            <c:if test="${param.get('page')>0}">
                                <button class="btn btn-white" type="button" onclick="location.href=location.pathname+'?key=${param.get('key')}&page=${param.get('page')-1}'"><i class="fa fa-chevron-left"></i>
                                </button>
                            </c:if>
                            <%
                                int now = ParseUtil.parseInt(request.getParameter("page"));
                                int count = ParseUtil.parseInt(request.getAttribute("count").toString());
                                int allPage = count / BaseStatic.EACH_PAGE_COUNT + 1;
                                request.setAttribute("allPage",allPage);
                                for (int i = now - 5; i < now + 5 && i < allPage; i++) {
                                    if (i >= 0 && i < count) {
                            %>
                            <button class="btn btn-white <%=now==i?"active":""%>" onclick="location.href=location.pathname+'?key=${param.get('key')}&page=<%=i%>'"><%=i + 1%>
                            </button>
                            <%
                                    }
                                }
                            %>
                            <c:if test="${param.get('page')<requestScope.get('allPage')-1}">
                                <button class="btn btn-white" type="button" onclick="location.href=location.pathname+'?key=${param.get('key')}&page=${param.get('page')+1}'"><i class="fa fa-chevron-right"></i>
                            </button>
                            </c:if>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<iframe width="100%" name="" scrolling="no" src="under.html" frameborder="0" id=""></iframe>

<!-- 全局js -->
<script src="${pageContext.request.contextPath}/resources/common/jquery/jquery.min.js?v=2.1.4"></script>
<script src="${pageContext.request.contextPath}/resources/common/boostrap/js/bootstrap.min.js"></script>

</body>

</html>