<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" con tent="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>教练信息</title>
    <link rel="stylesheet" type="text/css"
          href="${path}/resources/lib/bootstrap/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css"
          href="${path}/resources/css/match/style.1.0.css"/>
    <link rel="stylesheet" type="text/css"
          href="${path}/resources/css/coach/coach_info.css"/>
    <link rel="stylesheet" type="text/css"
          href="${path}/resources/css/common/style.css"/>
    <link href="${path}/resources/lib/layer/theme/default/layer.css"/>

    <style>
        .p3{
            font-family: "微软雅黑"!important;
            font-weight: 600;
            font-size: 30px;
        }
        button{
            padding:5px 20px!important;
        }
        input {
            border: 0px;
        }

        .coach-table td {
            /*border-top: 0xp!important;*/
            height: 40px;
            padding-left: 20px;
        }
        .headimg{
            height: 140px;
            width: 120px;
            border-radius:96px;

        }
    </style>

</head>

<body>
<jsp:include page="../common/head.jsp"/>

<div style="width: 100%;min-height:100%;background-color:#FBFBFB;display: flex;justify-content: center;">
    <div style="min-height:100%;background-color:white;padding: 10px;width: 1168px">

        <table class="table table-bordered text-center table-striped class" style="margin-bottom: 10px;">
            <div style="width: 100%;padding:40px 0px 10px 0px">
                <div class="list-AStitle">
                    <p class="col-lg-2 col-sm-2 col-xs-4 p3">教练信息</p>
                </div>
                <a href="${path}/coach/getAllCoach?pn=1&status=0">
                    <button class="btn btn-default pull-right" data-toggle="modal" data-target="#myModal">返回</button>
                </a>
                <div class="clearfix"></div>
            </div>
        </table>
        <div style="border: 1px solid #ccc;">
            <form id="form2" enctype="multipart/form-data">
                <div style="width: 100%;display: flex;justify-content: center;align-items: center;">
                    <div id="headimg" style="border-left: 50px solid white;border-right: 50px solid white;margin-top: -70px;overflow: hidden;">

                        <fmt:bundle basename="properties.upload">
                            <div style="width: 120px;height:140px;overflow: hidden;border-radius:96px;">

                                <div id="hidden" style="display: none"></div>
                                <c:if test="${!empty coach.photo}">
                                <img id='picImg' class="" src="${path}/<fmt:message key="upload.image.url.prefix"/>/${coach.photo}"
                                     style="height: 140px" >
                                </c:if>
                                <c:if test="${empty coach.photo}">
                                    <img class="headimg" id=""  src="${path}/resources/img/coach/upimg.png" />
                                </c:if>


                            </div>


                            <div id="headimg1" style="display: none;z-index: 3;margin-top: -140px;margin-left: 0px;height: 140px; width: 120px; position: absolute;">
                                <img class="headimg" id="click"  src="${path}/resources/img/coach/change.png" />

                            </div>

                        </fmt:bundle>



                    </div>
                    <input type="hidden" readOnly="" id="idcoach" name="idCoach" value="${coach.idCoach}">
                </div>
            </form>
            <div style="width: 100%;padding: 80px;">
                <div style="width: 100%;" id="top-btn">
                    <table style="width: 100%;" border="0">
                        <tr>
                            <th>
                                <h4>基本信息</h4>
                            </th>
                            <th>
                                <div class="pull-right">
                                    <button type="button" class="btn btn-success " style="display: none;"
                                            onclick="bt1()">保存
                                    </button>
                                    <button type="button" class="btn btn-warning" onclick="ch()">编辑</button>
                                    <button type="button" class="btn btn-primary pull-right" style="display: none" onclick="coachStatus()">就职
                                    </button>
                                    <button type="button" class="btn btn-danger pull-right" style="display: none" onclick="coachStatus()">离职
                                    </button>
                                </div>


                            </th>
                        </tr>

                    </table>
                </div>
                <div id="div1" class="coach-table">
                    <table style="width: 100%;" border="0">
                        <form id="form">
                            <tr>
                                <td align="right" style="width:100px;">用户名</td>
                                <td>${coach.name}</td>
                            </tr>
                            <tr>
                                <td align="right">性别</td>
                                <td>${coach.sex==0?"女":"男"}</td>
                            </tr>
                            <tr>
                                <td align="right">年龄</td>
                                <td>${coach.age}</td>
                            </tr>
                            <tr>
                                <td align="right">积分</td>
                                <td>${coach.score}</td>
                            </tr>
                            <tr>
                                <td align="right">编号</td>
                                <td>${coach.idCoach}</td>
                                <input type="hidden" readOnly="" id="coachId" name="idCoach" value="${coach.idCoach}">
                            </tr>
                            <tr>
                                <td align="right">是否在职</td>
                                <td>${coach.status==0?"是":"否"}</td>
                            </tr>
                            <tr>
                                <td align="right">手机号码</td>
                                <td class="div2"><input type="text" readOnly="" id="inputPhone" name="phone"
                                                        value="${coach.phone}"></td>
                            </tr>
                            <tr>
                                <td align="right">身份证号码</td>
                                <td>${coach.idcard}</td>
                            </tr>
                        </form>
                    </table>
                </div>


                <div style="width: 100%;">
                    <h4 style="width: 100px;">工作经历</h4>
                </div>
                <table style="width: 100%;" border="0" id="trnum">
                    <tr>
                        <td align="right" style="width:100px;">球队编号</td>
                        <td align="center">球队名称</td>
                        <td align="center">球队类型</td>
                        <td align="center">球队入队时间</td>
                        <td align="center">球队离队时间</td>
                    </tr>
                    <c:forEach var="ct" items="${coachTeam}">
                        <tr>
                            <td align="right">${ct.teamId}</td>
                            <td align="center">${ct.teamName}</td>
                            <td align="center" class="tnum">${ct.type}</td>
                            <td align="center">${ct.joinTime}</td>
                            <td align="center">${ct.outTime}</td>
                        </tr>
                    </c:forEach>

                </table>
            </div>
            <div style="clear: both;"></div>

        </div>
    </div>
