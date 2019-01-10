/**
 * 直播发布的 websocket  JS
 * Created by yrge on 2017/4/22.
 */
//连接发生错误的回调方法
websocket.onerror = function() {
    // alert("连接失败");
    layer.alert('连接失败',function (index) {
        layer.close();

    },true)
};

//连接成功建立的回调方法
websocket.onopen = function(event) {
    //alert(event+"链接成功")
}

//连接关闭的回调方法
websocket.onclose = function() {
    //alert("close");
}

//监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
window.onbeforeunload = function() {
    websocket.close();
}

//关闭连接
function closeWebSocket() {
    websocket.close();
}

//发送消息
function send(path) {
    var type;
    if (document.getElementById("radio-1").checked) {
        type = document.getElementById("radio-1").value;
    } else if (document.getElementById("radio-2").checked) {
        type = document.getElementById("radio-2").value;
    } else {
        type = document.getElementById("radio-3").value;
    }
    var message = new Object();
    // 得到文本框的内容
    message.html = editor.$txt.text();
    // 得到比分
    message.l_score = $("#l-score").text();
    message.r_score = $("#r-score").text();
    message.rId = $("#RID").data("rid");
    message.type = type;

    if(imgUrl != null){
        message.imgUrl=path+"/"+imgUrl;
    }
    //alert(JSON.stringify(message));

    websocket.send(JSON.stringify(message));
}