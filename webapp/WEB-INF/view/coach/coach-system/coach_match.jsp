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
    <link rel="stylesheet" type="text/css" href="${base_path }/resources/common/static/h-ui/css/index.css" />
   <!--[if IE 6]>
<script type="text/javascript" src="${base_path }/resources/common/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->

    <title>个人信息</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 教练员管理 <span class="c-gray en">&gt;</span> 我的比赛<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
    <div class="mt-20">
        <div class="cl pd-5 bg-1 bk-gray mt-20">
            <a class="text-r f-r">
                <span class="select-box radius">
                     <select class="select" size="1" name="select1" id="team_op">
                         <option value="" selected>选择你想查看的球队</option>
                         <c:forEach items="${tInfo }" var="team">
                         <option value="${team.TEAM_ID }">${team.TEAM_NAME }</option>
                         </c:forEach>
                     </select>
				</span>

            </a>

        </div>

        <table class="table table-border table-bordered table-bg  mt-20">
            <thead class="text-c">
            <tr>
                <th>赛事名称</th>
                <th>主队</th>
                <th>客队</th>
                <th>比分</th>
                <th>时间</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody class="text-c" id="matchs">
            </tbody>
        </table>
           <div id="pageNation" class="page-icon" style="margin-top: 50px">

	       </div>
    </div>
</div>

<!--模态框-->
<div id="modal-demo" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content radius">
            <div class="modal-header">
                <h3 class="modal-title">比赛详情</h3>
                <a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
            </div>

            <div class="modal-body">
                <table class="table table-border table-bordered table-bg  mt-10">
                    <tbody class="text-c">
                    <tr>
                        <td width="20%"></td>
                        <td>人数</td>
                        <td>比分</td>
                      
                    </tr>
                    <tr>
                        <td id="h_name">/</td>
                        <td id="h_num">/</td>
                        <td id="h_score">/</td>
                    </tr>
                    <tr>
                        <td id="v_name">/</td>
                        <td id="v_num">/</td>
                        <td id="v_score">/</td>
                    </tr>
                    </tbody>
                    </table>
            </div>
            <div class="modal-body">
                <table class="table table-border table-bordered table-bg  mt-10">
                    <tbody class="text-c" id="warning">
                    <tr>
                        <td width="25%">警告人员姓名</td>
                        <td width="10%">主客队</td>
                        <td width="15%">球员号码</td>
                        <td>原因</td>
                    </tr>
                    </tbody>
                    </table>
            </div>
            <div class="modal-body">
                <table class="table table-border table-bordered table-bg  mt-10">
                    <tbody class="text-c" id="sendoff">
                    <tr>
                        <td width="25%">罚出人员姓名</td>
                        <td width="10%">主客队</td>
                        <td width="15%">球员号码</td>
                        <td>原因</td>
                    </tr>
                    </tbody>
                    </table>
            </div>
            <div class="modal-body">
                <table class="table table-border table-bordered table-bg  mt-20">
                    <tbody class="text-c">
                     <tr id="redp">
                        <td width="25%">红牌情况：</td>
                    </tr>
                     <tr id="dianqiu">
                        <td>罚球点球情况：</td>
                    </tr>
                    <tr id="cuoloupan">
                        <td>严重错漏判情况:</td>
                    </tr>
                    </tbody>
                    </table>
            </div>
            <div class="modal-footer">
                <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
            </div>
        </div>
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
<script>
var path = "${base_path}";
    function modaldemo(rd){
    	$.ajax({
    		url:path+'/coach/match/results_details.action',
    		type:'POST',
    		dataType:'json',
    		data:{rd:rd},
    		success:function(data){
    			var team = "主队";
    			$("#h_name").text("主队:"+data.H_TEAM_NAME);
    			$("#v_name").text("客队:"+data.V_TEAM_NAME);
    			$("#h_num").text(data.H_TEAM_NUM);
    			$("#h_score").text(data.R_REGULAR_H_T_S);
    			$("#v_num").text(data.V_TEAM_NUM);
    			$("#v_score").text(data.R_REGULAR_V_T_S);
    			for(i in data.RaceWarning){
    				if(data.RaceWarning[i].W_TEAM==data.RaceWarning[i].R_V_TEAM_ID){
    					team = "客队";
    				}
    				$("#warning tr:gt(1)").remove();
    				$("#warning").append("<tr><td>"+data.RaceWarning[i].W_NAME+"</td><td>"+team+"</td><td>"+data.RaceWarning[i].W_NUM+"</td><td>"+data.RaceWarning[i].W_REASON+"</td></tr>");
    			}
    			for(i in data.RaceSendOff){
    				if(data.RaceSendOff[i].W_TEAM==data.RaceSendOff[i].R_V_TEAM_ID){
    					team = "客队";
    				}
    				$("#sendoff tr:gt(1)").remove();
    				$("#sendoff").append("<tr><td>"+data.RaceSendOff[i].S_NAME+"</td><td>"+team+"</td><td>"+data.RaceSendOff[i].S_NUM+"</td><td>"+data.RaceSendOff[i].S_REASON+"</td></tr>");
    			}
    			for(i in data.RacePunish){
    				$("#redp").empty();
    				$("#redp").append("<td width='25%'>红牌情况：</td><td>"+data.RacePunish[i].R_RED_C_TEXT+"</td>");
    				$("#dianqiu").empty();
    				$("#dianqiu").append("<td>罚球点球情况：</td><td>"+data.RacePunish[i].R_PENA_TEXT+"</td>");
    				$("#cuoloupan").empty();
    				$("#cuoloupan").append("<td>严重错漏判情况:</td><td>"+data.RacePunish[i].R_WRONG_TEXT+"</td>");
    			}
    			$("#modal-demo").modal("show");
    		},
    		error:function(){
    			$.Huimodalalert('未知错误，请刷新重试！',2000);
    		}
    	});
      }
