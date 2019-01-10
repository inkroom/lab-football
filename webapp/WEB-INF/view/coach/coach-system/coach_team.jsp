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
  <!--[if IE 6]>
<script type="text/javascript" src="${base_path }/resources/common/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->

    <title>我的球队</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 教练员管理 <span class="c-gray en">&gt;</span> 我的球队 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
    <div class="mt-20">
        <div class="cl pd-5 bg-1 bk-gray mt-20">
            <a class="text-r f-r">
                <span class="select-box radius">
                       <select class="select" size="1" name="select1" onchange="se();" id="team_op">
                         <option value="" selected>选择你想查看的球队</option>
                         <c:forEach items="${tInfo }" var="team">
                         <option value="${team.TEAM_ID }">${team.TEAM_NAME }</option>
                         </c:forEach>
                     </select>
</span>

            </a>

        </div>

        <table class="table table-border table-bordered table-bg  table-striped mt-20">
            <tbody class="text-c" id="players">
            <tr>
                <td colspan="" rowspan="4"><img id="team_img" class="avatar size-XXL radius" src="${base_path }/resources/common/static/h-ui/images/ucnter/avatar-default.jpg"></td>
                <td>球队名称</td><td id="team_name" class="playerInfo">无</td>
                <td >球队编号</td><td id="team_id" class="playerInfo">无</td>
            </tr>
            <tr>
                <td>球队积分</td><td id="team_integer"  class="playerInfo">无</td> <td >所属机构</td><td id="team_org"  class="playerInfo">无</td>
            </tr>
            <tr>
                <td>比赛场次</td><td id="team_match" class="playerInfo">无</td>
                <td>平均胜率</td><td id="team_win" class="playerInfo">无</td>
            </tr>
            <tr>
                <td >领队 </td><td id="team_leader" class="playerInfo">无</td>
                <td >教练员</td><td id="team_coach" class="playerInfo">无</td>
            </tr>
            <tr><td colspan="6"></td></tr>
            <tr>
                <th>队员</th><th>年龄</th><th>性别</th><th>擅长位置</th><th>个人积分</th>
            </tr>
            </tbody>
        </table>
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
<script type="text/javascript">
var path = "${base_path}";
	function se(){
		$(".playerInfo").text("无");
		$("#players tr:gt(5)").remove();
		if ($("#team_op").val()!=""){
			$.ajax({
				url:path+"/coach/team/info.action",
				type:"POST",
				dataType:"json",
				data:{td:$("#team_op").val()},
				success: function(data){
					$("#team_img").attr("src","${base_path}"+"/"+data.TEAM_BADGE);
					$("#team_name").text(data.TEAM_NAME);
					$("#team_id").text(data.TEAM_NUM);
					$("#team_integer").text(data.SCORE);
					if(data.TEAM_AFFILIATION==""){
						$("#team_org").text("无");
					}else{
					$("#team_org").text(data.TEAM_AFFILIATION);
					}
					$("#team_match").text(data.MATCH_NUM);
					$("#team_win").text(data.WINNING_RATIO);
					$("#team_leader").text(data.TEAM_LEADER);
					var coachName="";
					for(var i=0;i<data.TEAM_COACHS.length;i++){
						coachName+=data.TEAM_COACHS[i].A_NAME+";";
					}
					if(coachName!="")
					$("#team_coach").text(coachName);
				},
				error: function(){
					$.Huimodalalert('未知错误，请刷新重试！',2000)
				}
			});
		}
		
		$.ajax({
			url:path+"/coach/team/players.action",
			type:"POST",
			dataType:"json",
			data:{td:$("#team_op").val()},
			success: function(data){
				for(i in data){
					if((typeof(data[i].PLAYER_SCORE)=="undefined")){
						data[i].PLAYER_SCORE=0;
					}
				$("#players").append("<tr><td>"+data[i].A_NAME+"</td><td>"+data[i].P_AGE+"</td><td>"+data[i].P_SEX+"</td><td>"+data[i].P_POSITION+"</td><td>"+data[i].PLAYER_SCORE+"</td></tr>");
			
				}
				},
			error: function(){
				$.Huimodalalert('未知错误，请刷新重试！',2000)
			}
		});
	}
</script>
</body>
</html>