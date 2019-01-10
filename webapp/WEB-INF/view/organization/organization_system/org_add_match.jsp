<%@ page import="com.nsu.common.Constants" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
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

    <!--[if lt IE 9]>
    <script src="http://ie7-js.googlecode.com/svn/version/2.1(beta4)/IE9.js"></script>
    <![endif]-->
    <link rel="stylesheet" type="text/css" href="${base_path }/resources/common/static/h-ui.admin/css/style.css"/>
    <!--[if IE 6]>
    <script type="text/javascript" src="${base_path }/resources/common/lib/DD_belatedPNG_0.0.8a-min.js"></script>
    <script>DD_belatedPNG.fix('*');</script>
    <![endif]-->
    <link rel="stylesheet" href="${base_path }/resources/common/lib/jQuery.cxCalendar-1.5.3/css/jquery.cxcalendar.css">
    <title>发布赛事</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 机构管理 <span class="c-gray en">&gt;</span> 发布赛事 <a
        class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px"
        href="javascript:location.replace(location.href='${base_path }/org/to_create_match.html');" title="刷新"><i
        class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
    <div class="mt-20 mb-20"><br></div>
    <div class="mt-20">
        <form class="form form-horizontal" id="add-form" method="post" action="${base_path }/org/create_match.action">
            <div class="row cl mt-20">
                <label class="form-label col-xs-4 col-sm-2 ">赛事名称:</label>
                <div class="formControls col-xs-8 col-sm-9">
                    <input type="text" class="input-text radius" name="match_name" value="" id="match_name"/>
                </div>

            </div>
            <div class="row cl mt-20">
                <label class="form-label col-xs-4 col-sm-2 ">赛制:</label>
                <div class="formControls col-xs-8 col-sm-4">
                   <span class="select-box">
                        <select class="select" size="1" name="person_num">
                        	<option value="personNum_no" selected="selected">不限</option>
                            <option value="personNum_five">5人</option>
                            <option value="personNum_seven">7人</option>
                            <option value="personNum_eleven">11人</option>
                        </select>
                   </span>
                </div>
                <label class="form-label col-xs-4 col-sm-1 ">性别:</label>
                <div class="formControls col-xs-8 col-sm-4">
                   <span class="select-box">
                        <select class="select" size="1" name="person_sex">
                        	<option value="personSex_no" selected="selected">不限</option>
                            <option value="personSex_male">男子比赛</option>
                            <option value="personSex_female">女子比赛</option>
                        </select>
                   </span>
                </div>

            </div>
            <div class="row cl mt-20">
                <label class="form-label col-xs-4 col-sm-2 ">级别:</label>
                <div class="formControls col-xs-8 col-sm-4">
                    <span class="select-box">
                        <select class="select" size="1" name="match_big_level">
                            <option value="matchBigLevel_no" selected="selected">不限</option>
                            <option value="matchBigLevel_province">省级</option>
                            <option value="matchBigLevel_city">市(州)级</option>
                            <option value="matchBigLevel_county">县(市、区)级</option>
                            <option value="matchBigLevel_school">学校级</option>
                        </select>
                   </span>
                </div>
                <label class="form-label col-xs-4 col-sm-1 ">组别:</label>
                <div class="formControls col-xs-8 col-sm-4">
                   <span class="select-box">
                        <select class="select" size="1" name="match_level">
                            <option value="matchLevel_no" selected="selected">不限</option>
                            <option value="matchLevel_primarySchool">小学</option>
                            <option value="matchLevel_juniorSchool">初中</option>
                            <option value="matchLevel_highSchool">高中</option>
                            <option value="matchLevel_university">大学</option>
                        </select>
                   </span>
                </div>
            </div>
            <div class="row cl mt-20">
                <label class="form-label col-xs-4 col-sm-2 " for="date_a1">报名开始时间:</label>
                <div class="formControls col-xs-8 col-sm-4">
                    <input type="text" class="input-text" id="date_a1" name="enroll_startDate" readonly>
                </div>
                <label class="form-label col-xs-4 col-sm-1" for="date_b1">报名结束时间:</label>
                <div class="formControls col-xs-8 col-sm-4">
                    <input type="text" class="input-text" id="date_b1" name="enroll_endDate" rea donly>
                </div>
            </div>

            <div class="row cl mt-20">
                <label class="form-label col-xs-4 col-sm-2 " for="date_a">赛事开始时间:</label>
                <div class="formControls col-xs-8 col-sm-4">
                    <input type="text" class="input-text" id="date_a" name="match_startDate" readonly>
                </div>
                <label class="form-label col-xs-4 col-sm-1 " for="date_b">赛事结束时间:</label>
                <div class="formControls col-xs-8 col-sm-4">
                    <input type="text" class="input-text" id="date_b" name="match_endDate" readonly>
                </div>
            </div>

            <div class="row cl mt-20">
                <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
                    <input class="btn btn-primary radius" type="button" value="&nbsp;&nbsp;发布&nbsp;&nbsp;"
                           onclick="addmatch()">
                </div>
            </div>
            <input type="text" name="_csrf" value="${_csrf.token}" style="display: none;">
        </form>
    </div>
</div>

<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="${base_path }/resources/common/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="${base_path }/resources/common/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="${base_path }/resources/common/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="${base_path }/resources/common/static/h-ui.admin/js/H-ui.admin.js"></script>
<script type="text/javascript"
        src="${base_path }/resources/common/lib/jquery.contextmenu/jquery.contextmenu.r2.js"></script>
<!--/_footer 作为公共模版分离出去-->
<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript"
        src="${base_path }/resources/common/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${base_path }/resources/common/lib/laypage/1.2/laypage.js"></script>
