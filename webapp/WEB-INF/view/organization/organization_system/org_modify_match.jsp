<%@ page import="com.nsu.common.Constants" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
	<meta http-equiv="Cache-Control" content="no-siteapp" />
	<!--[if lt IE 9]>
	<script type="text/javascript" src="${base_path }/resources/common/lib/html5shiv.js"></script>
	<script type="text/javascript" src="${base_path }/resources/common/lib/respond.min.js"></script>
	<![endif]-->
	<link rel="stylesheet" type="text/css" href="${base_path }/resources/common/static/h-ui/css/H-ui.reset.css" />
	<link rel="stylesheet" type="text/css" href="${base_path }/resources/common/static/h-ui/css/H-ui.min.css" />
	<link rel="stylesheet" type="text/css" href="${base_path }/resources/common/static/h-ui.admin/css/H-ui.admin.css" />
	<link rel="stylesheet" type="text/css" href="${base_path }/resources/common/lib/Hui-iconfont/1.0.8/iconfont.css" />
	<link rel="stylesheet" type="text/css" href="${base_path }/resources/common/static/h-ui.admin/skin/green/skin.css" />

	<!--[if lt IE 9]>
	<script src="http://ie7-js.googlecode.com/svn/version/2.1(beta4)/IE9.js"></script>
	<![endif]-->
	<link rel="stylesheet" type="text/css" href="${base_path }/resources/common/static/h-ui.admin/css/style.css" />
	<!--[if IE 6]>
	<script type="text/javascript" src="${base_path }/resources/common/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
	<script>DD_belatedPNG.fix('*');</script>
	<![endif]-->
    <link rel="stylesheet" href="${base_path }/resources/common/lib/jQuery.cxCalendar-1.5.3/css/jquery.cxcalendar.css">
    <title>修改赛事</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 机构管理 <span class="c-gray en">&gt;</span> 修改赛事</nav>
<div class="page-container">
    <div class="mt-20 mb-20"><br></div>
    <div class="mt-20">
        <form class="form form-horizontal" id="modify-form" name="modify-form" method="post" action="${base_path }/org/${matchMap.COM_ID}/submit_modify.action">
            <div class="row cl mt-20">
                <label class="form-label col-xs-4 col-sm-2 ">赛事名称:</label>
                <div class="formControls col-xs-8 col-sm-9">
                	<input type="text" name="old_match_name" value="${matchMap.COM_NAME}" style="display: none;">
                    <input type="text" class="input-text radius" name="match_name" value="${matchMap.COM_NAME}" id="match_name"/>
                </div>

            </div>
            <div class="row cl mt-20">
                <label class="form-label col-xs-4 col-sm-2 ">赛制:</label>
                <div class="formControls col-xs-8 col-sm-4">
                   <span class="select-box">
                        <select class="select" size="1" name="person_num" id="person_num">
                        	<option value="personNum_no">不限</option>
                            <option value="personNum_five">5人</option>
                            <option value="personNum_seven">7人</option>
                            <option value="personNum_eleven">11人</option>
                        </select>
                   </span>
                </div>
                <label class="form-label col-xs-4 col-sm-1 ">性别:</label>
                <div class="formControls col-xs-8 col-sm-4">
                   <span class="select-box">
                        <select class="select" size="1" name="person_sex" id="person_sex">
                        	<option value="personSex_no">不限</option>
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
                        <select class="select" size="1" name="match_big_level" id="match_big_level">
                            <option value="matchBigLevel_no">不限</option>
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
                        <select class="select" size="1" name="match_level" id="match_level">
                            <option value="matchLevel_no">不限</option>
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
                    <input type="text"  class="input-text" id="date_a1" name="enroll_startDate" value="${matchMap.COM_START_TIME}" readonly>
                </div>
                <label class="form-label col-xs-4 col-sm-1" for="date_b1">报名结束时间:</label>
                <div class="formControls col-xs-8 col-sm-4">
                    <input type="text"  class="input-text" id="date_b1" name="enroll_endDate" value="${matchMap.COM_END_TIME}" rea donly>
                </div>
            </div>

            <div class="row cl mt-20">
                <label class="form-label col-xs-4 col-sm-2 " for="date_a">赛事开始时间:</label>
                <div class="formControls col-xs-8 col-sm-4">
                    <input type="text"  class="input-text" id="date_a" name="match_startDate" value="${matchMap.COM_START}" readonly>
                </div>
                <label class="form-label col-xs-4 col-sm-1 " for="date_b">赛事结束时间:</label>
                <div class="formControls col-xs-8 col-sm-4">
                    <input type="text"  class="input-text" id="date_b" name="match_endDate" value="${matchMap.COM_END}" readonly>
                </div>
            </div>

            <div class="row cl mt-20">
                <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
                    <input class="btn btn-primary radius" type="button" onclick="modify()" value="&nbsp;&nbsp;修改&nbsp;&nbsp;">
                    <input class="btn btn-primary radius" type="button" onclick="gobackList()" value="&nbsp;&nbsp;返回&nbsp;&nbsp;">
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
    <script type="text/javascript" src="${base_path }/resources/common/lib/jquery.contextmenu/jquery.contextmenu.r2.js"></script>
