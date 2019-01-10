<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<h1>Test</h1>
		<h2>${key }</h2>
	</body>
	<script type="application/javascript" src="${base_path}/resources/js/site/socket.js"></script>
	<script type="text/javascript">

        var stomp=new WebSocket("ws://10.14.2.151/webSocket");

        var stompClient = Stomp.over(stomp);

        stompClient.connect({}, function(frame) {
            //订阅地址
            stompClient.subscribe('/topic/waitting', function(message){
                console.info("topic Hello "+message.body);
            });
        });

	</script>
</html>