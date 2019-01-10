<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<c:set var="base_path" value="${path}"/>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>球队管理</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="${base_path}/resources/css/team/bootstrap-responsive.css" rel="stylesheet">
    <link href="${base_path}/resources/css/team/bootstrap.css" rel="stylesheet">
    <link href="${base_path}/resources/lib/layer/theme/default/layer.css" rel="stylesheet">
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" type="text/css" href="${base_path}/resources/css/team/createTeam-style.css" />
    <link rel="stylesheet" type="text/css" href="${base_path}/resources/css/team/bootstarp3-btn.css"/>
    <link rel="stylesheet" type="text/css" href="${base_path}/resources/css/common/style.css" />
    <%--[if IE 8]--%>
    <script src="${base_path}/resources/js/match/html5shiv.min.js"></script>
    <%--[endif]--%>
</head>

<body>
<jsp:include page="../common/head.jsp"/>

<div class="container2" style="width:1168px;">
    <p style="font-family: '微软雅黑';font-weight: 600;font-size: 24px;margin-top:30px;/*position:absolute;left:270px;top:81px;*/">球队管理</p>

    <ul class="nav nav-tabs nav-color" style="margin-top:21px;">
        <li><a href="${path}/team/display">球队预览</a></li>
        <li class="active"><a>球队创建</a></li>
    </ul>
    <!--导航结束-->
    <div class="row">
        <div class="span7" >
            <table class="table table-bordered  messageCard">
                <tr class="main-su">
                    <td style="color: #5f5f5f;">球队基本信息</td>
                </tr>
                <tr>
                    <td>
                        <p><span class="muted"style="float:left;margin-top:4px;margin-right:5px;">队&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名:</span>
                            <input id="teamName" type="text">
                            <span id="teamName_alert" style="color: red;visibility: hidden">请输入球队的名字</span>
                            <%-- <span id="teamName_duplication" style="color: red;visibility: hidden">球队名已存在，如果是要继续添加球员，那么请忽视，否则请重新赋球队名！</span>--%>
                        </p>
                        <p><span class="muted"style="float:left;margin-top:4px;margin-right:5px">球队类型:</span>
                            <select id="teamClazz" style="" name="type">
                                <option value="1">小学一年级</option>
                                <option value="2">小学二年级</option>
                                <option value="3">小学三年级</option>
                                <option value="4">小学四年级</option>
                                <option value="5">小学五年级</option>
                                <option value="6">小学六年级</option>
                                <option value="7">初中一年级</option>
                                <option value="8">小学二年级</option>
                                <option value="9">小学三年级</option>
                                <option value="10">混合(年级)</option>
                                <option value="11">混合(校级)</option>
                            </select>
                        </p>
                        <p><span class="muted">球队状态:</span>
                            <span name="teamStatus" checked="checked" value="0"> 在役</span>
                        </p>
                    </td>
                </tr>
            </table>
        </div>
        <div class="span7">
            <table class="table table-bordered  messageCard" style="margin-left:68px;">
                <tr class="main-su">
                    <td style="color: #5f5f5f;">教练信息</td>
                </tr>
                <tr>
                    <td>
                        <p style="margin-top:6px;">
                <span class="muted">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名:
                    <span id="coach_name"style="color:black"></span>
                </span>
                            <span id="teamCoachName_alert" style="color: red;visibility: hidden">请先添加教练</span>
                        </p>
                        <p style="margin-top:29px;"><span class="muted">教练编号:<span id="coach_id" class="muted"style="color:black"></span></span></p>
                        <hr style="margin:0 0 9px 0;">
                        <a id="add_coach" href="#myModal1" role="button" class="btn3 btn3-primary" data-toggle="modal"style="text-decoration: none">添加教练</a>
                        <!-- <button type="button" class="btn btn-default btn-info ">添加教练</button> -->
                    </td>
                </tr>
            </table>
        </div>
        <div class="pull-right" style="width:98%;">
            <table class="table table-bordered  messageCard" >
                <tr class="main-su">
                    <td style="color: #5f5f5f;">球员信息</td>
                </tr>
                <tr>
                    <td>
                        <div id="chooesPlayerInfos">

                        </div>
                        <span id="chooesPlayerInfos_alert" style="color: red;visibility: hidden">请先点击添加球员按钮</span>

                        <hr style="clear:both;margin-left:38px;">
                        <%--<a role="button" href="#myModal2" class="btn btn-info" data-toggle="modal" style="margin-left:38px;">添加球员</a>--%>
                        <%--<button id="getAllPlayers" type="button" class="btn btn-default btn-info" style="margin-left:38px;">添加球员</button>--%>
                        <a id="getAllPlayers" href="#myModal2" role="button" class="btn3 btn3-primary" data-toggle="modal" style="text-decoration: none;margin-left: 35px">添加球员</a>
                    </td>
                </tr>
            </table>

            <center>
                <button type="button" class="btn3 btn3-success btn3-large " id="button_team">提交</button>
                <button type="button" id="back_team" class="btn3 btn3-default btn3-large">取消</button>
            </center>
        </div>
    </div>



