<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>球员验证</title>
    <link rel="stylesheet" href="${base_path}/resources/common/static/h-ui/css/H-ui.reset.css">
    <link rel="stylesheet" href="${base_path}/resources/common/static/h-ui/css/H-ui.min.css">
    <link rel="stylesheet" href="${base_path}/resources/css/site/playerCheck.css">
    
    <!--    <link rel="stylesheet" href="../static/h-ui/css/H-ui.reset.css">
		<link rel="stylesheet" href="../static/h-ui/css/H-ui.min.css">
		<link rel="stylesheet" href="../lib/playerCheck.css">-->
    <!--[if lt IE 9]>
    <script type="text/javascript" src="../lib/html5shiv.js"></script>
    <script type="text/javascript" src="../lib/respond.min.js"></script>
    <script type="text/javascript" src="../lib/PIE_IE678.js"></script>
    <![endif]-->
</head>
<body>
<div class="playerCheck">
    <div class="playerCheck-box">
        <div class="row">
            <div class="col-sm-6 col-xs-6">
                <div class="row">
                    <div class="col-sm-12 text-c">
                        <ul class="team-title">
                            <li class="f-24 pl-30 pr-30 pt-5 check" >${A}</li>
                        </ul>
                    </div>
                </div>
                <div class="player" style="border:1px solid #666666 " >
                    <ul class="" style="">
                        <c:forEach var="player" items="${data.A}">
                            <c:if test="${player.CHECK_IDCARD == 1}"><li id="${player.A_ID}" class="playerchecked"></c:if>
                            <c:if test="${player.CHECK_IDCARD == 0}"><li id="${player.A_ID}"></c:if>
                            <img src="${base_path}/${player.P_PEROSONAL_PHOTO}" class="" alt="">
                            <span class="f-18">${player.A_NAME}</span>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
            <div class="col-sm-6 col-xs-6">
                <div class="row">
                    <div class="col-sm-12 text-c">
                        <ul class="team-title">
                            <li class="f-24 pl-30 pr-30 check pt-5" >${B}</li>
                        </ul>
                    </div>
                </div>
                <div class="player" style="border:1px solid #666666 " >
                    <ul class="" style="">
                        <c:forEach var="player" items="${data.B}">
                            <c:if test="${player.CHECK_IDCARD == 1}"><li id="${player.A_ID}" class="playerchecked"></c:if>
                            <c:if test="${player.CHECK_IDCARD == 0}"><li id="${player.A_ID}"></c:if>
                            <img src="${base_path}/${player.P_PEROSONAL_PHOTO}" class="" alt="">
                            <span class="f-18">${player.A_NAME}</span>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
            <div class="row">
            
            </div>
        
        </div>
        </div>
    </div>
</body>
<!--<script type="application/javascript" src="${base_path}/resources/common/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="application/javascript" src="${base_path}/resources/common/static/h-ui/js/H-ui.min.js"></script>
<script type="application/javascript" src="${base_path}/resources/js/site/playerCheck.js"></script>
<script type="application/javascript" src="${base_path}/resources/js/site/socket.js"></script>-->

<script type="application/javascript" src="${base_path}/resources/common/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="application/javascript" src="${base_path}/resources/common/static/h-ui/js/H-ui.min.js"></script>
<script type="application/javascript" src="${base_path}/resources/js/site/playerCheck.js"></script>
<script type="application/javascript" src="${base_path}/resources/js/site/socket.js"></script>>


<script type="text/javascript" >



    var root = '<%=request.getLocalAddr() %>';

    var port = '<%=request.getServerPort() %>';

    var path = '<%=request.getContextPath() %>';

    //    var path = 'localhost';
    var stomp = new WebSocket("ws://" + root + ":" + port + path + "/site/webSocket.action");

    var stompClient = Stomp.over(stomp);

    //    var ids = new Array();
    //    ids[0] = 1;
    //    ids[1] = 2;
    //    ids[2] = 3;

    stompClient.connect({}, function (frame) {
        //订阅地址
        stompClient.subscribe('/topic/waitting', function (message) {
            var jsonarray = $.parseJSON(message.body);
            addPass(jsonarray.message);
//            jsonarray.pass(ids);
        });
    });

    function pass(ids) {
        $(ids).each(function (index, obj) {
            addPass(obj);
        })
    }
    function addPass(id) {
        $("li#" + id).addClass("playerchecked");
    }
</script>
</html>