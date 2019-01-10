<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>现场直播</title>
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${base_path }/resources/common/lib/html5shiv.js"></script>
    <script type="text/javascript" src="${base_path }/resources/common/lib/respond.min.js"></script>
    <![endif]-->
    <link rel="stylesheet" type="text/css" href="${base_path }/resources/common/static/h-ui/css/H-ui.reset.css"/>
    <link rel="stylesheet" type="text/css" href="${base_path }/resources/common/static/h-ui/css/H-ui.min.css"/>
    <link rel="stylesheet" type="text/css" href="${base_path }/resources/common/static/h-ui.admin/css/H-ui.admin.css"/>
    <link rel="stylesheet" type="text/css" href="${base_path }/resources/common/lib/Hui-iconfont/1.0.8/iconfont.css"/>
    <link rel="stylesheet" type="text/css" href="${base_path }/resources/common/static/h-ui.admin/skin/green/skin.css"/>
    <link rel="stylesheet" href="${base_path}/resources/css/site/gameLive.css">
    <link rel="stylesheet" type="text/css"
          href="${base_path}/resources/common/lib/wangEditor-2.1.23/dist/css/wangEditor.min.css"/>
    <!--[if lt IE 9]>
    <script src="http://ie7-js.googlecode.com/svn/version/2.1(beta4)/IE9.js"></script>
    <![endif]-->
    <link rel="stylesheet" type="text/css" href="${base_path }/resources/common/static/h-ui.admin/css/style.css"/>
    <!--[if IE 6]>
    <script type="text/javascript" src="${base_path }/resources/common/lib/DD_belatedPNG_0.0.8a-min.js"></script>
    <script>DD_belatedPNG.fix('*');</script>
    <![endif]-->
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${base_path}/resources/common/lib/html5shiv.js"></script>
    <script type="text/javascript" src="${base_path}/resources/common/lib/respond.min.js"></script>
    <script type="text/javascript" src="${base_path}/resources/common/lib/PIE_IE678.js"></script>
    <![endif]-->

</head>
<body>
<div class="livePage box-shadow">
    <div class="live-wrap ">
        <div class="live-head">
            <h2 class="live-head-title c-white" style="color: black">${gameName}</h2>
        </div>
        <div class="live-info">
            <div class="live-team f-l text-c">
                <a><img src="${base_path}/${HTeamFlag}" alt=""
                        class="avatar size-XXXL ml-10 box-shadow"></a> <a><h4
                    class="text-c c-333 mt-30">
                <strong class="c-primary">主&nbsp;队:</strong>${HTeam_Name}
            </h4></a>
            </div>
            <div class="live-score f-l hui-fadein">
                <h1 class="text-c text-shadow pos-r">
                    <i id="l-plus" class="Hui-iconfont f-30 pos-a">&#xe600;</i> <span
                        id="l-score">${l_score}</span> <i id="l-minus"
                                                          class="Hui-iconfont f-30 pos-a">&#xe6a1;</i> <strong>&nbsp;:&nbsp;</strong>
                    <i id="r-plus" class="Hui-iconfont f-30 pos-a">&#xe600;</i> <span
                        id="r-score">${r_score}</span> <i id="r-minus"
                                                          class="Hui-iconfont f-30 pos-a">&#xe6a1;</i>
                </h1>
                <h5 class="live-head-time c-666 text-r f-l mt-50 ml-40">开始时间：${time}</h5>
                <h5 id="nowTime"
                    class="live-head-time c-666 text-r f-r mt-50 mr-40">
                    当前时间：
                    <p id="showTime" style="display: inline;">0000/00/00 00:00:00</p>
                </h5>
            </div>
            <div class="live-team f-r text-c">
                <a><img src="${base_path}/${VTeamFlag}" alt=""
                        class="avatar size-XXXL ml-10 box-shadow"></a> <a><h4
                    class="text-c c-333 mt-30">
                <strong class="c-error">客&nbsp;队:</strong>${VTeam_Name }
            </h4></a>
            </div>
        </div>
        <div class="live-pre">
            <div class="radio-box mb-10">
                <input type="radio" id="radio-1" name="demo-radio1" value="1"
                       checked> <label for="radio-1">普通信息</label>
            </div>
            <div class="radio-box mb-10">
                <input type="radio" id="radio-2" name="demo-radio1" value="2">
                <label for="radio-2">判罚信息</label>
            </div>
            <div class="radio-box mb-10">
                <input type="radio" id="radio-3" name="demo-radio1" value="3">
                <label for="radio-3">进球信息</label>
            </div>
            <div>
                <textarea id="editor" style="width: 1024px; height: 500px;"></textarea>
            </div>
            <div class="w-300 container">
                <input class="btn btn-success radius mt-20 ml-50 mb-30"
                       id="btn_live" type="button" value="确认发布" onclick=""> <input
                    class="btn btn-warning radius mt-20 f-r mr-50 mb-30"
                    onclick="endGame()" type="button" value="比赛结束">
            </div>
        </div>
    </div>