</div>
<!-- first Modal -->
<div id="myModal1" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3 id="myModalLabel">添加教练</h3>
    </div>
    <div class="modal-body"><!--模态中部开始-->
        <div class="row">
            <div class="span3">
                <div class="input-group">
                    <input id="coachName" name="coachName" type="text" class="form-control"  placeholder="请输入教练姓名">
                    <span id="coachName_alert" style="color: red;visibility: hidden">请输入教练的名字</span>
                </div>
                <!-- /input-group -->
            </div>
            <div class="span3">
				<span class="input-group-btn">
					<button id="button_coach" class="btn3 btn3-success " type="button" style="margin-left: -10px;margin-top: -3px;">搜索</button>
				</span>
                <!-- /input-group -->
            </div>
            <!-- /.col-lg-6 -->
        </div>


        <table class="table  table-hover" id="table_list" style="margin-top:-20px;">
            <thead class="info " style="color:#666;border-bottom: 2px solid #dddddd">
                <th >姓名</th>
                <th >性别</th>
                <th >编号</th>
                <th >操作</th>
            </thead>
            <tbody id="coach_table"></tbody>
        </table>
    </div><!--模态中部结束-->
    <div class="modal-footer">
        <button class="btn3 btn3-default " data-dismiss="modal" aria-hidden="true">关闭</button>
        <button id="sure_coach" class="btn3 btn3-success "  data-dismiss="modal" aria-hidden="true">确定</button>
    </div>
</div>
<!-- first model end -->
<!-- 模态框2Modal -->
<div id="myModal2" class="modal hide fade"style="width:1002px;margin-left:-500px;" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3 id="myModalLabel">添加球员</h3>
    </div>
    <div class="modal-body"><!--模态框中部开始-->
        <div class="row">
            <div class="span3" >
                <div class="input-group">
                    <select id="selectGrade" class="form-control" style="">
                        <option value="1">小学一年级</option>
                        <option value="2">小学二年级</option>
                        <option value="3">小学三年级</option>
                        <option value="4">小学四年级</option>
                        <option value="5">小学五年级</option>
                        <option value="6">小学六年级</option>
                        <option value="7">初中一年级</option>
                        <option value="8">初中二年级</option>
                        <option value="9">初中三年级</option>
                    </select>
                </div>
                <!-- /input-group -->
            </div>
            <div class="span3">
                <div class="input-group">
                    <!--<input type="text" placeholder="Text input">-->
                    <input id="playerClazz" type="text" class="form-control"  placeholder="请输入球员班级">
                </div>
                <!-- /input-group -->
            </div>
            <div class="span3">
                <div class="input-group">
                    <input id="playerName" type="text" class="form-control"  placeholder="请输入球员姓名">
                </div>
                <!-- /input-group -->
            </div>
            <div class="span3">
                <span class="input-group-btn">
                  <button id="player_btn" class="btn3 btn3-success" type="button" style="margin-top: -3px">搜索</button>
                </span>
                        <!-- /input-group -->
            </div>

            <!-- /.col-lg-6 -->
        </div>


        <table class="table table-hover"id="table_list" style="margin-top:15px;">
            <thead class="info" style="color:#666;border-bottom: 2px solid #dddddd">
                <th>姓名</th>
                <th>编号</th>
                <th>性别</th>
                <th>班级</th>
                <th>身份证号</th>
                <th><input type="checkbox" id="all" style="width:20px;height:20px;margin-bottom:-2px;margin-left: 20px">&nbsp;全选</th>
            </thead>
            <tbody id="player_table"></tbody>

        </table>

    </div><!--模态框中部结束-->
    <div class="modal-footer pagination">
        <center>
            <ul id="ulList" style="cursor: pointer;float:left">
            </ul>
            <button onclick="add()"  class="btn3 btn3-success pull-right" data-dismiss="modal" aria-hidden="true">确定</button>
            <button class="btn3 btn3-default pull-right"style="margin-right: 10px;" data-dismiss="modal" aria-hidden="true">关闭</button>

        </center>


        <%--id="addPlayer_btn"--%>
    </div>
