<%@ page import="cn.edu.nsu.lib.config.Constants" %><%--
  User: 墨盒
  Date: 2017/8/12
  Time: 22:38
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="shortcut icon" href="${path}/static/img/favicon.ico" type="image/x-icon"/>
    <title>提示</title>
</head>
<body>
<%request.setAttribute("key", Constants.KEY_REQUEST_MESSAGE);%>
<h2 style="text-align: center">${requestScope.get(requestScope.get('key'))}</h2>
</body>
</html>
