<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    <link rel="stylesheet" type="text/css" href="${base_path }/resources/common/static/h-ui.admin/css/style.css" />
     <link rel="stylesheet" href="${base_path }/resources/common/lib/jQuery.cxCalendar-1.5.3/css/jquery.cxcalendar.css">
<!--[if IE 6]>
<script type="text/javascript" src="${base_path }/resources/common/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->

    <title>球队信息</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i>  教练员管理 <span class="c-gray en">&gt;</span> 发布训练 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
    <div class="mt-20"></div>
    <div class="mt-20">
        <form class="form form-horizontal" action="${base_path}/coach/coachTraining.action" method="post">
            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-2">标题：</label>
                <div class="formControls col-xs-8 col-sm-9">
                    <input type="text" class="input-text radius" value="" placeholder=""  name="title" id= "title">
                </div>
            </div>
            <div class="row cl ">
                <label class="form-label col-xs-4 col-sm-2">球队：</label>
                <div class="formControls col-xs-8 col-sm-9">
                    <span class="select-box">
                        <select class="select radius" size="1" name="teamid" id="selectd">
                            <option id="select" value="" selected>选择要进行训练的球队</option>
                           <c:forEach items="${tInfo }" var="team">
                         <option value="${team.TEAM_ID }">${team.TEAM_NAME }</option>
                         </c:forEach>
                         </select>
                    </span>
                 </div>
            </div>
            <div class="row cl ">
                <label class="form-label col-xs-4 col-sm-2">时间：</label>
                <div class="formControls col-xs-8 col-sm-9">
                    <input type="text"    id="date_a" class="input-text Wdate" name="time"  readonly>
                </div>
            </div>
            <div class="row cl ">
            <label class="form-label col-xs-4 col-sm-2">地点：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" id="place"  class="input-text radius" value="" placeholder="" name="place">
            </div>
        </div>
            <div class="row cl ">
                <label class="form-label col-xs-4 col-sm-2">备注：</label>
                <div class="formControls col-xs-8 col-sm-9">
                    <textarea name="typs" cols="" rows="" class="textarea radius"  placeholder="" dragonfly="true" onKeyUp="$.Huitextarealength(this,100)"></textarea>
                    <p class="textarea-numberbar"><em class="textarea-length">0</em>/100</p>
                </div>
            </div>
            <div class="row cl">
                <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
                    <input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;" id="submit">
                </div>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token }">
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
<script type="text/javascript" src="${base_path }/resources/common/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="${base_path }/resources/common/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${base_path }/resources/common/lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript" src="${base_path }/resources/common/lib/jQuery.cxCalendar-1.5.3/js/jquery.cxcalendar.min.js"></script>
<script type="text/javascript" src="${base_path }/resources/common/lib/jQuery.cxCalendar-1.5.3/js/jquery.cxcalendar.languages.js"></script>

</body>
<script>
// 默认
$('#date_a').cxCalendar({
    type: 'datetime',
    format: 'YYYY-MM-DD HH:mm:ss'
});
</script>
<script>
	var msg = "${errmsg}";
	if(!(msg==null || msg=="")){
		layer.msg(msg);
	}
</script>
<script>
$("#submit").click(function(){
	var place = $("#place").val();
	if($("#place").val()==""||$("#place").val()==null) 
	{ 
	    layer.msg('地点为必填项！'); 
	    return false; 
	} 
	if(place.length>20)
	{
		layer.msg('地点名称过长,请重新填写!'); 
	    return false; 
	}
})
$("#submit").click(function(){
	if($("#date_a").val()==""||$("#date_a").val()==null) 
	{ 
	    layer.msg('日期为必填项！'); 
	    return false; 
	} 
})

$("#submit").click(function(){

	var id = $("#selectd").val();
	if(id==undefined||id==""||id==null) 
	{ 
	    layer.msg('球队为必填项！'); 
	    return false; 
	} 
})
$("#submit").click(function(){
	var title = $("#title").val();
	if($("#title").val()==""||$("#title").val()==null) 
	{ 
		layer.msg('标题 为必填项！'); 
	    return false; 
	} 
	if(title.length>20)
	{
		layer.msg('标题名过长,请重新填写!'); 
	    return false; 
	}
})
$("#place").blur(function(){
	var place = $("#place").val();
	if($("#place").val()==""||$("#place").val()==null) 
	{ 
	    layer.msg('地点为必填项！'); 
	    return false; 
	} 
	if(place.length>20)
	{
		layer.msg('地点名称过长,请重新填写!'); 
	    return false; 
	}
})
$("#date_a").change(function(){
	if($("#date_a").val()==""||$("#date_a").val()==null) 
	{ 
	    layer.msg('日期为必填项！'); 
	    return false; 
	} 
})

$("#selectd").blur(function(){

	var id = $("#selectd").val();
	if(id==undefined||id==""||id==null) 
	{ 
	    layer.msg('球队为必填项！'); 
	    return false; 
	} 
})
$("#title").blur(function(){
	var title = $("#title").val();
	if($("#title").val()==""||$("#title").val()==null) 
	{ 
	    layer.msg('标题 为必填项！'); 
	    return false; 
	} 
	if(title.length>20)
	{
		layer.msg('标题名过长,请重新填写!'); 
	    return false; 
	}
})
</script>
</html>