</div>
<!-- 模态框2Modal结束 -->
<!--container end-->
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="${base_path}/resources/lib/jquery/jquery-3.1.1.min.js "></script>
<script src="${base_path}/resources/lib/jquery/jquery-1.11.3.js "></script>
<script src="${base_path}/resources/lib/layer/layer.js "></script>
<!-- <script src="js/jquery-3.1.1.min.js"></script> -->
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="${base_path}/resources/lib/bootstrap.min.js"></script>
<script src="${path}/resources/js/index.js"></script>
<!-- 提交按钮触发 -->



<script>
    $(function(){
        //选择年级的时候就显示球员
           pg = 1;
        $("#getAllPlayers").click(function () {
            /*$("#playerClazz").val("");
            $("#playerName").val("");*/
            var all = $("#all");
            all.removeAttr("checked");
            pg = 1;
            laodIfPlayer(pg);
        });

        $("#selectGrade").change(function () {
            var a=$("#all");
            a.removeAttr("checked");
            $("#playerClazz").val("");
            $("#playerName").val("");
            pg = 1 ;
            laodIfPlayer(pg);
        });

        function laodIfPlayer(pg) {
            var $all = $("#all");
            $all.removeAttr("checked");
            $("#ulList").text("");
            var token = $('#token').html();
            var selectGrade = $("#selectGrade").val();//年级信息
            ajax({
                type: "POST",
                url: "${base_path}/team/findPlayersByconditions",
                dataType: "json",
                data: {"grade": selectGrade,"token": $('#token').html(),"page":pg},
                success: function (result) {
                    if(result.status==1){
                        $("#player_table").html("");
                        $("#playerId").val("");
                    }else if(result.status==0){
                        var  $ulList =   $("#ulList");


                        var  $li2 = $("<li></li>");
                        var  $a2 = $('<a id="ifpageOne" ></a>');
                        $a2.append( "首页");
                        $li2.append($a2);

                        var  $li3 = $("<li></li>");
                        var  $a3 = $('<a id="ifpagePre">&laquo;</a>');
                        $li3.append($a3);
                        if (result.data.pageInfo.hasPreviousPage == false){
                            $li2.addClass("disabled");
                            $li3.addClass("disabled");
                        }else {
                            $li2.click(function () {
                                laodIfPlayer(1);
                            });
                            $li3.click(function () {
                                laodIfPlayer(result.data.pageInfo.pageNum-1);
                            });
                        };

                        var  $li4 = $("<li></li>");
                        var  $a4 = $('<a id="ifpageNext">&raquo;</a>');
                        $li4.append($a4);

                        var  $li5 = $("<li></li>");
                        var  $a5 = $('<a id="ifpageLast"></a>');
                        $a5.append( "尾页");
                        $li5.append($a5);
                        if (result.data.pageInfo.hasNextPage == false){
                            $li4.addClass("disabled");
                            $li5.addClass("disabled");
                        }else {
                            $li4.click(function () {
                                laodIfPlayer(result.data.pageInfo.pageNum+1);
                            });
                            $li5.click(function () {
                                laodIfPlayer(result.data.pageInfo.pages);
                            })
                        };

                        //$ulList.append($li1);
                        $ulList.append($li2);
                        $ulList.append($li3);
                        jQuery.each(result.data.pageInfo.navigatepageNums,function (index,item) {
                            var  $li6 = $("<li></li>");
                            var  $a6 = $('<a id="page6" ></a>');
                            if (result.data.pageInfo.pageNum == item){
                                $li6.attr("class","active");
                            }
                            $a6.click(function () {
                                laodIfPlayer(item);
                            });
                            $a6.append(item);
                            $li6.append($a6);
                            $ulList.append($li6);
                        });
                        $ulList.append($li4);
                        $ulList.append($li5);

                        var players = result.data.players;
                        $("#player_table").html("");
                        $("#playerId").val("");

                        jQuery.each(result.data.players, function(i,item){
                            var player_sex = item.sex;
                            if (player_sex==0){
                                player_sex='女';
                            }else{
                                player_sex='男';
                            }
                            $("#player_table").append("<tr>" +
                                "<td style='width:150px;'>"+item.name+"</td>" +
                                "<td>"+item.idPlayer+"</td>" +
                                "<td>"+player_sex+"</td>" +
                                "<td>"+item.classes+"</td>" +
                                "<td>"+item.idCard+"</td>" +
                                "<td><input id='select_playerid' name='select_player'style='width:20px;height:20px;' type='checkbox' value='"+item.idPlayer+"'/>&nbsp;&nbsp;&nbsp;</td>" +
                                "</tr>");
                        });
                        var product3 = document.getElementsByName("select_player");
                        var all1 = document.getElementById("all");
                        var  flag = 0;
                        for (var  i = 0 ;i<product3.length;i++){
                            product3[i].onclick = function () {
                                if (this.checked){
                                    flag++;
                                }else {
                                    flag --;
                                }
                                if (flag == product3.length){
                                    all1.checked = "checked";
                                }else {
                                    all1.checked = false;
                                }

                            }
                        }
                    }
                }

            })
        }




        //返回主页
        $("#back_team").click(function () {
            window.location.href="${base_path}/team/display"
        })


        //队名这里，失去焦点，判断该队名是否已经存在
        $("#teamName").blur(function(){
            var teamName = $("#teamName").val();//获得球队名称
            var veryfiy=true;//判断位
            if(teamName.length == 0){
                veryfiy=false;
                $("#teamName_alert").css("visibility","visible");
                veryfiy=false;
            }else {
                $("#teamName_alert").css("visibility","hidden");
            }
            if (veryfiy){
                $.ajax({
                    type: "POST",
                    url: "${base_path}/team/findTeamName",
                    dataType: "json",
                    data: {"teamName": teamName},
                    success: function (data) {
                        if (data != null){
                            //说明队名存在，应该提示他
                            $("#teamName_duplication").css("visibility","visible");
                        }else{
                            //说明队名不存在，可以使用
                            $("#teamName_duplication").css("visibility","hidden");
                        }
                    }
                })
            }
        })

        $("#add_coach").click(function () {

            var token = $('#token').html();
            ajax({
                type: "POST",
                url: "${base_path}/team/findAllCoaches",
                dataType: "json",
                data: {token: $('#token').html()},
                success: function (result) {
                    if (result.status == 0) {
//                    $("#coach_table").val("");
                        $("#coach_table").html("");
                        jQuery.each(result.data.coachInfos, function (i, item) {
                            var coach_sex = item.sex;
                            if (coach_sex == 0) {
                                coach_sex = '女';
                            } else {
                                coach_sex = '男';
                            }
                            $("#coach_table").append("<tr>" +
                                "<td>" + item.coachName + "</td>" +
                                "<td>" + coach_sex + "</td>" +
                                "<td>" + item.idCoach + "</td>" +
                                "<td><input id='select_coachid'style='width:20px;height:20px;' name='select_coach' type='radio' value='" + item.idCoach + "'/></td>" +
                                "</tr>");
                        });
                    }
                }

            })
        });

        $("#playerName_btn").click(function () {
            var token = $('#token').html();
            var playerId = $("#playerId").val();//球员ID
            var veryfiy=true;
            if(playerId.length == 0){
                veryfiy=false;
                $("#playerId_alert").css("visibility","visible");
                veryfiy=false;
            }else {
                $("#playerId_alert").css("visibility","hidden");
            }
            if (veryfiy){
                ajax({
                    type: "POST",
                    url: "${base_path}/team/findPlayerByPlayerId",
                    dataType: "json",
                    data: {"idPlayer": playerId,token: $('#token').html()},
                    success: function (result) {
                        if (result.status == 0){
                            $("#player_table").html("");
//                        alert(data);
                            var player_sex = result.data.player.sex;
                            if (player_sex==0){
                                player_sex='男';
                            }else{
                                player_sex='女';
                            }
//                        当通过ID来搜索球员的时候
                            if(player_sex!=""){
                                $("#selectGrade").val("");//设置年级信息为空
                                $("#playerClazz").val("");//设置班级为空
                                $("#playerName").val("");//设置球员姓名为空
                            }
                            $("#player_table").append("<tr>" +
                                "<td>"+result.data.player.name+"</td>" +
                                "<td>"+result.data.player.idPlayer+"</td>" +
                                "<td>"+player_sex+"</td>" +
                                "<td>"+result.data.player.classes+"</td>" +
                                "<td>"+result.data.player.idCard+"</td>" +
                                "<td><input id='select_playerid'style='width:20px;height:20px;'class='select' name='select_player' type='checkbox' value='"+result.data.player.idPlayer+"'/></td>" +
                                "</tr>");
                        }

                    }

                })
            }

        });

        $("#player_btn").click(function () {
            //$("#ulList").text("");
            var all = $("#all");
            all.removeAttr("checked");
            pn = 1;
            laoda(pn)

        });


        function laoda(pn){
            var $all2 = $("#all");
            $all2.removeAttr("checked");
            $("#ulList").empty();
            $("#player_table").empty();
            var token = $('#token').html();
            var selectGrade = $("#selectGrade").val();//年级信息
            var playerClazz = $("#playerClazz").val();//班级
            var playerName = $("#playerName").val();//球员姓名
            ajax({
                type: "POST",
                url: "${base_path}/team/findPlayersByconditions",
                dataType: "json",
                data: {"grade": selectGrade,"classes":playerClazz,"name":playerName,token: $('#token').html(),"page":pn},
                success: function (result) {
                    if (result.status ==0) {
                        var $ulList = $("#ulList");

                        var $li2 = $("<li></li>");
                        var $a2 = $('<a id="pageOne" ></a>');
                        $a2.append("首页");
                        $li2.append($a2);

                        var $li3 = $("<li></li>");
                        var $a3 = $('<a id="pagePre">&laquo;</a>');
                        $li3.append($a3);
                        if (result.data.pageInfo.hasPreviousPage == false){
                            $li2.addClass("disabled");
                            $li3.addClass("disabled");
                        }else {
                            $li2.click(function () {
                                laoda(1);
                            });
                            $li3.click(function () {
                                laoda(result.data.pageInfo.pageNum-1);
                            });
                        }

                        var $li4 = $("<li></li>");
                        var $a4 = $('<a id="pageNext">&raquo;</a>');
                        $li4.append($a4);

                        var $li5 = $("<li></li>");
                        var $a5 = $('<a id="pageLast"></a>');
                        $a5.append("尾页");
                        $li5.append($a5);
                        if (result.data.pageInfo.hasNextPage == false){
                            $li4.addClass("disabled");
                            $li5.addClass("disabled");
                        }else {
                            $li4.click(function () {
                                laoda(result.data.pageInfo.pageNum+1);
                            });
                            $li5.click(function () {
                                laoda(result.data.pageInfo.pages);
                            })
                        }

                        //$ulList.append($li1);
                        $ulList.append($li2);
                        $ulList.append($li3);
                        jQuery.each(result.data.pageInfo.navigatepageNums,function (index,item) {
                            var  $li6 = $("<li></li>");
                            var  $a6 = $('<a id="page7" ></a>');
                            if (result.data.pageInfo.pageNum == item){
                                $li6.attr("class","active");
                            }
                            $a6.click(function () {
                                laoda(item);
                            });
                            $a6.append(item);
                            $li6.append($a6);
                            $ulList.append($li6);
                        });
                        $ulList.append($li4);
                        $ulList.append($li5);


                        $("#player_table").html("");
                        $("#playerId").val("");
                        jQuery.each(result.data.players, function (i, item) {
                            var player_sex = item.sex;
                            if (player_sex == 0) {
                                player_sex = '女';
                            } else {
                                player_sex = '男';
                            }
                            $("#player_table").append("<tr>" +
                                "<td>" + item.name + "</td>" +
                                "<td>" + item.idPlayer + "</td>" +
                                "<td>" + player_sex + "</td>" +
                                "<td>" + item.classes + "</td>" +
                                "<td>" + item.idCard + "</td>" +
                                "<td><input id='select_playerid'style='width:20px;height:20px;' name='select_player' type='checkbox' value='" + item.idPlayer + "'/>&nbsp;&nbsp;&nbsp;</td>" +
                                "</tr>");
                        });

                        var product4 = document.getElementsByName("select_player");
                        var all4 = document.getElementById("all");
                        var  flag = 0;
                        for (var  i = 0 ;i<product4.length;i++){
                            product4[i].onclick = function () {
                                if (this.checked){
                                    flag++;
                                }else {
                                    flag --;
                                }
                                if (flag == product4.length){
                                    all4.checked = "checked";
                                }else {
                                    all4.checked = false;
                                }

                            }
                        }
                    }else if (result.status==1){
                        $("#player_table").empty();
                        $("#player_table").html("");
                        $("#playerId").val("");
                    }
                }

            })
        }

        $("#sure_coach").click(function () {
            $("#teamCoachName_alert").css("visibility","hidden");
            var token = $('#token').html();
            var select_coachId=$('input:radio[name="select_coach"]:checked').val();
            var coachInfo_idCoach = select_coachId;
            if (coachInfo_idCoach == null){
                layer.msg("你未选择教练");
            }else {
                ajax({
                    type: "POST",
                    url: "${base_path}/team/findCoachById",
                    dataType: "json",
                    data: {"idCoach": coachInfo_idCoach,"token": $('#token').html()},
                    success: function (result) {
                        if (result.status == 0) {
                            $("#coach_name").html(result.data.coachInfo.name);
                            $("#coach_id").html(result.data.coachInfo.idCoach);
                        }
                    }
                })
            }
        })


        $("#button_coach").click(function () {
            var token = $('#token').html();
            //获得输入的数据
            var coachName  = $("#coachName").val();
            var veryfiy=true;
            if(coachName.length==0){
                $("#coachName_alert").css("visibility","visible");
                veryfiy=false;
            }else {
                $("#coachName_alert").css("visibility","hidden");
            }
            if(veryfiy){
                ajax({
                    type:"POST",
                    url:"${base_path}/team/findCoachByName",
                    dataType:"json",
                    data:{"coachName":coachName,token: $('#token').html()},
                    success:function (result) {
                        if (result.status == 0) {
                            $("#coach_table").html("");
                            jQuery.each(result.data.coachInfos, function (i, item) {
                                var coach_sex = item.sex;
                                if (coach_sex == 0) {
                                    coach_sex = '男';
                                } else {
                                    coach_sex = '女';
                                }
                                $("#coach_table").append("<tr>" +
                                    "<td>" + item.coachName + "</td>" +
                                    "<td>" + coach_sex + "</td>" +
                                    "<td>" + item.idCoach + "</td>" +
                                    "<td><input id='select_coachid'style='width:20px;height:20px;' name='select_coach' type='radio' value='" + item.idCoach + "'/></td>" +
                                    "</tr>");
                            });
                        }
                    }
                });
            }
            $("#coachName").val("");
        });

        $("#button_team").click(function () {
            var token = $('#token').html();

            //获得所有关于球队的信息，队名，教练，球员等...
            var teamName = $("#teamName").val();//获取队名
            var teamClazz = $("#teamClazz").val();//获取班级
            var teamStatus = $('input:radio[name="teamStatus"]:checked').val();//球队状态
            var teamCoachName = $("#coach_name").html();//教练的名字
            var teamCoachId = $("#coach_id").html();//教练的ID
            var teamPlayerInfos = $("#chooesPlayerInfos").html();//球员信息
//            alert(teamPlayerInfos.length);
            //判断输入框内是否为空
            var veryfiy=true;
            if(teamName.length==0){
                $("#teamName_alert").css("visibility","visible");
                veryfiy=false;
            }else {
                $("#teamName_alert").css("visibility","hidden");
            }

            if(teamCoachName.length==0){
                $("#teamCoachName_alert").css("visibility","visible");
                veryfiy=false;
            }else {
                $("#teamCoachName_alert").css("visibility","hidden");
            }

            if(teamPlayerInfos.length==0){
                $("#chooesPlayerInfos_alert").css("visibility","visible");
                veryfiy=false;
            }else {
                $("#chooesPlayerInfos_alert").css("visibility","hidden");
            }
//            $("#playerId").val();
            if(veryfiy){
                ajax({
                    type:"POST",
                    url:"${base_path}/team/addTeamInfo",
                    dataType:"json",
//                contentType:"application/json",
                    data:{"teamName":teamName,"teamClazz":teamClazz,"teamStatus":teamStatus,"teamCoachId":teamCoachId,token: $('#token').html()},
                    success:function (result) {
                        switch (result.status) {
                            case 0:
                                msg('添加成功！');
                                window.location.href="${base_path}/team/display"
                                break;
                            case 1:
                                msg('添加失败，请重试');
                                break;
                        }
                    }
                });
            }

        });

    });
