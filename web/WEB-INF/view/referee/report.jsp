<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: inkbox
  Date: 18-3-21
  Time: 下午3:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1.0, user-scalable=0">
    <title>裁判报告书</title>
    <link rel="stylesheet" type="text/css" href="${path}/resources/lib/bootstrap/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="${path}/resources/css/referee/public.css"/>
    <link rel="stylesheet" type="text/css" href="${path}/resources/css/referee/report.css"/>
    <link rel="stylesheet" type="text/css" href="${path}/resources/css/referee/lCalendar.css"/>
</head>

<body>

<div id="box">
    <!--顶部标题-->
    <div id="header">
        <jsp:include page="../common/head.jsp?v=2&title=裁判报告书"/>
    </div>

    <!--直播内容-->
    <div id="main">
        <div class="container body-height">
            <!--赛事内容-->
            <div class="row margin-top1">
                <div class="col-xs-12">
                    <span class="font-size1">赛事内容</span>
                </div>
            </div>

            <!--比赛名称-->
            <div class="row margin-top2">
                <div class="col-xs-4" style="padding-right: 0">
                    <span class="font-size2 color1">赛事名称：</span>
                </div>
                <span class="color2 font-size3">${match.name}</span>
            </div>

            <!--比赛队伍-->
            <div class="row margin-top2">
                <div class="col-xs-9 col-xs-offset-3">
                    <div class="row text-center ">
                        <div class="col-xs-6">
                            <span class="color1 font-size2">主队</span>
                        </div>
                        <div class="col-xs-6">
                            <span class="color1 font-size2">客队</span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row margin-top2">
                <div class="col-xs-4" style="padding-right: 0">
                    <span class="color1 font-size2">参赛队伍：</span>
                </div>
                <div class="col-xs-8">
                    <div class="row">
                        <div class="col-xs-5 none-padding">
                            <span class="color2 font-size3">${schedule.teamA.name}</span>
                        </div>
                        <div class="col-xs-6 none-padding text-right">
                            <span class="color2 font-size3">${schedule.teamB.name}</span>
                        </div>
                    </div>
                </div>
            </div>

            <!--比赛时间-->
            <div class="row margin-top2">
                <div class="col-xs-5" style="padding-right: 0">
                    <span class="color1 font-size2">比赛开始时间：</span>
                </div>
                <span class="color2 font-size3"><fmt:formatDate
                        value="${schedule.scheduleInformation.beginTime}" pattern="yyyy/MM/dd HH:mm"/></span>
            </div>

            <!--比赛情况-->
            <div class="row margin-top1">
                <div class="col-xs-12">
                    <span class="font-size1">比赛情况</span>
                </div>
            </div>

            <!--常规赛比分-->
            <div class="row margin-top3">
                <div class="col-xs-12">
                    <span class="color1 font-size2">常规赛比分</span>
                </div>
            </div>
            <div class="row">
                <div class="col-xs-8">
                    <div class="row">
                        <span class="color2 font-size3 margin-left1">${schedule.teamA.name}</span>
                        <img src="${path}/resources/img/referee/vs.png" class="img-style1" style="margin-left: 6px;"/>
                        <span class="color2 font-size3" style="margin-left: -4px;">${schedule.teamB.name}</span>
                    </div>
                </div>
                <div class="col-xs-1 text-center none-padding margin-left3 font-size3">${schedule.scheduleInformation.goalA}</div>
                <div class="col-xs-1 text-center none-padding font-size3 font-weight">:</div>
                <div class="col-xs-1 text-center none-padding font-size3">${schedule.scheduleInformation.goalB}</div>
            </div>

            <!--获胜队-->
            <div class="row margin-top3">
                <div class="col-xs-3" style="padding-right: 0px;">
                    <span class="color1 font-size2">获胜队</span>
                </div>
                <span class="font-size3">
                    <c:choose>
                        <c:when test="${schedule.scheduleInformation.goalA>schedule.scheduleInformation.goalB}">
                            ${schedule.teamA.name}
                        </c:when>
                        <c:when test="${schedule.scheduleInformation.goalA<schedule.scheduleInformation.goalB}">
                            ${schedule.teamB.name}
                        </c:when>
                        <c:otherwise>平局</c:otherwise>
                    </c:choose>
                </span>
            </div>

            <!--参赛队员名单-->
            <div class="row margin-top1">
                <div class="col-xs-12">
                    <span class="font-size1">首发队员名单</span>
                </div>
            </div>
            <!--主队-->
            <div class="row margin-top3">
                <div class="col-xs-12"><span class="font-size3 font-weight">${schedule.teamA.name}</span></div>
            </div>
            <table class="table" id="firstMaster">
                <thead>
                <tr>
                    <th>队员姓名</th>
                    <th>位置</th>
                    <th>号码</th>
                    <th>
                        <input type="button" class="btn btn-success btn-xs center-block tr-add" style="outline: none;"
                               value="添加"/>
                    </th>
                </tr>
                </thead>
                <tbody>

                </tbody>
            </table>
            <!--客队-->
            <div class="row">
                <div class="col-xs-12"><span class="font-size3 font-weight">${schedule.teamB.name}</span></div>
            </div>
            <table class="table" id="firstServant">
                <thead>
                <tr>
                    <th>队员姓名</th>
                    <th>位置</th>
                    <th>号码</th>
                    <th>
                        <input type="button" class="btn btn-success btn-xs center-block tr-add" style="outline: none;"
                               value="添加"/>
                    </th>
                </tr>
                </thead>
                <tbody>

                </tbody>
            </table>

            <!--替补队员名单-->
            <div class="row margin-top1">
                <div class="col-xs-12">
                    <span class="font-size1">替补队员名单</span>
                </div>
            </div>
            <!--主队-->
            <div class="row margin-top3">
                <div class="col-xs-12"><span class="font-size3 font-weight">${schedule.teamA.name}</span></div>
            </div>
            <table class="table" id="secMaster">
                <thead>
                <tr>
                    <th>进场队员</th>
                    <th>位置</th>
                    <th>号码</th>
                    <th>时间</th>
                    <th>
                        <input type="button" class="btn btn-success btn-xs center-block tr-add" style="outline: none;"
                               value="添加"/>
                    </th>
                </tr>
                </thead>
                <tbody>

                </tbody>
            </table>
            <!--客队-->
            <div class="row">
                <div class="col-xs-12"><span class="font-size3 font-weight">${schedule.teamB.name}</span></div>
            </div>
            <table class="table" id="secServant">
                <thead>
                <tr>
                    <th>进场队员</th>
                    <th>位置</th>
                    <th>号码</th>
                    <th>时间</th>
                    <th>
                        <input type="button" class="btn btn-success btn-xs center-block tr-add" style="outline: none;"
                               value="添加"/>
                    </th>
                </tr>
                </thead>
                <tbody>

                </tbody>
            </table>


            <!--进球队员-->
            <div class="row margin-top1">
                <div class="col-xs-12">
                    <span class="font-size1">进球队员</span>
                </div>
            </div>
            <!--主队-->
            <div class="row margin-top3">
                <div class="col-xs-12"><span class="font-size3 font-weight">${schedule.teamA.name}</span></div>
            </div>
            <table class="table" id="scoreMaster">
                <thead>
                <tr>
                    <th>队员</th>
                    <th>位置</th>
                    <th>号码</th>
                    <th>时间</th>
                    <th>
                        <input type="button" class="btn btn-success btn-xs center-block tr-add" style="outline: none;"
                               value="添加"/>
                    </th>
                </tr>
                </thead>
                <tbody>

                </tbody>
            </table>
            <!--客队-->
            <div class="row">
                <div class="col-xs-12"><span class="font-size3 font-weight">${schedule.teamB.name}</span></div>
            </div>
            <table class="table" id="scoreServant">
                <thead>
                <tr>
                    <th>队员</th>
                    <th>位置</th>
                    <th>号码</th>
                    <th>时间</th>
                    <th>
                        <input type="button" id="" class="btn btn-success btn-xs center-block tr-add"
                               style="outline: none;" value="添加"/>
                    </th>
                </tr>
                </thead>
                <tbody>

                </tbody>
            </table>

            <!--警告队员名单-->
            <div class="row margin-top1">
                <div class="col-xs-12">
                    <span class="font-size1">警告队员名单</span>
                </div>
            </div>
            <table class="table" id="warning">
                <thead>
                <tr>
                    <th>队伍</th>
                    <th>队员</th>
                    <th>号码</th>
                    <th>时间</th>
                    <th>原因</th>
                    <th>
                        <input type="button" class="btn btn-success btn-xs center-block tr-add" style="outline: none;"
                               value="添加"/>
                    </th>
                </tr>
                </thead>
                <tbody>

                </tbody>
            </table>

            <!--罚令出场名单-->
            <div class="row margin-top1">
                <div class="col-xs-12">
                    <span class="font-size1">罚令出场名单</span>
                </div>
            </div>
            <table class="table" id="punish">
                <thead>
                <tr>
                    <th>队伍</th>
                    <th>队员</th>
                    <th>号码</th>
                    <th>时间</th>
                    <th>原因</th>
                    <th>
                        <input type="button" class="btn btn-success btn-xs center-block tr-add" style="outline: none;"
                               value="添加"/>
                    </th>
                </tr>
                </thead>
                <tbody>

                </tbody>
            </table>

            <!--红牌情况说明-->
            <div class="row margin-top1">
                <div class="col-xs-12">
                    <span class="font-size1">红牌情况说明</span>
                </div>
            </div>
            <div class="row margin-top3">
                <div class="col-xs-12">
                    <textarea class="form-control" rows="3" id="redDes" placeholder="请输入本场比赛红牌情况，不超过100字"></textarea>
                </div>
            </div>

            <!--罚球点球情况说明-->
            <div class="row margin-top1">
                <div class="col-xs-12">
                    <span class="font-size1">罚球点球情况说明</span>
                </div>
            </div>
            <div class="row margin-top3">
                <div class="col-xs-12">
                    <textarea class="form-control" rows="3" id="punishDes"
                              placeholder="请输入本场比赛罚球点球情况，不超过100字"></textarea>
                </div>
            </div>

            <!--严重错漏判情况说明-->
            <div class="row margin-top1">
                <div class="col-xs-12">
                    <span class="font-size1">严重错漏判情况说明</span>
                </div>
            </div>
            <div class="row margin-top3">
                <div class="col-xs-12">
                    <textarea class="form-control" rows="3" id="missDes"
                              placeholder="请输入本场比赛严重错漏判情况，不超过100字"></textarea>
                </div>
            </div>

            <!--底部-->
            <div class="row margin-top1">
                <div class="col-xs-5" style="padding-right: 0px;"><span class="color1 font-size2">裁判员姓名：</span></div>
                <div class="col-xs-6"><span class="none-padding font-size4">${name}</span></div>
            </div>
            <div class="row margin-top1">
                <div class="col-xs-5" style="padding-right: 0px;"><span class="color1 font-size2">裁判员电话：</span></div>
                <div class="col-xs-6"><span class="none-padding font-size4">${phone}</span></div>
            </div>
            <!--按钮-->
            <div>
                <input class="btn btn-success radius center-block margin-top1 input-style1" style="outline: none;"
                       id="submit" type="button" value="提交">
            </div>

            <!--添加页面-->
            <div class="bor-bottom div-style1" id="add-page">
                <div class="container margin-top5">
                    <span class="font-size1">添加消息</span>
                    <div class="row margin-top3">
                        <div class="col-xs-4 text-right none-padding color1">队伍：</div>
                        <div>
                            <select class="bor-bottom margin-top4 select-style2" id="select-team">
                                <option value="${schedule.scheduleInformation.teamA}">${schedule.teamA.name}</option>
                                <option value="${schedule.scheduleInformation.teamB}">${schedule.teamB.name}</option>
                            </select>
                        </div>
                    </div>

                    <div class="row margin-top5">
                        <div class="col-xs-4 text-right none-padding color1">队员姓名：</div>
                        <div>
                            <select class="bor-bottom margin-top4 select-style2" id="select-name">
                                <c:forEach var="item" items="${players}">
                                    <option value="${item.playerId}" team="${item.teamId}">${item.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <div class="row margin-top5">
                        <div class="col-xs-4 text-right none-padding color1">位置：</div>
                        <div>
                            <select class="bor-bottom margin-top4 select-style2" id="select-place">
                                <option value="1">守门员</option>
                                <option value="2">后卫</option>
                                <option value="3">中场</option>
                                <option value="4">前锋</option>
                            </select>
                        </div>
                    </div>

                    <div class="row margin-top5">
                        <div class="col-xs-4 text-right none-padding color1">球衣号码：</div>
                        <div>
                            <input type="number" id="shirt-num" class="bor-bottom margin-top4 select-style2" value=""/>
                        </div>
                    </div>

                    <div class="row margin-top5" id="date">
                        <div class="col-xs-4 text-right none-padding color1">时间：</div>
                        <div>
                            <input id="demo3" type="text" name="input_date"
                                   class="bor-bottom margin-top4 select-style2"/>
                        </div>
                    </div>

                    <div class="row margin-top5" id="reasonRow">
                        <div class="col-xs-4 text-right none-padding color1">原因：</div>
                        <div>
                            <input type="text" id="reason" placeholder="选填" class="bor-bottom margin-top4 select-style2"
                                   value=""/>
                        </div>
                    </div>

                    <div class="row" style="margin-top: 15px;">
                        <div class="col-xs-12 font-color  text-center color3 div-style2" id="prompt" style=" ">信息不完整
                        </div>
                    </div>

                    <!--按钮-->
                    <div class="row margin">
                        <div class="col-xs-4 col-xs-offset-2">
                            <input type="button" class="btn btn-success btn-sm radius center-block width1"
                                   style="outline: none;" id="confim" value="确认"/>
                        </div>
                        <div class="col-xs-4">
                            <input type="button" class="btn btn-danger btn-sm radius center-block width1"
                                   style="outline: none;" id="cancel" value="取消"/>
                        </div>
                    </div>
                </div>

            </div>

        </div>
    </div>

</div>

<script src="${path}/resources/lib/jquery/jquery-3.1.1.min.js"></script>
<script src="${path}/resources/lib/bootstrap/bootstrap.min.js"></script>
<script src="${path}/resources/lib/layer_mobile/layer.js"></script>
<script src="${path}/resources/js/index.js"></script>
<script src="${path}/resources/js/referee/lCalendar.js"></script>
<script src="${path}/resources/js/referee/report.js"></script>
<script type="text/javascript">
    $(function () {
        function transDate(value) {
            if (value === '') {
                return new Date();
            }
            var split = value.split(':');
            var date = new Date();
            date.setHours(parseInt(split[0]));
            date.setMinutes(parseInt(split[1]));
            return date;
        }


        function getPlayDes() {
            var players = [];
            var $trs = $('#firstMaster>tbody>tr');
            //主队出场球员
            for (var i = 0; i < $trs.length; i++) {
                var item = {};
                var $tr = $trs.eq(i);
                item.teamId = $tr.attr('teamId');
                item.playerId = $tr.attr('playerId');
                item.name = $tr.find('td:eq(0)').html().trim();
                item.position = $tr.find('td:eq(1)').html().trim();
                item.number = $tr.find('td:eq(2)').html().trim();
                item.type = 1;
                players.push(item);
            }
            //客队首发球员
            $trs = $('#firstServant>tbody>tr');
            for (var i = 0; i < $trs.length; i++) {
                var item = {};
                var $tr = $trs.eq(i);
                item.teamId = $tr.attr('teamId');
                item.playerId = $tr.attr('playerId');
                item.name = $tr.find('td:eq(0)').html().trim();
                item.position = $tr.find('td:eq(1)').html().trim();
                item.number = $tr.find('td:eq(2)').html().trim();
                item.type = 1;
                players.push(item);
            }

            //主队替补球员
            $trs = $('#secMaster>tbody>tr');
            for (var i = 0; i < $trs.length; i++) {
                var item = {};
                var $tr = $trs.eq(i);
                item.teamId = $tr.attr('teamId');
                item.playerId = $tr.attr('playerId');
                item.name = $tr.find('td:eq(0)').html().trim();
                item.position = $tr.find('td:eq(1)').html().trim();
                item.number = $tr.find('td:eq(2)').html().trim();
                item.date = transDate($tr.find('td:eq(3)').html().trim());
                item.type = 2;
                players.push(item);
            }
            //客队替补球员
            $trs = $('#secServant>tbody>tr');
            for (var i = 0; i < $trs.length; i++) {
                var item = {};
                var $tr = $trs.eq(i);
                item.teamId = $tr.attr('teamId');
                item.playerId = $tr.attr('playerId');
                item.name = $tr.find('td:eq(0)').html().trim();
                item.position = $tr.find('td:eq(1)').html().trim();
                item.number = $tr.find('td:eq(2)').html().trim();
                item.date = transDate($tr.find('td:eq(3)').html().trim());
                item.type = 2;
                players.push(item);
            }
            //主队进球球员
            $trs = $('#scoreMaster>tbody>tr');
            for (var i = 0; i < $trs.length; i++) {
                var item = {};
                var $tr = $trs.eq(i);
                item.teamId = $tr.attr('teamId');
                item.playerId = $tr.attr('playerId');
                item.name = $tr.find('td:eq(0)').html().trim();
                item.position = $tr.find('td:eq(1)').html().trim();
                item.number = $tr.find('td:eq(2)').html().trim();
                item.date = transDate($tr.find('td:eq(3)').html().trim());
                item.type = 3;
                players.push(item);
            }
            //客队进球球员
            $trs = $('#scoreServant>tbody>tr');
            for (var i = 0; i < $trs.length; i++) {
                var item = {};
                var $tr = $trs.eq(i);
                item.teamId = $tr.attr('teamId');
                item.playerId = $tr.attr('playerId');
                item.name = $tr.find('td:eq(0)').html().trim();
                item.position = $tr.find('td:eq(1)').html().trim();
                item.number = $tr.find('td:eq(2)').html().trim();
                item.date = transDate($tr.find('td:eq(3)').html().trim());
                item.type = 3;
                players.push(item);
            }
            //警告球员
            $trs = $('#warning>tbody>tr');
            for (var i = 0; i < $trs.length; i++) {
                var item = {};
                var $tr = $trs.eq(i);
                item.teamId = $tr.attr('teamId');
                item.playerId = $tr.attr('playerId');
                item.name = $tr.find('td:eq(1)').html().trim();
                item.number = $tr.find('td:eq(2)').html().trim();
                item.date = transDate($tr.find('td:eq(3)').html().trim());
                item.other = $tr.find('td:eq(4)').html();
                item.type = 4;
                players.push(item);
            }
            //罚令球员
            $trs = $('#punish>tbody>tr');
            for (var i = 0; i < $trs.length; i++) {
                var item = {};
                var $tr = $trs.eq(i);
                item.teamId = $tr.attr('teamId');
                item.playerId = $tr.attr('playerId');
                item.name = $tr.find('td:eq(1)').html().trim();
                item.number = $tr.find('td:eq(2)').html().trim();
                item.date = transDate($tr.find('td:eq(3)').html().trim());
                item.other = $tr.find('td:eq(4)').html();
                item.type = 5;
                players.push(item);
            }

            return players;
        }


        $('#submit').click(function () {
            var players = getPlayDes();
            console.log(players)
            if (players.length === 0) {
                msg('您没有添加任何一个球员')
            } else {
                layer.open({
                    content: '您确定要提交裁判报告书？'
                    , btn: ['确定', '取消']
                    , yes: function (index) {
                        layer.close(index);
                        //获取
                        ajax({
                            url: '',
                            type: 'post',
                            dataType: 'json',
                            data: {
                                playerJson: JSON.stringify(players),
                                redDes: $('#redDes').val().trim(),
                                punishDes: $('#punishDes').val().trim(),
                                missDes: $('#missDes').val().trim(),
                                token: $('#token').html()
                            },
                            success: function (result) {
                                $('#token').val(result.token);
                                switch (result.status) {
                                    case 0:
                                        msg('提交成功，比赛结束')
                                        $('#token').val('');
                                        break;
                                    case 1:
                                        msg('提交失败，请重试');
                                        break;
                                }

                            }
                        })
                    }
                });
            }
        });
    })
</script>
</body>
</html>




