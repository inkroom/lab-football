<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html lang="zh-cn">
<head>
	<meta charset="utf-8">
	<meta name="renderer" content="webkit|ie-comp|ie-stand">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"	/>
	<meta http-equiv="Cache-Control" content="no-siteapp" />
	<!--[if	lt IE 9]>
	<script	type="text/javascript" src="../lib/html5shiv.js"></script>
	<script	type="text/javascript" src="../lib/respond.min.js"></script>
	<![endif]-->
	<link rel="stylesheet" type="text/css" href="${base_path}/resources/common/static/h-ui/css/H-ui.min.css" />
    <link rel="stylesheet" type="text/css" href="${base_path}/resources/common/static/h-ui/css/H-ui.ie.css" />
    <link rel="stylesheet" type="text/css" href="${base_path}/resources/common/static/h-ui.admin/css/H-ui.admin.css" />
    <link rel="stylesheet" type="text/css" href="${base_path}/resources/common/lib/Hui-iconfont/1.0.8/iconfont.css" />
    <link rel="stylesheet" type="text/css" href="${base_path }/resources/common/static/h-ui.admin/skin/green/skin.css"/>
    <link rel="stylesheet" type="text/css" href="${base_path}/resources/common/static/h-ui.admin/css/style.css" />
	<!--[if	IE 6]>
	<script	type="text/javascript" src="../lib/DD_belatedPNG_0.0.8a-min.js"	></script>
	<script>DD_belatedPNG.fix('*');</script>
	<![endif]-->
	<title>上场队员安排</title>
	<style type="text/css">
		.height{height:71px;line-height: 71px}
		.w-150{width: 150px;height:	35px}
		.f-60{font-size: 60px}
		.f-60:hover{cursor:	pointer;}
		.img_logo{width: 50px;height: 50px}
		.input-text{height:	35px;border:none;text-align: center;}
	</style>
</head>
<body>
	<div class="page-container text-c">
		<div class="cl pd-5	bg-1 bk-gray mt-20">
			<a class="text-r f-r"><h5>比赛总数:${scheduleNum}场</h5></a>
		</div>
		<table class="table	table table-border table-bordered table-bg mt-20">
			<thead class="text-c">
				<tr>
					<th>我的队伍</th>
					<th>赛事对战表</th>
					<th>对手</th>
					<th>操作</th>
				</tr>
			</thead>
			<c:forEach items="${page.records}" var="info" varStatus="vs">
				<tr	class="text-c">
					<td>
						<img onerror="javascript:this.src='${base_path}/resources/common/uploadimg/img/hu.jpg'"  src="${base_path}/${info.rHTeamBadge}" alt="LOGO" class="img_logo">
						<br/>${info.rHTeamName}
					</td>
					<td	style="padding:	0">${info.rTime}</td>
					<td>
						<img onerror="javascript:this.src='${base_path}/resources/common/uploadimg/img/hu.jpg'"  src="${base_path}/${info.rVTeamBadge}" alt="LOGO" class="img_logo">
						<br/>${info.rVTeamName}
					</td>
					<td><a href="#"	class="btn btn-success-outline radius mr-10" onclick="modaldemo('${info.rID}','${info.planContinue}')">安排队员</a><span>&nbsp;&nbsp;已安排${info.arrangedNum}人上场</span></td>
				</tr>	
			</c:forEach>
		</table>
		<c:if test="${page.totalPageNum > 1}">
    		<%@include file="page.jsp"%>
        </c:if>
	</div>
	
	<!-- 模态框部分 -->
	<div id="modal-demo" class="modal fade"	tabindex="-1" role="dialog"	aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content radius">
				<div class="modal-header">
					<h3	class="modal-title">队员信息</h3>
					<a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
				</div>
				<div class="modal-body">
					<div class="page-container">
						<div class="mt-20" ms-controller="listPlayer">
							<table id="playerTbody" class="table	table-border table-bordered	table-bg  table-striped	mt-20 ">
								<tbody id="for_w" class="text-c">
									<tr>
										<th>队员</th>
										<th>年龄</th>
										<th>擅长位置</th>
										<th>个人积分</th>
										<th>操作</th>
									</tr>
									<tr ms-for="value in infoList">
										<td>{{value.PLAYER_NAME}}</td>
										<td>{{value.pAge}}</td>
										<td>{{value.p_POSITION}}</td>
										<td>{{value.p_GRADE_TABLE_ID}}</td>
										<td ms-if="value.isAddMatch==0">
											<button ms-click="operPlanPlayer(value.PLAYER_NAME,value.pID,value.rID,value.isAddMatch)" class="btn btn-success radius">添加</button>
										</td>
										<td ms-if="value.isAddMatch==1">
											<button ms-click="operPlanPlayer(value.PLAYER_NAME,value.pID,value.rID,value.isAddMatch)" class="btn btn-danger radius">移除</button>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button id="okBtn"	class="btn"	data-dismiss="modal" aria-hidden="true">确定</button>
				</div>
			</div>
		</div>
	</div>