</script>

<script type="text/javascript">

        all.onclick = function () {
            var all = document.getElementById("all");
            var product = document.getElementsByName("select_player");
            var ll= product.length;
            for(var i=ll;i--;){
                product[i].checked = all.checked;
            }
            for(var i=ll;i--;){
                product[i].onclick = function(){
                    var k = 0;
                    for(var i=ll;i--;)product[i].checked && k++;
                    all.checked = ll==k;
                };
            }

        }



</script>
<script>
    function add() {
        var token = $('#token').html();
        var flag = window.checkFlash;
        console.log(typeof(flag));
        var arr = [];
        $("input[type='checkbox']:checked").each(function () {
            arr.push($(this).val());
            typeof (toString(arr));
        });

        ajax({
            type: "POST",
            url: "${base_path}/team/getAllPlayersByIds",
            dataType: "json",
            data: {"playerids": JSON.stringify(arr),"flag":flag,token: $('#token').html()},

            success: function (result) {
                <fmt:bundle basename="properties.upload">
                if (result.status == 0) {
                    jQuery.each(result.data.players, function (i, item) {
                        $("#chooesPlayerInfos").append(
                            " <div id=\"playerInfos\" class=\"zhazhazha\">" +
                            "<img class=\"clo\" src=\"${base_path}/<fmt:message key="upload.image.url.prefix"/>/"+item.headPic+"\" style=\"width:78px;height:91px;\"/>" +
                            "<a onclick=\"deleteRow($(this)," + item.idPlayer + ");\">" +
                            "<b>x</b>" +
                            "</a>\n" +
                            "<p class=\"muted zhazhahui\">" +
                            "<span>" + item.name + "</span><br><span>" + item.idPlayer + "</span>" +
                            "</p>" +
                            "</div>");
                    })
                    window.checkFlash = 0;
                }
                </fmt:bundle>
            }
        })

    }
</script>
<script>
    //删除指定的球员的信息
    function deleteRow(obj,id){
        var token = $('#token').html();
        var playerid = id;
        if (playerid != null){
            ajax({
                type: "POST",
                url: "${base_path}/team/deletePlayerById",
                dataType:"json",
                data: {"playerid": playerid,"token": $('#token').html()},
                success:function (result) {
                    if (result.status == 0){
                        obj.parent().remove();
                    }
                }
            });
        }

    }

</script>
<script>
    //判断页面是否刷新
    if(window.checkFlash){
        window.checkFlash = 0;
    }else{
        window.checkFlash = 1;
    }
    // 切换用户显示
    function userOne() {
        var a = $('#user2');
        a.css('display',"block");
    }
    function userTwo() {
        var a = $('#user2');
        a.css('display',"none");
    }
    $("[data-toggle='tooltip']").tooltip();
</script>
</body>

</html>
