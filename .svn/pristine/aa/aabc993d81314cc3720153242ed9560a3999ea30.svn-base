<%--
  Created by IntelliJ IDEA.
  User: inkbox
  Date: 18-3-28
  Time: 上午10:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--底部-->
<div class="bottom_box" >
    <div class="tab_bar" id="index" onclick="document.location.href='${path}/referee/index';">
        <img src="${path}/resources/img/referee/index${url.contains('index')?'-on':''}.png" />
    </div>
    <div class="tab_bar ${pageContext.request.getRequestURI().contains('check')?'choose':''}" id="identity" onclick="location.href='${path}/referee/check';">
        <img src="${path}/resources/img/referee/identity${url.contains('check')?'-on':''}.png"/>
    </div>
    <div class="tab_bar ${pageContext.request.getRequestURI().contains('QRcode')?'choose':''}" id="QRcode" onclick="location.href='${path}/referee/QRcode';">
        <img src="${path}/resources/img/referee/QRCode${url.contains('QRcode')?'-on':''}.png"/>
    </div>
    <div class="tab_bar ${pageContext.request.getRequestURI().contains('score')?'choose':''}" id="revisel" onclick="location.href='${path}/referee/score';">
        <img src="${path}/resources/img/referee/revise${url.contains('score')?'-on':''}.png"/>
    </div>
</div>
