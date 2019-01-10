<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>直播地址</title>
    <link rel="stylesheet" href="${path}/resources/lib/bootstrap/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="${path}/resources/css/referee/public.css">
    <link rel="stylesheet" type="text/css" href="${path}/resources/css/referee/qrCode.css">
</head>
<body>
<jsp:include page="../common/head.jsp?v=2&title=直播地址"/>

<div class="container" style="margin-top: 10%;">
    <div class="row">
        <div class="phone-qr" id="qrcode1"></div>
    </div>
    <div class="row text-center panel-distance">
        <a href="${path}/live/${schId}/m/index" target="_blank" >手机端链接</a><br>
        <a href="${path}/live/${schId}/m/index" target="_blank" id="mobile"></a>
    </div>

    <div class="row panel-distance">
        <div class="phone-qr" id="qrcode2"></div>
    </div>
    <div class="row text-center panel-distance btn-distance">
        <a href="${path}/live/${schId}/index" target="_blank" >电脑端链接</a><br>
        <a href="${path}/live/${schId}/index" target="_blank" id="pc">电脑端链接</a>
    </div>
</div>
<jsp:include page="bar.jsp"/>
<script src="${path}/resources/lib/jquery/jquery-3.1.1.min.js"></script>
<script src="${path}/resources/lib/bootstrap/bootstrap.min.js"></script>
<script src="${path}/resources/js/referee/qrcode.js"></script>
<script type="text/javascript">
    $('#mobile').html('手机端链接：' + location.host + $('#mobile').attr('href'))
    $('#pc').html('电脑端链接：' + location.host + $('#pc').attr('href'))
    /*生成二维码*/
    var qrcode1 = new QRCode(document.getElementById("qrcode1"), {
        width: 100,
        height: 100
    });
    var qrcode2 = new QRCode(document.getElementById("qrcode2"), {
        width: 100,
        height: 100
    });
    var prefix = location.host;
    if (prefix.indexOf("http://") === -1) {
        prefix = "http://" + prefix;
    }
    qrcode1.makeCode(prefix + "${path}/live/${schId}/m/index");
    qrcode2.makeCode(prefix + "${path}/live/${schId}/index");
</script>
</body>
</html>
