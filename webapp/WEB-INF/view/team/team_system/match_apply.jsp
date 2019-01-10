<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <!--[if lt IE 9]>
    <script type="text/javascript" src="../lib/html5shiv.js"></script>
    <script type="text/javascript" src="../lib/respond.min.js"></script>
    <![endif]-->
    <link rel="stylesheet" type="text/css" href="${base_path}/resources/common/static/h-ui/css/H-ui.min.css" />
    <link rel="stylesheet" type="text/css" href="${base_path}/resources/common/static/h-ui/css/H-ui.ie.css" />
    <link rel="stylesheet" type="text/css" href="${base_path}/resources/common/static/h-ui.admin/css/H-ui.admin.css" />
    <link rel="stylesheet" type="text/css" href="${base_path}/resources/common/lib/Hui-iconfont/1.0.8/iconfont.css" />
    <link rel="stylesheet" type="text/css" href="${base_path }/resources/common/static/h-ui.admin/skin/green/skin.css" />
    <link rel="stylesheet" type="text/css" href="${base_path}/resources/common/static/h-ui.admin/css/style.css" />
    <!--[if IE 6]>
    <script type="text/javascript" src="../lib/DD_belatedPNG_0.0.8a-min.js" ></script>
    <script>DD_belatedPNG.fix('*');</script>
    <![endif]-->
    <title>比赛申请</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 球队管理 <span class="c-gray en">&gt;</span> 比赛申请<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	
    <div class="mt-20">
    	<form id="seacherForm" action="" method="post">
        <div class="cl pd-5 bg-1 bk-gray mt-20">
        	<span style="color:#FF0000">注：请在报名审核通过后安排每场比赛的上传球员，请随时关注</span>
            <a class="text-r f-r">
	                <input type="text" id="seacherKeyWord" name="seacherKeyWord" class="input-text" style="width:350px" placeholder="请输入赛事名称" >
	                <button type="button" class="btn btn-success radius" id="seacherBtn"  name=""><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
            </a>
        </div>
        </form>    
        <table class="table table-border table-bordered radius mt-20">
            <thead>
	            <tr class="text-c">
	                <th>比赛名称</th>
	                <th>比赛等级</th>
	                <th>比赛类别</th>
	                <th>比赛类型</th>
	                <th>足球组别</th>
	                <th>报名截止日期</th>
<!-- 	                <th>比赛时间</th> -->
	                <th>操作</th>
	            </tr>
            </thead>
            <tbody>
            <c:if test="${page.records == null || fn:length(page.records) == 0}">
              <tr class="text-c">
              	<td colspan="7" rowspan="5"><span style="color:#ff0000">无赛事信息</span></td>
              </tr>
        	</c:if>
            <c:forEach items="${page.records}" var="info" varStatus="vs">  
       			<tr class="text-c">
	                <td>${info.comName}</td>
	                <td>${info.comBigLevel}</td>
	                <td>${info.comLevel}</td>
	                <td>${info.comType}</td>
	                <td>${info.comGroup}</td>
	                <td>${info.comEndTime}</td>
<%-- 	                <td>${info.comTime}</td> --%>
	                <td>
	                	<c:choose>
                			<c:when test="${info.allowApply == 0}">
								<input type="button" style="opacity:0.6" readonly class="btn btn-success radius" value="${info.applyText}">
							</c:when>
							<c:otherwise>
								<input type="button" onclick="applyMatch('${info.comID}', '${info.comName}')" class="btn btn-success radius" value="申请">
							</c:otherwise>
	                	</c:choose>
	                	<c:choose>
                			<c:when test="${info.allowPlanPlayers == 0}">
								<a href="javascript:void(0);" style="opacity:0.6" class="btn btn-success radius mr-10">球员安排</a>
							</c:when>
							<c:otherwise>
								<a href="${base_path }/team/team_schedule_arrange_view/${info.comID}/1.html" class="btn btn-success radius mr-10">球员安排</a>
							</c:otherwise>
	                	</c:choose>
	                </td>
	            </tr>
			</c:forEach>  
            </tbody>
        </table>
        
      	<c:if test="${page.totalPageNum > 1}">
  			<%@include file="page.jsp"%>
        </c:if>
</div>
</div>
<!--模态框-->

<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="${base_path}/resources/common/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="${base_path}/resources/common/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="${base_path}/resources/common/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="${base_path}/resources/common/static/h-ui.admin/js/H-ui.admin.js"></script>
<!--/_footer 作为公共模版分离出去-->
<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="${base_path}/resources/common/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="${base_path}/resources/common/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${base_path}/resources/common/lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript" src="${base_path}/resources/common/lib/js/bootstrap.js"></script>
<script type="text/javascript">	
	$(function(){
		if('${message}'!=null && '${message}'!=''){
			layer.msg('${message}',{icon: 0});
			<% session.removeAttribute("message");%>
		}
	}); 

	//申请比赛
	function applyMatch(matchID, comName){
		layer.confirm('您确认申请参加当前赛事吗?', {
	  		  btn: ['确认','取消'] //按钮
	  		}, function(){
	  			$.ajax({
	  	            url: "${base_path}/team/team_apply_match.action",
	  	            type: "POST",
	  	            dataType: "json",
	  	            data: {"comID":matchID, "comName":comName},
	  	            async: true,
	  	            success: function (data) {
	  	            	
	  	                if(data.status==200){
	  	                	layer.msg(data.message,{icon: 1});
	  	                	setTimeout(function(){
	  	                		 window.location.reload();
	  	                		},1200);
	  	                }else{
	  	                	layer.msg(data.message,{icon: 0});
	  	                	if(data.status==500){
	  	            	    	setTimeout(function(){
	  	                    		window.parent.location.replace("${base_path}/team/login_view.html");
	  	                    		},2000);
	  	            	    }
	  	                	return false;
	  	                }
	  	            },
	  	    		error : function(req, status, reason) {
	  	    			layer.alert('系统异常,请刷新重试', {
	  	    				skin: 'layer-bule-style'
	  	    			    ,closeBtn: 0
	  	    		  	});
	  	    			 window.location.reload();
	  	    		}
	  	        });
	  		}, function(){
	  			
	  		});
	}
	
	//搜索
	$("#seacherBtn").click(function(){
		
		$('#seacherForm').attr("action", "${base_path }/team/team_seacher_matches.action");
		$("#seacherForm").submit();
	});
	
</script>
</body>
</html>