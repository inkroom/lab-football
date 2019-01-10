<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: inkbox
  Date: 18-3-30
  Time: 下午4:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
        <title><c:out value="${param.get('m')}" default="比赛已结束" /></title>
        <link rel="stylesheet" href="${path}/resources/lib/bootstrap/bootstrap.min.css"/>
        <link rel="stylesheet" type="text/css" href="${path}/resources/css/common/style.css">
        <link rel="stylesheet" type="text/css" href="${path}/resources/css/referee/public.css">
        <link rel="stylesheet" type="text/css" href="${path}/resources/css/live/endPC.css"/>
        <script type="text/javascript">
            var w = window;
            while (w !== w.parent) {
                w = w.parent;
            }
            if (w !== window)
                w.location.reload();
        </script>
    </head>

<body>
<!--导航-->
<jsp:include page="../common/head.jsp"/>

<h2 class="text-center text-end  font-place" style="color:white;" ><c:out value="${param.get('m')}" default="比赛已结束"/></h2>
<%--<div class="text-center col-lg-12 col-md-12 col-sm-12 col-xs-12 text-end">--%>
    <%--<p class="font-size1 font-place" style="color:white;"><c:out value="${param.get('m')}" default="比赛已结束"/></p>--%>
<%--</div>--%>

</body>
</html>