</div>
</div>

</body>
<script src="${path}/resources/lib/jquery/jquery-1.11.3.js"></script>
<script src="${path}/resources/lib/respond.min.js"></script>
<script src="${path}/resources/lib/layer/layer.js"></script>
<script src="${path}/resources/js/index.js"></script>
<script src="${path}/resources/lib/webuploader/webuploader.withoutimage.min.js"></script>
<script>
    var p = document.getElementById("div1").getElementsByClassName("div2")
    var bt = document.getElementById("top-btn").getElementsByTagName("button")
    //		var p = document.getElementById("div1").getElementsByTagName("td")
    var ipt = document.getElementById("div1").getElementsByTagName("input")
    var myreg = /^[1][3,4,5,6,7,8,9][0-9]{9}$/;
    var check = 1;

    function bt1() {
        ch();
        checkn();

    }


    function ch() {
//				编辑样式
        if (check == 1) {
            for (var i = 0; i < p.length; i++) {
                bt[1].style.display = "none";
                bt[0].style.display = "";
                for (var x = 0; x < ipt.length; x++) {
                    ipt[x].readOnly = false;
                    ipt[x].setAttribute("class", "form-control");
                    ipt[x].style.background = "white";
                }

                check = 0;
            }
        } else {
            for (var i = 0; i < p.length; i++) {
                bt[0].style.display = "none";
                bt[1].style.display = "";
                //p[i].style.color="black";
                for (var x = 0; x < ipt.length; x++) {
                    ipt[x].readOnly = true;
                    ipt[x].setAttribute("class", "");
                    ipt[x].style.background = "white";
                }
                check = 1;
            }
        }

    }


    <%--${path}--%>

    var phone = document.getElementById("inputPhone");
    var phonen = phone.value;

    function checkn() {
        if (!myreg.test(phone.value)) {
            layer.msg('手机号码不正确');
            phone.value = phonen;
            return false;
        } else {
            updateCoach()
        }

    }

    function updateCoach() {
        var info = document.getElementById("form");

        layer.open({
            content: '您确定要修改教练的信息吗？'
            , btn: ['确定', '取消']
            , yes: function (index) {
                layer.close(index);

                ajax({
                    url: "${path}/coach/updateCoach",
                    type: "POST",
                    data: {
                        idCoach: info[0].value,
                        phone: info[1].value,
                        token: $('#token').html()
                    },
                    // dataType: "json",
                    success: function (data) {
                        if (data.status == 0) {
                            layer.msg('修改成功')
                            setTimeout("rel()", "1000");
                        } else if (data.status == 1) {
                            layer.msg('修改失败')
                            setTimeout("rel()", "1000");
                        }
                    },
                    error: function () {
                        layer.msg('网络错误');
                    }
                });
            }
        });
    }

    function coachStatus() {
        var info = document.getElementById("form");

        layer.open({
            content: '您确定要修改教练的就职状态吗？'
            , btn: ['确定', '取消']
            , yes: function (index) {
                layer.close(index);

                ajax({
                    url: "${path}/coach/coachStatus",
                    type: "POST",
                    data: {
                        idCoach: info[0].value,
                        token: $('#token').html()
                    },
                    success: function (data) {
                        if (data.status == 0) {
                            layer.msg('修改成功')
                            setTimeout("rel()", "1000");
                        } else if (data.status == 1) {
                            layer.msg('修改失败')
                            setTimeout("rel()", "1000");
                        }
                    },
                    error: function () {
                        layer.msg('网络错误');
                    }
                });
            }
        });
    }

    function rel() {
        location.reload();
    }