<!--/_footer 作为公共模版分离出去-->
<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="${base_path }/resources/common/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${base_path }/resources/common/lib/laypage/1.2/laypage.js"></script>
<script src="${base_path }/resources/common/lib/jQuery.cxCalendar-1.5.3/js/jquery.cxcalendar.min.js"></script>
<script src="${base_path }/resources/common/lib/jQuery.cxCalendar-1.5.3/js/jquery.cxcalendar.languages.js"></script>
<script type="text/javascript">
	$(function() {
        var person_num = document.getElementById("person_num");
        var person_sex = document.getElementById("person_sex");
        var match_big_level = document.getElementById("match_big_level");
        var match_level = document.getElementById("match_level");
        for(var i=0;i<person_num.length;i++){
            if(person_num[i].value == '${matchMap.person_num}'){
                person_num.options[i].selected=true;
            }
        }
        for(var i=0;i<person_sex.length;i++){
            if(person_sex[i].value == '${matchMap.person_sex}'){
                person_sex[i].selected = true;
            }
        }
        for(var i=0;i<match_big_level.length;i++){
            if(match_big_level[i].value == '${matchMap.match_big_level}'){
                match_big_level[i].selected = true;
            }
        }
        for(var i=0;i<match_level.length;i++){
            if(match_level[i].value == '${matchMap.match_level}'){
                match_level[i].selected = true;
            }
        }
		
	});
	$('#date_a,#date_a1').cxCalendar({
	    type: 'datetime',
	    format: 'YYYY-MM-DD HH:mm:ss'
	});
	$('#date_b,#date_b1').cxCalendar({
	    type: 'datetime',
	    format: 'YYYY-MM-DD HH:mm:ss'
	});
	function gobackList() {
        window.location.href = "${base_path }/org/to_modify_match_view.html";
    }
</script>
<script type="text/javascript">
$("#date_a1").change(function(){
	var time = new Date();
	   // 程序计时的月从0开始取值后+1
	   var m = time.getMonth() + 1;
	   var startTime= time.getFullYear() + "-" + m + "-"
	     + time.getDate() + " " + time.getHours() + ":"
	     + time.getMinutes() + ":" + time.getSeconds();
	   var endTime = document.getElementById("date_a1").value;  
	   startTime = Date.parse(new Date(startTime));
	   endTime = Date.parse(new Date(endTime));
	   if(startTime>=endTime)  {
	    	 layer.msg("报名开始时间不能小于当前时间");
	    	 $(".cxcalendar").css("display","none");
	    	 $("#date_a1").val('');
	     }else{
	     }
	  })
$("#date_b1").change(function(){
	if($("#date_a1").val()==""){
		layer.msg("请先填写报名开始时间");
		$("#date_b1").val("");
	}
var startTime = document.getElementById("date_a1").value;   
var endTime = document.getElementById("date_b1").value;  
startTime = Date.parse(new Date(startTime));
endTime = Date.parse(new Date(endTime));
     if(startTime>=endTime)  {
    	 layer.msg("报名开始时间不能大于结束报名时间");
    	 $(".cxcalendar").css("display","none");
    	 $("#date_b1").val('');
     }else{
     }
  })
$("#date_a").change(function(){
	if($("#date_b1").val()==""){
		layer.msg("请先填写报名结束时间");
		$("#date_a").val("");
	}
var startTime = document.getElementById("date_b1").value;   
var endTime = document.getElementById("date_a").value;  
startTime = Date.parse(new Date(startTime));
endTime = Date.parse(new Date(endTime));
     if(startTime>=endTime)  {
    	 layer.msg("报名结束时间不能大于赛事开始时间");
    	 $(".cxcalendar").css("display","none");
    	 $("#date_a").val('');
     }else{
    	 
     }
    
  })
  $("#date_b").change(function(){
	  if($("#date_a").val()==""){
			layer.msg("请先填写赛事开始时间");
			$("#date_b").val("");
		}
var startTime = document.getElementById("date_a").value;   
var endTime = document.getElementById("date_b").value;  
var start = (new Date(startTime)).getTime();
var end = (new Date(endTime)).getTime();
startTime = Date.parse(new Date(startTime));
endTime = Date.parse(new Date(endTime));

     if(startTime>=endTime)  {
    	 layer.msg("赛事开始时间不能大于赛事结束时间");
    	 $(".cxcalendar").css("display","none");
    	 $("#date_b").val('');
     }else if(((end-start)/86400000) < 1) {
    	 layer.msg("赛事开始时间与赛事结束时间间隔不能小于一天");
    	 $(".cxcalendar").css("display","none");
    	 $("#date_b").val('');
     } else {
    	 
     }
     
  })
  $("#match_name").blur(function(){
	 if( $("#match_name").val()==""){
		 layer.msg("赛事名称不能为空");
	 }
  });

$(function(){
	layer.ready(function(){
		if('${msg}' != '' || '${msg}' == null){
			layer.alert('${msg}');
		}
    });
});

function modify() {
	if( $("#match_name").val()==""){
		 layer.msg("赛事名称不能为空");
		 return;
	}
	if( $("#date_a1").val()==""){
		 layer.msg("报名开始时间不能为空");
		 return;
	}
	if( $("#date_b1").val()==""){
		 layer.msg("报名结束时间不能为空");
		 return;
	}
	if( $("#date_a").val()==""){
		 layer.msg("赛事开始时间不能为空");
		 return;
	}
	if( $("#date_b").val()==""){
		 layer.msg("赛事结束时间不能为空");
		 return;
	}
	$("#modify-form").submit();
}

</script>
</body>
</html>