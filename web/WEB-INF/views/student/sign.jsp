<%--
  User: 墨盒
  Date: 2017/11/8
  Time: 16:33
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html style="background: #FFF">
<head>
    <meta charset="UTF-8">
    <title>签到-五分钟内有效</title>
    <link rel="stylesheet" href="${base_path}/resources/css/student/qrcode.css">
    <%--<link rel="stylesheet" href="${base_path}/resources/css/student/crowd.css">--%>
</head>
<body style="background: #FFF">
<%--<header style="background: #FFF">--%>
<div class="bg" style="background: #FFF">
    <%--<canvas id="display"></canvas>--%>
    <div class="lanren" style="background: #FFF">
        <button id="send">点击生成二维码</button>
        <div id="qrcode" style="display: block"></div>
        <div id="blachole"></div>
    </div>
</div>
<%--</header>--%>
<script src="${base_path}/resources/common/jquery.min.js"></script>
<script src="${base_path}/resources/common/layer/2.4/layer.js"></script>
<script src="${base_path}/resources/js/index.js"></script>
<%--<script src="${base_path}/resources/js/student/constellation.js"></script>--%>
<script src="${base_path}/resources/js/student/qrCode.min.js"></script>
<script>
    function generateQRCode(rendermethod, picwidth, picheight, url) {
        $('#qrcode').html('');
        $("#qrcode").qrcode({
            render: rendermethod, // 渲染方式有table方式（IE兼容）和canvas方式
            width: picwidth, //宽度
            height: picheight, //高度
            text: utf16to8(url), //内容
            typeNumber: -1,//计算模式
            correctLevel: 2,//二维码纠错级别
            background: "#ffffff",//背景颜色
            foreground: "#000000"  //二维码颜色

        });
    }

    //中文编码格式转换
    function utf16to8(str) {
        var out, i, len, c;
        out = "";
        len = str.length;
        for (i = 0; i < len; i++) {
            c = str.charCodeAt(i);
            if ((c >= 0x0001) && (c <= 0x007F)) {
                out += str.charAt(i);
            } else if (c > 0x07FF) {
                out += String.fromCharCode(0xE0 | ((c >> 12) & 0x0F));
                out += String.fromCharCode(0x80 | ((c >> 6) & 0x3F));
                out += String.fromCharCode(0x80 | ((c >> 0) & 0x3F));
            } else {
                out += String.fromCharCode(0xC0 | ((c >> 6) & 0x1F));
                out += String.fromCharCode(0x80 | ((c >> 0) & 0x3F));
            }
        }
        return out;
    }

    $(function () {
        $('#send').on('click', function () {
            ajax({
                url: '${base_path}/chat/code',
                dataType: 'json',
                success: function (result) {
                    if (result.status === 0) {
                        generateQRCode("table", 200, 200, result.data.code);
//                        var margin = ($("#qrcode").height()-$("#codeico").height())/2;
//                        $("#codeico").css("margin",margin);
//                        var content = document.getElementById("qrcode");
//                        content.innerHTML = '';
//                        var qrcode = new QRCode(content, {
//                            width: 200,
//                            height: 200
//                        });
//                        qrcode.clear();
//                        qrcode.makeCode(result.data.code);
//                        content.style.display = "block";
                    }
                }
            });
        }).trigger('click');
    });
</script>
</body>
</html>
