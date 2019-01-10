/**
 * 直播接受的websocket js
 * Created by yrge on 2017/4/23.
 */
//连接发生错误的回调方法
websocket.onerror = function() {
    alert("链接错误！");
};

//连接成功建立的回调方法
websocket.onopen = function(event) {
    //alert(JSON.stringify(event)+"链接成功")
}
//接收到消息的回调方法
websocket.onmessage = function(event) {
    //alert(JSON.stringify(event))
    setMessageInnerHTML(event.data);
}

//连接关闭的回调方法
websocket.onclose = function() {
   // alert("close");
}

//监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
window.onbeforeunload = function() {
    websocket.close();
}

//将消息显示在网页上
function setMessageInnerHTML(innerHTML) {
    var json = JSON.parse(innerHTML);
    var inHtml = "";
    if (json.rId == $("#rId").data("rid")){
        if (json.html != null){
            if(json.type == 3){
                if (json.imgUrl == null){
                    inHtml=
                    "                <em class=\"live-goal-em pos-a round\"></em>\n" +
                    "                <div class=\"live-time f-20 pos-a  c-success\"><strong>" + json.liveTime + "</strong></div>\n" +
                    "                 <div class=\"live-goal-area\">\n" +
                    "<div class=\"live-text f-20 pb-20 c-success\">\n" +
                    "<div class=\"panel panel-success\"> \n" +
                    "<div class=\"panel-header \"><strong class=\"f-20\">进球信息</strong>></div>\n" +
                    "                   <div class=\"panel-body\">" +
                    json.html +
                    "                       </div><br/>\n" +
                    "                    </div>\n" +
                    "                </div>\n"+
                    "                </div>\n"
                }else {
                    inHtml= "                <em class=\"live-goal-em pos-a round\"></em>\n" +
                        "                <div class=\"live-time f-20 pos-a  c-success\"><strong>" + json.liveTime + "</strong></div>\n" +
                        "                 <div class=\"live-goal-area\">\n" +
                        "<div class=\"live-text f-20 pb-20 c-success\">\n" +
                        "<div class=\"panel panel-success\"> \n" +
                        "<div class=\"panel-header \"><strong class=\"f-20\">进球信息</strong>></div>\n" +
                        "                   <div class=\"panel-body\">" +
                        json.html +
                        "                       </div><br/>\n" +
                        "<img src=" + json.imgUrl + ">\n"+
                        "                       </div><br/>\n" +
                        "                    </div>\n" +
                        "                </div>\n"+
                        "                </div>\n"
                }
                var divs= document.getElementById('msg-live');
                var fist1_div = document.getElementById('msg-live').children;
                var odiv = document.createElement('div'); //创建一个div元素。
                odiv.innerHTML = inHtml;
                odiv.setAttribute("class","live-mod live-goal pos-r hui-fadeinB");
                divs.insertBefore(odiv,fist1_div[0]); //在大的div元素下插入创建出来的元素，第一个参数是创建的div，第二个参数是要插入到哪个div前面。
                $("#l-score").html(json.l_score);
                $("#r-score").html(json.r_score);
            }
            if(json.type == 2){
                if (json.imgUrl == null){
                    inHtml=
                        "                <em class=\"live-red-em pos-a round\"></em>\n" +
                        "                <div class=\"live-time f-20 pos-a  c-red\"><strong>" + json.liveTime + "</strong></div>\n" +
                        "                 <div class=\"live-red-area\">\n" +
                        "<div class=\"live-text f-20 pb-20 c-red\">\n" +
                        "<div class=\"panel panel-danger\"> \n" +
                        "<div class=\"panel-header \"><strong class=\"f-20\">判罚信息</strong>></div>\n" +
                        "                   <div class=\"panel-body\">" +
                        json.html +
                        "                       </div><br/>\n" +
                        "                    </div>\n" +
                        "                </div>\n"+
                        "                </div>\n"+
                        "                </div>\n"
                }else {
                    inHtml= "                <em class=\"live-red-em pos-a round\"></em>\n" +
                        "                <div class=\"live-time f-20 pos-a  c-red\"><strong>" + json.liveTime + "</strong></div>\n" +
                        "                 <div class=\"live-red-area\">\n" +
                        "<div class=\"live-text f-20 pb-20 c-red\">\n" +
                        "<div class=\"panel panel-danger\"> \n" +
                        "<div class=\"panel-header \"><strong class=\"f-20\">判罚信息</strong>></div>\n" +
                        "                   <div class=\"panel-body\">" +
                        json.html +"</div><br/>\n" +
                        "<img src=" + json.imgUrl + ">\n"+
                        "                    </div>\n" +
                        "                </div>\n"+
                        "                </div>\n"+
                        "                </div>\n"
                }
                var divs= document.getElementById('msg-live');
                var fist1_div = document.getElementById('msg-live').children;
                var odiv = document.createElement('div'); //创建一个div元素。
                odiv.innerHTML = inHtml;
                odiv.setAttribute("class","live-mod live-red pos-r hui-fadeinB");
                divs.insertBefore(odiv,fist1_div[0]); //在大的div元素下插入创建出来的元素，第一个参数是创建的div，第二个参数是要插入到哪个div前面。
                $("#l-score").html(json.l_score);
                $("#r-score").html(json.r_score);
            }
            if(json.type == 1){
                if (json.imgUrl == null){
                    inHtml=
                        "                <em class=\"live-normal-em pos-a round\"></em>\n" +
                        "                <div class=\"live-time f-20 pos-a  c-666\"><strong>" + json.liveTime + "</strong></div>\n" +
                        "                 <div class=\"live-normal-area\">\n" +
                        "<div class=\"live-text f-20 pb-20 c-666\">\n" +
                        "<div class=\"panel panel-default\"> \n" +
                        "<div class=\"panel-header \"><strong class=\"f-20\">普通信息</strong>></div>\n" +
                        "                   <div class=\"panel-body\">" +
                        json.html +
                        "                       </div><br/>\n" +
                        "                    </div>\n" +
                        "                </div>\n"+
                        "                </div>\n"+
                        "                </div>\n"
                }else {
                    inHtml= "                <em class=\"live-normal-em pos-a round\"></em>\n" +
                        "                <div class=\"live-time f-20 pos-a  c-666\"><strong>" + json.liveTime + "</strong></div>\n" +
                        "                 <div class=\"live-normal-area\">\n" +
                        "<div class=\"live-text f-20 pb-20 c-666\">\n" +
                        "<div class=\"panel panel-default\"> \n" +
                        "<div class=\"panel-header \"><strong class=\"f-20\">普通信息</strong>></div>\n" +
                        "                   <div class=\"panel-body\">" +
                        json.html +"</div><br/>\n" +
                        "<img src=" + json.imgUrl + ">\n"+
                        "                    </div>\n" +
                        "                </div>\n"+
                        "                </div>\n"+
                        "                </div>\n"
                }
                var divs= document.getElementById('msg-live');
                var fist1_div = document.getElementById('msg-live').children;
                var odiv = document.createElement('div'); //创建一个div元素。
                odiv.innerHTML = inHtml;
                odiv.setAttribute("class","live-mod live-normal pos-r hui-fadeinB");
                divs.insertBefore(odiv,fist1_div[0]); //在大的div元素下插入创建出来的元素，第一个参数是创建的div，第二个参数是要插入到哪个div前面。
                $("#l-score").html(json.l_score);
                $("#r-score").html(json.r_score);
            }
        }
        }
     $("#liveNumber").html(json.liveNumber);
}

//关闭连接
function closeWebSocket() {
    websocket.close();
}
