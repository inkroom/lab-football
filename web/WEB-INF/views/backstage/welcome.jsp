<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>欢迎页面</title>
    <link href=".${pageContext.request.contextPath}/resources/common/boostrap/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/common/boostrap/css/font-awesome.css?v=4.4.0" rel="stylesheet">

    <link href="${pageContext.request.contextPath}/resources/common/nav_css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/common/nav_css/style.css?v=4.1.0" rel="stylesheet">
</head>
<body>
<div class="wrapper wrapper-content animated fadeIn">
    <div class="row">
        <div class="col-lg-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>欢迎使用</h5>

                </div>

                <div class="ibox-content">
                    <fmt:setLocale value="zh_CN"/>
                    <fmt:bundle basename="properties.version">
                    <h3 class="text-center">欢迎使用<fmt:message key="version.name"/></h3>
                    <p class="text-center">     软件名称：<fmt:message key="version.name"/></p>
                    <p class="text-center">     软件版本：<fmt:message key="version.version"/></p>
                    <p class="text-center">     更新时间：<fmt:message key="version.date"/></p>
                    <p class="text-center">     开发人员联系方式：<fmt:message key="version.address"/></p>
                    </fmt:bundle>
                </div>

            </div>
        </div>
        <div class="col-lg-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>使用说明</h5>

                </div>
                <div class="ibox-content">
                    <p class="text-center">点击左侧标签，在相应的页面进行编辑</p>
                    <a href="${pageContext.request.contextPath}/resources/readMe.docx" class="btn btn-link">下载说明文档</a>
                </div>

            </div>
        </div>
    </div>
</div>

</body>
</html>