</script>
<script>
    (function () {
        /*就职*/
        if (${coach.status==1}) {
            bt[3].style.display = "none";
            bt[2].style.display = "";
        } else {
            bt[2].style.display = "none";
            bt[3].style.display = "";
        }
    }());


</script>
<script>
    (function () {
        num = document.getElementById("trnum").getElementsByClassName("tnum");
        // lv = document.getElementById("trlv").getElementsByClassName("tlv");
        for (var x = 0; x < num.length; x++) {
            if (0 < num[x].innerText && num[x].innerText < 7) {
                num[x].innerHTML = "小学球队";
            } else if (6 < num[x].innerText && num[x].innerText < 10) {
                num[x].innerHTML = "初中球队";
            } else if (num[x].innerText == 10) {
                num[x].innerHTML = "年级球队";
            } else {
                num[x].innerHTML = "校级球队";
            }
        }

    })();
</script>
<script>

     $('#click').click(function () {
         $('.webuploader-element-invisible:eq(0)').trigger('click');
     })

    var uploader = new WebUploader.Uploader({
        swf: getRootPath() + '/resources/lib/webuploader/Uploader.swf',
        fileVal: 'file',
        server: getRootPath() +'/coach/updatePhoto',

       pick:{
         id:'#hidden'
       },
        // 只允许选择图片文件。
        accept: {
            title: 'Images',
            extensions: 'gif,jpg,jpeg,bmp,png',
            mimeTypes: 'image/*'
        }
    });
    uploader.on('fileQueued', function (file) {
        console.log(file);

             if(file.size>204800){
                 layer.msg('图片大于200K！');
                 uploader.removeFile(file.getFiles()[0],true);
                 return false;
             }
             if(file.type!="image/png" && file.type!="image/jpg" && file.type!="image/jpeg"){
                 layer.msg('图片格式错误！');
                 uploader.removeFile(file.getFiles()[0],true);
                 return false;
             }
                layer.open({
                    content: '您确定要上传头像吗？'
                    , btn: ['确定', '取消']
                    , yes: function (index) {
                        layer.close(index);

                        uploader.upload();
                    }
                });
    })
    uploader.on('uploadBeforeSend', function (object, data, handler) {
        data.idCoach= $('#form2 input:eq(1)').val();
        delete data.id;
        delete data.type;
        delete  data.lastModifiedDate;
        delete data.size;
        data.token = $('#token').html();
        data['X-Requested-With'] = 'XMLHttpRequest';
    })
    uploader.on('uploadError', function (file, reason) {
        msg('上传失败，请重试');
    })
    uploader.on('uploadSuccess', function (file, data) {
        $('#token').html(data.token);
        if (data.status == 0) {
            layer.msg('图片修改成功');
            setTimeout("rel()", "1000");
        } else if (data.status == 1) {
            layer.msg('图片修改失败请重新尝试');
            setTimeout("rel()", "1000");
        }

    });
    uploader.on('error', function (type) {
        msg('系统错误');
    })


</script>
<script>
    window.onload = function(){
        var head = document.getElementById("headimg").getElementsByTagName("img");
        var head1 = document.getElementById("headimg1");

        if(head[0].src==""){
            head[1].style.display=""
        }
        head[0].onmouseover = function(){
            head1.style.display=""

        }
        head[1].onmouseover = function(){
            head1.style.display=""

        }
        head1.onmouseout = function(){
            head1.style.display="none"
        }

    }
</script>
<script>
    var imgwd = document.getElementById("picImg");

    if(imgwd.offsetWidth>120){
        var imgwd1 = imgwd.offsetWidth / 2 - 60;
        imgwd.style.marginLeft=-imgwd1 + "px";
    }

</script>


</html>