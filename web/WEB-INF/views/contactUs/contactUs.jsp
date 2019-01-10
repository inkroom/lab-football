<%@ page import="java.util.ArrayList" %>
<%@ page import="cn.inkroom.web.money.gate.beans.common.HtmlBean" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="cn.inkroom.web.money.gate.beans.contactUs.ContactUsBean" %><%--
  Created by IntelliJ IDEA.
  User: inkbox
  Date: 18-2-23
  Time: 下午10:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8"/>
    <title>联系我们</title>
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/home.css"/>
    <link rel="stylesheet" type="${pageContext.request.contextPath}/resources/text/css" href="css/prodAndTech.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/menu.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/under.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/contactUs.css"/>
    <script src="${pageContext.request.contextPath}/resources/common/jquery/jquery.min.js?v=2.1.4"></script>
    <script src="${pageContext.request.contextPath}/resources/common/boostrap/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/common/plugins/layer/layer.min.js"></script>

</head>

<body style="background: #F5F5F5;">
<jsp:include page="/head.html"/>
<div class="top-img" id="top-img">
    <div class="top-img-title">
        <h1 style="text-align: center;color:#04A4E1 ;">联系我们</h1>
    </div>
</div>


<%
    ArrayList<ContactUsBean> beans = (ArrayList<ContactUsBean>) request.getAttribute("beans");
    request.setAttribute("row", beans.size() / 3);//行（每行三个）
    request.setAttribute("line", beans.size() % 3);//余几个
%>
    <c:if test="${row == 0 && line != 0}">
<div class="middle">
        <c:forEach items="${beans}" var="bean" varStatus="status">
            <div class="contactBody">
                <div class="title">
                    <h4>${bean.title}
                    </h4>
                </div>
                <p>${bean.contactname1} <c:if test="${bean.contactname1 != ''}">:</c:if> ${bean.contactway1}
                </p>
                <p>${bean.contactname2} <c:if test="${bean.contactname2 != ''}">:</c:if> ${bean.contactway2}
                </p>
                <p>${bean.contactname3} <c:if test="${bean.contactname3 != ''}">:</c:if> ${bean.contactway3}
                </p>
                <p>${bean.contactname4} <c:if test="${bean.contactname4 != ''}">:</c:if> ${bean.contactway4}
                </p>
                <p>${bean.contactname5} <c:if test="${bean.contactname5 != ''}">:</c:if> ${bean.contactway5}
                </p>
            </div>
        </c:forEach>
</div>
    </c:if>

    <%--<c:if test="${row!=0}">--%>
        <%--<c:forEach items="${beans}" var="bean" varStatus="status" step="3" begin="0" end="${row}">--%>
<%--<div class="middle">--%>
            <%--<c:if test="${(status.index % 3) == 0}">--%>
                <%--<div class="contactBody">--%>
                    <%--<div class="title">--%>
                        <%--<h4>${bean.title}--%>
                        <%--</h4>--%>
                    <%--</div>--%>
                    <%--<p>${bean.contactname1}：${bean.contactway1}--%>
                    <%--</p>--%>
                    <%--<p>${bean.contactname2}：${bean.contactway2}--%>
                    <%--</p>--%>
                    <%--<p>${bean.contactname3}：${bean.contactway3}--%>
                    <%--</p>--%>
                    <%--<p>${bean.contactname4}：${bean.contactway4}--%>
                    <%--</p>--%>
                    <%--<p>${bean.contactname5}：${bean.contactway5}--%>
                    <%--</p>--%>
                <%--</div>--%>
            <%--</c:if>--%>
<%--</div>--%>
        <%--</c:forEach>--%>
    <%--</c:if>--%>

    <%--<c:if test="${line != 0 && row != 0}">--%>
        <%--<c:forEach items="${beans}" var="bean" varStatus="status">--%>
            <%--<c:if test="${(status.index % 3)==0}">--%>

            <%--</c:if>--%>
        <%--</c:forEach>--%>
    <%--</c:if>--%>

<%
//    ArrayList<ContactUsBean> beans = (ArrayList<ContactUsBean>) request.getAttribute("beans");
    int row = beans.size() / 3;   //行（每行三个）
    int line = beans.size() % 3;  //余几个
    int index = 0; //输出arraylist数组的下标
    ContactUsBean bean = null;

    if(row != 0 ){
    for (int i = 0; i < row; i++) {
%>


<div class="middle">

    <%
        for (int j = 1; j <= 3; j++) {
            index = i * 3 + j - 1;
            bean = beans.get(index);
            request.setAttribute("bean",bean);
    %>
    <div class="contactBody">
        <div class="title">
            <h4>${bean.title}
            </h4>
        </div>
        <p>${bean.contactname1} <c:if test="${bean.contactname1 != ''}">:</c:if> ${bean.contactway1}
        </p>
        <p>${bean.contactname2} <c:if test="${bean.contactname2 != ''}">:</c:if> ${bean.contactway2}
        </p>
        <p>${bean.contactname3} <c:if test="${bean.contactname3 != ''}">:</c:if> ${bean.contactway3}
        </p>
        <p>${bean.contactname4} <c:if test="${bean.contactname4 != ''}">:</c:if> ${bean.contactway4}
        </p>
        <p>${bean.contactname5} <c:if test="${bean.contactname5 != ''}">:</c:if> ${bean.contactway5}
        </p>
    </div>
    <% } %>

</div>
<%
    }
    }
    //处理余数
    if (line != 0 && row != 0) {
%>
<div class="middle">

    <%
        for (int j = 1; j <= line; j++) {
            index++;
            bean = beans.get(index);
            request.setAttribute("bean",bean);
    %>
    <div class="contactBody">
        <div class="title">
            <h4>${bean.title}
            </h4>
        </div>
        <p>${bean.contactname1} <c:if test="${bean.contactname1 != ''}">:</c:if> ${bean.contactway1}
        </p>
        <p>${bean.contactname2} <c:if test="${bean.contactname2 != ''}">:</c:if> ${bean.contactway2}
        </p>
        <p>${bean.contactname3} <c:if test="${bean.contactname3 != ''}">:</c:if> ${bean.contactway3}
        </p>
        <p>${bean.contactname4} <c:if test="${bean.contactname4 != ''}">:</c:if> ${bean.contactway4}
        </p>
        <p>${bean.contactname5} <c:if test="${bean.contactname5 != ''}">:</c:if> ${bean.contactway5}
        </p>
    </div>
    <% } %>

</div>
<%
    }
%>

<iframe width="100%" name="" scrolling="no" src="../under.html" frameborder="0" id=""></iframe>


</body>

</html>