<script src="${base_path }/resources/common/lib/jQuery.cxCalendar-1.5.3/js/jquery.cxcalendar.min.js"></script>
<script src="${base_path }/resources/common/lib/jQuery.cxCalendar-1.5.3/js/jquery.cxcalendar.languages.js"></script>
<script type="text/javascript">
    $(function () {
        layer.ready(function () {
            if ('${createMatch_msg}' != '') {
                layer.msg('${createMatch_msg}');
            }
        });
    });
    $('#date_a,#date_a1').cxCalendar({
        type: 'datetime',
        format: 'YYYY-MM-DD HH:mm:ss'
    });
    $('#date_b,#date_b1').cxCalendar({
        type: 'datetime',
        format: 'YYYY-MM-DD HH:mm:ss'
    });

</script>
<script type="text/javascript">
    $("#date_a1").change(function () {//报名开始时间
        var time = new Date();
        // 程序计时的月从0开始取值后+1
        var m = time.getMonth() + 1;
        var startTime = time.getFullYear() + "-" + m + "-"
            + time.getDate() + " " + time.getHours() + ":"
            + time.getMinutes() + ":" + time.getSeconds();
        var endTime = document.getElementById("date_a1").value;
        startTime = Date.parse(new Date(startTime));
        endTime = Date.parse(new Date(endTime));
        if (startTime >= endTime) {
            layer.msg("报名开始时间不能小于当前时间");
            $(".cxcalendar").css("display", "none");
            $(this).val('');
        }
        if ($('#date_b1').val() !== '') {//报名结束时间不为空
            if (endTime >= Date.parse($('#date_b1').val())) {//开始时间晚于（大于）结束时间
                layer.msg('报名开始时间不能晚于报名结束时间');
                $('.cxcalendar').hide();
                $(this).val('');
            }
        }
    })
    $("#date_b1").change(function () {//报名结束时间
        if ($("#date_a1").val() === "") {
            layer.msg("请先填写报名开始时间");
            $("#date_b1").val("");
        }
        var startTime = document.getElementById("date_a1").value;
        var endTime = document.getElementById("date_b1").value;
        startTime = Date.parse(new Date(startTime));
        endTime = Date.parse(new Date(endTime));
        if (startTime >= endTime) {
            layer.msg("报名开始时间不能晚于于结束报名时间");
            $(".cxcalendar").css("display", "none");
            $("#date_b1").val('');
        }
//        if ($('#date_a1').val() !== '') {//报名开始时间不为空
//            if (endTime <= Date.parse($('#date_a1').val())) {//报名结束时间早于（小于）报名开始时间
//                layer.msg('')
//            }
//        }
    })
    $("#date_a").change(function () {//比赛开始时间
        if ($("#date_b1").val() === "") {
            layer.msg("请先填写报名结束时间");
            $("#date_a").val("");
        }
        var startTime = document.getElementById("date_b1").value;
        var endTime = document.getElementById("date_a").value;
        startTime = Date.parse(new Date(startTime));
        endTime = Date.parse(new Date(endTime));
        if (startTime >= endTime) {
            layer.msg("报名结束时间不能晚于赛事开始时间");
            $(".cxcalendar").css("display", "none");
            $("#date_a").val('');
        } else {

        }
        if ($('#date_b').val() !== '') {//比赛结束时间不为空
            if (endTime >= Date.parse($('#date_b').val())) {//比赛开始时间晚于（大于）比赛结束时间
                layer.msg("赛事开始时间不能晚于赛事结束时间");
                $(".cxcalendar").css("display", "none");
                $("#date_a").val('');
            } else if (((end - Date.parse($('#date_b').val())) / 86400000) < 1) {
                layer.msg("赛事开始时间与赛事结束时间间隔不能小于一天");
                $(".cxcalendar").css("display", "none");
                $("#date_a").val('');
            }
        }

    })
    $("#date_b").change(function () {//比赛结束时间
        if ($("#date_a").val() == "") {
            layer.msg("请先填写赛事开始时间");
            $("#date_b").val("");
        }
        var startTime = document.getElementById("date_a").value;
        var endTime = document.getElementById("date_b").value;
        var start = (new Date(startTime)).getTime();
        var end = (new Date(endTime)).getTime();
        startTime = Date.parse(new Date(startTime));
        endTime = Date.parse(new Date(endTime));

        if (startTime >= endTime) {
            layer.msg("赛事开始时间不能晚于赛事结束时间");
            $(".cxcalendar").css("display", "none");
            $("#date_b").val('');
        } else if (((end - start) / 86400000) < 1) {
            layer.msg("赛事开始时间与赛事结束时间间隔不能小于一天");
            $(".cxcalendar").css("display", "none");
            $("#date_b").val('');
        } else {

        }
    })
    $("#match_name").blur(function () {
        if ($("#match_name").val() == "") {
            layer.msg("赛事名字不能为空")
        } else if ($(this).val().length > 40) {
            layer.msg('赛事名称不能超过40个字符');
            $(this).val($(this).val().substring(0, 40));
        }
    })
    function addmatch() {
        if ($("#match_name").val() == "") {
            layer.msg("赛事名称不能为空");
            return;
        }
        if ($("#match_name").val().length > 40) {
            layer.msg('赛事名称不能超过40个字符');
            $(this).val($(this).val().substring(0, 40));
            return;
        }
        if ($("#date_a1").val() == "") {
            layer.msg("报名开始时间不能为空");
            return;
        }
        if ($("#date_b1").val() == "") {
            layer.msg("报名结束时间不能为空");
            return;
        }
        if ($("#date_a").val() == "") {
            layer.msg("赛事开始时间不能为空");
            return;
        }
        if ($("#date_b").val() == "") {
            layer.msg("赛事结束时间不能为空");
            return;
        }
        $("#add-form").submit();
    }
</script>
</body>
</html>