</div>

<input type="text" id="info" style="display: none;" name="${info}"/>
<input type="text" id="RID" style="display: none;" name="${RID}"
       data-rId="${RID}">
<input type="hidden" id="csrf" name="_csrf" value="${_csrf.token }">
</body>
<script type="text/javascript" src="${base_path }/resources/common/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="${base_path }/resources/common/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="${base_path }/resources/common/static/h-ui.admin/js/H-ui.admin.js"></script>
<script type="text/javascript"
        src="${base_path }/resources/common/lib/jquery.contextmenu/jquery.contextmenu.r2.js"></script>
<script type="text/javascript" src="${base_path}/resources/js/site/layer/2.4/layer.js"></script>
<script type="text/javascript" src="${base_path}/resources/js/site/gameLive.js"></script>
<script type="text/javascript" src="${base_path}/resources/common/lib/wangEditor-2.1.23/dist/js/wangEditor.js"></script>
<script type="text/javascript">
    $("#btn_live").click(function () {
        SubmitMessage();
    })

    $(document).ready(function () {

        var info = document.getElementById("info").name;

        if (info == "nosession") {
            layer.confirm('当前会话失效，请重新登陆', {
                btn: ['确定']
                //按钮
            }, function () {
                location.href = "${base_path}/site/login_view.html"
            });
        }
        if (info == "noOpen") {
            layer.confirm('还未到比赛开始时间，不能开始直播', {
                btn: ['确定']
                //按钮
            }, function () {
                window.parent.location.reload();
            });
        }
        if (info == "error") {
            layer.confirm('当前比赛已经结束，无法再进行直播', {
                btn: ['确定']
                //按钮
            }, function () {
                window.parent.location.reload();
            });
        }

    });
    $(function () {
        setInterval("getTime();", 1000); //每隔一秒执行一次
    })

    //取得系统当前时间
    function getTime() {
        var myDate = new Date();
        var date = myDate.toLocaleDateString();
        var hours = myDate.getHours();
        var minutes = myDate.getMinutes();
        var seconds = myDate.getSeconds();
        if (seconds < 10) {
            seconds = '0' + seconds;
        }
        $("#showTime").html(date + " " + hours + ":" + minutes + ":" + seconds); //将值赋给div
    }

    var count = 0;
    var imgUrl;
    var editor = new wangEditor('editor');
    editor.config.menus = ['img', 'fullscreen'];
    // 上传图片路径
    editor.config.uploadImgUrl = '${base_path}/site/uploadImage.html';
    editor.config.uploadImgFileName = 'myFileName';
    editor.create();

    //当点击了图片上传时
    $(".menu-group div:first-child a:first-child").click(
        function () {
            var html = editor.$txt.html();
            var reg = /<img[^>]*>/gi;
            var newHtml = html.match(reg);
            var arr = JSON.stringify(newHtml);
            if (arr.split(">").length > 1) {
                layer.msg("每次最多上传一张图片");
                $(".wangEditor-drop-panel").css("display", "none");
                return false;
            } else if (count < 1) {
                // 自定义load事件
                editor.config.uploadImgFns.onload = function (resultText,
                                                              xhr) {
                    imgUrl = resultText;
                    if (imgUrl == "error") {
                        layer.msg("图片大小超出限制或者图片格式不规范，请上传1M以内jpg或png格式图片");
                    } else {
                        var url = "${base_path}/" + resultText;
                        var originalName = editor.uploadImgOriginalName
                            || '';
                        editor.command(null, 'insertHtml', '<img src="'
                            + url + '" alt="' + originalName
                            + '" style="max-width:100%;"/>');
                        count++;
                    }
                };
            }
        });

    function SubmitMessage() {
        var text = editor.$txt.text().replace(/\s/g, "");
        var l_score = $("#l-score").text();
        var r_score = $("#r-score").text();
        if (l_score == null || l_score == undefined || l_score == "") {
            l_score = 0;
        } else if (r_score == null || r_score == undefined || r_score == "") {
            r_score = 0;
        }
        var type;
        if (document.getElementById("radio-1").checked) {
            type = document.getElementById("radio-1").value;
        } else if (document.getElementById("radio-2").checked) {
            type = document.getElementById("radio-2").value;
        } else {
            type = document.getElementById("radio-3").value;
        }
        if (text.length < 1000) {
            var html = editor.$txt.html();
            var reg = /<img[^>]*>/gi;
            var newHtml = html.match(reg);
            var arr = JSON.stringify(newHtml);
            if (text != null && text != undefined && text != ""
                || arr.split(">").length > 1) {
                var csrf = $("#csrf").val();
                var parmas = {
                    l_score: l_score,
                    r_score: r_score,
                    html: text,
                    imgUrl: imgUrl,
                    type: type,
                    _csrf: csrf
                }
                $.ajax({
                    type: "POST",
                    url: "insertInfo.action",
                    data: parmas,
                    success: function (data) {
                        if (data != 200) {
                            layer.msg("抱歉,页面已经失效,请重新提交");
                        } else {
                            send("${base_path}");
                            layer.msg("发布成功");
                            location.href = "${base_path}/site/gameLive.html";
                        }
                    },
                    error: function (data) {
                        layer.msg("发布失败，请重新发布");
                    }
                });

            } else {
                layer.msg("请输入有效直播信息再发布");
            }
        } else {
            layer.msg("直播信息不能超过1000字");
        }
    }

    function endGame() {
        var html = editor.$txt.text();
        var l_score = $("#l-score").text();
        var r_score = $("#r-score").text();
        var parmas = {
            l_score: l_score,
            r_score: r_score,
        }
        /*if (l_score == r_score) {
            layer.msg("比赛尚未决出胜负 还不能结束哦");
        } else */
        {
            if (html != null && html != undefined && html != "") {
                layer.msg("请先提交当前页面内容再结束比赛");
            } else {
                layer.confirm('是否结束当前比赛,比赛结束后无法进行直播?', {
                    btn: ['结束比赛', '取消']
                    //按钮
                }, function () {
                    $.ajax({
                        type: "POST",
                        url: "endGame.html",
                        data: parmas,
                        success: function (data) {
                            layer.confirm('比赛结束', {
                                btn: ['确定']
                                //按钮
                            }, function () {
                                window.parent.location.reload();
                            });
                        },
                        error: function (data) {
                            layer.msg("比赛结束失败");
                        }
                    });
                }, function () {

                });
            }
        }
    }
</script>
<script type="text/javascript">
    var wsServer = 'ws://' + window.location.host + '<%=request.getContextPath()%>/site';
    var websocket = null;
    //判断当前浏览器是否支持WebSocket
    if ('WebSocket' in window) {
        try {
            websocket = new WebSocket(wsServer);
        } catch (e) {
            layer.alert('连接过多，请关闭浏览器重新打开该页面！');
        }
    } else {
        layer.alert('您的浏览器暂不支持观看本直播，请升级到IE10以上浏览器，或者使用谷歌、火狐、360浏览器!');
        window.close();
    }
</script>
<script src="${base_path}/resources/js/site/ReleaseWebSocket.js" type="text/javascript"></script>

</html>