<input type="text" id="base_path" hidden="" value="${base_path}"> 	
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
<script type="text/javascript" src="${base_path}/resources/common/avalon/avalon.js"></script>
<%-- <script type="text/javascript" src="${base_path}/resources/js/team/page/team_schedule_arrange.js"></script> ie和火狐不支持这样引入操作，否则avalon无效；vm会提示undefined--%>
<script type="text/javascript">
	
$(function(){
	if('${message}'!=null && '${message}'!=''){
		layer.msg('${message}',{icon: 0, time:4000});
		<% session.removeAttribute("message");%>
	}
});

var base_path=$('#base_path').val();
var vm = avalon.define({
	$id: "listPlayer",
	infoList: [],
	operPlanPlayer:function(pname, pid, rID, type){
		operPlanPlayers(pname, pid, rID, type);
	}
});

$('#okBtn').click(function(){
	 window.location.reload();	
});

function modaldemo(rID, planContinue){
	if(planContinue==1){
		$.ajax({
            url: base_path+"/team/find_team_plan_players.action",
            type: "POST",
            dataType: "json",
            data: {"rID":rID},
            async: true,
            success: function (data) {
                if(data.status==200){
                	var json=eval(data)
                    vm.infoList =json.playersList;
                	$("#modal-demo").modal("show");
                }else{
                	layer.alert(data.message, {icon: 0});
                	if(data.status==500){
            	    	setTimeout(function(){
                    		window.parent.location.replace(base_path+"/team/login_view.html");
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
    		}
        });
	}else{
		layer.alert('比赛已经开始,不能再做更改', {
			skin: 'layer-bule-style'
		    ,closeBtn: 0
	  	});
	}
	
}

function operPlanPlayers(pname, pid, rID, type){
	if(type==1){
		layer.confirm('您确认要移除'+pname+'吗?', {
	  		  btn: ['确认','取消'] //按钮
	  		}, function(){
	  			$.ajax({
		            url: base_path+"/team/team_arrange_players.action",
		            type: "POST",
		            dataType: "json",
		            data: {"rID":rID, "pid":pid, "type":type},
		            async: true,
		            success: function (data) {
		            	
		                if(data.status==200){
		                	//更新球员框
		                	layer.alert(data.message, {icon: 1});
		                	var json =eval(data)
                			vm.infoList =json.playersList;
		                }else{
		                	layer.alert(data.message, {icon: 0});
		                	if(data.status==500){
		            	    	setTimeout(function(){
		                    		window.parent.location.replace(base_path+"/team/login_view.html");
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
		    		}
		        });
	  		}, function(){
	  			
	  		});
	}else{
		$.ajax({
            url: base_path+"/team/team_arrange_players.action",
            type: "POST",
            dataType: "json",
            data: {"rID":rID, "pid":pid, "type":type},
            async: true,
            success: function (data) {
            	
                if(data.status==200){
                	//更新球员框
                	layer.alert(data.message, {icon: 1});
                	var json=eval(data);
                	vm.infoList =json.playersList;
                }else{
                	layer.alert(data.message, {icon: 0});
                	if(data.status==500){
            	    	setTimeout(function(){
                    		window.parent.location.replace(base_path+"/team/login_view.html");
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
    		}
        });
	}
}


</script>

</body>
</html>