</script>
<script type="text/javascript">
var path = "${base_path}";
function pSe(pg,totalPage){

	if(pg>totalPage){
		$.Huimodalalert('请输入正确的页数',2000);
		return;
	}
	if(pg<1){
		$("#page").val("1");
		$.Huimodalalert('请输入正确的页数',2000);
		return;
	}
	$("#matchs tr").remove();
	$("#pageNation").html('');
	$.ajax({
		url:path+'/coach/match/team.action',
		type:'POST',
		dataType:'json',
		data:{td:$("#team_op").val(),page:pg},
		success:function(data){			
			for(var i=0;i<data.length-1;i++)
				$("#matchs").append('<tr><td>'+data[i].COM_NAME+'</td><td>'+data[i].H_TEAM_NAME+'</td><td>'+data[i].V_TEAM_NAME+'</td><td>'+data[i].R_REGULAR_H_T_S+'：'+data[i].R_REGULAR_V_T_S+'</td><td>'+new Date(data[i].R_START_TIME.time).toLocaleString()+'到<br>'+new Date(data[i].R_END_TIME.time).toLocaleString()+'</td> <td ><button class="btn btn-success radius" onclick="modaldemo('+data[i].R_ID+')">查看详情</button> </td></tr>');
			
			var page = data[data.length-1].page;
			var totalPage = data[data.length-1].totalPage;
			if(page==1){
				$("#pageNation").append("<span class='page-disabled'><i></i>上一页</span>");
			}else if(page>1){
				pa = page-1;
				$("#pageNation").append("<a class='page-next' onclick='pSe("+pa+','+totalPage+");'>上一页<i></i></a>");
			
			}
			if(page==totalPage){			
				$("#pageNation").append("<span class='page-disabled'><i></i>下一页</span>");
			}else if(page < totalPage){	
				pa = page+1;
				$("#pageNation").append("<a class='page-next' onclick='pSe("+pa+','+totalPage+");'>下一页<i></i></a>");
			}
			$("#pageNation").append('<span class="page-disabled"><span>第'+page+'页,总：'+totalPage+'页&nbsp;跳转到</span><input id="page" class="input-text size-MINI radius" style="width: 40px" value="'+page+'"><button type="button" value="跳转" onclick="pageJump('+totalPage+');" class="btn radius ml-10 size-MINI">跳转</button></span>');
		},
		error:function(){
			$.Huimodalalert('未知错误，请刷新重试！',2000);
		}
	});
}
$("#team_op").change(function(){
	$("#matchs tr").remove();
	$("#pageNation").html('');	
	$.ajax({
		url:path+'/coach/match/team.action',
		type:'POST',
		dataType:'json',
		data:{td:$("#team_op").val()},
		success:function(data){
			for(var i=0;i<data.length-1;i++)
				$("#matchs").append('<tr><td>'+data[i].COM_NAME+'</td><td>'+data[i].H_TEAM_NAME+'</td><td>'+data[i].V_TEAM_NAME+'</td><td>'+data[i].R_REGULAR_H_T_S+'：'+data[i].R_REGULAR_V_T_S+'</td><td>'+new Date(data[i].R_START_TIME.time).toLocaleString()+'到<br>'+new Date(data[i].R_END_TIME.time).toLocaleString()+'</td> <td ><button class="btn btn-success radius" onclick="modaldemo('+data[i].R_ID+')">查看详情</button> </td></tr>');
			
			var page = data[data.length-1].page;
			var totalPage = data[data.length-1].totalPage;
			if(page==1){
				$("#pageNation").append("<span class='page-disabled'><i></i>上一页</span>");
			}else if(page>1){
				pa = page-1;
				$("#pageNation").append("<a class='page-next' onclick='pSe("+pa+','+totalPage+");'>上一页<i></i></a>");
			
			}
			if(page==totalPage){			
				$("#pageNation").append("<span class='page-disabled'><i></i>下一页</span>");
			}else if(page < totalPage){	
				pa = page+1;
				$("#pageNation").append("<a class='page-next' onclick='pSe("+pa+','+totalPage+");'>下一页<i></i></a>");
			}
			$("#pageNation").append('<span class="page-disabled"><span>第'+page+'页,总：'+totalPage+'页&nbsp;跳转到</span><input id="page" class="input-text size-MINI radius" style="width: 40px" value="'+page+'"><button type="button" value="跳转" onclick="pageJump('+totalPage+');" class="btn radius ml-10 size-MINI">跳转</button></span>');
		},
		error:function(){
			$.Huimodalalert('未知错误，请刷新重试！',2000);
		}
	});
});
function pageJump(totalPage){
	var page = $("#page").val();
	pSe(page,totalPage);
}

</script>
</body>
</html>